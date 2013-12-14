(ns overtone.orchestra.cello
  (:use [overtone.live]))

(def cello-dir "/Users/josephwilk/Workspace/music/samples/instruments/cello/wavs/")
(def cello-samples (remove nil? (map #(try (load-sample %)
                                           (catch Exception e nil)) (file-seq (clojure.java.io/file cello-dir)))))

(defn file->note [{name :name}]
  (let [[_ note duration] (re-find #"cello_([^_]+)_([^_]+)" name)]
    {:note (clojure.string/replace note #"s" "#") :duration duration}))

(defn filter-for [files] (filter #(re-find #"1_forte_arco-normal" (:name %)) files))

(def cello-buffer-ids
  (apply merge
         (map (fn [sample]
                (println (->
                          (file->note sample)
                          :note
                          match-note))
                {(-> (file->note sample)
                     :note
                     match-note
                     :midi-note)
                 (:id sample)})
              (filter-for cello-samples))))

(defonce ^:private silent-buffer (buffer 0))

(defonce index-buffer
  (let [buf (buffer 90)]
    (buffer-fill! buf (:id silent-buffer))
    (doseq [[idx val] cello-buffer-ids]
      (buffer-set! buf idx val))  buf))

(definst cello
  "length: 1, 1/2 1/15 1/25
   valid modes: * 0 forte-arco-normal
                * 1 fortissimo-arco-normal
                * 2 mezzo-piano-arco-normal
                * 3 mezzo-piano-non-vibrato
                * 4 pianissimo-arco-normal"
  [note 60]
  (let [buf (index:kr (:id index-buffer) note)]
    (scaled-play-buf 2 buf :action FREE)))

(cello)
(sample-player (nth cello-samples 13))

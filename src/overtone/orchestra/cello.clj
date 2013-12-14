(ns overtone.orchestra.cello
  (:use [overtone.live]))

;;wget http://www.philharmonia.co.uk/assets/audio/samples/cello/cello.zip
(def cello-dir "/Users/josephwilk/Workspace/music/samples/instruments/cello/wavs")

(def cello-samples (remove nil? (map #(try (load-sample %)
                                           (catch Exception e nil)) (file-seq (clojure.java.io/file cello-dir)))))

(defn file->note [{name :name}]
  (let [[_ note duration] (re-find #"cello_([^_]+)_([^_]+)" name)]
    {:note (clojure.string/replace note #"s" "#") :duration duration}))

(defn filter-for [files length] (filter #(re-find (re-pattern (str "_" length "_forte_arco-normal")) (:name %)) files))

(defn cello-buffer-ids [samples]
  (apply merge
         (map (fn [s]
                {(-> (file->note s)
                     :note
                     match-note
                     :midi-note)
                 (:id s)})
              samples)))

(def forte-arco-15  (cello-buffer-ids (filter-for cello-samples "15")))
(def forte-arco-1   (cello-buffer-ids (filter-for cello-samples "1")))
(def forte-arco-05  (cello-buffer-ids (filter-for cello-samples "05")))
(def forte-arco-025 (cello-buffer-ids (filter-for cello-samples "025")))

(defonce ^:private silent-buffer (buffer 0))

(defonce index-buffer-15
  (let [buf (buffer 128)]
    (buffer-fill! buf (:id silent-buffer))
    (doseq [[idx val] forte-arco-15]
      (buffer-set! buf idx val))
    buf))

(defonce index-buffer-1
  (let [buf (buffer 128)]
    (buffer-fill! buf (:id silent-buffer))
    (doseq [[idx val]  forte-arco-1]
      (buffer-set! buf idx val))
    buf))

(defonce index-buffer-05
  (let [buf (buffer 128)]
    (buffer-fill! buf (:id silent-buffer))
    (doseq [[idx val] forte-arco-05]
      (buffer-set! buf idx val))
    buf))

(defonce index-buffer-025
  (let [buf (buffer 128)]
    (buffer-fill! buf (:id silent-buffer))
    (doseq [[idx val] forte-arco-025]
      (buffer-set! buf idx val))
    buf))

(defonce length-buffer
  (let [buf (buffer 4)]
    (buffer-set! buf 0 (:id index-buffer-15))
    (buffer-set! buf 1 (:id index-buffer-1))
    (buffer-set! buf 2 (:id index-buffer-05))
    (buffer-set! buf 3 (:id index-buffer-025))))

(definst cello
  [note 60 length 0 level 1 rate 1 loop? 0 attack 0 decay 0.5 sustain 1 release 0.1 curve -4 gate 1]
  (let [l-buf (index:kr (:id length-buffer) length)
        buf (index:kr l-buf note)
        env (env-gen (adsr attack decay sustain release level curve)
                     :gate gate
                     :action FREE)]
        (* env (scaled-play-buf 1 buf :level level :loop loop? :action FREE))))

(comment
  (cello :note 50 :length 0)
  (cello :note 50 :length 1)
  (cello :note 50 :length 2)
  (cello :note 50 :length 3)
  )

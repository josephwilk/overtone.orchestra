(ns overtone.orchestra.samples
  (:use overtone.live))

(defonce ^:private silent-buffer (buffer 0))

(defn- instrument-dir [instrument]
  (str (System/getProperty "user.home") "/.overtone/orchestra/" instrument "/"))

(defn load [instrument]
  (remove nil? (map #(try (load-sample %)
                          (catch Exception e nil)) (file-seq (clojure.java.io/file (instrument-dir instrument))))))

(defn- sample->note [sample]
  (let [name (:name sample)
        [_ note duration] (re-find #"^[^_]+_([^_]+)_([^_]+)" name)]

    (println name note duration)

    (-> (clojure.string/replace note #"s" "#")
        match-note
        :midi-note)))

(defn- filter-for [files length type] (filter #(re-find (re-pattern (str "_" length "_" type)) (:name %)) files))

(defn- note-and-buffer-ids [samples] (apply merge (map (fn [s] {(sample->note s) (:id s)}) samples)))

(defn notes-map [samples length style]
  (note-and-buffer-ids (filter-for samples length style)))

(defn samples-style [samples style]
  {:15  (notes-map samples "15" style)
   :1   (notes-map samples "1" style)
   :05  (notes-map samples "05" style)
   :025 (notes-map samples "025" style)})

(defn- init-buffer [note-map]
  (let [buf (buffer 128)]
    (buffer-fill! buf (:id silent-buffer))
    (doseq [[idx val] note-map] (buffer-set! buf idx val))
    buf))

(defn buffer-for [note-map]
  {:15  (init-buffer (:15 note-map))
   :1   (init-buffer (:1 note-map))
   :05  (init-buffer (:05 note-map))
   :025 (init-buffer (:025 note-map))})

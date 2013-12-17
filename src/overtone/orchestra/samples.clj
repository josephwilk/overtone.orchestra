(ns overtone.orchestra.samples
  (:use overtone.live))

(defn instrument-dir [instrument]
  (str (System/getProperty "user.home") "/.overtone/orchestra/" instrument "/"))

(defn load [instrument]
  (remove nil? (map #(try (load-sample %)
                          (catch Exception e nil)) (file-seq (clojure.java.io/file (instrument-dir instrument))))))

(defn sample->note [sample]
  (let [name (:name sample)
        [_ note duration] (re-find #"cello_([^_]+)_([^_]+)" name)]
    (-> (clojure.string/replace note #"s" "#")
        match-note
        :midi-note)))

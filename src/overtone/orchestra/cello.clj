(ns overtone.orchestra.cello)

(def cello-dir "/Users/josephwilk/Workspace/music/samples/instruments/cello/wavs/")
(def cello-samples (file-seq (clojure.java.io/file cello-dir)))

(definst cello
  "length: 1, 1/2 1/15 1/25
   valid modes: * :forte-arco-normal
                * :fortissimo-arco-normal
                * :mezzo-piano-arco-normal
                * :mezzo-piano-non-vibrato
                * :pianissimo-arco-normal
"
  [note 60 length 1 mode :forte-acro-normal]
  (let [buf (index:kr (:id index-buffer) note)
        env (env-gen (adsr attack decay sustain release level curve)
                     :gate gate
                     :action FREE)]
    (* env (scaled-play-buf 2 buf :level level :loop loop? :action FREE))))

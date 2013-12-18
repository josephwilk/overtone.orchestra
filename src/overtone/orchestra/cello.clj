(ns overtone.orchestra.cello
  "Samples: http://www.philharmonia.co.uk/assets/audio/samples/cello/cello.zip"
  (:use [overtone.live])
  (:require [overtone.orchestra.samples :as samples]))

(defonce cello-samples (samples/load "cello"))

(defonce forte-arco              (samples/samples-style cello-samples "forte_arco-normal"))
(defonce fortissimo_arco         (samples/samples-style cello-samples "fortissimo_arco-normal"))
(defonce mezzo-piano_arco-normal (samples/samples-style cello-samples "mezzo-piano_arco-normal"))
(defonce mezzo-piano_non-vibrato (samples/samples-style cello-samples "mezzo-piano_non-vibrato"))
(defonce pianissimo_arco-normal  (samples/samples-style cello-samples "pianissimo_arco-normal"))

(defonce forte-arco-buffers (samples/buffer-for forte-arco))

(defonce length-buffer
  (let [buf (buffer 4)]
    (buffer-set! buf 3 (-> forte-arco-buffers :15 :id))
    (buffer-set! buf 2 (-> forte-arco-buffers :1 :id))
    (buffer-set! buf 1 (-> forte-arco-buffers :05 :id))
    (buffer-set! buf 0 (-> forte-arco-buffers :025 :id))))

(definst cello
  "length options:
   0 -> 0.25
   1 -> 0.5
   2 -> 1
   3 -> 1.5"
  [note 50 length 0 level 1 rate 1 loop? 0 attack 0 decay 0.5 sustain 1 release 0.1 curve -4 gate 1]
  (let [l-buf (index:kr (:id length-buffer) length)
        buf (index:kr l-buf note)
        env (env-gen (adsr attack decay sustain release level curve)
                     :gate gate
                     :action FREE)]
        (* env (scaled-play-buf 1 buf :level level :loop loop? :action FREE))))

(defn free-cello! []
  (doseq [sample cello-samples] (free-sample sample))
  (doseq [length [:15 :1 :05 :025]] (buffer-free (length forte-arco-buffers)))
  (buffer-free length-buffer))

(comment
  (cello :length 0)
  (cello :length 1)
  (cello :length 2)
  (cello :length 3)

  (free-cello!))

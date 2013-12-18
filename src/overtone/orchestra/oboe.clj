(ns overtone.orchestra.oboe
  "Samples: wget http://www.philharmonia.co.uk/assets/audio/samples/oboe/oboe.zip"
  (:use [overtone.live])
  (:require [overtone.orchestra.samples :as samples]))

(defonce oboe-samples (samples/load "oboe"))

(defonce forte-normal       (samples/samples-style oboe-samples "forte_normal"))
(defonce fortissimo-normal  (samples/samples-style oboe-samples "fortisssimo_normal"))
(defonce mezzo-forte-normal (samples/samples-style oboe-samples "mezzo-forte_normal"))
(defonce piano-normal       (samples/samples-style oboe-samples "piano_normal"))

(defonce forte-normal-buffers (samples/buffer-for forte-normal))

(defonce length-buffer
  (let [buf (buffer 4)]
    (buffer-set! buf 0 (-> forte-normal-buffers :15 :id))
    (buffer-set! buf 1 (-> forte-normal-buffers :1 :id))
    (buffer-set! buf 2 (-> forte-normal-buffers :05 :id))
    (buffer-set! buf 3 (-> forte-normal-buffers :025 :id))))

(definst oboe
  [note 60 length 0 level 1 rate 1 loop? 0 attack 0 decay 0.5 sustain 1 release 0.1 curve -4 gate 1]
  (let [l-buf (index:kr (:id length-buffer) length)
        buf (index:kr l-buf note)
        env (env-gen (adsr attack decay sustain release level curve)
                     :gate gate
                     :action FREE)]
        (* env (scaled-play-buf 1 buf :level level :loop loop? :action FREE))))

(comment
  (oboe :note 60 :length 0)
  (oboe :note 60 :length 1)
  (oboe :note 60 :length 2)
  (oboe :note 60 :length 3))

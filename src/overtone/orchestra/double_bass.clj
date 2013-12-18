(ns overtone.orchestra.double-bass
  "Samples: http://www.philharmonia.co.uk/assets/audio/samples/double%20bass/double%20bass.zip"
  (:use [overtone.live])
  (:require [overtone.orchestra.samples :as s]))

(defonce samples (s/load "double-bass"))

(defonce forte-arco-normal            (s/samples-style samples "forte_arco-normal"))
(defonce fortissimo-arco-normal       (s/samples-style samples "fortissimo_arco-normal"))
(defonce mezzo-forte-arco-normal      (s/samples-style samples "mezzo-forte_arco-normal"))
(defonce molto-pianissimo_arco-normal (s/samples-style samples "molto-pianissimo_arco-normal"))
(defonce piano-arco-normal            (s/samples-style samples "piano_arco-normal"))

(defonce forte-arco-buffers (s/buffer-for forte-arco-normal))

(defonce length-buffer
  (let [buf (buffer 4)]
    (buffer-set! buf 0 (-> forte-arco-buffers :15 :id))
    (buffer-set! buf 1 (-> forte-arco-buffers :1 :id))
    (buffer-set! buf 2 (-> forte-arco-buffers :05 :id))
    (buffer-set! buf 3 (-> forte-arco-buffers :025 :id))))

(definst double-bass
  [note 40 length 0 level 1 rate 1 loop? 0 attack 0 decay 0.5 sustain 1 release 0.1 curve -4 gate 1]
  (let [l-buf (index:kr (:id length-buffer) length)
        buf (index:kr l-buf note)
        env (env-gen (adsr attack decay sustain release level curve)
                     :gate gate
                     :action FREE)]
        (* env (scaled-play-buf 1 buf :level level :loop loop? :action FREE))))

(comment
  (double-bass :length 0)
  (double-bass :length 1)
  (double-bass :length 2)
  (double-bass :length 3))

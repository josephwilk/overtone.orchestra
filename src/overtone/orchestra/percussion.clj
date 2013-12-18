(ns overtone.orchestra.percussion
  (:require [overtone.orchestra.samples :as s])
  (:use [overtone.live]
        [overtone.helpers.file :only [glob canonical-path resolve-tilde-path mk-path]]))

(defonce chinese-cymbal-samples (s/load-samples-from-dir (str (s/percussion-dir "Chinese cymbal") "*")))
(defonce chinese-cymbal-buffer (let [buf (buffer 2)] (doall (map-indexed (fn [idx {id :id}]
                                                                       (buffer-set! buf idx id)) chinese-cymbal-samples)) buf))

(definst chinese-cymbal
  "0 -> crescendo roll
   1 -> forte damped"
  [style 0 length 0 level 1 rate 1 loop? 0 attack 0 decay 0.5 sustain 1 release 0.1 curve -4 gate 1]
  (let [buf (index:kr chinese-cymbal-buffer style)
        env (env-gen (adsr attack decay sustain release level curve)
                     :gate gate
                     :action FREE)]
    (* env (scaled-play-buf 1 buf :level level :loop loop? :action FREE))))

(comment (chinese-cymbal :style 1)
         (chinese-cymbal :style 0))

(defonce chinese-hand-cymbal-samples (s/load-samples-from-dir (str (s/percussion-dir "Chinese hand cymbals") "*")))
(def chinese-hand-cymbal-buffer (let [buf (buffer 2)] (doall (map-indexed (fn [idx {id :id}]
                                                                       (buffer-set! buf idx id)) chinese-hand-cymbal-samples)) buf))

(definst chinese-hand-cymbal
  "0 -> 1 mezzo-forte struck together
   1 -> 1.5 forte struck together"
  [style 0 length 0 level 1 rate 1 loop? 0 attack 0 decay 0.5 sustain 1 release 0.1 curve -4 gate 1]
  (let [buf (index:kr chinese-hand-cymbal-buffer style)
        env (env-gen (adsr attack decay sustain release level curve)
                     :gate gate
                     :action FREE)]
    (* env (scaled-play-buf 1 buf :level level :loop loop? :action FREE))))

(comment (chinese-hand-cymbal :style 1)
         (chinese-hand-cymbal :style 0))


(definst thai-gong [])
(definst agogo-bells [])
(definst banana-shaker [])
(definst bass-drum [])
(definst bell-tree [])
(definst cabasa [])
(definst castanets [])
(definst clash-cymbals [])
(definst cowbell [])
(definst djembe [])
(definst djundjun [])
(definst flexatone [])
(definst guiro [])
(definst lemon-shaker [])
(definst motor-horn [])
(definst ratchet [])
(definst sheeps-toenails [])
(definst sizzle-cymbal [])
(definst sleigh-bells [])
(definst snare-drum [])
(definst spring-coil [])
(definst squeaker [])
(definst strawberry-shaker [])
(definst surdo [])
(definst suspended-cymbal [])
(definst swanee-whistle [])
(definst tam-tam [])
(definst tambourine [])
(definst tenor-drum [])
(definst tom-toms [])
(definst train-whistle [])
(definst triangle [])
(definst vibraslap [])
(definst washboard [])
(definst whip [])
(definst wind-chimes [])
(definst woodblock [])

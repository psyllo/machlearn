(ns machlearn.core
  (:require [clatrix.core :as clx]
            [clojure.core.matrix.operators :as m]
            [incanter.core :as ic]
            [incanter.charts :as charts]
            [incanter.stats :as stats]
            [incanter.datasets :as ds])
  (:use [clojure.core.matrix]
        ))

(defn square-mat
  "Creates an n x n matrix (square matrix)."
  [n e & {:keys [impl] :or {impl :persistent-vector}}]
  (let [repeater #(repeat n %)]
    (matrix impl (-> e repeater repeater))))

(defn id-mat
  "Creates and identity matrix of n x n size. Like (identity-matrix n)"
  [n]
  (let [init (square-mat n 0 :impl :clatrix)
        identity-f (fn [i j n] (if (= i j) 1 n))]
    (clx/map-indexed identity-f init)))

(defn rand-square-mat
  "Create a random square matrix of a given size."
  [n]
  (repeatedly n #(map rand-int (repeat n 100))))

(defn rand-square-clmat
  "Create a random square clatrix matrix of a given size."
  [n]
  (clx/map rand-int (square-mat n 100 :impl :clatrix)))

(defn id-computed-mat
  "TODO"
  [])

(defn rand-computed-mat
  "TODO"
  [])

(defn mat-add
  "TODO"
  [])

(defn mat-eq
  "TODO"
  [])

(def X (clx/matrix [1 2 3 4 5]))
(def Y (clx/matrix [1 3 2 7 10]))

(defn plot-model []
  (ic/view (charts/add-lines (charts/scatter-plot X Y)
                             X (:fitted (stats/linear-model Y X)))))
;; (plot-model)
;; (:residuals (stats/linear-model Y X))

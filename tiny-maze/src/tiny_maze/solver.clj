(ns tiny-maze.solver)

; inspired by manuel-colmenero :-)
; https://github.com/manuel-colmenero/wonderland-clojure-katas/blob/master/tiny-maze/src/tiny_maze/solver.clj


(defn start-position
  "Finds the start position in the maze."
  [maze]
  (first (for [x (range (count maze))
              y (range (count (first maze)))
              :when (= :S (get-in maze [x y]))]
            [x y])
  )
)


(defn valid-target-neighbors
  "List of all neighbors of (x, y) in the maze that contain '0' or ':E'."
  [maze [x y]]
  (filter #(#{0 :E} (get-in maze %)) (list [(dec x) y] [(inc x) y] [x (dec y)] [x (inc y)]))
)

; broken down version:
; (defn neighbors [[x y]]
;   "List with the neighbors (existing or not) of position (x, y)."
;   (list [(dec x) y] [(inc x) y] [x (dec y)] [x (inc y)])
; )
; (defn valid-target-position?
;   "True if a step to position (x, y) is allowed, i.e. if the
;   position is in the maze and contains '0' or ':E'."
;   [maze [x y]]
;   (boolean (#{0 :E} (get-in maze [x y])))
; )
; (defn valid-target-neighbors
;   "List of all neighbors of (x, y) in the maze that contain '0' or ':E'."
;   [maze [x y]]
;   (filter #(valid-target-position? maze %) (neighbors [x y]))
; )


(defn walk-recursive [maze [x y]]
  (let [next-maze (assoc-in maze [x y] :x)]
    (if (= (get-in maze [x y]) :E)
      next-maze
      (some #(walk-recursive next-maze %)
            (valid-target-neighbors maze [x y])
            )
    )
  )
)

(defn solve-maze [maze]
  (walk-recursive maze (start-position maze))
)


(ns tiny-maze.solver-test
  (:require [clojure.test :refer :all]
            [tiny-maze.solver :refer :all]))

(def maze3
  [[:S 0 1]
   [1  0 1]
   [1  0 :E]])

(def maze3a
  [[:x 0 1]
   [1  0 1]
   [1  0 :E]])

(def maze4
  [[:E 0 0 0]
   [1  1 0 1]
   [0  0 0 0]
   [:S 0 1 0]])

(def maze5
  [[0  0 :S  0  0]
   [1  0  1  0  1]
   [0  0  1  0  0]
   [0  1  0  1  0]
   [0  0 :E  0  0]])

(deftest start-position-test
  (is (= (start-position maze3) [0 0]))
  (is (= (start-position maze4) [3 0]))
  (is (= (start-position maze5) [0 2]))
)

(deftest valid-target-neighbors-test
  (is (= (valid-target-neighbors maze3 [1 0]) '([1 1])))
  (is (= (valid-target-neighbors maze3 [1 1]) '([0 1] [2 1])))
  (is (= (valid-target-neighbors maze3 [2 1]) '([1 1] [2 2])))
)


; (deftest neighbors-test
;   (is (= (neighbors [3 7]) [[2 7] [4 7] [3 6] [3 8]]))
; )
; (deftest valid-target-position?-test
;   (is (false? (valid-target-position? maze3 [0 0])))
;   (is (true? (valid-target-position? maze3 [0 1])))
;   (is (false? (valid-target-position? maze3 [0 2])))
;   (is (true? (valid-target-position? maze3 [2 2])))
;   (is (false? (valid-target-position? maze3a [0 0])))
; )


(deftest solve-maze-test
  (is (= (solve-maze maze3)) [[:x :x 1] [1 :x 1] [1 :x :x]])
  (is (= (solve-maze maze4)) [[:x :x :x 0] [1 1 :x 1] [:x :x :x 0] [:x 0 1 0]])
  (is (or
    (= (solve-maze maze5) [[0 :x :x 0 0] [1 :x 1 0 1] [:x :x 1 0 0] [:x 1 0 1 0] [:x :x :x 0 0]])
    (= (solve-maze maze5) [[0 0 :x :x 0] [1 0 1 :x 1] [0 0 1 :x :x] [0 1 0 1 :x] [0 0 :x :x :x]])
  ))
)


; ---------------------------------------------------------
; provided by gigasquid

(deftest test-solve-maze
  (testing "can find way to exit with 3x3 maze"
    (let [maze [[:S 0 1]
                [1  0 1]
                [1  0 :E]]
          sol [[:x :x 1]
               [1  :x 1]
               [1  :x :x]]]
      (is (= sol (solve-maze maze)))))

    (testing "can find way to exit with 4x4 maze"
    (let [maze [[:S 0 0 1]
                [1  1 0 0]
                [1  0  0 1]
                [1  1  0 :E]]
          sol [[:x :x :x 1]
                [1  1 :x 0]
                [1  0 :x 1]
                [1  1  :x :x]]]
     (is (= sol (solve-maze maze))))))

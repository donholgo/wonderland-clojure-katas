(ns magic-square.puzzle-test
  (:require [clojure.test :refer :all]
            [magic-square.puzzle :refer :all]))

; --------------------------------------------------------
; example test data

(def square2a
  [[1 1]
   [1 1]])

(def square2b
  [[1 3]
   [5 8]])

(def square3a
  [[1 1 1]
   [1 1 1]
   [1 1 1]])

(def square3b
  [[1 0 3]
   [0 0 0]
   [5 0 8]])

(def square3c
  [[8 1 6]
   [3 5 7]
   [4 9 2]])

(def square4
  [[16 5 9 4]
  [2 11 7 14]
  [3 10 6 15]
  [13 8 12 1]]
)

(def numbers2 [1 2])
(def numbers3 [1 2 3])
(def numbers4 [1 2 3 4])
(def numbers9a (range 1 10))
(def numbers9b [1.0 1.5 2.0 2.5 3.0 3.5 4.0 4.5 5.0])


; --------------------------------------------------------
; functions for 3×3 squares

(deftest row-sums-test
  (is (= (row-sums square2a) '(2 2)))
  (is (= (row-sums square2b) '(4 13)))
  (is (= (row-sums square3a) '(3 3 3)))
  (is (= (row-sums square3b) '(4 0 13)))
  (is (= (row-sums square3c) '(15 15 15)))
  (is (= (row-sums square4) '(34 34 34 34)))
)

(deftest column-sums-test
  (is (= (column-sums square3a) '(3 3 3)))
  (is (= (column-sums square3b) '(6 0 11)))
  (is (= (column-sums square3c) '(15 15 15)))
)

(deftest diagonal-sums-test
  (is (= (diagonal-sums square3a) '(3 3)))
  (is (= (diagonal-sums square3b) '(9 8)))
  (is (= (diagonal-sums square3c) '(15 15)))
)

(deftest magic?-test
  (is (true?  (magic? square3a)))
  (is (false? (magic? square3b)))
  (is (true?  (magic? square3c)))
)


; --------------------------------------------------------
; functions for number vectors

(deftest to-square-test
  (is (=
    (to-square numbers9a)
    [[1 2 3] [4 5 6] [7 8 9]]
  ))
  (is (=
    (to-square numbers9b)
    [[1.0 1.5 2.0] [2.5 3.0 3.5] [4.0 4.5 5.0]]
  ))
)

(deftest permutations-test
  (is (=
    (permutations numbers2)
    '((1 2) (2 1))
  ))
  (is (=
    (permutations numbers3)
    '((1 2 3) (1 3 2) (2 1 3) (2 3 1) (3 1 2) (3 2 1))
  ))
)

(deftest magic-square-test
  (is (=
    (magic-square numbers9a)
    [[2 7 6] [9 5 1] [4 3 8]]
  ))
  (is (=
    (magic-square numbers9b)
    [[1.5 4.0 3.5] [5.0 3.0 1.0] [2.5 2.0 4.5]]
  ))
)


; --------------------------------------------------------
; functions for squares of arbitrary size

(deftest column-sums*-test
  (is (= (column-sums* square2a) '(2 2)))
  (is (= (column-sums* square2b) '(6 11)))
  (is (= (column-sums* square3a) '(3 3 3)))
  (is (= (column-sums* square3b) '(6 0 11)))
  (is (= (column-sums* square3c) '(15 15 15)))
  (is (= (column-sums* square4) '(34 34 34 34)))
)

(deftest diagonal-sums*-test
  (is (= (diagonal-sums* square2a) '(2 2)))
  (is (= (diagonal-sums* square2b) '(9 8)))
  (is (= (diagonal-sums* square3a) '(3 3)))
  (is (= (diagonal-sums* square3b) '(9 8)))
  (is (= (diagonal-sums* square3c) '(15 15)))
  (is (= (diagonal-sums* square4) '(34 34)))
)

(deftest to-square*-test
  (is (=
    (to-square* numbers4)
    [[1 2] [3 4]]
  ))
  (is (=
    (to-square* numbers9a)
    [[1 2 3] [4 5 6] [7 8 9]]
  ))
)

(deftest magic?*-test
  (is (true?  (magic?* square2a)))
  (is (false? (magic?* square2b)))
  (is (true?  (magic?* square3a)))
  (is (false? (magic?* square3b)))
  (is (true?  (magic?* square3c)))
  (is (true?  (magic?* square4)))
)


; --------------------------------------------------------
; default tests already provided by gigasquid

(defn sum-rows [m]
  (map #(reduce + %) m))

(defn sum-cols [m]
  [(reduce + (map first m))
   (reduce + (map second m))
   (reduce + (map last m))])

(defn sum-diagonals [m]
  [(+ (get-in m [0 0]) (get-in m [1 1]) (get-in m [2 2]))
   (+ (get-in m [2 0]) (get-in m [1 1]) (get-in m [0 2]))])

(deftest test-magic-square
  (testing "all the rows, columns, and diagonal add to the same number"
    (is (= (set (sum-rows (magic-square values)))
           (set (sum-cols (magic-square values)))
           (set (sum-diagonals (magic-square values)))))

    (is (= 1
           (count (set (sum-rows (magic-square values))))
           (count (set (sum-cols (magic-square values))))
           (count (set (sum-diagonals (magic-square values))))))))

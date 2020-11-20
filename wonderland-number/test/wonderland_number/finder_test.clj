(ns wonderland-number.finder-test
  (:require [clojure.test :refer :all]
            [wonderland-number.finder :refer :all]))

(deftest number-to-sorted-vector-test
  (is (= (number-to-sorted-vector 123) '("1" "2" "3")))
  (is (= (number-to-sorted-vector 221133) '("1" "1" "2" "2" "3" "3")))
)

(deftest same-digits-test
  (is (= (same-digits? 3 3) true))
  (is (= (same-digits? 5 6) false))
  (is (= (same-digits? 123 123) true))
  (is (= (same-digits? 123 456) false))
  (is (= (same-digits? 123 321) true))
  (is (= (same-digits? 123 1223) false))
)

(deftest possible-wonderland-number?-test
  (is (= (possible-wonderland-number? 42) false))
  (is (= (possible-wonderland-number? 100000) false))
  (is (= (possible-wonderland-number? 142857) true))
  (is (= (possible-wonderland-number? 1234567) false))
)


; ---------------------------------------------
; tests provided by gigasquid

(defn hasAllTheSameDigits? [n1 n2]
  (let [s1 (set (str n1))
        s2 (set (str n2))]
    (= s1 s2)))

(deftest test-wonderland-number
  (testing "A wonderland number must have the following things true about it"
    (let [wondernum (wonderland-number)]
      (is (= 6 (count (str wondernum))))
      (is (hasAllTheSameDigits? wondernum (* 2 wondernum)))
      (is (hasAllTheSameDigits? wondernum (* 3 wondernum)))
      (is (hasAllTheSameDigits? wondernum (* 4 wondernum)))
      (is (hasAllTheSameDigits? wondernum (* 5 wondernum)))
      (is (hasAllTheSameDigits? wondernum (* 6 wondernum))))))

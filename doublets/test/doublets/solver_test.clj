(ns doublets.solver-test
  (:require [clojure.test :refer :all]
            [doublets.solver :refer :all]))


(deftest one-letter-diff?-test
  (is (true? (one-letter-diff? "cat" "hat")))
  (is (false? (one-letter-diff? "cat" "cats")))
  (is (false? (one-letter-diff? "one" "two")))
)

(deftest vector-contains?-test
  (is (true? (vector-contains? [1 2 3] 2)))
  (is (false? (vector-contains? [1 2 3] 4)))
)

(deftest add-possible-continuations-test
  (is (=
    (add-possible-continuations ["look"])
    '(["look" "book"] ["look" "lock"] ["look" "loon"] ["look" "loot"] ["look" "cook"])
))
  (is (=
    (add-possible-continuations ["look" "book"])
    '(["look" "book" "boor"] ["look" "book" "bonk"] ["look" "book" "cook"])
))
)

(deftest search-test
  (is (=
    (search "wheat" "cheap")
    '(["wheat"] ["wheat" "cheat"] ["wheat" "cheat" "cheap"])
  ))
  (is (=
    (search "door" "look")
    '(["door"] ["door" "boor"] ["door" "boor" "book"] ["door" "boor" "book" "look"] ["door" "boor" "book" "bonk"] ["door" "boor" "book" "bonk" "bank"] ["door" "boor" "book" "cook"] ["door" "boor" "book" "cook" "look"])
  ))
)

(deftest doublets-test
  (is (=
    ["head" "heal" "teal" "tell" "tall" "tail"]
    (doublets "head" "tail")
  ))
  (is (=
    (doublets "door" "lock")
    ["door" "boor" "book" "look" "lock"]
  ))
  (is (=
    (doublets "bank" "loan")
    ["bank" "bonk" "book" "look" "loon" "loan"]
  ))
  (is (=
    (doublets "wheat" "bread")
    ["wheat" "cheat" "cheap" "cheep" "creep" "creed" "breed" "bread"]
  ))
  (is (=
    (doublets "door" "tall")
    []
  ))
  (is (=
    []
    (doublets "ye" "freezer")
  ))
)






; ---------------------------------------------------------------
; tests provided by gigasquid

(deftest solver-test
  (testing "with word links found"
    (is (= ["head" "heal" "teal" "tell" "tall" "tail"]
           (doublets "head" "tail")))

    (is (= ["door" "boor" "book" "look" "lock"]
           (doublets "door" "lock")))

    (is (= ["bank" "bonk" "book" "look" "loon" "loan"]
           (doublets "bank" "loan")))

    (is (= ["wheat" "cheat" "cheap" "cheep" "creep" "creed" "breed" "bread"]
           (doublets "wheat" "bread"))))

  (testing "with no word links found"
    (is (= []
           (doublets "ye" "freezer")))))

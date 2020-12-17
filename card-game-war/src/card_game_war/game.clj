(ns card-game-war.game)

;; feel free to use these cards or use your own data structure
(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))

(defn rank-index [rank]
  (.indexOf ranks rank)
)

(defn suit-index [suit]
  (.indexOf suits suit)
)

(defn first-card-better? [card1 card2]
  "true if card1 is better than card2, otherwise false."
  (let [suit1-index (suit-index (first card1))
        rank1-index (rank-index (second card1))
        suit2-index (suit-index (first card2))
        rank2-index (rank-index (second card2))]
    (if (= rank1-index rank2-index)
      (> suit1-index suit2-index)
      (> rank1-index rank2-index))
    )
)

(defn play-round [player1-card player2-card]
  "Determine the winner of a round with the two played cards."
  (if (first-card-better? player1-card player2-card)
    :player1
    :player2
    )
  )

(defn play-game [player1-cards player2-cards]
  (cond (empty? player1-cards) :player2-wins
        (empty? player2-cards) :player1-wins
        :else (let [[card1 & rest1] player1-cards
                    [card2 & rest2] player2-cards]
                (if (= :player1 (play-round card1 card2))
                  (recur (conj rest1 card1 card2) rest2)
                  (recur rest1 (conj rest2 card1 card2))
                  )
                )
        )
  )


(defn run-game [cards]
  (let [shuffled-cards (split-at (/ (count cards) 2) (shuffle cards))
        player-1-cards (first shuffled-cards)
        player-2-cards (last shuffled-cards)]
    (if (= :player1-wins (play-game player-1-cards player-2-cards))
      (println "Player 1 wins.")
      (println "Player 2 wins.")
      )
    )
  )

(run-game cards)

(ns fox-goose-bag-of-corn.puzzle)

(def start-pos [[:fox :goose :corn :you] [:boat] []])

(defn river-crossing-plan []
  [
    start-pos
    [[:fox :corn]        [:boat :goose :you] []]
    [[:fox :corn]        [:boat]             [:goose :you]]
    [[:fox :corn]        [:boat :you]        [:goose]]
    [[:fox :corn :you]   [:boat]             [:goose]]
    [[:corn]             [:boat :fox :you]   [:goose]]
    [[:corn]             [:boat]             [:goose :fox :you]]
    [[:corn]             [:boat :goose :you] [:fox]]
    [[:corn :goose :you] [:boat]             [:fox]]
    [[:goose]            [:boat :corn :you]  [:fox]]
    [[:goose]            [:boat]             [:fox :corn :you]]
    [[:goose]            [:boat :you]        [:fox :corn]]
    [[:goose :you]       [:boat]             [:fox :corn]]
    [[]                  [:boat :goose :you] [:fox :corn]]
    [[]                  [:boat]             [:fox :corn :goose :you]]
  ]
)

(ns secret-santa.models.db)

(def *db* (atom {}))

(def *id* (atom -1))

(defn gen-id []
  (swap! *id* inc))

(defn add [val]
  (let [id (gen-id)]
    (swap! *db* assoc id val ;; (merge {:id id} val)
           )
    id))

#_(defn where [a b]
  (str "You called?"))

(defn where [keyword value]
  (filter (fn [x] (= (keyword x) value)) @*db*))

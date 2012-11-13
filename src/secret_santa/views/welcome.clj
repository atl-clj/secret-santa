(ns secret-santa.views.welcome
  (:require [secret-santa.views.common :as common]
            [secret-santa.models.db :as db])
  (:use [noir.core :only [defpage defpartial]]
        [hiccup.form :only [label form-to  submit-button text-field]]))


(defpartial user-fields [{:keys [firstname lastname]}]
  (label "firstname" "First name: ")
  (text-field "firstname" firstname)
  (label "lastname" "Last name: ")
  (text-field "lastname" lastname))

(defpage "/user/add" {:as user}
  (common/layout
    (form-to [:post "/user/add"]
            (user-fields user)
            (submit-button "Add user"))))

(defpage [:post "/user/add"]  {:as user}
  (db/add user)
  (common/layout
   [:p "User Added"]
   (str @db/*db*)
   (str "where: "
        (map str (db/where :firstname "eric")))))




;; (defpage "/user/list" {}
;;   (common/layout
;;     (form-to [:post "/user/add"]
;;             (user-fields user)
;;             (submit-button "Add user"))))

(def valid? :t)

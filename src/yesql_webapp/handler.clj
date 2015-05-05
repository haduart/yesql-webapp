(ns yesql-webapp.handler
  (:use [compojure.core]
        [clojure.java.io :only (file resource)])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [cheshire.core :refer :all]
            [cheshire.generate :refer [add-encoder encode-str remove-encoder]]
            [ring.util.response :as resp]
            [ring.adapter.jetty :as jetty]
            [clojure.data.json :as json]
            [yesql.core :refer [defqueries]]))

(defqueries "test.sql")


(def db-spec {:subprotocol "mysql"
              :subname     "//localhost:3306/yesqltest"
              :user        "root"})

(defn json-response [data & [status]]
  {:status  (or status 200)
   :headers {"Content-Type" "application/json"}
   :body    (json/write-str data)})

(defroutes app-routes
  (GET "/" req (json-response (select-all db-spec)))
  (GET "/findName" [name] (json-response (find-name db-spec name)))
  (GET "/api/test/" [] (json-response {:response "response"})))

(defn init []
  (println "Running migrations")
  ;(clojure.pprint/pprint (select-all db-spec))
  (println "Migrations done!"))

(def app
  (handler/site app-routes))

(defn -main [& [port]]
  (let [port (Integer. (or port (System/getenv "PORT")))]
    (jetty/run-jetty app-routes
      {:port port :join? false})))
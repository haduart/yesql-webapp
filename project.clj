(defproject yesql-webapp "0.1.0-SNAPSHOT"
  :description "Example of Yesql inside a webapp"
  :url ""
  :license {:name "BSD"
            :url  "http://www.opensource.org/licenses/BSD-3-Clause"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.2"]
                 [cheshire "5.4.0"]
                 [clj-http "1.0.1"]
                 [org.clojure/data.json "0.2.5"]
                 [ring/ring-jetty-adapter "1.3.2"]
                 [clj-http "1.0.1"]
                 [javax.servlet/servlet-api "2.5"]
                 [yesql "0.4.1"]
                 [mysql/mysql-connector-java "5.1.32"]]

  :plugins [[lein-ring "0.8.10"]
            [lein-midje "3.0.0"]]
  :min-lein-version "2.0.0"
  :ring {:handler yesql-webapp.handler/app
         :init    yesql-webapp.handler/init}
  :main yesql-webapp.handler

  :profiles {:dev {:dependencies [[ring-mock "0.1.5"]
                                  [midje "1.6.3"]]}})
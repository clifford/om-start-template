;;; This namespace is used for development and testing purpose only.
(ns ring.server
  (:require [cemerick.austin.repls :refer (browser-connected-repl-js)]
            [net.cgrand.enlive-html :as enlive]
            ;; [compojure.route :refer  (resources)]
            [compojure.core :refer [defroutes GET PUT]]
            [ring.adapter.jetty :as jetty]
            [clojure.java.io :as io]
            ;; [ring.util.response :refer [file-response]]
            [ring.middleware.edn :refer [wrap-edn-params]]
            [compojure.route :as route]
            [compojure.handler :as handler])
  (:use [om-datomic.core :as core]))

(enlive/deftemplate page
  (io/resource "public/index.html")
  []
  [:body] (enlive/append
            (enlive/html [:script (browser-connected-repl-js)])))

;; (defroutes site
;;   (resources "/")
;;   (GET "/*" req (page)))

(defroutes routes
  ;; (GET "/" [] (index))
  (GET "/" [] (page))
  (GET "/classes" [] (core/classes))
  (PUT "/class/:id/update"
    {params :params edn-params :edn-params}
    (update-class (:id params) edn-params))
  (route/files "/" {:root "dev-resources/public"}))

(def app
  (-> routes
      wrap-edn-params))

(defn run
  "Run the ring server. It defines the server symbol with defonce."
  []
  (defonce server
    (jetty/run-jetty #'app {:port 3000 :join? false}))
  server)

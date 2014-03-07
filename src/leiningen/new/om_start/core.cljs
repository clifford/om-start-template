(ns {{namespace}}.core
  (:require [cljs.reader :as reader]
            [goog.events :as events]
            [goog.dom :as gdom]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true])
  (:import [goog.net XhrIo]
           goog.net.EventType
           [goog.events EventType]))



(enable-console-print!)

(println "Hello world!")

(def app-state (atom {:text "Hello world!"}))

(om/root
  (fn [app owner]
    (dom/h1 nil (:text app)))
  app-state
  {:target (. js/document (getElementById "app"))})

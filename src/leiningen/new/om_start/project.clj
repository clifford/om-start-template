(defproject {{raw-name}} "0.0.1-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License - v 1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo}

  :min-lein-version "2.3.4"

  :source-paths ["src/clj" "src/cljs"]
  :resource-paths ["dev-resources"]

  :jvm-opts ^:replace ["-Xmx1g" "-server"]

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2156"]
                 [org.clojure/core.async "0.1.267.0-0d7780-alpha"]
                 [om "0.5.0"]
                 [com.facebook/react "0.9.0"]]

  :plugins [[lein-cljsbuild "1.0.2"]]

  :hooks [leiningen.cljsbuild]

  :cljsbuild
  {:builds {:{{name}}
            {:source-paths ["src/clj" "src/cljs"]
             :compiler
             {:output-to "dev-resources/public/js/{{sanitized}}.js"
              :output-dir "dev-resources/public/js/"
              :optimizations :whitespace
              :source-map "dev-resources/public/js/{{sanitized}}.js"
              :pretty-print true}}}})

;; combination of :whitespace and :pretty-print true will use the Google Closure compiler and will not require any changes to the html file when you switch it to :advanced mode. Also, it consolidates all .cljs files into a single .js file specified by :output-to, therefore no need for the :output-dir. :output-dir only needed when using :none mode, as files are then 1-to-1 between .cljs and .js and are written to the :output-dir

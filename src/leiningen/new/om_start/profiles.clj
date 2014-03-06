{:shared {:clean-targets ["out" :target-path]}

 :tdd [:shared
       {:cljsbuild
        {:builds {:{{name}}
                  {:compiler
                   {:optimizations :whitespace
                    :pretty-print true}}}}}]

 :dev [:shared
       {:resources-paths ["dev-resources"]
        :source-paths ["dev-resources/tools/http" "dev-resources/tools/repl"]
        :dependencies [[ring "1.2.1"]
                       [compojure "1.1.6"]
                       [enlive "1.1.5"]
                       [fogus/ring-edn "0.2.0"]
                       [com.datomic/datomic-free "0.9.4532"]]
        :plugins [[com.cemerick/austin "0.1.4"]]
        :cljsbuild
        {:builds {:{{name}}
                  {:source-paths ["dev-resources/tools/repl"]
                   :compiler
                   {:optimizations :whitespace
                    :pretty-print true}}}}

        :injections [(require '[ring.server :as http :refer [run]]
                              'cemerick.austin.repls)
                     (defn browser-repl-env []
                       (reset! cemerick.austin.repls/browser-repl-env
                                (cemerick.austin/repl-env)))
                     (defn browser-repl []
                       (cemerick.austin.repls/cljs-repl
                         (browser-repl-env)))]}]}

(defproject re-frame-forms "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.773"]
                 [reagent "0.10.0" ]
                 [re-frame "1.2.0"]
                 [day8.re-frame/re-frame-10x "1.2.0"]]

  :source-paths ["src"]

  :aliases {"fig"       ["trampoline" "run" "-m" "figwheel.main"]
            "fig:build" ["trampoline" "run" "-m" "figwheel.main" "-b" "dev" "-r"]
            "fig:min"   ["run" "-m" "figwheel.main" "-O" "advanced" "-bo" "dev"]
            "fig:test"  ["run" "-m" "figwheel.main" "-co" "test.cljs.edn" "-m" "re-frame-forms.test-runner"]}

  :profiles {:dev {:dependencies [[com.bhauman/figwheel-main "0.2.15"]
                                  [com.bhauman/rebel-readline-cljs "0.1.4"]
                                  [day8.re-frame/tracing "0.6.2"] 
                                  [day8.re-frame/re-frame-10x "1.0.2"]]
                   
                   :resource-paths ["target"]
                   ;; need to add the compiled assets to the :clean-targets
                   :clean-targets ^{:protect false} ["target"]}})


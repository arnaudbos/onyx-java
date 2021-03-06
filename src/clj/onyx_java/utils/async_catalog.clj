(ns onyx-java.utils.async-catalog
  (:gen-class))

(defn in-catalog [task-name batch-size batch-timeout]
  "Generates a core.async input plugin catalog entry"
  {:onyx/name (keyword task-name)
   :onyx/plugin :onyx.plugin.core-async/input
   :onyx/type :input
   :onyx/medium :core.async
   :onyx/max-peers 1
   :onyx/batch-timeout batch-timeout
   :onyx/batch-size batch-size })

(defn out-catalog [task-name batch-size batch-timeout]
  "Generates a core.async output plugin catalog entry"
  {:onyx/name (keyword task-name)
   :onyx/plugin :onyx.plugin.core-async/output
   :onyx/type :output
   :onyx/medium :core.async
   :onyx/max-peers 1
   :onyx/batch-timeout batch-timeout
   :onyx/batch-size batch-size })


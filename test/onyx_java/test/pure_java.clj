(ns onyx-java.test.pure-java
  (:gen-class)
  (:import [onyxplatform.test SingleJavaTest SingleCljTest])
  (:require [clojure.test :refer [deftest is]]))

(deftest single-java-test
    (let [testObject (SingleJavaTest. "onyx-env.edn")
          inputs [{:pass-through "PASSTHROUGH"}]
          expected {:out [{:pass-through "PASSTHROUGH"} :done]}
          outputs (.runJobCollectOutputs testObject [{:pass-through "PASSTHROUGH"}])]
        (.shutdown testObject)
        (is (= (first inputs) (first (:out outputs))))))

(deftest single-clj-test
    (let [testObject (SingleCljTest. "onyx-env.edn")
          inputs [{:pass-through "PASSTHROUGH"}]
          expected {:out [{:pass-through "PASSTHROUGH"} :done]}
          outputs (.runJobCollectOutputs testObject [{:pass-through "PASSTHROUGH"}])]
        (.shutdown testObject)
        (is (= (first inputs) (first (:out outputs))))))

(deftest kill-test
    (let [testObject (SingleJavaTest. "onyx-env.edn")
          inputs [{:pass-through "PASSTHROUGH"}]
          expected true
          job-meta (.runJob testObject [{:pass-through "PASSTHROUGH"}])
          env (.getOnyx testObject) ]
      (try
        (is (.killJob env job-meta))
        (finally (.shutdown testObject)))))

(deftest await-test
    (let [testObject (SingleJavaTest. "onyx-env.edn")
          inputs [{:pass-through "PASSTHROUGH"}]
          expected "Success!"
          job-meta (.runJob testObject [{:pass-through "PASSTHROUGH"}])
          env (.getOnyx testObject)]
      (try
        (is (.awaitJobCompletion env job-meta))
        (finally (.shutdown testObject)))))

(deftest gc-test
    (let [testObject (SingleJavaTest. "onyx-env.edn")
          inputs [{:pass-through "PASSTHROUGH"}]
          expected "Success!"
          job-meta (.runJob testObject [{:pass-through "PASSTHROUGH"}])
          env (.getOnyx testObject) ]
      (try
        (is (.gc env))
        (finally (.shutdown testObject)))))


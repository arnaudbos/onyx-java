(ns onyx-java.utils.test-template
    (:gen-class)
    (:require [onyx-java.utils.persistence :as persistence]
              [onyx-java.utils.edn :as edn]
              [onyx-java.utils.filter :as filter]
              [onyx-java.wrapper.entity :as entity]
              [onyx-java.wrapper.vector :as vector]
              [onyx-java.wrapper.workflow :as workflow]
              [onyx-java.wrapper.scheduler :as scheduler]))

(defn create-master-map [dir]
    (let [master {}
          specvec-maker (edn/specvec-maker dir)
          specs (edn/get-specs dir)
          spec-vecs (map specvec-maker specs)]
    (apply merge (persistence/create-map master spec-vecs))))

(defn coerce-entities [object-map]
    (let [entities (filter/filter-by-base object-map "OnyxEntity")
          names (keys entities)
          mapper (entity/get-clojure-entry entities)]
    (apply merge (map mapper names))))

(defn get-expected-entities [object-map dir]
    (let [entities (filter/filter-by-base object-map "OnyxEntity")]
        (filter/make-comparison-map entities (filter/output-compare-fn dir))))

(defn add-entity-params [object-map dir]
    (let [entities (filter/object-compare-map
            (filter/filter-by-base object-map "OnyxEntity"))
          adder (fn [entity]
                    (let [source (val entity)
                           edn (edn/get-edn-from-spec dir source)
                           vectors (edn/read-map-edn edn)]
                (entity/add-parameters (key entity) object-map vectors)))]
          (dorun (map adder entities))))

(defn get-expected-vectors [object-map dir]
  (let [entities (filter/filter-by-base object-map "OnyxVector")]
      (filter/make-comparison-map entities (filter/output-compare-fn dir))))

(defn vector-frequency [object-map]
    (let [freq-fcn (fn [entry]
                    (let [k (key entry)
                          v (val entry)
                          freq (frequencies v)]
                          {k freq}))]
    (apply merge (map freq-fcn object-map))))

(defn coerce-vectors [object-map]
  (let [vectors (filter/filter-by-base object-map "OnyxVector")
        names (keys vectors)
        mapper (vector/get-clojure-entry vectors)]
  (apply merge (map mapper names))))

(defn add-vector-entities [object-map]
    (let [entities (filter/filter-by-base object-map "OnyxEntity")
          vectors (filter/filter-by-base object-map "OnyxVector")
          adder (fn [vector]
                (let [ent-type (vector/get-entity-type vector)
                    vector-entities (filter/filter-by-class entities ent-type)]
                    [vector vector-entities]))
          vector-entity-array (map adder vectors)]
          (vector/add-all-entities vector-entity-array)))

(defn coerce-workflows [object-map]
    (let [workflows (filter/filter-by-class object-map "Workflow")
          names (keys workflows)
          mapper (workflow/get-clojure-entry workflows)]
          (apply merge (map mapper names))))

(defn add-workflow-edges [object-map dir]
    (let [workflows (filter/filter-by-class object-map "Workflow")
          adder (fn [workflow]
                    (let [source (get (val workflow) :source)
                          edn (edn/get-edn-from-spec dir source)
                          edges (edn/read-vector-edn edn)]
                (workflow/add-edges (key workflow) object-map edges)))]
                (dorun (map adder workflows))))

(defn get-expected-workflows [object-map dir]
    (let [workflows (filter/filter-by-class object-map "Workflow")]
    (filter/make-comparison-map workflows (filter/output-compare-fn dir))))

(defn coerce-schedulers [object-map]
    (let [schedulers (filter/filter-by-class object-map "TaskScheduler")
          names (keys schedulers)
          mapper (scheduler/get-clojure-entry schedulers)]
    (apply merge (map mapper names))))

(defn get-expected-schedulers [object-map dir]
    (let [schedulers (filter/filter-by-class object-map "TaskScheduler")]
    (filter/make-comparison-map schedulers (filter/output-compare-fn dir))))

(defn add-job-components [object-map]
    (let []))

(defn coerce-job [object-map]
    (let []))

(defn get-expected-job [object-map]
    (let []))

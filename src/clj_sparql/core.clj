 ; Copyright (C) 2014 Al Baker
 ;
 ; Licensed under the Apache License, Version 2.0 (the "License");
 ; you may not use this file except in compliance with the License.
 ; You may obtain a copy of the License at
 ;
 ;      http://www.apache.org/licenses/LICENSE-2.0
 ;
 ; Unless required by applicable law or agreed to in writing, software
 ; distributed under the License is distributed on an "AS IS" BASIS,
 ; WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ; See the License for the specific language governing permissions and
 ; limitations under the License.

(ns clj-sparql.core
   (:import (java.util Date
                      Calendar)
            (com.hp.hpl.jena.query Query
                                   QueryExecution
                                   QueryExecutionFactory
                                   ARQ
                                   QueryFactory
                                   QuerySolution
                                   QuerySolutionMap
                                   ResultSet
                                   ResultSetFormatter
                                   Syntax)
            (org.apache.http.protocol HttpContext BasicHttpContext)
            (org.apache.http.auth AuthScope UsernamePasswordCredentials)
            (org.apache.http.client CredentialsProvider)
            (org.apache.http.client.protocol ClientContext)
            (org.apache.http.impl.client BasicCredentialsProvider )
            (com.hp.hpl.jena.sparql.modify UpdateProcessRemote UpdateProcessRemoteForm)
            (com.hp.hpl.jena.update  UpdateExecutionFactory
                                     UpdateFactory
                                     UpdateProcessor
                                     UpdateRequest)
            (com.hp.hpl.jena.sparql.engine.http QueryEngineHTTP)))


(defn convert-rdf-type [input]
  "Converts the Apache Jena types"
  (cond (.isLiteral input) (.getValue (.asLiteral input))
   :else (.toString input)))

(defn query [config qs]
  "Runs a SPARQL query against a particular endpoint configuration, returns list of maps where SPARQL vars are keywords"
  (let [{:keys [endpoint user pass]} config
        qe (doto (QueryExecutionFactory/sparqlService endpoint qs) (.setBasicAuthentication user (char-array pass)))]
    (map
     (fn [rs]
       (into  {}
         (mapcat
             (fn [vname]
                   { (keyword vname) (convert-rdf-type (.get rs vname))}) ; turn the sparql vars into keywords in the results
             (iterator-seq (.varNames rs)))))
     (iterator-seq (.execSelect qe)))))

;; There is an incredible amount of parameter configuration required
;; Perhaps clj-http is compatible with Jena, but for now, mutate the various objects
(defn update [config qs ]
  "Runs a SPARQL Update query against a particular endpoint configuration"
  (let [{:keys [endpoint user pass]} config
        http-context (BasicHttpContext. )
        provider (BasicCredentialsProvider. )
        _ (.setCredentials provider (AuthScope. (AuthScope/ANY)) (UsernamePasswordCredentials. user pass ))
        _ (.setAttribute http-context (ClientContext/CREDS_PROVIDER) provider)
        update-request (UpdateFactory/create)
        _ (.add update-request qs)
        update-processor (UpdateExecutionFactory/createRemoteForm update-request endpoint)
        _ (.setHttpContext update-processor http-context)]
    (.execute update-processor)))


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

(defproject clj-sparql "0.3.0"
  :description "Clojure SPARQL: Clojure API for interfacing with a SPARQL Endpoint, based on Apache Jena"
  :url "http://github.com/AlBaker/clj-sparql"
  :license {:name "Apache License, Version 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.apache.jena/jena-arq "2.12.0"]]
  :plugins [[lein-midje "3.1.3"]
            [jonase/eastwood "0.0.2"]]
  :profiles
  {:dev
   {:dependencies
    [
      [midje "1.6-beta1"]
    ]}})

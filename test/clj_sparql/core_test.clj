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

(ns clj-sparql.core-test
  (:use midje.sweet)
  (:require
            [clj-sparql.core :refer :all]))

(def config {:endpoint "http://localhost:5820/league/query" :user "admin" :pass "admin"})

(def update-config {:endpoint "http://localhost:5820/league/update" :user "admin" :pass "admin"})

(facts "about select queries"
       (fact "running a SPARQL query"
             (query config
 "select ?s ?p ?o WHERE { ?s ?p ?o } LIMIT 10") => truthy))

(facts "about update queries"
       (fact "running an update query"
             (update update-config "
 PREFIX dc: <http://purl.org/dc/elements/1.1/>
INSERT { <http://example/egbook> dc:title  \"This is an example title8\" } WHERE {}
") => nil))

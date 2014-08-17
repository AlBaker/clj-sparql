# clj-sparql

A Clojure library for running SPARQL queries against a SPARQL endpoint.  Essentially a wrapper on Apche Jena, simplifying SPARQL execution with a standard endpoint.
As of the first version of clj-sparql, only ARQ is included to keep it both lightweight and without having to expose any Jena handling directly to a Clojure app.

You should be able to use clj-sparql with [Matsu](https://github.com/boutros/matsu), which provides a Clojure DSL for SPARQL creation, if that is your preferred way for crafting a SPARQL query.

clj-sparql is tested with [Stardog 2.x](http://www.stardog.com)


## Usage

For executing a SPARQL select query:

     (query
       {:endpoint "http://localhost:5820/someDb/query"}
       "select ?s ?p ?o WHERE { ?s ?p ?o } LIMIT 10")
     => ({:s "http://someResource" :p "http://someProperty" :o "someValue"})

Update queries:

      (update update-config "
              PREFIX dc: <http://purl.org/dc/elements/1.1/>
              INSERT { <http://example/egbook> dc:title  \"This is an example title8\" } WHERE {}")


Ask queries:

      (ask config "ASK { <http://example/egbook> ?p ?o }")

This is handy, as it lets you do transformations via keyword, e.g. (map :p ...)

For executing a SPARQL Update:

    (update  {:endpoint "http://localhost:5820/someDb/update" :user admin :pass admin} "
      PREFIX dc: <http://purl.org/dc/elements/1.1/>
      INSERT { <http://example/egbook> dc:title  \"This is an example title8\" } WHERE {}")
    => nil

The rational for returning nil from the update function is the underlying Apache Jena handling, and in fact the update request execute method is the last expression in the function.


## TODO

* Construct
* Ask
* Maybe RDF handling

## License

Copyright 2013-2014 Al Baker

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

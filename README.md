# clj-sparql

A Clojure library for easily running SPARQL queries.  Uses Apache Jena internally.

## Usage

For executing a SPARQL select query:

     (query
       {:endpoint "http://localhost:5820/someDb/query"}
       "select ?s ?p ?o WHERE { ?s ?p ?o } LIMIT 10")

## TODO

* SPARQL Update
* Construct
* Ask
* Some RDF handling

## License

Copyright © 2014 Al Baker

Distributed under the Apache Public License 2.0.

# Apache-Lucene-Search-Engine

## Project developed under the coursework of  CS7IS3 - Information Retrieval and Web Search


### Following is an implementation of the Apache Lucene Library (v7.5.0), written in Java.
This search engine creates an index of the cranfield collection (Indexer.java), which allows you to query this index (Searcher.java). 
It works off the basic Apache Lucene tutorials, with specific alterations and additions to the code to improve the search engine for specific needs.

Trec-Eval can then be used to compare the scores of this search engine with the recommended scores and see the accuracy degree of this search engine.

### Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software

```
Ubuntu OS
Maven 3.3.9
OpenJDK 1.8.0_242
GCC 5.4.0
```


### Building, Compiling, Creating the Index, Querying the Index and Using Trec-eval to compare scores.

* Clone this repository to your designated directory.
* Using a terminal - cd the cloned directory.

run evaluate.sh from project root, it will run all the above commands using shell script

```
sh evaluate.sh
```
* by default it runs with CustomAnalyzer and BM25Similarity. If you want to perform individual operation follow next part

* Run the following command to build jar package 
```
    mvn clean install -DskipTests   
```
* Run the following command to index and search over created index 
```
 mvn exec:java -Dexec.mainClass="org.example.App" -Dexec.args="-indexpath index -querypath cran/cran.qry -analyzer custom -similarity bm25"
```
* go to trec-eval directory using following command
```
 cd trec-eval.8.1/
```
* Run the following command to get score
```
./trec_eval ../cran/QRelsCorrectedforTRECeval ../output/results.txt
``` 


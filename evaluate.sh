mvn clean install -DskipTests &&
mvn exec:java -Dexec.mainClass="org.example.App" -Dexec.args="-indexpath index -querypath cran/cran.qry" &&
./trec_eval.8.1/trec_eval cran/QRelsCorrectedforTRECeval output/results.txt > output/analyzer/custome_bm25_updatedstopwordlist.txt


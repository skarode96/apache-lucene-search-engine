package org.example;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.example.model.QueryModel;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Searcher {
    private static String INDEX_DIRECTORY = "index";
    private static int MAX_RESULTS = 1000;

    public static void search(String query) throws IOException, ParseException {
        Analyzer analyzer = new StandardAnalyzer();
        Directory directory = FSDirectory.open(Paths.get(INDEX_DIRECTORY));
        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
        QueryParser parser = new QueryParser("Title", analyzer);
        QueryModel queryModel = QueryCreator.getQuery(query);
        parse(isearcher, parser, queryModel);
        ireader.close();
        directory.close();
    }


    public static void search(List<QueryModel> queryModelList) throws IOException {
        Analyzer analyzer = new StandardAnalyzer();
        Directory directory = FSDirectory.open(Paths.get(INDEX_DIRECTORY));
        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
        QueryParser parser = new QueryParser("Title", analyzer);
        queryModelList.forEach(qM -> {
            QueryModel queryModel = QueryCreator.getQuery(qM.getQueryString());
            try {
                parse(isearcher, parser, queryModel);
            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }
        });
        ireader.close();
        directory.close();
    }
    private static void parse(IndexSearcher isearcher, QueryParser parser, QueryModel queryModel) throws ParseException, IOException {
        Query parse = parser.parse(queryModel.getQueryString().trim());
        ScoreDoc[] hits = isearcher.search(parse, MAX_RESULTS).scoreDocs;
        // Print the results
        System.out.println("Documents: " + hits.length);
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = isearcher.doc(hits[i].doc);
            System.out.printf("QueryId : %s Iter: 0 Doc Id: %s Rank : %d Hit Score: %s STANDARD%n", queryModel.getId(), hitDoc.get("Id"), i, hits[i].score);
        }
    }
}

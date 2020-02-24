package org.example;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.example.analyzer.CustomAnalyzer;
import org.example.model.QueryModel;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Searcher {
    private static String INDEX_DIRECTORY = "index";
    private static int MAX_RESULTS = 1000;

    public static void search(String query) throws IOException, ParseException {
        Analyzer analyzer = new StandardAnalyzer();
        List<String> resFileContent = new ArrayList<>();
        Directory directory = FSDirectory.open(Paths.get(INDEX_DIRECTORY));
        DirectoryReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        QueryParser parser = new QueryParser("Title", analyzer);
        QueryModel queryModel = QueryCreator.getQuery(query);
        parse(indexSearcher, parser, queryModel, resFileContent);
        indexReader.close();
        directory.close();
    }


    public static void search(List<QueryModel> queryModelList, String outputDirName, String outputFileName) throws IOException {
        Analyzer analyzer = new StandardAnalyzer();
        Directory directory = FSDirectory.open(Paths.get(INDEX_DIRECTORY));
        DirectoryReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        indexSearcher.setSimilarity(new BM25Similarity());
        List<String> resFileContent = new ArrayList<>();
        MultiFieldQueryParser queryParser = new MultiFieldQueryParser(
                new String[] {"Title", "Author", "Bibliography", "Words"},
                analyzer);
        queryModelList.forEach(qm -> {
            try {
                parse(indexSearcher, queryParser, qm, resFileContent);
            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }
        });
        File outputDir = new File(outputDirName);
        if (!outputDir.exists()) outputDir.mkdir();

        Files.write(Paths.get(outputDirName + "/"+ outputFileName), resFileContent, StandardCharsets.UTF_8);
        System.out.println("Results written to " + outputDirName + "/"+ outputFileName + "to be used in TREC Eval.");
        indexReader.close();
        directory.close();
    }
    private static void parse(IndexSearcher isearcher, QueryParser queryParser, QueryModel queryModel, List<String> resFileContent) throws ParseException, IOException {
        Query parse = queryParser.parse(QueryParser.escape(queryModel.getQueryString().trim()));
        ScoreDoc[] hits = isearcher.search(parse, MAX_RESULTS).scoreDocs;
        // Print the results
        System.out.println("Documents: " + hits.length);
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = isearcher.doc(hits[i].doc);
            String path = hitDoc.get("Id");
            if (path != null) {
                System.out.printf("QueryId : %s Iter: 0 Doc Id: %s Rank : %d Hit Score: %s STANDARD%n", queryModel.getId(), hitDoc.get("Id"), i + 1, hits[i].score);
                resFileContent.add(queryModel.getId() + " 0 " + hitDoc.get("Id") + " 0 " + hits[i].score + " STANDARD");
            }
        }
    }
}

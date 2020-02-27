package org.example;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.example.analyzer.CustomAnalyzer;
import org.example.model.QueryModel;
import org.example.parser.CranQueryParser;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class SearcherTest {

    @Test
    public void shouldSearchQueryListOverIndex() throws IOException, ParseException {
        List<QueryModel> queryModels = CranQueryParser.parseQueries("cran/cran.qry");
        Searcher.search(queryModels, "output", "results.txt", new CustomAnalyzer(), new BM25Similarity());
    }

}
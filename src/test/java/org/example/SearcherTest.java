package org.example;

import org.apache.lucene.queryparser.classic.ParseException;
import org.example.model.QueryModel;
import org.example.parser.CranQueryParser;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class SearcherTest {

    @Test
    public void shouldSearchQueryOverIndex() throws IOException, ParseException {
        Searcher.search("can the transverse potential flow about a body of revolution be\n" +
                "calculated efficiently by an electronic computer .");
    }

    @Test
    public void shouldSearchQueryListOverIndex() throws IOException, ParseException {
        List<QueryModel> queryModels = CranQueryParser.parseQueries("cran/cran.qry");
        Searcher.search(queryModels, "output", "results.txt");
    }

}
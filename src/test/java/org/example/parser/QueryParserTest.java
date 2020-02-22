package org.example.parser;

import org.example.model.Query;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class QueryParserTest {

    @Test
    public void shouldReturnQueryList() {
        List<Query> queries = QueryParser.parseQueries();
        Assert.assertEquals(225, queries.size());
        Assert.assertEquals(false, queries.isEmpty());
    }

}
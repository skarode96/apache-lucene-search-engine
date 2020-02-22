package org.example.parser;

import org.example.model.QueryModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CranQueryModelParserTest {

    @Test
    public void shouldReturnQueryList() {
        List<QueryModel> queries = CranQueryParser.parseQueries("cran/cran.qry");
        Assert.assertEquals(225, queries.size());
        Assert.assertFalse(queries.isEmpty());
    }

}
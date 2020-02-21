package org.example;


import org.example.model.CranFieldDoc;
import org.example.parser.CranFieldDocParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CranFieldDocParserTest {

    @Test
    public void shoudlReadCranFieldDocDataset() {
        String path = "cran/cran.all.1400";
        List<CranFieldDoc> cranFieldDocs = CranFieldDocParser.readCranFieldDocs(path);
        Assert.assertEquals(1400, cranFieldDocs.size());
        Assert.assertFalse(cranFieldDocs.isEmpty());
    }

}
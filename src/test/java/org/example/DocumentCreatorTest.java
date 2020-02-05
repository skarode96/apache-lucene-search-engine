package org.example;

import org.apache.lucene.document.Document;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DocumentCreatorTest {

    @Test
    public void shouldCreateDocuments() {
        List<CranFieldDoc> cranFieldDocList = CranFieldDocParser.readCranFieldDocs("cran/cran.all.1400");
        List<Document> docs = DocumentCreator.createDocs(cranFieldDocList);
        Assert.assertEquals(1400, docs.size());
    }
}
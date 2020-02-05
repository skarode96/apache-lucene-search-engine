package org.example;

import org.apache.lucene.document.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class IndexerTest {

        @Test
        public void shouldIndexDocuments() throws IOException {
            List<CranFieldDoc> cranFieldDocs = CranFieldDocParser.readCranFieldDocs("cran/cran.all.1400");
            List<Document> documentList = DocumentCreator.createDocs(cranFieldDocs);
            boolean status = Indexer.index(documentList, "index", false);
            Assert.assertTrue(status);
        }

}
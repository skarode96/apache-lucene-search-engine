package org.example;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

import java.util.List;
import java.util.stream.Collectors;

public class DocumentCreator {

    public static List<Document> createDocs(List<CranFieldDoc> cranFieldDocList) {
        List<Document> documentList = cranFieldDocList.stream().map(doc -> {
            Document document = new Document();
            document.add(new TextField("Title", doc.getTitle(), Field.Store.YES));
            document.add(new TextField("Bibliography", doc.getBibliography(), Field.Store.YES));
            document.add(new TextField("Authors", doc.getAuthors(), Field.Store.YES));
            document.add(new TextField("Words", doc.getWords(), Field.Store.YES));
            document.add(new TextField("Title", doc.getTitle(), Field.Store.YES));
            return document;
        }).collect(Collectors.toList());
        return documentList;
    }
}

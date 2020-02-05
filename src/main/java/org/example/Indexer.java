package org.example;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Indexer {

    public static boolean index(List<Document> documentList, String indexPath, boolean update) throws IOException {
        Directory dir = getDirectory(indexPath);
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriter writer = getIndexWriter(update, dir, analyzer);
        indexDocs(documentList, writer);
        writer.close();
        return true;
    }

    private static void indexDocs(List<Document> documentList, IndexWriter writer) {
        if (writer.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE) {
            System.out.println("adding document list");
            documentList.forEach(doc -> {
                try {
                    writer.addDocument(doc);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            System.out.println("updating ...");
            documentList.forEach(doc -> {
                try {
                    writer.addDocument(doc);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static Directory getDirectory(String indexPath) throws IOException {
        System.out.println("Indexing to directory '" + indexPath + "'...");
        return FSDirectory.open(Paths.get(indexPath));
    }

    private static IndexWriter getIndexWriter(boolean update, Directory dir, Analyzer analyzer) throws IOException {
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriterConfig.setOpenMode(update ? IndexWriterConfig.OpenMode.CREATE_OR_APPEND : IndexWriterConfig.OpenMode.CREATE);
        return new IndexWriter(dir, indexWriterConfig);
    }
}

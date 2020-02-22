package org.example;

import org.apache.lucene.document.Document;
import org.example.model.CranFieldDoc;
import org.example.model.QueryModel;
import org.example.parser.CranFieldDocParser;
import org.example.parser.CranQueryParser;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        if(args.length == 0) {
            System.out.println("Please Enter CMD Argument");
            return;
        }
        String indexPath = "index";
        String cranQueryDocPath = "cran/cran.qry";
        String cranDocPath = "cran/cran.all.1400";
        String outputDirName="output";
        String outputFileName="results.txt";
        boolean runIndex = false;
        boolean runQuery = false;

        for(int i=0;i<args.length;i++) {
            if ("-indexpath".equals(args[i])) {
                indexPath = args[i+1];
                runIndex = true;
                i++;
            } else if ("-docspath".equals(args[i])) {
                cranDocPath = args[i+1];
                i++;
            }
            else if ("-querypath".equals(args[i])) {
                cranQueryDocPath = args[i+1];
                runQuery = true;
                i++;
            }
            else if ("-outputdir".equals(args[i])) {
                outputDirName = args[i+1];
                i++;
            }
            else if ("-outputfilename".equals(args[i])) {
                outputFileName = args[i+1];
                i++;
            }
        }
        if (runIndex) {
            List<CranFieldDoc> cranFieldDocs = CranFieldDocParser.readCranFieldDocs(cranDocPath);
            List<Document> documentList = DocumentCreator.createDocs(cranFieldDocs);
            Indexer.index(documentList, indexPath, false);
        }
        if(runQuery) {
            List<QueryModel> queryModels = CranQueryParser.parseQueries(cranQueryDocPath);
            Searcher.search(queryModels, outputDirName, outputFileName);
        }
    }
}

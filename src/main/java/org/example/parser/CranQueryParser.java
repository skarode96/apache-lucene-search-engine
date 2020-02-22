package org.example.parser;

import org.example.model.QueryModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CranQueryParser {
    public static List<QueryModel> parseQueries() {
        String path = "cran/cran.qry";
        return loadCranQueryDoc(path);
    }

    private static ArrayList<QueryModel> loadCranQueryDoc(String path) {
        ArrayList<QueryModel> queries = new ArrayList<>();
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(path));
            String line = bufferedReader.readLine();
            int i=0;
            while(line != null) {
                if(line.matches("(\\.I)( )(\\d)*")) {
                    StringBuilder sb;
                    QueryModel queryModel = new QueryModel();
                    line = bufferedReader.readLine();
                    while (line != null && !line.matches("(\\.I)( )(\\d)*")) {
                        if(line.matches("(\\.W)")){
                            sb = new StringBuilder();
                            line = readConsecutiveLines(bufferedReader, sb, "(\\.I)( )(\\d)*", bufferedReader.readLine());
                            queryModel.setQueryString(sb.toString());
                            queryModel.setId(String.valueOf(i));
                            i++;
                        }
                    }
                    queries.add(queryModel);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return queries;
    }
    private static String readConsecutiveLines(BufferedReader bufferedReader, StringBuilder sb, String regex, String line) throws IOException {
        while(line != null && !line.matches(regex)) {
            sb.append(line);
            line = bufferedReader.readLine();
        }
        return line;
    }
}
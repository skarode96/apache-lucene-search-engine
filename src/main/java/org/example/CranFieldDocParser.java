package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CranFieldDocParser {

    private List<CranFieldDoc> cranFieldDocs;

    public static boolean parse() {
        return true;
    }

    public static List<CranFieldDoc> readCranFieldDocs(String path) {
        List<CranFieldDoc> cranFieldDocs = new ArrayList<>();
        CranFieldDoc cranFieldDoc;
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(path));
            String line = bufferedReader.readLine();
            while(line != null) {
                if(line.matches("(\\.I)( )(\\d)*")) {
                    StringBuilder sb;
                    cranFieldDoc = new CranFieldDoc();
                    line = bufferedReader.readLine();
                    while (line != null && !line.matches("(\\.I)( )(\\d)*")) {
                        if(line.matches("(\\.T)")){
                            sb = new StringBuilder();
                            line = readConsecutiveLines(bufferedReader, sb, "(\\.A)", bufferedReader.readLine());
                            cranFieldDoc.setTitle(sb.toString());
                        } else if(line.matches("(\\.A)")){
                            sb = new StringBuilder();
                            line = readConsecutiveLines(bufferedReader, sb, "(\\.B)", bufferedReader.readLine());
                            cranFieldDoc.setAuthors(sb.toString());
                        } else if(line.matches("(\\.B)")){
                            sb = new StringBuilder();
                            line = readConsecutiveLines(bufferedReader, sb, "(\\.W)", bufferedReader.readLine());
                            cranFieldDoc.setBiblography(sb.toString());
                        } else if(line.matches("(\\.W)")){
                            sb = new StringBuilder();
                            line = readConsecutiveLines(bufferedReader, sb, "(\\.I)( )(\\d)*", bufferedReader.readLine());
                            cranFieldDoc.setWords(sb.toString());
                        }
                    }
                    cranFieldDocs.add(cranFieldDoc);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cranFieldDocs;
    }

    private static String readConsecutiveLines(BufferedReader bufferedReader, StringBuilder sb, String regex, String line) throws IOException {
        while(line != null && !line.matches(regex)) {
            sb.append(line);
            line = bufferedReader.readLine();
        }
        return line;
    }
}

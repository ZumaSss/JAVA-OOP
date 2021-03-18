import java.io.*;
import java.lang.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
//        long total = 0;
//        Map<String, Long> counter = new TreeMap<>();
//        Reader reader = null;
//        PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("output.csv")));
        try (Parser parser = new Parser(
                new InputStreamReader(new FileInputStream(args[0])),
                new OutputStreamWriter(new FileOutputStream(args[1])))) {
            parser.Input();
            parser.writeResult();
        } catch (Exception e) {
            System.out.println("An error has occurred: " + e.getLocalizedMessage());
        }
//        try {
//            reader = new InputStreamReader(new FileInputStream("input.txt"));
//            OutputStream out;
//            StringBuilder strBuff = new StringBuilder();
//            int i;
//            while ((i = reader.read()) != -1) {
//                char curChar = (char)i;
//                if(Character.isLetterOrDigit(curChar)) strBuff.append(curChar);
//                else if (!strBuff.isEmpty()) {
//                    String word = strBuff.toString();
//                    long value = (counter.containsKey(word) ? counter.get(word) + 1 : 1);
//                    counter.put(word, value);
//                    strBuff = new StringBuilder();
//                    total++;
//                }
//            }
//            if (!strBuff.isEmpty()) {
//                String word = strBuff.toString();
//                long value = (counter.containsKey(word) ? counter.get(word) + 1 : 1);
//                counter.put(word, value);
//            }
//
//
//            List<Map.Entry<String, Long>> list = counter.entrySet().stream().sorted((e1, e2) -> -e1.getValue().compareTo(e2.getValue())).collect(Collectors.toList());
//            for (Map.Entry<String, Long> entry : list) {
//                DecimalFormat df = new DecimalFormat("#.##");
//                double temp = (double)entry.getValue() * 100 / total;
//                writer.write(entry.getKey() + "; " + entry.getValue() + "; " + df.format(temp) + "\n");
//            }
//
//            writer.close();
//        } catch (IOException e) {
//            System.err.println("Error reading the file:" + e.getLocalizedMessage());
//        } finally {
//            if (null != reader) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    e.printStackTrace(System.err);
//                }
//            }
//        }
    }
}

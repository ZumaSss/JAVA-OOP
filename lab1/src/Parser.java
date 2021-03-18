import java.io.*;
import java.lang.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Parser implements AutoCloseable{
    private long total = 0;
    private final Map<String, Long> counter;
    private final Reader reader;
    private final Writer writer;

    public Parser(Reader inputReader, Writer inputWriter) throws FileNotFoundException {
        reader = inputReader;
        writer = inputWriter;
        counter = new TreeMap<>();
    }

    public void Input() {
        try {
            StringBuilder strBuff = new StringBuilder();
            int i;
            while ((i = reader.read()) != -1) {
                char curChar = (char)i;
                if(Character.isLetterOrDigit(curChar)) strBuff.append(curChar);
                else if (!strBuff.isEmpty()) {
                    String word = strBuff.toString();
                    long value = (counter.containsKey(word) ? counter.get(word) + 1 : 1);
                    counter.put(word, value);
                    strBuff = new StringBuilder();
                    total++;
                }
            }
            if (!strBuff.isEmpty()) {
                String word = strBuff.toString();
                long value = (counter.containsKey(word) ? counter.get(word) + 1 : 1);
                counter.put(word, value);
            }



        } catch (IOException e) {
            System.err.println("Error reading the file:" + e.getLocalizedMessage());
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    public void writeResult() throws IOException {
        List<Map.Entry<String, Long>> list = counter.entrySet().stream().sorted((e1, e2) -> -e1.getValue().compareTo(e2.getValue())).collect(Collectors.toList());
        for (Map.Entry<String, Long> entry : list) {
            DecimalFormat df = new DecimalFormat("#.##");
            double temp = (double)entry.getValue() * 100 / total;
            writer.write(entry.getKey() + "; " + entry.getValue() + "; " + df.format(temp) + "\n");
        }


    }


    @Override
    public void close() throws Exception {
        reader.close();
        writer.close();
    }
}

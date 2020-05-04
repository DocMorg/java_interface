package todos.core;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CsvReaded implements Readed {

    private final FileInputStream in;

    public CsvReaded(FileInputStream in) {
        this.in = in;
    }

    @Override
    public List<String[]> read() {
        String line;
        String csvSplitBy = ",";
        LinkedList<String[]> list = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(this.in))) {

            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] newline = line.split(csvSplitBy);
                newline[0] = newline[0].replace("\"", "");
                newline[1] = newline[1].replace("\"", "");
                list.add(newline);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    void main(){

    }
}

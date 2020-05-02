package todos.core;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class CsvSaver implements Saver {

    private final PrintStream out;
    private final Map<String, String> map = new LinkedHashMap<>();

    public CsvSaver(PrintStream out) {
        this.out = out;
    }

    @Override
    public void add(String name, String value)  {
        this.map.put(name, value);
    }

    @Override
    public void save() {
        Iterator<Map.Entry<String, String>> iterator = this.map.entrySet().iterator();
        Map.Entry<String, String> entry;
        while (iterator.hasNext()) {
            entry = iterator.next();
            out.print("\"");
            out.print(entry.getValue());
            out.print("\"");
            if (iterator.hasNext()) {
                out.print(",");
            } else {
                out.println();
            }
        }
        this.map.clear();
        this.out.flush();
    }
}

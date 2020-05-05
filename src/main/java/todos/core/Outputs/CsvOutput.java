package todos.core.Outputs;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class CsvOutput implements Output {

    private final PrintStream out;
    private final Map<String, String> map = new LinkedHashMap<>();

    public CsvOutput(PrintStream out) {
        this.out = out;
    }

    @Override
    public void add(String name, String value)  {
        this.map.put(name, value);
    }

    @Override
    public Output save() {
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
        return this;
    }
}

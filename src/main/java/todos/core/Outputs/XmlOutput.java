package todos.core.Outputs;

import org.xembly.Directives;
import org.xembly.ImpossibleModificationException;
import org.xembly.Xembler;

import java.io.File;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class XmlOutput implements Output {

    private final PrintStream out;
    private final Map<String, String> map;
    private String buffer;

    public XmlOutput(PrintStream out) {
        this.out = out;
        this.map = new LinkedHashMap<>();
    }

    @Override
    public Output addHeader(String header) {
        return this;
    }

    @Override
    public Output add(String name, String value) {
        if (name.equals("name")){
            this.buffer = value;
        } else{
            this.map.put(buffer, value);
        }
        return this;
    }

    @Override
    public void save() {
        Directives directives = new Directives().add("data");

        for (Map.Entry<String, String> entry : map.entrySet()){
            directives.add("item")
                    .add("name").set(entry.getKey())
                    .up()
                    .add("date").set(entry.getValue())
                    .up()
                    .up();

        }
        try {
            out.print(new Xembler(directives).xml());
        } catch (ImpossibleModificationException e) {
            e.printStackTrace();
        }
    }
}

package todos.core.Outputs;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.PrintStream;

public class JsonOutput implements Output {

    private final PrintStream out;
    private JSONArray jsonArray;
    private JSONObject jsonObject;

    public JsonOutput(PrintStream out) {
        this.out = out;
        this.jsonArray = new JSONArray();
    }

    @Override
    public Output addHeader(String header) {
        this.jsonObject = new JSONObject();
        return this;
    }

    @Override
    public Output add(String name, String value) {
        if (jsonObject.isEmpty()) {
            jsonObject.put(name, value);
        } else{
            jsonObject.put(name, value);
            jsonArray.add(jsonObject);
        }
        return this;
    }

    @Override
    public void save() {
        out.print(jsonArray.toString());
        this.out.flush();
    }
}

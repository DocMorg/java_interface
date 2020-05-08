package todos.core.Outputs;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.PrintStream;

public class JsonOutput implements Output {

    private final PrintStream out;
    private JSONArray jsonArray;

    public JsonOutput(PrintStream out) {
        this.out = out;
    }

    @Override
    public Output addHeader(String header) {
        jsonArray = new JSONArray();
        return this;
    }

    @Override
    public Output add(String name, String value) {
        JSONObject data = new JSONObject();
        data.put(name, value);
        jsonArray.put(data);
        return this;
    }

    @Override
    public void save() {
        JSONObject obj = new JSONObject();
        obj.put("obj", jsonArray);
        out.print(obj.toString());
        this.out.flush();
    }
}

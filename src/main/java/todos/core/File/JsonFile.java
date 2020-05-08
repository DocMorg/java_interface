package todos.core.File;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import todos.core.Outputs.JsonOutput;
import todos.core.Outputs.Output;
import todos.core.Todos.Todo;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class JsonFile implements NewFile {

    private final File file;

    public JsonFile(File file){
        this.file = file;
    }

    @Override
    public List<String[]> read() {
        List<String[]> data = new LinkedList<>();
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = new JSONArray();
        try {
            try {
                jsonArray = (JSONArray) (parser.parse(new FileReader(file)));
            }catch (ClassCastException e){
                Object obj = parser.parse(new FileReader(file));
                jsonArray.add(obj);
            }

            for (Object o : jsonArray)
            {
                JSONObject obj = (JSONObject) o;
                String[] array = {(String) obj.get("name"), (String) obj.get("date")};
                data.add(array);
            }

        } catch (IOException | ParseException ignored) {

        }
        return data;
    }

    @Override
    public void save(List<Todo> list) {
        try {
            Output jsonout = new JsonOutput(new PrintStream(file));
            int i = 0;
            for (Todo todo: list){
                todo.saveTodo(jsonout, i);
                jsonout.save();
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

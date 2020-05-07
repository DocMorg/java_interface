package todos.core.File;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import todos.core.Outputs.CsvOutput;
import todos.core.Outputs.Output;
import todos.core.Todos.Todo;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class CsvFile implements NewFile {

    private final File file;

    public CsvFile(File file){
        this.file = file;
    }

    @Override
    public List<String[]> read() {
        List<String[]> data = new LinkedList<>();
        try {
            CSVReader csvReader = new CSVReader( new FileReader(file));
            data = csvReader.readAll();
        } catch (CsvException | IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void save(List<Todo> list) {
        try {
            Output csvout = new CsvOutput(new PrintStream(file));
            for (Todo todo: list){
                todo.saveTodo(csvout);
                csvout.save();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

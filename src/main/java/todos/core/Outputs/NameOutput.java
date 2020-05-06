package todos.core.Outputs;


import todos.core.Outputs.Output;

public class NameOutput implements Output {

    private String lineIn;

    @Override
    public Output add(String name, String value) {
        if (name.equals("name") ) {
            this.lineIn = value;
        }
        return this;
    }

    @Override
    public void save() {
    }

    public String toString() {
        return lineIn;
    }
}

package todos.core.Outputs;

public class DateOutput implements Output {

    private String lineIn;

    @Override
    public Output add(String name, String value) {
        if (name.equals("date") ) {
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

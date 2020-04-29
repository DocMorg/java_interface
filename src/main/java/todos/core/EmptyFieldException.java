package todos.core;

public class EmptyFieldException extends Exception {
    public EmptyFieldException() {
        super("FieldIsEmpty");
    }
}
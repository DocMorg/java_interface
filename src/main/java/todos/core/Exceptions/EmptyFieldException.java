package todos.core.Exceptions;

public class EmptyFieldException extends Exception {
    public EmptyFieldException() {
        super("FieldIsEmpty");
    }
}
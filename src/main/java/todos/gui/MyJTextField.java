package todos.gui;

import todos.core.Todos.DefaultTodo;
import todos.core.Exceptions.EmptyFieldException;
import todos.core.Todos.Todo;

import javax.swing.*;

public class MyJTextField extends JTextField {

    @Override
    public void setText(String t){
    }

    private String getMyText(){
        return super.getText();
    }

    public Todo readText() throws EmptyFieldException {
        if (this.getMyText().length() > 0) {
            String text = this.getMyText();
            this.emptyTextField();
            return new DefaultTodo(text);

        } else {
            throw new EmptyFieldException();
        }
    }

    private void emptyTextField(){
        super.setText("");
    }

    @Override
    public String getText() {
        try {
            throw new Exception("This method is not supposed to be used due to encapsulation rules");
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

}

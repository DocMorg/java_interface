package todos.gui;

import todos.core.Todos.DefaultTodo;
import todos.core.Exceptions.EmptyFieldException;
import todos.core.Todos.Todo;

import javax.swing.*;
import java.awt.*;

public class InputJTextField{

    private final JTextField jTextField;

    InputJTextField(){
          jTextField = new JTextField();
    }

    public Todo readText() throws EmptyFieldException {
        if (jTextField.getText().length() > 0) {
            String text = jTextField.getText();
            this.emptyTextField();
            return new DefaultTodo(text);

        } else {
            throw new EmptyFieldException();
        }
    }

    private void emptyTextField(){
        jTextField.setText("");
    }

    protected void withPanel(JPanel panel){
        panel.add(jTextField, BorderLayout.CENTER);
    }

}

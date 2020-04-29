package todos.gui;

import todos.core.EmptyFieldException;
import todos.core.Todos;

import javax.swing.*;

public class MyJTextField extends JTextField {

    @Override
    public String getText() {
        try {
            throw new Exception("This method is not supposed to be used due to encapsulation rules");
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    public void setText(String t){
    }

    private String getMyText(){
        return super.getText();
    }

    public Todos readText() throws EmptyFieldException {
        if (this.getText().length() > 0) {
            return new Todos(this.getMyText());
        } else {
            throw new EmptyFieldException();
        }
    }

}

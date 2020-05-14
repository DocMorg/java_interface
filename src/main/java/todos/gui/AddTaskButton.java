package todos.gui;

import todos.core.Exceptions.EmptyFieldException;
import todos.core.Storages.Storage;

import javax.swing.*;
import java.awt.*;

public class AddTaskButton {

    private final JButton button;
    private final Storage storage;
    private final InputJTextField textField;

    AddTaskButton(Storage storage, InputJTextField textField){
        this.button = new JButton("  Add  ");
        this.storage = storage;
        this.textField = textField;
        setUpButton();
    }

    private void setUpButton(){
        button.addActionListener(actionEvent -> {
            try {
                storage.add(textField.readText());
            } catch (EmptyFieldException ignored) {}
        });
    }

    public void bind(JPanel panel) {
        panel.add(button, BorderLayout.EAST);
//        panel.getRootPane().setDefaultButton(button);
    }

}

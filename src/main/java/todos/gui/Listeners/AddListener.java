package todos.gui.Listeners;

import todos.core.Exceptions.EmptyFieldException;
import todos.core.Storages.Storage;
import todos.gui.InputJTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddListener implements ActionListener {

    private final Storage storage;
    private final InputJTextField textField;

    public AddListener(Storage storage, InputJTextField textField){
        this.storage = storage;
        this.textField = textField;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            storage.add(textField.readText());
        } catch (EmptyFieldException ignored) {}
    }
}

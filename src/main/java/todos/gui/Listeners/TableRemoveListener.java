package todos.gui.Listeners;

import todos.core.Exceptions.EmptyFieldException;
import todos.core.Storages.Storage;
import todos.gui.InputJTextField;
import todos.gui.TaskTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableRemoveListener implements ActionListener {

    private final Storage storage;
    private final JTable table;

    public TableRemoveListener(Storage storage, JTable table){
        this.storage = storage;
        this.table = table;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
         storage.remove(table.getSelectedRow());
    }
}

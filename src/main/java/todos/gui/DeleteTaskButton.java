package todos.gui;

import todos.core.Storages.Storage;
import todos.gui.Listeners.TableRemoveListener;

import javax.swing.*;

public class DeleteTaskButton {

    public static final float CENTER_ALIGNMENT = 0.5F;
    private final JButton button;
    private final Storage storage;
    private JTable table;
    private boolean defined;

    DeleteTaskButton(Storage storage){
        this.button = new JButton(" Delete");
        this.storage = storage;
        this.defined = false;
        setUpButton();
    }

    private void setUpButton(){
        button.setAlignmentX(CENTER_ALIGNMENT);
    }

    public void withTableAndControls(JPanel listControls) {
        if (defined){
            DeleteTaskButton button1 = new DeleteTaskButton(storage);
            button1.withTable(this.table);
            button1.button.addActionListener(new TableRemoveListener(storage, this.table));
            listControls.add(button1.button);
        }

    }

    public void withTable(JTable table){
        this.table = table;
        this.defined = true;
        new DeleteTaskButton(storage).button.addActionListener(
                new TableRemoveListener(storage, this.table));
    }
}

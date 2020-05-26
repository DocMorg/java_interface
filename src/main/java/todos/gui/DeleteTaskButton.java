package todos.gui;

import todos.core.Storages.Storage;
import todos.gui.Listeners.TableRemoveListener;

import javax.swing.*;
import java.awt.event.ActionListener;

public class DeleteTaskButton {

    public static final float CENTER_ALIGNMENT = 0.5F;
    private final JButton button;
    private final Storage storage;
    private TableRemoveListener listener;
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
            button1.withTable(this.listener);
            listControls.add(button1.button);
        }
    }

    public void withTable(TableRemoveListener listener){
        this.listener = listener;
        this.defined = true;
        this.button.addActionListener(listener);
    }
}

package todos.gui;

import todos.core.Observer.DefaultEventListened;
import todos.core.Storages.XmlStorage;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        configureLookAndFeel();
//        CsvStorage storage = new CsvStorage("backup.csv");
//        JsonStorage storage = new JsonStorage("backup.json");
        XmlStorage storage = new XmlStorage("backup.xml");

        // linking model to our list using observer pattern realization
        TodoTableModel todoTableModel = new TodoTableModel(storage);
        storage.events.subscribe("add",new DefaultEventListened(todoTableModel));
        storage.events.subscribe("remove",new DefaultEventListened(todoTableModel));

        // Reading already written data
        storage.loadTodo();

        MainJPanel mainPanel = new MainJPanel(storage, todoTableModel);
        mainPanel.withFrame(this);

        this.setTitle("Todo List");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(320, 270));
        this.pack();
    }

    private static void configureLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                }
            }
        } catch (Exception ignored) {
        }
    }
}
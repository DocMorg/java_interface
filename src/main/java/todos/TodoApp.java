package todos;

import todos.core.Observer.EventsManaged;
import todos.core.Storages.XmlStorage;
import todos.gui.MainWindow;

import javax.swing.*;

public class TodoApp {

    public static void main(String[] args) {
        configureLookAndFeel();
        EventsManaged events = new EventsManaged("add", "remove");
        MainWindow window = new MainWindow(
                new XmlStorage("backup.xml", events), events);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
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

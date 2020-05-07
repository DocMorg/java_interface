package todos;

import todos.gui.MainWindow;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.io.FileNotFoundException;

public class TodoApp {

    public static void main(String[] args) throws FileNotFoundException {
        configureLookAndFeel();

        MainWindow window = new MainWindow();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    private static void configureLookAndFeel() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
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

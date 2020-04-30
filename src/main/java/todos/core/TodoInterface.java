package todos.core;
import javax.swing.ListModel;
import java.util.Iterator;
import java.util.List;

public interface TodoInterface extends Iterable<Todos> {

    void remove(int i);
    void add(Todos item);
    int getSize();
    Todos getElementAt(int i);
    Iterator<Todos> iterator();
    ListModel<Todos> getModel();
}

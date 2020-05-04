package todos.core.Observer;

import todos.gui.TodoTableModel;


/**
 * Default implementation of {@link EventListened} linked to todoTableModel
 * in constructor
 */
public class DefaultEventListened implements EventListened {

    private final TodoTableModel model;

    /**
     * @param model - links todoTableModel instance to listener
     */
    public DefaultEventListened(TodoTableModel model) {
        this.model = model;
    }

    /**
    @param i - index needed for model to redraw JList
     */
    @Override
    public void update(String eventType, int i) {
        switch (eventType){
            case "add": this.model.add(i);
            case "remove": this.model.remove(i);

        }



    }
}
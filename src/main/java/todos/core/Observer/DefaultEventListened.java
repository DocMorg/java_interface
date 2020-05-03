package todos.core.Observer;

import todos.gui.TodoListModel;


/**
 * Default implementation of {@link EventListened} linked to TodoListModel
 * in constructor
 */
public class DefaultEventListened implements EventListened {

    private final TodoListModel model;

    /**
     * @param model - links TodoListModel instance to listener
     */
    public DefaultEventListened(TodoListModel model) {
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
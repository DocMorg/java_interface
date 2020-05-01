package todos.core.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager  {
    Map<String, List<EventListener>> listeners = new HashMap<>();

    /**
     * @param operations - operations needed to be listened
     */
    public EventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    /**
     * Subscribing listeners to the exact types of the events
     * @param eventType - type of event which sends to listeners
     * @param listener - listener to send events to
     */
    public void subscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    /**
     * Notify listeners about coming update
     * @param eventType - type of event which to notify about the listeners
     * @param i - variable coming with notification for listeners
     */
    public void notify(String eventType, int i) {
        List<EventListener> users = listeners.get(eventType);
        for (EventListener listener : users) {
            listener.update(eventType, i);
        }
    }
}

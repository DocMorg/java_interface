package todos.core.Observer;

public interface EventListener {

    /**
     * @param eventType - only add or remove supported
     * @param i - variable coming with notification for listeners
     */
    void update(String eventType, int i);
}

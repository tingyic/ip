/**
 * The Event of tasks.
 * Inherits from the superclass Task.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String nameOfTask, String from, String to) {
        super(nameOfTask);
        this.from = from;
        this.to = to;
    }

    /**
     * The constructor used when retrieving event tasks from hard disk.
     * @param nameOfTask Name of the task.
     * @param from Start of the event.
     * @param to End of the event.
     * @param isDone Checks whether the event is done or not.
     */
    public Event(String nameOfTask, String from, String to, boolean isDone) {
        super(nameOfTask, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + " )";
    }
}

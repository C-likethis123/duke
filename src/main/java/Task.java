/**
 * A base class that Deadline, Event, and ToDo inherit from
 */
public class Task {
    String description;
    boolean isDone;
    String typeOfTask = "";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the current Task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        String statusIcon = isDone ? "[\u2713]" : "[\u2718]";
        return String.format("%s%s %s", typeOfTask, statusIcon, description);
    }

    public String writeToFile() {
        String status = isDone ? "1" : "0";
        return String.format("%s|%s|%s", typeOfTask, status, description);
    }
}

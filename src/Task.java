public class Task {
    private final int id;
    private final String title;
    private boolean completed;

    public Task(int id, String title) {
        this.id = id;
        this.title = title;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        completed = true;
    }

    @Override
    public String toString() {
        String status = completed ? "\u5b8c\u4e86" : "\u672a\u5b8c\u4e86";
        return id + ". [" + status + "] " + title;
    }
}
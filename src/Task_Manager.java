import java.util.ArrayList;
import java.util.List;

public class Task_Manager {
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public void addTask(String title) {
        tasks.add(new Task(nextId, title));
        nextId++;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public boolean completeTask(int id) {
        Task task = findTaskById(id);

        if (task == null) {
            return false;
        }

        task.markCompleted();
        return true;
    }

    public boolean deleteTask(int id) {
        Task task = findTaskById(id);

        if (task == null) {
            return false;
        }

        tasks.remove(task);
        return true;
    }

    private Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }

        return null;
    }
}
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Task_Manager taskManager = new Task_Manager();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            showMenu();
            int choice = readInt("番号を選んでください: ");

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    showTasks();
                    break;
                case 3:
                    completeTask();
                    break;
                case 4:
                    deleteTask();
                    break;
                case 5:
                    running = false;
                    System.out.println("アプリを終了します。");
                    break;
                default:
                    System.out.println("1〜5の番号を入力してください。");
                    break;
            }

            System.out.println();
        }
    }

    private static void showMenu() {
        System.out.println("==== タスク管理アプリ ====");
        System.out.println("1. タスクを追加");
        System.out.println("2. タスク一覧を表示");
        System.out.println("3. タスクを完了にする");
        System.out.println("4. タスクを削除");
        System.out.println("5. 終了");
    }

    private static void addTask() {
        System.out.print("タスク名を入力してください: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("タスク名が空です。追加できません。");
            return;
        }

        taskManager.addTask(title);
        System.out.println("タスクを追加しました。");
    }

    private static void showTasks() {
        List<Task> tasks = taskManager.getTasks();

        if (tasks.isEmpty()) {
            System.out.println("タスクはまだありません。");
            return;
        }

        System.out.println("---- タスク一覧 ----");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private static void completeTask() {
        int id = readInt("完了にするタスクIDを入力してください: ");

        if (taskManager.completeTask(id)) {
            System.out.println("タスクを完了にしました。");
        } else {
            System.out.println("指定されたIDのタスクは見つかりません。");
        }
    }

    private static void deleteTask() {
        int id = readInt("削除するタスクIDを入力してください: ");

        if (taskManager.deleteTask(id)) {
            System.out.println("タスクを削除しました。");
        } else {
            System.out.println("指定されたIDのタスクは見つかりません。");
        }
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("数字を入力してください。");
            }
        }
    }
}
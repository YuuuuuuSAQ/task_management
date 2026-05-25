import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TaskApp app = new TaskApp();
            app.show();
        });
    }
}
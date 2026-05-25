import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class TaskApp {
    private final Task_Manager taskManager = new Task_Manager();
    private final DefaultListModel<Task> taskListModel = new DefaultListModel<>();
    private final JFrame frame = new JFrame("\u30bf\u30b9\u30af\u7ba1\u7406\u30a2\u30d7\u30ea");
    private final JTextField taskInput = new JTextField();
    private final JList<Task> taskList = new JList<>(taskListModel);

    public TaskApp() {
        setupFrame();
        setupTaskList();
        frame.add(createHeader(), BorderLayout.NORTH);
        frame.add(createTaskArea(), BorderLayout.CENTER);
        frame.add(createButtonArea(), BorderLayout.SOUTH);
    }

    public void show() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setupFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(520, 420));
        frame.setLayout(new BorderLayout(12, 12));
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
    }

    private void setupTaskList() {
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        taskList.setFixedCellHeight(34);
    }

    private JPanel createHeader() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        JLabel title = new JLabel("\u30bf\u30b9\u30af\u7ba1\u7406");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        JLabel description = new JLabel("\u30bf\u30b9\u30af\u3092\u8ffd\u52a0\u3057\u3066\u3001\u5b8c\u4e86\u3084\u524a\u9664\u3092\u7ba1\u7406\u3067\u304d\u307e\u3059\u3002");
        description.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

        panel.add(title, BorderLayout.NORTH);
        panel.add(description, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createTaskArea() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.add(createInputArea(), BorderLayout.NORTH);
        panel.add(new JScrollPane(taskList), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createInputArea() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 0, 0, 8);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;

        taskInput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        taskInput.addActionListener(e -> addTask());
        panel.add(taskInput, constraints);

        JButton addButton = new JButton("\u8ffd\u52a0");
        addButton.addActionListener(e -> addTask());
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.weightx = 0;
        constraints.gridx = 1;
        panel.add(addButton, constraints);

        return panel;
    }

    private JPanel createButtonArea() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
    
        JButton completeButton = new JButton("\u5b8c\u4e86\u306b\u3059\u308b");
        completeButton.addActionListener(e -> completeSelectedTask());
    
        JButton deleteButton = new JButton("\u524a\u9664");
        deleteButton.addActionListener(e -> deleteSelectedTask());
    
        panel.add(completeButton);
        panel.add(deleteButton);
        return panel;
    }
    
    private void addTask() {
        String title = taskInput.getText().trim();
    
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "\u30bf\u30b9\u30af\u540d\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002");
            return;
        }
    
        taskManager.addTask(title);
        taskInput.setText("");
        refreshTaskList();
    }
    
    private void completeSelectedTask() {
        Task task = taskList.getSelectedValue();
    
        if (task == null) {
            JOptionPane.showMessageDialog(frame, "\u5b8c\u4e86\u306b\u3059\u308b\u30bf\u30b9\u30af\u3092\u9078\u3093\u3067\u304f\u3060\u3055\u3044\u3002");
            return;
        }
    
        taskManager.completeTask(task.getId());
        refreshTaskList();
    }
    
    private void deleteSelectedTask() {
        Task task = taskList.getSelectedValue();
    
        if (task == null) {
            JOptionPane.showMessageDialog(frame, "\u524a\u9664\u3059\u308b\u30bf\u30b9\u30af\u3092\u9078\u3093\u3067\u304f\u3060\u3055\u3044\u3002");
            return;
        }
    
        taskManager.deleteTask(task.getId());
        refreshTaskList();
    }
    
    private void refreshTaskList() {
        taskListModel.clear();
    
        for (Task task : taskManager.getTasks()) {
            taskListModel.addElement(task);
        }
    }
}
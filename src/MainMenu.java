import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class MainMenu {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Car Park Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLayout(new BorderLayout(20, 20));

            JLabel title = new JLabel("Car Park Management System", SwingConstants.CENTER);
            title.setFont(new Font("Arial", Font.BOLD, 24));
            title.setForeground(Color.BLACK);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(1, 2, 20, 20));

            JButton userModeButton = new JButton("User Mode");
            userModeButton.addActionListener(e -> openUserMode(frame));

            JButton adminModeButton = new JButton("Admin Mode");
            adminModeButton.addActionListener(e -> openAdminMode(frame));

            buttonPanel.add(userModeButton);
            buttonPanel.add(adminModeButton);

            frame.add(title, BorderLayout.NORTH);
            frame.add(buttonPanel, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }

    private static void openUserMode(JFrame parentFrame) {
        JFrame userFrame = new JFrame("User Mode");
        userFrame.setSize(400, 300);
        userFrame.setLayout(new BorderLayout(10, 10));

        JLabel instructionLabel = new JLabel("Enter your vehicle registration number (VRN):", SwingConstants.CENTER);
        JTextField vrnField = new JTextField();
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(e -> {
            String vrn = vrnField.getText();
            if (AppUtil.validateVRN(vrn)) {
                try {
                    CSVHandler handler = new CSVHandler();
                    String standardizedVRN = AppUtil.standardizeVRN(vrn);
                    boolean found = false;
                    for (VehicleRecord record : handler.readRecords()) {
                        if (record.getCarVRN().equals(standardizedVRN) && record.isCurrentlyParked()) {
                            record.setExitTime(LocalDateTime.now());
                            handler.updateRecord(standardizedVRN, LocalDateTime.now());
                            JOptionPane.showMessageDialog(userFrame, "Exit recorded successfully.");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        handler.addRecord(new VehicleRecord(standardizedVRN, LocalDateTime.now(), null));
                        JOptionPane.showMessageDialog(userFrame, "Entry recorded successfully.");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(userFrame, "Error: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(userFrame, "Invalid VRN. Please try again.");
            }
        });

        userFrame.add(instructionLabel, BorderLayout.NORTH);
        userFrame.add(vrnField, BorderLayout.CENTER);
        userFrame.add(submitButton, BorderLayout.SOUTH);
        userFrame.setVisible(true);
    }

    private static void openAdminMode(JFrame parentFrame) {
        JFrame adminFrame = new JFrame("Admin Login");
        adminFrame.setSize(400, 300);
        adminFrame.setLayout(new BorderLayout(10, 10));

        JLabel instructionLabel = new JLabel("Enter admin credentials:", SwingConstants.CENTER);
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passwordField);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (username.equals("admin") && password.equals("admin123")) {
                openAdminDashboard(adminFrame);
            } else {
                JOptionPane.showMessageDialog(adminFrame, "Invalid credentials. Please try again.");
            }
        });

        adminFrame.add(instructionLabel, BorderLayout.NORTH);
        adminFrame.add(inputPanel, BorderLayout.CENTER);
        adminFrame.add(loginButton, BorderLayout.SOUTH);
        adminFrame.setVisible(true);
    }

    private static void openAdminDashboard(JFrame parentFrame) {
        JFrame dashboardFrame = new JFrame("Admin Dashboard");
        dashboardFrame.setSize(600, 400);
        dashboardFrame.setLayout(new BorderLayout(10, 10));

        JLabel instructionLabel = new JLabel("Choose an admin action:", SwingConstants.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JButton viewParkedButton = new JButton("View Parked Vehicles");
        viewParkedButton.addActionListener(e -> openViewParked(dashboardFrame));

        JButton searchRecordsButton = new JButton("Search Records");
        JButton amendRecordsButton = new JButton("Amend Records");
        JButton deleteRecordsButton = new JButton("Delete Records");

        searchRecordsButton.addActionListener(
                e -> JOptionPane.showMessageDialog(dashboardFrame, "Search Records feature in progress!"));
        amendRecordsButton.addActionListener(
                e -> JOptionPane.showMessageDialog(dashboardFrame, "Amend Records feature in progress!"));
        deleteRecordsButton.addActionListener(
                e -> JOptionPane.showMessageDialog(dashboardFrame, "Delete Records feature in progress!"));

        buttonPanel.add(viewParkedButton);
        buttonPanel.add(searchRecordsButton);
        buttonPanel.add(amendRecordsButton);
        buttonPanel.add(deleteRecordsButton);

        dashboardFrame.add(instructionLabel, BorderLayout.NORTH);
        dashboardFrame.add(buttonPanel, BorderLayout.CENTER);
        dashboardFrame.setVisible(true);
    }

    private static void openViewParked(JFrame parentFrame) {
        try {
            JFrame viewFrame = new JFrame("Parked Vehicles");
            viewFrame.setSize(600, 400);

            CSVHandler handler = new CSVHandler();
            List<VehicleRecord> records = handler.readRecords();

            String[] columns = { "VRN", "Entry Time", "Duration", "Cost" };
            DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

            for (VehicleRecord record : records) {
                if (record.isCurrentlyParked()) {
                    tableModel.addRow(new Object[] {
                            record.getCarVRN(),
                            AppUtil.formatDateTime(record.getEntryTime()),
                            record.calculateDuration(),
                            record.calculateCost(5.0) // Example hourly rate
                    });
                }
            }

            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);

            viewFrame.add(scrollPane, BorderLayout.CENTER);
            viewFrame.setVisible(true);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(parentFrame, "Error: " + e.getMessage());
        }
    }
}

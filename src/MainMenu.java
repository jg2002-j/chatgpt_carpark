import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
  public static void main(String[] args) {
    // Launch the application in the Swing thread
    SwingUtilities.invokeLater(() -> {
      // Create the main frame
      JFrame frame = new JFrame("Car Park Management System");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(600, 400);
      frame.setLayout(new BorderLayout(20, 20));

      // Create title label
      JLabel title = new JLabel("Car Park Management System", SwingConstants.CENTER);
      title.setFont(new Font("Space Grotesk", Font.BOLD, 24));
      title.setForeground(Color.WHITE);

      // Panel for buttons
      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new GridLayout(1, 2, 20, 20));
      buttonPanel.setOpaque(false);

      // Create buttons
      JButton userModeButton = new JButton("User Mode");
      JButton adminModeButton = new JButton("Admin Mode");

      // Add action listeners to buttons
      userModeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          JOptionPane.showMessageDialog(frame, "User Mode not yet implemented.");
        }
      });

      adminModeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          JOptionPane.showMessageDialog(frame, "Admin Mode not yet implemented.");
        }
      });

      // Add buttons to the panel
      buttonPanel.add(userModeButton);
      buttonPanel.add(adminModeButton);

      // Add title and button panel to the frame
      frame.add(title, BorderLayout.NORTH);
      frame.add(buttonPanel, BorderLayout.CENTER);

      // Set a dark retro theme
      frame.getContentPane().setBackground(Color.BLACK);

      // Make the frame visible
      frame.setVisible(true);
    });
  }
}

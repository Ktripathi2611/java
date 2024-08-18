import java.awt.*;
import javax.swing.*;

public class CRMApplication {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("CRM Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        // Set the size to 1920x1080
        frame.setSize(400, 600);
        frame.setResizable(false);

        // Set the background color to a light gray
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();

        // Header panel with necessary components
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(1920, 96)); // 1 inch = 96 pixels at 96 DPI
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel blue color

        JLabel headerLabel = new JLabel("Welcome to CRM");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Add components to the header panel
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        // Add additional components to the header if needed
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setForeground(new Color(70, 130, 180));
        headerPanel.add(logoutButton, BorderLayout.EAST);

        // Set constraints for the header panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding
        frame.add(headerPanel, gbc);

        // Space for additional content
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // Add border for better visual separation

        // Add CRM components
        JLabel customerLabel = new JLabel("Customer Name:");
        JTextField customerField = new JTextField(20);
        JLabel contactLabel = new JLabel("Contact Number:");
        JTextField contactField = new JTextField(20);
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        JButton saveButton = new JButton("Save");

        // Set constraints for CRM components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 5, 5, 5);
        contentPanel.add(customerLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        contentPanel.add(customerField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(contactLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPanel.add(contactField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPanel.add(emailField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        contentPanel.add(saveButton, gbc);

        // Set constraints for the content panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding
        frame.add(contentPanel, gbc);

        // Footer label
        JLabel footerLabel = new JLabel("This is the end");
        footerLabel.setOpaque(true);
        footerLabel.setBackground(new Color(70, 130, 180));
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Set constraints for the footer
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding
        frame.add(footerLabel, gbc);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Display the frame
        frame.setVisible(true);
    }
}

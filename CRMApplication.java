import java.awt.*;
import javax.swing.*;

public class CRMApplication {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("CRM Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        // Set the size to 1920x1080
        frame.setSize(1920, 1080);
        frame.setResizable(false);

        // Set the background color to a light gray
        frame.getContentPane().setBackground(new Color(240, 240, 240));

        GridBagConstraints gbc = new GridBagConstraints();

        // Header panel with necessary components
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(1920, 96)); // 1 inch = 96 pixels at 96 DPI
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel blue color

        // Create a panel for the header items
        JPanel headerItemsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerItemsPanel.setOpaque(false);

        // Add header items from left to right as buttons
        String[] headerItems = {
            "Dashboard", "Tasks", "Calendar", "Recent Activities", "Sales Pipeline",
            "Notifications", "Leads", "Opportunities", "Customer Support Tickets",
            "Reports", "Email Inbox", "Social Media Feeds", "Notes", "Custom Widgets"
        };

        for (String item : headerItems) {
            JButton button = new JButton(item);
            button.setBackground(Color.WHITE);
            button.setForeground(new Color(70, 130, 180));
            headerItemsPanel.add(button);
        }

        // Add the header items panel to the header panel
        headerPanel.add(headerItemsPanel, BorderLayout.CENTER);

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
        String[] components = {
            "Dashboard: Provides an overview of key metrics and performance indicators.",
            "Tasks: Lists upcoming tasks and to-dos.",
            "Calendar: Displays scheduled meetings, calls, and events.",
            "Recent Activities: Shows recent interactions and updates.",
            "Sales Pipeline: Visualizes the current status of sales opportunities.",
            "Notifications: Alerts for important updates and reminders.",
            "Leads: Lists new and active leads.",
            "Opportunities: Tracks potential sales opportunities.",
            "Customer Support Tickets: Displays open and recent support tickets.",
            "Reports: Quick access to frequently used reports.",
            "Email Inbox: Integrates email communications.",
            "Social Media Feeds: Shows recent social media interactions.",
            "Notes: Allows for quick note-taking and reminders.",
            "Custom Widgets: Any additional custom components tailored to specific needs."
        };

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 5, 5, 5);

        for (String component : components) {
            JLabel label = new JLabel(component);
            contentPanel.add(label, gbc);
            gbc.gridy++;
        }

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

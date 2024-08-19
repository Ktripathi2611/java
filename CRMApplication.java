import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CRMApplication {
    private static JPanel contentPanel;

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("CRM Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        // Set the size to 1920x1080
        frame.setSize(1920, 1080);
        frame.setResizable(true); // Allow resizing

        // Set the background color to a light gray
        frame.getContentPane().setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();

        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(70, 130, 180));
        menuBar.setForeground(Color.WHITE);

        // Create menus
        String[] menuItems = {
            "Dashboard", "Tasks", "Calendar", "Recent Activities", "Sales Pipeline",
            "Notifications", "Leads", "Opportunities", "Customer Support Tickets",
            "Reports", "Email Inbox", "Social Media Feeds", "Notes", "Custom Widgets",
            "Contacts", "Analytics", "Settings", "Integrations", "Help & Support",
            "User Management", "Billing", "Marketing Campaigns", "Feedback"
        };

        for (String item : menuItems) {
            JMenu menu = new JMenu(item);
            menu.setForeground(Color.WHITE);
            menu.setFont(new Font("Arial", Font.BOLD, 14));
            menuBar.add(menu);

            // Add mouse listener for hover effect
            menu.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    menu.setForeground(Color.YELLOW); // Change color on hover
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    menu.setForeground(Color.WHITE); // Revert color when not hovered
                }
            });

            // Add menu item for click event
            JMenuItem menuItem = new JMenuItem(item);
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    displaySampleInfo(item);
                }
            });
            menu.add(menuItem);
        }

        // Set the menu bar
        frame.setJMenuBar(menuBar);

        // Space for additional content
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // Add border for better visual separation

        // Set constraints for the content panel
        gbc.gridx = 0;
        gbc.gridy = 0;
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
        gbc.gridy = 1;
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

    private static void displaySampleInfo(String item) {
        contentPanel.removeAll();
        JLabel infoLabel = new JLabel(item + " Content Here");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        infoLabel.setForeground(new Color(70, 130, 180));
        contentPanel.add(infoLabel);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}

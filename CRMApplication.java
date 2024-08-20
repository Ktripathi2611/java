import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CRMApplication {
    private static JPanel contentPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CRMApplication::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // Create the frame
        JFrame frame = new JFrame("CRM Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1920, 1080);
        frame.setResizable(true);
        frame.setBackground(new Color(245, 245, 245));

        // Create and set the content panel
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Add buttons to the content panel
        addButtonsToContentPanel();

        frame.add(contentPanel, BorderLayout.CENTER);

        // Create and set the footer
        JLabel footerLabel = createFooterLabel();
        frame.add(footerLabel, BorderLayout.SOUTH);

        // Center the frame and display it
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void addButtonsToContentPanel() {
        String[] buttonLabels = {
            "Dashboard", "Tasks", "Calendar", "Recent Activities", "Sales Pipeline",
            "Notifications", "Leads", "Opportunities", "Customer Support Tickets",
            "Reports", "Email Inbox", "Social Media Feeds", "Notes", "Custom Widgets",
            "Contacts", "Analytics", "Settings", "Integrations", "Help & Support",
            "User Management", "Billing", "Marketing Campaigns", "Feedback"
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setBackground(new Color(70, 130, 180));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.addActionListener(e -> displaySampleInfo(label));
            contentPanel.add(button, gbc);

            gbc.gridx++;
            if (gbc.gridx == 4) { // Adjust the number of columns as needed
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }
    }

    private static JLabel createFooterLabel() {
        JLabel footerLabel = new JLabel("This is the end");
        footerLabel.setOpaque(true);
        footerLabel.setBackground(new Color(70, 130, 180));
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        return footerLabel;
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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CRMApplication {
    private static JPanel contentPanel;
    private static CardLayout cardLayout;
    private static JPanel mainPanel;
    private static JPanel sidebarPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CRMApplication::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("CRM Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1920, 1080);
        frame.setResizable(true);
        frame.setBackground(new Color(245, 245, 245));

        JPanel topPanel = createTopPanel();
        frame.add(topPanel, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        addHeaderToContentPanel();

        mainPanel.add(contentPanel, "ContentPanel");
        frame.add(mainPanel, BorderLayout.CENTER);

        sidebarPanel = createSidebarPanel();
        frame.add(sidebarPanel, BorderLayout.WEST);

        JLabel footerLabel = createFooterLabel();
        frame.add(footerLabel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 245, 245));

        JButton backButton = new JButton("Back");
        styleButton(backButton);
        backButton.addActionListener(e -> goBack());
        topPanel.add(backButton, BorderLayout.WEST);

        JButton menuButton = new JButton("Menu");
        styleButton(menuButton);
        menuButton.addActionListener(e -> showMenu());
        topPanel.add(menuButton, BorderLayout.EAST);

        return topPanel;
    }

    private static JPanel createSidebarPanel() {
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(new Color(70, 130, 180));

        String[][] sections = {
            {"Dashboard", "Tasks", "Calendar", "Recent Activities"},
            {"Sales Pipeline", "Notifications", "Leads", "Opportunities"},
            {"Customer Support Tickets", "Reports", "Email Inbox", "Social Media Feeds"},
            {"Notes", "Custom Widgets", "Contacts", "Analytics"},
            {"Settings", "Integrations", "Help & Support", "User Management"},
            {"Billing", "Marketing Campaigns", "Feedback"}
        };

        for (String[] section : sections) {
            for (String label : section) {
                JButton button = new JButton(label);
                styleButton(button);
                button.addActionListener(e -> displaySampleInfo(label));
                sidebarPanel.add(button);
            }
            sidebarPanel.add(Box.createVerticalStrut(10)); // Add space between sections
        }

        return sidebarPanel;
    }

    private static void addHeaderToContentPanel() {
        JLabel headerLabel = new JLabel("This is Home Page");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(new Color(70, 130, 180));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        contentPanel.add(headerLabel, gbc);
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
        JPanel infoPanel = new JPanel(new BorderLayout());
        JTextArea infoTextArea = new JTextArea();
        infoTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
        infoTextArea.setForeground(new Color(70, 130, 180));
        infoTextArea.setEditable(false);

        switch (item) {
            case "Dashboard":
                infoTextArea.setText("Welcome to the Dashboard!\n\n- Total Sales: $1,234,567\n- New Leads: 123\n- Open Tickets: 45\n- Upcoming Meetings: 3");
                break;
            case "Tasks":
                infoTextArea.setText("Tasks Overview:\n\n1. Follow up with client A\n2. Prepare sales report\n3. Update CRM system\n4. Schedule team meeting");
                break;
            case "Calendar":
                infoTextArea.setText("Today's Events:\n\n- 10:00 AM: Team Standup\n- 1:00 PM: Client Meeting\n- 3:00 PM: Project Review");
                break;
            case "Recent Activities":
                infoTextArea.setText("Recent Activities:\n\n- Called client B\n- Sent proposal to client C\n- Closed deal with client D");
                break;
            // Add more cases for other sections as needed
            default:
                infoTextArea.setText(item + " Content Here");
                break;
        }

        infoPanel.add(new JScrollPane(infoTextArea), BorderLayout.CENTER);
        mainPanel.add(infoPanel, item);
        cardLayout.show(mainPanel, item);
    }

    private static void goBack() {
        cardLayout.show(mainPanel, "ContentPanel");
    }

    private static void showMenu() {
        cardLayout.show(mainPanel, "ContentPanel");
    }

    private static void styleButton(JButton button) {
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}

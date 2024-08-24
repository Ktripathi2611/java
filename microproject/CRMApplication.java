package microproject;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CRMApplication {
    private static JPanel contentPanel;
    private static CardLayout cardLayout;
    private static JPanel mainPanel;
    private static JPanel sidebarPanel;
    private static JPanel sectionButtonPanel;
    private static String previousPanel = "DefaultPage"; // Track the previous panel for the back button

    private static final Color PRIMARY_DARK = new Color(4, 13, 18); // Dark Blue-Gray
    private static final Color SECONDARY_DARK = new Color(24, 61, 61); // Teal Blue
    private static final Color ACCENT_COLOR = new Color(92, 131, 116); // Muted Olive Green
    private static final Color SIDEBAR_COLOR = new Color(147, 177, 166); // Light Olive Green
    private static final Color HIGHLIGHT_COLOR = new Color(92, 131, 116); // Muted Olive Green

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CRMApplication::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("CRM Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1920, 1080);
        frame.setResizable(true);

        // Modern Dark Theme Background
        frame.getContentPane().setBackground(PRIMARY_DARK);

        sidebarPanel = createSidebarPanel();
        frame.add(sidebarPanel, BorderLayout.WEST);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(SECONDARY_DARK);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(SECONDARY_DARK);
        contentPanel.setBorder(BorderFactory.createLineBorder(ACCENT_COLOR));

        mainPanel.add(contentPanel, "ContentPanel");
        frame.add(mainPanel, BorderLayout.CENTER);

        JLabel footerLabel = createFooterLabel();
        frame.add(footerLabel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        displayDefaultPage();
    }

    private static JPanel createSidebarPanel() {
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(SIDEBAR_COLOR);

        String[] mainSections = {"Dashboard", "Sales Pipeline", "Customer Support Tickets", "Notes", "Settings", "Billing", "Summary"};

        for (String section : mainSections) {
            JButton button = new JButton(section);
            styleButton(button);
            button.setToolTipText(section); // Tooltip for better usability
            button.addActionListener(e -> {
                if ("Summary".equals(section)) {
                    displaySummaryPage();
                } else {
                    displaySampleInfo(section);
                }
            });
            sidebarPanel.add(button);
            sidebarPanel.add(Box.createVerticalStrut(10)); // Add space between sections
        }

        return sidebarPanel;
    }

    private static JLabel createFooterLabel() {
        JLabel footerLabel = new JLabel("This is the end");
        footerLabel.setOpaque(true);
        footerLabel.setBackground(SECONDARY_DARK);
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        footerLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, ACCENT_COLOR));
        return footerLabel;
    }

    private static void displayDefaultPage() {
        JPanel defaultPanel = new JPanel(new BorderLayout());
        defaultPanel.setBackground(SECONDARY_DARK);
        JLabel defaultLabel = new JLabel("This is Home Page");
        defaultLabel.setFont(new Font("Arial", Font.BOLD, 24));
        defaultLabel.setForeground(Color.WHITE);
        defaultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        defaultPanel.add(defaultLabel, BorderLayout.CENTER);

        mainPanel.add(defaultPanel, "DefaultPage");
        cardLayout.show(mainPanel, "DefaultPage");
    }

    private static void displaySampleInfo(String item) {
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(SECONDARY_DARK);

        // Container panel for centered content
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(SECONDARY_DARK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 20, 20, 20);

        // Information content
        String title = "";
        String[] infoLines = new String[]{};
        Color infoBackground = ACCENT_COLOR;

        switch (item) {
            case "Dashboard":
                title = "Dashboard Overview";
                infoLines = new String[]{
                    "• Total Sales: $1,234,567",
                    "• New Leads: 123",
                    "• Open Tickets: 45",
                    "• Upcoming Meetings: 3"
                };
                infoBackground = HIGHLIGHT_COLOR; // Lighten background for Dashboard
                break;
            case "Sales Pipeline":
                title = "Sales Pipeline Overview";
                infoLines = new String[]{
                    "• Total Opportunities: 50",
                    "• New Leads: 20",
                    "• Closed Deals: 10"
                };
                infoBackground = HIGHLIGHT_COLOR; // Unique color for Sales Pipeline
                break;
            case "Customer Support Tickets":
                title = "Customer Support Overview";
                infoLines = new String[]{
                    "• Open Tickets: 30",
                    "• Closed Tickets: 70",
                    "• Average Response Time: 2 hours"
                };
                infoBackground = HIGHLIGHT_COLOR; // Different color for Customer Support
                break;
            case "Notes":
                title = "Notes Overview";
                infoLines = new String[]{
                    "• Total Notes: 100",
                    "• Recent Notes: 10",
                    "• Archived Notes: 50"
                };
                infoBackground = HIGHLIGHT_COLOR; // Different color for Notes
                break;
            case "Settings":
                title = "Settings Overview";
                infoLines = new String[]{
                    "• User Settings",
                    "• System Settings",
                    "• Notification Settings"
                };
                infoBackground = HIGHLIGHT_COLOR; // Different color for Settings
                break;
            case "Billing":
                title = "Billing Overview";
                infoLines = new String[]{
                    "• Total Invoices: 200",
                    "• Pending Payments: 50",
                    "• Completed Payments: 150"
                };
                infoBackground = HIGHLIGHT_COLOR; // Different color for Billing
                break;
            default:
                title = item + " Content Here";
                infoLines = new String[]{};
                infoBackground = HIGHLIGHT_COLOR; // Default color
                break;
        }

        // Create title label
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add title to the centerPanel
        gbc.gridy++;
        centerPanel.add(titleLabel, gbc);

        // Add information labels to the centerPanel
        for (String line : infoLines) {
            JLabel infoLabel = new JLabel(line);
            infoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            infoLabel.setForeground(Color.WHITE);
            infoLabel.setBackground(infoBackground);
            infoLabel.setOpaque(true);
            gbc.gridy++;
            centerPanel.add(infoLabel, gbc);
        }

        // Add bottom button panel
        JPanel bottomButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomButtonPanel.setBackground(SECONDARY_DARK);

        // Back Button
        JButton backButton = new JButton("Back");
        styleButton(backButton);
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, previousPanel);
        });
        bottomButtonPanel.add(backButton);

        // Home Button
        JButton homeButton = new JButton("Home");
        styleButton(homeButton);
        homeButton.addActionListener(e -> {
            displayDefaultPage();
        });
        bottomButtonPanel.add(homeButton);

        // Add components to infoPanel
        infoPanel.add(centerPanel, BorderLayout.CENTER);
        infoPanel.add(bottomButtonPanel, BorderLayout.SOUTH);

        mainPanel.add(infoPanel, item);
        cardLayout.show(mainPanel, item);

        // Update previous panel
        previousPanel = item;
    }

    private static void displaySummaryPage() {
        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new GridLayout(3, 2, 10, 10));
        summaryPanel.setBackground(SECONDARY_DARK);
        summaryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Define section info
        String[][] sectionInfo = {
            {"Dashboard Overview", "• Total Sales: $1,234,567\n• New Leads: 123\n• Open Tickets: 45\n• Upcoming Meetings: 3"},
            {"Sales Pipeline Overview", "• Total Opportunities: 50\n• New Leads: 20\n• Closed Deals: 10"},
            {"Customer Support Overview", "• Open Tickets: 30\n• Closed Tickets: 70\n• Average Response Time: 2 hours"},
            {"Notes Overview", "• Total Notes: 100\n• Recent Notes: 10\n• Archived Notes: 50"},
            {"Settings Overview", "• User Settings\n• System Settings\n• Notification Settings"},
            {"Billing Overview", "• Total Invoices: 200\n• Pending Payments: 50\n• Completed Payments: 150"}
        };

        // Create summary cards
        for (String[] info : sectionInfo) {
            JPanel cardPanel = new JPanel(new BorderLayout());
            cardPanel.setBackground(ACCENT_COLOR);
            cardPanel.setBorder(BorderFactory.createLineBorder(HIGHLIGHT_COLOR));

            JLabel titleLabel = new JLabel(info[0]);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            cardPanel.add(titleLabel, BorderLayout.NORTH);

            JTextArea textArea = new JTextArea(info[1]);
            textArea.setFont(new Font("Arial", Font.PLAIN, 16));
            textArea.setForeground(Color.WHITE);
            textArea.setBackground(PRIMARY_DARK);
            textArea.setEditable(false);
            textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            cardPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

            summaryPanel.add(cardPanel);
        }

        // Bottom button panel
        JPanel bottomButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomButtonPanel.setBackground(SECONDARY_DARK);

        // Back Button
        JButton backButton = new JButton("Back");
        styleButton(backButton);
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, previousPanel);
        });
        bottomButtonPanel.add(backButton);

        // Home Button
        JButton homeButton = new JButton("Home");
        styleButton(homeButton);
        homeButton.addActionListener(e -> {
            displayDefaultPage();
        });
        bottomButtonPanel.add(homeButton);

        // Add components to summaryPanel
        summaryPanel.add(bottomButtonPanel, BorderLayout.SOUTH);

        mainPanel.add(summaryPanel, "Summary");
        cardLayout.show(mainPanel, "Summary");

        // Update previous panel
        previousPanel = "Summary";
    }

    private static void styleButton(JButton button) {
        button.setBackground(ACCENT_COLOR);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding for better spacing
    }
}

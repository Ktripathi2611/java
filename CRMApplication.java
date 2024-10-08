import java.awt.*;
// import java.awt.event.*;
import javax.swing.*;

public class CRMApplication {
    private static JPanel contentPanel;
    private static CardLayout cardLayout;
    private static JPanel mainPanel;
    private static JPanel sidebarPanel;
    private static String previousPanel = "DefaultPage"; // Track the previous panel for the back button

    private static final Color PRIMARY_DARK = new Color(4, 13, 18); // Dark Blue-Gray
    private static final Color SECONDARY_DARK = new Color(24, 61, 61); // Teal Blue
    private static final Color ACCENT_COLOR = new Color(92, 131, 116); // Muted Olive Green
    private static final Color SIDEBAR_COLOR = new Color(147, 177, 166); // Light Olive Green
    private static final Color HIGHLIGHT_COLOR = new Color(92, 131, 116); // Muted Olive Green

    private static final String[] SECTIONS = {
        "Dashboard", "Sales Pipeline", "Customer Support Tickets", 
        "Notes", "Settings", "Billing", "Summary"
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CRMApplication::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("CRM Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1920, 1080);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);

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

        frame.setVisible(true);

        displayDefaultPage();
    }

    private static JPanel createSidebarPanel() {
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(SIDEBAR_COLOR);

        for (String section : SECTIONS) {
            JButton button = new JButton(section);
            styleButton(button);
            button.setToolTipText(section); // Tooltip for better usability
            button.addActionListener(e -> handleSectionClick(section));
            sidebarPanel.add(button);
            sidebarPanel.add(Box.createVerticalStrut(10)); // Add space between sections
        }

        return sidebarPanel;
    }

    private static JLabel createFooterLabel() {
        JLabel footerLabel = new JLabel("CRM Application");
        footerLabel.setOpaque(true);
        footerLabel.setBackground(SECONDARY_DARK);
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        footerLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, ACCENT_COLOR));
        return footerLabel;
    }

    private static void handleSectionClick(String section) {
        if ("Summary".equals(section)) {
            displaySummaryPage();
        } else {
            displaySectionPage(section);
        }
    }

    private static void displayDefaultPage() {
        displaySectionPage("This is Home Page", new String[]{});
    }

    private static void displaySectionPage(String section) {
        String title = section + " Overview";
        String[] infoLines = getInfoLinesForSection(section);

        displaySectionPage(title, infoLines);
    }

    private static String[] getInfoLinesForSection(String section) {
        switch (section) {
            case "Dashboard":
                return new String[]{"• Total Sales: $1,234,567", "• New Leads: 123", "• Open Tickets: 45", "• Upcoming Meetings: 3"};
            case "Sales Pipeline":
                return new String[]{"• Total Opportunities: 50", "• New Leads: 20", "• Closed Deals: 10"};
            case "Customer Support Tickets":
                return new String[]{"• Open Tickets: 30", "• Closed Tickets: 70", "• Average Response Time: 2 hours"};
            case "Notes":
                return new String[]{"• Total Notes: 100", "• Recent Notes: 10", "• Archived Notes: 50"};
            case "Settings":
                return new String[]{"• User Settings", "• System Settings", "• Notification Settings"};
            case "Billing":
                return new String[]{"• Total Invoices: 200", "• Pending Payments: 50", "• Completed Payments: 150"};
            default:
                return new String[]{};
        }
    }

    private static void displaySectionPage(String title, String[] infoLines) {
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(SECONDARY_DARK);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(SECONDARY_DARK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridy++;
        centerPanel.add(titleLabel, gbc);

        for (String line : infoLines) {
            JLabel infoLabel = new JLabel(line);
            infoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            infoLabel.setForeground(Color.WHITE);
            gbc.gridy++;
            centerPanel.add(infoLabel, gbc);
        }

        JPanel bottomButtonPanel = createBottomButtonPanel();

        infoPanel.add(centerPanel, BorderLayout.CENTER);
        infoPanel.add(bottomButtonPanel, BorderLayout.SOUTH);

        mainPanel.add(infoPanel, title);
        cardLayout.show(mainPanel, title);

        previousPanel = title;
    }

    private static void displaySummaryPage() {
        JPanel summaryPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        summaryPanel.setBackground(SECONDARY_DARK);
        summaryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[][] sectionInfo = {
            {"Dashboard Overview", "• Total Sales: $1,234,567\n• New Leads: 123\n• Open Tickets: 45\n• Upcoming Meetings: 3"},
            {"Sales Pipeline Overview", "• Total Opportunities: 50\n• New Leads: 20\n• Closed Deals: 10"},
            {"Customer Support Overview", "• Open Tickets: 30\n• Closed Tickets: 70\n• Average Response Time: 2 hours"},
            {"Notes Overview", "• Total Notes: 100\n• Recent Notes: 10\n• Archived Notes: 50"},
            {"Settings Overview", "• User Settings\n• System Settings\n• Notification Settings"},
            {"Billing Overview", "• Total Invoices: 200\n• Pending Payments: 50\n• Completed Payments: 150"}
        };

        for (String[] info : sectionInfo) {
            JPanel cardPanel = createSummaryCard(info[0], info[1]);
            summaryPanel.add(cardPanel);
        }

        JPanel bottomButtonPanel = createBottomButtonPanel();
        summaryPanel.add(bottomButtonPanel);

        mainPanel.add(summaryPanel, "Summary");
        cardLayout.show(mainPanel, "Summary");

        previousPanel = "Summary";
    }

    private static JPanel createSummaryCard(String title, String details) {
        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBackground(ACCENT_COLOR);
        cardPanel.setBorder(BorderFactory.createLineBorder(HIGHLIGHT_COLOR));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardPanel.add(titleLabel, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea(details);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(PRIMARY_DARK);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        cardPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        return cardPanel;
    }

    private static JPanel createBottomButtonPanel() {
        JPanel bottomButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomButtonPanel.setBackground(SECONDARY_DARK);

        JButton backButton = new JButton("Back");
        styleButton(backButton);
        backButton.addActionListener(e -> cardLayout.show(mainPanel, previousPanel));
        bottomButtonPanel.add(backButton);

        JButton homeButton = new JButton("Home");
        styleButton(homeButton);
        homeButton.addActionListener(e -> displayDefaultPage());
        bottomButtonPanel.add(homeButton);

        return bottomButtonPanel;
    }

    private static void styleButton(JButton button) {
        button.setBackground(ACCENT_COLOR);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false); // Prevent focus outline
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
}

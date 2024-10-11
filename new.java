import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CRMSystem {

    private static CardLayout cardLayout;
    private static JPanel mainContentPanel;

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("CRM System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(new BorderLayout());

        // Navbar panel
        JPanel navbarPanel = createNavbarPanel();
        frame.add(navbarPanel, BorderLayout.NORTH);

        // Sidebar panel
        JPanel sidebarPanel = createSidebarPanel();
        frame.add(sidebarPanel, BorderLayout.WEST);

        // Main content panel
        mainContentPanel = createMainContentPanel();
        frame.add(mainContentPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // Method to create the navbar panel
    private static JPanel createNavbarPanel() {
        JPanel navbarPanel = new JPanel(new BorderLayout());
        navbarPanel.setBackground(Color.DARK_GRAY);
        navbarPanel.setPreferredSize(new Dimension(0, 50));

        JLabel titleLabel = new JLabel("CRM System");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        navbarPanel.add(titleLabel, BorderLayout.WEST);

        JPanel navLinksPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        navLinksPanel.setBackground(Color.DARK_GRAY);
        String[] navLinks = {"Dashboard", "Settings", "Logout"};
        for (String link : navLinks) {
            JButton button = new JButton(link);
            styleButton(button);
            navLinksPanel.add(button);
        }

        navbarPanel.add(navLinksPanel, BorderLayout.EAST);
        return navbarPanel;
    }

    // Method to create the sidebar panel
    private static JPanel createSidebarPanel() {
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(Color.LIGHT_GRAY);
        sidebarPanel.setPreferredSize(new Dimension(200, 0));

        JLabel menuLabel = new JLabel("Menu");
        menuLabel.setFont(new Font("Arial", Font.BOLD, 16));
        sidebarPanel.add(menuLabel);

        String[] menuItems = {"Dashboard", "Leads Management", "Contact Management", "Opportunities", "Reports"};
        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.addActionListener(e -> handleMenuItemClick(item));
            sidebarPanel.add(button);
            sidebarPanel.add(Box.createVerticalStrut(10)); // Adds space between buttons
        }

        return sidebarPanel;
    }

    // Method to create the main content panel with different sections
    private static JPanel createMainContentPanel() {
        cardLayout = new CardLayout();
        JPanel panel = new JPanel(cardLayout);

        // Dashboard Panel
        JPanel dashboardPanel = new JPanel();
        dashboardPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        JLabel dashboardTitle = new JLabel("Dashboard");
        dashboardTitle.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        dashboardPanel.add(dashboardTitle, gbc);
        JLabel dashboardContent = new JLabel("<html><strong>Total Leads:</strong> 120<br><strong>Opportunities:</strong> 75<br><strong>Tasks Due Today:</strong> 5</html>");
        gbc.gridy = 1;
        dashboardPanel.add(dashboardContent, gbc);
        panel.add(dashboardPanel, "Dashboard");

        // Leads Management Panel
        JPanel leadsPanel = createLeadsManagementPanel();
        panel.add(leadsPanel, "Leads Management");

        // Contact Management Panel
        JPanel contactPanel = createContactManagementPanel();
        panel.add(contactPanel, "Contact Management");

        // Opportunities Panel
        JPanel opportunitiesPanel = createOpportunitiesPanel();
        panel.add(opportunitiesPanel, "Opportunities");

        // Reports Panel
        JPanel reportsPanel = createReportsPanel();
        panel.add(reportsPanel, "Reports");

        return panel;
    }

    // Leads Management Panel creation
    private static JPanel createLeadsManagementPanel() {
        JPanel leadsPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Leads Management");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        leadsPanel.add(title, BorderLayout.NORTH);

        String[] columnNames = {"Lead Name", "Contact", "Source", "Status", "Assigned Rep"};
        Object[][] data = {
            {"John Doe", "john@example.com", "Website", "Contacted", "Jane Smith"},
            {"Alice Brown", "alice@example.com", "Referral", "New", "Chris Evans"}
        };
        JTable leadsTable = new JTable(data, columnNames);
        leadsPanel.add(new JScrollPane(leadsTable), BorderLayout.CENTER);

        JButton addLeadButton = new JButton("Add New Lead");
        leadsPanel.add(addLeadButton, BorderLayout.SOUTH);

        return leadsPanel;
    }

    // Contact Management Panel creation
    private static JPanel createContactManagementPanel() {
        JPanel contactPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Contact Management");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        contactPanel.add(title, BorderLayout.NORTH);

        String[] columnNames = {"Contact Name", "Email", "Phone", "Company", "Job Title"};
        Object[][] data = {
            {"Jane Smith", "jane@example.com", "(123) 456-7890", "Acme Corp", "CEO"},
            {"Bob Jones", "bob@example.com", "(987) 654-3210", "Beta Ltd", "CTO"}
        };
        JTable contactsTable = new JTable(data, columnNames);
        contactPanel.add(new JScrollPane(contactsTable), BorderLayout.CENTER);

        JButton addContactButton = new JButton("Add New Contact");
        contactPanel.add(addContactButton, BorderLayout.SOUTH);

        return contactPanel;
    }

    // Opportunities Panel creation
    private static JPanel createOpportunitiesPanel() {
        JPanel opportunitiesPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Opportunities");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        opportunitiesPanel.add(title, BorderLayout.NORTH);

        String[] columnNames = {"Opportunity Name", "Stage", "Value", "Expected Close Date", "Assigned Rep"};
        Object[][] data = {
            {"Website Redesign", "Proposal Sent", "$15,000", "2024-10-31", "Jane Smith"},
            {"CRM Integration", "Negotiation", "$25,000", "2024-11-15", "Chris Evans"}
        };
        JTable opportunitiesTable = new JTable(data, columnNames);
        opportunitiesPanel.add(new JScrollPane(opportunitiesTable), BorderLayout.CENTER);

        JButton addOpportunityButton = new JButton("Add New Opportunity");
        opportunitiesPanel.add(addOpportunityButton, BorderLayout.SOUTH);

        return opportunitiesPanel;
    }

    // Reports Panel creation
    private static JPanel createReportsPanel() {
        JPanel reportsPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Reports");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        reportsPanel.add(title, BorderLayout.NORTH);

        JTextArea reportContent = new JTextArea("Sales Performance, Lead Conversion, etc.");
        reportContent.setEditable(false);
        reportsPanel.add(reportContent, BorderLayout.CENTER);

        JPanel reportLinks = new JPanel(new GridLayout(0, 1));
        reportLinks.add(new JLabel("<html><a href='#'>Monthly Sales Report</a></html>"));
        reportLinks.add(new JLabel("<html><a href='#'>Lead Conversion Report</a></html>"));
        reportLinks.add(new JLabel("<html><a href='#'>Customer Satisfaction Report</a></html>"));

        reportsPanel.add(reportLinks, BorderLayout.SOUTH);

        return reportsPanel;
    }

    // Handle sidebar navigation
    private static void handleMenuItemClick(String item) {
        cardLayout.show(mainContentPanel, item);
    }

    // Helper method to style buttons consistently
    private static void styleButton(JButton button) {
        button.setBackground(Color.GRAY);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
    }
}

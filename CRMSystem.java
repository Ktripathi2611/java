import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class CRMSystem {

    private static CardLayout cardLayout;
    private static JPanel mainContentPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("CRM System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLayout(new BorderLayout());

        JPanel navbarPanel = createNavbarPanel();
        frame.add(navbarPanel, BorderLayout.NORTH);

        JPanel sidebarPanel = createSidebarPanel();
        frame.add(sidebarPanel, BorderLayout.WEST);

        mainContentPanel = createMainContentPanel();
        frame.add(mainContentPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

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

    private static JPanel createMainContentPanel() {
        cardLayout = new CardLayout();
        JPanel panel = new JPanel(cardLayout);

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

        JPanel leadsPanel = createLeadsManagementPanel();
        panel.add(leadsPanel, "Leads Management");

        JPanel contactPanel = createContactManagementPanel();
        panel.add(contactPanel, "Contact Management");

        JPanel opportunitiesPanel = createOpportunitiesPanel();  // <-- This line now works because we have the method defined below.
        panel.add(opportunitiesPanel, "Opportunities");

        JPanel reportsPanel = createReportsPanel();
        panel.add(reportsPanel, "Reports");

        return panel;
    }

    private static JPanel createLeadsManagementPanel() {
        JPanel leadsPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Leads Management");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        leadsPanel.add(title, BorderLayout.NORTH);

        String[] columnNames = {"Lead Name", "Contact", "Source", "Status", "Assigned Rep"};
        DefaultTableModel leadsModel = new DefaultTableModel(columnNames, 0);
        JTable leadsTable = new JTable(leadsModel);

        // Sample data
        leadsModel.addRow(new Object[]{"John Doe", "john@example.com", "Website", "Contacted", "Jane Smith"});
        leadsModel.addRow(new Object[]{"Alice Brown", "alice@example.com", "Referral", "New", "Chris Evans"});

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(leadsModel);
        leadsTable.setRowSorter(sorter);

        leadsPanel.add(new JScrollPane(leadsTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addLeadButton = new JButton("Add Lead");
        JButton updateLeadButton = new JButton("Update Lead");
        JButton deleteLeadButton = new JButton("Delete Lead");

        addLeadButton.addActionListener(e -> addLead(leadsModel));
        updateLeadButton.addActionListener(e -> updateLead(leadsTable, leadsModel));
        deleteLeadButton.addActionListener(e -> deleteLead(leadsTable, leadsModel));

        buttonPanel.add(addLeadButton);
        buttonPanel.add(updateLeadButton);
        buttonPanel.add(deleteLeadButton);

        leadsPanel.add(buttonPanel, BorderLayout.SOUTH);

        return leadsPanel;
    }

    private static void addLead(DefaultTableModel model) {
        String name = JOptionPane.showInputDialog("Enter Lead Name:");
        String contact = JOptionPane.showInputDialog("Enter Contact:");
        String source = JOptionPane.showInputDialog("Enter Source:");
        String status = JOptionPane.showInputDialog("Enter Status:");
        String assignedRep = JOptionPane.showInputDialog("Enter Assigned Rep:");
        model.addRow(new Object[]{name, contact, source, status, assignedRep});
    }

    private static void updateLead(JTable table, DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to update.");
            return;
        }
        String name = JOptionPane.showInputDialog("Enter Lead Name:", model.getValueAt(selectedRow, 0));
        String contact = JOptionPane.showInputDialog("Enter Contact:", model.getValueAt(selectedRow, 1));
        String source = JOptionPane.showInputDialog("Enter Source:", model.getValueAt(selectedRow, 2));
        String status = JOptionPane.showInputDialog("Enter Status:", model.getValueAt(selectedRow, 3));
        String assignedRep = JOptionPane.showInputDialog("Enter Assigned Rep:", model.getValueAt(selectedRow, 4));

        model.setValueAt(name, selectedRow, 0);
        model.setValueAt(contact, selectedRow, 1);
        model.setValueAt(source, selectedRow, 2);
        model.setValueAt(status, selectedRow, 3);
        model.setValueAt(assignedRep, selectedRow, 4);
    }

    private static void deleteLead(JTable table, DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
            return;
        }
        model.removeRow(selectedRow);
    }

    private static JPanel createContactManagementPanel() {
        JPanel contactPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Contact Management");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        contactPanel.add(title, BorderLayout.NORTH);

        String[] columnNames = {"Contact Name", "Phone", "Email", "Address", "Company"};
        DefaultTableModel contactModel = new DefaultTableModel(columnNames, 0);
        JTable contactTable = new JTable(contactModel);

        // Sample data
        contactModel.addRow(new Object[]{"John Doe", "123-456-7890", "john@example.com", "123 Elm St", "Example Corp"});
        contactModel.addRow(new Object[]{"Jane Smith", "987-654-3210", "jane@example.com", "456 Oak St", "Tech Solutions"});

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(contactModel);
        contactTable.setRowSorter(sorter);

        contactPanel.add(new JScrollPane(contactTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addContactButton = new JButton("Add Contact");
        JButton updateContactButton = new JButton("Update Contact");
        JButton deleteContactButton = new JButton("Delete Contact");

        addContactButton.addActionListener(e -> addContact(contactModel));
        updateContactButton.addActionListener(e -> updateContact(contactTable, contactModel));
        deleteContactButton.addActionListener(e -> deleteContact(contactTable, contactModel));

        buttonPanel.add(addContactButton);
        buttonPanel.add(updateContactButton);
        buttonPanel.add(deleteContactButton);

        contactPanel.add(buttonPanel, BorderLayout.SOUTH);

        return contactPanel;
    }

    private static void addContact(DefaultTableModel model) {
        String name = JOptionPane.showInputDialog("Enter Contact Name:");
        String phone = JOptionPane.showInputDialog("Enter Phone:");
        String email = JOptionPane.showInputDialog("Enter Email:");
        String address = JOptionPane.showInputDialog("Enter Address:");
        String company = JOptionPane.showInputDialog("Enter Company:");
        model.addRow(new Object[]{name, phone, email, address, company});
    }

    private static void updateContact(JTable table, DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to update.");
            return;
        }
        String name = JOptionPane.showInputDialog("Enter Contact Name:", model.getValueAt(selectedRow, 0));
        String phone = JOptionPane.showInputDialog("Enter Phone:", model.getValueAt(selectedRow, 1));
        String email = JOptionPane.showInputDialog("Enter Email:", model.getValueAt(selectedRow, 2));
        String address = JOptionPane.showInputDialog("Enter Address:", model.getValueAt(selectedRow, 3));
        String company = JOptionPane.showInputDialog("Enter Company:", model.getValueAt(selectedRow, 4));

        model.setValueAt(name, selectedRow, 0);
        model.setValueAt(phone, selectedRow, 1);
        model.setValueAt(email, selectedRow, 2);
        model.setValueAt(address, selectedRow, 3);
        model.setValueAt(company, selectedRow, 4);
    }

    private static void deleteContact(JTable table, DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
            return;
        }
        model.removeRow(selectedRow);
    }

    private static JPanel createOpportunitiesPanel() {
        JPanel opportunitiesPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Opportunities");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        opportunitiesPanel.add(title, BorderLayout.NORTH);

        String[] columnNames = {"Opportunity Name", "Client", "Stage", "Value", "Owner"};
        DefaultTableModel opportunitiesModel = new DefaultTableModel(columnNames, 0);
        JTable opportunitiesTable = new JTable(opportunitiesModel);

        // Sample data
        opportunitiesModel.addRow(new Object[]{"New Website Project", "Tech Corp", "Proposal", "$5000", "Alice Johnson"});
        opportunitiesModel.addRow(new Object[]{"Marketing Campaign", "Global Solutions", "Negotiation", "$8000", "Bob Lee"});

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(opportunitiesModel);
        opportunitiesTable.setRowSorter(sorter);

        opportunitiesPanel.add(new JScrollPane(opportunitiesTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addOpportunityButton = new JButton("Add Opportunity");
        JButton updateOpportunityButton = new JButton("Update Opportunity");
        JButton deleteOpportunityButton = new JButton("Delete Opportunity");

        addOpportunityButton.addActionListener(e -> addOpportunity(opportunitiesModel));
        updateOpportunityButton.addActionListener(e -> updateOpportunity(opportunitiesTable, opportunitiesModel));
        deleteOpportunityButton.addActionListener(e -> deleteOpportunity(opportunitiesTable, opportunitiesModel));

        buttonPanel.add(addOpportunityButton);
        buttonPanel.add(updateOpportunityButton);
        buttonPanel.add(deleteOpportunityButton);

        opportunitiesPanel.add(buttonPanel, BorderLayout.SOUTH);

        return opportunitiesPanel;
    }

    private static void addOpportunity(DefaultTableModel model) {
        String name = JOptionPane.showInputDialog("Enter Opportunity Name:");
        String client = JOptionPane.showInputDialog("Enter Client:");
        String stage = JOptionPane.showInputDialog("Enter Stage:");
        String value = JOptionPane.showInputDialog("Enter Value:");
        String owner = JOptionPane.showInputDialog("Enter Owner:");
        model.addRow(new Object[]{name, client, stage, value, owner});
    }

    private static void updateOpportunity(JTable table, DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to update.");
            return;
        }
        String name = JOptionPane.showInputDialog("Enter Opportunity Name:", model.getValueAt(selectedRow, 0));
        String client = JOptionPane.showInputDialog("Enter Client:", model.getValueAt(selectedRow, 1));
        String stage = JOptionPane.showInputDialog("Enter Stage:", model.getValueAt(selectedRow, 2));
        String value = JOptionPane.showInputDialog("Enter Value:", model.getValueAt(selectedRow, 3));
        String owner = JOptionPane.showInputDialog("Enter Owner:", model.getValueAt(selectedRow, 4));

        model.setValueAt(name, selectedRow, 0);
        model.setValueAt(client, selectedRow, 1);
        model.setValueAt(stage, selectedRow, 2);
        model.setValueAt(value, selectedRow, 3);
        model.setValueAt(owner, selectedRow, 4);
    }

    private static void deleteOpportunity(JTable table, DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
            return;
        }
        model.removeRow(selectedRow);
    }

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

    private static void handleMenuItemClick(String item) {
        cardLayout.show(mainContentPanel, item);
    }

    private static void styleButton(JButton button) {
        button.setBackground(Color.GRAY);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
    }
}

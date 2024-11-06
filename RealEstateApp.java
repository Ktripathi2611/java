import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.Comparator;

public class RealEstateApp {

    private static CardLayout cardLayout;
    private static JPanel mainContentPanel;
    private static DefaultTableModel tableModel;
    private static JTable listingsTable;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Real Estate Property Listings");
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

        JLabel titleLabel = new JLabel("Real Estate Property Listings");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        navbarPanel.add(titleLabel, BorderLayout.WEST);

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

        String[] menuItems = {"Home", "Property Listings", "Add New Property", "Summary", "Contact"};
        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.addActionListener(e -> handleMenuItemClick(item));
            sidebarPanel.add(button);
            sidebarPanel.add(Box.createVerticalStrut(10));
        }

        return sidebarPanel;
    }

    private static JPanel createMainContentPanel() {
        cardLayout = new CardLayout();
        JPanel panel = new JPanel(cardLayout);

        // Home Panel
        JPanel homePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        JLabel homeTitle = new JLabel("Welcome to Real Estate Listings");
        homeTitle.setFont(new Font("Arial", Font.BOLD, 24));
        homePanel.add(homeTitle, gbc);
        panel.add(homePanel, "Home");

        // Property Listings Panel
        JPanel listingsPanel = createPropertyListingsPanel();
        panel.add(listingsPanel, "Property Listings");

        // Add New Property Panel
        JPanel addPropertyPanel = createAddNewPropertyPanel();
        panel.add(addPropertyPanel, "Add New Property");

        // Summary Panel
        JPanel summaryPanel = createSummaryPanel();
        panel.add(summaryPanel, "Summary");

        // Contact Panel
        JPanel contactPanel = createContactPanel();
        panel.add(contactPanel, "Contact");

        return panel;
    }

    private static JPanel createPropertyListingsPanel() {
        JPanel listingsPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Property Listings");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        listingsPanel.add(title, BorderLayout.NORTH);

        String[] columnNames = {"Property Name", "Location", "Price", "Type"};
        tableModel = new DefaultTableModel(columnNames, 0);
        listingsTable = new JTable(tableModel);
        listingsPanel.add(new JScrollPane(listingsTable), BorderLayout.CENTER);

        JPanel actionsPanel = new JPanel(new FlowLayout());
        JButton editButton = new JButton("Edit Property");
        JButton deleteButton = new JButton("Delete Property");
        JButton sortButtonAsc = new JButton("Sort by Price (Asc)");
        JButton sortButtonDesc = new JButton("Sort by Price (Desc)");

        actionsPanel.add(editButton);
        actionsPanel.add(deleteButton);
        actionsPanel.add(sortButtonAsc);
        actionsPanel.add(sortButtonDesc);
        listingsPanel.add(actionsPanel, BorderLayout.SOUTH);

        // Edit property
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = listingsTable.getSelectedRow();
                if (selectedRow != -1) {
                    String name = (String) tableModel.getValueAt(selectedRow, 0);
                    String location = (String) tableModel.getValueAt(selectedRow, 1);
                    String price = (String) tableModel.getValueAt(selectedRow, 2);
                    String type = (String) tableModel.getValueAt(selectedRow, 3);

                    JTextField nameField = new JTextField(name);
                    JTextField locationField = new JTextField(location);
                    JTextField priceField = new JTextField(price);
                    JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Apartment", "Villa", "Condo"});
                    typeComboBox.setSelectedItem(type);

                    JPanel editPanel = new JPanel(new GridLayout(5, 2, 10, 10));
                    editPanel.add(new JLabel("Property Name:"));
                    editPanel.add(nameField);
                    editPanel.add(new JLabel("Location:"));
                    editPanel.add(locationField);
                    editPanel.add(new JLabel("Price:"));
                    editPanel.add(priceField);
                    editPanel.add(new JLabel("Type:"));
                    editPanel.add(typeComboBox);

                    int result = JOptionPane.showConfirmDialog(null, editPanel, "Edit Property", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        tableModel.setValueAt(nameField.getText(), selectedRow, 0);
                        tableModel.setValueAt(locationField.getText(), selectedRow, 1);
                        tableModel.setValueAt(priceField.getText(), selectedRow, 2);
                        tableModel.setValueAt(typeComboBox.getSelectedItem(), selectedRow, 3);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a property to edit.");
                }
            }
        });

        // Delete property
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = listingsTable.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a property to delete.");
                }
            }
        });

        // Sort properties by price (ascending)
        sortButtonAsc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortProperties(true);
            }
        });

        // Sort properties by price (descending)
        sortButtonDesc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortProperties(false);
            }
        });

        // Sample data
        addSampleData();

        return listingsPanel;
    }

    private static void sortProperties(boolean ascending) {
        int rowCount = tableModel.getRowCount();
        Object[][] data = new Object[rowCount][4];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < 4; j++) {
                data[i][j] = tableModel.getValueAt(i, j);
            }
        }

        Arrays.sort(data, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                double price1 = Double.parseDouble((String) o1[2]);
                double price2 = Double.parseDouble((String) o2[2]);
                return ascending ? Double.compare(price1, price2) : Double.compare(price2, price1);
            }
        });

        // Clear the table and add the sorted data
        tableModel.setRowCount(0);
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }

    private static void addSampleData() {
        Object[][] sampleData = {
            {"Modern Apartment", "New York", "350000", "Apartment"},
            {"Luxury Villa", "California", "850000", "Villa"},
            {"Cozy Condo", "Florida", "250000", "Condo"},
            {"Beach House", "Hawaii", "500000", "Villa"},
            {"City Loft", "Chicago", "400000", "Apartment"}
        };

        for (Object[] row : sampleData) {
            tableModel.addRow(row);
        }
    }

    private static JPanel createAddNewPropertyPanel() {
        JPanel addPropertyPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Add New Property");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        addPropertyPanel.add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        JTextField nameField = new JTextField();
        JTextField locationField = new JTextField();
        JTextField priceField = new JTextField();
        JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"Apartment", "Villa", "Condo"});

        formPanel.add(new JLabel("Property Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Location:"));
        formPanel.add(locationField);
        formPanel.add(new JLabel("Price:"));
        formPanel.add(priceField);
        formPanel.add(new JLabel("Type:"));
        formPanel.add(typeComboBox);

        JButton addButton = new JButton("Add Property");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String location = locationField.getText();
                String price = priceField.getText();
                String type = (String) typeComboBox.getSelectedItem();

                if (name.isEmpty() || location.isEmpty() || price.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                } else {
                    tableModel.addRow(new Object[]{name, location, price, type});
                }
            }
        });

        addPropertyPanel.add(formPanel, BorderLayout.CENTER);
        addPropertyPanel.add(addButton, BorderLayout.SOUTH);

        return addPropertyPanel;
    }

    private static JPanel createSummaryPanel() {
        JPanel summaryPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Property Summary");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        summaryPanel.add(title, BorderLayout.NORTH);

        // Summary content
        JTextArea summaryContent = new JTextArea();
        summaryContent.setEditable(false);
        summaryContent.setText("Total Properties: " + tableModel.getRowCount() + "\nAverage Price: " + calculateAveragePrice());
        summaryPanel.add(summaryContent, BorderLayout.CENTER);

        return summaryPanel;
    }

    private static double calculateAveragePrice() {
        int rowCount = tableModel.getRowCount();
        if (rowCount == 0) return 0;

        double total = 0;
        for (int i = 0; i < rowCount; i++) {
            total += Double.parseDouble((String) tableModel.getValueAt(i, 2));
        }
        return total / rowCount;
    }

    private static JPanel createContactPanel() {
        JPanel contactPanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Contact Us");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        contactPanel.add(title, BorderLayout.NORTH);

        JTextArea contactInfo = new JTextArea();
        contactInfo.setText("For inquiries, please contact us at:\n\nEmail: info@realestate.com\nPhone: (123) 456-7890");
        contactInfo.setEditable(false);
        contactPanel.add(contactInfo, BorderLayout.CENTER);

        return contactPanel;
    }

    private static void handleMenuItemClick(String item) {
        cardLayout.show(mainContentPanel, item);
    }
}

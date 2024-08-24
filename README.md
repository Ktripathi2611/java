# CRM Application

This is a simple CRM (Customer Relationship Management) application built using Java Swing. The application features a main content area, a sidebar with various sections, and a top panel with navigation buttons.

## Features

- **Top Panel**: Contains "Back" and "Menu" buttons for navigation.
- **Sidebar Panel**: Contains buttons for different sections like Dashboard, Tasks, Calendar, etc.
- **Main Content Area**: Displays content based on the selected section from the sidebar.
- **Footer**: Displays a footer label.

## Components

### Main Components

- `JFrame`: The main window of the application.
- `JPanel`: Used for different sections and layouts.
- `CardLayout`: Manages the different panels in the main content area.
- `JButton`: Used for navigation and section buttons.
- `JLabel`: Used for displaying text in the header and footer.
- `JTextArea`: Used for displaying sample information in the content area.

### Panels

- **Top Panel**: Contains navigation buttons.
- **Sidebar Panel**: Contains buttons for different sections.
- **Main Panel**: Uses `CardLayout` to switch between different content panels.
- **Content Panel**: Displays the main content.

## How to Run

1. **Compile the Code**: Ensure you have Java installed. Compile the code using `javac CRMApplication.java`.
2. **Run the Application**: Run the compiled code using `java CRMApplication`.

## Code Overview

### Main Class

```java
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
}
```

### Top Panel

```java
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
```

### Sidebar Panel

```java
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
```

### Content Panel Header

```java
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
```

### Footer Label

```java
private static JLabel createFooterLabel() {
    JLabel footerLabel = new JLabel("This is the end");
    footerLabel.setOpaque(true);
    footerLabel.setBackground(new Color(70, 130, 180));
    footerLabel.setForeground(Color.WHITE);
    footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
    footerLabel.setFont(new Font("Arial", Font.BOLD, 24));
    return footerLabel;
}
```

### Display Sample Info

```java
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
        default:
            infoTextArea.setText(item + " Content Here");
            break;
    }

    infoPanel.add(new JScrollPane(infoTextArea), BorderLayout.CENTER);
    mainPanel.add(infoPanel, item);
    cardLayout.show(mainPanel, item);
}
```

### Navigation Methods

```java
private static void goBack() {
    cardLayout.show(mainPanel, "ContentPanel");
}

private static void showMenu() {
    cardLayout.show(mainPanel, "ContentPanel");
}
```

### Button Styling

```java
private static void styleButton(JButton button) {
    button.setBackground(new Color(70, 130, 180));
    button.setForeground(Color.WHITE);
    button.setFont(new Font("Arial", Font.BOLD, 14));
    button.setAlignmentX(Component.CENTER_ALIGNMENT);
}
```

## Conclusion

This CRM application provides a basic structure for a more complex system. You can expand it by adding more functionalities and improving the UI/UX.


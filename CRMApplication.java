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

        // Create the scrollable header
        JScrollPane headerScrollPane = createScrollableHeader();
        frame.add(headerScrollPane, BorderLayout.NORTH);

        // Create and set the content panel
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        frame.add(contentPanel, BorderLayout.CENTER);

        // Create and set the footer
        JLabel footerLabel = createFooterLabel();
        frame.add(footerLabel, BorderLayout.SOUTH);

        // Center the frame and display it
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JScrollPane createScrollableHeader() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridBagLayout());
        headerPanel.setBackground(new Color(245, 245, 245));

        JMenuBar menuBar = createMenuBar();
        headerPanel.add(menuBar);

        JScrollPane scrollPane = new JScrollPane(headerPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(null); // Remove border for better appearance

        return scrollPane;
    }

    private static JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(70, 130, 180));
        menuBar.setForeground(Color.WHITE);

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
            menu.addMouseListener(new MenuHoverListener(menu));

            JMenuItem menuItem = new JMenuItem(item);
            menuItem.addActionListener(e -> displaySampleInfo(item));
            menu.add(menuItem);
            menuBar.add(menu);
        }

        return menuBar;
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

    // Custom MouseListener for menu hover effects
    private static class MenuHoverListener extends MouseAdapter {
        private final JMenu menu;

        public MenuHoverListener(JMenu menu) {
            this.menu = menu;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            menu.setForeground(Color.YELLOW);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            menu.setForeground(Color.WHITE);
        }
    }
}

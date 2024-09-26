import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class HealthFitnessTracker {
    private static JPanel contentPanel;
    private static CardLayout cardLayout;
    private static JPanel mainPanel;
    private static JPanel sidebarPanel;
    private static String previousPanel = "Dashboard";

    private static final Color PRIMARY_DARK = new Color(34, 45, 50); 
    private static final Color SECONDARY_DARK = new Color(44, 62, 80); 
    private static final Color ACCENT_COLOR = new Color(39, 174, 96); 
    private static final Color SIDEBAR_COLOR = new Color(52, 73, 94); 
    private static final Color HIGHLIGHT_COLOR = new Color(46, 204, 113); 

    private static final String[] SECTIONS = {
        "Dashboard", "Activity Tracker", "Nutrition Tracker", 
        "Sleep Monitor", "Water Intake", "Progress", "Settings"
    };

    private static int stepsToday = 0;
    private static int caloriesBurned = 0;
    private static int caloriesConsumed = 0;
    private static int waterIntake = 0;
    private static int sleepHours = 0;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(HealthFitnessTracker::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Health and Fitness Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);

        sidebarPanel = createSidebarPanel();
        frame.add(sidebarPanel, BorderLayout.WEST);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(SECONDARY_DARK);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(SECONDARY_DARK);

        mainPanel.add(contentPanel, "ContentPanel");
        frame.add(mainPanel, BorderLayout.CENTER);

        JLabel footerLabel = createFooterLabel();
        frame.add(footerLabel, BorderLayout.SOUTH);

        frame.setVisible(true);
        displayDashboard();
    }

    private static JPanel createSidebarPanel() {
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(SIDEBAR_COLOR);

        for (String section : SECTIONS) {
            JButton button = new JButton(section);
            styleButton(button);
            button.setToolTipText(section);
            button.addActionListener(e -> handleSectionClick(section));
            sidebarPanel.add(button);
            sidebarPanel.add(Box.createVerticalStrut(10)); 
        }

        return sidebarPanel;
    }

    private static JLabel createFooterLabel() {
        JLabel footerLabel = new JLabel("Health & Fitness Tracker");
        footerLabel.setOpaque(true);
        footerLabel.setBackground(SECONDARY_DARK);
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        footerLabel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, ACCENT_COLOR));
        return footerLabel;
    }

    private static void handleSectionClick(String section) {
        switch (section) {
            case "Dashboard":
                displayDashboard();
                break;
            case "Activity Tracker":
                displayActivityTracker();
                break;
            case "Nutrition Tracker":
                displayNutritionTracker();
                break;
            case "Sleep Monitor":
                displaySleepMonitor();
                break;
            case "Water Intake":
                displayWaterIntake();
                break;
            case "Progress":
                displayProgress();
                break;
            case "Settings":
                displaySettings();
                break;
        }
    }

    private static void displayDashboard() {
        JPanel dashboardPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        dashboardPanel.setBackground(SECONDARY_DARK);

        dashboardPanel.add(createInfoCard("Steps Today", stepsToday + " steps"));
        dashboardPanel.add(createInfoCard("Calories Burned", caloriesBurned + " kcal"));
        dashboardPanel.add(createInfoCard("Calories Consumed", caloriesConsumed + " kcal"));
        dashboardPanel.add(createInfoCard("Water Intake", waterIntake + " cups"));
        dashboardPanel.add(createInfoCard("Sleep Hours", sleepHours + " hours"));

        mainPanel.add(dashboardPanel, "Dashboard");
        cardLayout.show(mainPanel, "Dashboard");
        previousPanel = "Dashboard";
    }

    private static JPanel createInfoCard(String title, String value) {
        JPanel cardPanel = new JPanel(new BorderLayout());
        cardPanel.setBackground(ACCENT_COLOR);
        cardPanel.setBorder(BorderFactory.createLineBorder(HIGHLIGHT_COLOR));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardPanel.add(titleLabel, BorderLayout.NORTH);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardPanel.add(valueLabel, BorderLayout.CENTER);

        return cardPanel;
    }

    private static void displayActivityTracker() {
        JPanel activityPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        activityPanel.setBackground(SECONDARY_DARK);

        JButton logStepsButton = new JButton("Log Steps");
        styleButton(logStepsButton);
        logStepsButton.addActionListener(e -> stepsToday += Integer.parseInt(JOptionPane.showInputDialog("Enter Steps:")));
        activityPanel.add(logStepsButton);

        JButton logCaloriesBurnedButton = new JButton("Log Calories Burned");
        styleButton(logCaloriesBurnedButton);
        logCaloriesBurnedButton.addActionListener(e -> caloriesBurned += Integer.parseInt(JOptionPane.showInputDialog("Enter Calories Burned:")));
        activityPanel.add(logCaloriesBurnedButton);

        mainPanel.add(activityPanel, "ActivityTracker");
        cardLayout.show(mainPanel, "ActivityTracker");
        previousPanel = "ActivityTracker";
    }

    private static void displayNutritionTracker() {
        JPanel nutritionPanel = new JPanel(new GridLayout(1, 1));
        nutritionPanel.setBackground(SECONDARY_DARK);

        JButton logCaloriesConsumedButton = new JButton("Log Calories Consumed");
        styleButton(logCaloriesConsumedButton);
        logCaloriesConsumedButton.addActionListener(e -> caloriesConsumed += Integer.parseInt(JOptionPane.showInputDialog("Enter Calories Consumed:")));
        nutritionPanel.add(logCaloriesConsumedButton);

        mainPanel.add(nutritionPanel, "NutritionTracker");
        cardLayout.show(mainPanel, "NutritionTracker");
        previousPanel = "NutritionTracker";
    }

    private static void displaySleepMonitor() {
        JPanel sleepPanel = new JPanel(new GridLayout(1, 1));
        sleepPanel.setBackground(SECONDARY_DARK);

        JButton logSleepButton = new JButton("Log Sleep Hours");
        styleButton(logSleepButton);
        logSleepButton.addActionListener(e -> sleepHours += Integer.parseInt(JOptionPane.showInputDialog("Enter Sleep Hours:")));
        sleepPanel.add(logSleepButton);

        mainPanel.add(sleepPanel, "SleepMonitor");
        cardLayout.show(mainPanel, "SleepMonitor");
        previousPanel = "SleepMonitor";
    }

    private static void displayWaterIntake() {
        JPanel waterPanel = new JPanel(new GridLayout(1, 1));
        waterPanel.setBackground(SECONDARY_DARK);

        JButton logWaterButton = new JButton("Log Water Intake");
        styleButton(logWaterButton);
        logWaterButton.addActionListener(e -> waterIntake += Integer.parseInt(JOptionPane.showInputDialog("Enter Water Intake (cups):")));
        waterPanel.add(logWaterButton);

        mainPanel.add(waterPanel, "WaterIntake");
        cardLayout.show(mainPanel, "WaterIntake");
        previousPanel = "WaterIntake";
    }

    private static void displayProgress() {
        JPanel progressPanel = new JPanel(new GridLayout(2, 1));
        progressPanel.setBackground(SECONDARY_DARK);

        progressPanel.add(createInfoCard("Steps Today", stepsToday + " steps"));
        progressPanel.add(createInfoCard("Calories Burned", caloriesBurned + " kcal"));

        mainPanel.add(progressPanel, "Progress");
        cardLayout.show(mainPanel, "Progress");
        previousPanel = "Progress";
    }

    private static void displaySettings() {
        JPanel settingsPanel = new JPanel(new BorderLayout());
        settingsPanel.setBackground(SECONDARY_DARK);

        JLabel settingsLabel = new JLabel("Settings");
        settingsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        settingsLabel.setForeground(Color.WHITE);
        settingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        settingsPanel.add(settingsLabel, BorderLayout.NORTH);

        mainPanel.add(settingsPanel, "Settings");
        cardLayout.show(mainPanel, "Settings");
        previousPanel = "Settings";
    }

    private static void styleButton(JButton button) {
        button.setBackground(ACCENT_COLOR);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(HIGHLIGHT_COLOR));
        button.setMaximumSize(new Dimension(200, 40));
    }
}

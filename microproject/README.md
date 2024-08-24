# CRM Application

## Overview
The CRM Application is a Java-based desktop application designed to manage customer relationships efficiently. It features a modern dark theme and a user-friendly interface, built using Swing components.

## Features
- **Dashboard**: Overview of total sales, new leads, open tickets, and upcoming meetings.
- **Sales Pipeline**: Details on total opportunities, new leads, and closed deals.
- **Customer Support Tickets**: Information on open and closed tickets, and average response time.
- **Notes**: Overview of total, recent, and archived notes.
- **Settings**: User, system, and notification settings.
- **Billing**: Summary of total invoices, pending payments, and completed payments.
- **Summary**: Consolidated view of all sections.

## Installation
1. Ensure you have Java Development Kit (JDK) installed.
2. Clone the repository.
3. Compile the Java files using `javac CRMApplication.java`.
4. Run the application using `java CRMApplication`.

## Usage
- **Navigation**: Use the sidebar to navigate between different sections.
- **Back and Home Buttons**: Use these buttons to return to the previous or home page.
- **Tooltips**: Hover over buttons for tooltips that provide additional information.

## Code Structure
- **CRMApplication.java**: Main class that initializes and displays the GUI.
- **createAndShowGUI()**: Sets up the main frame and layout.
- **createSidebarPanel()**: Creates the sidebar with navigation buttons.
- **displayDefaultPage()**: Displays the home page.
- **displaySampleInfo(String item)**: Displays information for the selected section.
- **displaySummaryPage()**: Displays a summary of all sections.
- **styleButton(JButton button)**: Styles the buttons for a consistent look.

## Colors
- **PRIMARY_DARK**: Dark Blue-Gray
- **SECONDARY_DARK**: Teal Blue
- **ACCENT_COLOR**: Muted Olive Green
- **SIDEBAR_COLOR**: Light Olive Green
- **HIGHLIGHT_COLOR**: Muted Olive Green


## Contact
For any questions or feedback, please contact [thakurstuffsbykushal@gmail.com].

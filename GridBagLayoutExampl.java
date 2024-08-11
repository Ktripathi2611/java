import java.awt.*;
import javax.swing.*;

public class GridBagLayoutExampl {
    public static void main(String[] args) {
        JFrame frame = new JFrame("GridBagLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        // Example components
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");

        // Adding components with GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(button1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(button2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(button3, gbc);

        frame.pack();
        frame.setVisible(true);
    }
}

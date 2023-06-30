import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaxElementGUI extends JFrame {
    private JTextField arrayField;
    private JTextArea resultArea;

    public MaxElementGUI() {
        setTitle("Max Element");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Create input field and button
        arrayField = new JTextField(20);
        JButton maxButton = new JButton("Find Maximum");
        maxButton.addActionListener(new MaxButtonListener());

        // Create result area
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Create panel and add components
        JPanel panel = new JPanel();
        panel.add(new JLabel("Array: "));
        panel.add(arrayField);
        panel.add(maxButton);
        panel.add(scrollPane);

        // Add panel to the frame
        add(panel);
    }

    private class MaxButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the array from the input field
            String arrayText = arrayField.getText();

            // Parse the array string into an integer array
            String[] arrayValues = arrayText.split(",");
            int[] array = new int[arrayValues.length];
            for (int i = 0; i < arrayValues.length; i++) {
                array[i] = Integer.parseInt(arrayValues[i].trim());
            }

            // Find the maximum element
            int max = maxElement(array);

            // Display the maximum element
            resultArea.setText("Maximum Element: " + max);
        }
    }

    private int maxElement(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MaxElementGUI gui = new MaxElementGUI();
                gui.setVisible(true);
            }
        });
    }
}

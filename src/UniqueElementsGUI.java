import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UniqueElementsGUI extends JFrame {
    private JTextField arrayField;
    private JTextArea resultArea;

    public UniqueElementsGUI() {
        setTitle("Unique Elements");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Create input field and button
        arrayField = new JTextField(20);
        JButton checkButton = new JButton("Check Unique");
        checkButton.addActionListener(new CheckButtonListener());

        // Create result area
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Create panel and add components
        JPanel panel = new JPanel();
        panel.add(new JLabel("Array: "));
        panel.add(arrayField);
        panel.add(checkButton);
        panel.add(scrollPane);

        // Add panel to the frame
        add(panel);
    }

    private class CheckButtonListener implements ActionListener {
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

            // Check if the elements are unique
            boolean unique = areElementsUnique(array);

            // Display the result
            resultArea.setText("Elements are Unique: " + unique);
        }
    }

    private boolean areElementsUnique(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UniqueElementsGUI gui = new UniqueElementsGUI();
                gui.setVisible(true);
            }
        });
    }
}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BubbleSortGUI extends JFrame {
    private JTextField arrayField;
    private JTextArea resultArea;

    public BubbleSortGUI() {
        setTitle("Bubble Sort");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Create input field and button
        arrayField = new JTextField(20);
        JButton sortButton = new JButton("Sort");
        sortButton.addActionListener(new SortButtonListener());

        // Create result area
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Create panel and add components
        JPanel panel = new JPanel();
        panel.add(new JLabel("Array: "));
        panel.add(arrayField);
        panel.add(sortButton);
        panel.add(scrollPane);

        // Add panel to the frame
        add(panel);
    }

    private class SortButtonListener implements ActionListener {
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

            // Sort the array using bubble sort
            bubbleSort(array);

            // Display the sorted array
            displayArray(array);
        }
    }

    private void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    private void displayArray(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]).append("\n");
        }
        resultArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BubbleSortGUI gui = new BubbleSortGUI();
                gui.setVisible(true);
            }
        });
    }
}

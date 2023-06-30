import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SequentialSearchGUI extends JFrame {
    private JTextField arrayField;
    private JTextField searchKeyField;
    private JTextArea resultArea;

    public SequentialSearchGUI() {
        setTitle("Sequential Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Create input fields and button
        arrayField = new JTextField(20);
        searchKeyField = new JTextField(10);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchButtonListener());

        // Create result area
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Create panel and add components
        JPanel panel = new JPanel();
        panel.add(new JLabel("Array: "));
        panel.add(arrayField);
        panel.add(new JLabel("Search Key: "));
        panel.add(searchKeyField);
        panel.add(searchButton);
        panel.add(scrollPane);

        // Add panel to the frame
        add(panel);
    }

    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the array and search key from the input fields
            String arrayText = arrayField.getText();
            int searchKey = Integer.parseInt(searchKeyField.getText());

            // Parse the array string into an integer array
            String[] arrayValues = arrayText.split(",");
            int[] array = new int[arrayValues.length];
            for (int i = 0; i < arrayValues.length; i++) {
                array[i] = Integer.parseInt(arrayValues[i].trim());
            }

            // Perform sequential search
            int index = sequentialSearch(array, searchKey);

            // Display the search result
            if (index != -1) {
                resultArea.setText("Element found at index: " + index);
            } else {
                resultArea.setText("Element not found");
            }
        }
    }

    private int sequentialSearch(int[] array, int searchKey) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == searchKey) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SequentialSearchGUI gui = new SequentialSearchGUI();
                gui.setVisible(true);
            }
        });
    }
}

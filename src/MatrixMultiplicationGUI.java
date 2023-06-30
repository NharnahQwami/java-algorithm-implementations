import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatrixMultiplicationGUI extends JFrame {
    private JTextField matrixAField;
    private JTextField matrixBField;
    private JTextArea resultArea;

    public MatrixMultiplicationGUI() {
        setTitle("Matrix Multiplication");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Create input fields and button
        matrixAField = new JTextField(20);
        matrixBField = new JTextField(20);
        JButton multiplyButton = new JButton("Multiply");
        multiplyButton.addActionListener(new MultiplyButtonListener());

        // Create result area
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Create panel and add components
        JPanel panel = new JPanel();
        panel.add(new JLabel("Matrix A: "));
        panel.add(matrixAField);
        panel.add(new JLabel("Matrix B: "));
        panel.add(matrixBField);
        panel.add(multiplyButton);
        panel.add(scrollPane);

        // Add panel to the frame
        add(panel);
    }

    private class MultiplyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the matrices from the input fields
            String matrixAText = matrixAField.getText();
            String matrixBText = matrixBField.getText();

            // Parse the matrices strings into integer arrays
            int[][] matrixA = parseMatrix(matrixAText);
            int[][] matrixB = parseMatrix(matrixBText);

            // Check if the matrices are valid for multiplication
            if (matrixA == null || matrixB == null || matrixA[0].length != matrixB.length) {
                resultArea.setText("Invalid matrices for multiplication");
                return;
            }

            // Perform matrix multiplication
            int[][] resultMatrix = multiplyMatrices(matrixA, matrixB);

            // Display the resulting matrix
            displayMatrix(resultMatrix);
        }
    }

    private int[][] parseMatrix(String matrixText) {
        String[] rows = matrixText.split(";");
        int rowCount = rows.length;
        int colCount = rows[0].split(",").length;

        int[][] matrix = new int[rowCount][colCount];
        try {
            for (int i = 0; i < rowCount; i++) {
                String[] elements = rows[i].split(",");
                for (int j = 0; j < colCount; j++) {
                    matrix[i][j] = Integer.parseInt(elements[j].trim());
                }
            }
            return matrix;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    private int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) {
        int rowCount = matrixA.length;
        int colCount = matrixB[0].length;
        int commonDim = matrixB.length;

        int[][] resultMatrix = new int[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                for (int k = 0; k < commonDim; k++) {
                    resultMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return resultMatrix;
    }

    private void displayMatrix(int[][] matrix) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                sb.append(matrix[i][j]).append("\t");
            }
            sb.append("\n");
        }

        resultArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MatrixMultiplicationGUI gui = new MatrixMultiplicationGUI();
                gui.setVisible(true);
            }
        });
    }
}

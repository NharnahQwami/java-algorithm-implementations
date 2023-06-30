import java.util.Arrays;
import java.util.Scanner;

public class SearchSortProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Cyber Dessy Search and Sort Program!");

        // Get the list of items from the user
        System.out.print("Enter the list of items (comma-separated): ");
        String input = scanner.nextLine();
        String[] items = input.split(",");
        int[] array = new int[items.length];
        for (int i = 0; i < items.length; i++) {
            array[i] = Integer.parseInt(items[i].trim());
        }

        // Choose the operation (searching or sorting)
        System.out.println("\nChoose an operation:");
        System.out.println("1. Sequential Search");
        System.out.println("2. Binary Search");
        System.out.println("3. Bubble Sort");
        System.out.println("4. Selection Sort");
        System.out.print("Enter the operation number: ");
        int operation = scanner.nextInt();

        // Perform the selected operation and measure the running time
        long startTime = System.nanoTime();
        switch (operation) {
            case 1:
                System.out.print("Enter the search value: ");
                int searchValue = scanner.nextInt();
                int index = sequentialSearch(array, searchValue);
                if (index != -1) {
                    System.out.println("The search value is found at index " + index);
                } else {
                    System.out.println("The search value is not found in the list.");
                }
                break;
            case 2:
                Arrays.sort(array);
                System.out.print("Enter the search value: ");
                searchValue = scanner.nextInt();
                index = binarySearch(array, searchValue);
                if (index != -1) {
                    System.out.println("The search value is found at index " + index);
                } else {
                    System.out.println("The search value is not found in the list.");
                }
                break;
            case 3:
                bubbleSort(array);
                System.out.println("The sorted list: " + Arrays.toString(array));
                break;
            case 4:
                selectionSort(array);
                System.out.println("The sorted list: " + Arrays.toString(array));
                break;
            default:
                System.out.println("Invalid operation number!");
        }
        long endTime = System.nanoTime();
        long runningTime = endTime - startTime;
        System.out.println("Running time: " + runningTime + " nanoseconds");

        // Determine the time complexity of the chosen operation
        String timeComplexity;
        switch (operation) {
            case 1:
                timeComplexity = "O(n)";
                break;
            case 2:
                timeComplexity = "O(log n)";
                break;
            case 3:
            case 4:
                timeComplexity = "O(n^2)";
                break;
            default:
                timeComplexity = "Unknown";
        }
        System.out.println("Time complexity: " + timeComplexity);

        scanner.close();
    }

    private static int sequentialSearch(int[] array, int key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return i;
            }
        }
        return -1;
    }

    private static int binarySearch(int[] array, int key) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (array[mid] == key) {
                return mid;
            } else if (array[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    private static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j + 1] < array[j]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    private static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }
}

package dome_3;

import java.util.Scanner;
import java.util.Random;
import java.util.List;

public class Main {
	
    private static BTree<String, IndexNode> bTree;
    private static Index index;
    private static TextFileProcessor fileProcessor;

    public static void main(String[] args) {
        // Initialize the B+ Tree and Index
        bTree = new BTree<>();
        index = new Index();

        // Initialize the TextFileProcessor
        fileProcessor = new TextFileProcessor(bTree, index);

        // Process a text file (replace "file.txt" with your actual file name)
        fileProcessor.processFile("1.txt");

        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Ask the user for a word to search for
        while (true) {
            // Print a prompt
        	System.out.println("Available commands:");
            System.out.println("search <word> - Search for a word in the index");
            System.out.println("performSearches - Test for 100 words");
            System.out.println("quit - Exit the program");
            System.out.println("All words in the index:");
            for (String word : index.getAllWords()) {
                System.out.println(word);
            }
            // Read a line of input from the user
            String line = scanner.nextLine();

            // Split the line into words
            String[] words = line.split("\\s+");

            // The first word is the command
            String command = words[0];

            // Process the command
            if (command.equalsIgnoreCase("search")) {
                // The rest of the line is the word to search for
                String word = line.substring(command.length()).trim();
                search(word);
            } else if (command.equalsIgnoreCase("performSearches")) {
                performSearches();
            } else if (command.equalsIgnoreCase("quit")) {
                // Exit the program
                break;
            } else {
                // Unknown command
                System.out.println("Unknown command: " + command);
            }
        }
        scanner.close();

        }

    public static void performSearches() {
        List<String> words = index.getAllWords();

    	 if (words.isEmpty()) {
    	        System.out.println("No words to search for.");
    	        return;
    	    }
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            String word = words.get(random.nextInt(words.size()));
            bTree.search(word);
            System.out.println("Search " + (i+1) + ": " + word);
            System.out.println("Nodes accessed: " + bTree.getNodeAccessCount());
            System.out.println("Comparisons made: " + bTree.getComparisonCount());
        }
    }

    public static void search(String word) {
        IndexNode indexNode = bTree.search(word);
        if (indexNode == null) {
            System.out.println("The word '" + word + "' was not found.");
        } else {
            System.out.println("The word '" + word + "' was found in the following files:");
            for (int i = 0; i < indexNode.fileNames.size(); i++) {
                System.out.println("File: " + indexNode.fileNames.get(i));
                System.out.println("Positions: " + indexNode.positions.get(i));
            }
        }
    }
}

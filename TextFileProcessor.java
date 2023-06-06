package dome_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class TextFileProcessor {
    private BTree<String, IndexNode> bTree;
    private Index index;

    public TextFileProcessor(BTree<String, IndexNode> bTree, Index index) {
        this.bTree = bTree;
        this.index = index;
    }
    public void processFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            int position = 0;

            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase();
                position++;

                // Print the word
                System.out.println("Word: " + word);

                // Check if the word is already in the B+ Tree
                IndexNode indexNode = bTree.search(word);
                if (indexNode == null) {
                    // The word is not in the B+ Tree, so create a new IndexNode
                    indexNode = new IndexNode(word, fileName, position);
                    bTree.insert(word, indexNode);
                } else {
                    // Add the file name and position to the IndexNode
                    indexNode.addPosition(fileName, position);
                }

                // Add the word to the index
                index.addWord(word);

                // Print the words in the index
                System.out.println("Words in the index: " + index.getAllWords());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
    }

//    public void processFile(String fileName) {
//        try {
//            File file = new File(fileName);
//            Scanner scanner = new Scanner(file);
//            int position = 0;
//
//            while (scanner.hasNext()) {
//                String word = scanner.next().toLowerCase();
//                position++;
//
//                // Check if the word is already in the B+ Tree
//                IndexNode indexNode = bTree.search(word);
//                if (indexNode == null) {
//                    // The word is not in the B+ Tree, so create a new IndexNode
//                    indexNode = new IndexNode(word, fileName, position);
//                    bTree.insert(word, indexNode);
//                } else {
//                    // Add the file name and position to the IndexNode
//                    indexNode.addPosition(fileName, position);
//                }
//
//                // Add the word to the index
//                index.addWord(word);
//            }
//
//            scanner.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found: " + fileName);
//        }
//    }

    

}


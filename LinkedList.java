package dome_3;

import java.util.ArrayList;
import java.util.List;



public class LinkedList {
    IndexNode head;

    public LinkedList() {
        this.head = null;
    }

    public void insert(String word, String fileName, int position) {
        if (head == null) {
            head = new IndexNode(word, fileName, position);
        } else {
            IndexNode currentNode = head;
            while (currentNode != null) {
                if (currentNode.word.equals(word)) {
                    currentNode.addPosition(fileName, position);
                    return;
                }
                if (currentNode.next == null) break;
                currentNode = currentNode.next;
            }
            currentNode.next = new IndexNode(word, fileName, position);
        }
    }

    public IndexNode search(String word) {
        IndexNode currentNode = head;
        while (currentNode != null) {
            if (currentNode.word.equals(word)) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;
    }
}

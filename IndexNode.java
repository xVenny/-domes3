package dome_3;

import java.util.ArrayList;
import java.util.List;

public class IndexNode {
    String word;
    List<String> fileNames;
    List<List<Integer>> positions;

    IndexNode next;

    // Default constructor
    public IndexNode() {
        this.word = null;
        this.fileNames = new ArrayList<>();
        this.positions = new ArrayList<>();
        this.next = null;
    }

    public IndexNode(String word, String fileName, int position) {
        this.word = word;
        this.fileNames = new ArrayList<>();
        this.fileNames.add(fileName);
        this.positions = new ArrayList<>();
        List<Integer> initialPositionList = new ArrayList<>();
        initialPositionList.add(position);
        this.positions.add(initialPositionList);
        this.next = null;
    }
    

    public void addPosition(String fileName, int position) {
        int index = fileNames.indexOf(fileName);
        if (index != -1) {
            positions.get(index).add(position);
        } else {
            fileNames.add(fileName);
            List<Integer> newPositionList = new ArrayList<>();
            newPositionList.add(position);
            positions.add(newPositionList);
        }
    }
}

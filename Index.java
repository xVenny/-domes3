package dome_3;
import java.util.List;
import java.util.ArrayList;

public class Index {
    LinkedList indexList;
    private List<String> words;

    public Index() {
        this.indexList = new LinkedList();
        this.words = new ArrayList<>();

    }

    public void insert(String word, String fileName, int position) {
        this.indexList.insert(word, fileName, position);
    }

    public IndexNode search(String word) {
        return this.indexList.search(word);
    }
    public void addWord(String word) {
        if (words == null) {
            words = new ArrayList<>();
        }
        if (!words.contains(word)) {
            words.add(word);
        }
            }

    public List<String> getAllWords() {
        List<String> words = new ArrayList<>();
        IndexNode currentNode = indexList.head;
        while (currentNode != null) {
            words.add(currentNode.word);
            currentNode = currentNode.next;
        }
        return words;
    }

}

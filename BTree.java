package dome_3;

/**
 * A B+ tree
 * Since the structures and behaviors between internal node and external node are different, 
 * so there are two different classes for each kind of node.
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */
public class BTree<TKey extends Comparable<TKey>, TValue> {
	private BTreeNode<TKey> root;
	private int nodeAccessCount;
	private int comparisonCount;

	public BTree() {
		this.root = new BTreeLeafNode<TKey, TValue>();
	}
	public int getNodeAccessCount() {
	    return nodeAccessCount;
	}

	public int getComparisonCount() {
	    return comparisonCount;
	}

	/**
	 * Insert a new key and its associated value into the B+ tree.
	 */
	public void insert(TKey key, TValue value) {
		BTreeLeafNode<TKey, TValue> leaf = this.findLeafNodeShouldContainKey(key);
		leaf.insertKey(key, value);
		
		if (leaf.isOverflow()) {
			BTreeNode<TKey> n = leaf.dealOverflow();
			if (n != null)
				this.root = n; 
		}
	}
	
	/**
	 * Search a key value on the tree and return its associated value.
	 */
	public TValue search(TKey key) {
	    nodeAccessCount = 0;
	    comparisonCount = 0;
	    BTreeLeafNode<TKey, TValue> leaf = this.findLeafNodeShouldContainKey(key);
	    int index = leaf.search(key);
	    comparisonCount += leaf.getComparisonCount();
	    return (index == -1) ? null : leaf.getValue(index);
	}

	/**
	 * Delete a key and its associated value from the tree.
	 */
	public void delete(TKey key) {
		BTreeLeafNode<TKey, TValue> leaf = this.findLeafNodeShouldContainKey(key);
		
		if (leaf.delete(key) && leaf.isUnderflow()) {
			BTreeNode<TKey> n = leaf.dealUnderflow();
			if (n != null)
				this.root = n; 
		}
	}
	
	/**
	 * Search the leaf node which should contain the specified key
	 */
	@SuppressWarnings("unchecked")
	private BTreeLeafNode<TKey, TValue> findLeafNodeShouldContainKey(TKey key) {
	    BTreeNode<TKey> node = this.root;
	    nodeAccessCount++;
	    while (node.getNodeType() == TreeNodeType.InnerNode) {
	        node = ((BTreeInnerNode<TKey>)node).getChild( node.search(key) );
	        nodeAccessCount++;
	    }
	    return (BTreeLeafNode<TKey, TValue>)node;
	}

}

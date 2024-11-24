package datafinal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A Binary Search Tree Implementation with Item objects
 * 
 * This implementation doesn't allow duplicate values
 * 
 * @author Matthew Washburn
 * @version 1
 * 
 */

public class InDorm {
    /**
     * Binary Tree Node class used to hold Item data and links to left and right children
     */
	public static class BTNode {
        private Item data;
        private BTNode left, right;

        BTNode(Item data) {
            this.data = data;
            this.left = this.right = null;
        }
        
        public BTNode getRight() {
        	return right;
        }
        
        public BTNode getLeft() {
        	return left;
        }
        
        public Item getData() {
        	return data;
        }
 
    }
    
    //--------------Attributes--------------
    private BTNode root;
    
    //--------------Constructor--------------
    /**
     * Constructs an empty BST
     */
    public InDorm() {
        root = null;
    }
    
    //--------------Getter--------------
    public BTNode getRoot() {
    	return root;
    }

    //--------------Methods--------------
    public boolean isEmpty() {
    	return root == null;
    }
    /**
     * Inserts an Item into the BST
     * @param data The Item to be inserted
     */
    public void insert(Item data) {
        root = insert(root, data);
    }

    /**
     * Private recursive method to insert an Item into the BST
     * @param node The current node in the BST
     * @param data The Item to be inserted
     * @return The updated node after insertion
     */
    private BTNode insert(BTNode node, Item data) {
        if (node == null) {
            return new BTNode(data);
        }

        if (data.compareTo(node.data) < 0) {
            node.left = insert(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insert(node.right, data);
        }

        return node;
    }

    /**
     * Deletes an Item from the BST
     * @param data The Item to be deleted
     */
    public void delete(Item data) {
        root = delete(root, data);
    }

    /**
     * Private recursive method to delete an Item from the BST
     * @param root The current root node in the BST
     * @param data The Item to be deleted
     * @return The updated node after deletion
     */
    public BTNode delete(BTNode root, Item data) {
        if (root == null) {
            return root;
        }

        if (data.compareTo(root.data) < 0) {
            root.left = delete(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = delete(root.right, data);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.data = minValue(root.right);
            root.right = delete(root.right, root.data);
        }

        return root;
    }

    /**
     * Deletes an Item of a certain size from the BST
     * @param size The size of the item to be deleted
     */
    public void deleteSize(Double size) {
    	root = deleteSize(root, size);
    }
    
    /**
     * Private recursive method to delete an Item of a certain size from the BST
     * @param root The current root node in the BST
     * @param size The size of the item to be deleted
     * @return The updated node after deletion
     */
    private BTNode deleteSize(BTNode root, Double size) {
    	if (root == null) {
            return root;
        }

        if (this.root.data.getSize() < root.data.getSize()) {
            root.left = deleteSize(root.left, size);
        } else if (this.root.data.getSize() > root.data.getSize()) {
            root.right = deleteSize(root.right, size);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.data = minValue(root.right);
            root.right = deleteSize(root.right, root.data.getSize());
        }

        return root;
    }
    
    /**
     * Finds the minimum value in the BST
     * @param root The root node of the subtree
     * @return The minimum value Item
     */
    private Item minValue(BTNode root) {
        Item minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    /**
     * Public facing interface for in-order traversal printing
     * @return In-order traversal as a string
     */
    public String printInOrder() {
        return printInOrder(root);
    }

    /**
     * Recursively prints the tree in in-order traversal order
     * @param root The root of this sub-tree
     * @return In-order traversal as a string
     */
    private String printInOrder(BTNode root) {
        if (root == null) {
            return "";
        }
        return printInOrder(root.left) + root.data + " " + printInOrder(root.right);
    }

    /**
     * Public facing interface for post-order traversal printing
     * @return Post-order traversal as a string
     */
    public String printPostOrder() {
        return printPostOrder(root);
    }

    /**
     * Recursively prints the tree in post-order traversal order
     * @param root The root of this sub-tree
     * @return Post-order traversal as a string
     */
    private String printPostOrder(BTNode root) {
        if (root == null) {
            return "";
        }
        return printPostOrder(root.left) + printPostOrder(root.right) + root.data + " ";
    }

    /**
     * Public facing interface for pre-order traversal printing
     * @return Pre-order traversal as a string
     */
    public String printPreOrder() {
        return printPreOrder(root);
    }

    /**
     * Recursively prints the tree in pre-order traversal order
     * @param root The root of this sub-tree
     * @return Pre-order traversal as a string
     */
    private String printPreOrder(BTNode root) {
        if (root == null) {
            return "";
        }
        return root.data + " " + printPreOrder(root.left) + printPreOrder(root.right);
    }

    /**
     * Public facing interface for level-order traversal printing
     * @return Level-order traversal as a string
     */
    public String printLevelOrder() {
        return printLevelOrder(root);
    }

    /**
     * Level-order traversal printing
     * @param root The root of this tree
     * @return Level-order traversal as a string
     */
    private String printLevelOrder(BTNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        Queue<BTNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BTNode node = queue.poll();
            result.append(node.data).append(" ");

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }

        return result.toString();
    }
}//InDorm

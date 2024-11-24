package machineLearning;

import java.util.Scanner;
/**
 * @author Matthew Washburn
 * @version 1
 */
public class machineLearning {

	private TreeNode root;

	private class TreeNode {
		private TreeNode left;
		private TreeNode right;
		private int data; // Generic Type

		public TreeNode(int data) {
			this.data = data;
		}
	}

	public void createBinaryTree() {
		TreeNode first = new TreeNode(1);
		TreeNode second = new TreeNode(2);
		TreeNode third = new TreeNode(3);
		TreeNode fourth = new TreeNode(4);
		TreeNode fifth = new TreeNode(5);
		root = first; // root --> first
		first.left = second;
		first.right = third; // second <---- first ----> third
		second.left = fourth;
		second.right = fifth; // fourth <---- second ----> fifth
		}

	}


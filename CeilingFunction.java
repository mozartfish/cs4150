package cs4150;

import java.util.ArrayList;
import java.util.Scanner;

class BST {
    Node root; // the root of the binary search tree
    Node left;
    Node right;

    class Node {
        //the internal data for the node
        int data;

        //left child node
        Node left;

        //right child node
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public BST() {
        root = null;
        left = null;
        right = null;
    }

    //insert an element
    public void insert(int n) {
        root = insertNode(root, n);
    }

    public Node insertNode(Node root, int n) {
        if (root == null) {
            root = new Node(n);
        }
        if (n < root.data) {
            root.left = insertNode(root.left, n);
        }
        if (n > root.data) {
            root.right = insertNode(root.right, n);
        }
        return root;
    }
}


public class CeilingFunction {
    public static void main(String[] args) {
        ArrayList<BST> listOfTrees = new ArrayList<BST>();
        ArrayList<BST> uniqueStructure = new ArrayList<BST>();
        int g = 0;
        boolean isUnique = false;
        //create a scanner to read in the input
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt(); // the number of binary trees we are analyzing
        int k = scn.nextInt(); // the number of nodes

        scn.nextLine();
        while (scn.hasNextLine()) {
            g = 0;
            BST tree = new BST();
            String p = scn.nextLine();
            String[] numbers = p.split(" ");
            while (g < numbers.length)
            {
                tree.insert(Integer.parseInt(numbers[g]));
                g++;
            }
            listOfTrees.add(tree);
        }

        for (BST tree: listOfTrees)
        {
            if (uniqueStructure.size() == 0)
            {
                uniqueStructure.add(tree);
            }
            else
            {
                for (BST t: uniqueStructure)
                {
                    if (!AreEqualTrees(tree, t))
                    {
                        isUnique =  true;
                    }
                    else
                    {
                        isUnique = false;
                        break;
                    }
                }
                if (isUnique)
                {
                    uniqueStructure.add(tree);
                }
            }
        }
        System.out.println(uniqueStructure.size());
    }
    public static Boolean AreEqualTrees(BST Tree1, BST Tree2) {
        return equalNumberOfNodes(Tree1.root, Tree2.root);
    }

    public static Boolean equalNumberOfNodes(BST.Node n1, BST.Node n2) {
        boolean lhs = false;
        boolean rhs = false;

        if (n1.left == null && n2.left == null) {
            lhs = true;
        } else if (n1.left != null && n2.left != null) {
            lhs = equalNumberOfNodes(n1.left, n2.left);
        }

        if (n1.right == null && n2.right == null) {
            rhs = true;
        } else if (n1.right != null && n2.right != null) {
            rhs = equalNumberOfNodes(n1.right, n2.right);
        }

        return lhs && rhs;
    }
}

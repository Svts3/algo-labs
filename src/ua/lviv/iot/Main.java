package ua.lviv.iot;

import ua.lviv.iot.tree.Color;
import ua.lviv.iot.tree.RedBlackTree;

public class Main {

    public static void main(String[] args) {
        RedBlackTree blackTree = new RedBlackTree();
        blackTree.insert(12, Color.BLACK);
        blackTree.insert(3, Color.RED);
        blackTree.insert(1, Color.BLACK);
        blackTree.insert(5, Color.BLACK);
        blackTree.insert(6, Color.RED);
        blackTree.insert(65, Color.BLACK);
        blackTree.insert(24, Color.RED);
        blackTree.insert(75, Color.RED);
        
        blackTree.printTree(blackTree.root);
        
        blackTree.deleteNode(blackTree.root, 6);
        blackTree.deleteNode(blackTree.root, 65);
        blackTree.deleteNode(blackTree.root, 24);
        
        System.out.println("\n");
        
        blackTree.printTree(blackTree.root);
    }

}

package PacmanAI.interfaces;

import PacmanAI.TreeNode;

public interface TreePrint {
    void printTree(TreeNode node, int height);
    String treePrint(TreeNode node, int height, String res);
}

package com.example;

/**
 * This class represents BDD Tree Node with:
 * Boolean function
 * Letter what will be substituted
 * Order of variables - "ABCD"
 * Left and Right pointers to new Nodes with smaller function
 */
public class TreeNode {
    private String bFunction;
    private String letter;
    private TreeNode right;
    private TreeNode left;
    private String order;

    TreeNode(String bFunc, String letter, String order) {
        this.letter = letter;
        this.bFunction = bFunc;
        this.right = null;
        this.left = null;
        this.order = order;
    }

    /**
     * @return the bFunction
     */
    public String getbFunction() {
        return bFunction;
    }

    /**
     * @param bFunction the bFunction to set
     */
    public void setbFunction(String bFunction) {
        this.bFunction = bFunction;
    }

    /**
     * @return the letter
     */
    public String getLetter() {
        return letter;
    }

    /**
     * @param letter the letter to set
     */
    public void setLetter(String letter) {
        this.letter = letter;
    }

    /**
     * @return the right
     */
    public TreeNode getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(TreeNode right) {
        this.right = right;
    }

    /**
     * @return the left
     */
    public TreeNode getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(TreeNode left) {
        this.left = left;
    }

    /**
     * @return the order
     */
    public String getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(String order) {
        this.order = order;
    }
}
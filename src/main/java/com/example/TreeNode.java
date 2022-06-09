package com.example;

/**
 * This class represents BDD Tree Node with:
 * Boolean function
 * Letter what will be substituted
 * Order of variables - "ABCD"
 * Left and Right pointers to new Nodes with smaller function
 *
 * @author MS
 * @version $Id: $Id
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
     * <p>Getter for the field <code>bFunction</code>.</p>
     *
     * @return the bFunction
     */
    public String getbFunction() {
        return bFunction;
    }

    /**
     * <p>Setter for the field <code>bFunction</code>.</p>
     *
     * @param bFunction the bFunction to set
     */
    public void setbFunction(String bFunction) {
        this.bFunction = bFunction;
    }

    /**
     * <p>Getter for the field <code>letter</code>.</p>
     *
     * @return the letter
     */
    public String getLetter() {
        return letter;
    }

    /**
     * <p>Setter for the field <code>letter</code>.</p>
     *
     * @param letter the letter to set
     */
    public void setLetter(String letter) {
        this.letter = letter;
    }

    /**
     * <p>Getter for the field <code>right</code>.</p>
     *
     * @return the right
     */
    public TreeNode getRight() {
        return right;
    }

    /**
     * <p>Setter for the field <code>right</code>.</p>
     *
     * @param right the right to set
     */
    public void setRight(TreeNode right) {
        this.right = right;
    }

    /**
     * <p>Getter for the field <code>left</code>.</p>
     *
     * @return the left
     */
    public TreeNode getLeft() {
        return left;
    }

    /**
     * <p>Setter for the field <code>left</code>.</p>
     *
     * @param left the left to set
     */
    public void setLeft(TreeNode left) {
        this.left = left;
    }

    /**
     * <p>Getter for the field <code>order</code>.</p>
     *
     * @return the order
     */
    public String getOrder() {
        return order;
    }

    /**
     * <p>Setter for the field <code>order</code>.</p>
     *
     * @param order the order to set
     */
    public void setOrder(String order) {
        this.order = order;
    }
}

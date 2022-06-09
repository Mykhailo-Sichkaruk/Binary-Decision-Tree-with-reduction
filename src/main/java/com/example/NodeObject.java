package com.example;

import java.math.BigInteger;

/**
 * This class represents basic object KEY:VALUE idea
 * KEY : hash of Boolean Function
 * VALUE : Pointer to BDD_Node that represents Boolean function
 */
public class NodeObject {
    private BigInteger hash;
    private TreeNode node;

    NodeObject(BigInteger key, TreeNode value) {
        this.setHash(key);
        this.setNode(value);
    }

    /**
     * @return the node
     */
    public TreeNode getNode() {
        return node;
    }

    /**
     * @param node the node to set
     */
    public void setNode(TreeNode node) {
        this.node = node;
    }

    /**
     * @return the hash
     */
    public BigInteger getHash() {
        return hash;
    }

    /**
     * @param key the hash to set
     */
    public void setHash(BigInteger key) {
        this.hash = key;
    }
}
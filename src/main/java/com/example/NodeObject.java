package com.example;

import java.math.BigInteger;

/**
 * This class represents basic object KEY:VALUE idea
 * KEY : hash of Boolean Function
 * VALUE : Pointer to BDD_Node that represents Boolean function
 *
 * @author MS
 * @version $Id: $Id
 */
public class NodeObject {
    private BigInteger hash;
    private TreeNode node;

    NodeObject(BigInteger key, TreeNode value) {
        this.setHash(key);
        this.setNode(value);
    }

    /**
     * <p>Getter for the field <code>node</code>.</p>
     *
     * @return the node
     */
    public TreeNode getNode() {
        return node;
    }

    /**
     * <p>Setter for the field <code>node</code>.</p>
     *
     * @param node the node to set
     */
    public void setNode(TreeNode node) {
        this.node = node;
    }

    /**
     * <p>Getter for the field <code>hash</code>.</p>
     *
     * @return the hash
     */
    public BigInteger getHash() {
        return hash;
    }

    /**
     * <p>Setter for the field <code>hash</code>.</p>
     *
     * @param key the hash to set
     */
    public void setHash(BigInteger key) {
        this.hash = key;
    }
}

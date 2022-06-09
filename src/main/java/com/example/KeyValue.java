package com.example;

import java.math.BigInteger;

/**
 * This class represents basic object KEY:VALUE idea
 * KEY : hash of Boolean Function
 * VALUE : Pointer to BDD_Node that represents Boolean function
 */
public class KeyValue{
    private BigInteger hash;
    public BDD_Node Node;

    KeyValue(BigInteger key, BDD_Node value){
        this.setHash(key);
        this.setNode(value);
    }

    /**
     * @return the node
     */
    public BDD_Node getNode() {
        return Node;
    }

    /**
     * @param node the node to set
     */
    public void setNode(BDD_Node node) {
        this.Node = node;
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
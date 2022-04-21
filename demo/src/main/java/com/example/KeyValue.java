package com.example;

public class KeyValue{
    private long hash;
    private BDD_Node Node;

    KeyValue(long key, BDD_Node value){
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
    public long getHash() {
        return hash;
    }

    /**
     * @param key the hash to set
     */
    public void setHash(long key) {
        this.hash = key;
    }
}
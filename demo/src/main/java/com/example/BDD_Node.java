package com.example;

public class BDD_Node{
    public String b_function;
    public String letter; 
    public BDD_Node right;
    public BDD_Node left;
    public String order;

    BDD_Node(String b_func, String letter, String order){
        this.letter = letter;
        this.b_function = b_func;
        this.right = null;
        this.left = null;
        this.order = order;
    }
}
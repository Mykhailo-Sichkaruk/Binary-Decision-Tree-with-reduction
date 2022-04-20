package com.example;

import java.net.SocketPermission;
import java.util.ArrayList;

public class BDD_Tree {
    private int Node_count;
    private int Variables_count;
    private BDD_Node BDD_Root = null;
    private String current_order = "";
    private int current_level = 1;
    private String order;

    BDD_Tree(String b_function, String order) {
        // Creating 1 level of Tree
        this.order = order;
        BDD_Root = new BDD_Node(b_function, String.valueOf(order.charAt(0)));
    }



}
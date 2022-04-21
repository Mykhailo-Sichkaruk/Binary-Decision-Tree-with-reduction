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
        BDD_Root = new BDD_Node(b_function, String.valueOf(order.charAt(0)), order);

        KeyValue[] Table = new KeyValue[(int) Math.pow(current_level, 2)];

    }

    private void callLvl(int lvl, int current_lvl, KeyValue[] Table, BDD_Node Root) {

        if (lvl == current_lvl) {
            Root.left = TableInsert(Table, 
                                    B_function.DNF_substitute_variable(false,
                                                                                String.valueOf(Root.order.charAt(0)), 
                                                                                Root.b_function, 
                                                                                order.substring(1)), 
                                    order.substring(1),
                                    String.valueOf(Root.order.charAt(0)));
                    
            Root.right = TableInsert(Table, 
                                    B_function.DNF_substitute_variable(true,
                                                                                String.valueOf(Root.order.charAt(0)), 
                                                                                Root.b_function, 
                                                                                order.substring(1)), 
                                    order.substring(1),
                                    String.valueOf(Root.order.charAt(0)));
                                    
        } else {
            callLvl(lvl, current_lvl++, Table, Root.left);
            callLvl(lvl, current_lvl++, Table, Root.right);
        }
    }

    private BDD_Node TableInsert(KeyValue[] Table, String Bfunction, String Order, String Letter) {
        for (int i = 0; i < Table.length; i++) {
            if (Table[i] != null && Table[i].getHash() == B_function.b_function_hashCode(Bfunction, Order)) {
                return Table[i].getNode();
            }
        }

        for (int i = 0; i < Table.length; i++) {
            if (Table[i] == null) {
                Table[i] = new KeyValue(B_function.b_function_hashCode(Bfunction, Order),
                                        new BDD_Node(Bfunction, Letter, Order));
                return Table[i].getNode();
            }
        }
        return null;

    }

}
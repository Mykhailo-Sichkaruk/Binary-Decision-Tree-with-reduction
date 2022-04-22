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
        String prettier_Bfunction = B_function.DNF_substitute_variable(true, "Z", b_function, "Z" + order); 
        BDD_Root = new BDD_Node(prettier_Bfunction, String.valueOf(order.charAt(0)), order);
        System.out.println(BDD_Root.b_function);
        System.out.println("--------------------------------");

        for (int i = 2; i <= order.length() + 1; i++) {
            KeyValue[] Table = new KeyValue[(int) Math.pow(2, i - 1)];
            createLvl(i, 1, Table, this.BDD_Root);
            for (int j = 0; j < Table.length; j++)
                if (Table[j] != null)
                    System.out.println(Table[j].getNode().b_function);
            
            System.out.println("--------------------------------");
        }
    }

    private void createLvl(int lvl, int current_lvl, KeyValue[] Table, BDD_Node Root) {

        if (lvl - 1 == current_lvl) {
            Root.left = TableInsert(Table,
                    B_function.DNF_substitute_variable(false,
                            String.valueOf(Root.order.charAt(0)),
                            Root.b_function,
                            order.substring(1)),
                    order.substring(1),
                    String.valueOf(Root.order.charAt(1)));

            Root.right = TableInsert(Table,
                    B_function.DNF_substitute_variable(true,
                            String.valueOf(Root.order.charAt(0)),
                            Root.b_function,
                            order.substring(1)),
                    order.substring(1),
                    String.valueOf(Root.order.charAt(1)));

        } else {
            createLvl(lvl, current_lvl + 1, Table, Root.left);
            createLvl(lvl, current_lvl + 1, Table, Root.right);
        }
    }

    private BDD_Node TableInsert(KeyValue[] Table, String Bfunction, String Order, String Letter) {
        long newNode_hash = B_function.b_function_hashCode(Bfunction, Order);
        for (int i = 0; i < Table.length; i++) {
            if (Table[i] != null && Table[i].getHash() == newNode_hash) {
                return Table[i].getNode();
            }
        }

        for (int i = 0; i < Table.length; i++) {
            if (Table[i] == null) {
                Table[i] = new KeyValue(newNode_hash, new BDD_Node(Bfunction, Letter, Order));
                return Table[i].getNode();
            }
        }
        return null;

    }

}
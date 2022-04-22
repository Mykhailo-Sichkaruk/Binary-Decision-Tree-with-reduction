package com.example;

import java.net.SocketPermission;
import java.util.ArrayList;

public class BDD_Tree {
    private BDD_Node BDD_Root = null;
    private String order;
    private int Node_count = 0;

    BDD_Tree(String b_function, String order) {
        // Creating 1st level of Tree
        this.order = order;
        String prettier_Bfunction = B_function.DNF_substitute_variable(true, "Z", b_function, "Z" + order);
        BDD_Root = new BDD_Node(prettier_Bfunction, String.valueOf(order.charAt(0)), order);
        System.out.println(BDD_Root.b_function);
        System.out.println("--------------------------------");

        //Creating other levels
        for (int i = 2; i <= order.length() + 1; i++) {
            KeyValue[] Table = new KeyValue[(int) Math.pow(2, i - 1)];
            CreateLvl(i, 1, Table, this.BDD_Root);
            for (int j = 0; j < Table.length; j++)
                if (Table[j] != null)
                    System.out.println(Table[j].getNode().b_function);

            System.out.println("--------------------------------");
        }

        System.out.println("Max     Nodes_count = " + (int )(Math.pow(2, this.order.length()) - 1));
        System.out.println("Current Nodes_count = " + this.Node_count);

    }

    /**
     * Creates new level of BDD_Tree, using Table, to reduse repaeats
     * @param lvl
     * @param current
     * @param Table
     * @param Root
     */
    private void CreateLvl(int lvl, int current, KeyValue[] Table, BDD_Node Root) {

        if (lvl == current + 1) {
            String new_order = Root.order.substring(1);
            String letter = String.valueOf(Root.order.charAt(0));
            String function =  B_function.DNF_substitute_variable(false, letter, Root.b_function, Root.order);
            
            Root.left = InsertTable(Table, function, new_order, letter);

            function = B_function.DNF_substitute_variable(true, letter, Root.b_function, Root.order);
            
            Root.right = InsertTable(Table, function, new_order, letter);


        } else {
            CreateLvl(lvl, current + 1, Table, Root.left);
            CreateLvl(lvl, current + 1, Table, Root.right);
        }
    }

    /**
     * Returns new or existing BDD_Node in Table
     * 
     * @param Table
     * @param Bfunction
     * @param Order
     * @param Letter
     * @return
     */
    private BDD_Node InsertTable(KeyValue[] Table, String Bfunction, String Order, String Letter) {
        long newNode_hash = B_function.b_function_hashCode(Bfunction, Order);

        // Search if there is existing Node with such a Hashcode, then return exsisting
        // one
        for (int i = 0; i < Table.length; i++) {
            if (Table[i] != null && Table[i].getHash() == newNode_hash) {
                return Table[i].getNode();
            }
        }

        // Or creates a new one and return it
        for (int i = 0; i < Table.length; i++) {
            if (Table[i] == null) {
                this.Node_count++;
                Table[i] = new KeyValue(newNode_hash, new BDD_Node(Bfunction, Letter, Order));
                return Table[i].getNode();
            }
        }
        return null;

    }

}
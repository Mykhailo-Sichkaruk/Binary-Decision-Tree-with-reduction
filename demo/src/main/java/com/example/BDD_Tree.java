package com.example;

import java.io.ObjectOutputStream.PutField;
import java.net.SocketPermission;
import java.util.ArrayList;

public class BDD_Tree {
    public BDD_Node Root = null;
    private String Order = "";
    private int Node_count = 0;
    public BDD_Node ONE = new BDD_Node("1", "", "");
    BDD_Node ZERO = new BDD_Node("0", "", "");;

    BDD_Tree(String b_function, String order) {
        // Creating: Root, "1", "0"
        this.Order = order;
        String prettier_Bfunction = B_function.SubstituteVariable_DNF(true, "Z", b_function, "Z" + order);
        this.Root = new BDD_Node(prettier_Bfunction, String.valueOf(order.charAt(0)), order);

        // Creating other levels
        for (int i = 2; i <= order.length() + 1; i++) {
            KeyValue[] Table = new KeyValue[(int) Math.pow(2, i - 1)];
            CreateLvl(i, 1, Table, this.Root);
        }



    }

    /**
     * Creates new level of BDD_Tree, using Table, to reduse repaeats
     * 
     * @param lvl
     * @param current
     * @param Table
     * @param Root
     */
    private void CreateLvl(int lvl, int current, KeyValue[] Table, BDD_Node Root) {
        if (Root.b_function.equals("1")) {
            Root.left = InsertTable(Table, "0", "", "");
            return;
        }

        else if (Root.b_function.equals("0")) {
            Root.right = InsertTable(Table, "1", "", "");
            return;
        }

        if (lvl == current + 1) {
            String new_order = Root.order.substring(1);
            String letter = String.valueOf(Root.order.charAt(0));
            String function = B_function.SubstituteVariable_DNF(false, letter, Root.b_function, Root.order);

            Root.left = InsertTable(Table, function, new_order, letter);

            function = B_function.SubstituteVariable_DNF(true, letter, Root.b_function, Root.order);

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
        if (Bfunction.equals("1"))
            return ONE;
        else if (Bfunction.equals("0"))
            return ZERO;

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

    public void PrintTree() {
        for (int i = 1; i <= this.Order.length() + 1; i++) {
            PrintLvl(i, 1, this.Root);
            System.out.println("\n-------------------------------\n");
        }
        double ReductionRate = this.Node_count / (int)(Math.pow(2, this.Order.length()));
        System.out.println("Count of node in the Tree      : " + this.Node_count );
        System.out.println("Count of node without reduction: " + (int)(Math.pow(2, this.Order.length())));
        System.out.println("Reduction efficiency: " + ReductionRate);
    }

    private void PrintLvl(int lvl, int current, BDD_Node Root) {
        if (lvl == current) {
            System.out.print("[" + Root.b_function + "] ");
        } else {
            if (Root.b_function.equals("1") || Root.b_function.equals("0"))
                return;

            PrintLvl(lvl, current + 1, Root.left);
            PrintLvl(lvl, current + 1, Root.right);
        }
    }

    public char BDD_USE(String Arguments, BDD_Node Root) {
        char result = '-';
        if (Root.b_function.equals("1"))
            return '1';
        else if (Root.b_function.equals("0"))
            return '0';
        else {
            if (Arguments.charAt(0) == '1'){
                result = BDD_USE(Arguments.substring(1), Root.right);
            }
            else if (Arguments.charAt(0) == '0'){
                result = BDD_USE(Arguments.substring(1), Root.left);
            }
        }

        return result;

    }

    /**
     * @return the order
     */
    public String getOrder() {
        return Order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(String order) {
        Order = order;
    }

}
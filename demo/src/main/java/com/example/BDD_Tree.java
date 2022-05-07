package com.example;

import java.math.BigInteger;

public class BDD_Tree {
    public BDD_Node ROOT = null;
    private String Order = "";
    private int nodeCount = 0;
    private final BDD_Node ONE = new BDD_Node("1", "", "");
    private final BDD_Node ZERO = new BDD_Node("0", "", "");;

    BDD_Tree(String bFunction, String order) {
        // Creating: Root, "1", "0"
        this.Order = order;
        String prettierFunction = DNF.SubstituteVariable(true, "Z", bFunction, "Z" + order);
        this.ROOT = new BDD_Node(prettierFunction, String.valueOf(order.charAt(0)), order);
        this.nodeCount += 3;

        // Creating other levels
        for (int i = 2; i <= order.length() + 1; i++) {
            KeyValue[] Table = new KeyValue[(int) Math.pow(2, i - 1)];
            createLvl(i, 1, Table, this.ROOT);
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
    private void createLvl(int lvl, int current, KeyValue[] Table, BDD_Node Root) {
        // case 1 - its Leaf - then return
        if (Root == null) {
            System.out.println(null + " :: " + lvl + " :: " + current);
            return;
        }
        if (Root == ONE) {
            return;
        } else if (Root == ZERO) {
            return;
        }

        String newOrder = this.ROOT.order.substring(lvl - 2);
        String substituteLetter = String.valueOf(this.ROOT.order.charAt(lvl - 2));
        String lvlLetter;
        String lvlOrder;

        if (this.ROOT.order.length() >= lvl) {
            lvlLetter = String.valueOf(this.ROOT.order.charAt(lvl - 1));
            lvlOrder = this.ROOT.order.substring(lvl - 1);
        } else {
            lvlOrder = this.ROOT.order.substring(lvl - 2);
            lvlLetter = String.valueOf(this.ROOT.order.charAt(lvl - 2));
        }
        // case 2 - we reach last level
        if (Root.letter.equals(substituteLetter)) {

            String function = DNF.SubstituteVariable(false, substituteLetter, Root.b_function, newOrder);
            if (function.equals("0")) {
                Root.left = ZERO;
            } else if (function.equals("1")) {
                Root.left = ONE;
            } else if (!function.contains(lvlLetter)) {
                Root.left = null;
            } else
                Root.left = insertTable(Table, function, lvlOrder, lvlLetter);

            function = DNF.SubstituteVariable(true, substituteLetter, Root.b_function, newOrder);
            if (function.equals("0")) {
                Root.right = ZERO;
            } else if (function.equals("1")) {
                Root.right = ONE;
            } else if (!function.contains(lvlLetter)) {
                Root.right = null;
            } else
                Root.right = insertTable(Table, function, lvlOrder, lvlLetter);

        } else {
            if (Root.left == null) {
                String function = DNF.SubstituteVariable(false, Root.letter, Root.b_function, Root.order);
                if (function.equals("0")) {
                    Root.left = ZERO;
                } else if (function.equals("1")) {
                    Root.left = ONE;
                } else if (!function.contains(lvlLetter)) {
                    Root.left = null;
                } else {
                    Root.left = insertTable(Table, function, lvlOrder, lvlLetter);
                }
            } else
                createLvl(lvl, current + 1, Table, Root.left);

            if (Root.right == null) {
                String function = DNF.SubstituteVariable(true, Root.letter, Root.b_function, Root.order);
                if (function.equals("0")) {
                    Root.right = ZERO;
                } else if (function.equals("1")) {
                    Root.right = ONE;
                } else if (!function.contains(lvlLetter)) {
                    Root.right = null;
                } else {
                    Root.right = insertTable(Table, function, lvlOrder, lvlLetter);
                }
            } else {
                createLvl(lvl, current + 1, Table, Root.right);
            }
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
    private BDD_Node insertTable(KeyValue[] Table, String Bfunction, String Order, String Letter) {
        if (Bfunction.equals("1"))
            return ONE;
        else if (Bfunction.equals("0"))
            return ZERO;

        BigInteger newNode_hash = DNF.HashCode(Bfunction, Order);

        // Search if there is existing Node with such a Hashcode, then return exsisting
        // one
        for (int i = 0; i < Table.length; i++) {
            if (Table[i] != null && Table[i].getHash().equals(newNode_hash)
                    && Table[i].getNode().b_function.equals(Bfunction)) {
                return Table[i].getNode();
            }
        }

        // Or creates a new one and return it
        for (int i = 0; i < Table.length; i++) {
            if (Table[i] == null) {
                this.nodeCount++;
                Table[i] = new KeyValue(newNode_hash, new BDD_Node(Bfunction, Letter, Order));
                return Table[i].getNode();
            }
        }
        return null;

    }

    public void PrintTree() {
        for (int i = 1; i <= this.Order.length(); i++) {
            PrintLvl(i, 1, this.ROOT);
            System.out.println("\n-------------------------------");
        }
        System.out.println("\t" + "[" + this.ZERO.b_function + "]" + "\t" + "[" + this.ONE.b_function + "]");

        int NodesMaxCount = ((int) (Math.pow(2, this.Order.length())));
        float count = (float) this.nodeCount;
        double ReductionRate = (count / NodesMaxCount);
        System.out.println("Count of node in the Tree      : " + this.nodeCount);
        System.out.println("Count of node without reduction: " + NodesMaxCount);
        System.out.println("Reduction efficiency: " + ReductionRate);
        System.out.println("================================================================================");
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

    /**
     * 
     * @param Arguments
     * @param Root
     * @return
     */
    public char BDD_USE(String Arguments, BDD_Node Root) {
        char result = '-';
        if (Root.b_function.equals("1"))
            return '1';
        else if (Root.b_function.equals("0"))
            return '0';
        else {
            String order = Root.order;
            int diff = Arguments.length() - Root.order.length();
            if (Arguments.charAt(diff) == '1') {
                result = BDD_USE(Arguments.substring(1), Root.right);
            } else if (Arguments.charAt(diff) == '0') {
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
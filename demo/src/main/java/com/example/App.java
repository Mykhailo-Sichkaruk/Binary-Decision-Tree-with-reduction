package com.example;

/**
 * Hello world!
 *
 */
public class App 
{   
    private final static String b_function = "";
    private final static String order = "ABCD";
    public static void main( String[] args )
    {
        BDD_Tree Tree = new BDD_Tree(b_function, order);
        Tree.cut_function(false, "A", "DDDD+BC+AC+ADC+CAB+DDDD+CCC+CAS+C!AS", order);
        Tree.cut_function(true, "A", "AB+BC+AC+ADC+CAB+DDDD+CCC+CAS+C!AS", order);

        Tree.cut_function(false, "A", "!A+!ABC+ACB", order);
        Tree.cut_function(true, "A", "!A+!ABC+ACB", order);


        Tree.cut_function(true, "B", "B+D+CAB+DB+C", order);
        Tree.cut_function(false, "B", "B+D+CAB+DB+C", order);
    }
}

package com.example;

/**
 * Hello world!
 *
 */
public class App 
{   
    private final static String b_function = "ABC+CAB+BAC+BC+BA+AC+!AC+!AB+DC+!DC+AD+DA+!AD";
    private final static String order = "ABCD";
    public static void main( String[] args )
    {
        BDD_Tree Tree = new BDD_Tree(b_function, order);
        //B_function.test_generate_substitute();
        //System.out.println(B_function.DNF_substitute_variable(false, "C", "C", "c"));
    }
}

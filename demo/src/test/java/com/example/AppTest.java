package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
class AppTest {
    private final static String Bfunction = "!D";
    private final static String Order = "ABCD";

    private static int DEFAULT_TEST_COUNT = 100;
    BDD_Tree Tree = new BDD_Tree(Bfunction, Order);
    /**
     * Rigorous Test.
     */
    @Test
    void BDDuseTest() {
        BDDuse_Test(Tree);
    }
    

    void BDDuse_Test(BDD_Tree Tree){


        char BDD_USE_result = ' ';
        char Alternative_result = ' ';
        int StateVariations = (int )Math.pow(2, Tree.getOrder().length());
        String Arguments ;

        for(int i = 0; i < StateVariations; i++){
            Arguments = Integer.toBinaryString(i);
            int arg_len = Arguments.length();
            if(arg_len < 8){
                for(int j = 0; j < Tree.getOrder().length() - arg_len; j++)
                    Arguments = "0" + Arguments;  
            }
            Alternative_result = B_function.SubstituteAllVariables_DNF(Arguments, Tree.Root.b_function, Tree.Root.order);
            BDD_USE_result = Tree.BDD_USE(Arguments, Tree.Root);
            assertEquals(BDD_USE_result, Alternative_result);
        }

    }
}

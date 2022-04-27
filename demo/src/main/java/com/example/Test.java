package com.example;

public class Test {
    private static int DEFAULT_TEST_COUNT = 100;

    public static void BDDuse_Test(BDD_Tree Tree, Integer testCount){
        if(testCount == null)
            testCount = DEFAULT_TEST_COUNT;

        char BDD_USE_result = ' ';
        char Alternative_result = ' ';
        int StateVariations = (int )Math.pow(2, Tree.getOrder().length());
        
        for(int i = 0; i < StateVariations; i++){
            Alternative_result =DNF.SubstituteAllVariables(Integer.toBinaryString(i), Tree.Root.b_function, Tree.Root.order);
            BDD_USE_result = Tree.BDD_USE(Integer.toBinaryString(i), Tree.Root);
            System.out.println();
        }

    }
}

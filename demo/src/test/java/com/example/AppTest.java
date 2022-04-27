package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

/**
 * Unit test for simple App.
 */
class AppTest {
    private final static int BDDuse_COUNT = 100;
    private final static int Generate_COUNT = 100;
    private final static int HashCode_COUNT = 100;
    private final static String Alphabet = "ABCD";

    private static int DEFAULT_TEST_COUNT = 100;
    /**
     * Rigorous Test.
     */
    @Test
    void BDDuse_TEST() {
        for(int i = 0; i < BDDuse_COUNT; i++){
            String Bfunction =DNF.Generate(Alphabet, 10, Alphabet.length() + 1);
            BDD_Tree Tree = new BDD_Tree(Bfunction, Alphabet);
            BDDuse_test(Tree);
            Tree.PrintTree();
        }
    }
    

    void BDDuse_test(BDD_Tree Tree){
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
            Alternative_result =DNF.SubstituteAllVariables(Arguments, Tree.Root.b_function, Tree.Root.order);
            BDD_USE_result = Tree.BDD_USE(Arguments, Tree.Root);
            assertEquals(BDD_USE_result, Alternative_result);
        }

    }

    @Test
    void DNFGenerate_TEST(){
        System.out.println("Alphabet : " + Alphabet);
        for (int i = 0; i < Generate_COUNT; i++) {
            System.out.println(DNF.Generate(Alphabet, 10, Alphabet.length() + 1));
        }
    }

    /**
     * Prints test of substitution in random boolean function
     * 
     * @param iterations_count = 10
     * @param alphabet         = "ABCD"
     */
    void GenerationSubstitution_Test(Integer iterations_count, String alphabet) {
        // Default values
        if (iterations_count == null) {
            iterations_count = 10;
        }
        if (alphabet == null) {
            alphabet = "ABCD";
        }

        Random rand = new Random();
        String function;
        String substituted_funciton;
        String letter;

        for (int i = 0; i < iterations_count; i++) {
            function = DNF.Generate(alphabet, 4, 4);
            letter = String.valueOf(alphabet.charAt(rand.nextInt(alphabet.length())));

            System.out.println(
                    function + "\thash() = " + DNF.HashCode(function, alphabet) + "\t" + letter + " = 0");
            substituted_funciton = DNF.SubstituteVariable(false, letter, function, alphabet);
            System.out.println(
                    substituted_funciton + "\t\thash() = " + DNF.HashCode(substituted_funciton, alphabet));

            System.out.println(
                    function + "\thash() = " + DNF.HashCode(function, alphabet) + "\t" + letter + " = 1");
            substituted_funciton = DNF.SubstituteVariable(true, letter, function, alphabet);
            System.out.println(
                    substituted_funciton + "\t\thash() = " + DNF.HashCode(substituted_funciton, alphabet));

            System.out.println("-----------------------------------------------");
        }
    }

    @Test
    void HashCode_TEST(){
        String function = "";
        long hash = 0;

        System.out.println("Alphabet : " + Alphabet + "\n");
        for (int i = 0; i < HashCode_COUNT; i++) {
            function = (DNF.Generate(Alphabet, 10, Alphabet.length() + 1));
            hash = DNF.HashCode(function, Alphabet);
            System.out.println(function);
            System.out.println("Hash: " + hash);
            System.out.println("--------------------------------");
        }
    }

    @Test
    void test(){}
}

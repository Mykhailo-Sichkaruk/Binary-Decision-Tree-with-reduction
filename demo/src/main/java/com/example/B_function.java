package com.example;

import java.util.ArrayList;
import java.util.Random;

public class B_function {

/**
     * Returns DNF from Array of conjunctions
     * 
     * @param stringArray
     * @return
     * Example :: "AB", "AC", "BC" ==> "AB+AC+BC"
     */
    private static String conjunctionArray_to_DNF(String[] stringArray) {
        String result = "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < stringArray.length; i++) {
            sb.append("+" + stringArray[i]);
        }
        result = sb.toString();
        result = result.substring(1);
        return result;
    }

    /**
     * Gets Array of conjunctions and returns Array without duplicates
     * 
     * @param b_functions
     * @return
     *         Example :: "AB", "A", "AC", "BC", "A", "AB" ==> "AB", "A", "AC", "BC"
     */
    private static String[] delete_duplicates(String[] b_functions) {
        ArrayList<String> result_pretty_NOduplicates_ArrayList = new ArrayList<String>();

        for (int i = 0; i < b_functions.length; i++) {
            // Push if there wasn`t such before
            if (!result_pretty_NOduplicates_ArrayList.contains(b_functions[i])) {
                result_pretty_NOduplicates_ArrayList.add(b_functions[i]);
            }
        }
        String[] result_pretty_NOduplicates = result_pretty_NOduplicates_ArrayList
                .toArray(new String[result_pretty_NOduplicates_ArrayList.size()]);
        return result_pretty_NOduplicates;
    }

    /**
     * Gets conjuction and make it prettier -
     * Removes duplicates of variables, put variables in specified order
     * 
     * @param b_function
     * @param order
     * @return
     *         Example :: BBCCAA ==> ABC (if order = "ABC")
     */
    private static String pretty_conjuction(String b_function, String order) {
        String result = "";
        for (int i = 0; i < order.length(); i++) {
            if (b_function.contains("!" + String.valueOf(order.charAt(i)))) {
                result += "!" + String.valueOf(order.charAt(i));
            } else if (b_function.contains(String.valueOf(order.charAt(i)))) {
                result += String.valueOf(order.charAt(i));
            }
        }
        return result;
    }

    /**
     * Gets Array of conjunctions and make them prettier -
     * Removes duplicates of variables, put variables in specified order, put bigger
     * conjunction
     * closer to start of array
     * 
     * @param b_functions
     * @param order
     * @return
     *         Example :: BAC+CCA+DDD+!A!B!A ==> ABC+AC+!A!B+D (if order = "ABCD")
     */
    private static String[] pretty_dnf(String[] b_functions, String order) {
        String[] result_pretty = b_functions;

        // Make prettier every conjunction
        for (int i = 0; i < result_pretty.length; i++) {
            result_pretty[i] = pretty_conjuction(result_pretty[i], order);
        }

        // Bubble sort to put biggest conjunction in beggining of array
        String buf;
        for (int i = 0; i < result_pretty.length - 1; i++) {
            for (int j = 0; j < result_pretty.length - i - 1; j++) {
                if (result_pretty[j].replaceAll("!", "").length() <= result_pretty[j + 1].replaceAll("!", "").length()) {
                    buf = result_pretty[j];
                    result_pretty[j] = result_pretty[j + 1];
                    result_pretty[j + 1] = buf;
                }
            }
        }

        return result_pretty;
    }

    /**
     * Return DNF with -
     * substituted variable
     * removed duplicates
     * in specified order
     * bigger conjunctions closer to start
     * 
     * @param state
     * @param letter
     * @param b_function
     * @param order
     * @return
     *         Example :: "AB+!AB+AAAC+BCB+!B!A+!A" (A = 1) ==> "BC+B+C+!B"
     */
    public static String DNF_substitute_variable(boolean state, String letter, String b_function, String order) {
        String result = "";
        String[] sub_finctions = b_function.split("\\+");

        sub_finctions = pretty_dnf(sub_finctions, order);
        sub_finctions = delete_duplicates(sub_finctions);

        if (state) { // If A = 1
            for (int i = 0; i < sub_finctions.length; i++) {
                // If there is !A - forget this conjunction
                if (sub_finctions[i].contains("!" + letter)) {
                    // Nothing
                } // If there is A - replace with "1", push to result
                else if (sub_finctions[i].contains(letter)) {
                    result += "+" + sub_finctions[i].replaceAll(letter, "1");
                } // If there are not A or !A - push to result
                else
                    result += "+" + sub_finctions[i];
            }
        } else { // If A = 0
            for (int i = 0; i < sub_finctions.length; i++) {
                // If there is !A - replace with "1", push to result
                if (sub_finctions[i].contains("!" + letter)) {
                    if (sub_finctions[i].replaceAll("!" + letter, "1").contains(letter)) {

                    } else
                        result += "+" + sub_finctions[i].replaceAll("!" + letter, "1");

                } // If there is A - forget this conjunction
                else if (sub_finctions[i].contains(letter)) {
                    // Nothing
                } // If there are not A or !A - push to result
                else
                    result += "+" + sub_finctions[i];
            }
        }

        result += ";";
        if (result.contains("+1+")) {
            result = "1";
        } else if (result.contains("+1;")) {
            result = "1";
        } else if (result.length() <= 1) {
            result = "0";
        } else { // By not we have "+1BC+B+1C+BC" b_function, and we remove first "+" and every
                 // "1"
            result = result.replaceAll("\\+1", "+");
            result = result.replaceAll("1", "");
            result = result.replaceAll(";", "");
            result = result.substring(1);

        }

        // Removes duplicates after substitution of variable
        result = conjunctionArray_to_DNF(delete_duplicates(result.split("\\+")));

        return result;
    }
    /**
     * Returns uniq ID that represents
     * 
     * @param b_func
     * @return
     */
    public static long b_function_hashCode(String b_func, String order) {
        long result = 1;
        int conjunction_code = 0;

        String[] conjunction = b_func.split("\\+");

        for (int i = 0; i < conjunction.length; i++) {
            conjunction_code = 0;
            for (int j = 0; j < order.length(); j++) {
                if (conjunction[i].contains("!" + String.valueOf(order.charAt(j)))) {
                    conjunction_code += 2 * Math.pow(3, j);
                } else if (conjunction[i].contains(String.valueOf(order.charAt(j)))) {
                    conjunction_code += Math.pow(3, j);
                }
            }
            if(conjunction_code == 0)
                conjunction_code = 1;
            result *= conjunction_code;
            
        }
        result += Math.pow(10, String.valueOf(result).length() + 1) * conjunction.length ;
        return result;
    }

    /**
     * Returns DNF with conjunction_count of conjunctions, every conjunction lenght
     * = conjunction_max_length, or could be less
     * 
     * @param Alphabet
     * @param conjunction_count
     * @param conjunction_max_length
     * @return
     */
    public static String generate_DNF(String Alphabet, int conjunction_count, int conjunction_max_length) {
        String result = "";
        Random rand = new Random();

        for (int i = 0; i < conjunction_count; i++) {
            int conjunction_lenght = rand.nextInt(conjunction_max_length);
            if (conjunction_lenght > 0)
                result += "+";
            for (int j = 0; j < conjunction_lenght; j++) {
                result += ((rand.nextBoolean()) ? "!" : "") + Alphabet.charAt(rand.nextInt(Alphabet.length()));
            }
        }
        result = result.substring(1);
        return result;
    }

    public static void test_generate_substitute() {
        int count = 10;
        String Alphabet = "ABCDE";
        int max_length = 3;
        int counjunction_count = 10;
        Random rand = new Random();
        String function = "";
        String substituted_funciton = "";
        String letter = "";
        for (int i = 0; i < count; i++) {
            function = generate_DNF(Alphabet, counjunction_count, max_length);
            letter = String.valueOf(Alphabet.charAt(rand.nextInt(Alphabet.length())));
            System.out.println(function + "\thash() = " + b_function_hashCode(function, Alphabet) + "\t" + letter + " = 0");
            substituted_funciton = DNF_substitute_variable(false, letter, function, Alphabet);
            System.out.println(substituted_funciton + "\t\thash() = " + b_function_hashCode(substituted_funciton, Alphabet));

            System.out.println(function + "\thash() = " + b_function_hashCode(function, Alphabet) + "\t" + letter + " = 1");
            substituted_funciton = DNF_substitute_variable(true, letter, function, Alphabet);
            System.out.println(substituted_funciton + "\t\thash() = " + b_function_hashCode(substituted_funciton, Alphabet));

            System.out.println("-----------------------------------------------");
        }
    }

}

package com.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Random;

/**
 * Class that provides functionality in
 * -generating DNF Bfuncitons
 * -substitution of one variable in DNF
 * -substitution of all variables in DNF
 * -getting HashCode of DNF
 */
public class DNF {

    /**
     * Returns String DNF from Array of conjunctions
     * 
     * @param stringArray
     * @return
     *         Example :: "AB", "AC", "BC" ==> "AB+AC+BC"
     */
    private static String ConjunctionArrayToDNF(String[] stringArray) {
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
     * Returns Array of conjunctions without duplicates
     * 
     * @param conjunction
     * @return
     *         Example :: "AB", "A", "AC", "BC", "A", "AB" ==> "AB", "A", "AC", "BC"
     */
    private static String[] DeleteDuplicates(String[] conjunction) {
        ArrayList<String> result_NOduplicates_ArrayList = new ArrayList<String>();

        for (int i = 0; i < conjunction.length; i++) {
            // Push if there wasn`t such before
            if (!result_NOduplicates_ArrayList.contains(conjunction[i])) {
                result_NOduplicates_ArrayList.add(conjunction[i]);
            }
        }
        String[] result_NOduplicates = result_NOduplicates_ArrayList
                .toArray(new String[result_NOduplicates_ArrayList.size()]);
        return result_NOduplicates;
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
    private static String PrettyConjunction(String b_function, String order) {
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
     * conjunction closer to start of array
     * 
     * @param conjunction
     * @param order
     * @return
     *         Example :: BAC+CCA+DDD+!A!B!A ==> ABC+AC+!A!B+D (if order = "ABCD")
     */
    private static String[] Pretty(String[] conjunction, String order) {
        String[] result = conjunction;

        // Make prettier every conjunction
        for (int i = 0; i < result.length; i++) {
            result[i] = PrettyConjunction(result[i], order);
        }

        // Bubble sort to put biggest conjunction in beggining of array
        String buf;
        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - i - 1; j++) {
                if (result[j].replaceAll("!", "").length() <= result[j + 1].replaceAll("!", "")
                        .length()) {
                    buf = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = buf;
                }
            }
        }

        result = DeleteDuplicates(result);
        return result;
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
    public static String SubstituteVariable(boolean state, String letter, String Bfunction, String order) {
        if (Bfunction.equals("1"))
            return "1";
        else if (Bfunction.equals("0"))
            return "0";

        // Preparing---------------------
        String result = "";
        String[] sub_finctions = Bfunction.split("\\+");

        sub_finctions = Pretty(sub_finctions, order);

        // Substitution--------------------
        if (state) { // If A = 1
            for (int i = 0; i < sub_finctions.length; i++) {
                // If there is !A - forget this conjunction
                if (sub_finctions[i].contains("!" + letter)) {
                    // Nothing
                } // If there is A - replace with "1", push to result
                else if (sub_finctions[i].equals(letter))
                    return "1";
                else if (sub_finctions[i].contains(letter)) {
                    result += "+" + sub_finctions[i].replaceAll(letter, "");
                } // If there are not A or !A - push to result
                else
                    result += "+" + sub_finctions[i];
            }
        } else { // If A = 0
            for (int i = 0; i < sub_finctions.length; i++) {
                // If there is !A
                if (sub_finctions[i].contains("!" + letter)) {
                    // If there is !A*A == 0
                    if (sub_finctions[i].replaceAll("!" + letter, "1").contains(letter)) {
                        // Nothing
                    } // If single !A - replace with "1", push to result
                    else if (sub_finctions[i].equals("!" + letter))
                        return "1";
                    else {
                        result += "+" + sub_finctions[i].replaceAll("!" + letter, "");
                    }
                } // If there is A - forget this conjunction
                else if (sub_finctions[i].contains(letter)) {
                    // Nothing
                } // If there are not A or !A - push to result
                else
                    result += "+" + sub_finctions[i];
            }
        }

        if (result.length() == 0) {
            return "0";
        } else {
            result = result.substring(1);
        }

        // Removes duplicates after substitution of variable

        result = ConjunctionArrayToDNF(Pretty(result.split("\\+"), order));

        return result;
    }

    /**
     * Returns uniq ID that represents
     * 
     * @param b_func
     * @return
     */
    public static long HashCode(String b_func, String order) {
        if (b_func.equals("1"))
            return 1;
        else if (b_func.equals("0"))
            return 0;

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
            if (conjunction_code == 0)
                conjunction_code = 1;

            // System.out.print("*" + conjunction_code);
            // if (order.length() > 8)
            //     result += (result * conjunction_code) % 1234;
            // else
                result *= conjunction_code;

            if (result > 9223372036854786L) {
                result %= 100000000;
                result ++;
                // System.out.print("-");
            }

        }
        //System.out.print("\n");

         result += Math.pow(10, String.valueOf(result).length()) * conjunction.length;
        // System.out.println(result);
        return result;
    }

    // public static BigInteger HashCodeBig(String b_func, String order) {
    // if (b_func.equals("1"))
    // return 1;
    // else if (b_func.equals("0"))
    // return 0;

    // long result = 1;
    // int conjunction_code = 0;

    // String[] conjunction = b_func.split("\\+");

    // for (int i = 0; i < conjunction.length; i++) {
    // conjunction_code = 0;
    // for (int j = 0; j < order.length(); j++) {
    // if (conjunction[i].contains("!" + String.valueOf(order.charAt(j)))) {
    // conjunction_code += 2 * Math.pow(3, j);
    // } else if (conjunction[i].contains(String.valueOf(order.charAt(j)))) {
    // conjunction_code += Math.pow(3, j);
    // }
    // }
    // if (conjunction_code == 0)
    // conjunction_code = 1;

    // System.out.print("*" + conjunction_code);
    // result *= conjunction_code;

    // }
    // System.out.print("\n");

    // result += Math.pow(10, String.valueOf(result).length()) * conjunction.length;
    // System.out.println(result);
    // return result;
    // }

    /**
     * Returns random DNF
     * 
     * @param alphabet             - letter in DNF
     * @param conjunctionsMaxCount - Max count of conjunctions
     * @param conjunctionMaxLength - Max length of conjunction
     * @return String DNF
     *         Example :: "BAC+!AD+DB+C!A"
     */
    public static String Generate(String alphabet, int conjunctionsMaxCount, int conjunctionMaxLength) {
        String result = "";
        Random rand = new Random();

        conjunctionsMaxCount = rand.nextInt(conjunctionsMaxCount);
        for (int i = 0; i < conjunctionsMaxCount; i++) {
            int conjunction_lenght = rand.nextInt(conjunctionMaxLength);
            if (conjunction_lenght > 0) {
                result += "+";
                for (int j = 0; j < conjunction_lenght; j++) {
                    result += ((rand.nextBoolean()) ? "!" : "") + alphabet.charAt(rand.nextInt(alphabet.length()));
                }
            }
        }

        if (result.length() <= 1)
            return ((rand.nextBoolean()) ? "!" : "") + alphabet.charAt(rand.nextInt(alphabet.length()));
        else {
            result = result.substring(1);
            return result;
        }
    }

    /**
     * Returns result of substitution of variables in DNF function- "0" or "1"
     * 
     * @param State     : array of variables value - "1010"
     * @param Bfunction : DNF function - "A!B+CD+!AD"
     * @param Order     : Varibles thas appears in Bfunction - "ABCD"
     * @return
     *         "1" / "0"
     */
    public static char SubstituteAllVariables(String State, String Bfunction, String Order) {
        String result = Bfunction;
        String letter;

        if (State.length() != Order.length()) {
            System.out.println("ERR");
            return '9';
        }

        for (int i = 0; i < Order.length(); i++) {
            letter = String.valueOf(Order.charAt(i));

            if (State.charAt(i) == '1') {
                result = result.replaceAll("!" + letter, "0");
                result = result.replaceAll(letter, "1");
            } else {
                result = result.replaceAll("!" + letter, "1");
                result = result.replaceAll(letter, "0");
            }
        }

        String[] conjunction = result.split("\\+");

        for (int i = 0; i < conjunction.length; i++) {
            if (conjunction[i].contains("0")) {

            } else {
                return '1';
            }

        }

        return '0';
    }

    /**
     * Returns random DNF - without repeats, in Alphabet order
     * 
     * @param Alphabet         - Letter that will be used in DNF
     * @param ConjunctionCount - Count of conjunctions (random by default)
     * @return String DNF
     * @Example "ABC+!AD+BD+!AC"
     */
    public static String GenerateDNF(String Alphabet, Integer ConjunctionCount) {
        String result = "";
        String conjunction = "";
        Random rand = new Random();
        if (ConjunctionCount == null) {
            ConjunctionCount = rand.nextInt(Alphabet.length() + 1);
        }

        for (int i = 0; i < ConjunctionCount; i++) {
            int conjunction_lenght = rand.nextInt(Alphabet.length());
            if (conjunction_lenght > 0) {
                conjunction = "";
                for (int j = 0; j < Alphabet.length(); j++) {
                    if (rand.nextBoolean())
                        conjunction += ((rand.nextBoolean()) ? "!" : "") + Alphabet.charAt(j);
                }
                if (conjunction.length() != 0)
                    result += "+" + conjunction;
            }
        }

        if (result.length() <= 1)
            return ((rand.nextBoolean()) ? "!" : "") + Alphabet.charAt(rand.nextInt(Alphabet.length()));
        else {
            result = result.substring(1);
            return result;
        }
    }

}

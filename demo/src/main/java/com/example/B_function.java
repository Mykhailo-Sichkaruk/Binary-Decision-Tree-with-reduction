package com.example;

import java.util.ArrayList;

public class B_function {
    /**
     * Returns DNF from Array of conjunctions
     * 
     * @param stringArray
     * @return
     */
    private String conjunctionArray_to_DNF(String[] stringArray) {
        String result = "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < stringArray.length; i++) {
            sb.append("+" + stringArray[i]);
        }
        result = sb.toString();

        return result;
    }

    /**
     * Gets Array of conjunctions and returns Array without duplicates
     * 
     * @param b_functions
     * @return
     */
    private String[] delete_duplicates(String[] b_functions) {
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
    private String pretty_conjuction(String b_function, String order) {
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
    private String[] pretty_dnf(String[] b_functions, String order) {
        String[] result_pretty = b_functions;

        // Make prettier every conjunction
        for (int i = 0; i < result_pretty.length; i++) {
            result_pretty[i] = pretty_conjuction(result_pretty[i], order);
        }

        // Bubble sort to put biggest conjunction in beggining of array
        String buf;
        for (int i = 0; i < result_pretty.length - 1; i++) {
            for (int j = 0; j < result_pretty.length - i - 1; j++) {
                if (result_pretty[j].length() < result_pretty[j + 1].length()) {
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
     */
    public String substitute_variable(boolean state, String letter, String b_function, String order) {
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

        System.out.println(state + " : " + letter);
        System.out.println("<<<" + b_function);
        System.out.println(">>>" + result + "\n");
        return result;
    }
}

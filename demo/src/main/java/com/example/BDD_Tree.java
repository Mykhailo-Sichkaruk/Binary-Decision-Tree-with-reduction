package com.example;

import java.net.SocketPermission;
import java.util.ArrayList;

public class BDD_Tree {
    private int Node_count;
    private int Variables_count;
    private BDD_Node BDD_Root = null;
    private String current_order = "";
    private int current_level = 1;
    private final String order;

    BDD_Tree(String b_function, String order) {
        // Creating 1 level of Tree
        this.order = order;
        BDD_Root = new BDD_Node(b_function, String.valueOf(order.charAt(0)));
        current_order = order.substring(1);
        //
    }

    private String pretty_AND(String b_function, String order) {
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

    private String[] pretty_dnf(String[] b_functions, String order) {
        String[] result_pretty = b_functions;
        for (int i = 0; i < result_pretty.length; i++) {
            result_pretty[i] = pretty_AND(result_pretty[i], order);
        }

        return result_pretty;
    }

    public String cut_function(boolean state, String letter, String b_function, String order) {
        String result = "";
        String[] sub_finctions = b_function.split("\\+");

        sub_finctions = pretty_dnf(sub_finctions, order);
        sub_finctions = delete_dublicates(sub_finctions);

        if (state) {
            for (int i = 0; i < sub_finctions.length; i++) {
                if (sub_finctions[i].contains("!" + letter)) {

                } else if (sub_finctions[i].contains(letter)) {
                    result += "+" + sub_finctions[i].replaceAll(letter, "1");
                } else
                    result += "+" + sub_finctions[i];
            }
        } else {
            for (int i = 0; i < sub_finctions.length; i++) {
                if (sub_finctions[i].contains("!" + letter))
                    result += "+" + sub_finctions[i].replaceAll("!" + letter, "1");
                else if (sub_finctions[i].contains(letter)) {

                } else
                    result += "+" + sub_finctions[i];
            }
        }

        if (result.contains("+1+")) {
            result = "1";
        } else {
            result = result.replaceAll("\\+1", "+");
            result = result.replaceAll("1", "");
            result = result.substring(1);
        }

        String[] result_noDuplicates = result.split("\\+");
        result_noDuplicates = delete_dublicates(result_noDuplicates);
        result = string_from_stringArray(result_noDuplicates);
        result = result.substring(1);

        System.out.println(state + " : " + letter);
        System.out.println("<<<" + b_function);
        System.out.println(">>>" + result + "\n");
        return result;
    }

    private String string_from_stringArray(String[] stringArray) {
        String result = "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < stringArray.length; i++) {
            sb.append("+" + stringArray[i]);
        }
        result = sb.toString();

        return result;
    }

    private String[] delete_dublicates(String[] b_functions) {
        ArrayList<String> result_pretty_NOduplicates_ArrayList = new ArrayList<String>();

        for (int i = 0; i < b_functions.length; i++) {

            if (!result_pretty_NOduplicates_ArrayList.contains(b_functions[i])) {
                result_pretty_NOduplicates_ArrayList.add(b_functions[i]);
            }
        }
        String[] result_pretty_NOduplicates = result_pretty_NOduplicates_ArrayList
                .toArray(new String[result_pretty_NOduplicates_ArrayList.size()]);
        return result_pretty_NOduplicates;
    }

    public static int b_function_hashCode(String b_func) {
        int result = 0;
        for (int i = 0; i < b_func.length(); i++)
            b_func.charAt(i);
        return result;
    }

}
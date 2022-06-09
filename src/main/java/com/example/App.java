package com.example;

public class App 
{   
    private final static String bFunction = "BCU!G!Q!Q!B!LM+LD!NGNSXKQ+LS!X!SIIL!W+JW+WLBUWGU!G!DU!WJ+!XJ+X!JK!D+JGW+XMMU!J!MJKQWS!BWC+U!WEJ!EUXE+BGJDJ+KLGEJ+ND!UD!UB+KEWUCEGEDDDC+!X!QB!BE+L!W!DIN!WXU!Q+!S!JUD!Q+CSE+BBLWIQIB+W!WUB!N!JXWJ!I";
    private final static String order = "EKLJQWSBMNGXUCDI";
    public static void main( String[] args )
    {
        BDD_Tree Tree = new BDD_Tree(bFunction, order);
        Tree.PrintTree();
        //System.out.println(Tree.BDD_USE("01001", Tree.ROOT));
    }
}

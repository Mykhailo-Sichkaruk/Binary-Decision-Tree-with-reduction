# Bianary-Decision-Tree-with-reduction

## Mykhailo Sichkaruk

1. Describe your solution
::My solution can creates reductioned BDD_tree for DNF functions (AB+BC+!AC)

Two key points of the project - BDD substitution and BDD reduction;

For BDD substitution I have DNF class and bunch of hepling funcitons. Main function here is -
public static String SubstituteVariable(boolean state, String letter, String Bfunction, String order)

    It Return DNF with - substituted variable, removed duplicates, in specified order and bigger conjunctions closer to start
    Example :: "AB+!AB+AAAC+BCB+!B!A+!A" (A = 1) ==> "BC+B+C+!B"

    In it`s body, SubstituteVariable() isnt so complicated. It goes through the DNF and divide it into conjunctions (like AB!C, or D!AEF)
    After that it replaces substituted letter if it gives us 1, or removes conjunction if it gives us 0.
    If while loop proccess we will find out at least one "1"="true" conjunction, SubstituteVariable() returns with "1", else it returns "0";

For BDD reduction I have implemented "hash-objects". And  

ABCDEFGHIJKLMNOP|
0110111010111001| :!AB!DFLMP+!AG!HIJN!P+FGHN!OP+!K!M!N+!FN+N+!I: [0] [1]

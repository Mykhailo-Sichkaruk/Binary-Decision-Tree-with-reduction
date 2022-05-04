
Checklist for DSA Assignment#2:

BDD_Create()

1.	BDD  * BDD_Create(string bfunction, string order)
2.	Char BDD_use(BDD *bdd, string input_values);

1.	BDD_create should create a reduced binary decision diagram that is supposed to represent/describe any given Boolean function(e.g, AB+C) it is provided with bfunction argument.
2.	The Boolean functions is described in a form of expression in a string
3.	The second argument is order of variables through which the BDD will be created in order.
4.	function BDD_create returns a pointer to the created BDD structure, this structure should have at least two parts(number of nodes (size of bdd) and number of variables. Of course you will have your own structure for representation of  one node too.
5.	This BDD_Create function already  includes reduction of BDD. (you can chose to reduce it after or during creation). You are required to reduce during the creation of  BDD.

BDD_Use()

1.	BDD_Use() will be used to use the created BDD with some input values for input variables of Boolean function  and for obtaining the result of Boolean function.
2.	You will go from top (root) to the down (leaf) nodes, the path from root to leaf is determined with  given combination of values for input variables,
3.	The argument of this BDD is a pointer to a specific BDD that is used and a string called input_values. String is combination of values for input variables.
4.	The return value of function BDD_use is char, which represent the result of Boolean function, it is either ‘1’ or ‘0’ and ‘-1’ in case of error in input values.

Testing:

1.	Use some randomly generated functions, which will be used for creation of BDD using the BDD_create
2.	The correctness of BDD can be proved with iterative calling of function BDD_Use in a way that you will use all possible combinations of values for the input variables of Boolean function and
3.	Compare the output/result of BDD_use with expected result. (the expected results can be obtained from application of the values to variables within the Boolean function expression(evaluating the expression).
4.	Test and evaluate your solution for various number of variables of Boolean function (at leat 13 variables) and the number of different Boolean functions within the same amount of variables should be at least 100,
5.	You should test the rate of BDD reduction(the number of deleted nodes/number of all nodes for a full diagram).
6.	
Documentation

1.	Describe your solution
2.	Describe individual functions, your structures, way of testing and test results.
3.	The test results should also include average percentage of BDD reduction and average execution time of your BDD functions, and number of variables
4.	Documentation must have a header(who and which assignment)
5.	Brief description of the used algorithms and rules with some pictures, showing how the solution works or selected part of your code,
6.	Try to explain why you think that your solution is correct and how it is efficient, reason why it is good/correct, and the way how you tested/evaluated. And
7.	Time and memory complexity estimation.
8.	As overall, it must be clear what you did and how you can prove that it is correct and how efficient it is.

-

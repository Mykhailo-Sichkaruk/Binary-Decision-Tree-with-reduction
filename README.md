# Bianary-Decision-Tree-with-reduction

Binary decision tree with no redundant nodes. Completely tested, with own generators.

## Reduction

This algorithm creates only unique nodes in tree. This is riched by 2-level reduction
 
### Horisontal Reduction

> Nodes in one level couldn't repeat

![Horisontal](/Documentation/HorisontalReduction.png)

For this I use NodeObject what is simply Object with *Node* itself and *hashCode* of the node  
When new level is creating, every node pushes in *ArrayList* of NodeObjects, and  

- if there is same node (with similar *hashCode*), then we just return node that was existing before, without creating new
- if there is **no** same node - then we just create one and return it

So there is no repeats in one level.

### Vertical Reduction

> Nodes among differend levels couldn't repeat

Or in other words

> Node that doesn't change anything in the next level couldn't be created

![Vertical](/Documentation/VerticalReduction.png)

For that I use comlex algorithm which started from root of the Tree and recurcively goes down to childrens. Also every level is asociated with some letter, so when we create level with letter "D" we check every Node without childrens on every level. It checks if child Nodes was changes relative to parent, if we substitute letter "D".

- if it changed, then moves to the both childs
- if is **didn't** change, than it stops.

## DNF Class

This is powerful instrument to manage DNF functions (ACD+!ACD+B!D+AB+AC+!C!D)  
Features:  

- Generate DNF function with/without repeats
- Substitute **one** variable in DNF and return smaller function  
- Substitute **all** variables in DNF and return 1/0  
- Pretty DNF: delete duplicates, reorder letters and conjunctions  
- Calculate HashCode  

---
## Default documentation for the task
- Read JavaDocs here : [Javadocs](/Documentation/Javadocs/index.html)
- Read task here : [task](/Documentation/Task.pdf)
- Read documentation here : [docs](/Documentation/Documentation.pdf)

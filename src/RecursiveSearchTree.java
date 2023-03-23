import org.w3c.dom.Node;

import java.awt.desktop.PreferencesEvent;

public class RecursiveSearchTree {

    public Node root;

    class Node {
        public int value;
        public Node left;
        public Node right;

        Node(int value) {
            this.value = value;
        }
    }


    public boolean rContains(int value) { //pass value to be searched

        return rContains(root, value); // start search using root of tree in overloaded private method below
    }

    private boolean rContains(Node currentNode, int value) { // private overloaded variant of above public method
        if (currentNode == null) return false; // loops starting with root node which first accounts for empty tree
        // then on second+ iterations accounts for leaf pointing to null

        if (currentNode.value == value) return true; // if value found, return true

        if (value < currentNode.value) { // otherwise if value is less than current node
            return rContains(currentNode.left, value); // recursively call the method such that the next 'left' node
            // is now the currentNode to continue searching down the tree
        } else {
            return rContains(currentNode.right, value); // same as above but if the searched for value is higher than
            // the current node
        }
        // the call stack will start with the public rContains method and will continue stacking recursive case method calls
        // which each 'rung' down the tree.  when we get to the base case, the true or false is returned to the preceding
        // recursive call in the call stack, which then returns its true or false, etc. until the original rContains public
        // method is finally popped off the call stack
    }

    public void rInsert (int value) {
        if (root == null) root = new Node(value); //accounts for empty tree
        rInsert(root, value); //otheerwise much traverse tree starting with root node
    }

    private Node rInsert(Node currentNode, int value) {
        if (currentNode == null) return new Node(value); // i thinnk the first line in public rInsert method isn't
        // needed since this will also handle empty tree and leaf pointing to null. this whole method will loop just
        // as the rContains method does until null pointer is found and creates the new Node and returns it to the last
        // recursion call where currentNode.left/right is set to the returned value of the recursion which in whatever
        // the value of the node is we just created. but the call stack is not empty at this point. the currenet node
        // still gets returned  which again is set to .left / .right etc. (which it already was pointing to) until we
        // pop off the recursive cases all the way back to the first item in the call stack when we get to root
        // which gets returned to the public rInsert method and the call stack is emptied

        if (value < currentNode.value) {
            currentNode.left = rInsert(currentNode.left, value);
        }
        else if (value > currentNode.value) {
            currentNode.right = rInsert(currentNode.right, value);
        }
        return  currentNode;
    }



}

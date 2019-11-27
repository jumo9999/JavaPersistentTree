import java.util.ArrayList;

public class InOrden {
    public static <K,V> ArrayList<Pair<K,V>> inOrden(LinkedBinarySearchTree<K,V> tree) {
        ArrayList<Pair<K, V>> List = new ArrayList<>();
        LinkedStack<LinkedBinarySearchTree<K,V>> linkedStack = new LinkedStack<>();
        addLeftBranch(linkedStack, tree);
        while (!linkedStack.isEmpty()){
            LinkedBinarySearchTree<K,V> currentTree = linkedStack.top();
            linkedStack.pop();
            addLeftBranch(linkedStack, currentTree.right());
            List.add(currentTree.root());
        }
        return List;
    }
    private static <K,V> void addLeftBranch(LinkedStack<LinkedBinarySearchTree<K,V>> stack, LinkedBinarySearchTree<K,V> tree){
        LinkedBinarySearchTree<K,V> currentTree = tree;
        while(!currentTree.isEmpty()){
            stack.push(currentTree);
            currentTree = currentTree.left();
        }
    }
}

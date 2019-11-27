import com.sun.source.tree.BinaryTree;

import javax.swing.*;
import java.util.Comparator;

public class LinkedBinarySearchTree<K,V> implements BinarySearchTree<K,V>, BinnaryTree<Pair<K,V>> {

    private final Node<K, V> root;
    private final Comparator<K> comparator;

    private static class Node<K, V> {
        private final K key;
        private final V value;
        private final Node<K, V> left;
        private final Node<K, V> right;

        private Node(K key, V value, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public boolean hasBothChild() {
            return hasLeftChild()&& hasRightChild();
        }

        private boolean hasLeftChild() {
            return this.left != null;
        }
        private boolean hasRightChild() {
            return this.right!=null;
        }
    }

    private LinkedBinarySearchTree(Node<K, V> root, Comparator<K> comparator) {
        this.root = root;
        this.comparator = comparator;
    }

    public LinkedBinarySearchTree(Comparator<K> comparator) {
        this.root = null;
        this.comparator = comparator;
    }
    
    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public Pair<K,V> root(){
        return null;
    }
    @Override
    public LinkedBinarySearchTree<K,V> left(){
        if(this.root.left == null){
            return new LinkedBinarySearchTree<>(comparator);
        }
        return new LinkedBinarySearchTree<>(root.left, comparator);
    }
    @Override
    public LinkedBinarySearchTree<K,V> right(){
        if(this.root.right == null){
            return new LinkedBinarySearchTree<>(comparator);
        }
        return new LinkedBinarySearchTree<>(root.right, comparator);
    }
    @Override
    public boolean containsKey(K key) {
        return contains(root, key);
    }

    private boolean contains(Node<K,V> node, K key){
        if(node == null){
            return false;
        }else {
            if (node.key == key) {
                return true;
            } else {
                if (comparator.compare(node.key, key) > 0) {
                    return contains(node.left, key);
                } else if (comparator.compare(node.key, key) < 0) {
                    return contains(node.right, key);
                } else {
                    return false;
                }
            }
        }
    }
    @Override
    public V get(K key) {
        if(!containsKey(key)){
            return null;
        }
        return getV(root, key);

    }

    private V getV(Node<K,V> root, K key) {
        if (root.key == key) {
            return root.value;
        } else {
            if (comparator.compare(root.key, key) > 0) {
                return getV(root.left, key);
            } else if (comparator.compare(root.key, key) < 0) {
                return getV(root.right, key);
            } else {
                return null;
            }
        }
    }

    @Override
    public LinkedBinarySearchTree<K, V> put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException();
        } else {
            Node<K, V> newNode = new Node<K, V>(key, value, null, null);
            if (this.isEmpty()) {
                return new LinkedBinarySearchTree<>(newNode, this.comparator);
            } else {
                return addNode(newNode);
            }
        }
    }

    private LinkedBinarySearchTree<K, V> addNode(Node<K, V> newNode) {

        if(comparator.compare(root.key, newNode.key) == 0){
            Node<K,V> newRoot = new Node<>(newNode.key, newNode.value, this.root.left, this.root.right);
            return new LinkedBinarySearchTree<>(newRoot, comparator);
        }else if(comparator.compare(root.key, newNode.key)>0){
            Node<K,V> newRoot = new Node<>(this.root.key, this.root.value, LinkWithTheNext(root.left, newNode), this.root.right);
            return new LinkedBinarySearchTree<>(newRoot, comparator);
        }
        Node<K,V> newRoot = new Node<>(this.root.key, this.root.value, this.root.left, LinkWithTheNext(root.right, newNode));
        return new LinkedBinarySearchTree<>(newRoot, comparator);
    }
    private Node<K,V> LinkWithTheNext(Node<K,V> n1, Node<K,V> newNode){
        if(n1 == null){
            return newNode;
        }
        if(comparator.compare(n1.key, newNode.key) == 0){
            return new Node<>(newNode.key, newNode.value, n1.left, n1.right);
        }else if(comparator.compare(n1.key, newNode.key)>0) {
            return new Node<>(n1.key, n1.value, LinkWithTheNext(n1.left,newNode), n1.right);
        }
        return new Node<>(n1.key,n1.value,n1.left,LinkWithTheNext(n1.right, newNode));

    }

    @Override
    public LinkedBinarySearchTree<K, V> remove(K key) {
        if(key == null){
            throw new NullPointerException();
        }else if(!containsKey(key)){
            return this;
        }else{
            return new LinkedBinarySearchTree<K,V>(RemoveNode(root, key), comparator);
        }
    }
    private Node<K,V> RemoveNode(Node<K,V> node, K key){
        if(comparator.compare(node.key, key) == 0){
            if(node.hasBothChild()){
                Node<K,V> MaxNode = Max(node.left);
                Node<K,V> RemovedNode = RemoveNode(node.left, MaxNode.key);
                return new Node<>(MaxNode.key, MaxNode.value, RemovedNode, node.right);
            }else if(node.hasLeftChild()){
                return node.left;
            }else if(node.hasRightChild()){
                return node.right;
            }
            return null;
        }else if(comparator.compare(node.key, key) >0){
            return new Node<>(node.key, node.value, RemoveNode(root.left, key), node.right);
        }
        return new Node<>(node.key, node.value, node.left, RemoveNode(root.right, key));


    }
    private Node<K,V> Max(Node<K,V> node) {
        Node<K,V> currentNode = node;
        while(currentNode.right != null){
            currentNode = currentNode.right;
        }
        return currentNode;
    }

}

import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class LinkedBinnarySearchTreeTest {

    @Test
    public void isEmptyTest(){
        Comparator<Integer> comp = new IntComparator();
        LinkedBinarySearchTree<Integer, Integer> Tree1 = new LinkedBinarySearchTree<>(comp);
        LinkedBinarySearchTree<Integer, Integer> T = Tree1.put(1,3);
        Assert.assertFalse(T.isEmpty());
        LinkedBinarySearchTree<Integer, Integer> Tree2 = new LinkedBinarySearchTree<>(comp);
        Assert.assertTrue(Tree2.isEmpty());
    }
    @Test
    public void ContainsTest(){
        Comparator<Integer> comp = new IntComparator();
        LinkedBinarySearchTree<Integer, Integer> Tree1 = new LinkedBinarySearchTree<>(comp);
        LinkedBinarySearchTree<Integer, Integer> T = Tree1.put(1,3);
        Assert.assertFalse(T.containsKey(3));
        LinkedBinarySearchTree<Integer, Integer> T2 = new LinkedBinarySearchTree<>(comp);
        Assert.assertFalse(T2.containsKey(1));
        LinkedBinarySearchTree<Integer, Integer> T3 = new LinkedBinarySearchTree<>(comp);
        T3 = T3
            .put(4,40)
            .put(2,20)
            .put(9,90)
            .put(1,10)
            .put(8,80)
            .put(14,140)
            .put(50,500);
        Assert.assertTrue(T3.containsKey(8));
        Assert.assertTrue(T3.containsKey(4));
        Assert.assertTrue(T3.containsKey(1));
        Assert.assertTrue(T3.containsKey(14));
        Assert.assertFalse(T3.containsKey(30));

    }
    @Test
    public void GetTest(){
        Comparator<Integer> comp = new IntComparator();
        LinkedBinarySearchTree<Integer, String> T3 = new LinkedBinarySearchTree<>(comp);
        T3 = T3
                .put(4,"Josep")
                .put(2,"Maria")
                .put(9,"Joan")
                .put(1,"Anna")
                .put(8,"Marc")
                .put(14,"Pau")
                .put(50,"Alex");
        String i = T3.get(4);
        Assert.assertEquals("Josep", i);
        String j = T3.get(8);
        Assert.assertEquals("Marc", j);
        Assert.assertNull(T3.get(100));
    }
    @Test
    public void updateTest(){
        Comparator<Integer> comp = new IntComparator();
        LinkedBinarySearchTree<Integer,String> tree1 = new LinkedBinarySearchTree<>(comp);
        LinkedBinarySearchTree<Integer,String> tree2 = new LinkedBinarySearchTree<>(comp);
        tree1 = tree1
                .put(4,"Josep")
                .put(2,"Maria")
                .put(9,"Joan");
        String nom = tree1.get(9);
        Assert.assertEquals("Joan", nom);
        tree1 = tree1.put(9,"Arnau");
        String nom2 = tree1.get(9);
        Assert.assertEquals("Arnau", nom2);

    }
    @Test
    public void removeTest(){
        Comparator<Integer> comp = new IntComparator();
        LinkedBinarySearchTree<Integer,String> tree1 = new LinkedBinarySearchTree<>(comp);
        tree1 = tree1
                .put(4,"Josep")
                .put(2,"Maria")
                .put(9,"Joan")
                .put(1,"Arnau")
                .put(3,"MArc")
                .put(8,"Aleu")
                .put(10,"Jordi")
                .put(14,"Nora");
        Assert.assertTrue(tree1.containsKey(9));
        tree1 =  tree1.remove(9);
        Assert.assertFalse(tree1.containsKey(9));
        Assert.assertTrue(tree1.containsKey(4));
    }
    @Test
    public void inordenTest(){

        Comparator<Integer> comp = new IntComparator();
        LinkedBinarySearchTree<Integer,String> tree = new LinkedBinarySearchTree<>(comp);
        tree = tree
                .put(4,"Josep")
                .put(2,"Maria")
                .put(9,"Joan")
                .put(1,"Arnau")
                .put(3,"MArc")
                .put(8,"Aleu")
                .put(10,"Jordi")
                .put(14,"Nora");
        ArrayList<Pair<Integer,String>> inOrden = InOrden.inOrden(tree);
        Iterator<Pair<Integer,String>> iter = inOrden.iterator();
        /*int i1 = iter.next().first();
        int i2;
        while(iter.hasNext()) {
            i2 = iter.next().first();
            if(i1 >= i2){
                Assert.fail();
            }
            i1 = i2;
        }*/
    }
}

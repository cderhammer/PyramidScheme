package pyramid_scheme;

import DataStructures.MultiTreeNode;
import Exceptions.ElementNotFoundException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Solution Test file for LinkedTree
 *
 * @author pmele
 * @version 3/28/2019
 */
public class LinkedTreeTest {

    private LinkedTree<String> instance;
    private MultiTreeNode<String> root;
    private String s01;
    private String s02;
    private String s03;
    private String s04;
    private String s05;

    /**
     * Sets up the later tests.
     */
    @Before
    public void setUp() {
        s01 = "Elem 1";
        s02 = "Elem 2";
        s03 = "Elem 3";
        s04 = "Elem 4";
        s05 = "Elem 5";
        root = new MultiTreeNode<>(s01);
        instance = new LinkedTree<>(root);
    }

    /**
     * Test of getRootElement method, of class LinkedTree.
     */
    @Test
    public void testGetRootElement() {
        System.out.println("testGetRootElement");

        try {
            // add children to root
            instance.addChild(s01, s02);
            instance.addChild(s02, s03);
            // assert that s01 is the correct root element
            assertEquals(s01, instance.getRootElement());
        } catch (ElementNotFoundException enf) {
            System.out.println("No such element");
        }
    }

    /**
     * Test of addChild method, of class LinkedTree.
     */
    @Test
    public void testAddChild() {
        System.out.println("testAddChild");

        try {
            // If parent element does not exist, ElementNotFoundException is thrown
            if (root == null) {
                throw new ElementNotFoundException("No such element");
            }
            // add children to root
            instance.addChild(root, s01);
            instance.addChild(root, s02);
            // asserts that findNode() can get the element at s01
            assertEquals(s01, instance.findNode(s01).getElement());
            // testing validity of the size
            assertEquals(instance.size(), 3);
            // asserts that findNode() can get the element at s02
            assertEquals(s02, instance.findNode(s02).getElement());
            // add another child
            instance.addChild(s02, s04);
            // check to make sure the size of the tree increased
            assertEquals(instance.size(), 4);
            // check to find an element deeper in the tree
            assertEquals(s04, instance.findNode(s04).getElement());

        } catch (ElementNotFoundException enf) {
            System.out.println("No such element");
        }
    }

    /**
     * Test of findNode method, of class LinkedTree.
     */
    @Test
    public void testFindNode() {
        System.out.println("testFindNode");
        try {
            //Can find root
            assertEquals(s01, instance.findNode(s01).getElement());
            instance.addChild(s01, s02);
            //System.out.println("Successfully added " + s02 + " to " + s01);
            //Can find a child node
            assertEquals(s02, instance.findNode(s02).getElement());
            instance.addChild(s01, s03); //Add several deep
            instance.addChild(s02, s04);
            instance.addChild(s04, s05);
            //Can find a child node deep within tree
            assertEquals(s05, instance.findNode(s05).getElement());
            //Trying to find things not in the tree returns null
            assertEquals(null, instance.findNode("Unreal element"));
        } catch (Exception ex) {
            System.out.println("Unexpected Exception");
        }
    }

    /**
     * Test of contains method, of class LinkedTree.
     */
    @Test
    public void testContains() {
        System.out.println("testContains");

        try {
            // add children to parent nodes
            instance.addChild(s01, s02);
            instance.addChild(s01, s03);
            // testing size
            assertEquals(instance.size(), 3);
            // add another child to a parent node to check for edge case
            instance.addChild(s02, s04);
            // test size adjustment
            assertEquals(instance.size(), 4);
            // if instance contains nodes
            if (instance != null) {
                // if target is in tree, true is returned
                assertTrue(instance.contains(s04));
            } else {
                // if target is not in tree, false is returned
                assertFalse(instance.contains(s05));
            }
        } catch (ElementNotFoundException enf) {
            System.out.println("No such element");
        }
    }

    /**
     * Test of size method, of class LinkedTree.
     */
    @Test
    public void testSize() {
        System.out.println("testSize");
        try {
            // if root is null, size is 0
            if (root == null) {
                assertEquals(instance.size(), 0);
            }
            // add children to parent node
            instance.addChild(s01, s02);
            instance.addChild(s01, s03);
            // test validity
            assertEquals(instance.size(), 3);
            assertEquals(instance.getRootElement(), s01);
            // add more children to parent node for edge case
            instance.addChild(s02, s04);
            instance.addChild(s02, s05);
            // test validity of edge case
            assertEquals(instance.size(), 5);

        } catch (ElementNotFoundException enf) {
            System.out.println("No such element");
        }
    }

}

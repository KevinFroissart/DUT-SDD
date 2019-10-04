/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.
 */

/**
 * SDD Seance TP 4 : Tests pour la classe SortedArraySet
 *
 * @author <a href="mailto:Frederic.Guyomarch@univ-lille1.fr">Frédéric Guyomarch</a>
 * IUT-A, Universite de Lille, Sciences et Technologies
 */
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;

public class SortedArraySetTest extends junit.framework.TestCase {
    public static class ReversedIntegerComparator implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            return -(o1.compareTo(o2));
        }
/*        public boolean equals(Object o1, Object o2) {
            return ((Integer) o1).compareTo((Integer) o2) == 0;
        }*/
    }
    
    SortedArraySet<Integer> ts;
    Integer[] intArray = new Integer[1000];
    
    /**
     * java.util.SortedArray#SortedArray()
     */
    public void test_Constructor() {
        // Test for method java.util.SortedArray()
        assertTrue("Did not construct correct SortedArray", new SortedArraySet<>().isEmpty());
    }
    
    /**
     * java.util.SortedArray#SortedArray(java.util.Collection)
     */
    public void test_ConstructorLjava_util_Collection() {
        // Test for method java.util.SortedArray(java.util.Collection)
        SortedArraySet<Integer> mySortedArray = new SortedArraySet<>(Arrays.asList(intArray));
        assertTrue("SortedArray incorrect size",
                mySortedArray.size() == intArray.length);
        for (int counter = 0; counter < intArray.length; counter++)
            assertTrue("SortedArray does not contain correct elements", mySortedArray
                    .contains(intArray[counter]));
    }
    
    /**
     * java.util.SortedArray#SortedArray(java.util.Comparator)
     */
    public void test_ConstructorLjava_util_Comparator() {
        // Test for method java.util.SortedArray(java.util.Comparator)
        SortedArraySet<Integer> mySortedArray = new SortedArraySet<>(new ReversedIntegerComparator());
        assertTrue("Did not construct correct SortedArray", mySortedArray.isEmpty());
        mySortedArray.add(new Integer(1));
        mySortedArray.add(new Integer(2));
        assertTrue(
                "Answered incorrect first element--did not use custom comparator ",
                mySortedArray.first().equals(new Integer(2)));
        assertTrue(
                "Answered incorrect last element--did not use custom comparator ",
                mySortedArray.last().equals(new Integer(1)));
    }
    
    /**
     * java.util.SortedArray#SortedArray(java.util.SortedSet)
     */
    public void test_ConstructorLjava_util_SortedSet() {
        // Test for method java.util.SortedArray(java.util.SortedSet)
        ReversedIntegerComparator comp = new ReversedIntegerComparator();
        SortedArraySet<Integer> mySortedArray = new SortedArraySet<>(comp);
        for (int i = 0; i < intArray.length; i++)
            mySortedArray.add(intArray[i]);
        SortedArraySet<Integer> anotherSortedArray = new SortedArraySet<>(mySortedArray);
        assertTrue("SortedArray is not correct size",
                anotherSortedArray.size() == intArray.length);
        for (int counter = 0; counter < intArray.length; counter++)
            assertTrue("SortedArray does not contain correct elements",
                    anotherSortedArray.contains(intArray[counter]));
        assertTrue("SortedArray does not answer correct comparator", anotherSortedArray
                .comparator() == comp);
        assertTrue("SortedArray does not use comparator",
                anotherSortedArray.first() == intArray[intArray.length - 1]);
    }
    
    /**
     * java.util.SortedArray#add(java.lang.Object)
     */
    public void test_addLjava_lang_Object() {
        // Test for method boolean java.util.SortedArray.add(java.lang.Object)
        ts.add(new Integer(-8));
        assertTrue("Failed to add Object", ts.contains(new Integer(-8)));
        ts.add(intArray[0]);
        assertTrue("Added existing element", ts.size() == intArray.length + 1);
    }
    
    /**
     * java.util.SortedArray#addAll(java.util.Collection)
     */
    public void test_addAllLjava_util_Collection() {
        // Test for method boolean
        // java.util.SortedArray.addAll(java.util.Collection)
        SortedArraySet<Integer> s = new SortedArraySet<>();
        s.addAll(ts);
        assertTrue("Incorrect size after add", s.size() == ts.size());
        Iterator<Integer> i = ts.iterator();
        while (i.hasNext())
            assertTrue("Returned incorrect set", s.contains(i.next()));
    }
    /**
     * java.util.SortedArray#clear()
     */
    public void test_clear() {
        // Test for method void java.util.SortedArray.clear()
        ts.clear();
        assertEquals("Returned non-zero size after clear", 0, ts.size());
        assertTrue("Found element in cleared set", !ts.contains(intArray[0]));
    }
    /**
     * java.util.SortedArray#clone()
     */
    public void test_clone() {
        // Test for method java.lang.Object java.util.SortedArray.clone()
        SortedArraySet<Integer> s = (SortedArraySet<Integer>) ts.clone();
        Iterator<Integer> i = ts.iterator();
        while (i.hasNext())
            assertTrue("Clone failed to copy all elements", s
                    .contains(i.next()));
    }
    /**
     * java.util.SortedArray#comparator()
     */
    public void test_comparator() {
        // Test for method java.util.Comparator java.util.SortedArray.comparator()
        ReversedIntegerComparator comp = new ReversedIntegerComparator();
        SortedArraySet<?> mySortedArray = new SortedArraySet<>(comp);
        assertTrue("Answered incorrect comparator",
                mySortedArray.comparator() == comp);
    }
    /**
     * java.util.SortedArray#contains(java.lang.Object)
     */
    public void test_containsLjava_lang_Object() {
        // Test for method boolean java.util.SortedArray.contains(java.lang.Object)
        assertTrue("Returned false for valid Object", ts
                .contains(intArray[intArray.length / 2]));
        assertTrue("Returned true for invalid Object", !ts
                .contains(new Integer(-9)));
        try {
            ts.contains(new Object());
        } catch (ClassCastException e) {
            // Correct
            return;
        }
        fail("Failed to throw exception when passed invalid element");
    }
    /**
     * java.util.SortedArray#first()
     */
    public void test_first() {
        // Test for method java.lang.Object java.util.SortedArray.first()
        assertTrue("Returned incorrect first element",
                ts.first() == intArray[0]);
    }
    /**
     * java.util.SortedArray#headSet(java.lang.Object)
     */
    public void test_headSetLjava_lang_Object() {
        // Test for method java.util.SortedSet
        // java.util.SortedArray.headSet(java.lang.Object)
        Set<Integer> s = ts.headSet(new Integer(100));
        assertEquals("Returned set of incorrect size", 100, s.size());
        for (int i = 0; i < 100; i++)
            assertTrue("Returned incorrect set", s.contains(intArray[i]));
    }
    /**
     * java.util.SortedArray#isEmpty()
     */
    public void test_isEmpty() {
        // Test for method boolean java.util.SortedArray.isEmpty()
        assertTrue("Empty set returned false", new SortedArraySet<>().isEmpty());
        assertTrue("Non-Empty returned true", !ts.isEmpty());
    }
    /**
     * java.util.SortedArray#iterator()
     */
    public void test_iterator() {
        // Test for method java.util.Iterator java.util.SortedArray.iterator()
        SortedArraySet<Integer> s = new SortedArraySet<>();
        s.addAll(ts);
        Iterator<Integer> i = ts.iterator();
        Set<Integer> as = new HashSet<>(Arrays.asList(intArray));
        while (i.hasNext())
            as.remove(i.next());
        assertEquals("Returned incorrect iterator", 0, as.size());
    }
    /**
     * java.util.SortedArray#last()
     */
    public void test_last() {
        // Test for method java.lang.Object java.util.SortedArray.last()
        assertTrue("Returned incorrect last element",
                ts.last() == intArray[intArray.length - 1]);
    }
    /**
     * java.util.SortedArray#remove(java.lang.Object)
     */
    public void test_removeLjava_lang_Object() {
        // Test for method boolean java.util.SortedArray.remove(java.lang.Object)
        ts.remove(intArray[0]);
        assertTrue("Failed to remove object", !ts.contains(intArray[0]));
        assertTrue("Failed to change size after remove",
                ts.size() == intArray.length - 1);
        try {
            ts.remove(new Object());
        } catch (ClassCastException e) {
            // Correct
            return;
        }
        fail("Failed to throw exception when past uncomparable value");
    }
    /**
     * java.util.SortedArray#size()
     */
    public void test_size() {
        // Test for method int java.util.SortedArray.size()
        assertTrue("Returned incorrect size", ts.size() == intArray.length);
    }
    /**
     * java.util.SortedArray#subSet(java.lang.Object, java.lang.Object)
     */
    public void test_subSetLjava_lang_ObjectLjava_lang_Object() {
        // Test for method java.util.SortedSet
        // java.util.SortedArray.subSet(java.lang.Object, java.lang.Object)
        final int startPos = intArray.length / 4;
        final int endPos = 3 * intArray.length / 4;
        SortedSet<Integer> aSubSet = ts.subSet(intArray[startPos], intArray[endPos]);
        assertTrue("Subset has wrong number of elements",
                aSubSet.size() == (endPos - startPos));
        for (int counter = startPos; counter < endPos; counter++)
            assertTrue("Subset does not contain all the elements it should",
                    aSubSet.contains(intArray[counter]));
        int result;
        try {
            ts.subSet(intArray[3], intArray[0]);
            result = 0;
        } catch (IllegalArgumentException e) {
            result = 1;
        }
        assertEquals("end less than start should throw", 1, result);
    }
    /**
     * java.util.SortedArray#tailSet(java.lang.Object)
     */
    public void test_tailSetLjava_lang_Object() {
        // Test for method java.util.SortedSet
        // java.util.SortedArray.tailSet(java.lang.Object)
        Set<Integer> s = ts.tailSet(new Integer(900));
        assertEquals("Returned set of incorrect size", 100, s.size());
        for (int i = 900; i < intArray.length; i++)
            assertTrue("Returned incorrect set", s.contains(intArray[i]));
    }
    /**
     * Tests equals() method.
     * Tests that no ClassCastException will be thrown in all cases.
     * Regression test for HARMONY-1639.
     */
    public void test_equals() throws Exception {
        // comparing SortedArrays with different object types
        Set<String> s1 = new SortedArraySet<>();
        Set<Integer> s2 = new SortedArraySet<>();
        s1.add("key1");
        s1.add("key2");
        s2.add(new Integer(1));
        s2.add(new Integer(2));
        assertFalse("Sets should not be equal 1", s1.equals(s2));
        assertFalse("Sets should not be equal 2", s2.equals(s1));
        // comparing SortedArray with HashSet
        s1 = new SortedArraySet<>();
        s2 = new HashSet<>();
        s1.add("key");
        s2.add(new Integer(0));
        assertFalse("Sets should not be equal 3", s1.equals(s2));
        assertFalse("Sets should not be equal 4", s2.equals(s1));
        // comparing SortedArrays with not-comparable objects inside
        s1 = new SortedArraySet<>();
        s2 = new SortedArraySet<>();
        s1.add(new String());
        s2.add(new Integer(0));
        assertFalse("Sets should not be equal 5", s1.equals(s2));
        assertFalse("Sets should not be equal 6", s2.equals(s1));
    }
    /**
     * Sets up the fixture, for example, open a network connection. This method
     * is called before a test is executed.
     */
    protected void setUp() {
        ts = new SortedArraySet<>();
        for (int i = 0; i < intArray.length; i++) {
            Integer x = intArray[i] = new Integer(i);
            ts.add(x);
        }
    }
    /**
     * Tears down the fixture, for example, close a network connection. This
     * method is called after a test is executed.
     */
    protected void tearDown() {
    }
}

package tp01;

/**
 * SDD Seance TP 1 :
 *
 * @author <a href="mailto:Frederic.Guyomarch@univ-lille1.fr">Frédéric Guyomarch</a>
 * IUT-A, Universite de Lille, Sciences et Technologies
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class SortTest {
	final int MIN = 8;
	final int MAX = 99;

	final int [] SORTED = {8,11,17,22,25,34,47,52,69,98};
	int [] unSorted = {22,11,8,17,47,98,25,34,69,52};

	@Test
	public void testGenerateRdmIntArray() {

		int [] rdmTab = Sort.generateRdmIntArray(100,MIN,MAX);
		int i = 0;
		boolean well = true;
		while(i<rdmTab.length&&well){
			if(rdmTab[i] < MIN || rdmTab[i] > MAX)
				well = false;
			i++;
		}
		assertTrue(well);
	}

	@Before
    public void setUp() {
      unSorted = new int[]{22,11,8,17,47,98,25,34,69,52};
    }

	@Test
	public void testSwap() {
		int [] before = {8,11,17,22,25,34,47,52,69,98};
		int [] after = {8,69,17,22,25,34,47,52,11,98};
		Sort.swap(before, 1, 8);
		assertArrayEquals(before, after);
	}

	@Test
	public void testBubbleSort() {
		Sort.bubbleSort(unSorted);
		assertArrayEquals(SORTED, unSorted);
	}


	@Test
	public void testInsertSort() {
		Sort.insertSort(unSorted);
		assertArrayEquals(SORTED, unSorted);
	}

	@Test
	public void testSelectSort() {
		Sort.selectSort(unSorted);
		assertArrayEquals(SORTED, unSorted);
	}

	/*@Test
	public void testfuseTab() {
		int [] tabA = {8,11,47,98};
		int [] tabB = {17,22,25,34,52,69};
		int [] tabOut = new int[tabA.length+tabB.length];
		Sort.fuseTab(tabA, tabB, tabOut);		
		assertArrayEquals(tabOut, SORTED);
	}*/





}

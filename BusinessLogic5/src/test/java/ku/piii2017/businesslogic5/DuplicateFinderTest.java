/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2017.businesslogic5;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author James
 */
public class DuplicateFinderTest {

    /**
     * Test of getAllButNoDuplicates method, of class DuplicateFinder.
     */
    //@Ignore
    @Test
    public void testGetAllButNoDuplicates_Question1() {
        System.out.println("getAllButNoDuplicates -- Question 1");
        Set<MediaItem> inputSet = TestHelper.getStandardSet();
        inputSet.addAll(TestHelper.getAllBillEvans1());
        inputSet.addAll(TestHelper.getAllBudPowell1());
        inputSet.add(TestHelper.oscarPeterson1b());
        
        DuplicateFinder instance =  new DuplicateFindFromID3();
        
        Set<MediaItem> expResult = TestHelper.getStandardSet();
        Set<MediaItem> result = instance.getAllButNoDuplicates(inputSet);
        assertSameMedia(expResult, result);
    }
    

    /**
     * Test of getAllButNoDuplicates method, of class DuplicateFinder.
     */
    //@Ignore
    @Test
    public void testGetAllButNoDuplicates_Question4a() {
        System.out.println("getAllButNoDuplicates -- Question 4a");
        Set<MediaItem> inputSet1 = new HashSet<>();        
        inputSet1.addAll(TestHelper.getAllBillEvans1());
        inputSet1.addAll(TestHelper.getAllBudPowell1());
        inputSet1.add(TestHelper.oscarPeterson1b());

        Set<MediaItem> inputSet2 = TestHelper.getStandardSet();
        
        DuplicateFinder instance =  new DuplicateFindFromID3();
        
        Set<MediaItem> expResult = TestHelper.getStandardSet();
       // System.out.println("Expected result =" + expResult);
        Set<MediaItem> result = instance.getAllButNoDuplicates(inputSet1, inputSet2);
        assertSameMedia(expResult, result);
    }

    /**
     * Test of getAllButNoDuplicates method, of class DuplicateFinder.
     */
   // @Ignore
    @Test
    public void testGetAllButNoDuplicates_Question4b() {
        System.out.println("getAllButNoDuplicates -- Question 4b");
        
        Set<MediaItem> inputSet = new HashSet<>();        
        inputSet.addAll(TestHelper.getAllBillEvans1());
        inputSet.addAll(TestHelper.getAllBudPowell1());
        inputSet.add(TestHelper.oscarPeterson1b());
        
        List<Set<MediaItem>> allInput  = new ArrayList<>();
        allInput.add(TestHelper.getStandardSet());
        allInput.add(inputSet);
        allInput.add(TestHelper.getStandardSetB());        
        DuplicateFinder instance =  new DuplicateFindFromID3();        
        Set<MediaItem> expResult = TestHelper.getStandardSet();
        Set<MediaItem> result = instance.getAllButNoDuplicates(allInput);
        assertSameMedia(expResult, result);
    }

    
    public DuplicateFinderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }


    @Before
    public void setUp() {
        
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllDuplicates method, of class DuplicateFindFromFilename.
     */
    @Test
    public void testGetAllDuplicates() {
        DuplicateFinder instance =  new DuplicateFindFromID3();
        System.out.println("getAllDuplicates");
        
        Set<MediaItem> allMediaItems = TestHelper.getStandardSet();
        allMediaItems.add(TestHelper.billEvans1b());
        allMediaItems.add(TestHelper.billEvans1c());
        allMediaItems.add(TestHelper.budPowell1b());
        
        
             
        Set<Set<MediaItem>> expResult = new HashSet<>();
        expResult.add(TestHelper.getAllBillEvans1());
        expResult.add(TestHelper.getAllBudPowell1());
        
        Set<Set<MediaItem>> result = instance.getAllDuplicates(allMediaItems);
        
        System.out.println("the expected result:");
        Workshop5TestHelper.print1(expResult);
        System.out.println("the actual result:");
        Workshop5TestHelper.print1(result);

        assertEquals(expResult, result);
    }
    @Test
    public void testGetSingleDuplicateSet() {
        DuplicateFinder instance =  new DuplicateFindFromID3();
        System.out.println("getSingleDuplicateSet");

        Set<MediaItem> allMediaItems = TestHelper.getStandardSet();
        allMediaItems.addAll(TestHelper.getAllBillEvans1());
        allMediaItems.addAll(TestHelper.getAllBudPowell1());

        Set<Set<MediaItem>> testSets = new HashSet<>();
        testSets.add(TestHelper.getAllBillEvans1());
        testSets.add(TestHelper.getAllBudPowell1());

        
        for(Set<MediaItem> expectedOutput : testSets) {
            MediaItem testInputItem = expectedOutput.iterator().next();
            Set<MediaItem> actualOutput = instance.getSingleDuplicateSet(allMediaItems, testInputItem);    
            assertEquals(expectedOutput,  actualOutput);
        }
    }
    @Test
    public void testTheseTwoAreDuplicates() {
        DuplicateFinder instance =  new DuplicateFindFromID3();
        System.out.println("getSingleDuplicateSet");
        
        List<MediaItem> list1 = TestHelper.getStandardList();
        List<MediaItem> list2 = TestHelper.getStandardListB();
        List<MediaItem> list3 = TestHelper.getStandardListToFail();
        
        boolean expectedReturnValue;
        int n;
        
        n= list2.size();
        expectedReturnValue = true;        
        for(int i = 0;i<n;i++)
        {
            boolean actualReturnValue = instance.theseTwoAreDuplicates(list1.get(i),
                                                                       list2.get(i));            
            String comment = "class is " + instance.getClass() + " and index is " + i;
            assertEquals(comment, expectedReturnValue, actualReturnValue);
        }
        n= list3.size();
        expectedReturnValue = false;        
        for(int i = 0;i<n;i++)
        {
            boolean actualReturnValue = instance.theseTwoAreDuplicates(list1.get(i),
                                                                       list3.get(i));            
            String comment = "class is " + instance.getClass() + " and index is " + i;
            assertEquals(comment, expectedReturnValue, actualReturnValue);
        }
    }


 
    private void assertSameMedia(Set<MediaItem> expResult, Set<MediaItem> result) {
        DuplicateFinder instance =  new DuplicateFindFromID3();
        for(MediaItem expItem : expResult)
        {
            System.out.println("We should have item " + expItem + "do we?");
            Set<MediaItem> shouldBeOne = instance.getSingleDuplicateSet(result, expItem);
            if(shouldBeOne.size()!=1)
            {
                System.out.println("eek");
            }
            assertEquals(shouldBeOne.size(), 1);
        }
        for(MediaItem item : result)
        {
            
            System.out.println("We do have item " + item  + "is this ok?");
            Set<MediaItem> shouldBeOne = instance.getSingleDuplicateSet(expResult, item);
            if(shouldBeOne.size()!=1)
            {
                System.out.println("eek2");
            }
            assertEquals(shouldBeOne.size(), 1);
        }
    }

    /**
     * Test of getMissingItems method, of class DuplicateFinder.
     */
    @Test
    public void testGetMissingItems() {
        System.out.println("getMissingItems");
        Set<MediaItem> myCollection = new HashSet<>();        
        myCollection.addAll(TestHelper.getAllBillEvans1());
        myCollection.addAll(TestHelper.getAllBudPowell1());
        myCollection.add(TestHelper.oscarPeterson1b());
        Set<MediaItem> yourCollection = TestHelper.getStandardSet();
        DuplicateFinder instance =  new DuplicateFindFromID3();
        Set<MediaItem> expResult = new HashSet<>(); 
        expResult.add(TestHelper.billEvans2());
        Set<MediaItem> result = instance.getMissingItems(myCollection, yourCollection);
        assertEquals(expResult, result);
    }
    @Test
    public void testGetListToRemove() {
                
        System.out.println("getListToRemove");
        DuplicateFinder instance = new DuplicateFindFromID3();
        String rootTestFolder = Paths.get(Workshop5TestHelper.TEST_SCRATCH_FOLDER)
                                     .toAbsolutePath()
                                     .toString();
        
        
        Set<Set<MediaItem>> duplicates = new HashSet<>();
        duplicates.add(TestHelper.getAllBillEvans1());
        duplicates.add(TestHelper.getAllBudPowell1());
        
        Set<MediaItem> itemsToRemove = instance.getItemsToRemove(duplicates);
        
        System.out.println("the duplicates to choose from:");
        Workshop5TestHelper.print1(duplicates);
        System.out.println("the list selected to remove:");
        Workshop5TestHelper.print2(itemsToRemove);
        
        for(Set<MediaItem> thisDuplicateSet : duplicates) {
            // for each duplicate set, find out how many elements in 'itemsToRemove'
            // are the duplicates (i.e. have the same filename)
            // the answer should be #num-items-in-set - 1 
            MediaItem firstDuplicateInSet = thisDuplicateSet.iterator().next();
            int numLikeThis = 
                    Workshop5TestHelper.getNumLikeThis(firstDuplicateInSet, itemsToRemove);
            assertEquals( thisDuplicateSet.size()-1, numLikeThis);
        }
    }
 
}

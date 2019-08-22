/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2017.businesslogic5;

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
public class MediaItemTest {
    
    public MediaItemTest() {
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
     * Test of setAbsolutePath method, of class MediaItem.
     */
    //@Ignore
    @Test
        //(expected = NullPointerException.class)
    public void testSetAbsolutePathNullInput() {
       // System.out.println("setAbsolutePath");
        String absolutePath = System.getProperty("user.dir") ;
        System.out.println(absolutePath);
        String nullPath = null;
        
        MediaItem instance = new MediaItem();
        
        // test setPath returns a nullpoint exception when it should be 
        boolean nullPointException = false;
        
        try {
            
        instance.setAbsolutePath(nullPath);    
        
        }
        catch (NullPointerException e){
        
          nullPointException = true;
        }
        
        assertTrue(nullPointException);
        
        // test setPath returns a nullpoint exception when it should not be 
        
        nullPointException = false;
        
        try {
            instance.setAbsolutePath(absolutePath);
        }
        catch( NullPointerException e){
            nullPointException = true;  
        }
        
        assertFalse(nullPointException);
        
    }

    //@Ignore
    @Test
        //(expected = IllegalArgumentException.class)
    public void testSetAbsolutePathNoneAbsolute(){
        
        System.out.println("setAbsolutePath.. non absolute path");
        String absolutePath = System.getProperty("user.dir");
        String illegalPath = "//";
        System.out.println("testing......"+absolutePath);
        MediaItem instance = new MediaItem();
        
         // test setPath returns a nullpoint exception when it should be 
        boolean nullPointException = false;
        
        try {
            
        instance.setAbsolutePath(illegalPath);    
        
        }
        catch (IllegalArgumentException e){
        
          nullPointException = true;
        }
        
        assertTrue(nullPointException);
        
        // test setPath returns a nullpoint exception when it should not be 
        
        nullPointException = false;
        
        try {
            instance.setAbsolutePath(absolutePath);
        }
        catch( NullPointerException e){
            nullPointException = true;  
        }
        
        assertFalse(nullPointException);

    }


}



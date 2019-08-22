/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2017.businesslogic5;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Ignore
public class FileServiceTest {

    
    // I think we can test on original collection only
    FileStore fileStore = new FileStoreOriginalNames();

    
    public FileServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Path cwdPath = Paths.get("").toAbsolutePath();
        // remove temporary folder, if it is there
        try {
            File destFolder = new File(Paths.get(cwdPath.toString(), 
                                                Workshop5TestHelper.TEST_SCRATCH_FOLDER, 
                                                fileStore.getRootFolder()
                                                ).toString());
//            Workshop5TestHelper.deleteFolderRecursively(destFolder);    
            Workshop5TestHelper.deleteFolderWithFileVisitor(destFolder.toString());
        }
        // make the 
        catch (Exception e) {
            // no problem
            e.printStackTrace();
        }
        try {
            Path testScratchFolder = 
                Paths.get(Workshop5TestHelper.TEST_SCRATCH_FOLDER);
            Files.createDirectory(testScratchFolder);
        }
        catch (Exception e) {
            // no problem
            e.printStackTrace();
        }

        // copy permanent folder to temp location
        try {
//            Workshop5TestHelper.copyFolder(cwdPath, srcFolder, destFolder);
            Path srcPath = Paths.get(cwdPath.toString(), 
                                     Workshop5TestHelper.TEST_SRC_FOLDER,
                                     fileStore.getRootFolder());
            Path dstPath = Paths.get(cwdPath.toString(), 
                                     Workshop5TestHelper.TEST_SCRATCH_FOLDER );
            Path relDstFolder = Paths.get(fileStore.getRootFolder() );
            
            Workshop5TestHelper.copyFolder(srcPath, dstPath, relDstFolder);
        }
        catch (Exception e) {
            // problem
            e.printStackTrace();
            fail("could not create test folder");
        }
        
    }
    
    @After
    public void tearDown() {
//        Workshop5TestHelper.deleteFolderRecursively
//                    (new File(Workshop5TestHelper.TEMP_INPUT_FOLDER_FOR_ORIGINAL_FILENAMES));
            Workshop5TestHelper.
                    deleteFolderWithFileVisitor
                        (Workshop5TestHelper.TEST_SCRATCH_FOLDER);
    }

    /**
     * Test of getDuplicates method, of class FileService.
     */

    /**
     * Test of removeFiles method, of class FileService.
     */
    @Test
    public void testRemoveFiles() {
        System.out.println("removeFiles");
        assertTrue(testEquals());
        assertTrue(testHashCode());
        Set<MediaItem> listToRemove = null;
        String rootTestFolder = Paths.get(Workshop5TestHelper.TEST_SCRATCH_FOLDER)
                                     .toAbsolutePath()
                                     .toString();
        
        Set<MediaItem> allMediaItems = 
                        fileStore.getAllMediaItems(rootTestFolder, null);
        assertTrue(Workshop5TestHelper.filesExist(allMediaItems));

        FileService instance = new FileServiceImpl();
        
        Set<MediaItem> allDuplicates = new HashSet<>();
        Set<Set<MediaItem>> duplicates = 
                        fileStore.getAllDuplicates(rootTestFolder, null);
        for(Set<MediaItem> m : duplicates)
            allDuplicates.addAll(m);
        instance.removeFiles(allDuplicates);
        allMediaItems.removeAll(allDuplicates);
        assertTrue(Workshop5TestHelper.filesExist(allMediaItems));
        assertTrue(Workshop5TestHelper.filesDontExist(allDuplicates));
    }
    boolean testEquals()
    {
        MediaItem foo = new MediaItem().setAbsolutePath(System.getProperty("user.dir") + File.separator + "foo");
        MediaItem bar = new MediaItem().setAbsolutePath(System.getProperty("user.dir") + File.separator + "bar");
        
        if(foo.equals(bar)){
            System.out.println("testEquals: foo does not equal bar!");
            return false;
        }
        bar.setAbsolutePath(System.getProperty("user.dir") + File.separator + "foo");
        if(foo.equals(bar)==false){
            System.out.println("testEquals: foo does now equal bar, so .equals() method should return true!");
            return false;
        }
        return true;
    }
    boolean testHashCode()
    {
        MediaItem foo = new MediaItem().setAbsolutePath(System.getProperty("user.dir") + File.separator + "foo");
        MediaItem bar = new MediaItem().setAbsolutePath(System.getProperty("user.dir") + File.separator + "bar");
        
        int fooHashCode = foo.hashCode();
        int barHashCode = bar.hashCode();
        System.out.println("testHashCode: foo and bar different, with hashCodes of " 
        + fooHashCode + " and " + barHashCode );
        
        if(fooHashCode==barHashCode){
            System.out.println("testHashCode: slightly surprising (but not actually incorrect) for these to have the same hashcode...");
        }
        bar.setAbsolutePath(System.getProperty("user.dir") + File.separator + "foo");
        fooHashCode = foo.hashCode();
        barHashCode = bar.hashCode();
        System.out.println("testHashCode: foo and bar same, with hashCodes of " 
        + fooHashCode + " and " + barHashCode );
        if(fooHashCode!=barHashCode){
            System.out.println("testHashCode: foo and bar hashCodes must be the same...");
            return false;
        }
        return true;
    }
}

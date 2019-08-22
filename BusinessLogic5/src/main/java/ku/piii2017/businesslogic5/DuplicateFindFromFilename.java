/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2017.businesslogic5;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author James
 */
public class DuplicateFindFromFilename implements DuplicateFinder {


    @Override
    public boolean theseTwoAreDuplicates(MediaItem m1, MediaItem m2) {
        
        String filename1 = (new File(m1.getAbsolutePath())).getName();
        String filename2 = (new File(m2.getAbsolutePath())).getName();
        
        if(filename1!=null && 
           filename1.equals(filename2)) {
            return true;
        }
        return false;
    }
}

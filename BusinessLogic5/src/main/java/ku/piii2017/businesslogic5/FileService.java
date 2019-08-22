/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2017.businesslogic5;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

/**
 *
 * @author James
 */
public interface FileService {
    Set<MediaItem>          getAllMediaItems    (String rootFolder);
    boolean                 removeFiles         (Set<MediaItem> listToRemove);
    
    
}

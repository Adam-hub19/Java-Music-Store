/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2017.businesslogic5;

import static java.nio.file.Files.list;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author James
 */
public interface DuplicateFinder {
    
    // this method is implemented in DuplicateFindFromID3 and DuplicateFindFromFilename:
    boolean                 theseTwoAreDuplicates  (MediaItem m1, MediaItem m2);

    default Set<MediaItem> getAllButNoDuplicates (Set<MediaItem> inputSet)
    {
        // convert inputSet to a list so items can be retrieved by index
        List<MediaItem> inputSet2 = new ArrayList<MediaItem>(inputSet);
       
       Set<MediaItem> duplicates = new HashSet();
       
       for (int i= 0; i<inputSet2.size();i++){
           
           for (int j= i + 1; j<inputSet2.size(); j++){
               
               if (theseTwoAreDuplicates(inputSet2.get(i), inputSet2.get(j))){
                   duplicates.add(inputSet2.get(i));
               }      
           }

       }
      inputSet.removeAll(duplicates);
       
       return inputSet;
    }
    
    
    default Set<MediaItem> getAllButNoDuplicates (Set<MediaItem> inputSet1, 
                                                  Set<MediaItem> inputSet2)
    {
        //throw new UnsupportedOperationException("this method not yet written");
        
        //Store both input sets into one set
        Set<MediaItem> possibleDuplcates = inputSet1;
        possibleDuplcates.addAll(inputSet2);
        
       return getAllButNoDuplicates(possibleDuplcates);
        
    }
    default Set<MediaItem> getAllButNoDuplicates (List<Set<MediaItem>> inputSets)
    {
        Set<MediaItem> allItems = new HashSet();
        
        
        for (Set inputSet: inputSets){
              allItems.addAll(inputSet);
        }
        
        return getAllButNoDuplicates(allItems);
    }
    
    
    default Set<MediaItem> getMissingItems(Set<MediaItem> myCollection,
            Set<MediaItem> yourCollection) {
        Set<MediaItem> missingItems = new HashSet<>();
        for (MediaItem item : yourCollection) {
            Set<MediaItem> duplicates1 = getSingleDuplicateSet(myCollection, item);
            if (duplicates1.isEmpty()) {
                Set<MediaItem> duplicates2 = getSingleDuplicateSet(missingItems, item);
                if (duplicates2.isEmpty()) {
                    missingItems.add(item);
                }
            }
        }
        return missingItems;
    }
    default Set<Set<MediaItem>> getAllDuplicates(Set<MediaItem> allMediaItems) {

        Set<Set<MediaItem>> allDuplicates = new HashSet<>();        
        Set<MediaItem> possibleDuplicates = new HashSet<>();
        possibleDuplicates.addAll(allMediaItems);

        while(possibleDuplicates.iterator().hasNext())
        {
            MediaItem m = possibleDuplicates.iterator().next();
            Set<MediaItem> duplicates = getSingleDuplicateSet(possibleDuplicates, m);
            if(duplicates.size()>1) {
                allDuplicates.add(duplicates);
            }
            possibleDuplicates.removeAll(duplicates);
        }
        return allDuplicates;
    }

    default Set<MediaItem> getSingleDuplicateSet(Set<MediaItem> inThisList, 
                                                    MediaItem toThis) {
        Set<MediaItem> duplicates = new HashSet<>();        
        for(MediaItem m : inThisList) {
            if(theseTwoAreDuplicates(m,toThis)){
                duplicates.add(m);
            }
        }
        return duplicates;      
    }
    default Set<MediaItem> getItemsToRemove(Set<Set<MediaItem>> duplicates) {
        Set<MediaItem> outputSet = new HashSet<>();
        for(Set<MediaItem> s : duplicates) {
            
            MediaItem firstItem = s.iterator().next();
            outputSet.addAll(s);
            outputSet.remove(firstItem);
        }
        return outputSet;
    }
}
       
    

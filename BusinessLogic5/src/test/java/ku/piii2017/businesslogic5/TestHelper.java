/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2017.businesslogic5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author James
 */
public class TestHelper {
    static List<String> getPlaylistNoMetaData()
    {
        List<String> output = new ArrayList<>();

        output.add("#EXTM3U");
        output.add(billEvans1().getAbsolutePath());
        output.add(billEvans2().getAbsolutePath());
        output.add(oscarPeterson1().getAbsolutePath());
        output.add(budPowell1().getAbsolutePath());
        
        return output;
    }
    static List<String> getPlaylistWithMetaData()
    {
        List<String> output = new ArrayList<>();
        List<String> orig = getPlaylistNoMetaData();
        output.add(orig.get(0));
        output.add("#EXTINF:100,Bill Evans - Peace Piece");
        output.add(orig.get(1));        
        output.add("#EXTINF:200,Bill Evans - Autumn Leaves");
        output.add(orig.get(2));        
        output.add("#EXTINF:300,Oscar Peterson - The Girl From Ipanema");
        output.add(orig.get(3));        
        output.add("#EXTINF:400,Bud Powell - A Night in Tunisia");
        output.add(orig.get(4));        
        
        return output;
    }
    static Set<MediaItem> getStandardSet()
    {
        Set<MediaItem> output = new HashSet<>();

        output.add(billEvans1());
        output.add(billEvans2());
        output.add(oscarPeterson1());
        output.add(budPowell1());
        
        return output;
    }
    static Set<MediaItem> getStandardSetB() {
        Set<MediaItem> output = new HashSet<>();

        output.add(billEvans1b());
        output.add(billEvans2b());
        output.add(oscarPeterson1b());
        output.add(budPowell1b());
        
        return output;
    }
    static List<MediaItem> getStandardList()
    {
        List<MediaItem> output = new ArrayList<>();

        output.add(billEvans1());
        output.add(billEvans2());
        output.add(oscarPeterson1());
        output.add(budPowell1());
        
        return output;
    }
    static List<MediaItem> getStandardListB() {
        List<MediaItem> output = new ArrayList<>();

        output.add(billEvans1b());
        output.add(billEvans2b());
        output.add(oscarPeterson1b());
        output.add(budPowell1b());
        
        return output;
    }

    static List<MediaItem> getStandardListToFail() {
        List<MediaItem> output = new ArrayList<>();
        output.add(billEvans1().setTitle("wrong title").setAbsolutePath(getPath(9876)));
        output.add(billEvans2().setAlbum("wrong album").setAbsolutePath(getPath(8765)));
        output.add(oscarPeterson1().setArtist("wrong artist").setAbsolutePath(getPath(7654)));
        output.add(budPowell1().setTitle("wrong title").setAbsolutePath(getPath(6543)));
        return output;
    }
    static Set<MediaItem> getAllBillEvans1() {
        Set<MediaItem> output = new HashSet<>();
        output.add(billEvans1());
        output.add(billEvans1b());
        output.add(billEvans1c());        
        return output;
    }

    static Set<MediaItem> getAllBudPowell1() {
        Set<MediaItem> output = new HashSet<>();
        output.add(budPowell1());
        output.add(budPowell1b());
        return output;
    }

    
    static String getPath(int i)
    {     
        return System.getProperty("user.dir") + File.separator + 
                "someFolder" + File.separator + 
                "file" + i  + ".mp3";        
    }
    static String getPathB(int i)
    {     
        return System.getProperty("user.dir") + File.separator + 
                "someFolderB" + File.separator + 
                "file" + i  + ".mp3";        
    }
    static String getPathC(int i)
    {     
        return System.getProperty("user.dir") + File.separator + 
                "someFolderC" + File.separator + 
                "file" + i  + ".mp3";        
    }

    static MediaItem billEvans1() {
        return new MediaItem().
                setAbsolutePath(getPath(1234)).
                setAlbum("Everybody Digs Bill Evans").
                setArtist("Bill Evans").
                setTitle("Peace Piece");
    }
    static MediaItem billEvans1b() {
        return new MediaItem().
                setAbsolutePath(getPathB(1234)).
                setAlbum("Everybody Digs Bill Evans").
                setArtist("Bill Evans").
                setTitle("Peace Piece");
    }

    static MediaItem billEvans1c() {
        return new MediaItem().
                setAbsolutePath(getPathC(1234)).
                setAlbum("Everybody Digs Bill Evans").
                setArtist("Bill Evans").
                setTitle("Peace Piece");
    }

    static MediaItem billEvans2() {
        return new MediaItem().
                setAbsolutePath(getPath(2435)).
                setAlbum("A Portrait of Bill Evans").
                setArtist("Bill Evans").
                setTitle("Autumn Leaves");    
    }
    static MediaItem billEvans2b() {
        return new MediaItem().
                setAbsolutePath(getPathB(2435)).
                setAlbum("A Portrait of Bill Evans").
                setArtist("Bill Evans").
                setTitle("Autumn Leaves");    
    }
    static MediaItem oscarPeterson1() {
       return new MediaItem().
                setAbsolutePath(getPath(3456)).
                setAlbum("We Get Requests").
                setArtist("Oscar Peterson").
                setTitle("The Girl From Ipanema");    
        
    }

    static MediaItem oscarPeterson1b() {
       return new MediaItem().
                setAbsolutePath(getPathB(3456)).
                setAlbum("We Get Requests").
                setArtist("Oscar Peterson").
                setTitle("The Girl From Ipanema");    
        
    }
    static MediaItem budPowell1() {

       return new MediaItem().
                setAbsolutePath(getPath(4567)).
                setAlbum("The Amazing Bud Powell").
                setArtist("Bud Powell").
                setTitle("A Night in Tunisia");        
    }
    static MediaItem budPowell1b() {
       return new MediaItem().
                setAbsolutePath(getPathB(4567)).
                setAlbum("The Amazing Bud Powell").
                setArtist("Bud Powell").
                setTitle("A Night in Tunisia");        
    }


    static List<String> getLines(Path filename) {
        try
        {            
            List<String> myList = new ArrayList<String>();
            BufferedReader reader = new BufferedReader(new FileReader(filename.toString()));
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                myList.add(inputLine);            
            }
            reader.close();  
            return myList;
        }
        catch (Exception e)
        {
            return null;
        }
    }
    static int getNumMatches(List<String> linesSaved, String searchString) {
        int numMatches = 0;
        for(String s : linesSaved) {
            if(s.contains(searchString)) {
                return numMatches;
            }
        }
        return numMatches;
    }


}

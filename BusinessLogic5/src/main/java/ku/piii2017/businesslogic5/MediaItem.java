/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii2017.businesslogic5;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author James
 */
public class MediaItem {

    private String absolutePath;
    private String title;
    private String album;
    

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }
    private String artist;
            
    public String getAbsolutePath() {
        return absolutePath;
    }

    public MediaItem setAbsolutePath(String absolutePath) {
        if (absolutePath==null) {
            throw new NullPointerException();
        }
        Path p = Paths.get(absolutePath);
        if(p.isAbsolute()==false) {
            throw new IllegalArgumentException();        
        }
        this.absolutePath = absolutePath;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof MediaItem)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return this.absolutePath.equals(((MediaItem) obj).getAbsolutePath());
    }
    @Override
    public int hashCode() {
        return this.absolutePath.hashCode();
    }


    public MediaItem  setTitle(String title) {
        this.title = title;
        return this;
    }

    public MediaItem  setAlbum(String album) {
        this.album = album;
        return this;
    }

    public MediaItem setArtist(String artist) {
        this.artist = artist;
        return this;
    }
}

package in.co.rays.proj4.bean;

public class MusicPlaylistBean extends BaseBean {

    private String playlistName;
    private String songName;
    private String artist;
    private String album;

    public String getPlaylistName() {
        return playlistName;
    }
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
    public String getSongName() {
        return songName;
    }
    public void setSongName(String songName) {
        this.songName = songName;
    }
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getAlbum() {
        return album;
    }
    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String getKey() {
        return null;
    }
    @Override
    public String getValue() {
        return null;
    }
}
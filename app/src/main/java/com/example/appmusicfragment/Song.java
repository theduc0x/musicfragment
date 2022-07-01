package com.example.appmusicfragment;

public class Song {
    private String name, singer;
    private int image, file;

    public Song(String name, String singer, int image, int file) {
        this.name = name;
        this.singer = singer;
        this.image = image;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }
}

package com.paintings.entity;

public class Painting {
    private Integer id;
    private String name;
    private Artist artist;

    public Painting() {
    }

    public Painting(String name, Artist artist) {
        this.name = name;
        this.artist = artist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Painting{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist=" + artist +
                '}' + "\n";
    }
}

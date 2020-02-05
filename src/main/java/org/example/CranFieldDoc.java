package org.example;


import lombok.Data;

@Data
public class CranFieldDoc {
    private String Id;
    private String title;
    private String authors;
    private String biblography;
    private String words;

    public CranFieldDoc() {
    }

    public CranFieldDoc(String id, String title, String authors, String biblography, String words) {
        Id = id;
        this.title = title;
        this.authors = authors;
        this.biblography = biblography;
        this.words = words;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getBiblography() {
        return biblography;
    }

    public void setBiblography(String biblography) {
        this.biblography = biblography;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }
}

package org.example.model;


import lombok.Data;

@Data
public class CranFieldDoc {
    private String Id;
    private String title;
    private String authors;
    private String bibliography;
    private String words;

    public CranFieldDoc() {
    }

    public CranFieldDoc(String id, String title, String authors, String bibliography, String words) {
        Id = id;
        this.title = title;
        this.authors = authors;
        this.bibliography = bibliography;
        this.words = words;
    }

    public String getId() {
        return Id;
    }

    @Override
    public String toString() {
        return "CranFieldDoc{" +
                "Id='" + Id + '\'' +
                ", title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", bibliography='" + bibliography + '\'' +
                ", words='" + words + '\'' +
                '}';
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

    public String getBibliography() {
        return bibliography;
    }

    public void setBibliography(String bibliography) {
        this.bibliography = bibliography;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }
}

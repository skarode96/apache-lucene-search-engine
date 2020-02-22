package org.example.model;

public class Query {
    private String id;
    private String queryString;

    public Query(String id, String queryString) {
        this.id = id;
        this.queryString = queryString;
    }

    public Query() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    @Override
    public String toString() {
        return "Query{" +
                "id='" + id + '\'' +
                ", queryString='" + queryString + '\'' +
                '}';
    }
}

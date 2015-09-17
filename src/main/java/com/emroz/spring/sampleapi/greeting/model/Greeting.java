package com.emroz.spring.sampleapi.greeting.model;

/**
 * Created by ahabib10 on 9/11/15.
 */
public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString(){
        return String.format("ID : %d, CONTENT: %s", id, content);
    }

}

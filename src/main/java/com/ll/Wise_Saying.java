package com.ll;

public class Wise_Saying {
    private String content;
    private String authorName;
    private long id = -1;
    Wise_Saying(long id, String content, String authorName){
        this.id = id;
        this.authorName = authorName;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getAuthorName() {
        return authorName;
    }

    public long getId() {
        return id;
    }


}

package yohahn.dailyworks.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author yohahn.kim
 * @since 9/12/14 10:29 PM
 */
public class Work {

    private long id;

    private String title;

    private boolean completed;

    public Work() {
        // Jackson deserialization
    }

    public Work(long id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    @JsonProperty
    public boolean isCompleted() {
        return completed;
    }
}

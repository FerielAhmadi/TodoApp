package com.example.todoapp.models;

public class Todo {
    private String title;
    private String priority;

    public Todo(String t, String p){
        this.title = t;
        this.priority = p;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}

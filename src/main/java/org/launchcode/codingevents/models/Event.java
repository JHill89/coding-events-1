package org.launchcode.codingevents.models;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private int id;
    private static int nextId = 1;
    
    private String name;
    private String description;
    
    public Event(String name, String description) {
        this.name = name;
        this.description = description;
        
        this.id = nextId;
        nextId++;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    public String getName() {
        return name;
    }
    
    public Event setName(String name) {
        this.name = name;
        return this;
    }
    
    public String getDescription() {
        return description;
    }
    
    public Event setDescription(String description) {
        this.description = description;
        return this;
    }
    
    public int getId() {
        return id;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Event event = (Event) o;
    
        return id == event.id;
    }
    
    @Override
    public int hashCode() {
        return id;
    }
}

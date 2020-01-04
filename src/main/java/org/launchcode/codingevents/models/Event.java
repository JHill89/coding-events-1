package org.launchcode.codingevents.models;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private String name;
    
    public Event(String name) {
        this.name = name;
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
}

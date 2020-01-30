package org.launchcode.codingevents.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Event extends AbstractEntity {
    

//    private static int nextId = 1; -no longer needed due to GeneratedValue attribute
    
    @NotBlank(message = "Name is required")
    @Size(min = 3,max = 50,message = "Name must be between 3 and 50 characters.")
    private String name;
    
    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private EventDetails eventDetails;
    
//    private EventType type;  // refactoring to use EventCategory
    
    @ManyToOne
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;
    
    public Event(String name, EventCategory eventCategory) {
        //No longer needed due to above
//        this();
        
        this.name = name;
        this.eventCategory = eventCategory;
        
    }
    
    public Event(){
        //No longer needed due to above
//        this.id = nextId;
//        nextId++;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    public EventCategory getEventCategory() {
        return eventCategory;
    }
    
    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public EventDetails getEventDetails() {
        return eventDetails;
    }
    
    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }
}

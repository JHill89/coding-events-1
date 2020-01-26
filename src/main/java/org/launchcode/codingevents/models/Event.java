package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Event extends AbstractEntity {
    

//    private static int nextId = 1; -no longer needed due to GeneratedValue attribute
    
    @NotBlank(message = "Name is required")
    @Size(min = 3,max = 50,message = "Name must be between 3 and 50 characters.")
    private String name;
    
    @Size(max = 500,message = "Description too long :(")
    private String description;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email. Please try again.")
    private String contactEmail;
    
    private EventType type;
    
    public Event(String name, String description, String contactEmail, EventType type) {
        //No longer needed due to above
//        this();
        
        this.name = name;
        this.description = description;
        
        this.contactEmail = contactEmail;
        this.type = type;
        
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
    
    public EventType getType() {
        return type;
    }
    
    public void setType(EventType type) {
        this.type = type;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getContactEmail() {
        return contactEmail;
    }
    
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    
}

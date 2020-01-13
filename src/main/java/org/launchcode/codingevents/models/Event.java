package org.launchcode.codingevents.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private int id;
    private static int nextId = 1;
    
    // Add a field to collect information about where the event will take place. This field should not be null or blank.
    @NotBlank(message = "Please enter a location")
     private String location;
    
    //Add a field to collect information about whether an attendee must register for the event or not. For the purposes of validation practice, make this field only able to be marked as true.
    private boolean registrationRequired = false; // *defaults to false, checking marks true
    
    
    //Add a field to collect information about the number of attendees for the event. Valid values for this field should be any number over zero.
    @Positive(message = "The number of attendees should be more than 0... :o")
     private int attendees;
    
    
    @NotBlank(message = "Name is required")
    @Size(min = 3,max = 50,message = "Name must be between 3 and 50 characters.")
    private String name;
    
    @Size(max = 500,message = "Description too long :(")
    private String description;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email. Please try again.")
    private String contactEmail;
    
    public Event(String name, String description, String location, boolean registrationRequired, int attendees, String contactEmail) {
        this.name = name;
        this.description = description;
        
        this.location = location;
        this.registrationRequired = registrationRequired;
        this.attendees = attendees;
        this.contactEmail = contactEmail;
        
        this.id = nextId;
        nextId++;
    }
    
    // empty constructor for validation object
    public Event(){}
    
    // getters/setters
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public boolean isRegistrationRequired() {
        return registrationRequired;
    }
    
    public void setRegistrationRequired(boolean registrationRequired) {
        this.registrationRequired = registrationRequired;
    }
    
    public int getAttendees() {
        return attendees;
    }
    
    public void setAttendees(int attendees) {
        this.attendees = attendees;
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
    
    public int getId() {
        return id;
    }
    
    public String getContactEmail() {
        return contactEmail;
    }
    
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    
    // toString methods
    @Override
    public String toString() {
        return name;
    }
    
    // comparison methods
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

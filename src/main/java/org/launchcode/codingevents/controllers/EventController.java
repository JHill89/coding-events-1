package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {
    
    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("events", EventData.getAllEvents());
        model.addAttribute("title","All Events");
        
        return "events/index";
    }
    
    // lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(Model model){
        model.addAttribute("title","Create Event");
        model.addAttribute(new Event());
        model.addAttribute("types",EventType.values());
        return "events/create";
    }
    
    // lives at /events/create
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title","Create Event");
//            model.addAttribute("errorMsg","Bad Data!");
            return "events/create";
        }
        EventData.add(newEvent);
        
        // redirects to the root path, like "redirect: /events"
        return "redirect:";
    }
    
    @GetMapping("delete")
    public String displayDeleteForm(Model model){
        model.addAttribute("title","Delete Events");
        model.addAttribute("events",EventData.getAllEvents());
        return "events/delete";
    }
    
    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds){
        if(eventIds != null){
            for (int id : eventIds){
                EventData.remove(id);
            }
        }
        return "redirect:";
    }
    
    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model,@PathVariable int eventId){
        Event eventToModify = EventData.getById(eventId);
        int id = eventToModify.getId();
        String name = eventToModify.getName();
        
        model.addAttribute("event",eventToModify);
        model.addAttribute("title", "Edit Event " + name + " (id=" + id + ")" );
        return "events/edit";
    }
    
    @PostMapping("edit")
    public String processEditForm(int eventId, String name, String description){
        Event modifyMe = EventData.getById(eventId);
        
        modifyMe.setDescription(description);
        modifyMe.setName(name);
    
        return "redirect:";
    }
}

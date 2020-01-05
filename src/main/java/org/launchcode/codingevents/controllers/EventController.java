package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "events/create";
    }
    
    // lives at /events/create
    @PostMapping("create")
    public String createEvent(@RequestParam String eventName, @RequestParam String eventDescription){
        EventData.add(new Event(eventName,eventDescription));
        
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
}

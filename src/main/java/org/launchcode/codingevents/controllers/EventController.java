package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("events")
public class EventController {
    
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private EventCategoryRepository eventCategoryRepository;
    
    // finAll, save, findById
    
    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("events", eventRepository.findAll());
        model.addAttribute("title","All Events");
        
        return "events/index";
    }
    
    // lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(Model model){
        model.addAttribute("title","Create Event");
//        model.addAttribute(new Event());
        model.addAttribute("eventCategories",eventCategoryRepository.findAll());
        model.addAttribute("event",new Event());
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
        eventRepository.save(newEvent);
        
        // redirects to the root path, like "redirect: /events"
        return "redirect:";
    }
    
    @GetMapping("delete")
    public String displayDeleteForm(Model model){
        model.addAttribute("title","Delete Events");
        model.addAttribute("events",eventRepository.findAll());
        return "events/delete";
    }
    
    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds){
        if(eventIds != null){
            for (int id : eventIds){
                eventRepository.deleteById(id);
            }
        }
        return "redirect:";
    }
    
    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model,@PathVariable int eventId){
        // TODO implement eventRepository
        Event eventToModify = eventRepository.findById(eventId).get();
//        int id = eventToModify.getId();
//        String name = eventToModify.getName();
        
        //Optional<Event> eventToModify = eventRepository.findById(eventId);
//        Event eventToModify = EventData.getById(eventId);
//        int id = eventToModify.getId();
//        String name = eventToModify.getName();
        
        model.addAttribute("event",eventToModify);
        model.addAttribute("title", "Edit Event " + eventToModify.getName() + " (id=" + eventToModify.getId() + ")" );
        return "events/edit";
    }
    
    @PostMapping("edit")
    public String processEditForm(int eventId, String name, String description){
//        Event modifyMe = EventData.getById(eventId);
        Event modifyMe = eventRepository.findById(eventId).get();
        modifyMe.setDescription(description);
        modifyMe.setName(name);
        eventRepository.save(modifyMe);
    
        return "redirect:";
    }
}

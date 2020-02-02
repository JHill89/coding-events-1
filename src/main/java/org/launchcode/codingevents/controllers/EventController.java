package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.data.TagRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.launchcode.codingevents.models.Tag;
import org.launchcode.codingevents.models.dto.EventTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {
    
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private EventCategoryRepository eventCategoryRepository;
    
    @Autowired
    private TagRepository tagRepository;
    
    // finAll, save, findById
    
    @GetMapping
    public String displayAllEvents(@RequestParam(required = false) Integer eventCategoryId, Model model){
        
        if (eventCategoryId == null){
            model.addAttribute("events", eventRepository.findAll());
            model.addAttribute("title","All Events");
        }
        else {
            Optional<EventCategory> result = eventCategoryRepository.findById(eventCategoryId);
            if(result.isEmpty()){
                model.addAttribute("title","Invalid Category ID: " + eventCategoryId);
            }
            else {
                EventCategory eventCategory = result.get();
                model.addAttribute("title", "Events in category: " + eventCategory.getName());
                model.addAttribute("events",eventCategory.getEvents());
            }
        }
        
        
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
    
    @GetMapping("detail")
    public String displayEventDetails(@RequestParam Integer eventId, Model model){
        Optional<Event> result = eventRepository.findById(eventId);
        if (result.isEmpty()){
            model.addAttribute("title","Invalid Event ID: " + eventId);
        }
        else{
            Event event = result.get();
            model.addAttribute("title",event.getName() + "Details");
            model.addAttribute("event", event);
        }
        
        return "events/detail";
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
        modifyMe.getEventDetails().setDescription(description);
        modifyMe.setName(name);
        eventRepository.save(modifyMe);
    
        return "redirect:";
    }
    
    // responds to /events/add-tag?eventId=13
    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer eventId, Model model){
        Optional<Event> result = eventRepository.findById(eventId);
        if (result.isEmpty()){
            model.addAttribute("title","Invalid Event ID: " + eventId);
            return "redirect:";
        }
        
        Event event = result.get();
        model.addAttribute("title","Add Tag to: " + event.getName());
        model.addAttribute("tags",tagRepository.findAll());
        
        EventTagDTO eventTag = new EventTagDTO();
        eventTag.setEvent(event);
        model.addAttribute("eventTag",eventTag);
        return "events/add-tag.html";
    }
    
    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag, Model model, Errors errors){
        if (!errors.hasErrors()){
            Event event = eventTag.getEvent();
            Tag tag = eventTag.getTag();
            
            if(!event.getTags().contains(tag)){
                event.addTag(tag);
                eventRepository.save(event);
            }
            return "redirect:detail?eventId=" + event.getId();
        }
        
        return "redirect:add-tag";
    
    }
}

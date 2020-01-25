package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("eventCategories")
public class EventCategoryController {
    
    @Autowired
    private EventCategoryRepository eventCategoryRepository;
    
    // root
    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("title","All Categories");
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "eventCategories/index";
    }
    
    // renderCreateEventCategoryForm
    @GetMapping("create")
    public String renderCreateEventCategoryForm(Model model){
        model.addAttribute("title","Create Category");
        model.addAttribute("category",new EventCategory());
        return "eventCategories/create";
    }
    
    // processCreateEventCategoryForm
    @PostMapping("create")
    public String processCreateEventCategoryForm(@ModelAttribute @Valid EventCategory eventCategory, Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title","Create Category");
            return "eventCategories/create";
        }
        
        eventCategoryRepository.save(eventCategory);
        
        return "redirect:";
    }
    
}

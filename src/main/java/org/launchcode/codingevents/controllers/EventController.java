package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {
    private static HashMap<String,String> events = new HashMap<>();
    
    @GetMapping
    public String displayAllEvents(Model model){
//        List<String> events = new ArrayList<>();
//        events.add("Code with Pride");
//        events.add("Strange Loop");
//        events.add("Apple WWDC");
//        events.add("SpringOne Platform");
        events.put("Code with Pride","Code with Pride is an affinity group, hosted by LaunchCode and 1904labs, that aims to help underrepresented groups succeed in the tech industry.");
        events.put("DevFest","This is a day long celebration of technology and careers in tech, brought to you by the St Louis chapter of Google Developers Group and Women Techmakers. We'll have a day of presentations about building great things with Google technologies, tech career advice, and our community.");
        events.put("Strange Loop","Strange Loop is a multi-disciplinary conference that brings together the developers and thinkers building tomorrow's technology in fields such as emerging languages, alternative databases, concurrency, distributed systems, security, and the web.");
        model.addAttribute("events",events);
        return "events/index";
    }
    
    // lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(){
        return "events/create";
    }
}

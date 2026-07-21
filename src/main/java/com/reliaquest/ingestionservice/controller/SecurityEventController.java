package com.reliaquest.ingestionservice.controller;

import com.reliaquest.ingestionservice.model.SecurityEventModel;
import com.reliaquest.ingestionservice.service.SecurityEventService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/events")
public class SecurityEventController {

    private final SecurityEventService service;

    public SecurityEventController(SecurityEventService service){
        this.service = service;
    }
    @PostMapping
    public void createEvent(@Valid @RequestBody SecurityEventModel event) {
        service.saveEvents(event);
    }
    @GetMapping
    public List<SecurityEventModel> getAllEvents() {
        return service.getAllEvents();
    }
    @GetMapping("/{ip}")
    public List<SecurityEventModel> getEventsByIp(@PathVariable String ip){
        return service.getEventsByIp(ip);
    }

}

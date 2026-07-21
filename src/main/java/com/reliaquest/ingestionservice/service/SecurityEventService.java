package com.reliaquest.ingestionservice.service;

import com.reliaquest.ingestionservice.model.SecurityEventModel;
import com.reliaquest.ingestionservice.repository.SecurityEventRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SecurityEventService {
    private final SecurityEventRepository repository;


    public SecurityEventService(SecurityEventRepository repository) {
        this.repository = repository;
    }

    public void saveEvents(SecurityEventModel event){
        repository.save(event);
    }

    public List<SecurityEventModel> getAllEvents(){
        return repository.findAll();
    }

    public List<SecurityEventModel> getEventsByIp(String ip){
        return repository.findByIp(ip);
    }
}

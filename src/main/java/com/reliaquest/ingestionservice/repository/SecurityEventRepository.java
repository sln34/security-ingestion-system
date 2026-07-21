package com.reliaquest.ingestionservice.repository;
import com.reliaquest.ingestionservice.model.SecurityEventModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SecurityEventRepository {

    /*
    So for this layer of the project we need to do a couple of things
    1. Add a new event
    2. Retrieve all events
    3. Retrieve events by a specific source IP

    What we can do here is create a hash map with the keys being the source IP
    this way we can handle all different things for this to work/

    -We can add an event by looking up the list for the IP and append to it
    -We can lookup direct IP's with O(1) time
    -Lastly we can detect future patterns by looking up the IP and filtering
    by if it was a success and the timeframe
     */
    private Map<String, List<SecurityEventModel>> events = new HashMap<>();

    public void save(SecurityEventModel event) {
       String key = event.getSourceIp();
       List<SecurityEventModel> eventsForIp = events.computeIfAbsent(key, k -> new ArrayList<>());
       eventsForIp.add(event);
        }

    public List<SecurityEventModel> findByIp(String ip) {

        List<SecurityEventModel> val = events.get(ip);

        return (val == null) ? new ArrayList<>() : val;
    }
    //.values extracts just the values from the map with no keys
    //.stream turns the collection into a stream
    //.flatMap turns the individual lists into a stream and merges them into one consecutive stream
    //.collect turns it all back into one singular list
    public List<SecurityEventModel> findAll() {
        List<SecurityEventModel> mergedList = events.values().stream().flatMap(List::stream).collect(Collectors.toList());
        return mergedList;
    }

}

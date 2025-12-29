package com.example.EventManagementSystem.Controller;

import com.example.EventManagementSystem.Entity.Event;
import com.example.EventManagementSystem.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event/v1")
public class EventController {

    @Autowired
    private EventService eventService;

    // CREATE
    @PostMapping("/add")
    public ResponseEntity<Event> addEvent(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.addEvent(event));
    }

    // READ - ALL
    @GetMapping("/getAll")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    // READ - BY ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Integer id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable Integer id,
            @RequestBody Event event) {
        return ResponseEntity.ok(eventService.updateEvent(id, event));
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Integer id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Event deleted successfully");
    }
}

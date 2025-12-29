package com.example.EventManagementSystem.Service;

import com.example.EventManagementSystem.Entity.Event;
import com.example.EventManagementSystem.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // CREATE
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    // READ - ALL
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // READ - BY ID
    public Event getEventById(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Event not found with id: " + id));
    }

    // UPDATE
    public Event updateEvent(Integer id, Event updatedEvent) {
        Event existingEvent = getEventById(id);


        existingEvent.setEventName(updatedEvent.getEventName());
        existingEvent.setEventType(updatedEvent.getEventType());
        existingEvent.setLocation(updatedEvent.getLocation());
        existingEvent.setOrganizer(updatedEvent.getOrganizer());
        existingEvent.setTicketPrice(updatedEvent.getTicketPrice());

        return eventRepository.save(existingEvent);
    }

    // DELETE
    public void deleteEvent(Integer id) {
        Event event = getEventById(id);
        eventRepository.delete(event);
    }
}

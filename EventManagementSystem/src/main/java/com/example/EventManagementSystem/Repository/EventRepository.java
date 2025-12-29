package com.example.EventManagementSystem.Repository;

import com.example.EventManagementSystem.Entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {

}

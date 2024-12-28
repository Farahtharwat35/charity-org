package com.charity_org.demo.Classes.IteratorComponents;

import com.charity_org.demo.Models.Model.Event;

import java.util.List;

public class SearchEventIterator implements EventIterator {
    private final List<Event> events;
    private final String keyword;
    private int position;

    public SearchEventIterator(List<Event> events, String keyword) {
        this.events = events;
        this.keyword = keyword.toLowerCase();
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        while (position < events.size()) {
            Event event = events.get(position);
            if (event.getEventName().toLowerCase().contains(keyword)) { // Check if event name contains the keyword
                return true;
            }
            position++;
        }
        return false;
    }

    @Override
    public Event next() {
        if (!hasNext()) {
            throw new RuntimeException("No more matching events.");
        }
        return events.get(position++);
    }

    // Reset the iterator
    public Event reset() {
        position = 0;
        return events.get(position);
    }
}
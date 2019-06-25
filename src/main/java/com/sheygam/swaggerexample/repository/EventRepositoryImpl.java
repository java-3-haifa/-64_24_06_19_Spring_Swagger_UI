package com.sheygam.swaggerexample.repository;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Repository
public class EventRepositoryImpl implements EventRepository {
    private ConcurrentHashMap<String, CopyOnWriteArrayList<EventEntity>> events = new ConcurrentHashMap<>();
    @Override
    public boolean add(EventEntity event) {
        events.computeIfAbsent(event.authorEmail, key -> new CopyOnWriteArrayList<>()).add(event);
        return true;
    }

    @Override
    public List<EventEntity> getAll() {
        return events.values().stream()
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(e -> e.date))
                .collect(Collectors.toUnmodifiableList());
    }
}

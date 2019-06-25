package com.sheygam.swaggerexample.repository;

import java.util.List;

public interface EventRepository {
    boolean add(EventEntity event);
    List<EventEntity> getAll();
}

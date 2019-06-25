package com.sheygam.swaggerexample.controller;

import com.sheygam.swaggerexample.repository.EventEntity;
import com.sheygam.swaggerexample.repository.EventRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EventController {

    private EventRepository repository;

    public EventController(EventRepository repository) {
        this.repository = repository;
    }

    @ApiOperation(value = "Add new event")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully! event added!"),
            @ApiResponse(code = 409, message = "Error message")
    }
    )
    @PostMapping("event")
    public void addEvent(
            @ApiParam(value = "Event object", required = true)
            @RequestBody EventDto event
    ) {
        repository.add(mapToEntity(event));
    }


    @ApiOperation(value = "Get all events", response = EventDto[].class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list")
    }
    )
    @GetMapping("event/all")
    public List<EventDto> getAll() {
        return repository.getAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private EventDto mapToDto(EventEntity entity) {
        EventDto dto = new EventDto();
        dto.authorEmail = entity.authorEmail;
        dto.title = entity.title;
        dto.description = entity.description;
        dto.date = entity.date;
        return dto;
    }

    private EventEntity mapToEntity(EventDto dto) {
        EventEntity entity = new EventEntity();
        entity.title = dto.title;
        entity.description = dto.description;
        entity.authorEmail = dto.authorEmail;
        entity.date = dto.date;
        return entity;
    }
}

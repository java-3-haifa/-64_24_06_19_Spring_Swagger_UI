package com.sheygam.swaggerexample.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

@ApiModel(value = "EventDto",description = "Event data transfer object")
public class EventDto {
    @ApiModelProperty(notes = "Title of event")
    public String title;
    @ApiModelProperty(notes = "Description of event")
    public String description;
    @ApiModelProperty(notes = "Date of event. Format dd/MM/yyyy HH:mm")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime date;
    @ApiModelProperty(notes = "Author email address")
    public String authorEmail;
}

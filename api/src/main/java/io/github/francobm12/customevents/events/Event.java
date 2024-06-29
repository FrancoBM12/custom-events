package io.github.francobm12.customevents.events;

public interface Event {

    String name();
    int eventId();

    EventSettings eventSettings();
    EventState eventState();

}

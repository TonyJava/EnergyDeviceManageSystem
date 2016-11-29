package com.sitanems.event.util;

public interface IEventGenerator {
    void addListener(IEventListener listener);

    void removeListener(IEventListener listener);

    public void publish(IEvent event);
}

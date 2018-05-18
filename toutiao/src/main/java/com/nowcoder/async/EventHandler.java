package com.nowcoder.async;

import java.util.List;


/**
 * Created by licker.
 */
public interface EventHandler {
    void doHandle(EventModel model);
    List<EventType> getSupportEventTypes();
}

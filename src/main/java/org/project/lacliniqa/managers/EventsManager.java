package org.project.lacliniqa.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class EventsManager {
    private static EventsManager instance;

    private Map<Integer, Callable<Integer>> listners;

    private EventsManager() {
        this.listners = new HashMap<Integer, Callable<Integer>>();
    }

    public static EventsManager getInstance() {
        if(instance == null) {
            instance = new EventsManager();
        }

        return instance;
    }

    public void registerEvent(int event_id, Callable<Integer> func) {
        listners.put(event_id, func);
    }

    public void fireEvent(int event_id) {
        try {
            listners.get(event_id).call();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

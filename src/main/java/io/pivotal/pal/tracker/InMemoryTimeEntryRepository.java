package io.pivotal.pal.tracker;

import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private Map<Long,TimeEntry> timeEntryStore = new HashMap<>();


    public TimeEntry create(TimeEntry timeEntry) {
        long id = timeEntry.getId();

        TimeEntry createdTimeEntry = new TimeEntry(timeEntryStore.size() + 1, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());

        timeEntryStore.put(createdTimeEntry.getId(), createdTimeEntry);
        System.out.println(timeEntryStore.values().toString());
        return createdTimeEntry;
    }

    public TimeEntry find(long id) {
        return timeEntryStore.get(id);
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry createdTimeEntry = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        timeEntryStore.put(createdTimeEntry.getId(), createdTimeEntry);
        return createdTimeEntry;
    }

    public TimeEntry delete(long id) {
        TimeEntry entry = timeEntryStore.get(id);
        timeEntryStore.remove(id);
        return entry;
    }

    public List list() {
        return new ArrayList<TimeEntry>(timeEntryStore.values());
    }

    private long getLargestId() {
        long maxId = 0;
        if(timeEntryStore != null && !timeEntryStore.values().isEmpty()) {
            for (long i = 0; i < timeEntryStore.size(); i++) {
                TimeEntry data = timeEntryStore.get(i);
                if (data != null && maxId < data.getId()) {
                    maxId = data.getId();
                }
            }
        }
        return maxId;
    }


}

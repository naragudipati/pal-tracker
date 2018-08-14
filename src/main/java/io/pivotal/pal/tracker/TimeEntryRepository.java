package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface TimeEntryRepository {

    public TimeEntry update(long id, TimeEntry timeEntry) ;

    public TimeEntry delete(long id) ;

    public TimeEntry create(TimeEntry timeEntry);

    public TimeEntry find(long id) ;

    public List<TimeEntry> list() ;
}

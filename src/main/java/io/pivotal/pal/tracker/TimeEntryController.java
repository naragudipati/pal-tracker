package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    @Autowired
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        if(timeEntryToCreate != null) {
            TimeEntry response = timeEntryRepository.create(timeEntryToCreate);
            return new ResponseEntity<TimeEntry>(response, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id)  {
        TimeEntry response = timeEntryRepository.find(id);
        if(response != null) {
            return new ResponseEntity<TimeEntry>(response, HttpStatus.OK);
        }
        return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> responseList = timeEntryRepository.list();
        return new ResponseEntity<List<TimeEntry>>(responseList, HttpStatus.OK);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry expected) {

        TimeEntry response = timeEntryRepository.update(id, expected);
        if(response != null) {
            return new ResponseEntity<TimeEntry>(response, HttpStatus.OK);
        }
        return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        return new ResponseEntity<TimeEntry>(HttpStatus.NO_CONTENT);
    }
}

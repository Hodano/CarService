package pl.hodan.carservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hodan.carservice.entity.Calendar;
import pl.hodan.carservice.exception.UserNotFoundException;
import pl.hodan.carservice.service.CalendarService;

import java.util.List;

@RestController
@RequestMapping(("/cars-service"))
public class CalendarController {
    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/calendars")
    public ResponseEntity getCalendarsByUserId(@RequestParam Long userId) {

        List<Calendar> calendars = calendarService.getCalendarsByUserId(userId);
        return ResponseEntity.ok(calendars);
    }

    @PostMapping("/add-calendar")
    public ResponseEntity addCalendar(@RequestParam Long userId, @RequestBody Calendar calendar) {
        if (calendarService.addCalendar(userId, calendar))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PutMapping("modify-calendar")
    public ResponseEntity modifyCalendar(@RequestParam Long userId, @RequestParam Long calendarId, @RequestBody Calendar newCalendar){
        if(calendarService.modifyCalendarWithUserIdByCalendarId(userId,calendarId,newCalendar)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("delete-calendar")
    public ResponseEntity deleteCalendar(@RequestParam Long userId, @RequestParam Long calendarId){
        if(calendarService.deleteCalendarWithUserIdByCalendarId(userId,calendarId))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

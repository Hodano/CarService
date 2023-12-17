package pl.hodan.carservice.common.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.hodan.carservice.auth.services.AuthenticationService;
import pl.hodan.carservice.common.configuration.UserUserDetails;
import pl.hodan.carservice.common.entity.Calendar;
import pl.hodan.carservice.common.service.CalendarsService;

import java.util.List;

@RestController
@RequestMapping(("/cars-service"))
public class CalendarController {
    private final CalendarsService calendarsService;
    private final AuthenticationService authenticationService;

    public CalendarController(CalendarsService calendarsService, AuthenticationService authenticationService) {
        this.calendarsService = calendarsService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/calendars")
    public ResponseEntity<List<Calendar>> getCalendarsByUserId() {
        Long userId = authenticationService.getCurrentUserDetails().getId();

        List<Calendar> calendars = calendarsService.getCalendarsByUserId(userId);
        return ResponseEntity.ok(calendars);
    }
    @GetMapping("/calendar")
    public ResponseEntity<Calendar> getCalendarByCalendarId(@RequestParam Long calendarId){
        Long userId = authenticationService.getCurrentUserDetails().getId();

        Calendar calendar = calendarsService.getCalendarById(userId,calendarId);

        return ResponseEntity.ok(calendar);
    }

    @PostMapping("/add-calendar")
    public ResponseEntity addCalendar(@RequestBody Calendar calendar) {
        Long userId = authenticationService.getCurrentUserDetails().getId();

        if (calendarsService.addCalendar(userId, calendar))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PutMapping("modify-calendar")
    public ResponseEntity modifyCalendar(@RequestParam Long calendarId, @RequestBody Calendar newCalendar){
        Long userId = authenticationService.getCurrentUserDetails().getId();

        if(calendarsService.modifyCalendarWithUserIdByCalendarId(userId,calendarId,newCalendar))
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("delete-calendar")
    public ResponseEntity deleteCalendar(@RequestParam Long calendarId){
        Long userId = authenticationService.getCurrentUserDetails().getId();

        if(calendarsService.deleteCalendarWithUserIdByCalendarId(userId,calendarId))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

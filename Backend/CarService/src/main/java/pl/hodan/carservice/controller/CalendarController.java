//package pl.hodan.carservice.controller;
//
//import lombok.Getter;
//import org.springframework.web.bind.annotation.*;
//import pl.hodan.carservice.entity.Calendar;
//import pl.hodan.carservice.service.CalendarService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/cars-service")
//public class CalendarController {
//    private CalendarService calendarService;
//
//    public CalendarController(CalendarService calendarService) {
//        this.calendarService = calendarService;
//    }
//
//    @GetMapping("calendars")
//    public List<Calendar> getCalendars(){
//        return calendarService.getCalendars();
//    }
//    @PostMapping("calendars")
//    public void addCalendar( @RequestBody Calendar calendar){
//
//        calendarService.addCalendar(calendar);
//    }
//}

//package pl.hodan.carservice.service;
//
//import org.springframework.stereotype.Service;
//import pl.hodan.carservice.entity.Calendar;
//import pl.hodan.carservice.repository.CalendarRepository;
//
//import java.util.List;
//
//@Service
//public class CalendarService {
//    private final CalendarRepository calendarRepository;
//
//    public CalendarService(CalendarRepository calendarRepository) {
//        this.calendarRepository = calendarRepository;
//    }
//
//    public List<Calendar> getCalendars(){
//        return calendarRepository.findAll();
//    }
//    public void addCalendar(Calendar calendar){
//        calendarRepository.save(calendar);
//
//    }
//
//}

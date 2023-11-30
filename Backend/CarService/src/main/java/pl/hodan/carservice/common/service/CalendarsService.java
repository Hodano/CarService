package pl.hodan.carservice.common.service;

import org.springframework.stereotype.Service;
import pl.hodan.carservice.common.entity.Calendar;
import pl.hodan.carservice.common.exception.CalendarNotFoundException;
import pl.hodan.carservice.common.repository.CalendarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarsService {
    private final CalendarRepository calendarRepository;

    private final UsersService usersService;

    public CalendarsService(CalendarRepository calendarRepository, UsersService usersService) {
        this.calendarRepository = calendarRepository;
        this.usersService = usersService;
    }

    public List<Calendar> getCalendarsByUserId(Long userId) {
        usersService.checkIfUserIdExist(userId);

        return calendarRepository.findCalendarsByUserId(userId);
    }

    public boolean addCalendar(Long userId, Calendar calendar) {
        setUserForCalendar(userId, calendar);
        if (calendar != null) {
            calendarRepository.save(calendar);
            return true;
        }
        return false;

    }

    public boolean modifyCalendarWithUserIdByCalendarId(Long userId, Long calendarId, Calendar newCalendar) {//

        checkIfCalendarIdExistByUserId(userId,calendarId);

        Optional<Calendar> calendar = calendarRepository.findCalendarByUserIdAndId(userId,calendarId);
        if(calendar.isPresent()){
            calendar.get().setDateEvent(newCalendar.getDateEvent());
            calendar.get().setEvent(newCalendar.getEvent());
            calendarRepository.save(calendar.get());
            return true;
        }

        return false;
    }
    public boolean deleteCalendarWithUserIdByCalendarId(Long userId, Long calendarId){//
        checkIfCalendarIdExistByUserId(userId,calendarId);

         Optional<Calendar> calendar = calendarRepository.findCalendarByUserIdAndId(userId,calendarId);
         if(calendar.isPresent()){
             calendarRepository.deleteById(calendarId);
             return true;
         }
         return false;
    }

    private void setUserForCalendar(Long userId, Calendar calendar) {
        calendar.setUser(usersService.checkIfUserIdExist(userId));
    }


    private void checkIfCalendarIdExistByUserId(Long userId,Long calendarId){
        usersService.checkIfUserIdExist(userId);
        if(!calendarRepository.existsCalendarById(calendarId))
            throw new CalendarNotFoundException("Calendar with id" + calendarId + "notExist");
    }
}

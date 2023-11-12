package pl.hodan.carservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hodan.carservice.entity.Client;
import pl.hodan.carservice.entity.History;
import pl.hodan.carservice.service.HistoriesService;

import java.util.List;

@RestController
@RequestMapping("/cars-service")
public class HistoryController {
    private final HistoriesService historiesService;

    public HistoryController(HistoriesService historiesService) {
        this.historiesService = historiesService;
    }
    @GetMapping("/histories")
    public ResponseEntity<List<History>> getHistoriesByCarId(@RequestParam Long carId) {
        List<History> histories = historiesService.getHistoryByCarId(carId);
        return ResponseEntity.ok(histories);
    }
    @PostMapping("/add-history")
    public ResponseEntity addHistory(@RequestParam Long carId, @RequestBody History history){
        if(historiesService.addHistory(carId,history))
            return new ResponseEntity(HttpStatus.CREATED);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/modify-history")
    public ResponseEntity modifyHistory(@RequestParam Long historyId, @RequestBody History history){
        if(historiesService.modifyHistoryWithCarIdByHistoryId(historyId,history))
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete-history")
    public ResponseEntity deleteClient(@RequestParam Long historyId){
        if(historiesService.deleteHistoryWithCarIdByHistoryId(historyId))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}

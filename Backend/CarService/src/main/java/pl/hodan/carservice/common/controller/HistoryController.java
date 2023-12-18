package pl.hodan.carservice.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hodan.carservice.common.entity.History;
import pl.hodan.carservice.common.service.HistoriesService;

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
    @GetMapping("/history")
    public ResponseEntity<History> getHistoryByHistoryId(@RequestParam Long historyId){
        History history = historiesService.getHistoryByHistoryId(historyId);
        return ResponseEntity.ok(history);
    }
    @PostMapping("/add-history")
    public ResponseEntity<String> addHistory(@RequestParam Long carId, @RequestBody History history){
        if(historiesService.addHistory(carId,history))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>("History could not be added",HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/modify-history")
    public ResponseEntity<String> modifyHistory(@RequestParam Long historyId, @RequestBody History history){
        if(historiesService.modifyHistoryWithCarIdByHistoryId(historyId,history))
            return new ResponseEntity<>("History modified",HttpStatus.OK);
        return new ResponseEntity<>("History not found or could not be modified",HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete-history")
    public ResponseEntity<String> deleteClient(@RequestParam Long historyId){
        if(historiesService.deleteHistoryWithCarIdByHistoryId(historyId))
            return new ResponseEntity<>("History deleted",HttpStatus.OK);
        return new ResponseEntity<>("History not found or could not be deleted",HttpStatus.NOT_FOUND);
    }
}

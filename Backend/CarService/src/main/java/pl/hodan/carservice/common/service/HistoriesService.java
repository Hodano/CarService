package pl.hodan.carservice.common.service;

import org.springframework.stereotype.Service;
import pl.hodan.carservice.common.entity.History;
import pl.hodan.carservice.common.exception.HistoryNotFoundException;
import pl.hodan.carservice.common.repository.HistoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HistoriesService {
    private final HistoryRepository historyRepository;
    private final CarsService carsService;

    public HistoriesService(HistoryRepository historyRepository, CarsService carsService) {
        this.historyRepository = historyRepository;
        this.carsService = carsService;
    }

    public List<History> getHistoryByCarId(Long carId) {
        carsService.checkIfCarIdExist(carId);

        return historyRepository.findHistoriesByCarId(carId);
    }

    public boolean addHistory(Long carId, History history) {
        setCarForHistory(carId,history);
        if (history != null) {
            historyRepository.save(history);
            return true;
        }
        return false;
    }

    public boolean modifyHistoryWithCarIdByHistoryId(Long historyId, History newHistory){
        Optional<History> history = Optional.ofNullable(checkIfHistoryIdExist(historyId));
        if(history.isPresent()){
            history.get().setDateOfHistoryCar(newHistory.getDateOfHistoryCar());
            history.get().setDescriptionHistory(newHistory.getDescriptionHistory());

            historyRepository.save(history.get());
            return true;
        }
        return false;
    }

    public boolean deleteHistoryWithCarIdByHistoryId( Long historyId) {

        Optional<History> history = Optional.ofNullable(checkIfHistoryIdExist(historyId));
        if (history.isPresent()) {
            historyRepository.deleteById(historyId);
            return true;
        }
        return false;
    }

    private void setCarForHistory(Long carId, History history) {
        history.setCar(carsService.checkIfCarIdExist(carId));
    }

//    private void checkIfHistoryIdExistByCarId(Long carId, Long historyId) {
//        carsService.checkIfCarIdExist(carId);
//        if (!historyRepository.existsHistoriesById(historyId)) {
//            throw new HistoryNotFoundException("History with id" + historyId + "notExist");
//        }
//    }
    public History checkIfHistoryIdExist(Long historyId){
        Optional<History> history = historyRepository.findById(historyId);
        if(history.isPresent()){
            return history.get();
        }
        throw new HistoryNotFoundException("History with this id " + historyId + "not exist");
    }
}

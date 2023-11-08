package pl.hodan.carservice.service;

import org.springframework.stereotype.Service;
import pl.hodan.carservice.entity.Calendar;
import pl.hodan.carservice.entity.PriceList;
import pl.hodan.carservice.exception.PriceListNotFoundException;
import pl.hodan.carservice.repository.PriceListRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PriceListService {
    private final PriceListRepository priceListRepository;
    private final UsersService usersService;

    public PriceListService(PriceListRepository priceListRepository, UsersService usersService) {
        this.priceListRepository = priceListRepository;
        this.usersService = usersService;
    }

    public List<PriceList> getPriceListsByUserId(Long userId) {
        usersService.checkIfUserIdExist(userId);

        return priceListRepository.findPriceListByUserId(userId);
    }

    public boolean addPriceList(Long userId, PriceList priceList) {
        setUserForPriceList(userId, priceList);
        if (priceList != null) {
            priceListRepository.save(priceList);
            return true;
        }
        return false;

    }

    public boolean modifyPriceListWithUserIdByPriceListId(Long userId, Long priceListId, PriceList newPriceList) {

        checkIfPriceListIdExistByUserId(userId, priceListId);

        Optional<PriceList> priceList = priceListRepository.findPriceListByUserIdAndId(userId, priceListId);
        if (priceList.isPresent()) {
            priceList.get().setNameOfService(newPriceList.getNameOfService());
            priceList.get().setPrices(newPriceList.getPrices());
            priceListRepository.save(priceList.get());
            return true;
        }

        return false;
    }

    public boolean deletePriceListWithUserIdByPriceListId(Long userId, Long priceListId){
        checkIfPriceListIdExistByUserId(userId,priceListId);

        Optional<PriceList> priceList = priceListRepository.findPriceListByUserIdAndId(userId,priceListId);
        if(priceList.isPresent()){
            priceListRepository.deleteById(priceListId);
            return true;
        }
        return false;
    }

    private void setUserForPriceList(Long userId, PriceList priceList) {
        priceList.setUser(usersService.checkIfUserIdExist(userId));
    }

    private void checkIfPriceListIdExistByUserId(Long userId, Long priceListId) {
        usersService.checkIfUserIdExist(userId);
        if (!priceListRepository.existsPriceListById(priceListId))
            throw new PriceListNotFoundException("PriceList with id " + priceListId + "notExist");
    }
}

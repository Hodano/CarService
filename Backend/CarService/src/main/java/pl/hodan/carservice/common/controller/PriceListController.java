package pl.hodan.carservice.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hodan.carservice.common.entity.PriceList;
import pl.hodan.carservice.common.service.PriceListService;

import java.util.List;

@RestController
@RequestMapping("/cars-service")
public class PriceListController {
    private final PriceListService priceListService;

    public PriceListController(PriceListService priceListService) {
        this.priceListService = priceListService;
    }

    @GetMapping("/priceLists")
    public ResponseEntity getPriceListByUserId(@RequestParam Long userId) {
        List<PriceList> priceLists = priceListService.getPriceListsByUserId(userId);
        return ResponseEntity.ok(priceLists);
    }

    @PostMapping("/add-priceList")
    public ResponseEntity addPriceList(@RequestParam Long userId, @RequestBody PriceList priceList) {
        if (priceListService.addPriceList(userId, priceList))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/modify-priceList")
    public ResponseEntity modifyPriceList(@RequestParam Long userId, @RequestParam Long priceListId, @RequestBody PriceList priceList) {
        if (priceListService.modifyPriceListWithUserIdByPriceListId(userId, priceListId, priceList))
            return new ResponseEntity(HttpStatus.ACCEPTED);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete-priceList")
    public  ResponseEntity deletePriceList(@RequestParam Long userId, @RequestParam Long priceListId){
        if(priceListService.deletePriceListWithUserIdByPriceListId(userId,priceListId))
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}

package pl.hodan.carservice.common.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hodan.carservice.auth.services.AuthenticationService;
import pl.hodan.carservice.common.entity.PriceList;
import pl.hodan.carservice.common.service.PriceListService;

import java.util.List;

@RestController
@RequestMapping("/cars-service")
public class PriceListController {
    private final PriceListService priceListService;
    private final AuthenticationService authenticationService;

    public PriceListController(PriceListService priceListService, AuthenticationService authenticationService) {
        this.priceListService = priceListService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/priceLists")
    public ResponseEntity<List<PriceList>> getPriceListsByUserId() {
        Long userId = authenticationService.getCurrentUserDetails().getId();

        List<PriceList> priceLists = priceListService.getPriceListsByUserId(userId);
        return ResponseEntity.ok(priceLists);
    }

    @GetMapping("/priceList")
    public ResponseEntity<PriceList> getPriceListById(@RequestParam Long priceListId) {
        Long userId = authenticationService.getCurrentUserDetails().getId();

        PriceList priceList = priceListService.getPriceListById(userId, priceListId);

        return ResponseEntity.ok(priceList);
    }

    @PostMapping("/add-priceList")
    public ResponseEntity addPriceList(@RequestBody PriceList priceList) {
        Long userId = authenticationService.getCurrentUserDetails().getId();

        if (priceListService.addPriceList(userId, priceList))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/modify-priceList")
    public ResponseEntity modifyPriceList(@RequestParam Long priceListId, @RequestBody PriceList priceList) {
        Long userId = authenticationService.getCurrentUserDetails().getId();

        if (priceListService.modifyPriceListWithUserIdByPriceListId(userId, priceListId, priceList))
            return new ResponseEntity(HttpStatus.ACCEPTED);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete-priceList")
    public ResponseEntity deletePriceList(@RequestParam Long priceListId) {
        Long userId = authenticationService.getCurrentUserDetails().getId();

        if (priceListService.deletePriceListWithUserIdByPriceListId(userId, priceListId))
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}

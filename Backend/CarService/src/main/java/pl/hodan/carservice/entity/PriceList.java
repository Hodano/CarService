package pl.hodan.carservice.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceList {
    private long id;
    private String nameOfService;
    private Double $prices;

    public PriceList(long id, String nameOfService, Double $prices) {
        this.id = id;
        this.nameOfService = nameOfService;
        this.$prices = $prices;
    }

    public PriceList() {
    }
}

package pl.hodan.carservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PriceList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameOfService;
    private Double $prices;

    public PriceList(Long id, String nameOfService, Double $prices) {
        this.id = id;
        this.nameOfService = nameOfService;
        this.$prices = $prices;
    }

    public PriceList() {
    }
}

package pl.hodan.carservice.entity;

import jakarta.persistence.*;
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
    private Double prices;
    @ManyToOne
    private User user;

    public PriceList(Long id, String nameOfService, Double prices) {
        this.id = id;
        this.nameOfService = nameOfService;
        this.prices = prices;
    }

    public PriceList() {
    }
}
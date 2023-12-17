package pl.hodan.carservice.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class PriceList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nameOfService;
    @NotNull
    @Min(1)
    private Double prices;
    @JsonIgnore
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

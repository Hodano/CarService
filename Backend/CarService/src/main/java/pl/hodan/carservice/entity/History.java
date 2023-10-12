package pl.hodan.carservice.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class History {
    private long id;
    private long carId;
    private String dateOfHistoryCar; // zmiana na date...
    private String descriptionHistory;

    public History(long id, long carId, String dateOfHistoryCar, String descriptionHistory) {
        this.id = id;
        this.carId = carId;
        this.dateOfHistoryCar = dateOfHistoryCar;
        this.descriptionHistory = descriptionHistory;
    }

    public History() {
    }
}

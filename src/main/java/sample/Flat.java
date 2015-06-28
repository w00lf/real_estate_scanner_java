package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;

/**
 * Created by w00lf on 28.06.2015.
 */
public class Flat {
    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty square;
    private final SimpleIntegerProperty floor;
    private final SimpleIntegerProperty totalFloors;
    private final SimpleIntegerProperty price;
    private final SimpleStringProperty  address;
//    private final SimpleIntegerProperty createdAt;
//    private final SimpleIntegerProperty updatedAt;

    Flat(int id, int square, int floor, int totalFloors, int price, String address) {
        this.id = new SimpleIntegerProperty(id);
        this.square = new SimpleIntegerProperty(square);
        this.floor = new SimpleIntegerProperty(floor);
        this.totalFloors = new SimpleIntegerProperty(totalFloors);
        this.price = new SimpleIntegerProperty(price);
        this.address = new SimpleStringProperty(address);
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer idif) {
        id.set(idif);
    }

    public Integer getSquare() {
        return square.get();
    }

    public void setSquare(Integer sqr) {
        square.set(sqr);
    }

    public Integer getFloor() {
        return floor.get();
    }

    public void setFloor(Integer flr) {
        floor.set(flr);
    }

    public Integer getTotalFloors() {
        return totalFloors.get();
    }

    public void setTotalFloors(Integer ttlFlrs) {
        totalFloors.set(ttlFlrs);
    }

    public Integer getPrice() {
        return price.get();
    }

    public void setPrice(Integer prs) {
        price.set(prs);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String adrs) {
        address.set(adrs);
    }
}

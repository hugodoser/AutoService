package entities;

public class AutomobileEntity {
    private String carNumber;
    private String brand;
    private int manufactureYear;
    private String ownerFIO;

    public AutomobileEntity() {

    }

    public AutomobileEntity(String carNumber) {
        this.carNumber = carNumber;
    }

    public AutomobileEntity(String carNumber, String brand, int manufactureYear, String ownerFIO) {
        this.carNumber = carNumber;
        this.brand = brand;
        this.manufactureYear = manufactureYear;
        this.ownerFIO = ownerFIO;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getOwnerFIO() {
        return ownerFIO;
    }

    public void setOwnerFIO(String ownerFIO) {
        this.ownerFIO = ownerFIO;
    }
}
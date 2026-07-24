import java.time.LocalDateTime;

public class RadarObservation {
    private final String plateNumber;
    private final LocalDateTime date;
    private final CarType carType;
    private final int speed;
    private final boolean seatbeltFastened;

    public RadarObservation(String plateNumber, LocalDateTime date, CarType carType, int speed, boolean seatbeltFastened) {
        this.plateNumber = plateNumber;
        this.date = date;
        this.carType = carType;
        this.speed = speed;
        this.seatbeltFastened = seatbeltFastened;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public CarType getCarType() {
        return carType;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isSeatbeltFastened() {
        return seatbeltFastened;
    }
}

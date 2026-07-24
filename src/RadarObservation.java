import java.time.LocalDateTime;


public class RADAROBSERVATION {
    private final String plateNumber;
    private final LocalDateTime date;

    private final CARTYPE carType;
    private final int speed;
    private final boolean seatbeltFastened;
    public RADAROBSERVATION(String plateNumber, LocalDateTime date, CARTYPE carType, int speed, boolean seatbeltFastened) {
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
    public CARTYPE getCarType() {
        return carType;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isSeatbeltFastened() {
        return seatbeltFastened;
    }
}

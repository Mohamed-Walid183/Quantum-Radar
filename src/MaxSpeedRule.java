public class MAXSPEEDRULE implements TRAFFICRULE {
    private static final int FEE = 300;


    private final CARTYPE carType;
    private final int maxAllowedSpeed;
    public MAXSPEEDRULE(CARTYPE carType, int maxAllowedSpeed) {
        this.carType = carType;
        this.maxAllowedSpeed = maxAllowedSpeed;
    }

    @Override
    public VIOLATION check(RADAROBSERVATION observation) {
        if (observation.getCarType() != carType || observation.getSpeed() <= maxAllowedSpeed) {
            return null;
        }

        return new VIOLATION(
                carType + " max speed " + maxAllowedSpeed,
                "speed of " + observation.getSpeed() + " exceeded max allowed " + maxAllowedSpeed,
                FEE
        );
    }
}

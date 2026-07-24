public class MaxSpeedRule implements TrafficRule {
    private static final int FEE = 300;

    private final CarType carType;
    private final int maxAllowedSpeed;

    public MaxSpeedRule(CarType carType, int maxAllowedSpeed) {
        this.carType = carType;
        this.maxAllowedSpeed = maxAllowedSpeed;
    }

    @Override
    public Violation check(RadarObservation observation) {
        if (observation.getCarType() != carType || observation.getSpeed() <= maxAllowedSpeed) {
            return null;
        }
        return new Violation(
                carType + " max speed " + maxAllowedSpeed,
                "speed of " + observation.getSpeed() + " exceeded max allowed " + maxAllowedSpeed,
                FEE
        );
    }
}

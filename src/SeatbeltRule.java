public class SeatbeltRule implements TrafficRule {
    private static final String RULE_NAME = "Seatbelt should be fastened";
    private static final int FEE = 100;

    @Override
    public Violation check(RadarObservation observation) {
        if (observation.isSeatbeltFastened()) {
            return null;
        }
        return new Violation(RULE_NAME, "Seatbelt not fastned", FEE);
    }
}

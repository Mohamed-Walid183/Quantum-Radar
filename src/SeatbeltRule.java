public class SEATBELTRULE implements TRAFFICRULE {
    private static final String RULE_NAME = "Seatbelt should be fastened";

    private static final int FEE = 100;

    @Override
    public VIOLATION check(RADAROBSERVATION observation) {
        if (observation.isSeatbeltFastened()) {
            return null;
        }

        return new VIOLATION(RULE_NAME, "Seatbelt not fastned", FEE);
    }
}

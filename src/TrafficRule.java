public interface TrafficRule {
    Violation check(RadarObservation observation);
}

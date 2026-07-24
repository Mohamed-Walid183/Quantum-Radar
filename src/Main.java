import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        QuRadar radar = new QuRadar();
        radar.addRule(new SeatbeltRule());
        radar.addRule(new MaxSpeedRule(CarType.TRUCK, 60));
        radar.addRule(new MaxSpeedRule(CarType.PRIVATE, 80));

        radar.observe(new RadarObservation(
                "ABC1234",
                LocalDateTime.of(2026, 7, 24, 10, 30),
                CarType.PRIVATE,
                94,
                false
        ));

        radar.observe(new RadarObservation(
                "TRK5000",
                LocalDateTime.of(2026, 7, 24, 10, 35),
                CarType.TRUCK,
                65,
                true
        ));

        radar.observe(new RadarObservation(
                "BUS7777",
                LocalDateTime.of(2026, 7, 24, 10, 40),
                CarType.BUS,
                50,
                true
        ));

        Fine[] fines = radar.getFines();
        for (int i = 0; i < fines.length; i++) {
            fines[i].print();
        }

        System.out.println("All possible fines:");
        FineSummary[] fineSummaries = radar.getAllPossibleFines();
        for (int i = 0; i < fineSummaries.length; i++) {
            System.out.println(fineSummaries[i].getPlateNumber() + " : " + fineSummaries[i].getTotalAmount() + " EGP");
        }

        System.out.println("Violated rules count:");
        RuleViolationCount[] ruleCounts = radar.getViolatedRulesCount();
        for (int i = 0; i < ruleCounts.length; i++) {
            System.out.println(ruleCounts[i].getRuleName() + " : " + ruleCounts[i].getCount());
        }
    }
}

import java.time.LocalDateTime;


public class main {
    public static void main(String[] args) {
        quantumradar radar = new quantumradar();

        radar.addRule(new SEATBELTRULE());
        radar.addRule(new MAXSPEEDRULE(CARTYPE.TRUCK, 60));
        radar.addRule(new MAXSPEEDRULE(CARTYPE.PRIVATE, 80));
        radar.observe(new RADAROBSERVATION(
                "ABC1234",
                LocalDateTime.of(2026, 7, 24, 10, 30),
                CARTYPE.PRIVATE,
                94,
                false
        ));


        radar.observe(new RADAROBSERVATION(
                "TRK5000",
                LocalDateTime.of(2026, 7, 24, 10, 35),
                CARTYPE.TRUCK,
                65,
                true
        ));
        radar.observe(new RADAROBSERVATION(
                "BUS7777",
                LocalDateTime.of(2026, 7, 24, 10, 40),
                CARTYPE.BUS,
                50,
                true
        ));

        FINE[] fines = radar.getFines();

        for (int i = 0; i < fines.length; i++) {
            fines[i].print();
        }

        System.out.println("All possible fines:");
        FINESUMMARY[] fineSummaries = radar.getAllPossibleFines();
        for (int i = 0; i < fineSummaries.length; i++) {
            System.out.println(fineSummaries[i].getPlateNumber() + " : " + fineSummaries[i].getTotalAmount() + " EGP");
        }


        System.out.println("Violated rules count:");
        RULEVIOLATIONCOUNT[] ruleCounts = radar.getViolatedRulesCount();
        for (int i = 0; i < ruleCounts.length; i++) {
            System.out.println(ruleCounts[i].getRuleName() + " : " + ruleCounts[i].getCount());
        }
    }
}

import java.time.LocalDateTime;
public class FINE {
    private final String plateNumber;

    private final LocalDateTime date;
    private final VIOLATION[] violations;

    public FINE(String plateNumber, LocalDateTime date, VIOLATION[] violations, int violationCount) {
        this.plateNumber = plateNumber;
        this.date = date;
        this.violations = new VIOLATION[violationCount];


        for (int i = 0; i < violationCount; i++) {
            this.violations[i] = violations[i];
        }
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public VIOLATION[] getViolations() {
        VIOLATION[] result = new VIOLATION[violations.length];

        for (int i = 0; i < violations.length; i++) {
            result[i] = violations[i];
        }
        return result;
    }

    public int getTotalAmount() {
        int total = 0;
        for (int i = 0; i < violations.length; i++) {
            total += violations[i].getFee();
        }

        return total;
    }

    public void print() {
        System.out.println("Traffic for car " + plateNumber + " Total amount: " + getTotalAmount() + " EGP Violations:");

        for (int i = 0; i < violations.length; i++) {
            System.out.println("- " + violations[i].getDescription() + " : " + violations[i].getFee() + " EGP");
        }
    }
}

public class FineSummary {
    private final String plateNumber;
    private int totalAmount;

    public FineSummary(String plateNumber, int totalAmount) {
        this.plateNumber = plateNumber;
        this.totalAmount = totalAmount;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void addAmount(int amount) {
        totalAmount += amount;
    }
}

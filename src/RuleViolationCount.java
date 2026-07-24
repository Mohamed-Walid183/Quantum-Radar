public class RuleViolationCount {
    private final String ruleName;
    private int count;

    public RuleViolationCount(String ruleName, int count) {
        this.ruleName = ruleName;
        this.count = count;
    }

    public String getRuleName() {
        return ruleName;
    }

    public int getCount() {
        return count;
    }

    public void increase() {
        count++;
    }
}

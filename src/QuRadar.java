/*
 * QuRadar is a simple rule-based quantum radar traffic system. It receives observations from a
 * physical radar with plate number, date, car type, speed, and seatbelt status. For each
 * observation, the registered traffic rules are checked and zero or more violations can be
 * generated. If any violation exists, the system issues a fine with all violations and their
 * individual fees. The radar also reports all possible fines by plate number and total amount,
 * and counts how many times each rule was violated. AI model used: none; the solution uses a
 * deterministic rule engine so new rules can be added without modifying this radar class.
 */
public class QuRadar {
    private static final int MAX_RULES = 20;
    private static final int MAX_FINES = 100;
    private static final int MAX_RULE_COUNTS = 20;

    private final TrafficRule[] rules = new TrafficRule[MAX_RULES];
    private int rulesCount = 0;

    private final Fine[] fines = new Fine[MAX_FINES];
    private int finesCount = 0;

    private final RuleViolationCount[] violatedRulesCount = new RuleViolationCount[MAX_RULE_COUNTS];
    private int violatedRulesCountSize = 0;

    public void addRule(TrafficRule rule) {
        if (rulesCount < rules.length) {
            rules[rulesCount] = rule;
            rulesCount++;
        }
    }

    public void observe(RadarObservation observation) {
        Violation[] violations = new Violation[rulesCount];
        int violationCount = 0;

        for (int i = 0; i < rulesCount; i++) {
            Violation violation = rules[i].check(observation);
            if (violation != null) {
                violations[violationCount] = violation;
                violationCount++;
                increaseRuleCount(violation.getRuleName());
            }
        }

        if (violationCount > 0 && finesCount < fines.length) {
            fines[finesCount] = new Fine(observation.getPlateNumber(), observation.getDate(), violations, violationCount);
            finesCount++;
        }
    }

    public Fine[] getFines() {
        Fine[] result = new Fine[finesCount];
        for (int i = 0; i < finesCount; i++) {
            result[i] = fines[i];
        }
        return result;
    }

    public FineSummary[] getAllPossibleFines() {
        FineSummary[] summaries = new FineSummary[finesCount];
        int summariesCount = 0;

        for (int i = 0; i < finesCount; i++) {
            Fine fine = fines[i];
            int summaryIndex = findFineSummary(summaries, summariesCount, fine.getPlateNumber());

            if (summaryIndex == -1) {
                summaries[summariesCount] = new FineSummary(fine.getPlateNumber(), fine.getTotalAmount());
                summariesCount++;
            } else {
                summaries[summaryIndex].addAmount(fine.getTotalAmount());
            }
        }

        FineSummary[] result = new FineSummary[summariesCount];
        for (int i = 0; i < summariesCount; i++) {
            result[i] = summaries[i];
        }
        return result;
    }

    public RuleViolationCount[] getViolatedRulesCount() {
        RuleViolationCount[] result = new RuleViolationCount[violatedRulesCountSize];
        for (int i = 0; i < violatedRulesCountSize; i++) {
            result[i] = violatedRulesCount[i];
        }
        return result;
    }

    private void increaseRuleCount(String ruleName) {
        for (int i = 0; i < violatedRulesCountSize; i++) {
            if (violatedRulesCount[i].getRuleName().equals(ruleName)) {
                violatedRulesCount[i].increase();
                return;
            }
        }

        if (violatedRulesCountSize < violatedRulesCount.length) {
            violatedRulesCount[violatedRulesCountSize] = new RuleViolationCount(ruleName, 1);
            violatedRulesCountSize++;
        }
    }

    private int findFineSummary(FineSummary[] summaries, int summariesCount, String plateNumber) {
        for (int i = 0; i < summariesCount; i++) {
            if (summaries[i].getPlateNumber().equals(plateNumber)) {
                return i;
            }
        }
        return -1;
    }
}

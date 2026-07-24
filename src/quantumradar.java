public class quantumradar {
    private static final int MAX_RULES = 20;
    private static final int MAX_FINES = 100;

    private static final int MAX_RULE_COUNTS = 20;

    private final TRAFFICRULE[] rules = new TRAFFICRULE[MAX_RULES];
    private int rulesCount = 0;


    private final FINE[] fines = new FINE[MAX_FINES];
    private int finesCount = 0;
    private final RULEVIOLATIONCOUNT[] violatedRulesCount = new RULEVIOLATIONCOUNT[MAX_RULE_COUNTS];
    private int violatedRulesCountSize = 0;

    public void addRule(TRAFFICRULE rule) {
        if (rulesCount < rules.length) {
            rules[rulesCount] = rule;
            rulesCount++;
        }
    }

    public void observe(RADAROBSERVATION observation) {
        VIOLATION[] violations = new VIOLATION[rulesCount];
        int violationCount = 0;


        for (int i = 0; i < rulesCount; i++) {
            VIOLATION violation = rules[i].check(observation);
            if (violation != null) {
                violations[violationCount] = violation;
                violationCount++;
                increaseRuleCount(violation.getRuleName());
            }
        }
        if (violationCount > 0 && finesCount < fines.length) {
            fines[finesCount] = new FINE(observation.getPlateNumber(), observation.getDate(), violations, violationCount);
            finesCount++;
        }
    }

    public FINE[] getFines() {
        FINE[] result = new FINE[finesCount];
        for (int i = 0; i < finesCount; i++) {
            result[i] = fines[i];
        }

        return result;
    }

    public FINESUMMARY[] getAllPossibleFines() {
        FINESUMMARY[] summaries = new FINESUMMARY[finesCount];
        int summariesCount = 0;

        for (int i = 0; i < finesCount; i++) {
            FINE fine = fines[i];
            int summaryIndex = findFineSummary(summaries, summariesCount, fine.getPlateNumber());


            if (summaryIndex == -1) {
                summaries[summariesCount] = new FINESUMMARY(fine.getPlateNumber(), fine.getTotalAmount());
                summariesCount++;
            } else {
                summaries[summaryIndex].addAmount(fine.getTotalAmount());
            }
        }
        FINESUMMARY[] result = new FINESUMMARY[summariesCount];

        for (int i = 0; i < summariesCount; i++) {
            result[i] = summaries[i];
        }
        return result;
    }

    public RULEVIOLATIONCOUNT[] getViolatedRulesCount() {
        RULEVIOLATIONCOUNT[] result = new RULEVIOLATIONCOUNT[violatedRulesCountSize];
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
            violatedRulesCount[violatedRulesCountSize] = new RULEVIOLATIONCOUNT(ruleName, 1);

            violatedRulesCountSize++;
        }
    }

    private int findFineSummary(FINESUMMARY[] summaries, int summariesCount, String plateNumber) {
        for (int i = 0; i < summariesCount; i++) {
            if (summaries[i].getPlateNumber().equals(plateNumber)) {
                return i;
            }
        }
        return -1;
    }
}

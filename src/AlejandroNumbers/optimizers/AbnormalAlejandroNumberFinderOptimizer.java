package AlejandroNumbers.optimizers;

import AlejandroNumbers.Queries.AbnormalAlejandroNumberFinderParams;
import AlejandroNumbers.Queries.AbnormalDominantAlejandroNumberFinder;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;

public class AbnormalAlejandroNumberFinderOptimizer {
    private final long timeLimit;
    //Just a test
    private double modifier;
    private double stepsize;
    private double toValue;
    private HashMap<AbnormalDominantAlejandroNumberFinder, Double> executedAbnormalFindersAndRespectiveTriedValues = new HashMap<>();
    private OnDoubleOptimizationFinishedListener optimizationFinishedListener;

    public AbnormalAlejandroNumberFinderOptimizer(double from, double stepsize, double toValue, long timeLimit) {
        this.modifier = from;
        this.stepsize = stepsize;
        this.toValue = toValue;
        this.timeLimit = timeLimit;
    }

    public void setOptimizationFinishedListener(OnDoubleOptimizationFinishedListener optimizationFinishedListener) {
        this.optimizationFinishedListener = optimizationFinishedListener;
    }

    public void optimize(AbnormalAlejandroNumberFinderParams params) {
        System.out.println("Started optimization");
        createAbnormalAlejandroNumberFinderInstances(params);
        Timer timer = new Timer();
        timer.schedule(new OptimizationEndTimer(), timeLimit);
    }

    class OptimizationEndTimer extends TimerTask {
        @Override
        public void run() {
            evaluateOptimizedValue();
        }
    }

    private void evaluateOptimizedValue() {
        int greatesResultSize = 0;
        Double greatesResultSizeModifier = 0.0;
        for (AbnormalDominantAlejandroNumberFinder abnormalDominantAlejandroNumberFinder : executedAbnormalFindersAndRespectiveTriedValues.keySet()) {
            abnormalDominantAlejandroNumberFinder.stop();
            int foundresults = abnormalDominantAlejandroNumberFinder.getFoundResults();
            if (foundresults > greatesResultSize) {
                greatesResultSize = foundresults;
                greatesResultSizeModifier = executedAbnormalFindersAndRespectiveTriedValues.get(abnormalDominantAlejandroNumberFinder);
            }
        }
        optimizationFinishedListener.optimized(greatesResultSizeModifier, greatesResultSize);
    }

    private void createAbnormalAlejandroNumberFinderInstances(AbnormalAlejandroNumberFinderParams params) {
        for (double currentMod = modifier; currentMod <= toValue; currentMod += stepsize) {
            System.out.println("Started new AbnormalDominanceFinder instance with modification value " + currentMod);
            createNewAbnormalFinderInstanceWithParam(params, currentMod);
        }
    }

    private void createNewAbnormalFinderInstanceWithParam(AbnormalAlejandroNumberFinderParams params, double currentMod) {
        CompletableFuture.runAsync(() -> {
            AbnormalDominantAlejandroNumberFinder abnormalDominantAlejandroNumberFinder = new AbnormalDominantAlejandroNumberFinder(params);
            abnormalDominantAlejandroNumberFinder.setTimeJumpMultiplicator(currentMod);
            executedAbnormalFindersAndRespectiveTriedValues.put(abnormalDominantAlejandroNumberFinder, currentMod);
            abnormalDominantAlejandroNumberFinder.startAbnormalSearch();
        });
    }

}

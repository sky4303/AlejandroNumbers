package AlejandroNumbers.Visualizers;

import AlejandroNumbers.Filters.ComparisonStrategyFilters.ComparisonStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigInteger;

public class AlejandroNumberGrapher {
    public static void plot(BigInteger[] alejandroNumbersSeeds, Integer[] associatedAbnormalities, int maxIterations, ComparisonStrategy comparisonStrategy, int abnormalityIndicator, BigInteger startSeed) {
        int[] x = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] y = {13, 46, 23, 8, 23, 25, 44, 31};

        JFrame frame = new JFrame("Analysis from "
                + startSeed
                + " to "
                + alejandroNumbersSeeds[alejandroNumbersSeeds.length-1]
                + " with Condition: "
                + comparisonStrategy.description()
                + abnormalityIndicator
                + " at "
                + maxIterations
                + " iterations ");
        frame.setSize(500, 500);
        frame.setLocation(100, 100);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        PlotPoints mainPane = new PlotPoints(alejandroNumbersSeeds, associatedAbnormalities, 0, alejandroNumbersSeeds[alejandroNumbersSeeds.length-1].doubleValue(), 0, 9);
        mainPane.setBackground(Color.white);

        frame.setContentPane(mainPane);
        frame.setVisible(true);
    }
}

package AlejandroNumbers.Visualizers;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.geom.*;
import java.awt.Graphics;
import java.math.BigInteger;

public class PlotPoints extends JPanel {

    //Plot Area
    final static double PA_XO = 0.15, PA_YO = 0.10; // Origin
    final static double PA_XS = 0.80, PA_YS = 0.80; // Size

    final static double dotSize = 5; // Plotted point size, in pixels
    int nFcnPts = 100;   // Number of points used to plot function

    // Input data
    int nPts;
    double[] x, y;
    // Plot area in user coordinates (x-y, not pixel, space)
    double xMin, xMax, yMin, yMax;

    // Constructor with user-defined plot area
    PlotPoints(BigInteger[] xdata, Integer[] ydata,
               double xMn, double xMx, double yMn, double yMx) {
        nPts = xdata.length;
        x = new double[nPts];
        y = new double[nPts];
        for(int i=0; i<nPts; i++){
            x[i] = xdata[i].doubleValue();
            y[i] = ydata[i];
        }
        xMin = xMn;
        xMax = xMx;
        yMin = yMn;
        yMax = yMx;
    }

    public void paintComponent (Graphics g) {

        super.paintComponent(g);  //paint background
        Graphics2D g2 = (Graphics2D)g; // Cast Graphics object to Graphics2D

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Dimension d = getSize();
        System.out.println("Panel is " + d.width + " x " + d.height);

        // It is useful to define the corners of the plot area in pixels
        int iMin = (int)(PA_XO*d.width);
        int iMax = (int)((PA_XO+PA_XS)*d.width);
        int jMin = (int)(PA_YO*d.height);
        int jMax = (int)((PA_YO+PA_YS)*d.height);
        // It is also useful to define the scaling between the plot area
        // in x-y and the plot area in pixels
        double xScale = (double)(iMax-iMin)/(xMax-xMin);
        double yScale = (double)(jMax-jMin)/(yMax-yMin);
        // There are (at least) THREE coordinate systems in play here:
        //  1) The pixel coordinates in the JPanel
        //  2) The x-y of the data
        //  3) The pixel coordinates of the plotting area
        // Either sort through how I am labelling these, or come up with your
        // own scheme.

        // Draw axes.
        g2.setColor(Color.black);
        g2.draw(new Rectangle2D.Double(PA_XO*d.width, PA_YO*d.height,
                PA_XS*d.width, PA_YS*d.height));

        double ix;
        double iy;

        for(int i=0; i<nPts; i++) {
            ix = iMin + ((x[i]-xMin)*xScale);
            iy = jMax - ((y[i]-yMin)*yScale);

            g2.setColor(Color.red);
            g2.fill(new Ellipse2D.Double(ix-dotSize/2., iy-dotSize/2.,
                    dotSize, dotSize));
        }
        g2.setColor(Color.black);
        g2.drawString( String.valueOf(xMin), (float)(PA_XO*d.width-10.),
                (float)((PA_YO+PA_YS)*d.height+15.) );
        g2.drawString( String.valueOf(xMax),
                (float)((PA_XO+PA_XS)*d.width-20.),
                (float)((PA_YO+PA_YS)*d.height+15.) );

        g2.drawString( String.valueOf(yMin), (float)(PA_XO*d.width-50.),
                (float)((PA_YO+PA_YS)*d.height+5.) );
        g2.drawString( String.valueOf(yMax), (float)(PA_XO*d.width-50.),
                (float)(PA_YO*d.height+5.) );
    }
}
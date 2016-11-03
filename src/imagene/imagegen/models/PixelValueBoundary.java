package imagene.imagegen.models;

/****************************************
 * Written by Dorothea Baker (s3367422) *
 * for                                  *
 * Programming Project 1                *
 * SP3 2016                             *
 ***************************************/

public class PixelValueBoundary {
    private double rMax, gMax, bMax, rMin, gMin, bMin;

    public PixelValueBoundary() {
        rMax = Integer.MIN_VALUE;
        gMax = Integer.MIN_VALUE;
        bMax = Integer.MIN_VALUE;

        rMin = Integer.MAX_VALUE;
        gMin = Integer.MAX_VALUE;
        bMin = Integer.MAX_VALUE;
    }

    public double rMax() { return rMax; }
    public double gMax() { return gMax; }
    public double bMax() { return bMax; }

    public double rMin() { return rMin; }
    public double gMin() { return gMin; }
    public double bMin() { return bMin; }


    public void setRMax(double value) { rMax = value; }
    public void setGMax(double value) { gMax = value; }
    public void setBMax(double value) { bMax = value; }

    public void setRMin(double value) { rMin = value; }
    public void setGMin(double value) { gMin = value; }
    public void setBMin(double value) { bMin = value; }

}

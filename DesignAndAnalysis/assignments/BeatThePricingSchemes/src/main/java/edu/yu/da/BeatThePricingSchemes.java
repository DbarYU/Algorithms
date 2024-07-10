package edu.yu.da;

import java.util.*;

public class BeatThePricingSchemes extends BeatThePricingSchemesBase {
    private class PricingScheme{
        private final double price;
        private final int quantity;
        PricingScheme(double price, int quantity){
            this.price = price;
            this.quantity = quantity;
        }
    }
    /**
     * Constructor: client specifies the price of a single quantity of the
     * desired item.
     *
     * @param unitPrice the price-per-single-unit, must be greater than 0.
     * @throw IllegalArgumentException if the parameter pre-conditions are
     * violated.
     */
    private final double unitPrice;
    private List<PricingScheme> pricingSchemes;
    private int[][] optimalPath;
    private int numOfSchemes;
    private List<Integer> mostOptimalPath;
    private double mostOptimalPrice;
    private boolean canReturnOptimalPaths;
    private double[][] optimalPrice;
    private int previousPrice;
    public BeatThePricingSchemes(double unitPrice) {
        super(unitPrice);
        if(unitPrice <= 0)
            throw new IllegalArgumentException();
        this.unitPrice = unitPrice;
        this.pricingSchemes = new ArrayList<>();
        mostOptimalPrice = 0;
        this.canReturnOptimalPaths = false;
        this.previousPrice = Integer.MAX_VALUE;

        pricingSchemes.add(new PricingScheme(unitPrice,1));
        numOfSchemes =0;
        mostOptimalPath = new ArrayList<>();
    }

    @Override
    public void addPricingScheme(double price, int quantity) {
        validate(price,quantity);
        this.pricingSchemes.add(new PricingScheme(price,quantity));
        canReturnOptimalPaths = false;
        numOfSchemes++;

    }

    private void validate(double price, int quantity) {
        if(numOfSchemes >= MAX_SCHEMES)
            throw new IllegalArgumentException();
        if(price <= 0)
            throw new IllegalArgumentException();
        if(quantity <= 0)
            throw new IllegalArgumentException();
        if(quantity > MAX_MATZOS)
            throw new IllegalArgumentException();
    }

    @Override
    public double cheapestPrice(int threshold) {
        validateCheapestPath(threshold);

        if (canReturnOptimalPaths && threshold<=this.previousPrice ) {
            this.mostOptimalPrice = this.optimalPrice[threshold][this.optimalPrice[0].length - 1];
            this.mostOptimalPath= decisions(threshold);
        } else {

            this.optimalPrice = new double[threshold + 1][this.pricingSchemes.size()];
            this.optimalPath = new int[threshold + 1][this.pricingSchemes.size()];
            optimalPrice[1][0] = this.unitPrice;
            for (int i = 2; i < optimalPrice.length; i++) {
                optimalPrice[i][0] = optimalPrice[i - 1][0] + this.unitPrice;
                optimalPath[i][0] = 0;
            }
            for (int i = 1; i < optimalPrice[0].length; i++) {
                int currentPricingSchemeQuantity = this.pricingSchemes.get(i).quantity;
                double currentPricingSchemePrice = this.pricingSchemes.get(i).price;
                for (int k = 1; k < optimalPrice.length; k++) {
                    int prevOptimalPrice = 0;
                    if (currentPricingSchemeQuantity <= k)
                        prevOptimalPrice = k - currentPricingSchemeQuantity;

                    if (optimalPrice[k][i - 1] <= optimalPrice[prevOptimalPrice][i] + currentPricingSchemePrice) {
                        optimalPrice[k][i] = optimalPrice[k][i - 1];
                        optimalPath[k][i] = optimalPath[k][i - 1];
                    } else {
                        double optimalCurrentPrice = optimalPrice[prevOptimalPrice][i] + currentPricingSchemePrice;
                        optimalPrice[k][i] = optimalCurrentPrice;
                        optimalPath[k][i] = i;
                    }
                }
            }

            this.mostOptimalPrice = optimalPrice[optimalPrice.length - 1][optimalPrice[0].length - 1];
            this.mostOptimalPath = decisions(threshold);
            canReturnOptimalPaths = true;
            previousPrice = threshold;
        }
        return mostOptimalPrice;
    }
    private void validateCheapestPath(int threshold){
        if(threshold > MAX_MATZOS)
            throw new IllegalArgumentException();
        if(threshold <= 0)
            throw new IllegalArgumentException();
    }
    private List<Integer> decisions(int threshold) {
        List<Integer> path= new ArrayList<>();
        for(int i = threshold; i>0;){
            int currentScheme  = optimalPath[i][optimalPath[0].length-1];
            path.add(currentScheme);
            i -= this.pricingSchemes.get(currentScheme).quantity;
        }
         Collections.sort(path);
        return path;
    }
@Override
public List<Integer> optimalDecisions(){
        if(canReturnOptimalPaths)
            return this.mostOptimalPath;
        throw new IllegalStateException();
}

}

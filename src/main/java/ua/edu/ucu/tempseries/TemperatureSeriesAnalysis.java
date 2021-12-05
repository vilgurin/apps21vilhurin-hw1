package ua.edu.ucu.tempseries;


import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;
    private int lowestTemp = -273;


    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatureSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
    }

    public void ifEmpty(){
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Empty");
        }
    }


    public double average() {
        ifEmpty();
        double averageValue = 0;
        for (int i = 0; i < temperatureSeries.length ; i++){
           averageValue += temperatureSeries[i];
        }
        return averageValue/temperatureSeries.length;
    }

    public double deviation() {
        ifEmpty();
        if (temperatureSeries.length == 1){
            return 0;
        }
        double averageValue = average();
        double sumValue = 0;
        for (int i = 0; i < temperatureSeries.length ; i++){
            sumValue += (temperatureSeries[i] - averageValue)*(temperatureSeries[i] - averageValue);
        }
    return Math.sqrt(sumValue/(temperatureSeries.length));
    }

    public double min() {
        ifEmpty();
        double minValue = temperatureSeries[0];
        for (int i = 0; i < temperatureSeries.length ; i++){
            if (temperatureSeries[i] < minValue){
                minValue = temperatureSeries[i];
            }
        }
        return minValue;
    }

    public double max() {
        ifEmpty();
        double maxValue = temperatureSeries[0];
        for (int i = 0; i < temperatureSeries.length ; i++){
            if (temperatureSeries[i] > maxValue){
                maxValue = temperatureSeries[i];
            }
        }
        return maxValue;
    }

    public double findTempClosestToZero() {
        ifEmpty();
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        ifEmpty();
        double finalDiff = Double.POSITIVE_INFINITY;
        for (int i = 0; i < temperatureSeries.length ; i++){
            if (Math.abs(temperatureSeries[i] - tempValue) < Math.abs(finalDiff)){
                finalDiff = temperatureSeries[i];
            }
            else if (Math.abs(temperatureSeries[i] - tempValue) == Math.abs(finalDiff)){
                finalDiff = Math.max(temperatureSeries[i], finalDiff);
            }
        }
        return finalDiff;
    }

    public int findLengthOfArrayLess(double tempValue){
        int length = 0;
        for (int i =0;i<temperatureSeries.length;i++){
            if (temperatureSeries[i] < tempValue){
                length += 1;
            }
        }
        return length;
    }
    public int findLengthOfArrayGreater(double tempValue){
        int length = 0;
        for (int i =0;i<temperatureSeries.length;i++){
            if (temperatureSeries[i] > tempValue){
                length += 1;
            }
        }
        return length;
    }
    public double[] findTempsLessThen(double tempValue) {
        ifEmpty();
        int lenArr = findLengthOfArrayLess(tempValue);
        double arr[] = new double[lenArr];
        int counter = 0;
        for (int i = 0; i< temperatureSeries.length;i++){
            if (temperatureSeries[i] < tempValue){
                arr[counter] = (temperatureSeries[i]);
                counter += 1;
            }
        }
        return arr;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        ifEmpty();
        int lenArr = findLengthOfArrayLess(tempValue);
        double arr[] = new double[lenArr];
        int counter = 0;
        for (int i = 0; i< temperatureSeries.length;i++){
            if (temperatureSeries[i] > tempValue){
                arr[counter] = (temperatureSeries[i]);
                counter += 1;
            }
        }
        return arr;
    }

    public TempSummaryStatistics summaryStatistics() {
        ifEmpty();
        double avg = average();
        double dev = deviation();
        double minimum = min();
        double maximum = max();
        return new TempSummaryStatistics(avg,dev,minimum,maximum);
    }

    public int lenAdd(double arr[]){
        int counter = 0;
        for (int i = 0; i< arr.length;i++){
         if (i >= lowestTemp){
             counter += 1;
         }
        }
        return counter;
    }

    public double addTemps(double... temps) {
        ifEmpty();
        int lenTemps = lenAdd(temps);
        int arrLength = lenAdd(temps) + temperatureSeries.length;
        double finalArr[] = new double[arrLength];
        int counter = 0;
        for (int i = 0; i< temperatureSeries.length;i++){
            finalArr[counter] = temperatureSeries[i];
            counter += 1;
        }
        counter = temperatureSeries.length;
        System.out.println(counter);
        for (int i = 0;i < lenTemps;i++){
            if (temps[i] > lowestTemp){
                finalArr[counter] = temps[i];
                counter += 1;
            }
            else{
                throw new InputMismatchException("Incorrect temperature");
            }        }


        double output = 0;

        for (int i = 0; i<lenAdd(temps)+temperatureSeries.length;i++){
            output +=  finalArr[i];
        }
        System.out.println(Arrays.toString(finalArr));
        return output;
    }

    public static void main(String[] args) {
        double arr[] = {3.0, -5.0, 1.0, 5.0};
        double arr2[] = {1,2};
        TemperatureSeriesAnalysis a = new TemperatureSeriesAnalysis(arr);
        System.out.println(a.addTemps(arr2));
    }
}

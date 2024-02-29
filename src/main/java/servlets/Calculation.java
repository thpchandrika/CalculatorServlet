package servlets;

public class Calculation {

    private double first;
    private double second;
    private String operation;
    private double result;

    public Calculation(double first, double second, String operation, double result) {
        this.first = first;
        this.second = second;
        this.operation = operation;
        this.result = result;
    }

    public double getFirst() {
        return first;
    }

    public void setFirst(double first) {
        this.first = first;
    }

    public double getSecond() {
        return second;
    }

    public void setSecond(double second) {
        this.second = second;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

}

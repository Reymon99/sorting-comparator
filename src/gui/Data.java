package gui;

public class Data {
    private int data;
    private double percentage;

    public Data(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(int max) {
        this.percentage = ((double) data/max);
    }
}

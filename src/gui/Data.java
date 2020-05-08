package gui;

public class Data {
    private int data;
    private int percentage;

    public Data(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int max) {
        this.percentage = 100 * data / max;
    }
}

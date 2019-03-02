package Domain;

public class StandAveragePriceViewModel {

    private int stand;
    private double averagePrice;

    @Override
    public String toString() {
        return "StandAveragePriceViewModel{" +
                "stand=" + stand +
                ", averagePrice=" + averagePrice +
                '}';
    }

    public int getStand() {
        return stand;
    }

    public void setStand(int stand) {
        this.stand = stand;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public StandAveragePriceViewModel(int stand, double averagePrice) {
        this.stand = stand;
        this.averagePrice = averagePrice;
    }
}


// ExpressPackage.java

public class ExpressPackage extends Package {
    public ExpressPackage(String trackingID, String destination, double weight) {
        super(trackingID, destination, weight);
    }

    @Override
    public double calculateShippingCost() {
        return getWeight() * 4.0;
    }
}

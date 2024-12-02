
// Package.java

import java.util.regex.Pattern;

public abstract class Package {
    private String trackingID;
    private String destination;
    private double weight;

    // Properly escaped regex patterns
    private static final Pattern TRACKING_ID_PATTERN = Pattern.compile("PKG\\d{5}");
    private static final Pattern DESTINATION_PATTERN = Pattern.compile("\\d+\\s+\\w+(\\s+\\w+)*");

    public Package(String trackingID, String destination, double weight) {
        this.trackingID = trackingID;
        this.destination = destination;
        this.weight = weight;
    }

    public String getTrackingID() {
        return trackingID;
    }

    public String getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }

    public abstract double calculateShippingCost();

    public boolean validateTrackingID() {
        return TRACKING_ID_PATTERN.matcher(trackingID).matches();
    }

    public boolean validateDestination() {
        return DESTINATION_PATTERN.matcher(destination).matches();
    }

    @Override
    public String toString() {
        return String.format("Tracking ID: %s | Destination: %s | Weight: %.2f | Cost: $%.2f",
                trackingID, destination, weight, calculateShippingCost());
    }
}

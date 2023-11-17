package com.nearby.util;

public class DistanceHelper {
  private static final int EARTH_RADIUS = 6371; // Approximate Earth radius in kilometers

  public static Double calculateDistance(Double lat1, Double lat2, Double lon1, Double lon2) {
    double lat1Rad = Math.toRadians(lat1);
    double lat2Rad = Math.toRadians(lat2);
    double lon1Rad = Math.toRadians(lon1);
    double lon2Rad = Math.toRadians(lon2);

    double x = (lon2Rad - lon1Rad) * Math.cos((lat1Rad + lat2Rad) / 2);
    double y = (lat2Rad - lat1Rad);

    return Math.sqrt(x * x + y * y) * EARTH_RADIUS;
  }
}

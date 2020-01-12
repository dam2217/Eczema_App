package com.example.eczema_app.ui.log;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * contains helper methods to manipulate user location
 */
public class LocationHelper {

    private Context mContext;

    public LocationHelper(Context context) {
        this.mContext = context;
    }

    /**
     * @param locServLat  latitude of address
     * @param locServLon longitude of address
     * @return simplified address of location
     */

    public String getSimplifiedAddress(String locServLat, String locServLon) {
        double latitude = Double.valueOf(locServLat);
        double longitude = Double.valueOf(locServLon);
        String[] lat_lon = new String[];
        lat_lon(1) =
        new AsyncTask<String[], Void, String>() {
            protected String doInBackground(Void... voids) {

                String location = "";
                double latitude = Double.valueOf(locServLat);
                double longitude = Double.valueOf(locServLon);
                try {
                    Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
                    List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                    if (addresses.size() > 0) {
                        Address address = addresses.get(0);
                        String subAdmin = address.getSubAdminArea();
                        String subLocality = address.getSubLocality();
                        String locality = address.getLocality();
                        if (locality != null && subLocality != null) {
                            location = subLocality + "," + locality;
                        } else if (subLocality != null) {
                            location = subLocality + "," + subAdmin;
                        } else {
                            location = subAdmin + "," + locality;
                        }
                    }
                } catch (
                        IOException e) {
                    e.printStackTrace();
                }
            }

            return location;
        }
    }

    /**
     * to get latitude and longitude of an address
     *
     * @param strAddress address string
     * @return lat and lng in comma separated string
     */
    public String getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(mContext);
        List<Address> address;

        try {
            address = coder.getFromLocationName(strAddress, 1);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            double lat = location.getLatitude();
            double lng = location.getLongitude();

            return lat + "," + lng;
        } catch (Exception e) {
            return null;
        }
    }
}
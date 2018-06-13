import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;



public abstract class GPS_Service extends Service {

        private LocationListener listner;

    {
        listner = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Intent i = new Intent("location_update");
                i.putExtra("coordinates", location.getLongitude() + " " + location.getLatitude());
                sendBroadcast(i);

            }


            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                Intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK))
                startActivities(i);

            }
        };
    }

    private LocationManager locationManager;


        @Override

        public IBinder onBinder(Intent intent){
            return null;
        }

        @SuppressLint("MissingPermission")
        @Override
        public void onCreate()
        {
            locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,0,listner);
        }

  @Override
    public void onDestroy(){
            super.onDestroy();
            if (locationManager !=null) {
                locationManager.removeUpdates(listner);
            }
  }

}


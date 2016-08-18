package com.jrsm.android.gentera;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ActivityUbicaciones extends AppCompatActivity {

    LocationManager mLocationManager;
    TextView tvCoordenadas, tvDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicaciones);

        tvCoordenadas = (TextView) findViewById(R.id.tv1);
        tvDireccion = (TextView) findViewById(R.id.tv2);

        StringBuilder mSB = null;

        //Se inicializa el objeto LocationManager
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Se define un location provider (en este caso se usa Criteria)
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);

        String locationprovider = mLocationManager.getBestProvider(criteria, true);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location mLocation = mLocationManager.getLastKnownLocation(locationprovider);

        List<Address> direccion;

        try{
            Geocoder mGC = new Geocoder(this, Locale.ENGLISH);
            direccion = mGC.getFromLocation(mLocation.getLatitude(),mLocation.getLongitude(), 1);

            if(direccion != null) {
                Address currentAddr = direccion.get(0);
                mSB = new StringBuilder("Dirección:\n");

                for (int i = 0; i < currentAddr.getMaxAddressLineIndex(); i++) {
                    mSB.append(currentAddr.getAddressLine(i)).append("\n");
                }
            }

            tvDireccion.setText(mSB.toString());
        }
        catch(IOException e){
            tvDireccion.setText(e.getMessage());
        }
        tvCoordenadas.setText("Coordenadas:" + "\n" + "Latitud:" + mLocation.getLatitude()
                + "\n" + "Longitud:" + mLocation.getLongitude());
    }
}

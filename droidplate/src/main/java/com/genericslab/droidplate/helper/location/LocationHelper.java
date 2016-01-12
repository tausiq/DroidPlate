package com.genericslab.droidplate.helper.location;

import android.content.Context;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;

import com.genericslab.droidplate.log.Tracer;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by shahab on 1/1/16.
 */
public class LocationHelper implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    LocationHelperListener listener;
    Location mLastKnownLocation;

    LocationConfig config;
    Context context;
    private AddressResultReceiver mResultReceiver;

    public LocationHelper(Context context, LocationHelperListener listener, LocationConfig config) {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();

            createLocationRequest();
        }

        this.listener = listener;
        this.config = config;
        this.context = context;
        mResultReceiver = new AddressResultReceiver(new Handler());
    }

    public void connect() {
        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }

    public void disconnect() {
        Tracer.d("LocationHelper disconnect");
        if (mGoogleApiClient !=  null && mGoogleApiClient.isConnected()) {
            stopLocationUpdates();
            mGoogleApiClient.disconnect();
        }

    }

    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    protected void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }

    @Override
    public void onConnected(Bundle bundle) {
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastKnownLocation = location;
        if (listener != null) listener.onLocationChanged(location);
        if (config.getFrequency() == LocationConfig.Frequency.ONE_TIME) disconnect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private void startIntentService(Location location) {
        Intent intent = new Intent(context, FetchAddressIntentService.class);
        intent.putExtra(LocationConstants.RECEIVER, mResultReceiver);
        intent.putExtra(LocationConstants.LOCATION_DATA_EXTRA, location);
        context.startService(intent);
    }

    public void getGeoAddress(Location location) {
        if (mGoogleApiClient != null && location != null) {
            if (!Geocoder.isPresent()) {
                return;
            }
            mLastKnownLocation = location;
            startIntentService(location);
        }
    }

    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            // Display the address string
            // or an error message sent from the intent service.
            String mAddressOutput = resultData.getString(LocationConstants.RESULT_DATA_KEY);

            // Show a toast message if an address was found.
            if (resultCode == LocationConstants.SUCCESS_RESULT) {
                Tracer.i("Got the address: " + mAddressOutput);
                listener.onReceiveGeoCoding(mLastKnownLocation, mAddressOutput);
            }

        }
    }

    public interface LocationHelperListener {
        void onLocationChanged(Location var);
        void onReceiveGeoCoding(Location loc, String address);
    }
}

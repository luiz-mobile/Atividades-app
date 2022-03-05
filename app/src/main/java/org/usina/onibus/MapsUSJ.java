package org.usina.onibus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsUSJ extends Fragment {

    private OnMapReadyCallback callback=new OnMapReadyCallback () {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng vilakennedy=new LatLng (-7.130625241843281, -35.01003296230005 );
            googleMap.addMarker ( new MarkerOptions().position ( vilakennedy ).title ( "USINA SÃO JOÃO  " ) );
            googleMap.moveCamera ( CameraUpdateFactory.newLatLngZoom ( vilakennedy,12 ) );

            LatLng rua15denovembro=new LatLng ( -7.1408104311672735, -35.085419457602605 );
            googleMap.addMarker ( new MarkerOptions ().position (  rua15denovembro ).title ( "ESPIRITO SANTO" ) );
            // googleMap.moveCamera ( CameraUpdateFactory.newLatLng (  rua15denovembro ) );

            LatLng boavista=new LatLng ( -7.195423068561215, -35.05616716924429 );
            googleMap.addMarker ( new MarkerOptions ().position ( boavista ).title ( " TIBIRI " ) );
            //  googleMap.moveCamera ( CameraUpdateFactory.newLatLng ( boavista ) );


            LatLng batateira=new LatLng ( -7.1770165358559455, -35.06554881890551 );
            googleMap.addMarker ( new MarkerOptions ().position ( batateira ).title ( "SÃO JOÃO" ) );
            //  googleMap.moveCamera ( CameraUpdateFactory.newLatLng ( batateira ) );


            LatLng s=new LatLng ( -7.083953680144277, -35.02787364323221 );
            googleMap.addMarker ( new MarkerOptions ().position ( s ).title ( "SÃO GONÇALO" ) );
            //  googleMap.moveCamera ( CameraUpdateFactory.newLatLng ( batateira ) );


        }
    };



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps_u_s_j, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}
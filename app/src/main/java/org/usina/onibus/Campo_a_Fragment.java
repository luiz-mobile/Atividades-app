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



public class Campo_a_Fragment extends Fragment {

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

            LatLng vilakennedy=new LatLng (-7.131143018148983, -35.009763018141555 );
            googleMap.addMarker ( new MarkerOptions().position ( vilakennedy ).title ( "FAZENDA CENTRAL " ) );
            googleMap.moveCamera ( CameraUpdateFactory.newLatLngZoom ( vilakennedy,12 ) );

            LatLng rua15denovembro=new LatLng ( -7.178863518218664, -35.06462881232167 );
            googleMap.addMarker ( new MarkerOptions ().position (  rua15denovembro ).title ( "SÃO JOÃO" ) );
            // googleMap.moveCamera ( CameraUpdateFactory.newLatLng (  rua15denovembro ) );

            //-7.141296267031698, -35.08735278310557
            LatLng es=new LatLng ( -7.141296267031698, -35.08735278310557);
            googleMap.addMarker ( new MarkerOptions ().position (  es ).title ( "ESPIRITO SANTO " ) );





        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_campo_a_, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated ( view, savedInstanceState );
        SupportMapFragment mapFragment=
                ( SupportMapFragment ) getChildFragmentManager ().findFragmentById ( R.id.map );
        if (mapFragment != null) {
            mapFragment.getMapAsync ( callback );
        }
    }
}

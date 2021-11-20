package br.edu.ifrs.projetoexemplomd.ui.map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.edu.ifrs.projetoexemplomd.R;

public class MapsFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

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
            LatLng deam = new LatLng(-30.047175366665464, -51.210610297076244);
            LatLng pc = new LatLng(-30.03266116892074, -51.235515672661506);

            googleMap.addMarker(new MarkerOptions().position(deam).title("Delegacia da Mulher - Rua xxxx telefone xxxx"));
            googleMap.addMarker(new MarkerOptions().position(pc).title("Polícia Civil - Rua xxxx telefone xxxx"));
//            googleMap.addMarker(new MarkerOptions().position(deam).title("Delegacia da Mulher - Rua xxxx telefone xxxx"));
//            googleMap.addMarker(new MarkerOptions().position(pc).title("Polícia Civil - Rua xxxx telefone xxxx"));
//            googleMap.addMarker(new MarkerOptions().position(deam).title("Delegacia da Mulher - Rua xxxx telefone xxxx"));

            googleMap.moveCamera(CameraUpdateFactory.newLatLng(deam));
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(12));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
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
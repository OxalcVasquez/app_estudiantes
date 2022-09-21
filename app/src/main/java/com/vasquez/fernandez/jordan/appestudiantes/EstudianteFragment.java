package com.vasquez.fernandez.jordan.appestudiantes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class EstudianteFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomMenu;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_estudiante, container, false);
        bottomMenu = view.findViewById(R.id.bottom_menu);
        bottomMenu.setOnNavigationItemSelectedListener(this);
        getActivity().setTitle("Estudiantes");

        this.onNavigationItemSelected(bottomMenu.getMenu().getItem(0));

        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = new Fragment();
        switch (item.getItemId()){
            case R.id.opcion_agregar:
                fragment = new EstudianteAgregarFragment();
                break;
            case R.id.opcion_listar:
                fragment = new EstudianteListadoFragment();
                break;

        }
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contenedor_estudiante,fragment).commit();

        return true;
    }
}
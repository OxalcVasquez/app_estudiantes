package com.vasquez.fernandez.jordan.appestudiantes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.vasquez.fernandez.jordan.appestudiantes.R;
import com.vasquez.fernandez.jordan.appestudiantes.adapter.EstudianteAdapter;
import com.vasquez.fernandez.jordan.appestudiantes.logica.Estudiante;
import com.vasquez.fernandez.jordan.appestudiantes.utils.Helper;


public class EstudianteListadoFragment extends Fragment {

    RecyclerView rvEstudiantes;
    EstudianteAdapter estudianteAdapter;

    public EstudianteListadoFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_estudiante_listado, container, false);
        this.getActivity().setTitle("Listado Estudiantes");
        rvEstudiantes = view.findViewById(R.id.rv_estudiantes);
        rvEstudiantes.setHasFixedSize(true);
        rvEstudiantes.setLayoutManager(new LinearLayoutManager(this.getContext()));
        estudianteAdapter = new EstudianteAdapter(this.getContext());
        rvEstudiantes.setAdapter(estudianteAdapter);
        listar();
        return view;
    }

    private void listar(){
        new Estudiante().cargarDatosEstudiante();
        estudianteAdapter.cargarDatosEstudiante(Estudiante.listaEstudiantes);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1: //opcion eliminar
                Helper.mensajeConfirmacion(this.getActivity(),"Desea eliminar el registro?"," ELIMINAR","CANCELAR",new EliminarEstudianteTask());
                break;
            case 2: //opcion ubucar en el mapap
                break;
        }

        return super.onContextItemSelected(item);
    }

    class EliminarEstudianteTask implements Runnable {

        @Override
        public void run() {
            eliminar();
        }
    }


    private void eliminar(){
        int pos = estudianteAdapter.posicionItemSeleccionadoRecyclerView;
        int id = Estudiante.listaEstudiantes.get(pos).getId();

        //Instanciar la clase cliente
        Estudiante estudiante = new Estudiante();
        estudiante.setId(id);
        long r = estudiante.eliminar();
        if (r > 0){
            listar();
            Helper.mensajeInformacion(this.getContext(),"Estudiante","Se ha eliminado correctamente");
        }
    }
}
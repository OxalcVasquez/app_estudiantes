package com.vasquez.fernandez.jordan.appestudiantes.adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vasquez.fernandez.jordan.appestudiantes.R;
import com.vasquez.fernandez.jordan.appestudiantes.datos.Conexion;
import com.vasquez.fernandez.jordan.appestudiantes.logica.Estudiante;
import com.vasquez.fernandez.jordan.appestudiantes.utils.Helper;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class EstudianteAdapter extends RecyclerView.Adapter<EstudianteAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Estudiante> listaEstudiantesAux;
    public int posicionItemSeleccionadoRecyclerView;

    public EstudianteAdapter(Context context){
        this.context = context;
        listaEstudiantesAux = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_estudiante,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Estudiante estudiante = listaEstudiantesAux.get(position);
        holder.txtNombres.setText(estudiante.getNombre());
        holder.txtDireccion.setText("Direccion : " +estudiante.getDireccion());
        holder.txtTelefono.setText("Telefono : " +estudiante.getTelefono());
        holder.txtTipo.setText(estudiante.getTipoEstudiante());
        holder.txtMembresia.setText(estudiante.isMembresia()?"CON MEMBRESIA":"SIN MEMBRESIA");
        holder.txtPromedio.setText("Promedio Semestral : " +String.valueOf(estudiante.getPromedio()));
        holder.txtTipo.setText(estudiante.getTipoEstudiante());
        holder.txtSexoFecha.setText("Sexo : " + (estudiante.isSexo()?"M":"F") + " Fecha Nac :" + estudiante.getFechaNacimiento());

        if (estudiante.getPromedio() > 13.5){
            holder.txtPromedio.setTextColor(context.getResources().getColor(android.R.color.holo_green_dark));
        } else {
            holder.txtPromedio.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));

        }

        if (estudiante.isMembresia()){
            holder.txtMembresia.setTextColor(context.getResources().getColor(android.R.color.holo_green_dark));
        } else {
            holder.txtMembresia.setTextColor(context.getResources().getColor(android.R.color.holo_red_dark));
        }

        if (estudiante.getFoto()==null){
            holder.imgEstudiante.setImageResource(R.drawable.profile);
        } else {
            //Mostrar la foto real grabada en la base de datos
            holder.imgEstudiante.setImageBitmap(Helper.base64ToImage(estudiante.getFoto()));
        }
    }

    @Override
    public int getItemCount() {
        return listaEstudiantesAux.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, View.OnLongClickListener {
        //Declarar
        TextView txtSexoFecha,txtNombres,txtTelefono,txtDireccion,txtTipo,txtMembresia,txtPromedio;
        CircleImageView imgEstudiante;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Controles
            txtSexoFecha = itemView.findViewById(R.id.txt_sexo_fecha);
            txtNombres = itemView.findViewById(R.id.txt_nombres);
            txtTelefono = itemView.findViewById(R.id.txt_telefono);
            txtDireccion = itemView.findViewById(R.id.txt_direccion);
            txtTipo = itemView.findViewById(R.id.txt_tipo_estudiante);
            txtMembresia = itemView.findViewById(R.id.txt_membresia);
            txtPromedio = itemView.findViewById(R.id.txt_promedio);
            imgEstudiante = itemView.findViewById(R.id.img_estudiante);
            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Opciones");
            contextMenu.add(0,1,0,"Eliminar");
            contextMenu.add(0,2,0,"Ubicar en el mapa");
        }

        @Override
        public boolean onLongClick(View view) {
            posicionItemSeleccionadoRecyclerView = getAdapterPosition();
            return false;
        }
    }

    public void cargarDatosEstudiante(ArrayList<Estudiante> listaDatos){
        listaEstudiantesAux = listaDatos;
        notifyDataSetChanged();
    }
}

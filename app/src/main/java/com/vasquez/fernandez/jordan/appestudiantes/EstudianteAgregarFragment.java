package com.vasquez.fernandez.jordan.appestudiantes;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.RadioButton;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.vasquez.fernandez.jordan.appestudiantes.datos.Conexion;
import com.vasquez.fernandez.jordan.appestudiantes.logica.Estudiante;
import com.vasquez.fernandez.jordan.appestudiantes.logica.TipoEstudiante;
import com.vasquez.fernandez.jordan.appestudiantes.utils.Gallery;
import com.vasquez.fernandez.jordan.appestudiantes.utils.Helper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;


public class EstudianteAgregarFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    TextInputEditText txtFecha,txtNombre,txtTelefono,txtContrasena,txtPromedio,txtDireccion;
    CircleImageView imgEstudiante;
    RadioButton rbMasculino,rbFemino;
    AutoCompleteTextView actvTipo;
    SwitchMaterial swMembresia;
    MaterialButton btnRegistrar,btnLimpiar;
    Calendar calendar = Calendar.getInstance();
    int posicionItemSeleccionadoActvTipo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_estudiante_agregar, container, false);
        getActivity().setTitle("Agregar estudiante");
        Conexion.contextApp= this.getContext();
        txtFecha = view.findViewById(R.id.txt_estudiante_fecha);
        txtFecha.setOnClickListener(this);
        actvTipo = view.findViewById(R.id.actv_estudiante_tipo);
        imgEstudiante = view.findViewById(R.id.img_estudiante);
        txtNombre = view.findViewById(R.id.txt_estudiante_nombre);
        txtTelefono = view.findViewById(R.id.txt_estudiante_telefono);
        txtPromedio = view.findViewById(R.id.txt_estudiante_promedio);
        txtContrasena = view.findViewById(R.id.txt_estudiante_contrasena);
        txtDireccion = view.findViewById(R.id.txt_estudiante_direccion);
        rbMasculino = view.findViewById(R.id.rb_masculino);
        rbMasculino.setChecked(true);
        actvTipo.setOnItemClickListener(this);
        swMembresia = view.findViewById(R.id.sw_membresia);
        btnRegistrar = view.findViewById(R.id.btn_estudiante_registrar);
        btnLimpiar = view.findViewById(R.id.btn_estudiante_limpiar);
        imgEstudiante.setTag("foto_no_real");

        btnRegistrar.setOnClickListener(this);
        btnLimpiar.setOnClickListener(this);
        imgEstudiante.setOnClickListener(this);
        this.cargarTipos();
        return view;
    }

    DatePickerDialog.OnDateSetListener fecha = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
            calendar.set(Calendar.YEAR,anio);
            calendar.set(Calendar.MONTH,mes);
            calendar.set(Calendar.DAY_OF_MONTH,dia);
            actualizarCalendario();

        }
    };
    private void actualizarCalendario(){
        String format = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        txtFecha.setText(sdf.format(calendar.getTime()));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_estudiante_registrar:
                registrar();
                break;
            case R.id.txt_estudiante_fecha:
                new DatePickerDialog(getActivity(),fecha,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                        ).show();
                break;
            case R.id.img_estudiante:
                abrirGaleria();
                break;
        }
    }

    private void cargarTipos(){
        String nombreTipos[] = new TipoEstudiante().obtenernombreTiposes();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1,nombreTipos);
        actvTipo.setAdapter(adapter);
    }

    public static final int REQUEST_PICK = 1;

    private void abrirGaleria() {
        startActivityForResult(new Intent(Intent.ACTION_PICK).setType("image/*"),REQUEST_PICK);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_PICK){
            //El activity que ha devuelto resultado es la galeria
            if (resultCode == Activity.RESULT_OK){
                Uri rutaImage = data.getData();
                try {
                    //Otorga la orientacion correcta a la imagen
                    Bitmap bitmap = Gallery.rotateImage(this.getActivity(),rutaImage, Gallery.getOrientation(this.getActivity(),rutaImage));
                    Bitmap bitmapCompress = Gallery.compress(bitmap);
                    imgEstudiante.setImageBitmap(bitmapCompress);
                    imgEstudiante.setTag("foto_real");
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void registrar(){
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(this.txtNombre.getText().toString().toUpperCase());
        estudiante.setDireccion(this.txtDireccion.getText().toString().toUpperCase());
        estudiante.setTelefono(this.txtTelefono.getText().toString().toUpperCase());
        estudiante.setPromedio(Double.parseDouble(this.txtPromedio.getText().toString()));
        estudiante.setContrasena(Helper.convertPassMd5(this.txtContrasena.getText().toString()));
        estudiante.setMembresia(swMembresia.isChecked());
        estudiante.setTipoEstudianteId(posicionItemSeleccionadoActvTipo);
        estudiante.setFechaNacimiento(this.txtFecha.getText().toString());
        estudiante.setSexo(rbMasculino.isChecked());
        if (imgEstudiante.getTag().equals("foto_real")){
            estudiante.setFoto(
                    Helper.imageToBase64(
                            ((BitmapDrawable) imgEstudiante.getDrawable()).getBitmap()
                    )
            );

        } else {
            estudiante.setFoto(null);
        }

        long r = estudiante.registrar();
        if (r>0){
            Helper.mensajeInformacion(this.getContext(),"Estudiante","Se ha registrado correctamente");
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        posicionItemSeleccionadoActvTipo = i + 1;

    }
}
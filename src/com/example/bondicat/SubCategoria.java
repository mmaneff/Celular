package com.example.bondicat;

import java.util.ArrayList;

import com.example.publicidad.Utils;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class SubCategoria extends Activity implements HiddenFragment.TaskCallbacks {
	
	/*
    Etiqueta de referencia del fragmento invisible
     */
    public static final String HIDDEN_FRAGMENT_TAG = "SubCategoriaFragment";
    
	/*
    Instancia del Fragmento
     */
    HiddenFragment fragment;
    
	//Variables privadas
    private int idcategoria;
    private String nombre;
    private ListView lstOpciones;
    private ImageView imgPublicidad;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subcategoria);

        //Recupero los datos de la pantalla categorias
        Bundle bundle = this.getIntent().getExtras();
        idcategoria = bundle.getInt("ID");
        
        //Cargo la lista de subcategorias
        CargarLista();

        //Maneja el evento onClick de un elemento seleccionado en el listView
        lstOpciones.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,long id) {
                nombre = ((itemsubcate)a.getItemAtPosition(position)).getNombre();
                
                Intent intent = new Intent(SubCategoria.this, Negocios.class);
                intent.putExtras(crearBundle(position));
                startActivity(intent);
            }
        });
        
        // Obtener referencia del fragmento
        fragment = (HiddenFragment)getFragmentManager().
                findFragmentByTag(HIDDEN_FRAGMENT_TAG);
    }       
    
    /**
     * 
     * @param position
     * @return
     */
    private Bundle crearBundle(int position) {
    	Bundle bundle = new Bundle();
        bundle.putInt("ID" , position);
        bundle.putInt("categoria", idcategoria);
        bundle.putString("nombre", nombre);
        bundle.putString("subcategoria", nombre);
        
        return bundle;
    }
    
    /**
     * 
     */
    private void CargarLista() {
        lstOpciones = (ListView)findViewById(R.id.listView1);
        
        //Obtengo la lista de items en base a la categoria seleccionada
        ArrayList<itemsubcate> itemslista = obtenerItems();

        //Cargo el listView
        ItemListaAdapterSub adapter = new ItemListaAdapterSub(this, itemslista);        
        lstOpciones = (ListView)findViewById(R.id.listView1);
        lstOpciones.setAdapter(adapter);
    }
    
    /**
     * Retorno un arreglo de subCategorias en base a la categoria seleccionada
     * @return
     */
    private ArrayList<itemsubcate> obtenerItems() {

        ArrayList<itemsubcate> items = new ArrayList<itemsubcate>();
        LinearLayout rl=(LinearLayout)findViewById(R.id.relativeLayout2);
        ImageView imagen=(ImageView)findViewById(R.id.imageView2);

        if(idcategoria==0){
        	rl.setBackgroundColor(getResources().getColor(R.color.red_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconoalimentos));
            items.add(new itemsubcate(1, "CARNICERIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "FORRAJERIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(3, "MAYORISTA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(4, "MINI SERVICE",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(5, "PESCADERIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(6, "POLLERIA", getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(7, "SUPER/HIPER", getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(8, "VERDULERIA", getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("ALIMENTOS");
//		    ImageView banner=(ImageView)findViewById(R.id.imageView3);
//		    banner.setImageDrawable(getResources().getDrawable(R.drawable.banner2));
        }
        if(idcategoria==1){
        	rl.setBackgroundColor(getResources().getColor(R.color.orange_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconoauto));
            items.add(new itemsubcate(1, "BATERIAS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "COMPRA/VENTA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(3, "ELECTRICISTA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(4, "LAVADERO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(5, "LUBRICENTRO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(6, "MECANICO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(7, "MOTO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(8, "NEUMATICOS", getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(9, "REPUESTOS", getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("AUTOMOTORES");
//			    ImageView banner=(ImageView)findViewById(R.id.imageView3);
//			    banner.setImageDrawable(getResources().getDrawable(R.drawable.banner3));
        }
        if(idcategoria==2){
        	rl.setBackgroundColor(getResources().getColor(R.color.yellow_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconobanco));
            items.add(new itemsubcate(1, "PRESTAMOS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "BANCOS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("BANCO/TARJETAS");
        }
        if(idcategoria==3){
        	rl.setBackgroundColor(getResources().getColor(R.color.green_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconobelleza));
            items.add(new itemsubcate(1, "CAMA SOLAR",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "CENTRO DE ESTETICA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(3, "COSMETICOS/TOLOGOS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(4, "MAKE UP",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(5, "MODISTA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(6, "PELUQUERIA", getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(7, "PERFUMERIA", getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(8, "PODOLOGO", getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("BELLEZA");

        }
        if(idcategoria==4){
        	rl.setBackgroundColor(getResources().getColor(R.color.blue_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconoboutique));
            items.add(new itemsubcate(1, "CASA DE DEPORTES",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(4, "INDUMENTARIA MASCULINA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(5, "INDUMENTARIA MUJER",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(7, "INDUMENTARIA BEBES/NIÑOS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(9, "RELOJERIA/JOYERIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("BOUTIQUE");

        }
        if(idcategoria==5){
        	rl.setBackgroundColor(getResources().getColor(R.color.deepPurple_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconoservicios));
            items.add(new itemsubcate(1, "METROPOLITANA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "CIUDADANA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("CIUDADANA Y METROPOLITANA");

        }
        if(idcategoria==6){
        	rl.setBackgroundColor(getResources().getColor(R.color.purple_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconoconstruccion));
            items.add(new itemsubcate(1, "AIRE ACONDICIONADO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "ALUMINIO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(3, "CARPINTERO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(4, "CERRAJERIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(5, "CORRALON",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(6, "ELECTRICIDAD",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(7, "FERRETERIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(8, "HERRERIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(9, "INSTALACION DE LONAS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(10, "MADERAS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(11, "PISOS Y CERAMICOS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(12, "PORTONES ELECTRICOS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(13, "VIDRIERIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("CONSTRUCCION");

        }
        if(idcategoria==7){
        	rl.setBackgroundColor(getResources().getColor(R.color.red_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconohogar));
            items.add(new itemsubcate(1, "AROMATIZANTES",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "BAZAR",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(3, "FUMIGACION",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(4, "LONERA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(5, "MERCERIA/REGALERIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(6, "MUEBLES",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(11, "SERVICIO DE LIMPIEZA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(10, "SERVICIO PILETA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(12, "VIVERO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("DECORACION Y HOGAR");

        }
        if(idcategoria==8){
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listaeducacionsubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#956500"));
        	rl.setBackgroundColor(getResources().getColor(R.color.orange_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconotiempoyrecreacion));
            items.add(new itemsubcate(1, "ARTES MARCIALES",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "BALLET",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(3, "BICICLETERIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(4, "CANCHA FUTBOL5",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(5, "CASA DE DEPORTES",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(6, "GYM",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(7, "MOUNTAIN BIKE",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(8, "NATACION",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(9, "PERSONAL TRAINER",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(10, "TENIS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(11, "YOGA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("DEPORTES");

        }
        if(idcategoria==9){
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listaeducacionsubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#956500"));
        	rl.setBackgroundColor(getResources().getColor(R.color.yellow_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconoeducacion));
            items.add(new itemsubcate(1, "COLEGIO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "INSTITUTO DE INGLES",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(3, "JARDINES Y MATERNALES",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(4, "LIBRERIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(5, "PARTICULARES PRI/SEC",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(6, "PARTICULARES SECUNDARIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(7, "PROF. DE IDIOMAS(FRANCES)",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(8, "PROF. DE IDIOMAS(INGLES)",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("EDUCACION");

        }
        if(idcategoria==10){
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listaemergenciasubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#006FA4"));
        	rl.setBackgroundColor(getResources().getColor(R.color.green_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconoemergencia));
            items.add(new itemsubcate(1, "BOMBEROS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "COMISARIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "EMERGENCIA MEDICA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(3, "HOSPITAL",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(4, "PATRULLA MOTORIZADA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(5, "POLICIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(6, "SERVICIO SACERDOTAL",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("EMERGENCIAS");

        }
        if(idcategoria==11){
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listaeventosyfiestassubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#CC00CC"));
        	rl.setBackgroundColor(getResources().getColor(R.color.blue_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconotiempoyrecreacion));
            items.add(new itemsubcate(1, "BALLET",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "EQUITACION/POLO/CABALGATAS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(3, "GIMNASIA ARTISTICA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(4, "MUSEOS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(5, "PROF. DE MUSICA(BATERIA)",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(6, "PROF. DE MUSICA(FLAUTA TRAVERSA)",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(7, "PROF. DE MUSICA(GUITARRA)",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(8, "PROF. DE MUSICA(GUITARRA-PIANA)",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(9, "PROF. DE MUSICA(LUTHIER)",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(10, "PROF. DE MUSICA(PIANO)",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(11, "PROF. DE MUSICA(VIOLIN)",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(12, "TALLERES (ARTE)",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(13, "TALLERES (COCINA)",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(14, "TEATRO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(15, "VIDEO CLUB",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(16, "YOGA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("ENTRETENIMIENTO");
        }
        if(idcategoria==12){
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listaeventosyfiestassubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#CC00CC"));
        	rl.setBackgroundColor(getResources().getColor(R.color.deepPurple_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconoeventosyfiestas));
            items.add(new itemsubcate(1, "ALQ. DE BARRAS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "CARPAS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(3, "CATERING",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(4, "COTILLON/DISFRAZ",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(5, "DELIVERY DE ALCOHOL",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(6, "FOTOGRAFIA/FILMACION",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(7, "INFLABLES/JUEGOS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(8, "MAGOS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(9, "MUSICA/ILUMINACION",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(10, "PASTELERIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(11, "SALONES DE FIESTAS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(12, "SALONES INFANTILES",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(13, "TITERES",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("EVENTOS Y FIESTAS");
        }
        if(idcategoria==13){
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listagastronomiasubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#00AE2C"));
        	rl.setBackgroundColor(getResources().getColor(R.color.purple_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconogastronomia));
            items.add(new itemsubcate(1, "CAFETERIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "EMPANADAS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(3, "HELADERIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(4, "PANIFICACION",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(5, "PIZZERIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(6, "PUB",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(7, "RESTAURANTE",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(8, "RESTOBAR",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(9, "ROTISERIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(10, "SANGUCHERIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(11, "SUSHI",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(12, "CARNICERIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(13, "PASTELERIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("GASTRONOMIA");
        }
        if(idcategoria==14){
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listaimprentasubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#9B2F1C"));
        	rl.setBackgroundColor(getResources().getColor(R.color.red_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconoimprenta));
            items.add(new itemsubcate(1, "FOTOCOPIAS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "IMPRENTA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("IMPRENTA");
        }
        if(idcategoria==15){
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listaprofesionalessubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#76329A"));
        	rl.setBackgroundColor(getResources().getColor(R.color.orange_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconobanco));
            items.add(new itemsubcate(1, "PAGO FACIL",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "RAPIPAGO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("PAGO FACIL Y RAPIPAGO");
        }
        if(idcategoria==16){
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listaprofesionalessubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#76329A"));
        	rl.setBackgroundColor(getResources().getColor(R.color.yellow_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconoprofesionales));
            items.add(new itemsubcate(1, "ABOGADO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "AGRIMENSOR",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(3, "ARQUITECTO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(4, "CONTADOR",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(5, "VETERINARIO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("PROFESIONALES");
        }
        if(idcategoria==17){
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listasaludsubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#37618E"));
        	rl.setBackgroundColor(getResources().getColor(R.color.green_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconosalud));
            items.add(new itemsubcate(1, "CENTRO MEDICO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "ESPECIALISTA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(3, "FARMACIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(4, "KINESIOLOGIA/FISIOTERAPIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(5, "LABORATORIO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(6, "NUTRICIONISTA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(7, "ODONTOLOGO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(8, "OPTICA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(9, "OTORRINONARINGOLOGO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(10, "PEDAGOGIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(11, "PEDIATRA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("SALUD");
        }
        if(idcategoria==18){
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listaseguridadsubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#864483"));
        	rl.setBackgroundColor(getResources().getColor(R.color.blue_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconoseguridad));
            items.add(new itemsubcate(1, "ALARMAS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "SEGUROS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(3, "SERVICIO DE CAMARAS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(3, "SERVICIO DE SEGURIDAD",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("SEGURIDAD");
        }
        if(idcategoria==19){
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listaserviciosnegocios));
        	//rl.setBackgroundColor(Color.parseColor("#A4A400"));
        	rl.setBackgroundColor(getResources().getColor(R.color.deepPurple_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconoservicios));
            items.add(new itemsubcate(1, "AIRE ACONDICIONADO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "ELECTRICISTA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(3, "ELECTRODOMESTICO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(4, "FLETES",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(5, "GASISTA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(6, "INMOBILIARIA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(7, "JARDINERO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(8, "PINTOR",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(9, "PLOMERO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(10, "CARPINTERO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(11, "SERVICIO DE COMPUTACION",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(12, "SERVICIO DE TELEFONO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("SERVICIOS");
        }
        if(idcategoria==20){
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listaserviciospublicossubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#820228"));
        	rl.setBackgroundColor(getResources().getColor(R.color.purple_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconoserviciospublicos));
            items.add(new itemsubcate(1, "AEROPARQUE",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "ANSES",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(3, "ATENCION AL VECINO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(4, "BRIGADA DE INVESTIGACION NORTE",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(5, "CAMION VERDE",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(6, "CANAL 10",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(7, "CANAL 8",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(8, "CENTRO CONTROL DE MONITOREO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(9, "COMANDO RADIOELECTRICO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(10, "CORREO ARGENTINO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(11, "DEFENSA CIVIL",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(12, "EDET",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(13, "GASNOR",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(14, "LA GACETA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(15, "MUNICIPALIDAD",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(16, "OBRAS PUBLICAS YERBA BUENA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(17, "SAT",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(18, "TERMINAL OMNIBUS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("SERVICIOS PUBLICOS");
        }
        if(idcategoria==21){
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listashoppingygaleriassubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#029930"));
        	rl.setBackgroundColor(getResources().getColor(R.color.red_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconoshopping));
            items.add(new itemsubcate(1, "LOS TRONCOS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "OPEN PLAZA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(3, "PORTAL",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(4, "SOLAR",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(5, "YERBA BUENA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("SHOPPINGS Y GALERIAS");
        }
        if(idcategoria==22){
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listatransportesubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#660033"));
        	rl.setBackgroundColor(getResources().getColor(R.color.orange_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconotransportes));
            items.add(new itemsubcate(1, "TAXI YERBA BUENA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "TAXI CENTRO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("TRANSPORTE");
        }
        if(idcategoria==23){
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listaturismosubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#333333"));
        	rl.setBackgroundColor(getResources().getColor(R.color.yellow_800));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconoturismo));
            items.add(new itemsubcate(1, "HOTEL",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "AGENCIA DE VIAJE",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(3, "BOLETOS OMNIBUS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("TURISMO");
        }
        return items;
    }

    /**
     * 
     */
    private void iniciarPublicidad() {
        FragmentManager fg = getFragmentManager();
        //fragment = new HiddenFragment(Utils.IMAGENES_SUBCATEGORIA_IDS.length);
        fragment = new HiddenFragment(Utils.IMAGENES_PUBLICIDAD_VERTICAL_IDS.length);
        FragmentTransaction transaction = fg.beginTransaction();
        transaction.add(fragment, HIDDEN_FRAGMENT_TAG);
        transaction.commit();
    }
    
    /**
     * Es en este lugar en donde lanzo las animaciones de la publicidad
     */
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	Log.i("onResume", "Categorias - Cargo y muestro la publicidad");
    	//Inicio la publicidad
        iniciarPublicidad();  
    }
    
    /**
     * En esta sección de detiene la publicidad
     */
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	Log.i("onPause", "Categorias - Detengo publicidad y libero recursos");
    	//Detengo la publicidad
    	fragment.publicityTask.cancel(true);
    	fragment = null;
    }
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    	this.finish();
    }
    
	@Override
	public void onPreExecute() {
		imgPublicidad = (ImageView)findViewById(R.id.imgPublicidad);
	}

	@Override
	public void onProgressUpdate(int index) {
		//imgPublicidad.setImageResource(Utils.IMAGENES_SUBCATEGORIA_IDS[index]);
		imgPublicidad.setImageResource(Utils.IMAGENES_PUBLICIDAD_VERTICAL_IDS[index]);
        Animation rotateImage = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        imgPublicidad.startAnimation(rotateImage);
	}

	@Override
	public void onCancelled() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPostExecute() {
		// TODO Auto-generated method stub
		
	}



}

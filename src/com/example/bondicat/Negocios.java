package com.example.bondicat;

import java.util.ArrayList;

import com.example.entidades.Comercio;
import com.example.publicidad.Utils;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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

public class Negocios extends Activity implements LocationListener, HiddenFragment.TaskCallbacks{

	/*
    Etiqueta de referencia del fragmento invisible
     */
    public static final String HIDDEN_FRAGMENT_TAG = "NegocioFragment";
    
	/*
    Instancia del Fragmento
     */
    HiddenFragment fragment;
    
    //A good practice is to define database field names as constants
    private static final String TABLE_NAME = "comercio";
    private static final String FRIEND_ID = "_id";
    private static final String FRIEND_NAME = "name";

    private SQLiteDatabase database;
    private ListView listView;
    private ArrayList comercios;
    private TextView tvTituloNegocio;
    private ImageView imgPublicidad;

    ListView lstOpciones;
    int idcategoria;
    int idsubcategoria;
    String nombre;
    String longitud;
    String latitud;
    String subcategoria;
    int colorTexto;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.negocios);

//        this.root = (FlyOutContainer) this.getLayoutInflater().inflate(R.layout.negocios, null);
//
//        this.setContentView(root);
        
        //Recupero los datos de la pantalla categorias
        Bundle bundle = this.getIntent().getExtras();
        idsubcategoria=bundle.getInt("ID");
        idcategoria=bundle.getInt("categoria");
        nombre=bundle.getString("nombre");
        System.out.println("nombre = " + nombre);
        //System.out.println(nombre);
        //iniciarServicio();
        //CargarLista();
        tvTituloNegocio = (TextView)findViewById(R.id.tvTituloNegocio);
        subcategoria = bundle.getString("subcategoria");
        tvTituloNegocio.setText(subcategoria);

        CargarLista2();


        lstOpciones.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,long id) {
                if(id > 0){
                	int vista = getVista(id);
                    Bundle bundle = new Bundle();
                    bundle.putLong("ID", id );
                    bundle.putInt("datos_activity", vista);                    
                    Intent intent = new Intent(Negocios.this, Datos.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }
        });
        
     // Obtener referencia del fragmento
        fragment = (HiddenFragment)getFragmentManager().
                findFragmentByTag(HIDDEN_FRAGMENT_TAG);
    }

    private void CargarLista2() {
        // TODO Auto-generated method stub
        ArrayList<ItemNegocio> itemslista = obtenerItems2();
        System.out.println("Entrï¿½ en cargarLista2()");

        ItemNegocioAdapter adapter = new ItemNegocioAdapter(this, itemslista, colorTexto);
        lstOpciones = (ListView)findViewById(R.id.listView1);

        lstOpciones.setAdapter(adapter);
    }

    private ArrayList<ItemNegocio> obtenerItems2() {
        ArrayList<ItemNegocio> items = new ArrayList<ItemNegocio>();
        //RelativeLayout rl=(RelativeLayout)findViewById(R.id.relativeLayout2);


        if(nombre!=null) {
//			rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondoalimentos));
            establecerColorDeFondo();
            ArrayList<Comercio> comercios = getComerciosPorCategoria(nombre);
            for(Comercio comercio : comercios) {
                items.add(new ItemNegocio(comercio.getId(), comercio.getRazonSocial(),getResources().getDrawable(R.drawable.logonegociosbuscafacil),"",comercio.getDomicilio()));
            }

            //	    items.add(new ItemNegocio(1, "Automotores Rodriguez Hnos",getResources().getDrawable(R.drawable.logoautomotor),"1Km","Av.Alem 853"));
        } else {
            System.out.println("No entrï¿½ en CARNICERIA");
        }



        //	    if(idcategoria==0 && idsubcategoria==1){
        //	    	rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondoautomotores));
        //	      items.add(new ItemNegocio(1, "Kotler",getResources().getDrawable(R.drawable.imanodisp),"1Km","Av. Nestor Kirchner 1604"));
        //	      items.add(new ItemNegocio(2, "Equipauto SK",getResources().getDrawable(R.drawable.imanodisp),"1Km","Av.Roca 971"));
        //
        //	    }
        //	    if(idcategoria==0&& idsubcategoria==2){
        //	    	rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondoautomotores));
        //	    	  items.add(new ItemNegocio(1, "Belgrano Audio Car",getResources().getDrawable(R.drawable.imanodisp),"1Km","Av. Belgrano 1648"));
        //		      items.add(new ItemNegocio(2, "Bernegui Audio Car",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
        //
        //	    }
        //	    if(idcategoria==0 && idsubcategoria==3){
        //	    	rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondoautomotores));
        //	      items.add(new ItemNegocio(1, "Baterias Bernat",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
        //		  items.add(new ItemNegocio(2, "Baterias Yerba Buena",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
        //
        //	    }
        //	    if(idcategoria==1 && idsubcategoria==0){
        //	    	rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondoconstruccion));
        //		  items.add(new ItemNegocio(1, "Aluminios Belgrano",getResources().getDrawable(R.drawable.logocostruccion),"1Km","Juan Jose Paso 885"));
        //		  items.add(new ItemNegocio(2, "Alem Vidrios",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
        //
        //	    }
        //		   if(idcategoria==1 && idsubcategoria==1){
        //			   rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondoconstruccion));
        //		   items.add(new ItemNegocio(1, "Osmo.Tec",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
        //
        //		    }
        //		    if(idcategoria==1&& idsubcategoria==2){
        //		    	rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondoconstruccion));
        //		   items.add(new ItemNegocio(1, "Clide Vargas Vera",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
        //
        //		    }
        //		    if(idcategoria==1 && idsubcategoria==3){
        //		    	rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondoconstruccion));
        //		      items.add(new ItemNegocio(1, "Nivel",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
        //
        //		    }
        //		    if(idcategoria==2 && idsubcategoria==0){
        //		    	rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondohospedaje));
        //			    items.add(new ItemNegocio(1, "Soy Paisajismo Moderno",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
        //			    }
        //			    if(idcategoria==2 && idsubcategoria==1){
        //			    	rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondohospedaje));
        //			      items.add(new ItemNegocio(1, "Nery",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
        //
        //			    }
        //			    if(idcategoria==2&& idsubcategoria==2){
        //			    	rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondohospedaje));
        //			    	  items.add(new ItemNegocio(1, "Poda de Arboles",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
        //
        //			    }
        //			    if(idcategoria==2 && idsubcategoria==3){
        //			    	rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondohospedaje));
        //			      items.add(new ItemNegocio(1, "Imac Riego",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
        //			      items.add(new ItemNegocio(2, "Ingeriego",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
        //
        //			    }
        //			    if(idcategoria==3 && idsubcategoria==0){
        //			    	rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondodecoracionyhogar));
        //				    items.add(new ItemNegocio(1, "A la Lavanderia",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
        //				    items.add(new ItemNegocio(2, "Lavanderia",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
        //
        //			    }
        //				    if(idcategoria==3 && idsubcategoria==1){
        //				    	rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondodecoracionyhogar));
        //				      items.add(new ItemNegocio(1, "Alfombras y Sillones",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
        //				      items.add(new ItemNegocio(2, "Total Clean",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
        //
        //				    }
        //				    if(idcategoria==3&& idsubcategoria==2){
        //				    	rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondodecoracionyhogar));
        //				    	  items.add(new ItemNegocio(1, "La Cenicienta",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
        //
        //				    }
        //				    if(idcategoria==3 && idsubcategoria==3){
        //				    	rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondodecoracionyhogar));
        //				      items.add(new ItemNegocio(1, "Julia R",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
        //					  items.add(new ItemNegocio(2, "Lorena Balangione",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
        //
        //				    }
        return items;
    }

    private void establecerColorDeFondo() {
        //LinearLayout rl = (LinearLayout)findViewById(R.id.relativeLayout2);
        
        System.out.println("NOMBRENOMBRENOMBRE = " + nombre);
        if( (nombre.equals("CARNICERIA") || (nombre.equals("FORRAJERIA")) ||
                nombre.equals("MAYORISTA")) || nombre.equals("POLLERIA") ||
                nombre.equals("SUPER/HIPER") || nombre.equals("VERDULERIA") ) {
        	
            //rl.setBackgroundColor(Color.parseColor("#2F5279"));
        	tvTituloNegocio.setTextColor(getResources().getColor(R.color.red_800));
        	colorTexto = R.color.red_800;
        } else if( (nombre.equals("COMPRAVENTA") || (nombre.equals("MECANICO")) ||
                nombre.equals("MOTO")) || nombre.equals("NEUMATICOS") ||
                nombre.equals("REPUESTOS") || nombre.equals("COMPRA/VENTA") || 
                nombre.equals("ELECTRICISTA")) {
        	//rl.setBackgroundColor(Color.parseColor("#8B4788"));
        	tvTituloNegocio.setTextColor(getResources().getColor(R.color.orange_800));
        	colorTexto = R.color.orange_800;
        } else if( nombre.equals("PRESTAMOS") || nombre.equals("BANCOS")) {
        	//rl.setBackgroundColor(Color.parseColor("#028228"));
        	tvTituloNegocio.setTextColor(getResources().getColor(R.color.yellow_800));
        	colorTexto = R.color.yellow_800;
        } else if( (nombre.equals("CAMA SOLAR") || (nombre.equals("CENTRO DE ESTETICA")) ||
                nombre.equals("COSMETICOS/TOLOGOS")) || nombre.equals("PELUQUERIA") ||
                nombre.equals("PERFUMERIA") || nombre.equals("RELOJERIA/JOYERIA") || 
                nombre.equals("MAKE UP") || nombre.equals("MODISTA") || nombre.equals("PODOLOGO") ) {
        	//rl.setBackgroundColor(Color.parseColor("#6B37C1"));
        	tvTituloNegocio.setTextColor(getResources().getColor(R.color.green_800));
        	colorTexto = R.color.green_800;
        } else if( (nombre.equals("CASA DE DEPORTES") || (nombre.equals("IND DEP")) ||
                nombre.equals("INDUMENTARIA DEPORTIVA")) || nombre.equals("INDUMENTARIA MASCULINA") ||
                nombre.equals("INDUMENTARIA MUJER") || nombre.equals("INDUMENTARIA NIÃ‘O") ||
                nombre.equals("INDUMENTARIA BEBES/NIÑOS") || nombre.equals("JOYERIA") ||
                nombre.equals("RELOJERIA/JOYERIA") ) {
        	//rl.setBackgroundColor(Color.parseColor("#EC4700"));
        	tvTituloNegocio.setTextColor(getResources().getColor(R.color.blue_800));
        	colorTexto = R.color.blue_800;
        } else if( (nombre.equals("AIRE ACONDICIONADO") || (nombre.equals("AUTOMATIZACION DE PORTONES")) ||
                nombre.equals("CERRAJERIA")) || nombre.equals("CORRALON") ||
                nombre.equals("ELECTRICIDAD") || nombre.equals("FERRETERIA") ||
                nombre.equals("GASISTA") || nombre.equals("HERRERIA") ||
                nombre.equals("INSTALACION DE LONAS") || nombre.equals("MADERAS") ||
                nombre.equals("PISOS Y CERAMICOS") || nombre.equals("PORTONES ELECTRICOS") ||
                nombre.equals("VIDRIERIA") || nombre.equals("ALUMINIO") || nombre.equals("CARPINTERO") || 
                nombre.equals("PORTONES ELECTRICOS")) {
        	//rl.setBackgroundColor(Color.parseColor("#C60A00"));
        	tvTituloNegocio.setTextColor(getResources().getColor(R.color.purple_800));
        	colorTexto = R.color.purple_800;
        } else if( (nombre.equals("AROMATIZANTES") || (nombre.equals("BAZAR")) ||
                nombre.equals("FUMIGACION")) || nombre.equals("LONERA") ||
                nombre.equals("MERCERIA/REGALERIA") || nombre.equals("MUEBLES") ||
                nombre.equals("OTROS") || nombre.equals("PILETA") ||
                nombre.equals("REGALERIA") || nombre.equals("SERVICIO PILETA") ||
                nombre.equals("SERVICIO DE LIMPIEZA") || nombre.equals("VIVERO") ) {
        	//rl.setBackgroundColor(Color.parseColor("#AA413E"));
        	tvTituloNegocio.setTextColor(getResources().getColor(R.color.red_800));
        	colorTexto = R.color.red_800;
        } else if( (nombre.equals("COLEGIO") || (nombre.equals("INSTITUTO DE INGLES")) ||
                nombre.equals("LIBRERIA")) || nombre.equals("JARDINES Y MATERNALES") ||
                nombre.equals("PARTICULARES PRI/SEC") || nombre.equals("PARTICULARES SECUNDARIA") ||
                nombre.equals("PROF. DE IDIOMAS(FRANCES)") || nombre.equals("PROF. DE IDIOMAS(INGLES)") ||
                nombre.equals("LIBRERIA") ) {
        	//rl.setBackgroundColor(Color.parseColor("#956500"));
        	tvTituloNegocio.setTextColor(getResources().getColor(R.color.yellow_800));
        	colorTexto = R.color.yellow_800;
        } else if( (nombre.equals("BOMBEROS") || (nombre.equals("COMISARIA")) ||
                nombre.equals("HOSPITAL")) || nombre.equals("MUNICIPALIDAD") ||
                nombre.equals("POLICIA") || nombre.equals("SERVICIO SACERDOTAL") ||
                nombre.equals("PATRULLA MOTORIZADA") || nombre.equals("EMERGENCIA MEDICA")) {
        	//rl.setBackgroundColor(Color.parseColor("#006FA4"));
        	tvTituloNegocio.setTextColor(getResources().getColor(R.color.green_800));
        	colorTexto = R.color.green_800;
        } else if( (nombre.equals("ALQ. DE BARRAS") || (nombre.equals("ALQ. DE LIVINGS")) ||
                nombre.equals("CARPAS")) || nombre.equals("CATERING") ||
                nombre.equals("COTILLON/DISFRAZ") || nombre.equals("DELIVERY DE ALCOHOL") ||
                nombre.equals("FOTOGRAFIA/FILMACION") || nombre.equals("INFLABLES/JUEGOS") ||
                nombre.equals("MAGOS") || nombre.equals("MUSICA/ILUMINACION") || nombre.equals("PASTELERIA") ||
                nombre.equals("SALONES DE FIESTAS") || nombre.equals("SALONES INFANTILES") ||
                nombre.equals("TITERES") ) {
        	//rl.setBackgroundColor(Color.parseColor("#CC00CC"));
        	tvTituloNegocio.setTextColor(getResources().getColor(R.color.deepPurple_800));
        	colorTexto = R.color.deepPurple_800;
        } else if( (nombre.equals("BAR") || (nombre.equals("CAFETERIA")) ||
                nombre.equals("RESTOBAR")) || nombre.equals("CARNICERIA") ||
                nombre.equals("EMPANADAS") || nombre.equals("HELADERIA") ||
                nombre.equals("PANIFICACION") || nombre.equals("PASTELERIA") ||
                nombre.equals("PIZZERIA") || nombre.equals("RESTAURANTE") ||
                nombre.equals("ROTISERIA") || nombre.equals("SANGUCHERIA") || 
                nombre.equals("PUB") || nombre.equals("SUSHI")) {
        	//rl.setBackgroundColor(Color.parseColor("#00AE2C"));
        	tvTituloNegocio.setTextColor(getResources().getColor(R.color.purple_800));
        	colorTexto = R.color.purple_800;
        } else if( (nombre.equals("IMPRESIONES") ) ) {
        	//rl.setBackgroundColor(Color.parseColor("#9B2F1C"));
        	tvTituloNegocio.setTextColor(getResources().getColor(R.color.red_800));
        	colorTexto = R.color.red_800;
        } else if( (nombre.equals("ABOGADO") || (nombre.equals("AGRIMENSOR")) ||
                nombre.equals("ARQUITECTO")) || nombre.equals("CONTADOR") ||
                nombre.equals("VETERINARIO") ) {
        	//rl.setBackgroundColor(Color.parseColor("#76329A"));
        	tvTituloNegocio.setTextColor(getResources().getColor(R.color.yellow_800));
        	colorTexto = R.color.yellow_800;
        } else if( (nombre.equals("METROPOLITANA") || (nombre.equals("CIUDADANA"))) ) {
        	//rl.setBackgroundColor(Color.parseColor("#326332"));
        	tvTituloNegocio.setTextColor(getResources().getColor(R.color.deepPurple_800));
        	colorTexto = R.color.deepPurple_800;
        } else if( (nombre.equals("CENTRO MEDICO") || (nombre.equals("ESPECIALISTA")) ||
                nombre.equals("FARMACIA")) || nombre.equals("LABORATORIO") ||
                nombre.equals("NUTRICIONISTA") || nombre.equals("ODONTOLOGO") ||
                nombre.equals("OPTICA") || nombre.equals("OTORRINONARINGOLOGO") ||
                nombre.equals("PEDAGOGIA") || nombre.equals("PEDIATRA") ||
                nombre.equals("KINESIOLOGIA/FISIOTERAPIA") ) {
        	//rl.setBackgroundColor(Color.parseColor("#37618E"));
        	tvTituloNegocio.setTextColor(getResources().getColor(R.color.green_800));
        	colorTexto = R.color.green_800;
        } else if( (nombre.equals("ALARMAS") || (nombre.equals("SEGUROS")) ||
                nombre.equals("SERVICIO DE CAMARAS") || (nombre.equals("SERVICIO DE SEGURIDAD"))) ) {
        	//rl.setBackgroundColor(Color.parseColor("#864483"));
        	tvTituloNegocio.setTextColor(getResources().getColor(R.color.blue_800));
        	colorTexto = R.color.blue_800;
        } else if( (nombre.equals("AIRE ACONDICIONADO") || (nombre.equals("ELECTRICISTA")) ||
                nombre.equals("ELECTRODOMESTICO")) || nombre.equals("FLETES") ||
                nombre.equals("GASISTA") || nombre.equals("INMOBILIARIA") ||
                nombre.equals("JARDINERO") || nombre.equals("PINTOR") ||
                nombre.equals("PLOMERO") || nombre.equals("SERVICIO DE TELEFONO") ||
                nombre.equals("SERVICIO DE COMPUTACION") ) {
        	//rl.setBackgroundColor(Color.parseColor("#A4A400"));
        	tvTituloNegocio.setTextColor(getResources().getColor(R.color.deepPurple_800));
        	colorTexto = R.color.deepPurple_800;
        } else if( (nombre.equals("CORREO ARGENTINO") || (nombre.equals("EDET")) ||
                nombre.equals("GASNOR")) || nombre.equals("SAT") || nombre.equals("AEROPARQUE") || 
                nombre.equals("ANSES") || nombre.equals("ATENCION AL VECINO") || nombre.equals("CANAL 10") || 
                nombre.equals("CANAL 8") || nombre.equals("BRIGADA DE INVESTIGACION NORTE") || 
                nombre.equals("CAMION VERDE") || nombre.equals("CENTRO CONTROL DE MONITOREO") || 
                nombre.equals("COMANDO RADIOELECTRICO") || nombre.equals("DEFENSA CIVIL") || 
                nombre.equals("LA GACETA")|| nombre.equals("MUNICIPALIDAD") || 
                nombre.equals("OBRAS PUBLICAS YERBA BUENA") || nombre.equals("TERMINAL OMNIBUS")) {
        	//rl.setBackgroundColor(Color.parseColor("#820228"));
        	tvTituloNegocio.setTextColor(getResources().getColor(R.color.purple_800));
        	colorTexto = R.color.purple_800;
        } else if( (nombre.equals("LAS PALMERAS") || (nombre.equals("PORTAL")) ||
                nombre.equals("SOLAR")) || nombre.equals("SOLAR MARCOS PAZ") ||
                nombre.equals("YERBA BUENA") ) {
        	//rl.setBackgroundColor(Color.parseColor("#029930"));
        	tvTituloNegocio.setTextColor(getResources().getColor(R.color.red_800));
        	colorTexto = R.color.red_800;
        }
	    else if( (nombre.equals("BALLET") || (nombre.equals("BICICLETERIA")) ||
	            nombre.equals("ARTES MARCIALES")) || nombre.equals("CASA DE DEPORTES") ||
	            nombre.equals("CANCHA FUTBOL5") || nombre.equals("NATACION") ||
	            nombre.equals("GYM") || nombre.equals("MOUNTAIN BIKE") ||
	            nombre.equals("PERSONAL TRAINER") || nombre.equals("TENIS") || nombre.equals("YOGA") ) {
	    	//rl.setBackgroundColor(Color.parseColor("#029D9D"));
	    	tvTituloNegocio.setTextColor(getResources().getColor(R.color.orange_800));
	    	colorTexto = R.color.orange_800;
	    } 
	    else if( (nombre.equals("AGENCIA DE VIAJE") || (nombre.equals("BOLETOS OMNIBUS")) 
        		|| (nombre.equals("HOTEL"))) ) {
        	//rl.setBackgroundColor(Color.parseColor("#333333"));
        	tvTituloNegocio.setTextColor(getResources().getColor(R.color.yellow_800));
        	colorTexto = R.color.yellow_800;
        } else {
        	//rl.setBackgroundColor(Color.parseColor("#836953"));
        	tvTituloNegocio.setTextColor(getResources().getColor(R.color.red_800));
        	colorTexto = R.color.red_800;
        }
    }

    private void CargarLista() {
        // TODO Auto-generated method stub
        ArrayList<ItemNegocio> itemslista = obtenerItems();

        ItemNegocioAdapter adapter = new ItemNegocioAdapter(this, itemslista, colorTexto);
        lstOpciones = (ListView)findViewById(R.id.listView1);

        lstOpciones.setAdapter(adapter);

		/*
		//Agencia de autos
		final String[] datos =  new String[]{"AUTOMOTORES RODRIGUEZ HNOS" +longitud+","+latitud};
		//Accesorio del automor
		final String[] datos1 =  new String[]{"KOTLER","EQUIPAUTO SK"};
		//Audio car
		final String[] datos2 =  new String[]{"BELGRANO AUDIO CAR","BEMERGUI AUDIO CAR"};
		//Baterias
		final String[] datos3 =  new String[]{"BATERIAS BERNAT","BATERIAS YERBA BUENA"};
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos);

		//CONTRUCCION
		//Aberturas de aluminios
		final String[] datos4 =  new String[]{"ALUMINIO BELGRANO","ALEM VIDRIOS"};
		//Anti humedad / humedad cimientos
		final String[] datos5 =  new String[]{"OSMO.TEC"};
		//Yesero
		final String[] datos6 =  new String[]{"CLIDE VARGAS VERA"};

		//Amoblamiento y carpinteria
		final String[] datos7 =  new String[]{"NIVEL"};

		//HOGAR Y JARDINES
		//Diseï¿½o y mantenimiento
		final String[] datos8 =  new String[]{"SOY PAISAJISMO MODERNO"};
		//Jardinero
		final String[] datos9 =  new String[]{"NERY"};
		//Poda de arboles
		final String[] datos10 =  new String[]{"PODA DE ARBOLES"};
		//Riego
		final String[] datos11 =  new String[]{"IMAC RIEGO","INGERIEGO"};

		//HOGAR
		//Lavanderia y tintoreria
		final String[] datos12 =  new String[]{"A LA LAVANDERIA","lAVANDERIA"};
		//Limpieza de alfombras
		final String[] datos13 =  new String[]{"ALFOMBRAS Y SILLONES","TOTAL CLEAN"};
		//Limpieza y mantenimiento
		final String[] datos14 =  new String[]{"LA CENICIENTA"};
		//Alta Costura
		final String[] datos15 =  new String[]{"JULIA L.","LORENA BALANGIONE"};

		if(idcategoria==0 && idsubcategoria==0){
		    adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos);	
			}
		if(idcategoria==0 && idsubcategoria==1){
			    adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos1);	
				}
		if(idcategoria==0 && idsubcategoria==2){
			    adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos2);	
				}
		if(idcategoria==0 && idsubcategoria==3){
			    adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos3);	
				}


		if(idcategoria==1 && idsubcategoria==0){
		    adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos4);	
			}
		if(idcategoria==1 && idsubcategoria==1){
			    adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos5);	
				}
		if(idcategoria==1 && idsubcategoria==2){
			    adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos6);	
				}
		if(idcategoria==1 && idsubcategoria==3){
		    adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos7);	
			}


		if(idcategoria==2 && idsubcategoria==0){
		    adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos8);	
			}
		if(idcategoria==2 && idsubcategoria==1){
			    adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos9);	
				}
		if(idcategoria==2 && idsubcategoria==2){
			    adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos10);	
				}
		if(idcategoria==2 && idsubcategoria==3){
			    adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos11);	
				}

		if(idcategoria==3 && idsubcategoria==0){
		    adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos12);	
			}
		if(idcategoria==3 && idsubcategoria==1){
			    adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos13);	
				}
		if(idcategoria==3 && idsubcategoria==2){
			    adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos14);	
				}
		if(idcategoria==3 && idsubcategoria==3){
			    adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos15);	
				}


		lstOpciones = (ListView)findViewById(R.id.listView1);
		lstOpciones.setAdapter(adaptador);

		 */
    }
    public void iniciarServicio(){

        //Crea el objeto que gestiona las localizaciones
        LocationManager handle = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //Obtenemos la ï¿½ltima posiciï¿½n conocida
        //Location loc =handle.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        Criteria c = new Criteria();
        c.setCostAllowed(false);
        c.setAccuracy(Criteria.ACCURACY_FINE);
        //obtiene el mejor proveedor en funciï¿½n del criterio asignado
        //(la mejor precisiï¿½n posible)
        String provider = handle.getBestProvider(c, true);
        //Se activan las notificaciones de localizaciï¿½n con los parï¿½metros: proveedor, tiempo mï¿½nimo de actualizaciï¿½n, distancia mï¿½nima, Locationlistener
        handle.requestLocationUpdates(provider, 10000, 1, this);
        //Obtenemos la ï¿½ltima posiciï¿½n conocida dada por el proveedor
        Location loc = handle.getLastKnownLocation(provider);

        muestraPosicionActual(loc);
    }
    public void muestraPosicionActual(Location loc){
        if(loc == null){//Si no se encuentra localizaciï¿½n, se mostrarï¿½ "Desconocida"
            longitud="Desconocida";;
            latitud="Desconocida";
        }if(loc!=null){//Si se encuentra, se mostrarï¿½ la latitud y longitud
            latitud=(String.valueOf(loc.getLatitude()));
            latitud=(String.valueOf(loc.getLongitude()));
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        // TODO Auto-generated method stub
        muestraPosicionActual(location);
    }

    @Override
    public void onProviderDisabled(String arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
        // TODO Auto-generated method stub

    }
    private ArrayList<ItemNegocio> obtenerItems() {
        ArrayList<ItemNegocio> items = new ArrayList<ItemNegocio>();
        //RelativeLayout rl=(RelativeLayout)findViewById(R.id.relativeLayout2);
        LinearLayout rl = (LinearLayout)findViewById(R.id.relativeLayout2);

        if(idcategoria==0 && idsubcategoria==0){
            rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondoautomotores));

            ArrayList<Comercio> comercios = getComerciosPorCategoria("AUTOMOTORES");
            for(Comercio comercio : comercios) {
                items.add(new ItemNegocio(comercio.getId(), comercio.getRazonSocial(),getResources().getDrawable(R.drawable.logo),"1Km",comercio.getDomicilio()));
            }

            //		    items.add(new ItemNegocio(1, "Automotores Rodriguez Hnos",getResources().getDrawable(R.drawable.logoautomotor),"1Km","Av.Alem 853"));
        }
        if(idcategoria==0 && idsubcategoria==1){
            rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondoautomotores));
            items.add(new ItemNegocio(1, "Kotler",getResources().getDrawable(R.drawable.imanodisp),"1Km","Av. Nestor Kirchner 1604"));
            items.add(new ItemNegocio(2, "Equipauto SK",getResources().getDrawable(R.drawable.imanodisp),"1Km","Av.Roca 971"));

        }
        if(idcategoria==0&& idsubcategoria==2){
            rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondoautomotores));
            items.add(new ItemNegocio(1, "Belgrano Audio Car",getResources().getDrawable(R.drawable.imanodisp),"1Km","Av. Belgrano 1648"));
            items.add(new ItemNegocio(2, "Bernegui Audio Car",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));

        }
        if(idcategoria==0 && idsubcategoria==3){
            rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondoautomotores));
            items.add(new ItemNegocio(1, "Baterias Bernat",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
            items.add(new ItemNegocio(2, "Baterias Yerba Buena",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));

        }
        if(idcategoria==1 && idsubcategoria==0){
            rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondoconstruccion));
            items.add(new ItemNegocio(1, "Aluminios Belgrano",getResources().getDrawable(R.drawable.logo),"1Km","Juan Jose Paso 885"));
            items.add(new ItemNegocio(2, "Alem Vidrios",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));

        }
        if(idcategoria==1 && idsubcategoria==1){
            rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondoconstruccion));
            items.add(new ItemNegocio(1, "Osmo.Tec",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));

        }
        if(idcategoria==1&& idsubcategoria==2){
            rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondoconstruccion));
            items.add(new ItemNegocio(1, "Clide Vargas Vera",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));

        }
        if(idcategoria==1 && idsubcategoria==3){
            rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondoconstruccion));
            items.add(new ItemNegocio(1, "Nivel",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));

        }
        if(idcategoria==2 && idsubcategoria==0){
            rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondohospedaje));
            items.add(new ItemNegocio(1, "Soy Paisajismo Moderno",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
        }
        if(idcategoria==2 && idsubcategoria==1){
            rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondohospedaje));
            items.add(new ItemNegocio(1, "Nery",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));

        }
        if(idcategoria==2&& idsubcategoria==2){
            rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondohospedaje));
            items.add(new ItemNegocio(1, "Poda de Arboles",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));

        }
        if(idcategoria==2 && idsubcategoria==3){
            rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondohospedaje));
            items.add(new ItemNegocio(1, "Imac Riego",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
            items.add(new ItemNegocio(2, "Ingeriego",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));

        }
        if(idcategoria==3 && idsubcategoria==0){
            rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondodecoracionyhogar));
            items.add(new ItemNegocio(1, "A la Lavanderia",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
            items.add(new ItemNegocio(2, "Lavanderia",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));

        }
        if(idcategoria==3 && idsubcategoria==1){
            rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondodecoracionyhogar));
            items.add(new ItemNegocio(1, "Alfombras y Sillones",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
            items.add(new ItemNegocio(2, "Total Clean",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));

        }
        if(idcategoria==3&& idsubcategoria==2){
            rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondodecoracionyhogar));
            items.add(new ItemNegocio(1, "La Cenicienta",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));

        }
        if(idcategoria==3 && idsubcategoria==3){
            rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondodecoracionyhogar));
            items.add(new ItemNegocio(1, "Julia R",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));
            items.add(new ItemNegocio(2, "Lorena Balangione",getResources().getDrawable(R.drawable.imanodisp),"1Km","domicilio"));

        }
        return items;
    }


    //Extracting elements from the database
    private ArrayList<Comercio> getComerciosPorCategoria(String subCategoria) {
        comercios = new ArrayList<Comercio>();
        ExternalDbOpenHelper dbConexion = new ExternalDbOpenHelper(this, "comercio.db");
        database = dbConexion.openDataBase();
        Cursor comercioCursor = database.rawQuery("SELECT id, correoelectronico, detalle, domicilio, latitud, longitud, razonsocial, sitioweb, telefono, telefono2, telefono3, vista FROM comercio WHERE upper(subcategoria) = ?", new String[] {subCategoria.toUpperCase()});
        if(comercioCursor.moveToFirst()) {
            do {
                Comercio comercio = new Comercio();
                if(comercioCursor.getString(0) != null) {
                    comercio.setId(comercioCursor.getLong(0));
                }                
                
                if(comercioCursor.getString(1) != null) {
                    comercio.setCorreoElectronico(comercioCursor.getString(1));
                }
                else {
                	comercio.setCorreoElectronico("");
                }
                	
                if(comercioCursor.getString(2) != null) {
                    comercio.setDetalle(comercioCursor.getString(2));
                }
                else {
                	comercio.setDetalle("");
                }
                
                if(comercioCursor.getString(3) != null) {
                    comercio.setDomicilio(comercioCursor.getString(3));
                }
                else {
                	comercio.setDomicilio("");
                }
                
                if(comercioCursor.getString(4) != null) {
                    comercio.setLatitud(comercioCursor.getString(4));
                }
                else {
                	comercio.setLatitud("");
                }
                
                if(comercioCursor.getString(5) != null) {
                    comercio.setLongitud(comercioCursor.getString(5));
                }
                else {
                	comercio.setLongitud("");
                }
                
                if(comercioCursor.getString(6) != null) {
                    comercio.setRazonSocial(comercioCursor.getString(6));
                }
                else {
                	comercio.setRazonSocial("");
                }
                
                if(comercioCursor.getString(7) != null) {
                    comercio.setSitioWeb(comercioCursor.getString(7));
                }
                else {
                	comercio.setSitioWeb("");
                }
                
                if(comercioCursor.getString(8) != null) {
                    comercio.setTelefono(comercioCursor.getString(8));
                }
                else {
                	comercio.setTelefono("");
                }
                
                if(comercioCursor.getString(9) != null) {
                	comercio.setTelefono2(comercioCursor.getString(9));
                }
                else {
                	comercio.setTelefono2("");
                }
                
                if(comercioCursor.getString(10) != null) {
                	comercio.setTelefono3(comercioCursor.getString(10));
                }
                else {
                	comercio.setTelefono3("");
                }
                
                if(comercioCursor.getString(11) != null) {
                	comercio.setVista(comercioCursor.getInt(11));
                }
                System.out.println("idComercio=" + comercio.getId());
                System.out.println("razonSocial=" + comercio.getRazonSocial());
                comercios.add(comercio);
            } while (comercioCursor.moveToNext());
        }
        comercioCursor.close();
        database.close();
        return comercios;
    }
    
    /**************************************************************************************
     * Esto es lo agregado por Mateo para la publiciad y funciona
     ***************************************************************************************/
    
    /**
     * 
     */
    private void iniciarPublicidad() {
        FragmentManager fg = getFragmentManager();
        fragment = new HiddenFragment(Utils.IMAGENES_NEGOCIO_IDS.length);
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
		imgPublicidad.setImageResource(Utils.IMAGENES_NEGOCIO_IDS[index]);
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
	
	private int getVista(long id) {
		int vista = 1;
		
        ExternalDbOpenHelper dbConexion = new ExternalDbOpenHelper(this, "comercio.db");
        database = dbConexion.openDataBase();
        Cursor comercioCursor = database.rawQuery("SELECT vista FROM comercio WHERE id = ?", new String[] { Long.toString(id) });
        
        comercioCursor.moveToFirst();
	    while (!comercioCursor.isAfterLast()) {
	    	vista  = comercioCursor.getInt(0);
	    	
	    	comercioCursor.moveToNext();
	    }	    
        
        comercioCursor.close();
        database.close();
        return vista;
    }

}

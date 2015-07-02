package com.example.bondicat;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.example.publicidad.PublicidadCategoriaAdapter;
import com.example.publicidad.PublicidadSubCategoriaAdapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class SubCategoria extends Activity{
    int idcategoria;
    String nombre;
    ListView lstOpciones;
    int count = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subcategoria);

//        this.root = (FlyOutContainer) this.getLayoutInflater().inflate(R.layout.subcategoria, null);
//
//        this.setContentView(root);

        //Recupero los datos de la pantalla categorias
        Bundle bundle = this.getIntent().getExtras();
        idcategoria=bundle.getInt("ID");
        CargarLista();

        lstOpciones.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,long id) {
                //lblpulsado.setText("Has pulsado: "+datos[position]);
                //if(position==0){
                nombre=((itemsubcate)a.getItemAtPosition(position)).getNombre();
                System.out.println("nombre000=" + nombre);
                Bundle bundle = new Bundle();
                bundle.putInt("ID" , position );
                bundle.putInt("categoria",idcategoria);
                bundle.putString("nombre",nombre );
                bundle.putString("subcategoria",nombre );
                Intent intent = new Intent(SubCategoria.this, Negocios.class);
                intent.putExtras(bundle);
                startActivity(intent);
                //}
            }
        });
        
        
    }
       

    private void CargarLista() {
        // TODO Auto-generated method stub
        lstOpciones = (ListView)findViewById(R.id.listView1);

        ArrayList<itemsubcate> itemslista = obtenerItems();

        ItemListaAdapterSub adapter = new ItemListaAdapterSub(this, itemslista);

        //final String[] datos =  new String[]{"AGENCIA DE AUTO","ACCESORIOS AUTOMOTOR","AUDIO CAR","BATERIAS"};
        //final String[] datos1 =  new String[]{"ABERTURAS DE ALUMINIOS","ANTI HUMEDAD/HUMEDAD CIMIENTOS","YESERO","AMOBLAMIENTO/ CARPINTERIA"};
        //final String[] datos2 =  new String[]{"DISEï¿½O Y MANTENIMIENTO","JARDINERO","PODA DE ARBOLES","RIEGO"};
        //final String[] datos3 =  new String[]{"LAVANDERIA Y TINTORERIA","LIMPIEZA DE ALFOMBRAS","LIMPIEZA Y MANTENIMIENTO EDILICIO","ALTA COSTURA"};

        //ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos);
        //System.out.println(idcategoria);
        //if(idcategoria==0){
        //adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos);
        //}
        //if(idcategoria==1){
        //   adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos1);
        //	}
        //if(idcategoria==2){
        //   adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos2);
        //	}
        //if(idcategoria==3){
        //  adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, datos3);
        //	}
        lstOpciones = (ListView)findViewById(R.id.listView1);

        lstOpciones.setAdapter(adapter);

    }
    private ArrayList<itemsubcate> obtenerItems() {

        ArrayList<itemsubcate> items = new ArrayList<itemsubcate>();
        //RelativeLayout rl=(RelativeLayout)findViewById(R.id.relativeLayout2);
        LinearLayout rl=(LinearLayout)findViewById(R.id.relativeLayout2);
        ImageView imagen=(ImageView)findViewById(R.id.imageView2);

        if(idcategoria==0){
//            rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listaalimentossubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#2F5279"));
        	rl.setBackgroundColor(getResources().getColor(R.color.green_500));
        	
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
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listaautomotorsubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#8B4788"));
        	rl.setBackgroundColor(getResources().getColor(R.color.indigo_500));
        	
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
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listabancosytarjetassubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#028228"));
        	rl.setBackgroundColor(getResources().getColor(R.color.pink_500));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconobanco));
            items.add(new itemsubcate(1, "PRESTAMOS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "BANCOS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("BANCO/TARJETAS");

        }
        if(idcategoria==3){
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listabellezasubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#6B37C1"));
        	rl.setBackgroundColor(getResources().getColor(R.color.orange_500));
        	
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
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listaboutiquesubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#EC4700"));
        	rl.setBackgroundColor(getResources().getColor(R.color.lime_500));
        	
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
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listaconstruccionsubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#C60A00"));
        	rl.setBackgroundColor(getResources().getColor(R.color.lightBlue_500));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconoservicios));
            items.add(new itemsubcate(1, "METROPOLITANA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "CIUDADANA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("CIUDADANA Y METROPOLITANA");

        }
        if(idcategoria==6){
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listaconstruccionsubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#C60A00"));
        	rl.setBackgroundColor(getResources().getColor(R.color.deepPurple_500));
        	
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
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listadecoracionyhogarsubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#AA413E"));
        	rl.setBackgroundColor(getResources().getColor(R.color.cyan_500));
        	
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
        	rl.setBackgroundColor(getResources().getColor(R.color.lightGreen_500));
        	
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
        	rl.setBackgroundColor(getResources().getColor(R.color.amber_500));
        	
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
        	rl.setBackgroundColor(getResources().getColor(R.color.teal_500));
        	
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
        	rl.setBackgroundColor(getResources().getColor(R.color.yellow_500));
        	
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
        	rl.setBackgroundColor(getResources().getColor(R.color.purple_500));
        	
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
        	rl.setBackgroundColor(getResources().getColor(R.color.red_500));
        	
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
        	rl.setBackgroundColor(getResources().getColor(R.color.lime_500));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconoimprenta));
            items.add(new itemsubcate(1, "FOTOCOPIAS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "IMPRENTA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("IMPRENTA");
        }
        if(idcategoria==15){
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listaprofesionalessubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#76329A"));
        	rl.setBackgroundColor(getResources().getColor(R.color.orange_500));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconobanco));
            items.add(new itemsubcate(1, "PAGO FACIL",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "RAPIPAGO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("PAGO FACIL Y RAPIPAGO");
        }
        if(idcategoria==16){
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listaprofesionalessubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#76329A"));
        	rl.setBackgroundColor(getResources().getColor(R.color.brown_500));
        	
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
        	rl.setBackgroundColor(getResources().getColor(R.color.blueGrey_500));
        	
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
        	rl.setBackgroundColor(getResources().getColor(R.color.grey_500));
        	
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
        	rl.setBackgroundColor(getResources().getColor(R.color.yellow_500));
        	
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
        	rl.setBackgroundColor(getResources().getColor(R.color.cyan_500));
        	
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
        	rl.setBackgroundColor(getResources().getColor(R.color.green_500));
        	
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
        	rl.setBackgroundColor(getResources().getColor(R.color.pink_500));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconotransportes));
            items.add(new itemsubcate(1, "TAXI YERBA BUENA",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "TAXI CENTRO",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("TRANSPORTE");
        }
        if(idcategoria==23){
            //rl.setBackgroundDrawable(getResources().getDrawable(R.drawable.listaturismosubcategoria));
        	//rl.setBackgroundColor(Color.parseColor("#333333"));
        	rl.setBackgroundColor(getResources().getColor(R.color.orange_500));
        	
            imagen.setImageDrawable(getResources().getDrawable(R.drawable.iconoturismo));
            items.add(new itemsubcate(1, "HOTEL",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(2, "AGENCIA DE VIAJE",getResources().getDrawable(R.drawable.imagensubcategoria)));
            items.add(new itemsubcate(3, "BOLETOS OMNIBUS",getResources().getDrawable(R.drawable.imagensubcategoria)));
            TextView texto = (TextView)findViewById(R.id.textView2);
            texto.setText("TURISMO");
        }
        return items;
    }



}

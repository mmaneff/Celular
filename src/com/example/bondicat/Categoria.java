package com.example.bondicat;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.example.publicidad.PublicidadCategoriaAdapter;
import com.example.publicidad.PublicidadPrincipalAdapter;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView.OnEditorActionListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 1-Definir los métodos privados y publicos.
 * 2-Tratar de usar alguna regla para el nombre de las variables o métodos
 * 3-Lo que ya no se use quitar y NO dejar comentado. De esta manera se tiene un código más limpio
 * 		y permite que el mismo sea facilmente mantenible en el futuro.
 * 4-Los nombres de los métodos deben identificar la funcionalidad que hacen. Por ejemplo "cargarListaConFondoDeColor".
 * @author emaneff
 *
 */
public class Categoria extends Activity {

	//Variables locales, por eso se las declara como privada, no podrán ser accedidas desde otra clase
    private ListView lstOpciones;
    private EditText etBuscar;
    static int count = 0;
    //private Publicidad publicidadThread;
    private ViewPager viewPager = null;

    private SQLiteDatabase database;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoria);

//        this.root = (FlyOutContainer) this.getLayoutInflater().inflate(R.layout.categoria, null);

//        this.setContentView(root);

        //Modificar si desean que cada fila de la lista use color en hexa o bien una imagen
        cargarListaConFondoDeImagen();
        //cargarListaConFondoDeColor();
        //cargarbanners();

//	 ExternalDbOpenHelper dbConexion = new ExternalDbOpenHelper(this, "comercio.db");
//     database = dbConexion.openDataBase();
//     dbConexion.hacerConsultaPrueba();

        lstOpciones.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,long id) {
                //lblpulsado.setText("Has pulsado: "+datos[position]);
                // if(position==0){
                Bundle bundle = new Bundle();
                bundle.putInt("ID" , position );
                Intent intent = new Intent(Categoria.this, SubCategoria.class);
                intent.putExtras(bundle);
                startActivity(intent);
                //}
            }
        });
        
        etBuscar = (EditText) findViewById(R.id.editText1);
        etBuscar.setOnEditorActionListener(new OnEditorActionListener() {
    	    @Override
    	    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
    	        boolean handled = false;
    	        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
    	        	Bundle bundle = new Bundle();
                    bundle.putString("busqueda" , etBuscar.getText().toString());
    	        	Intent intent = new Intent(Categoria.this, Busqueda.class);
    	        	intent.putExtras(bundle);
                    startActivity(intent);
    	            handled = true;
    	        }
    	        return handled;
    	    }
    	});
        /*
        PagerAdapter adapter = new PublicidadCategoriaAdapter(Categoria.this);
        viewPager = (ViewPager) findViewById(R.id.viewPager2);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        
        mostrarPublicidad();*/
        
    }
  /*  
    private void mostrarPublicidad() {
    	publicidadThread = new Publicidad();
    	publicidadThread.start();
    }
    
    private class Publicidad extends Thread {
        Timer timer;
        private boolean running = false;
        
        @Override
    	public void run() {
    		running = true;
    		while (running) {
	    		try
	            {
	    			sleep(2000);
	    			runOnUiThread(new Runnable() {
	    				public void run() {
	    					if(count <= 4) {
	    	            		viewPager.setCurrentItem(count);
	    	                    count++;
	    	                }
	    	                else {
	    	                	count = 0;
	    	                    viewPager.setCurrentItem(count);
	    	                }
	    				}
	    			});
	            }
	            catch (InterruptedException e)
	            {
	               e.printStackTrace();
	            }
	            finally
	            {
	              System.out.println("finally");
	            }
        	}
    	}
        
        public void close() {
        	running = false;
        }        
    }
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    	publicidadThread.close();
    	publicidadThread = null;
    }
*/
    /**
     * Este metodo carga la lista. Además el fondo de cada elemento lo pone en base a cada imagen 
     * guardada en el archivo de recurso. 
     */
    private void cargarListaConFondoDeImagen() {
        lstOpciones = (ListView)findViewById(R.id.listView1);
        ArrayList<ItemLista> itemslista = cargarItemsConImagenesDeFondo();

        ItemListaAdapter adapter = new ItemListaAdapter(this, itemslista);

        lstOpciones.setAdapter(adapter);
    }
    
    /**
     * Este metodo carga la lista. Además el fondo de cada elemento lo pone en base a un 
     * color recuperado en hexadecimal. 
     */
    /*
    private void cargarListaConFondoDeColor() {
        lstOpciones = (ListView)findViewById(R.id.listView1);
        ArrayList<ItemLista2> itemslista = cargarItemsConColorDeFondo();

        ItemListaAdapter2 adapter = new ItemListaAdapter2(this, itemslista);

        lstOpciones.setAdapter(adapter);
    }
     */
    /**
     * 
     * @return
     */
    private ArrayList<ItemLista> cargarItemsConImagenesDeFondo() {
        ArrayList<ItemLista> items = new ArrayList<ItemLista>();

        items.add(new ItemLista(1, "ALIMENTOS", getResources().getDrawable(R.drawable.iconoalimentos),getResources().getDrawable(R.drawable.item_color_green)));
        items.add(new ItemLista(2, "AUTOMOTORES",getResources().getDrawable(R.drawable.iconoauto),getResources().getDrawable(R.drawable.item_color_indigo)));
        items.add(new ItemLista(3, "BANCOS Y TARJETAS", getResources().getDrawable(R.drawable.iconobanco),getResources().getDrawable(R.drawable.item_color_pink)));
        items.add(new ItemLista(4, "BELLEZA", getResources().getDrawable(R.drawable.iconobelleza),getResources().getDrawable(R.drawable.item_color_orange)));
        items.add(new ItemLista(5, "BOUTIQUE", getResources().getDrawable(R.drawable.iconoboutique),getResources().getDrawable(R.drawable.item_color_lime)));
        items.add(new ItemLista(6, "CIUDADANA Y METROPOLITANA",getResources().getDrawable(R.drawable.iconoservicios),getResources().getDrawable(R.drawable.item_color_light_blue)));
        items.add(new ItemLista(7, "CONSTRUCCION",getResources().getDrawable(R.drawable.iconoconstruccion),getResources().getDrawable(R.drawable.item_color_deep_purple)));
        items.add(new ItemLista(8, "DECORACION Y HOGAR", getResources().getDrawable(R.drawable.iconohogar),getResources().getDrawable(R.drawable.item_color_cyan)));
        items.add(new ItemLista(9, "DEPORTES", getResources().getDrawable(R.drawable.iconotiempoyrecreacion),getResources().getDrawable(R.drawable.item_color_light_green)));        
        items.add(new ItemLista(10, "EDUCACION", getResources().getDrawable(R.drawable.iconoeducacion),getResources().getDrawable(R.drawable.item_color_amber)));
        items.add(new ItemLista(11, "EMERGENCIA", getResources().getDrawable(R.drawable.iconoemergencia),getResources().getDrawable(R.drawable.item_color_teal)));
        items.add(new ItemLista(12, "ENTRETENIMIENTO", getResources().getDrawable(R.drawable.iconotiempoyrecreacion),getResources().getDrawable(R.drawable.item_color_yellow)));
        items.add(new ItemLista(13, "EVENTOS Y FIESTAS", getResources().getDrawable(R.drawable.iconoeventosyfiestas),getResources().getDrawable(R.drawable.item_color_purple)));
        items.add(new ItemLista(14, "GASTRONOMIA", getResources().getDrawable(R.drawable.iconogastronomia),getResources().getDrawable(R.drawable.item_color_red)));
        items.add(new ItemLista(15, "IMPRENTA", getResources().getDrawable(R.drawable.iconoimprenta),getResources().getDrawable(R.drawable.item_color_lime)));
        items.add(new ItemLista(16, "PAGO FACIL Y RAPIPAGO", getResources().getDrawable(R.drawable.iconobanco),getResources().getDrawable(R.drawable.item_color_deep_orange)));
        items.add(new ItemLista(17, "PROFESIONALES", getResources().getDrawable(R.drawable.iconoprofesionales),getResources().getDrawable(R.drawable.item_color_brown)));
        items.add(new ItemLista(18, "SALUD", getResources().getDrawable(R.drawable.iconosalud),getResources().getDrawable(R.drawable.item_color_light_blue)));
        items.add(new ItemLista(19, "SEGURIDAD", getResources().getDrawable(R.drawable.iconoseguridad),getResources().getDrawable(R.drawable.item_color_grey)));
        items.add(new ItemLista(20, "SERVICIOS", getResources().getDrawable(R.drawable.iconoservicios),getResources().getDrawable(R.drawable.item_color_yellow)));
        items.add(new ItemLista(21, "SERVICIOS PUBLICOS", getResources().getDrawable(R.drawable.iconoserviciospublicos),getResources().getDrawable(R.drawable.item_color_cyan)));
        items.add(new ItemLista(22, "SHOPPINGS Y GALERIAS", getResources().getDrawable(R.drawable.iconoshopping),getResources().getDrawable(R.drawable.item_color_green)));
        items.add(new ItemLista(23, "TRANSPORTE", getResources().getDrawable(R.drawable.iconotransportes),getResources().getDrawable(R.drawable.item_color_pink)));
        items.add(new ItemLista(24, "TURISMO", getResources().getDrawable(R.drawable.iconoturismo),getResources().getDrawable(R.drawable.item_color_orange)));
    	
        return items;
    }
    
    /*
    private ArrayList<ItemLista2> cargarItemsConColorDeFondo() {
    	ArrayList<ItemLista2> items = new ArrayList<ItemLista2>();
  	
    	items.add(new ItemLista2(1, "ALIMENTOS", getResources().getDrawable(R.drawable.iconoalimentos),"#2E7D32"));
		items.add(new ItemLista2(2, "AUTOMOTOR",getResources().getDrawable(R.drawable.iconoauto),"#283593"));
		items.add(new ItemLista2(3, "BANCOS Y TARJETAS", getResources().getDrawable(R.drawable.iconobanco),"#028228"));
		items.add(new ItemLista2(4, "BELLEZA", getResources().getDrawable(R.drawable.iconobelleza),"#6B37C1"));
		items.add(new ItemLista2(5, "BOUTIQUE", getResources().getDrawable(R.drawable.iconoboutique),"#EC4700"));
		items.add(new ItemLista2(6, "CONSTRUCCION",getResources().getDrawable(R.drawable.iconoconstruccion),"#C60A00"));
		items.add(new ItemLista2(7, "DECORACION Y HOGAR", getResources().getDrawable(R.drawable.iconohogar),"#AA413E"));
		items.add(new ItemLista2(8, "EDUCACION", getResources().getDrawable(R.drawable.iconoeducacion),"#956500"));
		items.add(new ItemLista2(9, "EMERGENCIA", getResources().getDrawable(R.drawable.iconoemergencia),"#006FA4"));
		items.add(new ItemLista2(10, "EVENTOS Y FIESTAS", getResources().getDrawable(R.drawable.iconoeventosyfiestas),"#CC00CC"));
		items.add(new ItemLista2(11, "GASTRONOMIA", getResources().getDrawable(R.drawable.iconogastronomia),"#00AE2C"));
		items.add(new ItemLista2(12, "IMPRENTA", getResources().getDrawable(R.drawable.iconoimprenta),"#9B2F1C"));
		items.add(new ItemLista2(13, "PROFESIONALES", getResources().getDrawable(R.drawable.iconoprofesionales),"#76329A"));
		items.add(new ItemLista2(14, "SALUD", getResources().getDrawable(R.drawable.iconosalud),"#37618E"));
		items.add(new ItemLista2(15, "SEGURIDAD", getResources().getDrawable(R.drawable.iconoseguridad),"#864483"));
		items.add(new ItemLista2(16, "SERVICIOS", getResources().getDrawable(R.drawable.iconoservicios),"#A4A400"));
		items.add(new ItemLista2(17, "SERVICIOS PUBLICOS", getResources().getDrawable(R.drawable.iconoserviciospublicos),"#820228"));
		items.add(new ItemLista2(18, "SHOPPINGS Y GALERIAS", getResources().getDrawable(R.drawable.iconoshopping),"#029930"));
		items.add(new ItemLista2(19, "TIEMPO LIBRE Y RECREACION", getResources().getDrawable(R.drawable.iconotiempoyrecreacion),"#029D9D"));
		items.add(new ItemLista2(20, "TRANSPORTE", getResources().getDrawable(R.drawable.iconotransportes),"#660033"));
		items.add(new ItemLista2(21, "TURISMO", getResources().getDrawable(R.drawable.iconoturismo),"#333333"));
		
		return items;
    }
     */
    
    
    public void cargarbanners(){
        ImageView banner=(ImageView)findViewById(R.id.imageView2);
        banner.setImageDrawable(getResources().getDrawable(R.drawable.havana));
    }



}

package com.example.bondicat;

import java.util.ArrayList;

import com.example.publicidad.Utils;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
public class Categoria extends Activity implements HiddenFragment.TaskCallbacks {

	/*
    Etiqueta de referencia del fragmento invisible
     */
    public static final String HIDDEN_FRAGMENT_TAG = "CategoriaFragment";
    
	/*
    Instancia del Fragmento
     */
    HiddenFragment fragment;
    
	//Variables privadas
    private ListView lstOpciones;
    private EditText etBuscar;
    private ImageView imgPublicidad;

    /**
     * Se ejecuta cuando se crea la actividad
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoria);

        //Carga el listado de Categorias y les pone un color distinto de fonto.
        cargarListaConFondoDeImagen();

        lstOpciones.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,long id) {
                Bundle bundle = new Bundle();
                bundle.putInt("ID" , position );
                Intent intent = new Intent(Categoria.this, SubCategoria.class);
                intent.putExtras(bundle);
                startActivity(intent);
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
        
     // Obtener referencia del fragmento
        fragment = (HiddenFragment)getFragmentManager().
                findFragmentByTag(HIDDEN_FRAGMENT_TAG);
        
    }
    
    /**
     * 
     */
    private void iniciarPublicidad() {
        FragmentManager fg = getFragmentManager();
        fragment = new HiddenFragment(Utils.IMAGENES_CATEGORIA_IDS.length);
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
    
    /**
     * Este metodo carga la lista. Además el fondo de cada elemento lo pone en 
     * base a cada imagen guardada en el archivo de recurso. 
     */
    private void cargarListaConFondoDeImagen() {
        lstOpciones = (ListView)findViewById(R.id.listView1);
        ArrayList<ItemLista> itemslista = cargarItemsConImagenesDeFondo();

        ItemListaAdapter adapter = new ItemListaAdapter(this, itemslista);

        lstOpciones.setAdapter(adapter);
    }    
    
    /**
     * Carga la lista con las categorias. Se usan los 7 colores del arco iris para mostrar 
     * una categoria con un color distinto. 
     * @return
     */
    private ArrayList<ItemLista> cargarItemsConImagenesDeFondo() {
        ArrayList<ItemLista> items = new ArrayList<ItemLista>();

        items.add(new ItemLista(1, "ALIMENTOS", getResources().getDrawable(R.drawable.iconoalimentos),getResources().getDrawable(R.drawable.item_color_red)));
        items.add(new ItemLista(2, "AUTOMOTORES",getResources().getDrawable(R.drawable.iconoauto),getResources().getDrawable(R.drawable.item_color_orange)));
        items.add(new ItemLista(3, "BANCOS Y TARJETAS", getResources().getDrawable(R.drawable.iconobanco),getResources().getDrawable(R.drawable.item_color_yellow)));
        items.add(new ItemLista(4, "BELLEZA", getResources().getDrawable(R.drawable.iconobelleza),getResources().getDrawable(R.drawable.item_color_green)));
        items.add(new ItemLista(5, "BOUTIQUE", getResources().getDrawable(R.drawable.iconoboutique),getResources().getDrawable(R.drawable.item_color_blue)));
        items.add(new ItemLista(6, "CIUDADANA Y METROPOLITANA",getResources().getDrawable(R.drawable.iconoservicios),getResources().getDrawable(R.drawable.item_color_deep_purple)));
        items.add(new ItemLista(7, "CONSTRUCCION",getResources().getDrawable(R.drawable.iconoconstruccion),getResources().getDrawable(R.drawable.item_color_purple)));
        items.add(new ItemLista(8, "DECORACION Y HOGAR", getResources().getDrawable(R.drawable.iconohogar),getResources().getDrawable(R.drawable.item_color_red)));        
        items.add(new ItemLista(9, "DEPORTES", getResources().getDrawable(R.drawable.iconotiempoyrecreacion),getResources().getDrawable(R.drawable.item_color_orange)));        
        items.add(new ItemLista(10, "EDUCACION", getResources().getDrawable(R.drawable.iconoeducacion),getResources().getDrawable(R.drawable.item_color_yellow)));
        items.add(new ItemLista(11, "EMERGENCIA", getResources().getDrawable(R.drawable.iconoemergencia),getResources().getDrawable(R.drawable.item_color_green)));
        items.add(new ItemLista(12, "ENTRETENIMIENTO", getResources().getDrawable(R.drawable.iconotiempoyrecreacion),getResources().getDrawable(R.drawable.item_color_blue)));
        items.add(new ItemLista(13, "EVENTOS Y FIESTAS", getResources().getDrawable(R.drawable.iconoeventosyfiestas),getResources().getDrawable(R.drawable.item_color_deep_purple)));
        items.add(new ItemLista(14, "GASTRONOMIA", getResources().getDrawable(R.drawable.iconogastronomia),getResources().getDrawable(R.drawable.item_color_purple)));        
        items.add(new ItemLista(15, "IMPRENTA", getResources().getDrawable(R.drawable.iconoimprenta),getResources().getDrawable(R.drawable.item_color_red)));
        items.add(new ItemLista(16, "PAGO FACIL Y RAPIPAGO", getResources().getDrawable(R.drawable.iconobanco),getResources().getDrawable(R.drawable.item_color_orange)));
        items.add(new ItemLista(17, "PROFESIONALES", getResources().getDrawable(R.drawable.iconoprofesionales),getResources().getDrawable(R.drawable.item_color_yellow)));
        items.add(new ItemLista(18, "SALUD", getResources().getDrawable(R.drawable.iconosalud),getResources().getDrawable(R.drawable.item_color_green)));
        items.add(new ItemLista(19, "SEGURIDAD", getResources().getDrawable(R.drawable.iconoseguridad),getResources().getDrawable(R.drawable.item_color_blue)));
        items.add(new ItemLista(20, "SERVICIOS", getResources().getDrawable(R.drawable.iconoservicios),getResources().getDrawable(R.drawable.item_color_deep_purple)));
        items.add(new ItemLista(21, "SERVICIOS PUBLICOS", getResources().getDrawable(R.drawable.iconoserviciospublicos),getResources().getDrawable(R.drawable.item_color_purple)));
        items.add(new ItemLista(22, "SHOPPINGS Y GALERIAS", getResources().getDrawable(R.drawable.iconoshopping),getResources().getDrawable(R.drawable.item_color_red)));
        items.add(new ItemLista(23, "TRANSPORTE", getResources().getDrawable(R.drawable.iconotransportes),getResources().getDrawable(R.drawable.item_color_orange)));
        items.add(new ItemLista(24, "TURISMO", getResources().getDrawable(R.drawable.iconoturismo),getResources().getDrawable(R.drawable.item_color_yellow)));
    	
        return items;
    }

	@Override
	public void onPreExecute() {
		imgPublicidad = (ImageView)findViewById(R.id.imgPublicidad);		
	}

	@Override
	public void onProgressUpdate(int index) {
		imgPublicidad.setImageResource(Utils.IMAGENES_CATEGORIA_IDS[index]);
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

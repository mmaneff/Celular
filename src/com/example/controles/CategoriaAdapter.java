package com.example.controles;

import java.util.ArrayList;
import java.util.List;

import com.example.bondicat.R;
import com.example.entidades.Categoria;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class CategoriaAdapter extends BaseAdapter {

	private final static String LOGCAT = "CategoriaAdapter";
	
	protected Activity activity;
	protected ArrayList<Categoria> arrayList;
	protected List<Categoria> list;
	protected Resources resources;
	
	public CategoriaAdapter(Activity activity, ArrayList<Categoria> list) {
		this.activity = activity;
	    this.arrayList = list;
	}
	
	public CategoriaAdapter(Activity activity, List<Categoria> list, Resources resources) {
		this.activity = activity;
	    this.list = list;
	    this.resources = resources;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return list.get(position).id;
	}
	
	@Override
	public View getView(int position, View contentView, ViewGroup viewGroup) {
		View view = contentView;
		  
	    if(contentView == null) {
	      LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	      view = inflater.inflate(R.layout.lista_categoria, null);
	    }
	    
	    try {	
	    	
		    //Recupero la categoria
		    Categoria categoria = list.get(position);
		    
		    //Actualizo el color de fondo en base a cada categoria
		    LinearLayout fondo = (LinearLayout)view.findViewById(R.id.id_lista_categoria);
		    fondo.setBackgroundDrawable(Common.getBackgroundByName(resources, categoria.color));
		    
		    //Cambio la imagen de acuerdo a cada categoria
		    ImageView imageCategoria = (ImageView) view.findViewById(R.id.imgCategoria);
		    imageCategoria.setImageDrawable(Common.getImageByName(resources, categoria.imagen));
		    
		    //Muestro el texto de cada categoria
		    TextView nombre = (TextView) view.findViewById(R.id.tvNombreCategoria);
		    nombre.setText(categoria.nombre);	
		    	    
	    }
	    catch(Exception ex) {
	    	Log.e(LOGCAT, ex.getMessage());
	    }
	    
	    return view;
	}
	
}

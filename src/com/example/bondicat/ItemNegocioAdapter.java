package com.example.bondicat;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemNegocioAdapter extends BaseAdapter {
	
	protected Activity activity;
	protected ArrayList<ItemNegocio> items;
	protected int colorTexto;
	  
	public ItemNegocioAdapter(Activity activity, ArrayList<ItemNegocio> items, int colorTexto) {
		this.activity = activity;
	    this.items = items;
	    this.colorTexto = colorTexto;
	}	

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return items.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
	  
	    if(convertView == null) {
	    	LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    	view = inflater.inflate(R.layout.itemnegocio, null);
	    }
	            
	    ItemNegocio item = items.get(position);
	        
	    ImageView image = (ImageView) view.findViewById(R.id.ivImage);
	    image.setImageDrawable(item.getImagen());
	    
	    TextView nombre = (TextView) view.findViewById(R.id.tvCompany);
	    nombre.setText(item.getNombre());
	    nombre.setTextColor(colorTexto);
	    
	    TextView domicilio = (TextView) view.findViewById(R.id.tvAddress);
	    domicilio.setText(item.getDomicilio());
	    
	    TextView cercania = (TextView) view.findViewById(R.id.tvCercania);
	    cercania.setText(item.getCercania());

	    return view;
	}
}

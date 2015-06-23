package com.example.bondicat;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemListaAdapterSub extends BaseAdapter {

	protected Activity activity;
	protected ArrayList<itemsubcate> items;
	  
	public ItemListaAdapterSub(Activity activity, ArrayList<itemsubcate> items) {
	    this.activity = activity;
	    this.items = items;
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
			view = inflater.inflate(R.layout.itemsubcate, null);
	    }
	            
	    itemsubcate item = items.get(position);
	        
	    ImageView image = (ImageView) view.findViewById(R.id.imageView1);
	    image.setImageDrawable(item.getImagen());
	    
	    TextView nombre = (TextView) view.findViewById(R.id.textView2);
	    nombre.setText(item.getNombre());

	    return view;
	}
}

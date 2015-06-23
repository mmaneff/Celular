package com.example.bondicat;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemListaAdapter extends BaseAdapter {
	 protected Activity activity;
	  protected ArrayList<ItemLista> items;
	  
	  public ItemListaAdapter(Activity activity, ArrayList<ItemLista> items) {
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
	    View vi=convertView;
	  
	    if(convertView == null) {
	      LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	      vi = inflater.inflate(R.layout.itemlist, null);
	    }
	            
	    ItemLista item = items.get(position);
	        
	    ImageView image = (ImageView) vi.findViewById(R.id.ivImage);
	    image.setImageDrawable(item.getRutaImagen());
	    
	    //RelativeLayout fondo = (RelativeLayout) vi.findViewById(R.id.relativeLayout0);
	    LinearLayout fondo = (LinearLayout)vi.findViewById(R.id.relativeLayout0);
	    
	    fondo.setBackgroundDrawable(item.getFondo());    
	    TextView nombre = (TextView) vi.findViewById(R.id.tvTextName);
	    nombre.setText(item.getNombre());
	   // RelativeLayout rl=(RelativeLayout) vi.findViewById(R.id.relativeLayout0);
	    //rl.setBackgroundColor(item.getColor());
	   // TextView tipo = (TextView) vi.findViewById(R.id.tipo);
	    //tipo.setText(item.getTipo());

	    return vi;
	  }
}

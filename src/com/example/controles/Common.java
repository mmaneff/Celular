package com.example.controles;

import com.example.bondicat.R;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.Log;


public class Common {

	private final static String LOGCAT = "Common"; 
	
	/*
	private static Resources _resource;
	
	public void setResources(Resources resources) {
		_resource = resources;
	}
	*/
	
	/**
	 * 
	 * @param resources
	 * @param color
	 * @return
	 */
	public static Drawable getDrawableColorByName(Resources resources, String color) {
		Drawable itemColor = null;
		try {
			if(color.equals("brown"))
				itemColor = resources.getDrawable(R.color.brown_400);
			else if(color.equals("green"))
				itemColor = resources.getDrawable(R.color.green_400);
			else if(color.equals("indigo"))
				itemColor = resources.getDrawable(R.color.indigo_400);
			else if(color.equals("orange"))
				itemColor = resources.getDrawable(R.color.orange_400);
			else if(color.equals("pink"))
				itemColor = resources.getDrawable(R.color.pink_400);
			else if(color.equals("red"))
				itemColor = resources.getDrawable(R.color.red_400);
			else if(color.equals("lime"))
				itemColor = resources.getDrawable(R.color.lime_400);
			else if(color.equals("blue"))
				itemColor = resources.getDrawable(R.color.blue_400);
			else if(color.equals("blue_grey"))
				itemColor = resources.getDrawable(R.color.blueGrey_400);
			else if(color.equals("amber"))
				itemColor = resources.getDrawable(R.color.amber_400);
			else if(color.equals("cyan"))
				itemColor = resources.getDrawable(R.color.cyan_400);
			else if(color.equals("deep_orange"))
				itemColor = resources.getDrawable(R.color.deepOrange_400);
			else if(color.equals("deep_purple"))
				itemColor = resources.getDrawable(R.color.deepPurple_400);
			else 
				itemColor = resources.getDrawable(R.color.grey_400);
		}
		catch(Exception ex) {
			Log.e(LOGCAT, ex.getMessage());
		}
		return itemColor;
	}
	
	/**
	 * 
	 * @param resources
	 * @param color
	 * @return
	 */
	public static int getColorByName(Resources resources, String color) {
		int itemColor = resources.getColor(R.color.white);
		try {			
			if(color.equals("brown"))
				itemColor = resources.getColor(R.color.brown_400);
			else if(color.equals("green"))
				itemColor = resources.getColor(R.color.green_400);
			else if(color.equals("indigo"))
				itemColor = resources.getColor(R.color.indigo_400);
			else if(color.equals("orange"))
				itemColor = resources.getColor(R.color.orange_400);
			else if(color.equals("pink"))
				itemColor = resources.getColor(R.color.pink_400);
			else if(color.equals("red"))
				itemColor = resources.getColor(R.color.red_400);
			else if(color.equals("lime"))
				itemColor = resources.getColor(R.color.lime_400);
			else if(color.equals("blue"))
				itemColor = resources.getColor(R.color.blue_400);
			else if(color.equals("blue_grey"))
				itemColor = resources.getColor(R.color.blueGrey_400);
			else if(color.equals("amber"))
				itemColor = resources.getColor(R.color.amber_400);
			else if(color.equals("cyan"))
				itemColor = resources.getColor(R.color.cyan_400);
			else if(color.equals("deep_orange"))
				itemColor = resources.getColor(R.color.deepOrange_400);
			else if(color.equals("deep_purple"))
				itemColor = resources.getColor(R.color.deepPurple_400);
			else 
				itemColor = resources.getColor(R.color.grey_400);				
		}
		catch(Exception ex) {
			Log.e(LOGCAT, ex.getMessage());
		}
		return itemColor;		
	}
	
	/**
	 * 
	 * @param resources
	 * @param color
	 * @return
	 */
	public static Drawable getBackgroundByName(Resources resources, String color) {
		Drawable itemColor = null;
		try {
			if(color.equals("brown"))
				itemColor = resources.getDrawable(R.drawable.item_color_brown);
			else if(color.equals("green"))
				itemColor = resources.getDrawable(R.drawable.item_color_green);
			else if(color.equals("indigo"))
				itemColor = resources.getDrawable(R.drawable.item_color_indigo);
			else if(color.equals("orange"))
				itemColor = resources.getDrawable(R.drawable.item_color_orange);
			else if(color.equals("pink"))
				itemColor = resources.getDrawable(R.drawable.item_color_pink);
			else if(color.equals("red"))
				itemColor = resources.getDrawable(R.drawable.item_color_red);
			else if(color.equals("lime"))
				itemColor = resources.getDrawable(R.drawable.item_color_lime);
			else if(color.equals("blue"))
				itemColor = resources.getDrawable(R.drawable.item_color_blue);
			else if(color.equals("blue_grey"))
				itemColor = resources.getDrawable(R.drawable.item_color_blue_grey);
			else if(color.equals("amber"))
				itemColor = resources.getDrawable(R.drawable.item_color_amber);
			else if(color.equals("cyan"))
				itemColor = resources.getDrawable(R.drawable.item_color_cyan);
			else if(color.equals("deep_orange"))
				itemColor = resources.getDrawable(R.drawable.item_color_deep_orange);
			else if(color.equals("deep_purple"))
				itemColor = resources.getDrawable(R.drawable.item_color_deep_purple);
			else 
				itemColor = resources.getDrawable(R.drawable.item_color_grey);
		}
		catch(Exception ex) {
			Log.e(LOGCAT, ex.getMessage());
		}
		return itemColor;
	}
	
	/**
	 * 
	 * @param resources
	 * @param name
	 * @return
	 */
	@SuppressLint("Recycle")
	public static Drawable getImageByName(Resources resources, String imagen) {
		Drawable iconoCategoria = null;
		
		try {			
			TypedArray iconos = resources.obtainTypedArray(R.array.imagenes);
		    		    
		    for(int i = 0; i <= iconos.length() - 1; i++) {
		    	Log.i(LOGCAT, iconos.getString(i));			    
			    
			    if(iconos.getString(i).contains(imagen)) {
			    	iconoCategoria = iconos.getDrawable(i);
			    	break;
			    }
		    }		    
		}
		catch(Exception ex) {
			Log.e(LOGCAT, ex.getMessage());
		}
		return iconoCategoria;
	}
	
	/**
	 * 
	 * @param detalle
	 * @return
	 */
	public static String getDetalleCorto(String detalle) {
		String detalleCorto = "";
		
		if(detalle.length() > 35) {
			detalleCorto = detalle.substring(0, 35) + "...";
			
		}
		
		return detalleCorto;
	}
}

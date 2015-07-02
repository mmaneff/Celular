package com.example.publicidad;

import com.example.bondicat.R;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PublicidadCategoriaAdapter extends PagerAdapter {

	private Activity ac_categoria;
	
	int[] imageId = { R.drawable.pb_claro, R.drawable.pb_falabella, R.drawable.pb_cinemacenter, R.drawable.pb_2 };
	
	public PublicidadCategoriaAdapter(Activity activity) {
		this.ac_categoria = activity;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		LayoutInflater inflater = (LayoutInflater)ac_categoria.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        View viewItem = inflater.inflate(R.layout.image_item2, container, false);
        ImageView imageView = (ImageView) viewItem.findViewById(R.id.imageView_2);
        imageView.setImageResource(imageId[position]);
        ((ViewPager)container).addView(viewItem);
         
        return viewItem;
	}
	
	@Override
	public int getCount() {
		return imageId.length;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((View)object);
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((View) object);
	}
	
}

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

public class PublicidadPrincipalAdapter extends PagerAdapter {

	private Activity ac_principal;
	
	int[] imageId = { R.drawable.pb_1_burger, R.drawable.pb_1_cines, R.drawable.pb_1_dontoribio, R.drawable.pb_1_marathon, R.drawable.pb_1 };
	
	public PublicidadPrincipalAdapter(Activity activity) {
		this.ac_principal = activity;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		LayoutInflater inflater = (LayoutInflater)ac_principal.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        View viewItem = inflater.inflate(R.layout.image_item, container, false);
        ImageView imageView = (ImageView) viewItem.findViewById(R.id.imageView);
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

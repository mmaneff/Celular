package com.example.bondicat;

import java.util.Timer;
import java.util.TimerTask;

import com.example.publicidad.Utils;
import com.facebook.Settings;
import com.facebook.widget.LikeView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends Activity {

    //Miembros privados
	private Handler mHandler = null;	
	private ImageView imgPublicidad;
	public int currentImageIndex = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        //Inicializo el SDK de facebook
        iniciarFacebookSdk();
        
        //Personalizo la fuente usada
        personalizarFuente();

        
        // cambiarImagen();
        /*
        PagerAdapter adapter = new PublicidadPrincipalAdapter(Principal.this);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        
        mostrarPublicidad();
        */
    }
    
    /**
     * Inicializa el SDK de facebook para hacer el like de la pagina.
     */
    private void iniciarFacebookSdk() {
    	// To initialize Facebook SDK in your app
        Settings.sdkInitialize(getApplicationContext());
        // Get LikeView button
        LikeView likeView = (LikeView) findViewById(R.id.like_view);
        // Set the object for which you want to get likes from your users (Photo, Link or even your FB Fan page)
        likeView.setObjectId("http://www.facebook.com/buscaafacil");
        // Set foreground color fpr Like count text
        likeView.setForegroundColor(-256);
    }    
   
    /**
     * Seteo un nuevo tipo de fuente BreeExtrabold
     */
    private void personalizarFuente(){
        Typeface breeExtraboldFont = Typeface.createFromAsset(getAssets(), "fonts/BreeExtrabold.otf");
        Typeface breeRegularFont = Typeface.createFromAsset(getAssets(), "fonts/BreeRegular.otf");

        TextView tvTitle = (TextView)findViewById(R.id.tvTitle);
        TextView tvFiendMe = (TextView)findViewById(R.id.tvEncuentranos);

        tvTitle.setTypeface(breeExtraboldFont);
        tvFiendMe.setTypeface(breeRegularFont);
    }
    
    /**
     * Funcionalidad en construción
     * @param view
     */
    public void onCapitalClick(View view) {
    	Toast.makeText(getApplicationContext(), "Funcionalidad en Construcción", Toast.LENGTH_SHORT).show();
    }
    
    /**
     * Inicia la vista de Categorias
     * @param view
     */
    public void onYerbaBuenaClick(View view) {
    	Intent intent = new Intent(Principal.this, Categoria.class);
        startActivity(intent);
    }
    
    /**
     * Permite enviar un mail a busca facil
     * @param view
     */
    public void onMailClick(View view) {
    	Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822") ;
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@guiabuscafacil.com"});
        startActivity(Intent.createChooser(i, "Seleccionar aplicación."));
    }
    
    /**
     * Redirecciona a la página de Facebook
     * @param view
     */
    public void onFacebookClick(View view) {
    	Uri uriUrl = Uri.parse("http://www.facebook.com/buscaafacil");
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
    
    /**
     * Redirecciona a la página de twitter.
     * @param view
     */
    public void onTwitterClick(View view) {
    	Uri uriUrl = Uri.parse("http://www.twitter.com/buscaafacil");
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
    
    /**
     * Es en este lugar en donde lanzo las animaciones de la publicidad
     */
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	Log.i("onResume", "Principal - Cargo y muestro la publicidad");
    	//Cargo la publicidad
    	cargarPublicidad();
    }
    
    /**
     * En esta sección de detiene la publicidad
     */
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	if(mHandler != null)
    		mHandler = null;
    	Log.i("onPause", "Principal - Detengo publicidad y libero recursos");
    }
    
    /**
     * 
     */
    private void cargarPublicidad() {
    	mHandler = new Handler();
    	
    	// Create runnable for posting
        final Runnable mUpdateResults = new Runnable() {
            public void run() {                
                AnimateAndSlideShow();
            }
        };
        
        int delay = 1000; // delay for 1 sec.
        int period = 10000; // repeat every 4 sec.

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
        	public void run() {
        		mHandler.post(mUpdateResults);
        	}
        }, delay, period);
    }
    
    /**
     * Helper method to start the animation on the splash screen
     */
    private void AnimateAndSlideShow() {    	
    	imgPublicidad = (ImageView)findViewById(R.id.imgPublicidad);
    	imgPublicidad.setImageResource(Utils.IMAGENES_PRINCIPAL_IDS[currentImageIndex]);
           
    	currentImageIndex++;
    	if(currentImageIndex >= Utils.IMAGENES_PRINCIPAL_IDS.length)
    		currentImageIndex = 0;
        
        Animation rotateImage = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        imgPublicidad.startAnimation(rotateImage);            
    }

}

package com.example.bondicat;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.example.publicidad.Utils;
import com.facebook.Settings;
import com.facebook.widget.LikeView;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class Principal extends Activity implements HiddenFragment.TaskCallbacks {

	/*
    Etiqueta de referencia del fragmento invisible
     */
    public static final String HIDDEN_FRAGMENT_TAG = "PrincipalFragment";
    
    //Miembros privados
	private ImageView imgPublicidad;
	
	/*
    Instancia del Fragmento
     */
    HiddenFragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        //Inicializo el SDK de facebook
        iniciarFacebookSdk();
        //Obtengo una key hash para conectarme con facebook
        getKeyHash();
        
        //Personalizo la fuente usada
        personalizarFuente();

        // Obtener referencia del fragmento
        fragment = (HiddenFragment)getFragmentManager().
                findFragmentByTag(HIDDEN_FRAGMENT_TAG);
    }
    
    private void getKeyHash() {
	    try {
	        PackageInfo info = getPackageManager().getPackageInfo("com.example.bondicat", PackageManager.GET_SIGNATURES);
	        for (Signature signature : info.signatures) {
	        	MessageDigest md = MessageDigest.getInstance("SHA");
	            md.update(signature.toByteArray());
	            Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
	        }	        
	    }
    	catch (NameNotFoundException e) {
    		Log.e("NameNotFoundException", e.getMessage());
    	} 
    	catch (NoSuchAlgorithmException e) {
    		Log.e("NoSuchAlgorithmException", e.getMessage());
    	}
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
     * 
     */
    private void iniciarPublicidad() {
        FragmentManager fg = getFragmentManager();
        fragment = new HiddenFragment(Utils.IMAGENES_PRINCIPAL_IDS.length);        
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
    	Log.i("onResume", "Principal - Cargo y muestro la publicidad");
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
    	Log.i("onPause", "Principal - Detengo publicidad y libero recursos");
    	//Detengo la publicidad
    	fragment.publicityTask.cancel(true);
    }
    
	@Override
	public void onPreExecute() {
		imgPublicidad = (ImageView)findViewById(R.id.imgPublicidad);
	}

	@Override
	public void onProgressUpdate(int index) {
		imgPublicidad.setImageResource(Utils.IMAGENES_PRINCIPAL_IDS[index]);
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

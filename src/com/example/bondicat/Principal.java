package com.example.bondicat;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class Principal extends Activity {

    private Spinner spinner1;
    private List<String> lista;
    //private Button  btnCategory;
    private Button  btnCapital;
    private Button  btnYerbaBuena;
    
    ImageView ivEmail;
    ImageView ivFacebook;
    ImageView ivTwitter;
    //EditText etBuscar;
    int contador;

    final String message = "Funcionalidad en construcción";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        PersonalizarFuente();

        DatosPorDefecto();
        // cambiarImagen();
    }

    private void PersonalizarFuente(){
        Typeface breeExtraboldFont = Typeface.createFromAsset(getAssets(), "fonts/BreeExtrabold.otf");
        Typeface breeRegularFont = Typeface.createFromAsset(getAssets(), "fonts/BreeRegular.otf");

        TextView tvTitle = (TextView)findViewById(R.id.tvTitle);
        TextView tvFiendMe = (TextView)findViewById(R.id.tvEncuentranos);

        tvTitle.setTypeface(breeExtraboldFont);
        tvFiendMe.setTypeface(breeRegularFont);
    }

    private void DatosPorDefecto() {
        // TODO Auto-generated method stub
    	/*
    	etBuscar = (EditText) findViewById(R.id.etFiend);
    	etBuscar.setOnEditorActionListener(new OnEditorActionListener() {
    	    @Override
    	    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
    	        boolean handled = false;
    	        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
    	        	Bundle bundle = new Bundle();
                    bundle.putString("busqueda" , etBuscar.getText().toString());
    	        	Intent intent = new Intent(Principal.this, Busqueda.class);
    	        	intent.putExtras(bundle);
                    startActivity(intent);
    	            handled = true;
    	        }
    	        return handled;
    	    }
    	});*/
    	
    	/*
        btnCategory=(Button) findViewById(R.id.btnCapital);
        btnCategory.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(Principal.this, Categoria.class);
                startActivity(intent);
            }
        });*/
    	
    	//CON ESTA OPCION LLAMO A LA VISTA PARA CAPITAL
    	btnCapital = (Button) findViewById(R.id.btnCapital);
    	btnCapital.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                // Intent intent = new Intent(Principal.this, Categoria.class);
                // startActivity(intent);
            	Context context = getApplicationContext();
            	CharSequence text = "Proximamente";
            	int duration = Toast.LENGTH_SHORT;

            	Toast toast = Toast.makeText(context, text, duration);
            	toast.show();
            }
        });
    	
    	//CON ESTA OPCION LLAMO A LA VISTA PARA YERBA BUENA
    	btnYerbaBuena = (Button) findViewById(R.id.btnYerbaBuena);
    	btnYerbaBuena.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(Principal.this, Categoria.class);
                startActivity(intent);
            }
        });
        
        ivEmail = (ImageView) this.findViewById(R.id.ivMail);
        ivEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == findViewById(R.id.ivMail)) {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("message/rfc822") ;
                    i.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@guiabuscafacil.com"});
                    startActivity(Intent.createChooser(i, "Seleccionar aplicación."));
                }
            }
        });
        ivFacebook = (ImageView) this.findViewById(R.id.ivFacebook);
        ivFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == findViewById(R.id.ivFacebook)) {
                    Uri uriUrl = Uri.parse("http://www.facebook.com/buscaafacil");
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                }
            }
        });
        ivTwitter = (ImageView) this.findViewById(R.id.ivTwitter);
        ivTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == findViewById(R.id.ivTwitter)) {
                    Uri uriUrl = Uri.parse("http://www.twitter.com/buscaafacil");
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                }
            }
        });
    }

    private void Imagen() {
        // TODO Auto-generated method stub
        contador++;
        ImageView image = (ImageView) findViewById(R.id.ivPublicity);
        if(contador==1){
            image.setImageResource(R.drawable.publicidad);
        }if(contador==2){
            image.setImageResource(R.drawable.imagensubcategoria);
        }
        if(contador==3){
            image.setImageResource(R.drawable.logo);
            contador=0;
        }
    }

    /**
     * Maneja los radio buttons
     * @param view
     */

}

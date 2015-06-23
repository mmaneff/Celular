package com.example.bondicat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;


public class MainActivity extends Activity {

		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Intent intent = new Intent(MainActivity.this, Principal.class);
		startActivity(intent);
		
		/*
        // Creamos thread para que espere 3 segundos
        Thread timer = new Thread() {
        	// El nuevo thread exige el m�todo run
        	public void run() {
        		try {
        			sleep(2000);
        			
        		} catch (InterruptedException e) {
        			// Si no puedo ejecutar el sleep muestro el error
        			e.printStackTrace();
        		} finally {
        			// Llamo a la actividad principal
        			Intent intent = new Intent(MainActivity.this, Principal.class);
      	  		  	startActivity(intent);
        		}
        	}
        };
        // Ejecuto el thread
        timer.start();
        */
        
        //ImageView image=(ImageView)findViewById(R.id.imageView1);
        //image.setOnClickListener(new OnClickListener() {
	    	
	    	//public void onClick(View arg0) {

	  		 // Intent intent = new Intent(MainActivity.this, Principal.class);
	  		  //startActivity(intent);  		
	  //	}
	  //}); 
 	           
    }

    

	//@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_main, menu);
        //return true;
    //}

	
	   

	   
}

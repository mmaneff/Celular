package com.example.bondicat;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;

public class HiddenFragment extends Fragment {

	/*
    Interfaz para la comunicación con la actividad ABTest.
     */
    static interface TaskCallbacks {
        void onPreExecute();
        void onProgressUpdate(int progress);
        void onCancelled();
        void onPostExecute();
    }

    /*
    Instancia de la interfaz
     */
    private TaskCallbacks mCallbacks;

    /*
    Instancia de la tarea PublicityTask
    Tarea Asincronica para mostrar la publicidad
     */
    PublicityTask publicityTask;
    
    private int imageCount;

    /**
     * Constructor
     */
    public HiddenFragment() { }

    /**
     * Constructor
     * @param imageCount
     */
    public HiddenFragment(int imageCount) {
    	this.imageCount = imageCount;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        // Obtener la instancia de ABTest
        mCallbacks = (TaskCallbacks) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retener el fragmento creado
        setRetainInstance(true);

        /*
        Una vez creado el fragmento se inicia la tarea asíncrona
         */
        publicityTask = new PublicityTask();
        publicityTask.execute(imageCount);
    }

    @Override
    public void onDetach(){
        super.onDetach();
        mCallbacks = null;
    }

    /**
     * 
     * @author Matute
     *
     */
    public class PublicityTask extends AsyncTask<Integer, Integer, Long> {

        @Override
        protected void onPreExecute() {
            if (mCallbacks != null) {
                mCallbacks.onPreExecute();
            }
        }

        @Override
        protected void onCancelled() {
            if(mCallbacks != null)
                mCallbacks.onCancelled();
        }

        @Override
        protected Long doInBackground(Integer... params) {        	
        	try {
        		for(int i = 0; i < params[0]; i++) {
        			Thread.sleep(5000);
    				if(!isCancelled())
                        publishProgress(i);
                    else 
                    	break;
    				
    				if(i == params[0]-1)
    					i = 0;
        		}				
			} 
        	catch (InterruptedException e) {
				e.printStackTrace();
			}
        	
        	return Long.MIN_VALUE;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if (mCallbacks != null) {
                mCallbacks.onProgressUpdate(values[0]);
            }
        }

        @Override
        protected void onPostExecute(Long aLong) {
            if (mCallbacks != null) {
                mCallbacks.onPostExecute();
            }
        }
    }
    
}

package com.example.bondicat;

import com.example.entidades.Comercio;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
//import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Datos extends android.support.v4.app.FragmentActivity {
	private final LatLng maternidad = new LatLng(-26.8398988, -65.2211458);
	private GoogleMap mMap;
	private SQLiteDatabase database;

	long idnegocio;

	private Comercio comercio = new Comercio();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Bundle bundle = this.getIntent().getExtras();
        int datos_activity = bundle.getInt("datos_activity");
        String colorName = bundle.getString("colorName");
        idnegocio = bundle.getLong("ID");
        
        if(datos_activity == 1)
        	setContentView(R.layout.datos);
        else
        	setContentView(R.layout.datos2);
		
		PersonalizarFuente();
			
		// inicializarMapa();
		setBackgroundColor(colorName);
		inicializarComponentes();
		Descripcion();

		Button btnmapa = (Button) findViewById(R.id.btnMapa);

		if (comercio.getDomicilio() == null) {
			btnmapa.setVisibility(View.INVISIBLE);
		}

		btnmapa.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Bundle bundle = new Bundle();
				bundle.putLong("ID", idnegocio);
				bundle.putString("razonSocial", comercio.getRazonSocial());
				if(comercio.getLatitud() != null) {
					bundle.putDouble("latitud",
							Double.parseDouble(comercio.getLatitud()));
				}
				if(comercio.getLongitud() != null) {
					bundle.putDouble("longitud",
							Double.parseDouble(comercio.getLongitud()));
				}
				bundle.putString("domicilio", comercio.getDomicilio());
				Intent intent = new Intent(Datos.this, Mapa.class);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});

		Button btnvolver = (Button) findViewById(R.id.btnRegresar);

		btnvolver.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Datos.this.finish();
			}
		});
		Button btnllamar = (Button) findViewById(R.id.btnLlamar);

		if (comercio.getTelefono().equals("")) {
			btnllamar.setVisibility(View.INVISIBLE);
		}

		btnllamar.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {

				if (comercio.getTelefono2().equals("")) {
					System.out.println("Tengo un solo teléfono");
					Intent callIntent = new Intent(Intent.ACTION_CALL);
					callIntent.setData(Uri.parse("tel:"
							+ comercio.getTelefono().replace("-", "")
									.replace("/", "")));
					startActivity(callIntent);
				} else {
					if (comercio.getTelefono3().equals("")) {
						System.out.println("Tengo dos teléfonos");
						CharSequence colors[] = new CharSequence[] {
								comercio.getTelefono(), comercio.getTelefono2() };

						AlertDialog.Builder builder = new AlertDialog.Builder(
								Datos.this);
						builder.setTitle("Elija un número");
						builder.setItems(colors,
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										if (which == 0) {
											Intent callIntent = new Intent(
													Intent.ACTION_CALL);
											callIntent.setData(Uri.parse("tel:"
													+ comercio.getTelefono()
															.replace("-", "")
															.replace("/", "")));
											startActivity(callIntent);
										} else if (which == 1) {
											Intent callIntent = new Intent(
													Intent.ACTION_CALL);
											callIntent.setData(Uri.parse("tel:"
													+ comercio.getTelefono2()
															.replace("-", "")
															.replace("/", "")));
											startActivity(callIntent);
										}
									}
								});
						builder.show();

					} else {
						System.out.println("Tengo tres teléfonos");
						CharSequence colors[] = new CharSequence[] {
								comercio.getTelefono(), comercio.getTelefono2(), comercio.getTelefono3() };

						AlertDialog.Builder builder = new AlertDialog.Builder(
								Datos.this);
						builder.setTitle("Elija un número");
						builder.setItems(colors,
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										if (which == 0) {
											Intent callIntent = new Intent(
													Intent.ACTION_CALL);
											callIntent.setData(Uri.parse("tel:"
													+ comercio.getTelefono()
															.replace("-", "")
															.replace("/", "")));
											startActivity(callIntent);
										} else if (which == 1) {
											Intent callIntent = new Intent(
													Intent.ACTION_CALL);
											callIntent.setData(Uri.parse("tel:"
													+ comercio.getTelefono2()
															.replace("-", "")
															.replace("/", "")));
											startActivity(callIntent);
										} else if (which == 2) {
											Intent callIntent = new Intent(
													Intent.ACTION_CALL);
											callIntent.setData(Uri.parse("tel:"
													+ comercio.getTelefono3()
															.replace("-", "")
															.replace("/", "")));
											startActivity(callIntent);
										}
									}
								});
						builder.show();
					}
				}

			}
		});
	}

	private void PersonalizarFuente() {
		Typeface breeRegularFont = Typeface.createFromAsset(getAssets(),
				"fonts/BreeRegular.otf");

		Button btnRegresar = (Button) findViewById(R.id.btnRegresar);
		Button btnMapa = (Button) findViewById(R.id.btnMapa);
		Button btnFavorito = (Button) findViewById(R.id.btnLlamar);

		btnRegresar.setTypeface(breeRegularFont);
		btnMapa.setTypeface(breeRegularFont);
		btnFavorito.setTypeface(breeRegularFont);
	}
	
	private void setBackgroundColor(String colorName) {
		//Instancio botones y la actividad principal
		LinearLayout dato_1 = (LinearLayout) findViewById(R.id.dato_1);
		Button btnRegresar = (Button) findViewById(R.id.btnRegresar);
		Button btnMapa = (Button) findViewById(R.id.btnMapa);
		Button btnFavorito = (Button) findViewById(R.id.btnLlamar);
		
		if(colorName.equalsIgnoreCase("red_900")) {
			dato_1.setBackgroundColor(getResources().getColor(R.color.red_800));
			btnRegresar.setBackgroundColor(getResources().getColor(R.color.red_900));
			btnMapa.setBackgroundColor(getResources().getColor(R.color.red_900));
			btnFavorito.setBackgroundColor(getResources().getColor(R.color.red_900));
		} 
		else if(colorName.equalsIgnoreCase("orange_900")) {
			dato_1.setBackgroundColor(getResources().getColor(R.color.orange_800));
			btnRegresar.setBackgroundColor(getResources().getColor(R.color.orange_900));
			btnMapa.setBackgroundColor(getResources().getColor(R.color.orange_900));
			btnFavorito.setBackgroundColor(getResources().getColor(R.color.orange_900));
		} 
		else if(colorName.equalsIgnoreCase("yellow_900")) {
			dato_1.setBackgroundColor(getResources().getColor(R.color.yellow_800));
			btnRegresar.setBackgroundColor(getResources().getColor(R.color.yellow_900));
			btnMapa.setBackgroundColor(getResources().getColor(R.color.yellow_900));
			btnFavorito.setBackgroundColor(getResources().getColor(R.color.yellow_900));
		}
		else if(colorName.equalsIgnoreCase("green_900")) {
			dato_1.setBackgroundColor(getResources().getColor(R.color.green_800));
			btnRegresar.setBackgroundColor(getResources().getColor(R.color.green_900));
			btnMapa.setBackgroundColor(getResources().getColor(R.color.green_900));
			btnFavorito.setBackgroundColor(getResources().getColor(R.color.green_900));
		}
		else if(colorName.equalsIgnoreCase("blue_900")) {
			dato_1.setBackgroundColor(getResources().getColor(R.color.blue_800));
			btnRegresar.setBackgroundColor(getResources().getColor(R.color.blue_900));
			btnMapa.setBackgroundColor(getResources().getColor(R.color.blue_900));
			btnFavorito.setBackgroundColor(getResources().getColor(R.color.blue_900));
		}
	}

	private void Descripcion() {

		ExternalDbOpenHelper dbConexion = new ExternalDbOpenHelper(this,
				"comercio.db");
		database = dbConexion.openDataBase();
		Cursor comercioCursor = database.rawQuery(
				"SELECT id, correoelectronico, detalle, domicilio, latitud, longitud, razonsocial, sitioweb, telefono, telefono2, telefono3 FROM comercio WHERE id = ?",
				new String[] { String.valueOf(idnegocio) });
		if (comercioCursor.moveToFirst()) {
			do {
				if (comercioCursor.getString(0) != null) {
					comercio.setId(comercioCursor.getLong(0));
				}
				if(comercioCursor.getString(1) != null) {
                    comercio.setCorreoElectronico(comercioCursor.getString(1));
                }
                else {
                	comercio.setCorreoElectronico("");
                }
                	
                if(comercioCursor.getString(2) != null) {
                    comercio.setDetalle(comercioCursor.getString(2));
                }
                else {
                	comercio.setDetalle("");
                }
                
                if(comercioCursor.getString(3) != null) {
                    comercio.setDomicilio(comercioCursor.getString(3));
                }
                else {
                	comercio.setDomicilio("");
                }
                
                if(comercioCursor.getString(4) != null) {
                    comercio.setLatitud(comercioCursor.getString(4));
                }
                else {
                	comercio.setLatitud("");
                }
                
                if(comercioCursor.getString(5) != null) {
                    comercio.setLongitud(comercioCursor.getString(5));
                }
                else {
                	comercio.setLongitud("");
                }
                
                if(comercioCursor.getString(6) != null) {
                    comercio.setRazonSocial(comercioCursor.getString(6));
                }
                else {
                	comercio.setRazonSocial("");
                }
                
                if(comercioCursor.getString(7) != null) {
                    comercio.setSitioWeb(comercioCursor.getString(7));
                }
                else {
                	comercio.setSitioWeb("");
                }
                
                if(comercioCursor.getString(8) != null) {
                    comercio.setTelefono(comercioCursor.getString(8));
                }
                else {
                	comercio.setTelefono("");
                }
                
                if(comercioCursor.getString(9) != null) {
                	comercio.setTelefono2(comercioCursor.getString(9));
                }
                else {
                	comercio.setTelefono2("");
                }
                
                if(comercioCursor.getString(10) != null) {
                	comercio.setTelefono3(comercioCursor.getString(10));
                }
                else {
                	comercio.setTelefono3("");
                }
			} while (comercioCursor.moveToNext());
		}
		comercioCursor.close();
		database.close();

		TextView texto = (TextView) findViewById(R.id.txtRazonSocial);
		//TextView texto1 = (TextView) findViewById(R.id.txtDetalle);
		TextView texto2 = (TextView) findViewById(R.id.txtDireccion);
		TextView texto3 = (TextView) findViewById(R.id.txtTelefono);
		//TextView texto4 = (TextView) findViewById(R.id.txtCorreoElectronico);
		//TextView texto5 = (TextView) findViewById(R.id.txtSitioWeb);
		
		//Agrego nuevos controles para mejorar la información de la vista
		TextView texto6, texto7, texto8; 
		texto6 = (TextView) findViewById(R.id.txtRazonSocial2);
		texto7 = (TextView) findViewById(R.id.txtLocalidad);
		//texto8 = (TextView) findViewById(R.id.txtHorario);
		
		texto.setText(comercio.getRazonSocial());
		texto6.setText(comercio.getRazonSocial());
		
		//texto1.setText(comercio.getDetalle());
		texto2.setText(comercio.getDomicilio());
		if (comercio.getTelefono2() == null) {
			texto3.setText(comercio.getTelefono());
		} else if (comercio.getTelefono3() == null) {
			texto3.setText(comercio.getTelefono() + "/" + comercio.getTelefono2());
		} else {
			texto3.setText(comercio.getTelefono() + "/" + comercio.getTelefono2() + "/" + comercio.getTelefono3());
		}
		//texto4.setText(comercio.getCorreoElectronico());
		//texto5.setText(comercio.getSitioWeb());
		texto7.setText(comercio.getCorreoElectronico());
		//texto8.setText(comercio.getSitioWeb());

		if (!comercio.getCorreoElectronico().equals("")) {
			//texto4.setOnClickListener(new View.OnClickListener() {
			texto7.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent i = new Intent(Intent.ACTION_SEND);
					i.setType("message/rfc822");
					String[] arrayString = new String[1];
					arrayString[0] = comercio.getCorreoElectronico();
					i.putExtra(Intent.EXTRA_EMAIL, arrayString);
					startActivity(Intent.createChooser(i,
							"Seleccionar aplicación."));

				}
			});
		}

		if (!comercio.getSitioWeb().equals("")) {
			//texto5.setOnClickListener(new View.OnClickListener() {
			/*
			texto8.setOnClickListener(new View.OnClickListener() {	
				@Override
				public void onClick(View view) {
					Uri uriUrl = Uri.parse("http://" + comercio.getSitioWeb());
					Intent launchBrowser = new Intent(Intent.ACTION_VIEW,
							uriUrl);
					startActivity(launchBrowser);
				}
			});
			*/
		}

		LinearLayout rl2 = (LinearLayout) findViewById(R.id.relativeLayout2);
		LinearLayout rl3 = (LinearLayout) findViewById(R.id.relativeLayout3);

		// rl2.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondoautomotores));
		// rl3.setBackgroundDrawable(getResources().getDrawable(R.drawable.fondoautomotores));
		ImageView image = (ImageView) findViewById(R.id.imageView2);
		image.setImageDrawable(getResources().getDrawable(
				R.drawable.logonegociosbuscafacil));
	}

	private void inicializarComponentes() {
		// TODO Auto-generated method stub
		Button imageButton = (Button) findViewById(R.id.btnRegresar);
		Button imageButton1 = (Button) findViewById(R.id.btnLlamar);
		Button imageButton2 = (Button) findViewById(R.id.btnMapa);
		imageButton.setCompoundDrawablesWithIntrinsicBounds(0,
				R.drawable.botonvolver, 0, 0);
		imageButton1.setCompoundDrawablesWithIntrinsicBounds(0,
				R.drawable.botonllamar, 0, 0);
		imageButton2.setCompoundDrawablesWithIntrinsicBounds(0,
				R.drawable.botonmapa, 0, 0);

	}
	/*
	 * private void inicializarMapa() { if(mMap==null){ mMap=
	 * ((SupportMapFragment
	 * )getSupportFragmentManager().findFragmentById(R.id.fragment1)).getMap();
	 * mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);//
	 * mMap.setMyLocationEnabled(true);
	 * 
	 * //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(maternidad, 15));//
	 * //mMap.setLocationSource(null); //mMap.setMyLocationEnabled(true);
	 * //mMap.getUiSettings().setZoomControlsEnabled(false);//
	 * //mMap.getUiSettings().setCompassEnabled(true);// mMap.addMarker(new
	 * MarkerOptions()// .position(maternidad)//
	 * .title("AUTOMOTORES RODRIGUEZ HNOS")// .snippet("Automotor")//
	 * .icon(BitmapDescriptorFactory// .fromResource(R.drawable.ic_launcher))//
	 * .anchor(0.1f, 0.1f));// //
	 * mMap.setOnMapClickListener((OnMapClickListener) this);//
	 * 
	 * }
	 * 
	 * } public void onMapClick(LatLng puntoPulsado) {
	 * 
	 * mMap.addMarker(new MarkerOptions().position(puntoPulsado).
	 * icon(BitmapDescriptorFactory
	 * .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
	 * 
	 * }
	 */
}

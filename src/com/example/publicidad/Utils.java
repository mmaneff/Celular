package com.example.publicidad;


import com.example.bondicat.R;


public class Utils {

	public final static int[] IMAGENES_PRINCIPAL_IDS = {
		R.drawable.pb_burger, R.drawable.pb_olx, R.drawable.publicidad_horizontal_1, R.drawable.pb_cines,
		R.drawable.pb_dontoribio, R.drawable.pb_marathon
    };
	
	public final static int[] IMAGENES_CATEGORIA_IDS = {
		R.drawable.pb_cinemacenter, R.drawable.pb_havana, R.drawable.pb_pcvirtual, R.drawable.publicidad_vertical_1,
		R.drawable.pb_claro
    };
	
	public final static int[] IMAGENES_SUBCATEGORIA_IDS = {
        R.drawable.pb_falabella, R.drawable.pb_lagaceta, R.drawable.pb_pcvirtual, R.drawable.publicidad_vertical_2
    };
	
	public final static int[] IMAGENES_NEGOCIO_IDS = {
        R.drawable.pb_falabella, R.drawable.pb_havana, R.drawable.publicidad_vertical_3, R.drawable.pb_claro,
        R.drawable.pb_fibertel
    };
	
	public final static int[] IMAGENES_PUBLICIDAD_VERTICAL_IDS = { R.drawable.publicidad_vertical_1, 
		R.drawable.publicidad_vertical_2, R.drawable.publicidad_vertical_3 
	};
	
	public final static int[] IMAGENES_PUBLICIDAD_HORIZONTAL_IDS = { R.drawable.publicidad_horizontal_2, 
		R.drawable.publicidad_horizontal_3 
	};
	
	/**
	 * 
	 * @param negocio
	 * @return
	 */
	/*
	public static String normalizarTelefonos(Negocio negocio) {
		StringBuilder result = new StringBuilder();
		List<Telefono> telefonos = TelefonoDao.getAll(negocio);
		
		if(telefonos.size() > 0) {
			for(Telefono telefono : telefonos) {
				result.append(telefono.numero);
				result.append(" - ");
			}
		}
		
		return result.toString();
	}
	*/
	
}

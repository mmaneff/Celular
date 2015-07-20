package com.example.publicidad;


import com.example.bondicat.R;


public class Utils {

	public final static int[] IMAGENES_PRINCIPAL_IDS = {
		R.drawable.publicidad_horizontal_1,		
		R.drawable.publicidad_horizontal_2, 
		R.drawable.publicidad_horizontal_3,
		R.drawable.publicidad_horizontal_1
    };
	
	public final static int[] IMAGENES_CATEGORIA_IDS = {
		R.drawable.publicidad_vertical_1, 
		R.drawable.publicidad_vertical_2, 
		R.drawable.publicidad_vertical_3,
		R.drawable.publicidad_vertical_1 
    };
	
	public final static int[] IMAGENES_SUBCATEGORIA_IDS = {
		R.drawable.publicidad_vertical_1, 
		R.drawable.publicidad_vertical_2, 
		R.drawable.publicidad_vertical_3,
		R.drawable.publicidad_vertical_1
    };
	
	public final static int[] IMAGENES_NEGOCIO_IDS = {
		R.drawable.publicidad_vertical_1, 
		R.drawable.publicidad_vertical_2, 
		R.drawable.publicidad_vertical_3,
		R.drawable.publicidad_vertical_1
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

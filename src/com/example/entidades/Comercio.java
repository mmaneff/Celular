package com.example.entidades;

public class Comercio {

	private Long id;
    private String razonSocial;
    private String categoria;
    private String subcategoria;
    private String domicilio;
    private String telefono;
    private String telefono2;
    private String telefono3;
    private String correoElectronico;
    private String sitioWeb;
    private String detalle;
    private String latitud;
    private String longitud;
	
    public Comercio(Long id, String razonSocial, String categoria,
			String subcategoria, String domicilio, String telefono, String telefono2, String telefono3,
			String correoElectronico, String sitioWeb, String detalle,
			String latitud, String longitud) {
		super();
		this.id = id;
		this.razonSocial = razonSocial;
		this.categoria = categoria;
		this.subcategoria = subcategoria;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.telefono2 = telefono2;
		this.telefono3 = telefono3;
		this.correoElectronico = correoElectronico;
		this.sitioWeb = sitioWeb;
		this.detalle = detalle;
		this.latitud = latitud;
		this.longitud = longitud;
	}
    
    public Comercio() {
		super();
	}



	public Long getId() {
		return id;
	}
	
    public void setId(Long id) {
		this.id = id;
	}
	
    public String getRazonSocial() {
		return razonSocial;
	}
	
    public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
    public String getCategoria() {
		return categoria;
	}
	
    public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
    public String getSubcategoria() {
		return subcategoria;
	}
	
    public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}
	
    public String getDomicilio() {
		return domicilio;
	}
	
    public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	
    public String getTelefono() {
		return telefono;
	}
	
    public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
    
    public String getTelefono2() {
		return telefono2;
	}
	
    public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
    public String getTelefono3() {
		return telefono3;
	}
	
    public void setTelefono3(String telefono3) {
		this.telefono3 = telefono3;
	}
	
    public String getCorreoElectronico() {
		return correoElectronico;
	}
	
    public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
    public String getSitioWeb() {
		return sitioWeb;
	}
	
    public void setSitioWeb(String sitioWeb) {
		this.sitioWeb = sitioWeb;
	}
	
    public String getDetalle() {
		return detalle;
	}
	
    public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
    
    public String getLatitud() {
		return latitud;
	}
	
    public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
    
    public String getLongitud() {
		return longitud;
	}
	
    public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	
}

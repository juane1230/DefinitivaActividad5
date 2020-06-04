package Dominio;

import java.util.ArrayList;

import Persistencia.ExtraDao;
// CLASE EXTRA
public class Extra {
	//ATRIBUTOS
	private int id;
	private String descripcion;
	
	private ExtraDao extraDao;
	//CONSTRUCTOR
	public Extra(int id, String descripcion) {
		
		this.id = id;
		this.descripcion = descripcion;
		
		
		extraDao= new ExtraDao();
	}
	//CONSTRUCTOR CON OBJETO EXTRA
	public Extra() {
		extraDao= new ExtraDao();
		
	}
	//METOS PARA IMPLANTAR EN LA CLASE EXTRA DAO
	
	public ArrayList<Extra> leerTodosExtras() throws ClassNotFoundException {
		return extraDao.leerTodosExtras();
	}
	
	public Extra leerExtra(int id) throws ClassNotFoundException {
		
		return extraDao.leerExtra(id);
	}
	public void insertar() throws ClassNotFoundException {
		extraDao.insertar(this);
		
	}
	@Override
	
	//METODO TO STRING
	public String toString() {
		return "Extra [id=" + id + ", descripcion=" + descripcion + "]";
	}
//GETTER YY SETTER
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void actualizar(int id) throws ClassNotFoundException {
		extraDao.actualizar(this, id);
		
	}

	public void eliminar() throws ClassNotFoundException {
		extraDao.eliminar(this);
		
	}

	public void eliminartTodosExtra() throws ClassNotFoundException {
		extraDao.eliminarTodo();
		
	}

	

}

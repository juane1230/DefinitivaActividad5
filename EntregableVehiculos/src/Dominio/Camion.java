package Dominio;

import java.util.ArrayList;

import Persistencia.CamionDao;
import Persistencia.VehiculoDao;
//CLASE CAMION
public class Camion  extends Vehiculo{
	//ATRIBUTOS
	private double carga;
	
	private CamionDao cDao;
	//CONSTRUCTOR
	public Camion(String matricula, String marca, String modelo, String color, double precio ,double carga) {
		super(matricula, marca, modelo, color, precio);
		this.carga = carga;
		cDao = new CamionDao();
	}
	//CONSTRUCTOR CON OBJETO DE TIPO CAMION
	public Camion( ) {
		cDao = new CamionDao();
		
	
	//GETTER Y SETTERS

}
	public double getCarga() {
		return carga;
	}
	public void setCarga(double carga) {
		this.carga = carga;
	}
	public CamionDao getcDao() {
		return cDao;
	}
	public void setcDao(CamionDao cDao) {
		this.cDao = cDao;
	}
	@Override
	//METODOS DE TIPO VEHICULO PARA IMPLANTAR EN CAMION DAO
	public ArrayList<Vehiculo> leerTodos() throws ClassNotFoundException {
		return cDao.leerTodos();
	}
	@Override
	public Vehiculo leerVehiculo(String matricula) throws ClassNotFoundException {
		return cDao.leer(matricula);
	}
	@Override
	public void insertarVehiculo() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		cDao.insertar(this);
	}
	@Override
	public void actualizar(String matricula2) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		cDao.actualizar(this, matricula2);
	}
	@Override
	public void eliminar() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		cDao.eliminar(this);
		
	}
	@Override
	public void eliminarTodo() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		cDao.eliminarTodo();
		
	}
	@Override
	//METODO TO STRING
	public String toString() {
		return "Camion [matricula="+this.getMatricula()+" ,marca="+this.getMarca()+"modelo="+this.getModelo()+"color="+this.getColor()+" ,precio="+this.getPrecio()+" ,carga=" + carga + "]";
	}
	
	
}
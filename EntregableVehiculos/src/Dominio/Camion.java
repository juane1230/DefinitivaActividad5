package Dominio;

import java.util.ArrayList;

import Persistencia.CamionDao;
import Persistencia.VehiculoDao;

public class Camion  extends Vehiculo{
	private double carga;
	
	private CamionDao cDao;
	
	public Camion(String matricula, String marca, String modelo, String color, double precio ,double carga) {
		super(matricula, marca, modelo, color, precio);
		this.carga = carga;
		cDao = new CamionDao();
	}
	public Camion( ) {
		cDao = new CamionDao();
		
	
	

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
	public String toString() {
		return "Camion [matricula="+this.getMatricula()+" ,marca="+this.getMarca()+"modelo="+this.getModelo()+"color="+this.getColor()+" ,precio="+this.getPrecio()+" ,carga=" + carga + "]";
	}
	
	
}
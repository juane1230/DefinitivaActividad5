package Dominio;

import java.util.ArrayList;

import Persistencia.TurismoDao;

public class Turismo extends Vehiculo {
	private int puertas;
	private Extra extra;
	
	private TurismoDao tDao;

	public Turismo(String matricula, String marca, String modelo, String color, double precio, int puertas, Extra extra) {
		super(matricula,marca,modelo,color,precio);
		this.puertas = puertas;
		this.extra = extra;
		tDao=new TurismoDao();
		
	}

	public Turismo() {
		tDao=new TurismoDao();
		
	}

	public int getPuertas() {
		return puertas;
	}

	public void setPuertas(int puertas) {
		this.puertas = puertas;
	}

	public Extra getExtra() {
		return extra;
	}

	public void setExtra(Extra extra) {
		this.extra = extra;
	}



	
	public String toString() {
		return "Turismo [matricula="+this.getMatricula()+" ,marca="+this.getMarca()+" ,modelo="+this.getModelo()+" ,color="+this.getColor()+" ,precio="+this.getPrecio()+" ,num_puertas=" + puertas + ", extra=" + extra + "]";
	}
	
	public ArrayList<Vehiculo> leerTodos() throws ClassNotFoundException {
		return tDao.leerTodos();
	}

	
	public Vehiculo leerVehiculo(String matricula) throws ClassNotFoundException{
		return tDao.leer(matricula);
	}

	
	public void insertarVehiculo() throws ClassNotFoundException {
		tDao.insertar(this);
		
	}

	
	public void actualizar(String matricula) throws ClassNotFoundException {
		tDao.actualizar(this, matricula);
		
	}

	
	public void eliminar() throws ClassNotFoundException {

		tDao.eliminar(this);
	}

	
	public void eliminarTodo() throws ClassNotFoundException {
		tDao.eliminarTodo();
	}
	
	

	

}

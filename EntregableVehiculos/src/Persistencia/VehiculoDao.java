package Persistencia;

import java.util.ArrayList;

import Dominio.Vehiculo;

abstract public class VehiculoDao {
	public VehiculoDao() {
		
	}
	abstract public void actualizar(Vehiculo vehiculo,String matricula) throws ClassNotFoundException;
	abstract public void eliminarTodo() throws ClassNotFoundException;
	abstract public ArrayList<Vehiculo> leerTodos() throws ClassNotFoundException;
	abstract public Vehiculo leer(String Matricula) throws ClassNotFoundException;
	abstract public void insertar(Vehiculo vehiculo) throws ClassNotFoundException;
	abstract public void eliminar(Vehiculo vehiculo) throws ClassNotFoundException;
}

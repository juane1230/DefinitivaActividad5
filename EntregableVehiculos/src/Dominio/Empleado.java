package Dominio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import Persistencia.EmpleadoDao;
// CLASE EMPLEADO
public class Empleado {
	private String loggin;
	private String password;
	private EmpleadoDao empleadoDao;
	//CONSTRUCTOT
	public Empleado(String loggin, String password) {
		this.loggin = loggin;
		this.password = password;
		empleadoDao= new EmpleadoDao();
	}
	//CONSTRUCTOR CON EL OBJETO
	public Empleado() {
		empleadoDao= new EmpleadoDao();
	}
	//GETTER Y SETTER
	public String getLoggin() {
		return loggin;
	}

	public void setLoggin(String loggin) {
		this.loggin = loggin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	// METODOS PARA LLAMAR DESDE EMPLEADO DAO
	public ArrayList<Empleado> leerTodos() throws ClassNotFoundException {
		return empleadoDao.leerTodos();

	}
	public Empleado leerEmpleado(String loggin, String password) throws ClassNotFoundException {
		return empleadoDao.leerEmpleado(loggin, password);

	}
	
}

package Persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Dominio.*;
//CLASE EMPLEADO DAO
public class EmpleadoDao {
	//CONSTRUCTOR VACIO
public EmpleadoDao() {
		
	}
//METODO INSERTAR
public boolean insertar(Empleado empleado) throws ClassNotFoundException {
	boolean registrar = false;
	
	Statement stm= null;
	Connection con=null;
	//CONSULTA EN LA BASE DE DATOS
	String sql="INSERT INTO Empleado values ('"+empleado.getLoggin()+"','"+empleado.getPassword()+")";
	
	try {	
		//EXCEPCION DE CONEXION
		con=Conexion.conectar();
		stm= con.createStatement();
		stm.execute(sql);
		registrar=true;
		stm.close();
		con.close();
	} catch (SQLException e) {
		System.out.println("Error: Clase ProfesorDaoImple, método registrar");
		e.printStackTrace();
	}
	
	//DEVUELVE EL METODO
	return registrar;
}

//METODO LEER TODO 
public ArrayList<Empleado> leerTodos() throws ClassNotFoundException {
	Connection co = null;
	Statement stm = null;
	ResultSet rs = null;
	//CONSULTA EN LA BASE DE DATOS
	String sql = "SELECT * FROM Empleado ORDER BY loggin";

	ArrayList<Empleado> listaEmpleado = new ArrayList<Empleado>();

	try {
		//EXCEPCION CONEXION
		co = Conexion.conectar();
		stm = co.createStatement();
		rs = stm.executeQuery(sql);
		while (rs.next()) {
			listaEmpleado.add(new Empleado(rs.getString(1), rs.getString(2)));
		}
		stm.close();
		rs.close();
		co.close();
		//RECORREMOS  EL ARRAY
		for (int i = 0; i < listaEmpleado.size(); i++) {
			co = Conexion.conectar();
			stm = co.createStatement();
			//HACEMOS LA CONSULTA SOBRE EL ARRAY
			sql = "SELECT * FROM Empleado WHERE loggin='" + listaEmpleado.get(i).getLoggin() + "'";
			rs = stm.executeQuery(sql);
			rs.next();
			listaEmpleado.get(i).setLoggin(rs.getString(2));
			stm.close();
			rs.close();
			co.close();
		}
	} catch (SQLException e) {
		System.out.println("Error: Clase ProfesorDaoImple, método obtener");
		e.printStackTrace();
	}
	//DEVOLVEMOS EL METODO
	return listaEmpleado;
}


//METODO LEER EMPLEADO
public Empleado leerEmpleado(String loggin, String password) throws ClassNotFoundException {
	Connection co = null;
	Statement stm = null;
	ResultSet rs = null;

	Empleado leerEmpleado = null;
	//CONSULTA SOBRE LA BASE DE DATOS
	String sql = "SELECT * FROM Usuarios WHERE loggin='" + loggin + "' AND password='" + password + "' ";
	try {
		//EXCEPCION DE CONEXION
		co = Conexion.conectar();
		stm = co.createStatement();
		rs = stm.executeQuery(sql);
		while (rs.next()) {
			leerEmpleado = new Empleado(rs.getString(1), rs.getString(2));
			System.out.println("El usuario se a logueado con exito");
			//System.out.println("Error:  método leer empleado");
		}
		stm.close();
		rs.close();
		co.close();
		
	} catch (SQLException e) {
		System.out.println("Error:  método leer empleado");
		e.printStackTrace();
	}
	return leerEmpleado;
	
}
/*
public boolean actualizar(Empleado empleado, String loggin) throws ClassNotFoundException {
	Connection connect= null;
	Statement stm= null;
	
	boolean actualizar=false;
	if(empleado.getLoggin().equals(loggin)) {		
	String sql="UPDATE Empleado SET loggin='"+empleado.getLoggin()+"', contraseña='"+empleado.getPassword()+" WHERE loggin='"+empleado.getLoggin()+"'";
	try {
		connect=Conexion.conectar();
		stm=connect.createStatement();
		stm.execute(sql);
		actualizar=true;
		stm.close();
		connect.close();
	} catch (SQLException e) {
		System.out.println("Error: método actualizar");
		e.printStackTrace();
	}		
	}else {
	String sql="INSERT INTO Empleado values ('"+empleado.getLoggin()+"','"+empleado.getPassword()+")";
	try {
		connect=Conexion.conectar();
		stm=connect.createStatement();
		stm.execute(sql);

		actualizar=true;
		stm.close();
		connect.close();
	} catch (SQLException e) {
		System.out.println("Error: método actualizar");
		e.printStackTrace();
	}	
	}
		
	
	return actualizar;
}


public boolean eliminar(Empleado empleado) throws ClassNotFoundException {
	Connection connect= null;
	Statement stm= null;
	
	boolean eliminar=false;
	
	String sql="DELETE FROM Empleado WHERE loggin='"+empleado.getLoggin()+"'";
	try {
		connect=Conexion.conectar();
		stm=connect.createStatement();
		stm.execute(sql);
		eliminar=true;
	} catch (SQLException e) {
		System.out.println("Error:  método eliminar");
		e.printStackTrace();
	}				
		
	return eliminar;
}

public boolean eliminarTodo() throws ClassNotFoundException {
	Connection connect= null;
	Statement stm= null;
	boolean eliminar=false;
		
	String sql="DELETE FROM Empleado";
	
	try {
		connect=Conexion.conectar();
		stm=connect.createStatement();
		stm.execute(sql);
		eliminar=true;
	} catch (SQLException e) {
		System.out.println("Error:  método eliminarTodo");
		e.printStackTrace();
	}		
	return eliminar;		
}
*/
}



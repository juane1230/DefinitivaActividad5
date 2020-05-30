package Persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Dominio.*;

public class EmpleadoDao {
public EmpleadoDao() {
		
	}
public boolean insertar(Empleado empleado) throws ClassNotFoundException {
	boolean registrar = false;
	
	Statement stm= null;
	Connection con=null;
	
	String sql="INSERT INTO Empleado values ('"+empleado.getLoggin()+"','"+empleado.getPassword()+")";
	
	try {			
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
	
	
	return registrar;
}


public ArrayList<Empleado> leerTodos() throws ClassNotFoundException {
	Connection co = null;
	Statement stm = null;
	ResultSet rs = null;
	String sql = "SELECT * FROM Empleado ORDER BY loggin";

	ArrayList<Empleado> listaEmpleado = new ArrayList<Empleado>();

	try {
		co = Conexion.conectar();
		stm = co.createStatement();
		rs = stm.executeQuery(sql);
		while (rs.next()) {
			listaEmpleado.add(new Empleado(rs.getString(1), rs.getString(2)));
		}
		stm.close();
		rs.close();
		co.close();
		for (int i = 0; i < listaEmpleado.size(); i++) {
			co = Conexion.conectar();
			stm = co.createStatement();
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

	return listaEmpleado;
}



public Empleado leerEmpleado(String loggin, String password) throws ClassNotFoundException {
	Connection co = null;
	Statement stm = null;
	ResultSet rs = null;

	Empleado leerEmpleado = null;
	String sql = "SELECT * FROM Usuarios WHERE loggin='" + loggin + "' AND password='" + password + "' ";
	try {
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



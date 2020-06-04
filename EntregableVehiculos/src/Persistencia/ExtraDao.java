package Persistencia;

import java.util.*;


import Dominio.Extra;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
//CALSE EXTRA
public class ExtraDao {
	//CONSTRUCOR vacio
	public ExtraDao()  {
		
	}
	//METODO LEER EXTREAS
	public ArrayList<Extra> leerTodosExtras() throws ClassNotFoundException{
		Connection co =null;
		Statement stm= null;
		ResultSet rs=null;
		//CONSULTA SOBRE LA BASE DE DATOS
		String sql="SELECT * FROM Extras ORDER BY id";
		
		ArrayList<Extra> extras =new ArrayList();
		
		try {
			//EXCEPCION CONEXION
			co= Conexion.conectar();
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				extras.add( new Extra(rs.getInt(1), rs.getString(2)));
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ExtraDaoImple, método obtener");
			e.printStackTrace();
		}
		//DEVUELVE EXTRAS
		return extras;
	}
	
	//METODO LEER EXTRA
	public Extra leerExtra(int id) throws ClassNotFoundException {
		Connection co =null;
		Statement stm= null;
		ResultSet rs=null;
		Extra leerExtra=null;
		//CONSULTA
		String sql="SELECT * FROM Extras WHERE id="+id+"";
		
		try {//EXCEPCION DE CONEXCION
			co= Conexion.conectar();
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				leerExtra = new Extra(rs.getInt(1),rs.getString(2));
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: metodo leer extra");
			e.printStackTrace();
		}	//DEVUELVE EL OBJETO DE LEER EXTRA	
		return leerExtra;
	}
	///METODO INSERTAR
	public void insertar(Extra extra) throws ClassNotFoundException {
		boolean registrar = false;
		
		Statement stm= null;
		Connection con=null;
		//CONSULTA EN LA BASE DE DATOS
		String sql="INSERT INTO Extras values ("+extra.getId()+",'"+extra.getDescripcion()+"')";
		
		try {	
			//EXCEPCION CONEXION
			con=Conexion.conectar();
			stm= con.createStatement();
			stm.execute(sql);
			registrar=true;
			stm.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error: Clase ExtraDaoImple, método registrar");
			e.printStackTrace();
		}
		
	}
	//METODO ACTUALIZAR
	public void actualizar(Extra extra, int id) throws ClassNotFoundException {
		Connection connect= null;
		Statement stm= null;
		
		boolean actualizar=false;
		//CPNSULTA SOBRE LA BASE DE DATOS
		String sql="UPDATE Extras SET id="+extra.getId()+", descripcion='"+extra.getDescripcion()+"' WHERE id="+id+"";
		try {
			//EXCEPCION DE CONEXION
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
			actualizar=true;
		} catch (SQLException e) {
			System.out.println("Error: método actualizar");
			e.printStackTrace();
		}
		
	}
	//METODO ELIMINAR
	public void eliminar(Extra extra) throws ClassNotFoundException {
		Connection connect= null;
		Statement stm= null;
		//CONSULTA EN LA BASE DE DATOS
		String sql="UPDATE Turismos SET id=1 WHERE id="+extra.getId()+"";
		try {
			//EXCEPCION CONEXION
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
			stm.close();
			connect.close();
		} catch (SQLException e) {
			System.out.println("Error: método actualizar");
			e.printStackTrace();
		}	
		sql="DELETE FROM Extras WHERE id="+extra.getId()+"";
		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
		} catch (SQLException e) {
			System.out.println("Error:  método eliminar");
			e.printStackTrace();
		}		


		
	}
	//METODO ELIMINAR TODO
	public void eliminarTodo() throws ClassNotFoundException {
		Connection connect= null;
		Statement stm= null;
		//CONSULTA
		String sql="UPDATE Turismos SET id=1";
		try {
			//EXCEPCION CONEXION
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);

			stm.close();
			connect.close();
		} catch (SQLException e) {
			System.out.println("Error: método actualizar");
			e.printStackTrace();
		}	


		sql="DELETE FROM Extras WHERE id <> 1";
		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
		} catch (SQLException e) {
			System.out.println("Error:  método eliminar");
			e.printStackTrace();
		}		
		
	}

}

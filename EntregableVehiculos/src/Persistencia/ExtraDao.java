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

public class ExtraDao {
	public ExtraDao()  {
		
	}
	public ArrayList<Extra> leerTodosExtras() throws ClassNotFoundException{
		Connection co =null;
		Statement stm= null;
		ResultSet rs=null;
		
		String sql="SELECT * FROM Extras ORDER BY id";
		
		ArrayList<Extra> extras =new ArrayList();
		
		try {			
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
		return extras;
	}
	public Extra leerExtra(int id) throws ClassNotFoundException {
		Connection co =null;
		Statement stm= null;
		ResultSet rs=null;
		Extra leerExtra=null;
		
		String sql="SELECT * FROM Extras WHERE id="+id+"";
		
		try {
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
		}		
		return leerExtra;
	}
	public void insertar(Extra extra) throws ClassNotFoundException {
		boolean registrar = false;
		
		Statement stm= null;
		Connection con=null;
		
		String sql="INSERT INTO Extras values ("+extra.getId()+",'"+extra.getDescripcion()+"')";
		
		try {			
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
	public void actualizar(Extra extra, int id) throws ClassNotFoundException {
		Connection connect= null;
		Statement stm= null;
		
		boolean actualizar=false;
		
		String sql="UPDATE Extras SET id="+extra.getId()+", descripcion='"+extra.getDescripcion()+"' WHERE id="+id+"";
		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
			actualizar=true;
		} catch (SQLException e) {
			System.out.println("Error: método actualizar");
			e.printStackTrace();
		}
		
	}
	public void eliminar(Extra extra) throws ClassNotFoundException {
		Connection connect= null;
		Statement stm= null;
		String sql="UPDATE Turismos SET id=1 WHERE id="+extra.getId()+"";
		try {
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
	public void eliminarTodo() throws ClassNotFoundException {
		Connection connect= null;
		Statement stm= null;
		String sql="UPDATE Turismos SET id=1";
		try {
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

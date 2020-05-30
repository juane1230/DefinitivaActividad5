package Persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Dominio.Camion;
import Dominio.Vehiculo;

public class CamionDao extends VehiculoDao {
	public CamionDao() {
		
	}
	
	//Metodo de leer todos los camiones
	public ArrayList <Vehiculo> leerTodos() throws ClassNotFoundException {
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Camiones ORDER BY Matricula";

		ArrayList <Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
		
		try {
			co = Conexion.conectar();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				listaVehiculos.add(new Camion(rs.getString(2), "", "","",0, rs.getDouble(1)));
			}
			stm.close();
			rs.close();
			co.close();

			for (int i = 0; i < listaVehiculos.size(); i++) {
				co = Conexion.conectar();
				stm = co.createStatement();
				sql = "SELECT * FROM Vehiculos WHERE Matricula='" + listaVehiculos.get(i).getMatricula() + "'";
				rs = stm.executeQuery(sql);
				rs.next();
				listaVehiculos.get(i).setMarca(rs.getString(2));
				listaVehiculos.get(i).setModelo(rs.getString(3));
				listaVehiculos.get(i).setColor(rs.getString(4));
				listaVehiculos.get(i).setPrecio(rs.getDouble(5));
				
				stm.close();
				rs.close();
				co.close();
			}
		} catch (SQLException e) {
			System.out.println("Error: Clase CamionDao leer todos en el metodo obtener");
			e.printStackTrace();
		}

		return listaVehiculos;
	}
	
	
	//metodo leer camion
	public Vehiculo leer(String matricula) throws ClassNotFoundException {
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;

		Vehiculo leerVehiculo = null;
		String sql = "SELECT * FROM Camiones WHERE Matricula='" + matricula + "'";
		try {
			co = Conexion.conectar();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				leerVehiculo = new Camion(rs.getString(2), "", "","",0, rs.getDouble(1));
			}
			stm.close();
			rs.close();
			co.close();
			if(leerVehiculo!=null) {
				co = Conexion.conectar();
				stm = co.createStatement();
				sql = "SELECT * FROM Vehiculos WHERE Matricula='" + leerVehiculo.getMatricula() + "'";
				rs = stm.executeQuery(sql);
				rs.next();
				leerVehiculo.setMarca(rs.getString(2));
				leerVehiculo.setModelo(rs.getString(3));
				leerVehiculo.setColor(rs.getString(4));
				leerVehiculo.setPrecio(rs.getDouble(5));
				}

		} catch (SQLException e) {
			System.out.println("Error: camionDAO leer camion metodo eliminar");
			e.printStackTrace();
		}
		return leerVehiculo;
	}
	
	//Metodo de eliminar todos los camiones
		public void eliminarTodo() throws ClassNotFoundException {
			Connection connect= null;
			Statement stm= null;
			
			boolean eliminar=false;
					
			String sql="DELETE FROM Camiones";
			
			try {
				connect=Conexion.conectar();
				stm=connect.createStatement();
				stm.execute(sql);
				eliminar=true;
			} catch (SQLException e) {
				System.out.println("Error: camionDAO metodo eliminar camiones");
				e.printStackTrace();
			}					
			
		}
	
	//metodo insertar camion
	public void insertar(Vehiculo camiones) throws ClassNotFoundException {
		Statement stm = null;
		Connection con = null;

		String sql = "INSERT INTO Vehiculos values ('" + camiones.getMatricula() + "','" + camiones.getMarca() + "','" + camiones.getModelo() 
		+ "','" + camiones.getColor() + "'," +camiones.getPrecio() +")";
		String sql2 = "INSERT INTO Camiones values (" + ((Camion) camiones).getCarga()+ ",'" + camiones.getMatricula() + "')";

		try {
			con = Conexion.conectar();
			stm = con.createStatement();
			stm.execute(sql);
			stm.execute(sql2);
			stm.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error: camionDAO metodo a�adir");
			e.printStackTrace();
		}

	}
	
	//metodo actualizar camiones
	public void actualizar(Vehiculo camiones,String matricula) throws ClassNotFoundException {
		Connection connect= null;
		Statement stm= null;
		
		boolean actualizar=false;
		if(camiones.getMatricula().equals(matricula)) {			
		String sql="UPDATE Vehiculos SET Matricula='" + camiones.getMatricula() + "', Marca='" + camiones.getMarca() + "', modelo='" + camiones.getModelo() +
				"', color='" + camiones.getColor() + "', precio=" + camiones.getPrecio() + " WHERE Matricula='" + camiones.getMatricula() + "'";
		String sql2="UPDATE Camiones SET Matricula='" + camiones.getMatricula() + "', carga=" + ((Camion) camiones).getCarga() + 
				" WHERE Matricula='" + camiones.getMatricula() + "'";
		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
			stm.execute(sql2);
			actualizar=true;
			stm.close();
			connect.close();
		} catch (SQLException e) {
			System.out.println("Error: m�todo actualizar");
			e.printStackTrace();
		}		
		}else {
		String sql="INSERT INTO Vehiculos values ('" + camiones.getMatricula() + "','" + camiones.getMarca() + "','" + camiones.getModelo()
		+ "','" + camiones.getColor() + "'," +camiones.getPrecio() +")";
		String sql2="INSERT INTO Camiones values (" + ((Camion) camiones).getCarga()+ ",'" + camiones.getMatricula() + "')";
		String sql3="DELETE FROM Camiones WHERE Matricula='"+ matricula +"'";
		String sql4="DELETE FROM Vehiculos WHERE Matricula='"+matricula+"'";
		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
			stm.execute(sql2);
			stm.execute(sql3);
			stm.execute(sql4);

			actualizar=true;
			stm.close();
			connect.close();
		} catch (SQLException e) {
			System.out.println("Error: camionDAO metodo actualizar");
			e.printStackTrace();
		}	
		}
	
	}
	
	//metodo eliminar camion
	public void eliminar(Vehiculo vehiculos) throws ClassNotFoundException {
		Connection connect= null;
		Statement stm= null;
		
		boolean eliminar=false;
		
		String sql="DELETE FROM Camiones WHERE Matricula='" +vehiculos.getMatricula() + "'";
		String sql2="DELETE FROM vehiculos WHERE Matricula='" +vehiculos.getMatricula() + "'";

		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
			stm.execute(sql2);
			eliminar=true;
		} catch (SQLException e) {
			System.out.println("Error: camionDAO metodo eliminar");
			e.printStackTrace();
		}			
	}


}

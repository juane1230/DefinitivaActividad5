package Persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Dominio.Extra;
import Dominio.Turismo;
import Dominio.Vehiculo;

public class TurismoDao extends VehiculoDao {
	public TurismoDao() {
	}

	//metodo eliminar turismo
	public void eliminarTodo() throws ClassNotFoundException {
		Connection connect = null;
		Statement stm = null;

		String sql = "DELETE FROM Turismos";
		try {
			connect = Conexion.conectar();
			stm = connect.createStatement();
			stm.execute(sql);
			sql = "DELETE FROM Vehiculos";
			connect = Conexion.conectar();
			stm = connect.createStatement();
			stm.execute(sql);
		} catch (SQLException e) {
			System.out.println("Error: TurismoDao metodo eliminar");
			e.printStackTrace();
		}
		
	}

	//metodo para leer todos los turismos
	public ArrayList <Vehiculo> leerTodos() throws ClassNotFoundException {
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Turismos ORDER BY Matricula";

		ArrayList<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
		Extra extra = new Extra();
		ArrayList<Integer> auxExtra=new ArrayList<Integer>();
		try {
			co = Conexion.conectar();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				auxExtra.add(rs.getInt(3));	
				listaVehiculos.add(new Turismo(rs.getString(2), "", "","",0 ,rs.getInt(1),extra));
			}
			stm.close();
			rs.close();
			co.close();
			for (int i=0;i<auxExtra.size();i++) {
				extra= extra.leerExtra(auxExtra.get(i));
				((Turismo)listaVehiculos.get(i)).setExtra(extra);

			}
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
			System.out.println("Error: Clase TurismoDao método leer todos en obtener");
			e.printStackTrace();
		}

		return listaVehiculos;
	}
	
	//metodo para leer un turismo
	public Vehiculo leer(String Matricula) throws ClassNotFoundException {
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;

		Vehiculo leerVehiculo = null;
		String sql = "SELECT * FROM Turismos WHERE Matricula='" + Matricula + "'";
		Extra extra = new Extra();
		int auxExtra=0;
	    boolean encontrado=false;
		try {
			co = Conexion.conectar();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
			auxExtra=rs.getInt(3);
			encontrado=true;
			leerVehiculo = new Turismo(rs.getString(2), "", "","", 0,rs.getInt(1),extra);
			}
			stm.close();
			rs.close();
			co.close();
			if(encontrado) {
			extra=extra.leerExtra(auxExtra);
			((Turismo)leerVehiculo).setExtra(extra);
			}
			if (leerVehiculo != null) {
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
			System.out.println("Error: TurismoDao método eliminar");
			e.printStackTrace();
		}
		return leerVehiculo;
	}
	
	//metodo para insertar un turismo
	public void insertar(Vehiculo turismos) throws ClassNotFoundException {
		Statement stm = null;
		Connection con = null;

		String sql = "INSERT INTO Vehiculos values ('" + turismos.getMatricula() + "','" + turismos.getMarca() + "','"
				+ turismos.getModelo() + "','" + turismos.getColor() + "'," +turismos.getPrecio() +")";
		String sql2 = "INSERT INTO Turismos values ("+((Turismo)turismos).getPuertas()+",'" + turismos.getMatricula() + "'," + ((Turismo) turismos).getExtra().getId() + ")";

		try {
			con = Conexion.conectar();
			stm = con.createStatement();
			stm.execute(sql);
			stm.execute(sql2);
			stm.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error: TurismoDao metodo registrar");
			e.printStackTrace();
		}
		
	}
	
	//metodo para actualizar turismo
	public void actualizar(Vehiculo turismos, String matricula) throws ClassNotFoundException {
		Connection connect = null;
		Statement stm = null;

		if(turismos.getMatricula().equals(matricula)) {			
		String sql="UPDATE Vehiculos SET Matricula='"+turismos.getMatricula()+"', Marca='"+turismos.getMarca()+"', modelo='"+turismos.getModelo()+"', color='"+turismos.getColor()+"', precio="+turismos.getPrecio()+" WHERE Matricula='"+turismos.getMatricula()+"'";
		String sql2="UPDATE Turismos SET Matricula='"+turismos.getMatricula()+"', id="+((Turismo)turismos).getExtra().getId()+", numpuertas="+((Turismo)turismos).getPuertas()+" WHERE Matricula='"+turismos.getMatricula()+"'";
		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
			stm.execute(sql2);
			stm.close();
			connect.close();
		} catch (SQLException e) {
			System.out.println("Error: método actualizar");
			e.printStackTrace();
		}		
		}else {
		String sql="INSERT INTO Vehiculos values ('" + turismos.getMatricula() + "','" + turismos.getMarca() + "','"
				+ turismos.getModelo() + "','" + turismos.getColor() + "'," +turismos.getPrecio() +")";
		String sql2="INSERT INTO Turismos values ("+((Turismo)turismos).getPuertas()+",'" + turismos.getMatricula() + "'," + ((Turismo) turismos).getExtra().getId() + ")";
		String sql3="DELETE FROM Turismos WHERE Matricula='"+matricula+"'";
		String sql4="DELETE FROM Vehiculos WHERE Matricula='"+matricula+"'";
		try {
			connect=Conexion.conectar();
			stm=connect.createStatement();
			stm.execute(sql);
			stm.execute(sql2);
			stm.execute(sql3);
			stm.execute(sql4);

			stm.close();
			connect.close();
		} catch (SQLException e) {
			System.out.println("Error: TurismoDao metodo actualizar");
			e.printStackTrace();
		}	
		}
		
	}

	public void eliminar(Vehiculo vehiculo) throws ClassNotFoundException {
		Connection connect = null;
		Statement stm = null;
		

		String sql = "DELETE FROM Turismos WHERE Matricula='" + vehiculo.getMatricula() + "'";
		String sql2 = "DELETE FROM Vehiculos WHERE Matricula='" + vehiculo.getMatricula() + "'";

		try {
			connect = Conexion.conectar();
			stm = connect.createStatement();
			stm.execute(sql);
			stm.execute(sql2);
		} catch (SQLException e) {
			System.out.println("Error: TurismoDao método eliminar");
			e.printStackTrace();
		}
	}
	
}


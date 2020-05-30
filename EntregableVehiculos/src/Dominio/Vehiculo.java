package Dominio;

import java.util.ArrayList;

import Persistencia.VehiculoDao;

abstract public class Vehiculo {
	 protected String matricula;
		protected String marca;
		protected String modelo;
		protected String color;
		protected double precio;
		protected VehiculoDao vDao;
		
		public Vehiculo(String matricula, String marca, String modelo, String color, double precio ) {
			
			this.matricula = matricula;
			this.marca = marca;
			this.modelo = modelo;
			this.color = color;
			this.precio = precio;
			this.vDao = vDao;
		}
		public Vehiculo() {
			
		}
		public String getMatricula() {
			return matricula;
		}
		public void setMatricula(String matricula) {
			this.matricula = matricula;
		}
		public String getMarca() {
			return marca;
		}
		public void setMarca(String marca) {
			this.marca = marca;
		}
		public String getModelo() {
			return modelo;
		}
		public void setModelo(String modelo) {
			this.modelo = modelo;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public double getPrecio() {
			return precio;
		}
		public void setPrecio(double precio) {
			this.precio = precio;
		}
		public VehiculoDao getvDao() {
			return vDao;
		}
		public void setvDao(VehiculoDao vDao) {
			this.vDao = vDao;
		}
		abstract public ArrayList<Vehiculo> leerTodos() throws ClassNotFoundException ;

		public abstract Vehiculo leerVehiculo(String matricula) throws ClassNotFoundException;

		public abstract void insertarVehiculo() throws ClassNotFoundException;

		public abstract void actualizar(String matricula) throws ClassNotFoundException;

		public abstract void eliminar() throws ClassNotFoundException;

		public abstract void eliminarTodo() throws ClassNotFoundException;

}

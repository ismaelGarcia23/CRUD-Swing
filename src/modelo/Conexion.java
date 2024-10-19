/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ismael engrique
 */
public class Conexion {
    
    private String server = "localhost:3306";
    private String bd = "labds1tg1";
    private String user = "user";
    private String pass = "1234";
    private String url = "jdbc:mysql://" + server + "/" + bd + "";
    
    private Connection Con;
    
    public Connection getCon() {
        return Con;
    }
    
    public void setCon(Connection con) {
		Con = con;
    }
	
	public Connection Conectar() {
		try {
			setCon(DriverManager.getConnection(url, user, pass));
			System.out.println("Conectado");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return getCon(); // Devolver la conexión
	}
	
	public void Desconectar() {
		try {
			if (getCon() != null && !getCon().isClosed()) {
				getCon().close();
				System.out.println("Desconectado");
			}
		} catch (SQLException e) {
			System.err.println("Error al desconectar: " + e.getMessage());
		}
	}
	
	public Conexion() {
		Conectar();
	}
    
    
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteConection {
	
	public static List leerFichero() {
	      
	    List<String> lista = new ArrayList<String>();
	    String fichero = "nombres.txt";
	    try {
	      FileReader fr = new FileReader(fichero);
	      BufferedReader br = new BufferedReader(fr);
	      String linea;
	      while((linea = br.readLine()) != null)
		    lista.add(linea);
	        System.out.println(linea);
	      fr.close();
	    }
	    catch(Exception e) {
	      System.out.println("Excepcion leyendo fichero "+ fichero + ": " + e);
	    }
		return lista;
	}
	
	public static Connection getConnection(String dir) {
		Connection con = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + dir);
			System.out.println("Connected");
		}catch(Exception e) {
			System.out.println(e);
		}
		return con;
	}
	
	public static void insertData(String tabla, String atributos, String datos, Connection con) {
		try {
			Statement state = con.createStatement();
			state.executeUpdate("INSERT INTO " + tabla + " ("+ atributos + ")"+ " VALUES " + " (" + datos + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		Connection c = getConnection("database.sqlite3");
		insertData("Especie", "Nombre, NombreCientifico, Descripcion, IdZona", "'Caballo', 'caballus', 'Carrera', 2", c);
	}
}

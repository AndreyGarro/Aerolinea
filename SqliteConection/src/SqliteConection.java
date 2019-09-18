import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SqliteConection {
	
	public static List<String> readList(String fichero) {
	      
	    List<String> lista = new ArrayList<String>();
	    try {
	      File fr = new File(fichero);
	      BufferedReader in = new BufferedReader(
	   		   new InputStreamReader(new FileInputStream(fr), "UTF8"));
//	      BufferedReader br = new BufferedReader(fr);
	      String linea;
	      while((linea = in.readLine()) != null)
		    lista.add(linea);
	        System.out.println(linea);
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
//			state.executeUpdate("INSERT INTO " + tabla + " ("+ atributos + ")"+ " VALUES " + datos);
			System.out.println("INSERT INTO " + tabla + " ("+ atributos + ")"+ " VALUES " + datos);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> generateCode(String opCode, int cant) {
		Random rand = new Random();
		List<String> listaCodigos = new ArrayList<String>();
		
		//Carga lista para códigos
		List<String> listaAbecedario = readList("SqliteConection\\data\\abecedario.txt");
		List<String> listaNumeros = readList("SqliteConection\\data\\numeros.txt");
		
		//Longitudes de listas
		int lenAbecedario = listaAbecedario.size();
		int lenNumeros = listaNumeros.size();
		int lenCodigos = listaCodigos.size();
		
		String codigo;
		for(int i = 0; i < cant; i++) {
			codigo = opCode + "-" + listaAbecedario.get(rand.nextInt(lenAbecedario)) + 
					listaAbecedario.get(rand.nextInt(lenAbecedario)) + 
					listaAbecedario.get(rand.nextInt(lenAbecedario)) +
					listaAbecedario.get(rand.nextInt(lenAbecedario)) +
					listaAbecedario.get(rand.nextInt(lenAbecedario)) +
					listaAbecedario.get(rand.nextInt(lenAbecedario)) +
					listaNumeros.get(rand.nextInt(lenNumeros))+
					listaNumeros.get(rand.nextInt(lenNumeros));
			
			for(String j : listaCodigos) {
				if(j == codigo) {
					codigo = "";
					i--;
					continue;
				}
			}
			listaCodigos.add(codigo);
		}
		
		return listaCodigos;
	}

	public static void main(String[] args) throws Exception {
		Connection c = getConnection("C:\\Users\\diego\\OneDrive\\Documentos\\Bases de Datos\\TareaCorta1 - copia\\database.sqlite3");
		Random rand = new Random();
		String codigo;
		
		//Carga datos para los empleados
		List<String> listaNombres = readList("SqliteConection\\data\\nombres.txt");
		List<String> listaApellidos = readList("SqliteConection\\data\\apellidos.txt");
		List<String> listaCedulas = readList("SqliteConection\\data\\cedulas.txt");
		List<String> listaCuentas = readList("SqliteConection\\data\\cuentas_banco.txt");
		List<String> listaDirecciones = readList("SqliteConection\\data\\direcciones.txt");
		List<String> listaHorarios = readList("SqliteConection\\data\\horarios.txt");
		List<String> listaCodigos = new ArrayList<String>();
		
		int lenCuentas = listaCuentas.size();
		int lenNombres = listaNombres.size();
		int lenApellidos = listaApellidos.size();
		int lenCedulas = listaCedulas.size();
		int lenDirecciones = listaDirecciones.size();
		int lenHorarios = listaHorarios.size();
		
		//Inserta datos de los empleados
		
		listaCodigos = generateCode("EMP", 80);
		String datos = "";
		for (int i = 0; i < 80; i++) {
			if(i == 79) {
				datos += "(\""+ listaCodigos.get(i) + "\", \"" + listaNombres.get(rand.nextInt(lenNombres)) + "\", \""
						+ listaApellidos.get(rand.nextInt(lenApellidos)) + "\", \""+ listaApellidos.get(rand.nextInt(lenApellidos)) + "\", \""
						+ listaCedulas.get(i) + "\", \"" + listaCuentas.get(i) + "\", \"" + listaHorarios.get(rand.nextInt(lenHorarios)) + "\", \""
						+ listaDirecciones.get(rand.nextInt(lenDirecciones)) + "\");\n";
			}else {
				datos += "(\""+ listaCodigos.get(i) + "\", \"" + listaNombres.get(rand.nextInt(lenNombres)) + "\", \""
						+ listaApellidos.get(rand.nextInt(lenApellidos)) + "\", \""+ listaApellidos.get(rand.nextInt(lenApellidos)) + "\", \""
						+ listaCedulas.get(i) + "\", \"" + listaCuentas.get(i) + "\", \"" + listaHorarios.get(rand.nextInt(lenHorarios)) + "\", \""
						+ listaDirecciones.get(rand.nextInt(lenDirecciones)) + "\"),\n";	
			}
		}
		
//		insertData("Empleado", "Codigo, Nombre, PrimerApellido, SegundoApellido, Cedula, CuentaBanco, Horario, Direccion", datos, c);
		
//		----------------------------------------------------------------------------------------------------------------------------------------
		//Carga datos para aerolinea
		List<String> listaAerolineas = readList("SqliteConection\\data\\aerolineas.txt");
		int lenAerolineas = listaAerolineas.size();
		
		
		//Inserta datos de las aerolineas
		listaCodigos = generateCode("AIR", 30);
		datos = "";
		for (int i = 0; i < 30; i++) {
			if(i == 29) {
				datos += "(\""+ listaCodigos.get(i) + "\", \"" + listaAerolineas.get(i) + "\", "
						+ (rand.nextInt(10)+10) + ");";
			}else {
				datos += "(\""+ listaCodigos.get(i) + "\", \"" + listaAerolineas.get(i) + "\", "
						+ (rand.nextInt(10)+10) + "),\n";	
			}
		}
		
//		insertData("Aerolinea", "Codigo, Nombre, CantidadEmpleados", datos, c);
		
//		----------------------------------------------------------------------------------------------------------------------------------------
		//Carga datos para las aerolineas
		List<String> listaAeropuertos = readList("SqliteConection\\data\\aeropuertos.txt");
		List<String> listaNumerosAero = readList("SqliteConection\\data\\telefonos.txt");
		List<String> listaCiudades = readList("SqliteConection\\data\\ciudades.txt");
		int lenAeropuertos = listaAeropuertos.size();
		int lenNumerosAero = listaNumerosAero.size();
		int lenCiudades = listaCiudades.size();
		
		//Inserta datos de las aerolineas
		listaCodigos = generateCode("AER", 42);
		datos = "";
		for (int i = 0; i < 42; i++) {
			if(i == 41) {
				datos += "(\""+ listaCodigos.get(i) + "\", \"" + listaAeropuertos.get(i) + "\", \""
						+ listaNumerosAero.get(i) + "\", \"" + listaCiudades.get(i) +"\");";
			}else {
				datos += "(\""+ listaCodigos.get(i) + "\", \"" + listaAeropuertos.get(i) + "\", \""
						+ listaNumerosAero.get(i) + "\", \"" + listaCiudades.get(i) +"\"),\n";	
			}
		}
		
//		insertData("Aeropuerto", "Codigo, Nombre, NumeroTelefono, Localizacion", datos, c);
		
//		----------------------------------------------------------------------------------------------------------------------------------------
		//Carga datos para Avion
		List<String> listaModeloAvion = readList("SqliteConection\\data\\modelos_aviones.txt");
		List<String> listaFabAviones = readList("SqliteConection\\data\\fab_aviones.txt");
		int lenModeloAvion = listaModeloAvion.size();
		int lenFabAviones = listaFabAviones.size();
		List<String> listaEstado = new ArrayList<String>();
		listaEstado.add("Activo");
		listaEstado.add("En reparación");
		listaEstado.add("Inactivo");
		
		//Inserta datos de los aeropuertos
		listaCodigos = generateCode("AV", 60);
		datos = "";
		for (int i = 0; i < 60; i++) {
			if(i == 59) {
				datos += "(\""+ listaCodigos.get(i) + "\", \"" + listaModeloAvion.get(rand.nextInt(lenModeloAvion)) + "\", "
						+ (rand.nextInt(350)+150) + ", "+rand.nextInt(60) + ", \""+ listaEstado.get(rand.nextInt(3)) +"\", \""+
						listaFabAviones.get(rand.nextInt(lenFabAviones))+ "\");";
			}else {
				datos += "(\""+ listaCodigos.get(i) + "\", \"" + listaModeloAvion.get(rand.nextInt(lenModeloAvion)) + "\", "
						+ (rand.nextInt(350)+150) +", "+ rand.nextInt(60) + ", \""+ listaEstado.get(rand.nextInt(3)) +"\", \""+
						listaFabAviones.get(rand.nextInt(lenFabAviones))+ "\"),\n";
			}
		}
		insertData("Avion", "Codigo, Nombre, NumeroTelefono, Localizacion", datos, c);
		
//		----------------------------------------------------------------------------------------------------------------------------------------
		//Carga datos para clase
		List<String> listaClases = new ArrayList<String>();
		List<String> listaPrecios = new ArrayList<String>();
	
		listaClases.add("Primera Clase");
		listaPrecios.add("");
		listaClases.add("Clase Ejecutiva");
		listaPrecios.add("");
		listaClases.add("Clase Premium Economy");
		listaPrecios.add("");
		listaClases.add("Clase Turista");
		listaPrecios.add("");
		
		//Inserta datos de los aeropuertos
		listaCodigos = generateCode("AV", 60);
		datos = "";
		for (int i = 0; i < 60; i++) {
			if(i == 59) {
				datos += "(\""+ listaCodigos.get(i) + "\", \"" + listaModeloAvion.get(rand.nextInt(lenModeloAvion)) + "\", "
						+ (rand.nextInt(350)+150) + ", "+rand.nextInt(60) + ", \""+ listaEstado.get(rand.nextInt(3)) +"\", \""+
						listaFabAviones.get(rand.nextInt(lenFabAviones))+ "\");";
			}else {
				datos += "(\""+ listaCodigos.get(i) + "\", \"" + listaModeloAvion.get(rand.nextInt(lenModeloAvion)) + "\", "
						+ (rand.nextInt(350)+150) +", "+ rand.nextInt(60) + ", \""+ listaEstado.get(rand.nextInt(3)) +"\", \""+
						listaFabAviones.get(rand.nextInt(lenFabAviones))+ "\"),\n";
			}
		}
//		insertData("Avion", "Codigo, Nombre, NumeroTelefono, Localizacion", datos, c);
	}
}

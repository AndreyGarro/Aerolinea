import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

public class SqliteConection {
	
	public static String getDate (Date fecha, int horas){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); 
		calendar.add(Calendar.HOUR, horas);
		java.util.Date temp = calendar.getTime(); 
        String strDateFormat = "dd-MM-YYYY HH:mm:ss";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        String result_date = objSDF.format(temp);
		return result_date;
	}

	public static List<String> readList(String fichero) {

		List<String> lista = new ArrayList<String>();
		try {
			File fr = new File(fichero);
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fr), "UTF8"));
			String linea;
			while ((linea = in.readLine()) != null)
				lista.add(linea);
		} catch (Exception e) {
			System.out.println("Excepcion leyendo fichero " + fichero + ": " + e);
		}
		return lista;
	}
	
	public static List<Integer> consultInteger(Connection con, String consult, String dato) {
        List<Integer> result = new ArrayList<Integer>();
        try (Statement stmt  = con.createStatement();
             ResultSet rs    = stmt.executeQuery(consult)){
            while (rs.next()) {
                result.add(rs.getInt(dato));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
	
	public static List<String> consultString(Connection con, String consult, String dato) {
        List<String> result = new ArrayList<String>();
        try (Statement stmt  = con.createStatement();
             ResultSet rs    = stmt.executeQuery(consult)){
            while (rs.next()) {
                result.add(rs.getString(dato));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

	public static Connection getConnection(String dir) {
		Connection con = null;

		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + dir);
			System.out.println("Connected");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public static void insertData(String tabla, String atributos, String datos, Connection con) {
		try {
			Statement state = con.createStatement();
			state.executeUpdate("INSERT INTO " + tabla + " ("+ atributos + ")"+ " VALUES" + datos);
//			System.out.println("INSERT INTO " + tabla + " (" + atributos + ")" + " VALUES " + datos);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<String> generateCode(String opCode, int cant) {
		Random rand = new Random();
		List<String> listaCodigos = new ArrayList<String>();

		// Carga lista para códigos
		List<String> listaAbecedario = readList("..\\SqliteConection\\data\\abecedario.txt");
		List<String> listaNumeros = readList("..\\SqliteConection\\data\\numeros.txt");

		// Longitudes de listas
		int lenAbecedario = listaAbecedario.size();
		int lenNumeros = listaNumeros.size();
		int lenCodigos = listaCodigos.size();

		String codigo;
		for (int i = 0; i < cant; i++) {
			codigo = opCode + "-" + listaAbecedario.get(rand.nextInt(lenAbecedario))
					+ listaAbecedario.get(rand.nextInt(lenAbecedario))
					+ listaAbecedario.get(rand.nextInt(lenAbecedario))
					+ listaAbecedario.get(rand.nextInt(lenAbecedario))
					+ listaAbecedario.get(rand.nextInt(lenAbecedario))
					+ listaAbecedario.get(rand.nextInt(lenAbecedario)) + listaNumeros.get(rand.nextInt(lenNumeros))
					+ listaNumeros.get(rand.nextInt(lenNumeros));

			for (String j : listaCodigos) {
				if (j == codigo) {
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
		Connection c = getConnection("..\\database.sqlite3");
		String datos;
		List<String> listaCodigos = new ArrayList<String>();
		Random rand = new Random();

// ----------------------------------------------------------------------------------------------------------------------------------------
	// Carga datos para horarios
	List<String> listaHorasOrdenadas = readList("..\\SqliteConection\\data\\horas_ordenadas.txt");
	List<String> listaSiNo = new ArrayList<String>();
	
	listaSiNo.add("Si");
	listaSiNo.add("No");
	int lenSiNo = listaSiNo.size();
	int lenHorasOrdenadas = listaHorasOrdenadas.size();
	
	// Inserta datos de las horarios
	listaCodigos = generateCode("AIR", 30);
	datos = "";
	for (int i = 0; i < 300; i++) {
		if (i == 299) {
			datos += "(\"" + listaSiNo.get(rand.nextInt(lenSiNo))+ "\", \"" 
					+ listaSiNo.get(rand.nextInt(lenSiNo)) + "\", \""
					+ listaSiNo.get(rand.nextInt(lenSiNo)) + "\", \""
					+ listaSiNo.get(rand.nextInt(lenSiNo)) + "\", \""
					+ listaSiNo.get(rand.nextInt(lenSiNo)) + "\", \""
					+ listaSiNo.get(rand.nextInt(lenSiNo)) + "\", \""
					+ listaSiNo.get(rand.nextInt(lenSiNo)) + "\", \"" 
					+ listaHorasOrdenadas.get(rand.nextInt(lenHorasOrdenadas)) + "\", \""
					+ listaHorasOrdenadas.get(rand.nextInt(lenHorasOrdenadas)) + "\");";
		}else if(i == 0) { 
			datos += "(\"Si\", \"Si\", \"Si\", \"Si\", \"Si\", \"Si\", \"Si\", \"00:00:00\", \"00:00:00\"),\n";
		}else {
			datos += "(\"" + listaSiNo.get(rand.nextInt(lenSiNo))+ "\", \"" 
					+ listaSiNo.get(rand.nextInt(lenSiNo)) + "\", \""
					+ listaSiNo.get(rand.nextInt(lenSiNo)) + "\", \""
					+ listaSiNo.get(rand.nextInt(lenSiNo)) + "\", \""
					+ listaSiNo.get(rand.nextInt(lenSiNo)) + "\", \""
					+ listaSiNo.get(rand.nextInt(lenSiNo)) + "\", \""
					+ listaSiNo.get(rand.nextInt(lenSiNo)) + "\", \"" 
					+ listaHorasOrdenadas.get(rand.nextInt(lenHorasOrdenadas)) + "\", \""
					+ listaHorasOrdenadas.get(rand.nextInt(lenHorasOrdenadas)) + "\"),\n";
		}
	}

		insertData("Horario", "Lunes, Martes, Miercoles, Jueves, Viernes, Sabado, Domingo, HoraEntrada, HoraSalida", datos, c);
//--------------------------------------------------------------------------------------------------------------------------------------------

		String codigo;

		// Carga datos para los empleados
		List<String> listaNombres = readList("..\\SqliteConection\\data\\nombres.txt");
		List<String> listaApellidos = readList("..\\SqliteConection\\data\\apellidos.txt");
		List<String> listaCedulas = readList("..\\SqliteConection\\data\\cedulas.txt");
		List<String> listaCuentas = readList("..\\SqliteConection\\data\\cuentas_banco.txt");
		List<String> listaDirecciones = readList("..\\SqliteConection\\data\\direcciones.txt");

		int lenCuentas = listaCuentas.size();
		int lenNombres = listaNombres.size();
		int lenApellidos = listaApellidos.size();
		int lenCedulas = listaCedulas.size();
		int lenDirecciones = listaDirecciones.size();
		
		List<Integer> idHorarios = consultInteger(c, "SELECT IdHorario FROM Horario;", "IdHorario");
		int lenIdHorarios = idHorarios.size();
		
		// Inserta datos de los empleados
		datos = "";
		listaCodigos = generateCode("EMP", 1000);
		for (int i = 0; i < 1000; i++) {
			if (i == 999) {
				datos += "(\"" + listaCodigos.get(i) + "\", \"" + listaNombres.get(rand.nextInt(lenNombres)) + "\", \""
						+ listaApellidos.get(rand.nextInt(lenApellidos)) + "\", \""
						+ listaApellidos.get(rand.nextInt(lenApellidos)) + "\", \"" + listaCedulas.get(rand.nextInt(lenCedulas)) + "\", \""
						+ listaCuentas.get(rand.nextInt(lenCuentas)) + "\", \"" 
						+ listaDirecciones.get(rand.nextInt(lenDirecciones)) + "\", " 
						+ idHorarios.get(rand.nextInt(lenIdHorarios)) + ");";
			} else {
				datos += "(\"" + listaCodigos.get(i) + "\", \"" + listaNombres.get(rand.nextInt(lenNombres)) + "\", \""
						+ listaApellidos.get(rand.nextInt(lenApellidos)) + "\", \""
						+ listaApellidos.get(rand.nextInt(lenApellidos)) + "\", \"" + listaCedulas.get(rand.nextInt(lenCedulas)) + "\", \""
						+ listaCuentas.get(rand.nextInt(lenCuentas)) + "\", \"" 
						+ listaDirecciones.get(rand.nextInt(lenDirecciones)) + "\", " 
						+ idHorarios.get(rand.nextInt(lenIdHorarios))+ "),\n";
			}
		}
		 insertData("Empleado", "Codigo, Nombre, PrimerApellido, SegundoApellido, Cedula, CuentaBanco, Direccion, IdHorario", datos, c);
// ----------------------------------------------------------------------------------------------------------------------------------------
		// Carga datos para las aeropuertos
		List<String> listaAeropuertos = readList("..\\SqliteConection\\data\\aeropuertos.txt");
		List<String> listaNumeros = readList("..\\SqliteConection\\data\\telefonos.txt");
		List<String> listaCiudades = readList("..\\SqliteConection\\data\\ciudades.txt");
		int lenAeropuertos = listaAeropuertos.size();
		int lenCiudades = listaCiudades.size();
		int lenNumeros = listaNumeros.size();
		
		// Inserta datos de las aeropuertos
		listaCodigos = generateCode("AER", 42);
		datos = "";
		for (int i = 0; i < 42; i++) {
			if (i == 41) {
				datos += "(\"" + listaCodigos.get(i) + "\", \"" + listaAeropuertos.get(i) + "\", \""
						+ listaNumeros.get(i) + "\", \"" + listaCiudades.get(i) + idHorarios.get(0) + "\", "
						+ idHorarios.get(0) +");";
			} else {
				datos += "(\"" + listaCodigos.get(i) + "\", \"" + listaAeropuertos.get(i) + "\", \""
						+ listaNumeros.get(i) + "\", \"" + listaCiudades.get(i) + idHorarios.get(0) + "\", "
						+ idHorarios.get(0) +"), \n";
			}
		}

		 insertData("Aeropuerto", "Codigo, Nombre, NumeroDeTelefono, Localizacion, IdHorario", datos, c);

// ----------------------------------------------------------------------------------------------------------------------------------------
		//Carga datos para pasaporte
		List<String> listaNacionalidades = readList("..\\SqliteConection\\data\\nacionalidades.txt");
		List<String> listaExpPasaporte = readList("..\\SqliteConection\\data\\exp_pasaporte.txt");
		int lenNacionalidades = listaNacionalidades.size();
		int lenExpPasaporte = listaExpPasaporte.size();
		

		List<String> listaTiposPas = new ArrayList<String>();
		listaTiposPas.add("Pasaporte Ordinario");
		listaTiposPas.add("Pasaporte Oficial");
		listaTiposPas.add("Pasaporte Diplom�tico");
		int lenTiposPas = listaTiposPas.size();

		datos = "";
		for (int i = 0; i < 200; i++) {
			if (i == 199) {
				datos += "(\""+listaTiposPas.get(rand.nextInt(lenTiposPas)) + "\", \"" + listaNacionalidades.get(rand.nextInt(lenNacionalidades))
						+ "\", \"" + listaNumeros.get(rand.nextInt(lenNumeros)) + "\", \""
						+ listaExpPasaporte.get(rand.nextInt(lenExpPasaporte)) + "\");";
			} else {
				datos += "(\""+listaTiposPas.get(rand.nextInt(lenTiposPas)) + "\", \"" + listaNacionalidades.get(rand.nextInt(lenNacionalidades))
				+ "\", \"" + listaNumeros.get(rand.nextInt(lenNumeros)) + "\", \""
				+ listaExpPasaporte.get(rand.nextInt(lenExpPasaporte)) + "\"),\n";
			}
		}
		insertData("Pasaporte", "Tipo, Nacionalidad, Numero, FechaExpiracion",datos, c);

// ----------------------------------------------------------------------------------------------------------------------------------------
		// Inserta datos de la Bodega
		List<String> listaBodegas = new ArrayList<String>();
		listaBodegas.add("Bodega_R38");
		listaBodegas.add("Bodega_T34");
		listaBodegas.add("Bodega_Y56");
		listaBodegas.add("Bodega_U89");
		listaBodegas.add("Bodega_I45");
		listaBodegas.add("Bodega_D45");
		listaBodegas.add("Bodega_Q12");

		datos = "";
		for (int i = 0; i < 7; i++) {
			if (i == 6) {
				datos += "(\"" + listaBodegas.get(i) + "\");";
			} else {
				datos += "(\"" + listaBodegas.get(i) +"\"),\n";
			}
		}
		 insertData("Bodega", "Nombre", datos, c);	

// ----------------------------------------------------------------------------------------------------------------------------------------
		// Inserta datos del taller
		List<String> listaTalleres = new ArrayList<String>();
		listaTalleres.add("Taller_Alpha");
		listaTalleres.add("Taller_Beta");
		listaTalleres.add("Taller_F30");
		listaTalleres.add("Taller_Logic");
		listaTalleres.add("Taller_AF7");
		listaTalleres.add("Taller_777");
		listaTalleres.add("Taller_Centro");
		listaTalleres.add("Taller_Norte");
		listaTalleres.add("Taller_Sur");
		listaTalleres.add("Taller_FTF");
		listaTalleres.add("Taller_JIJI");
		datos = "";
		for (int i = 0; i < 11; i++) {
			if (i == 10) {
				datos += "(\"" + listaTalleres.get(i)+ "\");";
			} else {
				datos += "(\"" + listaTalleres.get(i) +"\"),\n";
			}
		}
		insertData("Taller", "Nombre", datos, c);	

// ----------------------------------------------------------------------------------------------------------------------------------------
		// Inserta datos del Fabricante
		List<String> listaFabricantes = readList("..\\SqliteConection\\data\\fab_aviones.txt");
		int lenFabricantes = listaFabricantes.size();
		datos = "";
		for (int i = 0; i < lenFabricantes; i++) {
			if (i == lenFabricantes - 1) {
				datos += "(\"" + listaFabricantes.get(i) + "\");";
			} else {
				datos += "(\"" + listaFabricantes.get(i) +"\"),\n";
			}
		}
		insertData("Fabricante", "Nombre", datos, c);					
		
// ----------------------------------------------------------------------------------------------------------------------------------------
		// Carga datos para las PuestoAeropuerto
		List<String> listaPuestoAero = new ArrayList<String>();
		List<Integer> listaSalarioAero = new ArrayList<Integer>();
		listaPuestoAero.add("Despachador de Vuelos");
		listaSalarioAero.add(500000);
		listaPuestoAero.add("Tecnico Administrativo");
		listaSalarioAero.add(550000);
		listaPuestoAero.add("Agente de Servicios Aeroportuarios");
		listaSalarioAero.add(600000);
		listaPuestoAero.add("Auxiliar de Tierra");
		listaSalarioAero.add(450000);

		// Inserta datos de las PuestoAeropuerto
		datos = "";
		int temp = 0;
		for (int i = 0; i < 42; i++) {
			temp = rand.nextInt(3);
			if (i == 41) {
				datos += "(\"" + listaPuestoAero.get(temp) + "\", "  + listaSalarioAero.get(temp) +");";
			} else {
				datos += "(\"" + listaPuestoAero.get(temp) + "\", "  + listaSalarioAero.get(temp) +"),\n";
			}
		}

		insertData("PuestoAeropuerto", "PuestoTrabajo, Salario", datos, c);

// ----------------------------------------------------------------------------------------------------------------------------------------
		// Carga datos para las PuestoAerolinea
		List<String> listaPuestoAerolinea = new ArrayList<String>();
		List<Integer> listaSalarioAerolinea = new ArrayList<Integer>();
		listaPuestoAerolinea.add("Piloto");
		listaSalarioAerolinea.add(800000);
		listaPuestoAerolinea.add("Azafata");
		listaSalarioAerolinea.add(550000);
		listaPuestoAerolinea.add("Copiloto");
		listaSalarioAerolinea.add(450000);

		// Inserta datos de las PuestoAerolinea
		datos = "";
		for (int i = 0; i < 42; i++) {
			temp = rand.nextInt(2);
			if (i == 41) {
				datos += "(\"" + listaPuestoAerolinea.get(temp) + "\", " + listaSalarioAerolinea.get(temp) + ");";
			} else {
				datos += "(\"" + listaPuestoAerolinea.get(temp) + "\", " + listaSalarioAerolinea.get(temp) + "),\n";
			}
		}

		 insertData("PuestoAerolinea", "PuestoTrabajo, Salario", datos, c);		

// ----------------------------------------------------------------------------------------------------------------------------------------
		// Carga datos para clase
		List<String> listaClases = new ArrayList<String>();
		List<Integer> listaPrecios = new ArrayList<Integer>();

		listaClases.add("Primera Clase");
		listaPrecios.add(500000);
		listaClases.add("Clase Ejecutiva");
		listaPrecios.add(400000);
		listaClases.add("Clase Premium Economy");
		listaPrecios.add(415000);
		listaClases.add("Clase Turista");
		listaPrecios.add(250000);

		datos = "";
		for (int i = 0; i < 4; i++) {
			if (i == 3) {
				datos += "(\"" + listaClases.get(i) + "\", \"" + listaPrecios.get(i) + "\");";
			} else {
				datos += "(\"" + listaClases.get(i) + "\", \"" + listaPrecios.get(i) + "\"),\n";
			}
		}
		 insertData("Clase","Nombre, Precio", datos, c);
	
// ----------------------------------------------------------------------------------------------------------------------------------------
		//Carga datos para proforma
	
		List<Integer> idTalleres = consultInteger(c, "SELECT IdTaller FROM Taller", "IdTaller");
		List<String> listaDanos = readList("..\\SqliteConection\\data\\ciudades.txt");	
		List<String> listaRepuestos = new ArrayList<String>();
		listaRepuestos.add("Ala");
		listaRepuestos.add("Fuselaje");
		listaRepuestos.add("Superficie de Control");
		listaRepuestos.add("Alerones");
		listaRepuestos.add("Flaps");
		listaRepuestos.add("Spoilers");
		listaRepuestos.add("Slats");
		listaRepuestos.add("Estabilizador Horizontal");
		listaRepuestos.add("Estabilizador Vertical");
		listaRepuestos.add("Tren de Aterrizaje");
		listaRepuestos.add("Instrumento de Control");
		
		int lenRepuestos = listaRepuestos.size();
		int lenDanos = listaDanos.size();
		int lenIdTalleres = idTalleres.size();
		Date fechaTemp = new java.util.Date();
		String fechaLlegadaTemp;
		//Inserta datos de proforma
		datos = "";
		int pos = 0;
		for (int i = 0; i < 20 ; i++) {
			fechaLlegadaTemp = getDate(fechaTemp,-1* rand.nextInt(72));
			pos = rand.nextInt(lenRepuestos);
			if(i == 19) {
				datos += "(\""+ listaRepuestos.get(pos) +"\", " + (rand.nextInt(2000000)+100000) + ", \""
						+ fechaLlegadaTemp + "\", \""+ getDate(fechaTemp, rand.nextInt(130) + 10) + "\""
								+ ",\"" + listaDanos.get(rand.nextInt(lenDanos)) + "\", \"" + idTalleres.get(rand.nextInt(lenIdTalleres)) + "\");";
			}else {
				datos += "(\""+ listaRepuestos.get(pos) +"\", " + (rand.nextInt(2000000)+100000) + ", \""
						+ fechaLlegadaTemp + "\", \""+ getDate(fechaTemp, rand.nextInt(130) + 10) + "\" ,"
								+ "\"" + listaDanos.get(rand.nextInt(lenDanos)) + "\", \"" + idTalleres.get(rand.nextInt(lenIdTalleres)) + "\"),\n";
			}
		}
		  insertData("Proforma", "Respuestos, Costo, FechaLLegada, FechaSalida, Daños, IdTaller", datos, c);

// ----------------------------------------------------------------------------------------------------------------------------------------
		// Carga datos para aerolinea
		List<String> listaAerolineas = readList("..\\SqliteConection\\data\\aerolineas.txt");
		int lenAerolineas = listaAerolineas.size();

		// Inserta datos de las aerolineas
		listaCodigos = generateCode("AIR", 30);
		datos = "";
		for (int i = 0; i < 30; i++) {
			if (i == 29) {
				datos += "(\"" + listaCodigos.get(i) + "\", \"" + listaAerolineas.get(i) + "\", "
						+ (rand.nextInt(10) + 10) + ");";
			} else {
				datos += "(\"" + listaCodigos.get(i) + "\", \"" + listaAerolineas.get(i) + "\", "
						+ (rand.nextInt(10) + 10) + "),\n";
			}
		}

		 insertData("Aerolinea", "Codigo, Nombre, CantidadEmpleados", datos, c);
		
// ----------------------------------------------------------------------------------------------------------------------------------------
		// Carga datos para Avion
		List<String> listaModeloAvion = readList("..\\SqliteConection\\data\\modelos_aviones.txt");
		List<String> listaFabAviones = readList("..\\SqliteConection\\data\\fab_aviones.txt");
		int lenModeloAvion = listaModeloAvion.size();
		int lenFabAviones = listaFabAviones.size();
		List<String> listaEstadoAvion = new ArrayList<String>();
		listaEstadoAvion.add("Activo");
		listaEstadoAvion.add("En reparación");
		listaEstadoAvion.add("Inactivo");
		
		List<Integer> idFabricantes = consultInteger(c, "SELECT IdFabricante FROM Fabricante", "IdFabricante");
		List<Integer> idProformas = consultInteger(c, "SELECT IdProforma FROM Proforma", "IdProforma");
		List<Integer> idClases = consultInteger(c, "SELECT IdClase FROM Clase", "IdClase");
		List<Integer> idAerolinea = consultInteger(c, "SELECT idAerolinea FROM Aerolinea", "idAerolinea");
		int lenIdFabricantes = idFabricantes.size();
		int lenIdProformas = idProformas.size();
		int lenIdClases = idClases.size();
		int lenIdAerolinea = idAerolinea.size();

		// Inserta datos de los Avion
		listaCodigos = generateCode("AV", 60);
		datos = "";
		for (int i = 0; i < 60; i++) {
			if (i == 59) {
				datos += "(\"" + listaCodigos.get(i) + "\", \"" + listaModeloAvion.get(rand.nextInt(lenModeloAvion))
						+ "\", " + (rand.nextInt(350) + 150) + ", " + rand.nextInt(60) + ", \""
							+ listaEstadoAvion.get(rand.nextInt(3)) + "\", "
							+ idFabricantes.get(rand.nextInt(lenIdFabricantes)) + ", "
							+ idClases.get(rand.nextInt(lenIdClases)) + ", "
							+ idAerolinea.get(rand.nextInt(lenIdAerolinea)) + ", "
							+ idProformas.get(rand.nextInt(lenIdProformas))
							+ ");";
			} else {
				datos += "(\"" + listaCodigos.get(i) + "\", \"" + listaModeloAvion.get(rand.nextInt(lenModeloAvion))
					+ "\", " + (rand.nextInt(350) + 150) + ", " + rand.nextInt(60) + ", \""
					+ listaEstadoAvion.get(rand.nextInt(3)) + "\", "
					+ idFabricantes.get(rand.nextInt(lenIdFabricantes)) + ", "
					+ idClases.get(rand.nextInt(lenIdClases)) + ", "
					+ idAerolinea.get(rand.nextInt(lenIdAerolinea)) + ", "
					+ idProformas.get(rand.nextInt(lenIdProformas))
					+ "),\n";
			}
		}
		insertData("Avion", "CodigoAvion, ModeloAvion, CapacidadTripulacion, CapacidadItinerario, Estado, "
				+ "IdFabricante, IdClaseViaje, IdAerolinea, IdProforma", datos, c);
// ----------------------------------------------------------------------------------------------------------------------------------------
		//Carga datos para Vuelo
		
		List<Integer> idAviones = consultInteger (c,"SELECT IdAvion FROM Avion", "IdAvion");
		Date fechaActual = new Date();
		Date fechaSalida1;
		Date fechaLlegada1;
		String estadoVuelo;
		String fechaSalidaVuelo, fechaLlegadaVuelo;
        String strDateFormat = "dd-MM-yyyy HH:mm:ss";
        SimpleDateFormat formateador = new SimpleDateFormat(strDateFormat);
        int lenIdAviones = idAviones.size();
		//Inserta datos de los Vuelos
		datos = "";
		for (int i = 0; i < 800 ; i++) {
			fechaSalidaVuelo = getDate(fechaActual, rand.nextInt(5) + -10 * rand.nextInt(3));
			fechaSalida1 = formateador.parse(fechaSalidaVuelo);
		    fechaLlegadaVuelo = getDate(fechaSalida1, rand.nextInt(20));
		    fechaLlegada1 = formateador.parse(fechaLlegadaVuelo);
		    if (fechaSalida1.compareTo(fechaActual) < 1){
		    	if(fechaLlegada1.compareTo(fechaActual) >= 1) {
		    		estadoVuelo = "Llego al destino";
		    	}else {
		    		estadoVuelo = "En Proceso";
		    	}
		    }else {
		    	estadoVuelo = "Activo";
		    }
			if(i == 799) {
				datos += "("+ i +", \"" + listaCiudades.get(rand.nextInt(lenCiudades)) + "\", \""
						+ listaCiudades.get(rand.nextInt(lenCiudades)) + "\", \""+ fechaSalidaVuelo + "\", \""+ 
						fechaLlegadaVuelo +"\", \""+ estadoVuelo + "\", "+ idAviones.get(rand.nextInt(lenIdAviones)) + ");";
			}else {
				datos += "("+ i +", \"" + listaCiudades.get(rand.nextInt(lenCiudades)) + "\", \""
						+ listaCiudades.get(rand.nextInt(lenCiudades)) + "\", \""+ fechaSalidaVuelo + "\", \""+ 
						fechaLlegadaVuelo +"\", \""+ estadoVuelo + "\", "+ idAviones.get(rand.nextInt(lenIdAviones)) + "),\n";
			}
		}
//		System.out.println(datos);
		insertData("Vuelo", "NumeroVuelo, Destino, Origen, FechaSalida, FechaLlegada, EstadoVuelo, IdAvion", datos, c);
				
// ----------------------------------------------------------------------------------------------------------------------------------------
		//Pasajero
		List<Integer> idPasaporte = consultInteger(c, "SELECT IdPasaporte FROM Pasaporte;", "IdPasaporte");
		List<Integer> idVuelos = consultInteger(c, "SELECT IdVuelo FROM Vuelo;", "IdVuelo");
		int lenVuelos = idVuelos.size();
		int lenPasaporte = idPasaporte.size();
		
		// Inserta datos del pasajero
		listaCodigos = generateCode("PAS", 200);
		datos = "";
		for (int i = 0; i < 200; i++) {
			if (i == 199) {
				datos += "("+ (rand.nextInt(5) + 1) + ", \"" + listaCodigos.get(i) + "\", \"" 
						+ listaNombres.get(rand.nextInt(lenNombres)) + "\", \""
						+ listaApellidos.get(rand.nextInt(lenApellidos)) + "\", \""
						+ listaApellidos.get(rand.nextInt(lenApellidos)) + "\", \"" 
						+ listaNumeros.get(i)+ "\", "
						+ idVuelos.get(rand.nextInt(lenVuelos)) + ", "
						+ idPasaporte.get(rand.nextInt(lenPasaporte)) +");";
			} else {
				datos += "("+ (rand.nextInt(5) + 1) + ", \"" + listaCodigos.get(i) + "\", \"" 
						+ listaNombres.get(rand.nextInt(lenNombres)) + "\", \""
						+ listaApellidos.get(rand.nextInt(lenApellidos)) + "\", \""
						+ listaApellidos.get(rand.nextInt(lenApellidos)) + "\", \"" 
						+ listaNumeros.get(i)+ "\", "
						+ idVuelos.get(rand.nextInt(lenVuelos)) + ", "
						+ idPasaporte.get(rand.nextInt(lenPasaporte)) +"), \n";
			}
		}
		insertData("Pasajero", "CantEquipaje, Codigo, Nombre, Apellido1, Apellido2, Telefono, IdVuelo, IdPasaporte", datos, c);
		
// ----------------------------------------------------------------------------------------------------------------------------------------
		// Inserta datos del equipaje
		datos = "";
		List<Integer> idPasajeros = consultInteger(c, "SELECT IdPasajero FROM Pasajero", "IdPasajero");
		int lenIdPasajeros = idPasajeros.size();
		
		for (int i = 0; i < 350; i++) {
			if (i == 349) {
				datos += "(\"" + (rand.nextInt(150) + 1) + "\", " + idPasajeros.get(rand.nextInt(lenIdPasajeros)) + ");";
			} else {
				datos += "(\"" + (rand.nextInt(150) + 1) + "\", " + idPasajeros.get(rand.nextInt(lenIdPasajeros)) + "),\n";
			}
		}
//		System.out.println(datos);
		insertData("Equipaje","Peso, IdPasajero", datos, c);
				
// ----------------------------------------------------------------------------------------------------------------------------------------
		// Carga datos para Controlador
		List<String> listaPosiciones = readList("..\\SqliteConection\\data\\posiciones_aviones.txt");
		int lenListaPosiciones = listaPosiciones.size();
		
		List<Integer> idVuelo = consultInteger(c, "SELECT IdVuelo FROM Vuelo;", "IdVuelo");
		List<Integer> idAvion = consultInteger(c, "SELECT IdAvion FROM Avion;", "IdAvion");
		int lenIdVuelo = idVuelo.size();
		int lenIdAvion = idAvion.size();

		datos = "";
		listaCodigos = generateCode("CONT", 20);
		List<String> comunicacion = generateCode("COM", 20);
		for (int i = 0; i < 20; i++) {
			if (i == 19) {
				datos += "(\"" + comunicacion.get(i) + "\", "
						+ idVuelo.get(rand.nextInt(lenIdVuelo)) + ", \""
						+ listaPosiciones.get(rand.nextInt(lenListaPosiciones)) + "\");";
			} else {
				datos += "(\"" + comunicacion.get(i) + "\", "
						+ idVuelo.get(rand.nextInt(lenIdVuelo)) + ", \""
						+ listaPosiciones.get(rand.nextInt(lenListaPosiciones)) + "\"),\n";
			}
		}
		 insertData("Controlador", "CodigoComunicacion, IdVuelo, Posicion", datos, c);

	}
}

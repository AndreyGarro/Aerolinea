-- -- Creación de las tablas

-- CREATE TABLE IF NOT EXISTS Aerolinea
-- (
--     IdAerolinea INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
--     Codigo TEXT NOT NULL,
--     CantidadEmpleados INTEGER NOT NULL
-- );

-- CREATE TABLE IF NOT EXISTS Aeropuerto
-- (
--     IdAeropuerto INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
--     Nombre TEXT NOT NULL,
--     NumeroDeTelefono TEXT NOT NULL,
--     Localizacion TEXT NOT NULL,
--     Horario TEXT NOT NULL,
--     Codigo TEXT NOT NULL
-- );

-- CREATE TABLE IF NOT EXISTS Empleado
-- (
--     IdEmpleado INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
--     Codigo TEXT NOT NULL,
--     Nombre TEXT NOT NULL,
--     PrimerApellido TEXT NOT NULL,
--     SegundoApellido TEXT NOT NULL,
--     Cedula TEXT NOT NULL,
--     CuentaBanco TEXT NOT NULL,
--     Horario TEXT NOT NULL,
--     Direccion TEXT NOT NULL
-- );

-- CREATE TABLE IF NOT EXISTS PuestoAeropuerto
-- (
--     IdPuestoAeropuerto INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
--     PuestoTrabajo TEXT NOT NULL,
--     Salario INTEGER NOT NULL
-- );

-- CREATE TABLE IF NOT EXISTS PuestoAerolinea 
-- (
--     IdPuestoAerolinea INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
--     PuestoTrabajo TEXT NOT NULL,
--     Salario INTEGER NOT NULL
-- );

-- CREATE TABLE IF NOT EXISTS Clase
-- (
--     IdClase INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
--     Nombre TEXT NOT NULL,
--     Precio INTEGER NOT NULL
-- );

-- CREATE TABLE IF NOT EXISTS Proforma
-- (
--     IdProforma INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
--     Respuestos TEXT NOT NULL,
--     Costo INTEGER NOT NULL,
--     FechaLlegada DATE NOT NULL,
--     FechaSalida DATE NOT NULL,
--     Daños TEXT NOT NULL    
-- );

-- CREATE TABLE IF NOT EXISTS Avion
-- (
--     IdAvion INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
--     CodigoAvion TEXT NOT NULL,
--     ModeloAvion TEXT NOT NULL,
--     CapacidadTripulacion INTEGER NOT NULL,
--     CapacidadItinerario INTEGER NOT NULL,
--     Estado TEXT NOT NULL,
--     Fabricante TEXT NOT NULL,
--     IdClaseViaje INTEGER NOT NULL,
--     IdAeroLinea INTEGER NOT NULL,
--     IdProforma INTEGER NULL,
--     FOREIGN KEY (IdClaseViaje) REFERENCES Clase(IdClaseViaje),
--     FOREIGN KEY (IdAeroLinea) REFERENCES AeroLinea(IdAeroLinea),
--     FOREIGN KEY (IdProforma) REFERENCES Proforma(IdProforma)
-- );

-- CREATE TABLE IF NOT EXISTS Vuelo
-- (
--     IdVuelo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
--     NumeroVuelo TEXT NOT NULL,
--     Destino TEXT NOT NULL,
--     Origen TEXT NOT NULL,
--     FechaSalida DATE NOT NULL,
--     HoraSalida DATE NOT NULL,
--     FechaLLegada DATE NOT NULL,
--     HoraLLegada DATE NOT NULL,
--     EstadoVuelo TEXT NOT NULL
-- );
-- CREATE TABLE IF NOT EXISTS Pasaporte
-- (
--     IdPasaporte INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
--     Tipo TEXT NOT NULL,
--     Nacionalidad TEXT NOT NULL,
--     Numero TEXT NOT NULL,
--     FechaExpiracion DATE NOT NULL
-- );


-- CREATE TABLE IF NOT EXISTS Pasajero
-- (
--     IdPasajero INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
--     CantEquipaje INTEGER NOT NULL,
--     Codigo TEXT NOT NULL,
--     Nombre TEXT NOT NULL,
--     Apellido1 TEXT NOT NULL,
--     Apellido2 TEXT NOT NULL,
--     Telefono TEXT NOT NULL,
--     IdVuelo INTEGER NOT NULL,
--     IdPasaporte INTEGER NOT NULL,
--     FOREIGN KEY (IdVuelo) REFERENCES Vuelo(IdVuelo),
--     FOREIGN KEY (IdPasaporte) REFERENCES Pasaporte(IdPasaporte)
-- );

-- CREATE TABLE IF NOT EXISTS Equipaje
-- (
--     IdEquipaje INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
--     Peso INTEGER NOT NULL,
--     IdPasajero INTEGER NOT NULL,
--     FOREIGN KEY (IdPasajero) REFERENCES Pasajero(IdPasajero)
-- );

-- CREATE TABLE IF NOT EXISTS Controlador
-- (
--     IdControlador INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
--     CodigoComunicacion TEXT NOT NULL,
--     HoraLlegada DATE NOT NULL,
--     PosicionAVion TEXT NOT NULL,
--     IdVuelo INTEGER NOT NULL,
--     IdAvion INTEGER NOT NULL,
--     FOREIGN KEY (IdVuelo) REFERENCES Vuelo(IdVuelo),
--     FOREIGN KEY (IdAvion) REFERENCES Avion(IdAvion)
-- );

-- CREATE TABLE IF NOT EXISTS AerolineaXEmpleado 
-- (
--     IdAeroLinea INTEGER NOT NULL,
--     IdEmpleado INTEGER NOT NULL,
--     IdPuestoAerolinea INTEGER NOT NULL,
--     FOREIGN KEY (IdAeroLinea) REFERENCES Aerolinea(IdAeroLinea),
--     FOREIGN KEY (IdEmpleado) REFERENCES Empleado(IdEmpleado),
--     FOREIGN KEY (IdPuestoAerolinea) REFERENCES PuestoAerolinea(IdPuestoAerolinea)
-- );

-- CREATE TABLE IF NOT EXISTS VueloXAvion
-- (
--     IdVuelo INTEGER NOT NULL,
--     IdAvion INTEGER NOT NULL,
--     FOREIGN KEY (IdVuelo) REFERENCES Vuelo(IdVuelo),
--     FOREIGN KEY (IdAvion) REFERENCES Avion(IdAvion)
-- );

-- CREATE TABLE IF NOT EXISTS AeropuertoXEmpleado
-- (
--     IdAeropuerto INTEGER NOT NULL,
--     IdEmpleado INTEGER NOT NULL,
--     IdPuestoAeropuerto INTEGER NOT NULL,
--     FOREIGN KEY (IdPuestoAeropuerto) REFERENCES PuestoAeropuerto(IdPuestoAeropuerto),
--     FOREIGN KEY (IdAeropuerto) REFERENCES Aeropuerto(IdAeropuerto),
--     FOREIGN KEY (IdEmpleado) REFERENCES Empleado(IdEmpleado)
-- );


-- DROP TABLE AerolineaXEmpleado;
-- DROP TABLE AeropuertoXEmpleado;
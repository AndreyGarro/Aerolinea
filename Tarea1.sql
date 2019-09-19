-- Creación de las tablas

CREATE TABLE IF NOT EXISTS Aerolinea
(
    IdAerolinea INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    Nombre Text NOT NULL,
    Codigo TEXT NOT NULL,
    CantidadEmpleados INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS Horario
(
    IdHorario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    Lunes TEXT NOT NULL,
    Martes TEXT NOT NULL,
    Miercoles TEXT NOT NULL,
    Jueves TEXT NOT NULL,
    Viernes TEXT NOT NULL,
    Sabado TEXT NOT NULL,
    Domingo TEXT NOT NULL,
    HoraEntrada Date NOT NULL,
    HoraSalida Date NOT NULL
);

CREATE TABLE IF NOT EXISTS Aeropuerto
(
    IdAeropuerto INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    Nombre TEXT NOT NULL,
    NumeroDeTelefono TEXT NOT NULL,
    Localizacion TEXT NOT NULL,
    IdHorario INTEGER NOT NULL,
    Codigo TEXT NOT NULL,
    FOREIGN KEY (IdHorario) REFERENCES Horario(IdHorario)
);

CREATE TABLE IF NOT EXISTS Empleado
(
    IdEmpleado INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    Codigo TEXT NOT NULL,
    Nombre TEXT NOT NULL,
    PrimerApellido TEXT NOT NULL,
    SegundoApellido TEXT NOT NULL,
    Cedula TEXT NOT NULL,
    CuentaBanco TEXT NOT NULL,
    IdHorario INTEGER NOT NULL,
    Direccion TEXT NOT NULL,
    FOREIGN KEY (IdHorario) REFERENCES Horario(IdHorario)
);
CREATE TABLE IF NOT EXISTS PuestoAeropuerto
(
    IdPuestoAeropuerto INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    PuestoTrabajo TEXT NOT NULL,
    Salario INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS PuestoAerolinea 
(
    IdPuestoAerolinea INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    PuestoTrabajo TEXT NOT NULL,
    Salario INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS Clase
(
    IdClase INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    Nombre TEXT NOT NULL,
    Precio INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS Proforma
(
    IdProforma INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    Respuestos TEXT NOT NULL,
    Costo INTEGER NOT NULL,
    FechaLlegada DATE NOT NULL,
    FechaSalida DATE NOT NULL,
    Daños TEXT NOT NULL,
    IdTaller INTEGER NOT NULL,
    FOREIGN KEY (IdTaller) REFERENCES Taller(IdTaller)
);

CREATE TABLE IF NOT EXISTS Avion
(
    IdAvion INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    CodigoAvion TEXT NOT NULL,
    ModeloAvion TEXT NOT NULL,
    CapacidadTripulacion INTEGER NOT NULL,
    CapacidadItinerario INTEGER NOT NULL,
    Estado TEXT NOT NULL,
    IdFabricante INTEGER NOT NULL,
    IdClaseViaje INTEGER NOT NULL,
    IdAeroLinea INTEGER NOT NULL,
    IdProforma INTEGER NULL,
    FOREIGN KEY (IdClaseViaje) REFERENCES Clase(IdClaseViaje),
    FOREIGN KEY (IdAeroLinea) REFERENCES AeroLinea(IdAeroLinea),
    FOREIGN KEY (IdProforma) REFERENCES Proforma(IdProforma),
    FOREIGN KEY (IdFabricante) REFERENCES Fabricante(IdFabricante)
);

CREATE TABLE IF NOT EXISTS Fabricante
(
    IdFabricante INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    Nombre TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS Vuelo
(
    IdVuelo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    NumeroVuelo TEXT NOT NULL,
    Destino TEXT NOT NULL,
    Origen TEXT NOT NULL,
    FechaSalida DATE NOT NULL,
    FechaLLegada DATE NOT NULL,
    EstadoVuelo TEXT NOT NULL,
    IdAvion INTEGER NOT NULL,
    FOREIGN KEY (IdAvion) REFERENCES Avion(IdAvion)
);

CREATE TABLE IF NOT EXISTS Pasaporte
(
    IdPasaporte INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    Tipo TEXT NOT NULL,
    Nacionalidad TEXT NOT NULL,
    Numero TEXT NOT NULL,
    FechaExpiracion DATE NOT NULL
);


CREATE TABLE IF NOT EXISTS Pasajero
(
    IdPasajero INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    CantEquipaje INTEGER NOT NULL,
    Codigo TEXT NOT NULL,
    Nombre TEXT NOT NULL,
    Apellido1 TEXT NOT NULL,
    Apellido2 TEXT NOT NULL,
    Telefono TEXT NOT NULL,
    IdVuelo INTEGER NOT NULL,
    IdPasaporte INTEGER NOT NULL,
    FOREIGN KEY (IdVuelo) REFERENCES Vuelo(IdVuelo),
    FOREIGN KEY (IdPasaporte) REFERENCES Pasaporte(IdPasaporte)

);
CREATE TABLE IF NOT EXISTS Equipaje
(
    IdEquipaje INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    Peso INTEGER NOT NULL,
    IdPasajero INTEGER NOT NULL,
    FOREIGN KEY (IdPasajero) REFERENCES Pasajero(IdPasajero)

);
CREATE TABLE IF NOT EXISTS Controlador
(
    IdControlador INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    CodigoComunicacion TEXT NOT NULL,
    IdVuelo INTEGER NOT NULL,
    Posicion TEXT NOT NULL,
    FOREIGN KEY (IdVuelo) REFERENCES Vuelo(IdVuelo)
);

CREATE TABLE IF NOT EXISTS Bodega
(
    IdBodega INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    Nombre NOT NULL
);

CREATE TABLE IF NOT EXISTS Taller
(
    IdTaller INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    Nombre NOT NULL
);

CREATE TABLE IF NOT EXISTS AerolineaXEmpleado 
(
    IdAeroLinea INTEGER NOT NULL,
    IdEmpleado INTEGER NOT NULL,
    IdPuestoAerolinea INTEGER NOT NULL,
    FOREIGN KEY (IdAeroLinea) REFERENCES Aerolinea(IdAeroLinea),
    FOREIGN KEY (IdEmpleado) REFERENCES Empleado(IdEmpleado),
    FOREIGN KEY (IdPuestoAerolinea) REFERENCES PuestoAerolinea(IdPuestoAerolinea)
);

CREATE TABLE IF NOT EXISTS VueloXAvion
(
    IdVuelo INTEGER NOT NULL,
    IdAvion INTEGER NOT NULL,
    FOREIGN KEY (IdVuelo) REFERENCES Vuelo(IdVuelo),
    FOREIGN KEY (IdAvion) REFERENCES Avion(IdAvion)
);

CREATE TABLE IF NOT EXISTS AeropuertoXEmpleado
(
    IdAeropuerto INTEGER NOT NULL,
    IdEmpleado INTEGER NOT NULL,
    IdPuestoAeropuerto INTEGER NOT NULL,
    FOREIGN KEY (IdPuestoAeropuerto) REFERENCES PuestoAeropuerto(IdPuestoAeropuerto),
    FOREIGN KEY (IdAeropuerto) REFERENCES Aeropuerto(IdAeropuerto),
    FOREIGN KEY (IdEmpleado) REFERENCES Empleado(IdEmpleado)
);

CREATE TABLE IF NOT EXISTS AeropuertoXAerolinea
(
    IdAeropuerto INTEGER NOT NULL,
    IdAeroLinea INTERGER NOT NULL,
    FOREIGN KEY (IdAeropuerto) REFERENCES Aeropuerto(IdAeropuerto),
    FOREIGN KEY (IdAeroLinea) REFERENCES Aerolinea(IdAeroLinea)
);

CREATE TABLE IF NOT EXISTS  ClaseXAvion
(
    IdAvion INTEGER NOT NULL,
    IdClase INTERGER NOT NULL,
    FOREIGN KEY (IdAvion) REFERENCES Avion(IdAvion),
    FOREIGN KEY (IdClase) REFERENCES Clase(IdClase)
);

CREATE TABLE IF NOT EXISTS  AvionXProforma
(
    IdAvion INTEGER NOT NULL,
    IdProforma INTERGER NOT NULL,
    FOREIGN KEY (IdAvion) REFERENCES Avion(IdAvion),
    FOREIGN KEY (IdProforma) REFERENCES Proforma(IdProforma)
);

CREATE TABLE IF NOT EXISTS  BodegaXAvion
(
    IdAvion INTEGER NOT NULL,
    IdBodega INTERGER NOT NULL,
    FOREIGN KEY (IdAvion) REFERENCES Avion(IdAvion),
    FOREIGN KEY (IdBodega) REFERENCES Bodega(IdBodega)
);


-- DROP TABLE Aerolinea;
-- DROP TABLE Horario;
-- DROP TABLE Aeropuerto;
-- DROP TABLE Empleado;
-- DROP TABLE PuestoAeropuerto;
-- DROP TABLE PuestoAerolinea;
-- DROP TABLE Clase;
-- DROP TABLE Proforma;
-- DROP TABLE Avion;
-- DROP TABLE Fabricante;
-- DROP TABLE Vuelo;
-- DROP TABLE Pasaporte;
-- DROP TABLE Pasajero;
-- DROP TABLE Equipaje;
-- DROP TABLE Controlador;
-- DROP TABLE AerolineaXEmpleado;
-- DROP TABLE AeropuertoXEmpleado;
-- DROP TABLE AeropuertoXAerolinea;
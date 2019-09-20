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
    IdAvion INTEGER NOT NULL,
    FOREIGN KEY (IdAvion) REFERENCES Avion(IdAvion),
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
    FOREIGN KEY (IdClaseViaje) REFERENCES Clase(IdClaseViaje),
    FOREIGN KEY (IdAeroLinea) REFERENCES AeroLinea(IdAeroLinea),
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

CREATE TABLE IF NOT EXISTS  BodegaXAvion
(
    IdAvion INTEGER NOT NULL,
    IdBodega INTERGER NOT NULL,
    FOREIGN KEY (IdAvion) REFERENCES Avion(IdAvion),
    FOREIGN KEY (IdBodega) REFERENCES Bodega(IdBodega)
);

-- Consulta 1: TOP 10 de aerolÃ­neas con mayor cantidad de empleados.

SELECT Nombre, CantidadEmpleados 
FROM Aerolinea A
ORDER BY CantidadEmpleados DESC
LIMIT 10;

-- Consulta 2: TOP 10 de aeropuertos con mÃ¡s AerolÃ­neas.
SELECT A.Nombre, A.IdAeropuerto, COUNT(Ae.IdAeroLinea)
FROM Aeropuerto A
INNER JOIN AeropuertoXAerolinea Ae ON A.IdAeropuerto = Ae.IdAeroPuerto
GROUP BY A.IdAeropuerto
ORDER BY COUNT (Ae.IdAerolinea) DESC
LIMIT 10;

-- Consulta 3: Toda la información de un empleado de la aerolínea y del aeropuerto con
-- el sueldo más alto.
    SELECT * FROM (
    SELECT E.NOMBRE, PA.Salario
    FROM Empleado E
        INNER JOIN AeropuertoXEmpleado AE ON AE.IdEmpleado = E.IdEmpleado
        INNER JOIN PuestoAeropuerto PA ON AE.IdPuestoAeropuerto = PA.IdPuestoAeropuerto
        ORDER BY PA.Salario DESC LIMIT 1)
UNION
    SELECT * FROM
    (SELECT E.NOMBRE, PA.Salario
    FROM Empleado E
        INNER JOIN AerolineaXEmpleado AE ON AE.IdEmpleado = E.IdEmpleado
        INNER JOIN PuestoAerolinea PA ON AE.IdPuestoAerolinea = PA.IdPuestoAerolinea
        ORDER BY PA.Salario DESC LIMIT 1);


-- Consulta 4: Promedio de salario para los aeropuertos con mayor número de
-- empleados.
SELECT A.Nombre, A.IdAeropuerto, COUNT(AE.IdEmpleado) AS CantEmpleados, 
        AVG(PA.Salario) AS Salario
FROM Aeropuerto A
INNER JOIN AeropuertoXEmpleado AE ON A.IdAeropuerto = AE.IdAeropuerto
INNER JOIN PuestoAeropuerto PA ON AE.IdPuestoAeropuerto = PA.IdPuestoAeropuerto
GROUP by A.IdAeropuerto
ORDER BY COUNT(AE.IdEmpleado) DESC;

-- Consulta 5: Cantidad de aviones en una aerolínea que están en estado de reparación.
SELECT AE.Nombre, COUNT(IdAvion) AS CantidadEnReparación
FROM Avion A
INNER JOIN Aerolinea AE ON A.IdAerolinea = AE.IdAerolinea
WHERE A.Estado = 'En reparación'
GROUP BY (AE.Nombre)

-- Consulta 6: Costo de reparación, modelo, fabricante y el código de un avión para una
-- aerolínea perteneciente a un aeropuerto específico.
SELECT P.Costo, A.ModeloAvion, F.Nombre, A.CodigoAvion, 
        AE.Nombre AS NombreAeropuerto, AE1.Nombre AS NombreAerolinea
FROM Avion A
INNER JOIN Proforma P ON A.IdAvion = P.IdAvion
INNER JOIN Fabricante F ON A.IdFabricante = F.IdFabricante
INNER JOIN Aerolinea AE1 ON A.IdAeroLinea = AE1.IdAeroLinea
INNER JOIN AeropuertoXAerolinea AxE ON AE1.IdAerolinea = AxE.IdAeroLinea
INNER JOIN Aeropuerto AE ON AxE.IdAeropuerto = AE.IdAeropuerto;

-- Consulta 7: Cantidad de aviones activos en un aeropuerto.
SELECT AE.Nombre, COUNT(A.IdAvion) AS CantidadAviones
FROM Avion A
INNER JOIN AeropuertoXAerolinea AA ON A.IdAerolinea = AA.IdAerolinea
INNER JOIN Aeropuerto AE ON AA.IdAeropuerto = AE.IdAeropuerto
GROUP BY (AE.Nombre);

-- Consulta 8: Promedio de costo de reparación de los aviones para un aeropuerto
-- específico.

SELECT AE.Nombre, AVG(P.Costo) AS PromedioDeCostos
FROM Aeropuerto AE
INNER JOIN AeropuertoXAerolinea AA ON AE.IdAeropuerto = AA.IdAeropuerto
INNER JOIN Aerolinea AE1 ON AA.IdAeroLinea = AE1.IdAeroLinea
INNER JOIN Avion A ON A.IdAeroLinea = AE1.IdAeroLinea
INNER JOIN Proforma P ON A.IdAvion = P.IdAvion
GROUP BY (AE.Nombre);

-- Consulta 9: Cantidad de aviones inactivos dentro de una bodega.
SELECT B.Nombre, COUNT(BA.IdAvion) AS CantidadAviones
FROM Avion A
INNER JOIN BodegaXAvion BA ON BA.IdAvion = A.IdAvion
INNER JOIN Bodega B ON B.IdBodega = BA.IdBodega
WHERE A.Estado = "Inactivo"
GROUP BY (B.Nombre);

-- Consulta 10: Nombre de los fabricantes con la mayor cantidad de modelos.
SELECT F.Nombre, COUNT(A.IdAvion) AS CantidadAviones
FROM Avion A
INNER JOIN Fabricante F ON A.IdFabricante = F.IdFabricante
GROUP BY (F.Nombre);

-- Consulta 11: Cantidad de aerolíneas que contienen la letra “A” en el nombre. De este
-- resultado además deben de mostrar cuáles tienen más vuelos activos.
SELECT AE1.Nombre, COUNT(A.IdAvion) AS CantidadAviones
FROM Aerolinea AE1
INNER JOIN Avion A ON A.IdAeroLinea = AE1.IdAeroLinea
INNER JOIN Vuelo V ON A.IdAvion = V.IdAvion
WHERE instr(AE1.Nombre, 'A') > 1 
OR instr(AE1.Nombre, 'a') > 1
AND V.EstadoVuelo = "Activo"
GROUP BY (AE1.Nombre)
ORDER BY COUNT(A.IdAvion) DESC;

-- Consulta 12: Intervalo de horas con la mayor llegada de aviones para un aeropuerto.
SELECT TIME(V.FechaLLegada), COUNT(V.IdVuelo)
FROM Aeropuerto AE
INNER JOIN AeropuertoXAerolinea AA ON AE.IdAeropuerto = AA.IdAeropuerto 
INNER JOIN Aerolinea AE1 ON AA.IdAeroLinea = AE1.IdAerolinea
INNER JOIN Avion A ON AE1.IdAvion = A.IdAvion
INNER JOIN Vuelo V ON A.IdAvion = V.IdAvion
GROUP BY (TIME(V.FechaLLegada))
ORDER BY COUNT(V.IdVuelo);

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


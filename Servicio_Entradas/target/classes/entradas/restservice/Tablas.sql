
CREATE TABLE eventos(
    idEvento SERIAL PRIMARY KEY,
    Artista VARCHAR(255) NOT NULL,
    Fecha VARCHAR(255) NOT NULL,
    Lugar VARCHAR(255) NOT NULL,
    Ciudad VARCHAR(255) NOT NULL,
    numentradasdisponibles INTEGER NOT NULL,
    tipoEstadio INTEGER NOT NULL 
);

CREATE TABLE Compras(
    idCompra SERIAL PRIMARY KEY,
    idEvento INTEGER REFERENCES Eventos(idEvento),
    nombrecomprador VARCHAR(255) NOT NULL,
    dnicomprador VARCHAR(9) NOT NULL,
    cantidadentradascompradas INTEGER NOT NULL,
    Grada INTEGER NOT NULL
);

\copy Eventos FROM eventos.txt

DROP
DATABASE IF EXISTS apiHoteles;
CREATE
DATABASE apiHoteles;
USE
apiHoteles;

CREATE TABLE Hotel
(
    id          int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre      varchar(50)  DEFAULT NULL,
    descripcion varchar(1000) DEFAULT NULL,
    categoria   int          DEFAULT 3,
    piscina     boolean      DEFAULT FALSE,
    localidad   varchar(50)  DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1;

CREATE TABLE Habitacion
(
    id       int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tamanno  int     DEFAULT 1,
    precio   double  DEFAULT 100.00,
    desayuno boolean DEFAULT FALSE,
    ocupada  boolean DEFAULT FALSE,
    hotel    int NOT NULL,
    FOREIGN KEY (hotel) REFERENCES Hotel(id)

) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1;

INSERT INTO Hotel (nombre, descripcion, categoria, piscina, localidad)
VALUES ('Castilla Vieja',
        'El Hotel Castilla Vieja, de cuatro estrellas, está situado en pleno centro neurálgico de la hermosa y sosegada ciudad de Palencia. Nos encontramos a 200 metros del Teatro Principal, a 2 minutos a pie de la Plaza Mayor y a 4 minutos de a la zona de compras por excelencia, la Calle Mayor.',
        4, FALSE, 'Palencia'),
       ('Rey Sancho',
        'El Hotel Rey Sancho es ideal para quienes buscan una estancia cómoda en Palencia. Bien sea que te alojes con nosotros por turismo, trabajo, o para organizar eventos especiales, te ofrecemos el mejor trato y la mejor ubicación. Estamos situados junto al campo de golf Isla Dos Aguas, a tan solo 150 metros de la Calle Mayor, un punto de partida ideal para explorar la ciudad y sus alrededores.',
        4, TRUE, 'Palencia'),
       ('Sercotel',
        'Qué maravillosa escapada, Valladolid. Una ciudad vibrante que esconde bellos rincones en su casco histórico, situado a escasos minutos de Sercotel Valladolid. Solo tendrás que iniciar un pequeño y agradable paseo entre calles estrechas y… Voilà! Arquitectura, historia, puro Valladolid. La excelente ubicación es solo una de las razones por las que querrás alojarte en nuestro Hotel Sercotel Valladolid. ¿Escuchas eso? Sí, querido viajero, es un chapuzón, es el agua de la piscina exterior del hotel estallando tras el zambullido de un huésped y tú puedes ser ese huésped.',
        4, TRUE, 'Valladolid'),
       ('Eurostars Madrid Tower',
        'El Eurostars Madrid Tower, situado en las primeras 30 plantas de la imponente Torre SyV, es una verdadera joya arquitectónica que se eleva hasta los 236 metros sobre el cielo de Madrid. Su ubicación privilegiada ofrece una experiencia única que fusiona un servicio excepcional con elegancia y comodidad en el mismo corazón financiero de una de las ciudades más vibrantes de Europa.',
        5, TRUE, 'Madrid');


INSERT INTO Habitacion (tamanno, precio, desayuno, ocupada, hotel)
VALUES (1, 50.00, TRUE, FALSE, 1),
       (2, 75.00, TRUE, FALSE, 1),
       (1, 60.99, TRUE, FALSE, 2),
       (2, 89.99, TRUE, FALSE, 2),
       (1, 100.00, TRUE, FALSE, 3),
       (2, 150.00, TRUE, FALSE, 3),
       (1, 200.00, TRUE, FALSE, 4),
       (2, 250.00, TRUE, FALSE, 4);
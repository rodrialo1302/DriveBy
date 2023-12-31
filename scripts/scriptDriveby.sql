DROP TABLE "VOTE";
DROP TABLE "COMMENT";
DROP TABLE "POST";
DROP TABLE "TRANSACTION";
DROP TABLE "USER";
DROP TABLE "PRODUCT";

CREATE TABLE "PRODUCT"
(
ID_PRODUCT BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
POINTS integer,
NAME varchar(255),
MEDIA varchar(1000),
TYPE integer,
PRIMARY KEY (ID_PRODUCT)
);

CREATE TABLE "USER"
( 
USERNAME varchar(30) NOT NULL,
NAME varchar(255),
MAIL varchar(255),
PASSWORD varchar(255),
CAR varchar(255),
BIO varchar(255),
MEDIA blob,
FRAME BIGINT,
POINTS INTEGER,
PRIMARY KEY (USERNAME),
FOREIGN KEY (FRAME) 
REFERENCES "PRODUCT"(ID_PRODUCT) ON DELETE CASCADE
);

CREATE TABLE "POST"
(
ID_POST BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
USERNAME varchar(30),
LOCATION varchar(255),
DESCRIPTION varchar(255),
ALERT_TYPE varchar(255),
DATE TIMESTAMP,
MEDIA blob,
PRIMARY KEY (ID_POST),
FOREIGN KEY (USERNAME)
REFERENCES "USER"(USERNAME) ON DELETE CASCADE
);

CREATE TABLE "COMMENT"
(
USERNAME varchar(30),
ID_POST BIGINT,
DATE timestamp,
TEXT varchar(255),
PRIMARY KEY (USERNAME, ID_POST, DATE),
FOREIGN KEY (USERNAME) REFERENCES "USER"(USERNAME)ON DELETE CASCADE,
FOREIGN KEY (ID_POST) REFERENCES POST(ID_POST) ON DELETE CASCADE
);

CREATE TABLE "VOTE"
(
VALUE BOOLEAN,
USERNAME varchar(30),
ID_POST BIGINT,
PRIMARY KEY (USERNAME, ID_POST),
FOREIGN KEY (USERNAME) REFERENCES "USER"(USERNAME) ON DELETE CASCADE,
FOREIGN KEY (ID_POST) REFERENCES POST(ID_POST) ON DELETE CASCADE
);

CREATE TABLE "TRANSACTION"
(
ID_TRANSACTION BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1),
ID_PRODUCT BIGINT,
USERNAME varchar(30),
DATE timestamp,
POINTS integer,
PRIMARY KEY (ID_TRANSACTION),
FOREIGN KEY (USERNAME) REFERENCES "USER"(USERNAME)ON DELETE CASCADE,
FOREIGN KEY (ID_PRODUCT) REFERENCES PRODUCT (ID_PRODUCT) ON DELETE CASCADE
);


INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (100,'marco1','https://cdn.akamai.steamstatic.com/steamcommunity/public/images/items/730/c30260bb120bf1379f075802653c8eb86da7a7e9.png',0);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (200,'marco2','https://cdn.akamai.steamstatic.com/steamcommunity/public/images/items/1263950/8472d7295ca91f42faaae0fb9143b2d94bd5b719.png',0);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (320,'marco3','https://cdn.akamai.steamstatic.com/steamcommunity/public/images/items/503830/420efee31f0b0a3fe39cb7409dbe9c3a3e0a0e3a.png',0);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (310,'marco4','https://cdn.akamai.steamstatic.com/steamcommunity/public/images/items/503830/f11982f49fc45890633921760df30d5aadee4ec4.png',0);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (150,'marco5','https://img1.picmix.com/output/stamp/normal/4/5/4/7/267454_1a01d.png',0);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (350,'marco6','https://cdn.akamai.steamstatic.com/steamcommunity/public/images/items/1263950/7c2d50439db54d0f305e7ed3fad30a426a92dc77.png',0);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (250,'marco7','https://cdn.akamai.steamstatic.com/steamcommunity/public/images/items/503830/6efa47b5601d4705d47b3a9b629b8f59072da376.png',0);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (400,'marco8','https://cdn.cloudflare.steamstatic.com/steamcommunity/public/images/items/855630/6e21507b7d02b18fbac3d34338c73228d799a11b.png',0);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (450,'marco9','https://cdn.cloudflare.steamstatic.com/steamcommunity/public/images/items/1442870/722e24aa17dbdcffe10f14ede941d851314dfd37.pngull',0);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (300,'marco10','https://cdn.cloudflare.steamstatic.com/steamcommunity/public/images/items/105600/2c29ecab86c2249f90c4f08ac5942810b188b50e.png',0);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (225,'marco11','https://avatarfiles.alphacoders.com/318/318389.png',0);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (175,'marco12','https://cdn.cloudflare.steamstatic.com/steamcommunity/public/images/items/1210230/1aa6c11f7af1af68afd17b60a2a558e427a4c687.png',0);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (500,'marcoAnim1','https://cdn.akamai.steamstatic.com/steamcommunity/public/images/items/1732740/1a05a372467f6f34d27014939133395ae9259ca4.png',1);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (400,'marcoAnim2','https://cdn.cloudflare.steamstatic.com/steamcommunity/public/images/items/1218900/9b7dd56af9d0cd8d492c7af823122398ef692842.png',1);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (450,'marcoAnim3','https://cdn.akamai.steamstatic.com/steamcommunity/public/images/items/212070/e39b802b3cff406590139dba9de470f31810027c.gif',1);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (600,'marcoAnim4','https://cdn.akamai.steamstatic.com/steamcommunity/public/images/items/212070/9b6b26c7a03046da283408d72319f9eec932c80a.gif',1);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (650,'marcoAnim5','https://cdn.cloudflare.steamstatic.com/steamcommunity/public/images/items/601220/b52bd23d8c484b108683f378441fdd5d8bcf285f.png',1);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (700,'marcoAnim6','https://cdn.cloudflare.steamstatic.com/steamcommunity/public/images/items/1218900/b9c02dcdac95603a33c8bc39212098aef6adc3f6.png',1);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (550,'marcoAnim7','https://cdn.akamai.steamstatic.com/steamcommunity/public/images/items/1735420/e22ef9c9e2af9d0a70904ec8e8ff88cb0c2b5e0e.png',1);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (625,'marcoAnim8','https://cdn.cloudflare.steamstatic.com/steamcommunity/public/images/items/322330/984f18d4bcc76669bcbd8971f962d1b75716dd11.png',1);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (475,'marcoAnim9','https://cdn.akamai.steamstatic.com/steamcommunity/public/images/items/730/0069a31ee7292be2aae3969b57a261e06a57bdcc.png',1);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (700,'marcoAnim10','https://cdn.cloudflare.steamstatic.com/steamcommunity/public/images/items/1239300/083c19cc935001ee0508aff3b948da62b6a093f6.png',1);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (525,'marcoAnim11','https://cdn.akamai.steamstatic.com/steamcommunity/public/images/items/1263950/ebe6b674deca163b28423e3b925bd36b0f0f357b.png',1);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (600,'marcoAnim12','https://cdn.cloudflare.steamstatic.com/steamcommunity/public/images/items/646270/ce9de0f7b286a2f15f49814021e0a61642b0fc39.png',1);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (300,'avatar1','https://statics.vrutal.com/gamertars/psn/03c/ste_stave.png',2);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (425,'avatar2','https://www.pngall.com/wp-content/uploads/12/Avatar-PNG-HD-Image.png',2);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (350,'avatar3','https://statics.vrutal.com/gamertars/psn/4b4/ruby12345678.png',2);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (550,'avatar4','https://avatars.trackercdn.com/api/avatar/2/x86demon_UA.png',2);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (525,'avatar5','https://oyster.ignimgs.com/social/avatars/users2/66/5858142-1371517666.png',2);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (375,'avatar6','https://image.api.playstation.com/cdn/UP0102/CUSA01200_00/zei3t2PKUYFK7PBDegZFy1gdao505iMz.png',2);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (500,'avatar7','https://forum.psnprofiles.com/uploads/monthly_09_2016/post-188170-0-02184600-1474556298.png',2);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (450,'avatar8','https://static-resource.np.community.playstation.net/avatar/WWS_J/J0003.png',2);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (525,'avatar9','https://static.wikia.nocookie.net/rumbleverse/images/8/8a/Pigeon_Mask_-_Accessory_-_Rumbleverse.png',2);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (325,'avatar10','https://i.redd.it/rfgm9eyp8o421.png',2);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (475,'avatar11','https://i.psnprofiles.com/avatars/l/9.fac11608.png',2);
INSERT INTO DRIVEBY.PRODUCT (POINTS,"NAME",MEDIA,"TYPE") VALUES (425,'avatar12','https://pngimg.es/d/fallout_PNG44.png',2);
 


INSERT INTO "USER" VALUES ('ConductorMolon78','Javier Jacinto Jimenez','javijacintojimenez@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99','Citroen C4 ','Me gustan los coches y conducir. Divorciado',null,null,650);
INSERT INTO "USER" VALUES ('MuchosCochesPocasNueces','Manuel Herreros','manuelherreros@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99','Volkswagen Golf ','Coches. Vin Diesel. Fast and Furios: Tokyo Drift. Nitroglicerina',null,null,800);
INSERT INTO "USER" VALUES ('ElVerdeConBigoteDeCars','Oscar Garcia','oscargarc@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99','Peugeot 206 ','Una vez soñe con un aleron bastante chulo',null,null,200);
INSERT INTO "USER" VALUES ('conducirEsVivir21','Juan Eredia','eredia.juan@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99','Renault Megane ','Del osasuna desde crio. Soy mecanico en Torremolinos',null,null,430); 
INSERT INTO "USER" VALUES ('allCarsWYSTMN','Pablo Gutierrez','pabloguti02 @gmail.com','5f4dcc3b5aa765d61d8327deb882cf99','Audi A6 ','Tengo un audi ;)',null,null,720);
INSERT INTO "USER" VALUES ('ConductorPromedio','Sergio Caballero','caballero.sergio@hotmail.com','5f4dcc3b5aa765d61d8327deb882cf99','Citroen C2 ','Biografia promedia',null,6,120);
INSERT INTO "USER" VALUES ('babyDriverGallego','Jordi Calvo','jordiCalvo87@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99','Toyota Yaris ','Aficionado al cine y a los coches que salen en el cine. Divorciado',null,19,490);
INSERT INTO "USER" VALUES ('AdelantadoPorLaJessy','Sara Hernandez','sarahernandez93@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99','Renault Scenic','La mas chula de la carretera <3',null,8,670);
INSERT INTO "USER" VALUES ('ChoferSinAnimoDeLucro','Gabriel Sanchez', 'sanchezGabriel81@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99','Fiat Panda','Taxista en madrid por el dia, dormido por la noche',null,19,230);
INSERT INTO "USER" VALUES ('xXLealAlAsfaltoXx','Julio Fernandez','julio.fer123@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99','Seat Ibiza','Lealtad a la patria y al motor',null,22,420);
INSERT INTO "USER" VALUES ('conduceCamiones24','Jose Miguel Ramos', 'jose.miguel.ramos@yahoo.es','5f4dcc3b5aa765d61d8327deb882cf99','Toyota Auris','Coches. Vehiculos incluso. Automobiles puede ser.',null,11,230);
INSERT INTO "USER" VALUES ('cocheNuevoQuienEs','Alba Lopez','lopezalba@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99','Kia Rio','Esta bastante bien esto de conducir con un coche eh',null,12,560);
INSERT INTO "USER" VALUES ('cochecitoLere', 'Domingo Carrasco', 'carrasco.domingo87@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99','Volkswagen Polo','Conduzco un autobus y poco mas',null,14,820);
INSERT INTO "USER" VALUES ('CadenaVial','Mohamed Sanchez','mohamedsanchez@hotmail.com','5f4dcc3b5aa765d61d8327deb882cf99','Renault Laguna','¿Quien quiere musica al volante cuando puedes escuchar al motor?',null,12,430);
INSERT INTO "USER" VALUES ('ConducirYLoQueSurja','Carlos Gomez', 'gomezCarlos23@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99','Seat Ibiza','Mad Max:Fury Road en realidad esta basada en la V-30',null,5,290);
INSERT INTO "USER" VALUES('a', 'a', 'a', '0cc175b9c0f1b6a831c399e269772661', 'a', 'a', null, 5, 29000000);

INSERT INTO DRIVEBY.POST (USERNAME,LOCATION,DESCRIPTION,ALERT_TYPE,"DATE",MEDIA) VALUES ('ConductorMolon78','Valladolid','Retencion en el km 12 de la autopista','Congestion de trafico','2023-03-18 14:55:00',null);
INSERT INTO DRIVEBY.POST (USERNAME,LOCATION,DESCRIPTION,ALERT_TYPE,"DATE",MEDIA) VALUES ('MuchosCochesPocasNueces','Santovenia de Pisuerga','Volkswagen polo accidentado','Aviso','2023-03-19 16:15:00',null);
INSERT INTO DRIVEBY.POST (USERNAME,LOCATION,DESCRIPTION,ALERT_TYPE,"DATE",MEDIA) VALUES ('conducirEsVivir21','Valladolid','Control en la rotonda a la salida del centro comercial Rio Shopping','Control','2023-03-16 20:35:00',null);
INSERT INTO DRIVEBY.POST (USERNAME,LOCATION,DESCRIPTION,ALERT_TYPE,"DATE",MEDIA) VALUES ('allCarsWYSTMN','Valladolid','Atasco en la Avenida Palencia','Congestion de trafico','2023-03-17 19:45:00',null);
INSERT INTO DRIVEBY.POST (USERNAME,LOCATION,DESCRIPTION,ALERT_TYPE,"DATE",MEDIA) VALUES ('ConductorPromedio','Malaga','Citroen negro circulando con dos tubos atravesando el coche','Aviso','2023-03-20 17:30:00',null);
INSERT INTO DRIVEBY.POST (USERNAME,LOCATION,DESCRIPTION,ALERT_TYPE,"DATE",MEDIA) VALUES ('xXLealAlAsfaltoXx','Madrid','2 zumbaos saliendose del coche por la ventanilla en la M-40','Aviso','2023-03-12 12:15:00',null);
INSERT INTO DRIVEBY.POST (USERNAME,LOCATION,DESCRIPTION,ALERT_TYPE,"DATE",MEDIA) VALUES ('CadenaVial','Valladolid','Twingo azul circulando por autovia sin rueda','Aviso','2023-03-22 09:25:00',null);
INSERT INTO DRIVEBY.POST (USERNAME,LOCATION,DESCRIPTION,ALERT_TYPE,"DATE",MEDIA) VALUES ('ChoferSinAnimoDeLucro','Valladolid','Accidente en la salida de la rondilla hacia Vallsur','Obstaculo','2023-03-20 13:45:00',null);
INSERT INTO DRIVEBY.POST (USERNAME,LOCATION,DESCRIPTION,ALERT_TYPE,"DATE",MEDIA) VALUES ('ConductorPromedio','Valladolid','Precio de la gasolina excesivo en Navarra','Congestion de trafico','2023-03-17 19:45:00',null);

INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ConductorMolon78',1);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'MuchosCochesPocasNueces',1);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ElVerdeConBigoteDeCars',1);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'babyDriverGallego',1);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ConducirYLoQueSurja',1);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'CadenaVial',1);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'xXLealAlAsfaltoXx',1);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'AdelantadoPorLaJessy',1);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'conduceCamiones24',1);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'cochecitoLere',1);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'cocheNuevoQuienEs',1);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'ChoferSinAnimoDeLucro',1);

INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ConductorMolon78',2);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'MuchosCochesPocasNueces',2);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ElVerdeConBigoteDeCars',2);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'babyDriverGallego',2);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ConducirYLoQueSurja',2);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'CadenaVial',2);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'xXLealAlAsfaltoXx',2);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'AdelantadoPorLaJessy',2);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'conduceCamiones24',2);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'cochecitoLere',2);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'allCarsWYSTMN',2);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ConductorPromedio',2);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'cocheNuevoQuienEs',2);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'ChoferSinAnimoDeLucro',2);

INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ConductorMolon78',3);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'MuchosCochesPocasNueces',3);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ElVerdeConBigoteDeCars',3);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ConducirYLoQueSurja',3);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'CadenaVial',3);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'xXLealAlAsfaltoXx',3);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'AdelantadoPorLaJessy',3);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'conduceCamiones24',3);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'cocheNuevoQuienEs',3);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'ChoferSinAnimoDeLucro',3);

INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ConductorMolon78',4);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'MuchosCochesPocasNueces',4);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'AdelantadoPorLaJessy',4);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'conduceCamiones24',4);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'cochecitoLere',4);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'allCarsWYSTMN',4);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'cocheNuevoQuienEs',4);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ChoferSinAnimoDeLucro',4);

INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ConductorMolon78',5);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'MuchosCochesPocasNueces',5);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'ElVerdeConBigoteDeCars',5);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'babyDriverGallego',5);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'ConducirYLoQueSurja',5);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'CadenaVial',5);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'ConductorPromedio',5);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'xXLealAlAsfaltoXx',5);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'AdelantadoPorLaJessy',5);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'allCarsWYSTMN',5);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'cocheNuevoQuienEs',5);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ChoferSinAnimoDeLucro',5);

INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'ConductorMolon78',6);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'MuchosCochesPocasNueces',6);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'ElVerdeConBigoteDeCars',6);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'babyDriverGallego',6);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ConducirYLoQueSurja',6);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'CadenaVial',6);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ConductorPromedio',6);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'xXLealAlAsfaltoXx',6);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'AdelantadoPorLaJessy',6);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'allCarsWYSTMN',6);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'cocheNuevoQuienEs',6);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ChoferSinAnimoDeLucro',6);

INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ConductorMolon78',7);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'MuchosCochesPocasNueces',7);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'ElVerdeConBigoteDeCars',7);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'babyDriverGallego',7);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'ConducirYLoQueSurja',7);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'CadenaVial',7);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ConductorPromedio',7);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'xXLealAlAsfaltoXx',7);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'AdelantadoPorLaJessy',7);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'conduceCamiones24',7);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'cochecitoLere',7);

INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ConductorMolon78',8);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'MuchosCochesPocasNueces',8);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'ElVerdeConBigoteDeCars',8);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'babyDriverGallego',8);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'AdelantadoPorLaJessy',8);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'conduceCamiones24',8);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'cochecitoLere',8);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'allCarsWYSTMN',8);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ConductorPromedio',8);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'cocheNuevoQuienEs',8);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'ChoferSinAnimoDeLucro',8);

INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ConductorMolon78',9);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'MuchosCochesPocasNueces',9);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'ElVerdeConBigoteDeCars',9);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'babyDriverGallego',9);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'ConducirYLoQueSurja',9);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'CadenaVial',9);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'xXLealAlAsfaltoXx',9);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'AdelantadoPorLaJessy',9);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (false,'allCarsWYSTMN',9);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ConductorPromedio',9);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'cocheNuevoQuienEs',9);
INSERT INTO DRIVEBY.VOTE ("VALUE",USERNAME,ID_POST) VALUES (true,'ChoferSinAnimoDeLucro',9);

INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('AdelantadoPorLaJessy',1,'2023-03-18 15:55:00','¿Alguien sabe porque?');
INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('cocheNuevoQuienEs',1,'2023-03-18 16:15:00','Lo tendre en cuenta');
INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('MuchosCochesPocasNueces',1,'2023-03-18 16:35:00','Estoy a punto de pasar por ahi');

INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('allCarsWYSTMN',2,'2023-03-19 16:20:00','¿El conductor esta bien?');
INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('cocheNuevoQuienEs',2,'2023-03-19 16:30:00','Conozco al tio del coche y es bastante despistado');
INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('ConductorMolon78',2,'2023-03-19 16:45:00','Que mal ha quedado');
INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('conducirEsVivir21',2,'2023-03-19 17:05:00','Yo si fuera el me compraba otro coche');

INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('cochecitoLere',3,'2023-03-16 20:55:00','gracias por el aviso');
INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('ConducirYLoQueSurja',3,'2023-03-16 21:15:00','a mi me han parao y me han hecho un control de alcoholemia');

INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('conduceCamiones24',4,'2023-03-17 19:55:00','que asco con el trafico');
INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('ConductorPromedio',4,'2023-03-17 20:15:00','alguien sabe si es por algo especifico');
INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('babyDriverGallego',4,'2023-03-17 20:20:00','Me suena que es por obras');

INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('ConductorMolon78',5,'2023-03-20 17:55:00','Que hace el tio ese');
INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('xXLealAlAsfaltoXx',5,'2023-03-20 18:00:00','Va a matar a alguien con la tonteria');

INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('cocheNuevoQuienEs',6,'2023-03-12 12:35:00','que irresponables');
INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('MuchosCochesPocasNueces',6,'2023-03-12 12:55:00','ojala les hayan metido una multa');
INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('AdelantadoPorLaJessy',6,'2023-03-12 13:05:00','tiene pinta divertido en realidad');

INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('ConducirYLoQueSurja',7,'2023-03-22 09:45:00','pero se ha enterado acaso?');
INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('conduceCamiones24',7,'2023-03-22 09:55:00','que peligro tiene encima');

INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('allCarsWYSTMN',8,'2023-03-20 13:55:00','Yo lo he visto y no tiene buena pinta');
INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('ElVerdeConBigoteDeCars',8,'2023-03-20 14:00:00','alguien sabe si hay heridos?');
INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('babyDriverGallego',8,'2023-03-20 14:10:00','ha sido muy grave?');

INSERT INTO DRIVEBY.COMMENT (USERNAME,ID_POST,"DATE",TEXT) VALUES ('ConductorMolon78',8,'2023-03-17 19:55:00','pasare por murcia entonces, gracias');


INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (1,'ConductorMolon78','2023-02-18 11:15:00.0',100);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (13,'ConductorMolon78','2023-02-18 11:15:00.0',500);

INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (2,'MuchosCochesPocasNueces','2023-02-17 12:30:00.0',200);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (14,'MuchosCochesPocasNueces','2023-02-17 12:30:00.0',400);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (25,'MuchosCochesPocasNueces','2023-02-17 12:30:00.0',300);

INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (3,'ElVerdeConBigoteDeCars','2023-01-22 11:15:00.0',320);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (15,'ElVerdeConBigoteDeCars','2023-01-22 11:15:00.0',450);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (27,'ElVerdeConBigoteDeCars','2023-01-22 11:15:00.0',350);

INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (4,'conducirEsVivir21','2022-12-28 19:30:00.0',310);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (16,'conducirEsVivir21','2022-12-28 19:30:00.0',600);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (28,'conducirEsVivir21','2022-12-28 19:30:00.0',550);

INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (5,'allCarsWYSTMN','2023-05-03 11:45:00.0',150);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (17,'allCarsWYSTMN','2023-05-03 11:45:00.0',650);

INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (6,'ConductorPromedio','2023-03-12 20:15:00.0',350);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (18,'ConductorPromedio','2023-03-12 20:15:00.0',700);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (29,'ConductorPromedio','2023-03-12 20:15:00.0',525);

INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (7,'babyDriverGallego','2023-04-16 12:00:00.0',250);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (19,'babyDriverGallego','2023-04-16 12:00:00.0',550);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (30,'babyDriverGallego','2023-04-16 12:00:00.0',375);

INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (8,'AdelantadoPorLaJessy','2023-02-21 14:15:00.0',400);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (20,'AdelantadoPorLaJessy','2023-02-21 14:15:00.0',625);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (31,'AdelantadoPorLaJessy','2023-02-21 14:15:00.0',500);

INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (9,'ChoferSinAnimoDeLucro','2023-01-04 15:30:00.0',450);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (21,'ChoferSinAnimoDeLucro','2023-01-04 15:30:00.0',475);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (32,'ChoferSinAnimoDeLucro','2023-01-04 15:30:00.0',450);

INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (10,'xXLealAlAsfaltoXx','2023-03-14 17:45:00.0',300);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (22,'xXLealAlAsfaltoXx','2023-03-14 17:45:00.0',700);

INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (11,'conduceCamiones24','2023-02-28 10:40:00.0',225);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (23,'conduceCamiones24','2023-02-28 10:40:00.0',525);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (33,'conduceCamiones24','2023-02-28 10:40:00.0',525);

INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (12,'cocheNuevoQuienEs','2023-01-04 19:10:00.0',175);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (24,'cocheNuevoQuienEs','2023-1-04 10:40:00.0',600);

INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (2,'cochecitoLere','2023-05-12 11:20:00.0',200);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (14,'cochecitoLere','2023-05-12 11:20:00.0',400);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (34,'cochecitoLere','2023-05-12 11:20:00.0',325);

INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (5,'CadenaVial','2023-04-26 13:40:00.0',150);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (13,'CadenaVial','2023-04-26 13:40:00.0',500);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (35,'CadenaVial','2023-04-26 13:40:00.0',475);

INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (8,'ConducirYLoQueSurja','2023-03-10 10:15:00.0',400);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (16,'ConducirYLoQueSurja','2023-03-10 10:15:00.0',600);
INSERT INTO DRIVEBY."TRANSACTION" (ID_PRODUCT,USERNAME,"DATE",POINTS) VALUES (36,'ConducirYLoQueSurja','2023-03-10 10:15:00.0',425);

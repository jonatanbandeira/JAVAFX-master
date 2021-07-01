CREATE TABLE raca(
   CdRaca     serial       NOT NULL,
   nomeRaca   varchar(50) NOT NULL,
   CONSTRAINT pk_cdRaca
      PRIMARY KEY(CdRaca)
);

INSERT INTO raca(nomeRaca) VALUES('Akita');
INSERT INTO raca(nomeRaca) VALUES('Beagle');
INSERT INTO raca(nomeRaca) VALUES('Border Collie');
INSERT INTO raca(nomeRaca) VALUES('Labrador');
INSERT INTO raca(nomeRaca) VALUES('Vira Lata');

CREATE TABLE porte(
   cdPorte     serial         NOT NULL,
   nomePorte   varchar(50)    NOT NULL,
   CONSTRAINT pk_Porte
      PRIMARY KEY(cdPorte)
);--porte do animal

INSERT INTO porte(nomePorte) VALUES('Pequeno:   0kg - 10kg');
INSERT INTO porte(nomePorte) VALUES('Médio:    10kg - 20kg');
INSERT INTO porte(nomePorte) VALUES('Grande:  +20kg');

CREATE TABLE sexo(
   cdSexo     serial         NOT NULL,
   nomeSexo   varchar(50)    NOT NULL,
   CONSTRAINT pk_cdSexo
      PRIMARY KEY(cdSexo)
);--sexo do animal

INSERT INTO sexo(nomeSexo) VALUES('Fêmea');
INSERT INTO sexo(nomeSexo) VALUES('Macho');

CREATE TABLE cidade(
   cdCidade     serial      NOT NULL,
   nomeCidade   varchar(50) NOT NULL,
   CONSTRAINT pk_cdCidade
      PRIMARY KEY(cdCidade)
);--cidade do animal

INSERT INTO cidade(nomeCidade) VALUES('Cachoeiro de Itapemirim');
INSERT INTO cidade(nomeCidade) VALUES('Vitória');
INSERT INTO cidade(nomeCidade) VALUES('Vila Velha');
INSERT INTO cidade(nomeCidade) VALUES('Marechal Floriano');
INSERT INTO cidade(nomeCidade) VALUES('Vargem Alta');

CREATE TABLE pets(
    cdPet       serial        NOT NULL,
    nomePet     varchar(50)   NOT NULL,
    nomeDono    varchar(50)   NOT NULL,
    telefone    varchar(15)   NOT NULL,
    email       varchar(50)   NOT NULL,
    cdPorte     int           NOT NULL,   
    cdSexo      int           NOT NULL,
    cdCidade    int           NOT NULL,
    cdRaca      int           NOT NULL,
   CONSTRAINT pk_pets
      PRIMARY KEY(cdPet),
   CONSTRAINT fk_pets_raca
      FOREIGN KEY(cdRaca)
      REFERENCES raca(cdRaca),
   CONSTRAINT fk_pets_porte
      FOREIGN KEY(cdPorte)
      REFERENCES porte(cdPorte),
   CONSTRAINT fk_pets_cidade
      FOREIGN KEY(cdCidade)
      REFERENCES cidade(cdCidade),
   CONSTRAINT fk_pets_sexo
      FOREIGN KEY(cdSexo)
      REFERENCES sexo(cdSexo)
);


INSERT INTO pets(nomePet, cdraca, cdporte, cdsexo, nomeDono, telefone, email, cdcidade) 
  VALUES('Meg',2, 2, 1,'Maria','(11)9999-9999','mariasilva@email.com',2);

INSERT INTO pets(nomePet, cdraca, cdporte, cdsexo, nomeDono, telefone, email, cdcidade) 
  VALUES('Tomaz',2, 2, 2,'Pedro','(11)8888-9999','pedrosantos@email.com',2);

INSERT INTO pets(nomePet, cdraca, cdporte, cdsexo, nomeDono, telefone, email, cdcidade) 
  VALUES('Lola',1, 1, 1,'Lais','(11)7777-9999','laisramos@email.com',1);

INSERT INTO pets(nomePet, cdraca, cdporte, cdsexo, nomeDono, telefone, email, cdcidade) 
  VALUES('Tobias',1, 1, 2,'Fabio','(27)7777-1111','fabiosiqueira@email.com',1);

INSERT INTO pets(nomePet, cdraca, cdporte, cdsexo, nomeDono, telefone, email, cdcidade) 
  VALUES('Paçoca',3, 3, 1,'Mario','(28)3333-9999','mariomecias@email.com',3);

INSERT INTO pets(nomePet, cdraca, cdporte, cdsexo, nomeDono, telefone, email, cdcidade) 
  VALUES('Nutela',3, 3, 2,'Bia','(27)2222-1111','biamachado@email.com',3);
  
INSERT INTO pets(nomePet, cdraca, cdporte, cdsexo, nomeDono, telefone, email, cdcidade) 
  VALUES('Brutos',4, 1, 2,'Julio','(28)1111-9999','juliomecias@email.com',4);

INSERT INTO pets(nomePet, cdraca, cdporte, cdsexo, nomeDono, telefone, email, cdcidade) 
  VALUES('Lola',4, 1, 1,'Rita','(27)2222-5555','ritamachado@email.com',3);
  
INSERT INTO pets(nomePet, cdraca, cdporte, cdsexo, nomeDono, telefone, email, cdcidade) 
  VALUES('Mili',5, 2, 1,'Marcos','(28)1111-9999','marcosfelix@email.com',5);

INSERT INTO pets(nomePet, cdraca, cdporte, cdsexo, nomeDono, telefone, email, cdcidade) 
  VALUES('Donald',5, 2, 2,'Monica','(27)6611-5555','monicaamachado@email.com',5);
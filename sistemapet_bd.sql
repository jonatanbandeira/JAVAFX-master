CREATE TABLE especies(
   tipo_pet   serial      NOT NULL,
   descricao  varchar(50) NOT NULL,
   CONSTRAINT pk_especies
      PRIMARY KEY(tipo_pet)
);--Cachorro ou gato

INSERT INTO especies(descricao) VALUES('Gato');
INSERT INTO especies(descricao) VALUES('Cachorro');

CREATE TABLE raca(
   cod_raca   serial      NOT NULL,
   descricao  varchar(50) NOT NULL,
   tipo_pet   int         NOT NULL,
   CONSTRAINT pk_raca
      PRIMARY KEY(cod_raca),
   CONSTRAINT fk_raca_especies
      FOREIGN KEY(tipo_pet)
      REFERENCES especies(tipo_pet)
);--raça do animal

INSERT INTO raca(descricao,tipo_pet) VALUES('Akita'        , 2);
INSERT INTO raca(descricao,tipo_pet) VALUES('Beagle'       , 2);
INSERT INTO raca(descricao,tipo_pet) VALUES('Border Collie', 2);
INSERT INTO raca(descricao,tipo_pet) VALUES('Labrador'     , 2);
INSERT INTO raca(descricao,tipo_pet) VALUES('Vira Lata'    , 2);

INSERT INTO raca(descricao,tipo_pet) VALUES('Siames'       , 1);
INSERT INTO raca(descricao,tipo_pet) VALUES('Persa'        , 1);
INSERT INTO raca(descricao,tipo_pet) VALUES('Himalaia'     , 1);

CREATE TABLE porte(
   cod_porte   serial      NOT NULL,
   descricao   varchar(50) NOT NULL,
   CONSTRAINT pk_cod_porte
      PRIMARY KEY(cod_porte)
);--porte do animal

INSERT INTO porte(descricao) VALUES('Pequeno:   0kg - 10kg');
INSERT INTO porte(descricao) VALUES('Médio:    10kg - 20kg');
INSERT INTO porte(descricao) VALUES('Grande:  +20kg');

CREATE TABLE sexo(
   cod_sexo    serial      NOT NULL,
   descricao   varchar(50) NOT NULL,
   CONSTRAINT pk_cod_sexo
      PRIMARY KEY(cod_sexo)
);--sexo do animal

INSERT INTO sexo(descricao) VALUES('Fêmea');
INSERT INTO sexo(descricao) VALUES('Macho');

CREATE TABLE cidade(
   cod_cidade  serial      NOT NULL,
   descricao   varchar(50) NOT NULL,
   CONSTRAINT pk_cod_cidade
      PRIMARY KEY(cod_cidade)
);--cidade do animal

INSERT INTO cidade(descricao) VALUES('Cachoeiro de Itapemirim');
INSERT INTO cidade(descricao) VALUES('Vitória');
INSERT INTO cidade(descricao) VALUES('Vila Velha');
INSERT INTO cidade(descricao) VALUES('Marechal Floriano');
INSERT INTO cidade(descricao) VALUES('Vargem Alta');

/*CREATE TABLE usuario(
    cod_usuario   serial      NOT NULL,
    email         varchar(50) NOT NULL,
    senha         varchar(10) NOT NULL,
   CONSTRAINT pk_cod_usuario
      PRIMARY KEY(cod_usuario)
);-- Ainda não sei bem  se isso está certo.*/


CREATE TABLE pet(
    cod_pet     serial         NOT NULL,
    nome_pet    varchar(50)    NOT NULL,
    tipo_pet    int            NOT NULL,--Dominio em especies
    cod_raca    int            NOT NULL,--Dominio em raça
    cod_porte   int            NOT NULL,--Dominio em porte
    cod_sexo    int            NOT NULL,--Dominio em sexo
    nome_dono   varchar(50)    NOT NULL,
    telefone    varchar(12)    NOT NULL,
    cod_cidade  int            NOT NULL,--Dominio em cidade
	senha       varchar(10)    NOT NULL,
	email       varchar(50)    NOT NULL,
   CONSTRAINT pk_pet
      PRIMARY KEY(cod_pet),
   CONSTRAINT fk_pet_especies
      FOREIGN KEY(tipo_pet)
      REFERENCES especies(tipo_pet),
   CONSTRAINT fk_pet_raca
      FOREIGN KEY(cod_raca)
      REFERENCES raca(cod_raca),
   CONSTRAINT fk_pet_porte
      FOREIGN KEY(cod_porte)
      REFERENCES porte(cod_porte),
   CONSTRAINT fk_pet_cidade
      FOREIGN KEY(cod_cidade)
      REFERENCES cidade(cod_cidade)
);

INSERT INTO pet(nome_pet, tipo_pet, cod_raca, cod_porte, cod_sexo,nome_dono, telefone, cod_cidade, senha, email) 
         VALUES('Belinha', 2,5,2,1,'Alberto', '11111-1111', '3','111111', 'Alberto@gmail.com');
	
INSERT INTO pet(nome_pet, tipo_pet, cod_raca, cod_porte, cod_sexo,nome_dono, telefone, cod_cidade
	, senha , email)
         VALUES('Bob', 2, 5, 2, 2, 'Luana', '22222-2222', '3', '222222', 'Luana@gmail.com');
	
INSERT INTO pet(nome_pet, tipo_pet, cod_raca, cod_porte, cod_sexo,nome_dono, telefone, cod_cidade
	,senha, email) 
         VALUES('Laurinha', 1, 6, 1, 1, 'Mateus', '33333-3333', '4', '333333', 'mateus@gmail.com');

INSERT INTO pet(nome_pet, tipo_pet, cod_raca, cod_porte, cod_sexo,nome_dono,telefone, cod_cidade,
	senha, email)
         VALUES('Thor', 1, 6, 1, 2, 'Fernanda','66666-6666', '4','666666', 'fernanda@gmail.com');
	
INSERT INTO pet(nome_pet, tipo_pet, cod_raca, cod_porte, cod_sexo,nome_dono, telefone, cod_cidade, 
		email, senha)
         VALUES('Tequila', 1, 6, 2, 1, 'Eduardo', '98164-7965', '1', 'eduardo@gmail.com', '555555');
	
INSERT INTO pet(nome_pet, tipo_pet, cod_raca, cod_porte, cod_sexo,nome_dono, telefone, cod_cidade,
		email, senha)
         VALUES('Tody', 1, 6, 2, 2, 'Ulises', '98654-9784', '1', 'ulises@gmail.com', '777777');
	
INSERT INTO pet(nome_pet, tipo_pet, cod_raca, cod_porte, cod_sexo,nome_dono, telefone, cod_cidade,
	    email, senha)
         VALUES('Stark', 1, 7, 2, 2, 'Luis', '55555-5555', '2', 'luis@gmail.com', '556669');

INSERT INTO pet(nome_pet, tipo_pet, cod_raca, cod_porte, cod_sexo,nome_dono, telefone, cod_cidade,
		email, senha)
		 VALUES('Pipoca', 1, 7, 2, 1, 'Rafaela', '99999-9999', '2', 'rafaela@gmail.com', '965823');
	
--Cidade 5, mesmo porte, mesma raça.
INSERT INTO pet(nome_pet, tipo_pet, cod_raca, cod_porte, cod_sexo, nome_dono, telefone    , cod_cidade,
	email, senha)
         VALUES('Bartolomeu', 2, 1, 3, 2, 'Vanessa', '99977-9977', '5', 'vanessa@gmail.com', '506320');
	
INSERT INTO pet(nome_pet, tipo_pet, cod_raca, cod_porte, cod_sexo,nome_dono, telefone, cod_cidade,
	email,senha)
         VALUES('Bolinha', 2, 1, 3, 2, 'Mariana', '99988-8988', '5', 'mariana@gmail.com', '975613');

INSERT INTO pet(nome_pet, tipo_pet, cod_raca, cod_porte, cod_sexo, nome_dono, telefone, cod_cidade,
	email, senha)
         VALUES('Lipe', 2, 1, 3, 2,'Ana','98654-9867', '5', 'ana@gmail.com', '987453');
	
INSERT INTO pet(nome_pet, tipo_pet, cod_raca, cod_porte, cod_sexo,nome_dono, telefone, cod_cidade,
	email, senha)
         VALUES('Mel', 2, 1, 3, 1, 'Ricardo', '12321-2288', '5', 'ricardo@gmail.com', '545459');

INSERT INTO pet(nome_pet, tipo_pet, cod_raca, cod_porte, cod_sexo,nome_dono, telefone    , cod_cidade
	,email, senha)
         VALUES('Laika', 2, 1, 3, 1, 'Ester','32111-8822', '5', 'ester@gmail.com', '4793326');
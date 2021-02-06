	CREATE TABLE LOTERIA(
		id bigint(200) NOT NULL AUTO_INCREMENT,
	    email VARCHAR(55) NOT NULL,

	    PRIMARY KEY (id)

	    )
		ENGINE = InnoDB DEFAULT CHARSET=utf8;
		
		
	CREATE TABLE loteria_numero_aleatorio(
	numero_id bigint(200) NOT NULL AUTO_INCREMENT,
	loteria_id bigint(200) NOT NULL,
	numeros VARCHAR (255),
	
	FOREIGN KEY (loteria_id) REFERENCES LOTERIA(id),
	PRIMARY KEY (numero_id)
	);
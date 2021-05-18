CREATE TABLE public.loteria
(
   
    id int GENERATED BY DEFAULT AS IDENTITY,
    email character varying(64) NOT NULL,
    PRIMARY KEY (id)
);

		
		
CREATE TABLE loteria_numero_aleatorio
(
    numero_id int GENERATED BY DEFAULT AS IDENTITY,
    loteria_id int NOT NULL,
    numeros character varying(255),
    
    PRIMARY KEY (numero_id),
    CONSTRAINT loteria_id_fkey
    FOREIGN KEY(loteria_id) REFERENCES LOTERIA(id)
);
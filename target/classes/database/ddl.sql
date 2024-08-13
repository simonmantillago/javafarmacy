-- Creación de la tabla customer
CREATE TABLE customer (
    idcustomer varchar(20) PRIMARY KEY,
    namecustomer varchar(50) NOT NULL,
    lastnamecustomer varchar(50) NOT NULL,
    codecitycustomer varchar(5),
    emailcustomer varchar(100),
    birthdate date,
    lon float8,
    latitud float8,
    FOREIGN KEY (codecitycustomer) REFERENCES city (codecity)
);

-- Creación de la tabla city
CREATE TABLE city (
    codecity varchar(5) PRIMARY KEY,
    namecity varchar(50) NOT NULL,
    codereg varchar(5),
    FOREIGN KEY (codereg) REFERENCES region (codereg)
);

-- Creación de la tabla country
CREATE TABLE country (
    codecountry varchar(5) PRIMARY KEY,
    namecountry varchar(50) NOT NULL
);

-- Creación de la tabla region
CREATE TABLE region (
    codereg varchar(5) PRIMARY KEY,
    namereg varchar(50) NOT NULL,
    codecountry varchar(5),
    FOREIGN KEY (codecountry) REFERENCES country (codecountry)
);

-- Creación de la tabla farmacy
CREATE TABLE farmacy (
    idfarmacy int AUTO_INCREMENT PRIMARY KEY,
    namefarmacy varchar(60) NOT NULL,
    adressfarmacy varchar(100) NOT NULL,
    longitude float8,
    latfarmacy float8,
    codecityfarmacy varchar(5),
    logofarmacy varchar(50),
    FOREIGN KEY (codecityfarmacy) REFERENCES city (codecity)
);

-- Creación de la tabla medicine
CREATE TABLE medicine (
    id int AUTO_INCREMENT PRIMARY KEY,
    proceedings varchar(10),
    namemedicine varchar(100) NOT NULL,
    healthregister varchar(50),
    description text,
    descriptionshort varchar(60),
    codemodeadmin int4,
    codeap int4,
    namerol varchar(100),
    codelab int4,
    codeum int4,
    FOREIGN KEY (codemodeadmin) REFERENCES modeadminstration (id),
    FOREIGN KEY (codeap) REFERENCES activeprinciple (idap),
    FOREIGN KEY (codelab) REFERENCES labotary (id),
    FOREIGN KEY (codeum) REFERENCES unitmeasurement (idum)
);

-- Creación de la tabla modeadminstration
CREATE TABLE modeadminstration (
    id int AUTO_INCREMENT PRIMARY KEY,
    descriptionmode varchar(60) NOT NULL
);

-- Creación de la tabla activeprinciple
CREATE TABLE activeprinciple (
    idap int AUTO_INCREMENT PRIMARY KEY,
    nameap varchar(60) NOT NULL
);

-- Creación de la tabla labotary
CREATE TABLE labotary (
    id int AUTO_INCREMENT PRIMARY KEY,
    namelab varchar(50) NOT NULL,
    codelab varchar(5)
);

-- Creación de la tabla unitmeasurement
CREATE TABLE unitmeasurement (
    idum int AUTO_INCREMENT PRIMARY KEY,
    nameum varchar(50) NOT NULL
);

-- Creación de la tabla farmacymedicine
CREATE TABLE farmacymedicine (
    idfarmacy int4,
    idmedicinetafrm int4,
    price float8,
    PRIMARY KEY (idfarmacy, idmedicinetafrm),
    FOREIGN KEY (idfarmacy) REFERENCES farmacy (idfarmacy),
    FOREIGN KEY (idmedicinetafrm) REFERENCES medicine (id)
);

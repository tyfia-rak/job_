CREATE TABLE Company (
    IdCompany INT PRIMARY KEY,
    CompanyName VARCHAR(250),
    Address VARCHAR(250)
);

CREATE TABLE Post (
    IdPost INT PRIMARY KEY,
    DatePost DATE,
    TitlePost VARCHAR(250),
    DescriptionPost VARCHAR(1000),
    responsibility VARCHAR(250),
    requirement VARCHAR(250),
    Salary Float,
    IdCompany INT,
    FOREIGN KEY (IdCompany) REFERENCES Company(IdCompany)
);

CREATE TABLE Applyer (
    id_applyer SERIAL PRIMARY KEY,
    name VARCHAR(50),
    age INT,
    email VARCHAR(150),
    phoneNumber VARCHAR(20),
    applying_date DATE,
    IdPost INT,
    FOREIGN KEY (IdPost) REFERENCES Post(IdPost)
);
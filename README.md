Mokymosi įstaigos valdymo sistema (pirmas etapas)
(REIKALAVIMAI NE GALUTINIAI IR GALI KEISTIS)

PROJEKTO REIKALAVIMAI:

    Projekto kodas saugomas github'e
    Projektui valdyti naudojamas maven
    Visa programos veikimo metu sukurta informacija saugoma failuose
    Programos logika padengta unit testais
    Vartotojo sąsajai naudojama konsolė

FUNKCIONALUMO REIKALAVIMAI:

    Prie sistemos galima prisijungti suvedus username ir password.

    Pirmą kartą paleidus programą turi egzistuoti vartotojas admin su slaptažodžiu admin, kuris turėtų admin rolę ir galėtų atlikti visas operacijas programoje.

    Sistemoje naujus vartotojus gali užsiregistruoti tik vartotojai su admin role

  įvedę:
    firstName, secondName, password, userName
  pasirinkę:
    rolę iš admin, lecturer, student

Naujam vartotojui automatiškai priskiriamas unikalus numeris ID (galima generuoti savo arba pnaudoti UUID).

    Vartotojai turintys rolę lecturer arba student gali turėti ir keisti, pridėti informaciją apie save

    userName
    firstName
    lastName
    personalNumber
    dateOfBirth
    email
    mobileNumber
    gender
    address
    runningCourses (kursai, kuriuos veda)
    

    Vartotojai turintys rolę admin gali
        pridėti/ištrinti/pakeisti course

      code
      tittle
      desciption
      startDate
      credit
      lecturerId

        pamatyti visų studentų sąrašą
        pamatyti visų kursų sąrašą

    Vartotojai su student role gali:
        matyti visų kursų sąrašą
        matyti užsiregistruotų kursų sąrašą
        užsiregistruoti į kursą kai:
            kurso startDate yra ankstenė už dabatinę
            bendra užseregistuotų kursų kreditų suma mažesnė us 12

    Vartotojai su lecturer role gali:
        pridėti course (automatiškai priskiriamas jam)/
        ištrinti/pakeisti jam priklausančius kursus

      code
      tittle
      desciption
      startDate
      credit
      lecturerId

        matyti jam priskirtus kursus
        matyti į kursą užsiregistravusius studentus

# Debut des ennuis  

Nous allons donc représenter les entités User et Media ainsi que leurs association sous la forme d'une collection dans le monde objet.  
La principale problématique est lors de la manipulation d'un object User. Les Media associés soivent être correctement interprétés que ce soit en écriture ou en lecture.  

Nous allons essayer de représenter cette association dans notre hierarchie de classe.  
Pour cela nous devons réalisés un pont entre le monde Objet et le monde Relation en écrivant des méthode permettant de créer des User et Media à partir de ResultSet représentant un ensemble de tuples de base de donnée.

Dans un premier temps concentrons nous sur la récupération d'une entité User à partir de la base de données.
?[Etapes à suivre]
-[x] UserDao.findByFirstName -> Ecrire la requete pour recuperer un utilisateur par nom
-[x] UserDao.UserMapper.mapRow : Ecrire le RowMapper permettant de convertir un ResultSet en un User

@[Mapping User]({"stubs": ["src/main/java/fr/ccavalier/hibernate/course/mapping/UserDao.java","src/main/java/fr/ccavalier/hibernate/course/mapping/User.java","src/test/resources/mapping/create-db.sql","src/test/resources/mapping/insert-data.sql"],"command": "fr.ccavalier.hibernate.course.mapping.UserDaoTest#testFindByName", "layout":"aside"})

Nous avons écrit une requete SQL qui a remonté un tupe de la table User et qui a été converti en un objet User dans notre application.  
Mais la table User possède une association vers des Media qui correspond à l'ensemble des coordonnées pour un utilisateur et nous voulons récupérer cette liste lors d'un appel à la méthode findByFirstName.

?[Etapes à suivre]
-[x] User.Media : Creer la classe Media comme représentation de la table media
-[x] User : Ajouter un attribut contacts qui est une liste de media
-[x] User : Ajouter les getter/setter pour le nouvelle attribut contacts sur User
-[x] UserDao.MediaMapper.mapRow : Ecrire le RowMapper permettant de convertir un ResultSet en un Media
-[x] UserDao.findByFirstName : Mapper les valeurs correctes au media de l'objet User remonté dans la requete

@[Mapping association]({"stubs": ["src/main/java/fr/ccavalier/hibernate/course/mapping/UserDao.java","src/main/java/fr/ccavalier/hibernate/course/mapping/User.java","src/test/resources/mapping/create-db.sql","src/test/resources/mapping/insert-data.sql"],"command": "fr.ccavalier.hibernate.course.mapping.UserDaoTest#testMediaMappingOnFindByName", "layout":"aside"})


Maintenant, au tour de l'insertion de donnée.

?[Etapes à suivre]
- [x] UserDao.add : Completez la méthode pour insérer un User

@[Insert User]({"stubs": ["src/main/java/fr/ccavalier/hibernate/course/mapping/UserDao.java","src/main/java/fr/ccavalier/hibernate/course/mapping/User.java","src/test/resources/mapping/create-db.sql","src/test/resources/mapping/insert-data.sql"],"command": "fr.ccavalier.hibernate.course.mapping.UserDaoTest#testAddUser", "layout":"aside"})
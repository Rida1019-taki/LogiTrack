# LogiTrack API

## 1. Nom du projet

**Nom du projet :** LogiTrack API – Système de Gestion des Commandes Logistiques

---

# 2. Présentation du projet

LogiTrack API est une API REST développée avec Spring Boot permettant de gérer les clients, les produits et les commandes d'une entreprise logistique. Elle s'adresse aux entreprises souhaitant centraliser la gestion de leurs opérations de vente et de stockage. Son objectif principal est de fournir une solution backend simple, organisée et performante pour suivre les commandes, gérer les stocks et faciliter l'accès aux données via des endpoints REST.

---

# 3. Problématique

Le problème identifié est que la gestion des commandes et des stocks peut devenir complexe lorsque les informations des clients, des produits et des commandes sont dispersées dans plusieurs outils.

La solution proposée permet de centraliser la gestion logistique grâce à une API REST offrant des fonctionnalités de création, consultation, suppression et recherche avancée des données.

---

# 4. Fonctionnalités principales

- Gérer les clients (ajouter, consulter, lister et supprimer).
- Gérer les produits disponibles dans l'entrepôt.
- Créer et consulter les commandes.
- Ajouter des produits aux commandes.
- Modifier le statut des commandes.
- Rechercher les commandes par client.
- Rechercher les produits par catégorie.
- Rechercher les produits selon leur prix.
- Afficher les produits avec un stock faible.
- Calculer le nombre total de commandes.
- Identifier le produit le plus commandé.

---

# 5. Technologies utilisées

| Technologie | Utilisation dans le projet |
|-------------|----------------------------|
| Java 21 | Développement de l'application backend |
| Spring Boot | Création de l'API REST |
| Spring Web | Création des contrôleurs REST |
| Spring Data JPA | Gestion de la persistance des données |
| Hibernate | Mapping Objet-Relationnel |
| MySQL | Stockage des données |
| Maven | Gestion des dépendances |
| Postman | Test des endpoints API |
| Git & GitHub | Gestion du code source |

Nous avons utilisé **Spring Boot** pour développer l'architecture backend de l'application.

Nous avons utilisé **Spring Web** pour créer les différents endpoints REST.

Nous avons utilisé **Spring Data JPA** afin de simplifier l'accès aux données et gérer les relations entre les entités.

Nous avons utilisé **MySQL** comme système de gestion de base de données.

---

# 6. Installation et lancement

## 6.1 Prérequis

Pour utiliser ce projet, vous devez disposer de :

- Java 17 ou 21
- Maven
- MySQL
- Postman
- Git
- IntelliJ IDEA ou Visual Studio Code

---

## 6.2 Cloner le dépôt

```bash
git clone https://github.com/VOTRE_COMPTE/logitrack-api.git
```

---

## 6.3 Ouvrir le dossier

```bash
cd logitrack-api
```

---

## 6.4 Installer les dépendances

```bash
mvn clean install
```

---

## 6.5 Variables d'environnement

Configurer le fichier `application.properties` :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/logitrack
spring.datasource.username=root
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 6.6 Lancer le projet

```bash
mvn spring-boot:run
```

---

## 6.7 Tester l'API

L'API sera accessible sur :

```
http://localhost:8080
```

Les endpoints peuvent être testés avec :

```
Postman
```

### Point de vigilance

- Vérifier que MySQL est démarré.
- Vérifier les informations de connexion à la base de données.
- Tester chaque endpoint avec les méthodes HTTP correspondantes.

---

# 7. Captures d'écran

## Capture 1

### Titre

Tests API avec Postman

```md
![Postman](images/postman.png)
```

### Explication

Cette capture montre les tests des endpoints REST pour la gestion des clients, produits et commandes.

---

## Capture 2

### Titre

Base de données MySQL

```md
![Database](images/database.png)
```

### Explication

Cette capture montre les tables générées pour les différentes entités du système logistique.

---

# 8. Contribution personnelle

Ce projet a été réalisé individuellement.

J'ai conçu l'architecture backend, créé les entités Client, Produit, Commande et LigneCommande, développé les repositories, services et contrôleurs REST.

J'ai également implémenté les fonctionnalités CRUD, les recherches avec Derived Queries et les requêtes personnalisées avec `@Query`, puis testé les différents endpoints avec Postman.

---

# 9. Difficultés rencontrées

## Difficulté 1

### Problème rencontré

La gestion des relations entre les entités Commande, Client et LigneCommande.

### Recherches / Tests

J'ai étudié les relations JPA comme `@OneToMany`, `@ManyToOne` et la gestion des clés étrangères.

### Solution

J'ai configuré correctement les relations entre les entités afin de permettre la création de commandes associées aux clients et aux produits.

### Ce que j'ai appris

J'ai appris à modéliser une base de données relationnelle avec JPA et Hibernate.

### Texte final

J'ai rencontré une difficulté lors de la conception des relations entre les entités. Après plusieurs recherches sur JPA et Hibernate, j'ai configuré correctement les associations entre Client, Commande et Produit. Cette expérience m'a permis d'améliorer mes compétences en modélisation de données.

---

## Difficulté 2

### Problème rencontré

Créer des recherches spécifiques avec Derived Query et `@Query`.

### Recherches / Tests

J'ai étudié la syntaxe des méthodes Spring Data JPA et l'utilisation des requêtes JPQL.

### Solution

J'ai créé des méthodes personnalisées permettant de rechercher les commandes par client, les produits par catégorie et d'obtenir des statistiques comme le produit le plus commandé.

### Ce que j'ai appris

J'ai appris à exploiter les fonctionnalités avancées de Spring Data JPA pour créer des recherches efficaces.

---

# 10. Améliorations possibles

Dans une prochaine version, je pourrais :

- ajouter une authentification avec Spring Security et JWT ;
- intégrer la pagination et le tri des résultats ;
- ajouter une gestion des rôles utilisateurs ;
- créer une interface frontend React pour consommer l'API.

### Conclusion

Ces améliorations permettraient de rendre l'application plus sécurisée, plus complète et plus adaptée à un environnement professionnel.

---

# ✅ Checklist finale

## Présentation

- [x] Le nom du projet est clair.
- [x] Le projet est présenté en 3 à 5 lignes.
- [x] Le public cible est identifié.
- [x] Le besoin est expliqué.
- [x] L'objectif est précisé.

## Fonctionnalités

- [x] Les fonctionnalités principales sont présentes.
- [x] Chaque fonctionnalité commence par un verbe.
- [x] Elles correspondent aux besoins du projet.

## Technologies

- [x] Les technologies sont indiquées.
- [x] Leur rôle est expliqué.

## Installation

- [x] Les prérequis sont présents.
- [x] Les commandes fonctionnent.
- [x] La configuration MySQL est indiquée.
- [x] Les outils de test sont précisés.

## Captures

- [ ] Ajouter les captures Postman et MySQL.

## Contribution

- [x] La contribution personnelle est clairement expliquée.

## Difficultés

- [x] Les difficultés sont décrites.
- [x] Les solutions sont présentées.
- [x] Les apprentissages sont expliqués.

## Améliorations

- [x] Les améliorations sont réalistes.

---

# Validation finale

Une personne qui découvre ce projet peut comprendre :

- l'objectif de l'API LogiTrack ;
- les entités principales ;
- les fonctionnalités disponibles ;
- les technologies utilisées ;
- la manière de lancer l'application ;
- les recherches avancées réalisées avec Spring Data JPA ;
- les améliorations prévues.
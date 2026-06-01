# LogiTrack - API de Gestion Logistique

## Contexte du projet
La société LogiTrack Solutions souhaite développer une API REST permettant de gérer les commandes logistiques entre les clients et l’entrepôt, l’application sera développée avec Spring Boot et Spring Data JPA, et toutes les fonctionnalités devront être accessibles via des endpoints REST.

## Modélisation des données
L’application devra gérer les entités suivantes.

### Client : représente un client qui passe des commandes.
**Attributs :**
- id
- nom
- email
- téléphone
- ville

### Produit : représente un produit disponible dans l’entrepôt.
**Attributs :**
- id
- nom
- catégorie
- prix
- quantitéStock

### Commande : représente une commande passée par un client.
**Attributs :**
- id
- dateCommande
- statut (EN_ATTENTE, EXPEDIEE, LIVREE)

### LigneCommande : représente un produit présent dans une commande.
**Attributs :**
- id
- quantité

## Fonctionnalités attendues (API REST)

### Gestion des clients
- **Ajouter un client :** POST `/api/clients`
- **Afficher tous les clients :** GET `/api/clients`
- **Consulter un client :** GET `/api/clients/{id}`
- **Supprimer un client :** DELETE `/api/clients/{id}`

### Gestion des produits
- **Ajouter un produit :** POST `/api/products`
- **Afficher tous les produits :** GET `/api/products`
- **Consulter un produit :** GET `/api/products/{id}`
- **Supprimer un produit :** DELETE `/api/products/{id}`

### Gestion des commandes
- **Créer une commande pour un client :** POST `/api/orders`
- **Ajouter un produit à une commande :** POST `/api/orders/{orderId}/products`
- **Afficher toutes les commandes :** GET `/api/orders`
- **Consulter une commande :** GET `/api/orders/{id}`
- **Modifier le statut d’une commande :** PUT `/api/orders/{id}/status`, statuts possibles : `EN_ATTENTE`, `EXPEDIEE`, `LIVREE`

### Fonctionnalités avec Derived Query
- **Rechercher les commandes d’un client :** GET `/api/orders/client/{clientId}`
- **Rechercher les produits par catégorie :** GET `/api/products/category/{category}`
- **Rechercher les produits avec un prix inférieur :** GET `/api/products/price/{price}`

### Fonctionnalités avec @Query
- **Afficher les produits avec stock faible :** GET `/api/products/low-stock`
- **Nombre total de commandes :** GET `/api/orders/count`
- **Produit le plus commandé :** GET `/api/statistics/top-product`

## Technologies obligatoires
Le projet doit utiliser :
- Java 17/21
- Spring Boot
- Spring Web
- Spring Data JPA
- Maven
- Base de données MySQL
- Postman pour tester les APIs

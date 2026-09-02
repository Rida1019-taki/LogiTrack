CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    role VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS clients (
    id_client BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    email VARCHAR(255),
    telefone VARCHAR(255),
    ville VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS produits (
    id_produit BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255),
    categorie VARCHAR(255),
    prix DOUBLE NOT NULL,
    quantity INT NOT NULL
);

CREATE TABLE IF NOT EXISTS commandes (
    id_commande BIGINT AUTO_INCREMENT PRIMARY KEY,
    date_commande DATE,
    statut VARCHAR(255),
    id_client BIGINT NOT NULL,
    CONSTRAINT fk_commande_client FOREIGN KEY (id_client) REFERENCES clients(id_client) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ligne_commandes (
    id_ligne_commande BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantity INT NOT NULL,
    id_produit BIGINT NOT NULL,
    id_commande BIGINT NOT NULL,
    CONSTRAINT fk_ligne_produit FOREIGN KEY (id_produit) REFERENCES produits(id_produit) ON DELETE CASCADE,
    CONSTRAINT fk_ligne_commande FOREIGN KEY (id_commande) REFERENCES commandes(id_commande) ON DELETE CASCADE
);

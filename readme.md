# Récupération du projet

le projet se trouve sous le répo git : https://github.com/ipm2016/Ekwateur.git

## Test

Le projet est exécuté en utilisant le port 8080.

Un fichier data.sql se trouve sous src/main/resources avec des requêtes sql pour initié les différents tables.

Le service expose deux endpoints:

* /billing/particulier/{id} , pour la facturation des particuliers
* /billing/professionnel/{id} , pour la facturation des professionnels

{id} est l'identifiant technique du client.

pour chaque reqêute il faut spécifier un ensemble d'informations:

* l'energy concerné (GAZ | ELECTRICITE)
* le mois
* l'année
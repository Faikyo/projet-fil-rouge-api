# Projet Fil Rouge — API Java / Spring Boot
**Sujet : API de gestion de commandes et de facturation pour une plateforme de services**  
Back-end uniquement (REST). Objectif : pratiquer Java POO, design patterns, clean code, tests, puis Spring Boot.

---

## 1) Contexte
Une entreprise vend des **services** (maintenance, location, prestations numériques).  
Elle souhaite une **API** pour gérer :
- Utilisateurs (clients / admins)
- Catalogue de services
- Commandes (panier, statuts, total)
- Factures (créées au paiement)

---

## 2) Objectifs pédagogiques
### Compétences Java / POO
- Encapsulation, héritage, polymorphisme, composition
- Gestion des exceptions métier
- Collections / Génériques
- SOLID (SRP, OCP, LSP, ISP, DIP)
- Code lisible, maintenable, testable

### Design patterns (ciblés et justifiés)
- **MVC** (architecture globale)
- **Repository** (accès données)
- **DTO** (contrats API)
- **Factory** (création contrôlée d’objets métier)
- **Strategy** (calcul du total / politique de pricing)
- **Builder** (construction de facture)
- **Singleton** (avec précaution, uniquement si utile / justifié)

### Spring Boot / Back-end
- API REST (Spring Web)
- Validation (`@Valid`)
- Gestion d’erreurs globale (exception handler)
- JPA / transactions (`@Transactional`)
- Tests unitaires & intégration (JUnit, Mockito)
- Qualité (structure, README, commits)

---

## 3) Périmètre V1 (obligatoire)
### Utilisateurs
- CRUD minimal (création, modification)
- Activer / désactiver
- Rôles : `CLIENT`, `ADMIN`

### Services (catalogue)
- Création, modification
- Activer / désactiver
- Prix > 0

### Commandes
- Créer une commande (pour un client actif)
- Ajouter / retirer un service à une commande
- Total calculé automatiquement
- Statuts : `CREATED`, `VALIDATED`, `PAID`, `CANCELLED`

### Facturation
- Générer une **facture** lors du paiement
- Facture liée à la commande, non modifiable

---

## 4) Hors V1 (optionnel / plus tard)
- Auth JWT / refresh token
- Gestion avancée des droits (RBAC)
- Notifications / emails
- Export PDF facture
- Observabilité avancée (metrics, tracing)

---

## 5) Règles métier (Business Rules)
1. Un utilisateur **inactif** ne peut pas créer de commande.
2. Un service **inactif** ne peut pas être ajouté à une commande.
3. Une commande doit contenir **au moins 1 item**.
4. Le total d’une commande = somme(`prixUnitaire × quantité`) de chaque item.
5. Une commande au statut **PAID** ne peut plus être modifiée.
6. Une commande au statut **CANCELLED** est définitive.
7. Une facture est créée **uniquement** quand la commande passe à **PAID**.
8. Une facture est **non modifiable** une fois générée.

---

## 6) Modèle de domaine (V1)
### Entités / Concepts
- `User { id, email, role, active }`
- `ServiceCatalogItem { id, name, price, active }`
- `Order { id, customerId, status, items, total }`
- `OrderItem { serviceId, unitPrice, quantity, lineTotal }`
- `Invoice { id, orderId, issuedAt, amount }`

### Enums
- `Role = CLIENT | ADMIN`
- `OrderStatus = CREATED | VALIDATED | PAID | CANCELLED`

---

## 7) Architecture (MVC + séparation claire)
Packages (cible) :
src/main/java/com/furkan/filrouge
controller/
service/
repository/
domain/
dto/
exception/
config/


Principes :
- Le **Controller** gère HTTP (DTO, codes, validation) — pas de logique métier.
- Le **Service** contient la logique métier (règles, orchestration).
- Le **Domain** porte les comportements (calcul total, transitions d’état).
- Le **Repository** gère l’accès aux données.
- Les **DTO** protègent l’API (pas d’Entity JPA exposée).

---

## 8) API (Endpoints V1 — cible)
> Les routes exactes peuvent évoluer, mais l’intention reste la même.

### Users
- `POST /api/users`
- `PATCH /api/users/{id}`
- `PATCH /api/users/{id}/activate`
- `PATCH /api/users/{id}/deactivate`
- `GET /api/users/{id}`

### Services
- `POST /api/services`
- `PATCH /api/services/{id}`
- `PATCH /api/services/{id}/activate`
- `PATCH /api/services/{id}/deactivate`
- `GET /api/services`
- `GET /api/services/{id}`

### Orders
- `POST /api/orders` (créer pour un client)
- `POST /api/orders/{orderId}/items` (ajouter item)
- `DELETE /api/orders/{orderId}/items/{itemId}` (retirer)
- `PATCH /api/orders/{orderId}/status` (transition)
- `GET /api/orders/{orderId}`
- `GET /api/orders` (admin = tous, client = ses commandes)

### Invoices
- `GET /api/invoices/{id}`
- `GET /api/orders/{orderId}/invoice`

---

## 9) Qualité attendue
- Nommage clair, classes courtes, responsabilité unique
- Exceptions métier explicites (`BusinessException`, etc.)
- Validation cohérente + erreurs uniformes
- Tests :
  - unitaires sur services / domaine
  - intégration API (minimum)

---

## 10) Backlog (User Stories V1)
- US1 — En tant qu’admin, je peux créer un service.
- US2 — En tant qu’admin, je peux activer/désactiver un service.
- US3 — En tant que client actif, je peux créer une commande.
- US4 — En tant que client, je peux ajouter/supprimer des services à ma commande.
- US5 — En tant que client, je peux valider puis payer une commande.
- US6 — En tant que système, je génère une facture lors du paiement.
- US7 — En tant qu’admin, je peux consulter toutes les commandes.
- US8 — En tant que client, je peux consulter mes commandes et ma facture.

---

## 11) Stack technique
- Java 17+
- Maven
- Spring Boot
- Spring Web
- Spring Data JPA
- Validation
- H2 (dev) → PostgreSQL (optionnel)
- JUnit 5, Mockito

---

## 12) Lancer le projet
### Prérequis
- Java 17 installé
- Maven (ou wrapper Maven si ajouté)

### Exécution
```bash
mvn spring-boot:run

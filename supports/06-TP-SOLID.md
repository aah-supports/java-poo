# TP SOLID - Systeme de controle d'acces (Java)

## Contexte
Vous developpez un systeme de controle d'acces pour un batiment.
Le diagramme UML de reference est disponible ici : `images/uml_project.png`.

Objectif : implementer le modele puis refactorer le code pour appliquer les 5 principes SOLID avec de bonnes pratiques Java.

## Objectifs pedagogiques
- Appliquer `SRP`, `OCP`, `LSP`, `ISP`, `DIP` sur un cas concret.
- Organiser un mini projet Java en couches simples (`domain`, `application`, `infrastructure`).
- Produire un code testable et evolutif.

## Modele metier de base
Classes du diagramme :
- `Building(name, doors)`
- `Door(id, requiredLevel)`
- `Badge(level)`
- `Employee(name, badge)`
- `PermissionService.hasAccess(Employee, Door)`
- `LogService.record(String)`
- `AccessController.check(Employee, Door)`

Relations :
- `Employee` compose `Badge`.
- `Building` agrege des `Door`.
- `AccessController` orchestre les services.

## Enonce du TP
### Partie 1 - Implementation minimale
1. Creer les classes metier (`Building`, `Door`, `Badge`, `Employee`).
2. Implementer `PermissionService` avec la regle simple :
`employee.badge.level >= door.requiredLevel`.
3. Implementer `LogService` en memoire (liste de chaines).
4. Implementer `AccessController` qui :
- demande la decision a `PermissionService`,
- enregistre le resultat dans `LogService`,
- retourne `true/false`.

### Partie 2 - Appliquer SOLID
#### S - Single Responsibility Principle
- Verifier qu'une classe n'a qu'une seule raison de changer.
- `AccessController` ne doit pas contenir la logique de permission.
- `LogService` ne doit pas calculer les droits d'acces.

#### O - Open/Closed Principle
- Introduire une interface `AccessRule` :
`boolean allows(Employee e, Door d)`.
- Transformer `PermissionService` pour evaluer une liste de regles.
- Ajouter une nouvelle regle sans modifier `AccessController`.

Exemples de regles a creer :
- `BadgeLevelRule` (obligatoire),
- `BlacklistRule` (optionnel),
- `TimeWindowRule` (optionnel).

#### L - Liskov Substitution Principle
- Toute implementation de `AccessRule` doit etre substituable.
- Aucune regle ne doit casser le contrat (`true`/`false` coherent, pas d'effet de bord inattendu).

#### I - Interface Segregation Principle
- Eviter les interfaces "fat".
- Separer les besoins de journalisation :
- `AccessLogger` -> `record(String entry)`
- `AccessLogReader` -> `List<String> getLogs()`

#### D - Dependency Inversion Principle
- `AccessController` depend d'abstractions, pas de classes concretes.
- Injecter les dependances par constructeur (`PermissionChecker`, `AccessLogger`).
- Interdire les `new` directs dans la logique applicative.

### Partie 3 - Qualite et robustesse
- Champs immuables `private final` quand possible.
- Validation des arguments dans les constructeurs :
- `id`/`name` non vides,
- `requiredLevel` et `badge level` >= 0.
- Encapsulation stricte (pas d'acces direct aux attributs).
- Noms explicites et constantes pour eviter la magie.

## Tests attendus (JUnit 5)
Ecrire des tests unitaires couvrant au minimum :
1. Acces autorise quand le niveau badge est suffisant.
2. Acces refuse sinon.
3. Une tentative cree exactement une entree de log.
4. L'ajout d'une nouvelle `AccessRule` ne casse pas le code existant.
5. Les validations de constructeur levent les exceptions attendues.

## Structure de projet recommandee
```text
src/
  main/java/
    domain/
      Badge.java
      Employee.java
      Door.java
      Building.java
    application/
      AccessController.java
      PermissionService.java
      AccessRule.java
      PermissionChecker.java
      AccessLogger.java
      AccessLogReader.java
    infrastructure/
      InMemoryLogService.java
      rules/
        BadgeLevelRule.java
        BlacklistRule.java
        TimeWindowRule.java

  test/java/
    application/
      AccessControllerTest.java
      PermissionServiceTest.java
    domain/
      EmployeeTest.java
      DoorTest.java
```

## Critere d'evaluation (grille simple)
- Conception SOLID respectee : 40%
- Qualite du code (noms, immutabilite, validation) : 25%
- Qualite des tests : 25%
- Organisation du projet et lisibilite : 10%

## Bonus
- Ajouter un export des logs vers un format CSV (via une abstraction dediee).
- Ajouter un niveau de severite (`INFO`, `WARN`) pour les logs d'acces.
- Ajouter une regle composite configurable au lancement.

## Livrables
- Code source compile et executable.
- Tests unitaires verts.
- Court `README` (5-10 lignes) expliquant les choix SOLID.

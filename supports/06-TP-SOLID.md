# TP SOLID - Système de contrôle d'accès (Java)

## Contexte
Vous développez un système de contrôle d'accès pour un bâtiment.
Le diagramme UML de référence est disponible ici : `images/uml_project.png`.

Objectif : implémenter le modèle puis refactorer le code pour appliquer les 5 principes SOLID avec de bonnes pratiques Java.

## Objectifs pédagogiques
- Appliquer `SRP`, `OCP`, `LSP`, `ISP`, `DIP` sur un cas concret.
- Organiser un mini projet Java en couches simples (`domain`, `application`, `infrastructure`).
- Produire un code testable et évolutif.

## Modèle métier de base
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
- `Building` agrège des `Door`.
- `AccessController` orchestre les services.

## Énoncé du TP
### Partie 1 - Implémentation minimale
1. Créer les classes métier (`Building`, `Door`, `Badge`, `Employee`).
2. Implémenter `PermissionService` avec la règle simple :
`employee.badge.level >= door.requiredLevel`.
3. Implémenter `LogService` en mémoire (liste de chaînes).
4. Implémenter `AccessController` qui :
- demande la décision à `PermissionService`,
- enregistre le résultat dans `LogService`,
- retourne `true/false`.

### Partie 2 - Appliquer SOLID
#### S - Single Responsibility Principle
- Vérifier qu'une classe n'a qu'une seule raison de changer.
- `AccessController` ne doit pas contenir la logique de permission.
- `LogService` ne doit pas calculer les droits d'accès.

#### O - Open/Closed Principle
- Introduire une interface `AccessRule` :
`boolean allows(Employee e, Door d)`.
- Transformer `PermissionService` pour évaluer une liste de règles.
- Ajouter une nouvelle règle sans modifier `AccessController`.

Exemples de règles à créer :
- `BadgeLevelRule` (obligatoire),
- `BlacklistRule` (optionnel),
- `TimeWindowRule` (optionnel).

#### L - Liskov Substitution Principle
- Toute implémentation de `AccessRule` doit être substituable.
- Aucune règle ne doit casser le contrat (`true`/`false` cohérent, pas d'effet de bord inattendu).

#### I - Interface Segregation Principle
- Éviter les interfaces "fat".
- Séparer les besoins de journalisation :
- `AccessLogger` -> `record(String entry)`
- `AccessLogReader` -> `List<String> getLogs()`

#### D - Dependency Inversion Principle
- `AccessController` dépend d'abstractions, pas de classes concrètes.
- Injecter les dépendances par constructeur (`PermissionChecker`, `AccessLogger`).
- Interdire les `new` directs dans la logique applicative.

### Partie 3 - Qualité et robustesse
- Champs immuables `private final` quand possible.
- Validation des arguments dans les constructeurs :
- `id`/`name` non vides,
- `requiredLevel` et `badge level` >= 0.
- Encapsulation stricte (pas d'accès direct aux attributs).
- Noms explicites et constantes pour éviter la magie.

## Tests attendus (JUnit 5)
Écrire des tests unitaires couvrant au minimum :
1. Accès autorisé quand le niveau badge est suffisant.
2. Accès refusé sinon.
3. Une tentative crée exactement une entrée de log.
4. L'ajout d'une nouvelle `AccessRule` ne casse pas le code existant.
5. Les validations de constructeur lèvent les exceptions attendues.

## Structure de projet recommandée
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

## Critère d'évaluation (grille simple)
- Conception SOLID respectée : 40%
- Qualité du code (noms, immutabilité, validation) : 25%
- Qualité des tests : 25%
- Organisation du projet et lisibilité : 10%

## Bonus
- Ajouter un export des logs vers un format CSV (via une abstraction dédiée).
- Ajouter un niveau de sévérité (`INFO`, `WARN`) pour les logs d'accès.
- Ajouter une règle composite configurable au lancement.

## Livrables
- Code source compilé et exécutable.
- Tests unitaires verts.
- Court `README` (5-10 lignes) expliquant les choix SOLID.

# ✅ 1. Modèle UML (texte)

```
Employee *–––> Badge        (composition forte)
Building o–––> Door         (agrégation)

AccessController ––> LogService
AccessController ––> PermissionService
```

* **Employee** possède un **Badge** → composition.
* **Building** contient des **Door**, mais elles peuvent exister indépendamment → agrégation.
* **AccessController** décide si l’accès est autorisé.
* **PermissionService** encapsule la logique métier.
* **LogService** mémorise seulement les tentatives.

---

# ✅ 2. Code refactorisé

## 🔹 Employee + Badge (composition)

```java
public class Badge {
    private int level;

    public Badge(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
```

```java
public class Employee {
    private String name;
    private Badge badge; // composition : appartient pleinement à l'employé

    public Employee(String name, int badgeLevel) {
        this.name = name;
        this.badge = new Badge(badgeLevel);
    }

    public String getName() {
        return name;
    }

    public Badge getBadge() {
        return badge;
    }
}
```

---

## 🔹 Door + Building (agrégation)

```java
public class Door {
    private String id;
    private int requiredLevel;

    public Door(String id, int requiredLevel) {
        this.id = id;
        this.requiredLevel = requiredLevel;
    }

    public String getId() {
        return id;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }
}
```

```java
import java.util.List;

public class Building {
    private String name;
    private List<Door> doors; // agrégation : les portes existent indépendamment

    public Building(String name, List<Door> doors) {
        this.name = name;
        this.doors = doors;
    }

    public List<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }
}
```

---

## 🔹 PermissionService (SRP : logique métier d’accès)

```java
public class PermissionService {

    public boolean hasAccess(Employee e, Door d) {
        return e.getBadge().getLevel() >= d.getRequiredLevel();
    }
}
```

---

## 🔹 LogService (SRP : journalisation interne, sans console ni fichier)

```java
import java.util.ArrayList;
import java.util.List;

public class LogService {

    private List<String> logs = new ArrayList<>();

    public void record(String entry) {
        logs.add(entry);
    }

    public List<String> getLogs() {
        return logs;
    }
}
```

---

## 🔹 AccessController (coordination, pas de logique métier)

```java
public class AccessController {

    private PermissionService permissionService;
    private LogService logService;

    public AccessController(PermissionService permissionService, LogService logService) {
        this.permissionService = permissionService;
        this.logService = logService;
    }

    public boolean check(Employee e, Door d) {

        boolean ok = permissionService.hasAccess(e, d);

        String entry = e.getName() +
                (ok ? " -> accès autorisé à " : " -> accès refusé à ") +
                d.getId();

        logService.record(entry);

        return ok;
    }
}
```

---

# ✅ 3. Exemple d’utilisation (sans console)

```java
import java.util.List;

public class App {

    public static void main(String[] args) {

        // Composition
        Employee alice = new Employee("Alice", 3);

        // Agrégation
        Door d1 = new Door("D-101", 2);
        Door d2 = new Door("D-102", 5);
        Building building = new Building("HQ", List.of(d1, d2));

        // Services
        PermissionService permissionService = new PermissionService();
        LogService logService = new LogService();
        AccessController controller =
                new AccessController(permissionService, logService);

        // Scénario
        controller.check(alice, d1); // autorisé
        controller.check(alice, d2); // refusé

        // Les logs existent en mémoire mais ne sont pas affichés
        List<String> history = logService.getLogs();
        // Utilisables pour tests / assertions
    }
}
```

---

# 🎯 Résultat

* Architecture claire et modulaire
* SRP appliqué à chaque classe
* Composition Employee/Badge
* Agrégation Building/Doors
* Log interne propre (aucun effet de bord)
* Exemple applicatif cohérent (système d’accès sécurisé)

---

Si tu veux, je peux aussi produire :

* un diagramme UML visuel,
* une version avancée avec interfaces,
* une variante orientée DDD ou hexagonal.

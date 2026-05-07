# Cours Java - Injection de dependance (DI)

## 1) Definition
L'injection de dependance consiste a **fournir les dependances d'une classe depuis l'exterieur** au lieu de les creer avec `new` a l'interieur de la classe.

Objectif : reduire le couplage et rendre le code plus testable et evolutif.

## 2) Couplage fort (a eviter)

```java
public class OrderService {
    private final EmailService emailService = new EmailService();

    public void placeOrder(String customerEmail) {
        // logique metier...
        emailService.send(customerEmail, "Commande confirmee");
    }
}

public class EmailService {
    public void send(String to, String message) {
        System.out.println("Email envoye a " + to + " : " + message);
    }
}
```

Problemes :
- `OrderService` depend directement de `EmailService` concret.
- Difficile de remplacer par SMS, Slack, etc.
- Difficile de tester sans envoyer de vrai message.

## 3) Decoupler avec une interface

```java
public interface NotificationService {
    void send(String to, String message);
}

public class EmailNotificationService implements NotificationService {
    @Override
    public void send(String to, String message) {
        System.out.println("Email envoye a " + to + " : " + message);
    }
}

public class SmsNotificationService implements NotificationService {
    @Override
    public void send(String to, String message) {
        System.out.println("SMS envoye a " + to + " : " + message);
    }
}
```

Ici, le contrat est `NotificationService`. Les implementations sont interchangeables.

## 4) Injection de dependance (constructeur)

```java
public class OrderService {
    private final NotificationService notificationService;

    public OrderService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void placeOrder(String customerContact) {
        // logique metier...
        notificationService.send(customerContact, "Commande confirmee");
    }
}
```

Creation des objets (composition root) :

```java
public class Main {
    public static void main(String[] args) {
        NotificationService notifier = new EmailNotificationService();
        OrderService orderService = new OrderService(notifier);

        orderService.placeOrder("alice@example.com");
    }
}
```

Pour changer de canal :

```java
NotificationService notifier = new SmsNotificationService();
OrderService orderService = new OrderService(notifier);
```

Aucune modification de `OrderService`.

## 5) Pourquoi c'est mieux
- Faible couplage : dependance a une abstraction.
- Meilleure testabilite : injection d'un fake/mocking.
- Evolutivite : ajout d'implementations sans casser le code metier.
- Aligne avec SOLID : OCP + DIP.

## 6) Types d'injection
- Injection par constructeur : recommandee (dependances obligatoires).
- Injection par setter : utile pour dependances optionnelles.
- Injection par champ : a eviter hors framework (moins explicite).

## 7) Exemple de test simple

```java
public class FakeNotificationService implements NotificationService {
    public String lastTo;
    public String lastMessage;

    @Override
    public void send(String to, String message) {
        this.lastTo = to;
        this.lastMessage = message;
    }
}

// Test
FakeNotificationService fake = new FakeNotificationService();
OrderService service = new OrderService(fake);
service.placeOrder("bob@example.com");

assert "bob@example.com".equals(fake.lastTo);
assert "Commande confirmee".equals(fake.lastMessage);
```

## 8) Resume
Sans DI : `OrderService` cree ses dependances -> couplage fort.
Avec DI : dependances injectees via interface -> code flexible, testable et maintenable.

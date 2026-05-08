# Cours Java - Injection de dépendance (DI)

## 1) Définition
L'injection de dépendance consiste à **fournir les dépendances d'une classe depuis l'extérieur** au lieu de les créer avec `new` à l'intérieur de la classe.

Objectif : réduire le couplage et rendre le code plus testable et évolutif.

## 2) Couplage fort (à éviter)

```java
public class OrderService {
    private final EmailService emailService = new EmailService();

    public void placeOrder(String customerEmail) {
        // logique métier...
        emailService.send(customerEmail, "Commande confirmée");
    }
}

public class EmailService {
    public void send(String to, String message) {
        System.out.println("Email envoyé à " + to + " : " + message);
    }
}
```

Problèmes :
- `OrderService` dépend directement de `EmailService` concret.
- Difficile de remplacer par SMS, Slack, etc.
- Difficile de tester sans envoyer de vrai message.

## 3) Découpler avec une interface

```java
public interface NotificationService {
    void send(String to, String message);
}

public class EmailNotificationService implements NotificationService {
    @Override
    public void send(String to, String message) {
        System.out.println("Email envoyé à " + to + " : " + message);
    }
}

public class SmsNotificationService implements NotificationService {
    @Override
    public void send(String to, String message) {
        System.out.println("SMS envoyé à " + to + " : " + message);
    }
}
```

Ici, le contrat est `NotificationService`. Les implémentations sont interchangeables.

## 4) Injection de dépendance (constructeur)

```java
public class OrderService {
    private final NotificationService notificationService;

    public OrderService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void placeOrder(String customerContact) {
        // logique métier...
        notificationService.send(customerContact, "Commande confirmée");
    }
}
```

Création des objets (composition root) :

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
- Faible couplage : dépendance à une abstraction.
- Meilleure testabilité : injection d'un fake/mocking.
- Évolutivité : ajout d'implémentations sans casser le code métier.
- Aligné avec SOLID : OCP + DIP.

## 6) Types d'injection
- Injection par constructeur : recommandée (dépendances obligatoires).
- Injection par setter : utile pour dépendances optionnelles.
- Injection par champ : à éviter hors framework (moins explicite).

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
assert "Commande confirmée".equals(fake.lastMessage);
```

## 8) Résumé
Sans DI : `OrderService` crée ses dépendances -> couplage fort.
Avec DI : dépendances injectées via interface -> code flexible, testable et maintenable.

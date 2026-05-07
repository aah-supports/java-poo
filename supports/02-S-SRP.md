# SOLID - S (Single Responsibility Principle)

## Objectif
Comprendre le `S` de SOLID : **une classe ne doit avoir qu'une seule raison de changer**.

Une *raison de changer* = un type de changement métier/technique qui impacte la classe.

## Définition
Le principe SRP dit :

- Une classe doit avoir une responsabilité claire.
- Cette responsabilité doit pouvoir se résumer en une phrase.
- Si une classe change pour plusieurs raisons différentes, elle viole SRP.

## Exemple qui viole SRP

```java
public class InvoiceService {
    public double calculateTotal(double amount, double taxRate) {
        return amount + (amount * taxRate);
    }

    public void saveToDatabase(String invoiceJson) {
        System.out.println("INSERT INTO invoices ... " + invoiceJson);
    }

    public void sendEmail(String email, String invoicePdf) {
        System.out.println("Envoi facture a " + email);
    }
}
```

`InvoiceService` a **3 raisons de changer** :

- les règles de calcul changent,
- la persistance (DB) change,
- le système d'email change.

Donc ce n'est pas SRP.

## Exemple conforme SRP

```java
public class InvoiceCalculator {
    public double calculateTotal(double amount, double taxRate) {
        return amount + (amount * taxRate);
    }
}

public class InvoiceRepository {
    public void save(String invoiceJson) {
        System.out.println("INSERT INTO invoices ... " + invoiceJson);
    }
}

public class InvoiceEmailSender {
    public void send(String email, String invoicePdf) {
        System.out.println("Envoi facture a " + email);
    }
}

public class InvoiceApplicationService {
    private final InvoiceCalculator calculator = new InvoiceCalculator();
    private final InvoiceRepository repository = new InvoiceRepository();
    private final InvoiceEmailSender emailSender = new InvoiceEmailSender();

    public void processInvoice(double amount, double taxRate, String invoiceJson, String email, String invoicePdf) {
        double total = calculator.calculateTotal(amount, taxRate);
        repository.save(invoiceJson + " | total=" + total);
        emailSender.send(email, invoicePdf);
    }
}
```

Ici, chaque classe a une responsabilité unique :

- `InvoiceCalculator` : calcul,
- `InvoiceRepository` : persistance,
- `InvoiceEmailSender` : notification,
- `InvoiceApplicationService` : orchestration.

## Comment vérifier SRP rapidement
Avant de valider une classe, pose ces 3 questions :

1. Puis-je décrire son rôle en une phrase claire ?
2. Change-t-elle pour une seule catégorie de raisons ?
3. Puis-je la tester sans dépendances inutiles (DB, mail, UI) ?

Si la réponse est non, il faut probablement découper.

## Résumé
SRP ne veut pas dire "petites classes" à tout prix.
SRP veut dire : **une classe = une responsabilité = une raison de changer**.

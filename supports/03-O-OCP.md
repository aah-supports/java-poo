# SOLID - O (Open/Closed Principle)

## Objectif
Comprendre le `O` de SOLID : **un module doit être ouvert à l'extension, mais fermé à la modification**.

## Définition simple
Quand un nouveau besoin arrive, on doit pouvoir **ajouter du code** plutôt que **modifier du code existant** déjà stable.

Pourquoi :
- modifier du code existant augmente le risque de régression,
- ajouter une nouvelle classe est souvent plus sûr et testable.

## Exemple qui viole OCP

```java
public class DiscountService {
    public double applyDiscount(String customerType, double price) {
        if (customerType.equals("REGULAR")) {
            return price;
        } else if (customerType.equals("STUDENT")) {
            return price * 0.90;
        } else if (customerType.equals("VIP")) {
            return price * 0.80;
        }

        throw new IllegalArgumentException("Type inconnu");
    }
}
```

Problème : à chaque nouveau type (`SENIOR`, `PARTNER`, etc.), il faut modifier la méthode `applyDiscount`.
La classe n'est pas fermée à la modification.

## Exemple conforme OCP

```java
public interface DiscountPolicy {
    double apply(double price);
}

public class RegularDiscount implements DiscountPolicy {
    public double apply(double price) {
        return price;
    }
}

public class StudentDiscount implements DiscountPolicy {
    public double apply(double price) {
        return price * 0.90;
    }
}

public class VipDiscount implements DiscountPolicy {
    public double apply(double price) {
        return price * 0.80;
    }
}

public class PriceService {
    public double finalPrice(double basePrice, DiscountPolicy policy) {
        return policy.apply(basePrice);
    }
}
```

Si demain tu ajoutes `SeniorDiscount`, tu crées une nouvelle classe qui implémente `DiscountPolicy`.
Tu n'as pas besoin de modifier `PriceService`.

## Ce qu'il faut retenir
- **Ouvert à l'extension** : je peux ajouter un nouveau comportement.
- **Fermé à la modification** : je n'édite pas le code stable existant.
- OCP s'obtient souvent avec : interfaces, polymorphisme, injection de dépendances.

## Checklist rapide
Avant de coder, pose ces questions :

1. Si un nouveau cas métier arrive, vais-je modifier une grosse méthode `if/else` ?
2. Puis-je introduire une abstraction (`interface`) pour brancher de nouveaux comportements ?
3. Est-ce que le cœur du code reste inchangé quand j'ajoute une variante ?

Si la réponse est oui à 2 et 3, tu es proche de OCP.

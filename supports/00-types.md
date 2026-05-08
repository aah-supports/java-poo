# Cours Java - Les types

## 1) Pourquoi les types sont importants
Le type d'une variable définit :
- les valeurs autorisées,
- les opérations possibles,
- la mémoire utilisée,
- les erreurs détectables à la compilation.

En Java, le typage est statique : le type est connu avant l'exécution.

## 2) Les types primitifs
Java fournit 8 types primitifs :
- `byte` (8 bits)
- `short` (16 bits)
- `int` (32 bits)
- `long` (64 bits)
- `float` (32 bits, décimal)
- `double` (64 bits, décimal)
- `char` (16 bits, caractère Unicode)
- `boolean` (`true`/`false`)

Exemple :

```java
int age = 21;
long population = 8_100_000_000L;
double prix = 19.99;
char lettre = 'A';
boolean actif = true;
```

## 3) Types références (objets)
Tout ce qui n'est pas primitif est un type référence :
- `String`
- tableaux (`int[]`, `String[]`)
- classes (`Employee`, `Door`)
- interfaces (`List`, `Map`)

Exemple :

```java
String nom = "Alice";
int[] notes = {12, 15, 18};
List<String> tags = List.of("java", "poo");
```

Un type référence peut valoir `null` :

```java
String email = null;
```

## 4) Conversion de types
### Conversion implicite (widening)
Pas de perte d'information, Java accepte automatiquement :

```java
int n = 42;
long l = n;
double d = l;
```

### Conversion explicite (casting)
Risque de perte d'information :

```java
double d = 9.8;
int n = (int) d; // n vaut 9
```

## 5) Wrappers et autoboxing
Chaque primitif a une classe wrapper :
- `int` -> `Integer`
- `double` -> `Double`
- `boolean` -> `Boolean`

Exemple :

```java
Integer a = 10;     // autoboxing
int b = a;          // unboxing
List<Integer> ids = List.of(1, 2, 3);
```

## 6) String : type référence immuable
`String` est immuable : chaque modification crée un nouvel objet.

```java
String s = "Java";
s = s + " POO"; // nouveau String
```

Pour construire beaucoup de texte, préférer `StringBuilder`.

```java
StringBuilder sb = new StringBuilder();
sb.append("TP ").append("SOLID");
String resultat = sb.toString();
```

## 7) Tableaux, List, Map
### Tableau
Taille fixe :

```java
int[] valeurs = {1, 2, 3};
```

### List
Taille dynamique :

```java
List<String> noms = new ArrayList<>();
noms.add("Alice");
noms.add("Bob");
```

### Map
Association clé/valeur :

```java
Map<String, Integer> scores = new HashMap<>();
scores.put("Alice", 15);
scores.put("Bob", 12);
```

## 8) `var` (Java 10+)
`var` laisse le compilateur inférer le type local :

```java
var total = 100;          // int
var message = "Bonjour";  // String
```

Bonne pratique : utiliser `var` seulement si le type reste évident.

## 9) Bonnes pratiques
- Choisir le type le plus précis possible.
- Utiliser `int` par défaut pour les entiers courants.
- Utiliser `BigDecimal` pour les montants financiers.
- Éviter `null` quand possible.
- Utiliser des generics (`List<String>`) au lieu de types bruts (`List`).

## 10) Exemple complet

```java
import java.util.ArrayList;
import java.util.List;

public class TypeExample {
    public static void main(String[] args) {
        int age = 21;
        double note = 15.5;
        boolean present = true;
        String name = "Alice";

        List<Integer> notes = new ArrayList<>();
        notes.add(12);
        notes.add(16);
        notes.add((int) note); // cast explicite

        System.out.println(name + " (" + age + ") present=" + present);
        System.out.println("Notes: " + notes);
    }
}
```

## 11) Mini exercices
1. Écrire une méthode `boolean isAdult(int age)`.
2. Écrire une méthode `double average(List<Integer> values)`.
3. Transformer une `List<String>` en `Map<String, Integer>` avec la longueur de chaque mot.
4. Comparer `StringBuilder` et concaténation dans une boucle.

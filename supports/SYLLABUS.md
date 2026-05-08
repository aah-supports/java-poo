# Syllabus - Java POO et SOLID

## 1) Informations du module
- Intitulé : Java POO et principes SOLID
- Niveau : débutant/intermédiaire
- Format : cours + démos + TP
- Langage : Java (JDK 17 recommandé)

## 2) Objectifs pédagogiques
À la fin du module, l'étudiant doit être capable de :
- modéliser un problème en classes/objets,
- choisir les bons types Java (primitifs, références, collections),
- utiliser interfaces et injection de dépendance,
- appliquer SRP, OCP et DIP sur un cas concret,
- écrire un code testable, lisible et évolutif.

## 3) Prérequis
- Bases d'algorithmie (conditions, boucles, fonctions)
- Notions de terminal (compiler/exécuter)
- Connaissance de base de Git (commit/push)

## 4) Outils
- JDK 17+
- IDE (IntelliJ, VS Code, Eclipse)
- Git + GitHub
- JUnit 5 (pour les tests)

## 5) Plan du module (ordre recommandé)
1. Types Java : `00-types.md`
2. Interfaces : `01-interfaces.md`
3. SOLID - SRP : `02-S-SRP.md`
4. SOLID - OCP : `03-O-OCP.md`
5. Injection de dépendance : `04-injection-dependance.md`
6. Cas pratique accès : `05-cas-pratique-acces.md`
7. TP fil rouge SOLID : `06-TP-SOLID.md`

## 6) Déroulé proposé (8 séances)
### Séance 1 - Types et modélisation de base
- Primitifs, références, wrappers, conversions
- Exercices courts de manipulation de types

### Séance 2 - Interfaces et polymorphisme
- Contrat d'interface, `implements`, `@Override`
- Variation d'implémentations sans changer le code appelant

### Séance 3 - SRP
- Identifier les responsabilités
- Découpage d'une classe en services cohérents

### Séance 4 - OCP
- Remplacer les `if/else` métier par polymorphisme
- Ajout d'un comportement sans modifier le cœur

### Séance 5 - Injection de dépendance
- Couplage fort vs faible couplage
- Injection par constructeur

### Séance 6 - Cas pratique UML (contrôle d'accès)
- Lecture du diagramme
- Implémentation du modèle métier

### Séance 7 - TP SOLID
- Refactor complet du système d'accès
- Écriture de tests unitaires

### Séance 8 - Revue, feedback, soutenance courte
- Revue de code
- Corrections finales

## 7) Évaluation
- TP SOLID (implémentation) : 40%
- Qualité du code (lisibilité, conception) : 25%
- Tests unitaires : 25%
- Restitution orale/courte démo : 10%

## 8) Critère de réussite
- Le projet compile et s'exécute
- Le code respecte les principes vus en cours
- Les tests couvrent les cas critiques
- Les classes ont des responsabilités claires

## 9) Livrables
- Code source versionné sur GitHub
- README projet (contexte + choix techniques)
- Tests unitaires exécutés avec résultat vert

## 10) Bonnes pratiques attendues
- Nommage clair et cohérent
- Champs `private final` quand possible
- Validation des entrées (constructeurs/méthodes)
- Pas de logique métier dans `main`
- Utilisation d'interfaces pour les points de variation

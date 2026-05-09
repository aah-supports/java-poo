# Sandbox Java (Docker)

Cette sandbox permet de compiler et exécuter du Java sans installation locale du JDK.

## Prérequis
- Docker Desktop installé et lancé

## Démarrage rapide
Depuis le dossier `sandbox-java` :

```bash
./sandbox-java.sh build
./sandbox-java.sh run
```

## Commandes utiles

```bash
./sandbox-java.sh shell    # Ouvrir un terminal dans le conteneur
./sandbox-java.sh compile  # Compiler vers /tmp/out (dans le conteneur)
./sandbox-java.sh run      # Compiler + lancer Main
./sandbox-java.sh clean    # Nettoyer /tmp/out (dans le conteneur)
```

## Procédure manuelle dans le conteneur (`/workspace`)

```bash
rm -rf /tmp/out
mkdir -p /tmp/out
javac -d /tmp/out $(find src -name "*.java")
java -cp /tmp/out Main
```

## Notes
- Le dossier `src` est monté dans le conteneur (`/workspace/src`).
- Les classes compilées vont dans `/tmp/out` (filesystem interne du conteneur).
- On évite volontairement `/workspace/out` pour supprimer les problèmes de permissions.

# Sandbox Java (Docker)

Cette sandbox permet de compiler et exécuter du Java sans installation locale du JDK.

## Conteneur

Se connecter à son conteneur 

```bash
docker exec -it container-java bash
```

## Prérequis

- Docker Desktop installé et lancé

## Démarrage rapide

Depuis le dossier `SANDBOX` :

```bash
./sandbox-java.sh build
./sandbox-java.sh run
```

## Commandes utiles

```bash
./sandbox-java.sh shell    # Ouvrir un terminal dans le conteneur
./sandbox-java.sh compile  # Compiler les .java vers /out
./sandbox-java.sh run      # Compiler + lancer Main
./sandbox-java.sh clean    # Nettoyer /out
```

## Procédure dans le conteneur (`/workspace`)

Si vous êtes déjà dans le conteneur et placé dans `/workspace` :

```bash
javac -d out $(find src -name "*.java")
java -cp out Main
```

Si qu'un seul fichier 

```bash
java Main.java 
```

Rôle de chaque commande :

- `find src -name "*.java"` : trouve tous les fichiers source Java dans `src`.
- `$(...)` : injecte la liste trouvée dans la commande `javac`.
- `javac` : compile les fichiers `.java`.
- `-d out` : écrit les fichiers compilés `.class` dans `out` en respectant les packages.
- `java -cp out Main` : lance la classe `Main` en utilisant `out` comme classpath.

Optionnel (recompile propre) :

```bash
rm -f out/**/*.class out/*.class 2>/dev/null
javac -d out $(find src -name "*.java")
java -cp out Main
```

## Notes

- Le dossier `src` est monté dans le conteneur (`/workspace/src`).
- Les classes compilées sont écrites dans `out` (`/workspace/out`).
- La commande `run` lance `Main` avec `java -cp out Main`.

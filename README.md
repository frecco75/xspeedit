XspeedIt
========

[![Build Status](https://travis-ci.org/frecco75/xspeedit.svg?branch=master)](https://travis-ci.org/frecco75/xspeedit) [![Coverage Status](https://coveralls.io/repos/github/frecco75/xspeedit/badge.svg?branch=master)](https://coveralls.io/github/frecco75/xspeedit?branch=master) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/fbfa77766af04a74ac747ee2f0be0219)](https://www.codacy.com/app/fabien-recco/xspeedit?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=frecco75/xspeedit&amp;utm_campaign=Badge_Grade)

### Required configuration ###
* JDK 13
* Git

Technologies: [Java](https://www.java.com/) - [Vavr](https://www.vavr.io/) - [Maven](https://maven.apache.org/) - [JUnit](http://junit.org/) - [Git](https://git-scm.com/)

## Running  ##

### Compilation ###
```shell script
./mvnw clean install
```
This command line creates the executable file **robot.jar** in the /target directory.

### Running ###
```shell script
java -jar target/robot-1.0.0-SNAPSHOT.jar [articles]
```

## Specifications ##

XspeedIt est une société d'import / export ayant robotisé toute sa chaîne d'emballage de colis.  
Elle souhaite trouver un algorithme permettant à ses robots d'optimiser le nombre de cartons d'emballage utilisés.

Les articles à emballer sont de taille variable, représentée par un entier compris entre 1 et 9.  
Chaque carton a une capacité de contenance de 10.  
Ainsi, un carton peut par exemple contenir un article de taille 3, un article de taille 1, et un article de taille 6.

La chaîne d'articles à emballer est représentée par une suite de chiffres, chacun représentant un article par sa taille.  
Après traitement par le robot d'emballage, la chaîne est séparée par des "/" pour représenter les articles contenus dans un carton.

*Exemple*  
```text
Chaîne d'articles en entrée : 163841689525773  
Chaîne d'articles emballés  : 163/8/41/6/8/9/52/5/7/73
```

L'algorithme actuel du robot d'emballage est très basique.  
Il prend les articles les uns après les autres, et les mets dans un carton.  
Si la taille totale dépasse la contenance du carton, le robot met l'article dans le carton suivant.

Objectif
--------

Implémenter un algorithme qui permettrait de maximiser le nombre d'articles par carton, en utilisant un langage pouvant être exécuté sur une JVM 1.8 minimum ou en node.js.
L'ordre des cartons et des articles n'a pas d'importance.

*Exemple*  
```text
Articles      : 163841689525773  
Robot actuel  : 163/8/41/6/8/9/52/5/7/73 => 10 cartons utilisés  
Robot optimisé: 163/82/46/19/8/55/73/7   => 8  cartons utilisés
```

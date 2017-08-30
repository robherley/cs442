# Lecture 2

### Steps in Database Design

1. Requirements Analysis
	* user needs
2. Conceptual Design
	* high level description
3. Logical Design
	* translate ER into DBMS data model
4. Schema Refinement
	* consistency, normalization
5. Physical Design
	* indexes, disk layout
6. Security Design
	* who accesses what, and how

### Conceptual Database Design
* **ER model**: (E-*entities*, R - *relationships*) 

* **Entity**: real world object, distinguishable from other objects.
	* An entity is described using a set of *attributes*.
* **Entity Set**: A collection of similar entities. Ex: all employees.
	* All entities in an entity set have the same set of attributes.
	* Each entity set has a **key**.
	* Each attribute has a **domain**.

![entity-set-example](https://images.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.dmst.aueb.gr%2Fdds%2Fetech%2Fdb%2Fent.gif)ip


### Keys of Entities
* A *superkey*: a set of attributes which, taken collectively, identify uniquely an entity in an entity set
* A *key* (or candidate key): a superkey for which no proper subset is a superkey (~ a **minimal** superkey)


An example:
User has:

* id (unique)
* age
* gender
* school
* email (unique)

Candidate Keys: ID + email

Primary Key: ID


### Relationships
* **Relationship**: Association among two or more entities.
	* Ex, Alan works in Pharmacy department. Alice works in HR department.
* **Relationship Set**: Collection of similar relationships.

### Degree of A Relationship
* Degree: the number of participating entities.
	* Degree 2: binary
	* Degree 3: ternary
	* Degree n: n-ary
* Binary relationships are very common and widely used.

![relset](https://images.duckduckgo.com/iu/?u=http%3A%2F%2Fimage.slidesharecdn.com%2Fbpmcourse-1228730832862745-9%2F95%2Fbusiness-process-modeling-32-728.jpg)

### Roles in Relationships
* Same entity set can participate more than onece in the same relationship, with different "roles".
* Also called as recursive relationship

<style>

img {
	display: block;
  	margin-left: auto;
  	margin-right: auto;
}

</style>
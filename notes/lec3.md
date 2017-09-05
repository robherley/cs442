# CS442 - Lecture 3

### Cardinality Constraints in E-R Design
* Cardinality ratio of R: the number of entitites of E2 that an entity of E1 can possibly be associated thru R
* Four possibilities are usually specified:
	1. one-to-one
		* See notes on canvas
		* Ex: Count
	2. one-to-many
		* an entity in A is associated with any number of entities in B
		* an entity in B is associate with at most one entity in A
		* Ex: Mother - Children
	3. many-to-one
		* Directors - Movie
	4. many-to-many
		* an entity in A is associated with any number of entities in B and vice versa
		* Ex: Students - Teachers

### Participation Constraints
* An entity set E may participate in a relation either totally or partially.
	* **Total Participation**: Every entity in E is involved in some association of the relationship.
	* **Partial Participation**: Not all entities in the set are involved in the association of the relationship.
	* **Weak Entity Set**: an entitiy set whose members owe their existence to some entity in a strong entity set.
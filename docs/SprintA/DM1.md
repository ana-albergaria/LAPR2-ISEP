# OO Analysis #

The construction process of the domain model is based on the client specifications, especially the nouns (for _concepts_) and verbs (for _relations_) used. 

## Rationale to identify domain conceptual classes ##
To identify domain conceptual classes, start by making a list of candidate conceptual classes inspired by the list of categories suggested in the book "Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development". 


### _Conceptual Class Category List_ ###

**Business Transactions**

* Test;

---

**Transaction Line Items**

* Sample;
* Result;
* Chemical Result;
* Diagnosis;

---

**Product/Service related to a Transaction or Transaction Line Item**

* Parameter:
* Notification;

---


**Transaction Records**

* Report (Specialist Doctor);
* Covid-19 Data;
* Forecasts;
* Daily reports (NHS);


---  


**Roles of People or Organizations**

* Client;
* Administrator;
* Receptionist;
* Medical lab technician;
* Clinical chemistry technologist;
* Specialist doctor;
* Laboratory coordinator;


---


**Places**

* Clinical Analysis Laboratory;
* Chemical Laboratory;
* Application;



---

**Noteworthy Events**

* Chemical Analysis;

---


**Physical Objects**

*

---


**Descriptions of Things**

* Type of Test;
* Category;
* Covid-19 test;
* Blood test;
* Other Test;


---


**Catalogs**

*  

---


**Containers**

*  

---


**Elements of Containers**

*  

---


**Organizations**

* Company;

---

**Other External/Collaborating Systems**

* NHS API;


---


**Records of finance, work, contracts, legal matters**

* 

---


**Financial Instruments**

* 

---


**Documents mentioned/used to perform some work/**

* Lab order;

---



### **Rationale to identify associations between conceptual classes**

An association is a relationship between instances of objects that indicates a relevant connection and that is worth of remembering, or it is derivable from the List of Common Associations: 

* **_A_** is physically or logically part of **_B_**
* **_A_** is physically or logically contained in/on **_B_**
* **_A_** is a description for **_B_**
* **_A_** known/logged/recorded/reported/captured in **_B_**
* **_A_** uses or manages or owns **_B_**
* **_A_** is related with a transaction (item) of **_B_**
* etc.



| Concept (A) 		|  Association   	|  Concept (B) |
|----------	   		|:-------------:		|------:       |
| Company 	| performs		 | Test  |
|  | conducts | TestType |
|  |owns |Chemical laboratory|
|  |owns |Clinical Analysis Laboratory|
|  | reports | Covid-19 Data |
|  | knows | Application |
|  Parameter	| presented under  		 	| Category  |
|  Category	| created by		 	| Administrator |
| Test	| requested by 		 	| Client |
|  | is of | TestType|
|  | collects | Sample|
|  | requested by |  Lab Order|
|  | requestes analysis of | Parameter|
|  | is registered by | Medical Lab Technician |
|Receptionist | registers | Client |
| | registers | Test |
| | works for | Clinical Analysis Laboratory |
|Medical Lab Technician | collects | Samples|
| | records the | Samples |
| | works for | Clinical Analysis Laboratory |
|Samples| are sent to| Chemical laboratory|
|Clinical chemistry technologist| works for | Chemical Laboratory|
| | performs |Chemical analysis|
| | records |Chemical result|
|Chemical Result| is analysed by| Specialist doctor|
|Specialist doctor |  makes | Diagnosis |
| | writes| Report|
|Diagnosis | is validated by | Laboratory Coordinator |
|Report| is validated by | Laboratory Coordinator|
|Laboratory Coordinator| confirms | Result |
|Client| receives |Notification|
| | owns |Lab Order|
|Result | is known by | Client|
|NHS API |receives |Covid-19 Data|
|Application | generates | Covid-19 Data|
|Daily reports (NHS) | is a part of | Covid-19 Data|
|Forecasts | is a part of | Covid-19 Data|
|Covid-19 test | is a part of | TestType|
| | is characterized by | Parameter|
|Blood test | is a part of |  TestType|
| | is characterized by | Parameter|
|Application | suports | Other Test|
| | knows | Company |
|Other Test | is a part of | TestType|
| | is characterized by | Parameter|






 









## Domain Model

**Do NOT forget to identify concepts atributes too.**

**Insert below the Domain Model Diagram in a SVG format**

![DM.svg](DM.svg)



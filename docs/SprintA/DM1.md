# OO Analysis #

The construction process of the domain model is based on the client specifications, especially the nouns (for _concepts_) and verbs (for _relations_) used. 

## Rationale to identify domain conceptual classes ##
To identify domain conceptual classes, start by making a list of candidate conceptual classes inspired by the list of categories suggested in the book "Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development". 


### _Conceptual Class Category List_ ###

**Business Transactions**

* **Test;**

---

**Transaction Line Itemss**

* **Sample;**
* Result;
* Chemical Result;
* Clinical Result;
* Diagnosis;

---

**Product/Service related to a Transaction or Transaction Line Item**

* **Parameter:**
* Notification;
* Courier;

---


**Transaction Records**

* Report (Specialist Doctor);
* Covid-19 Data;
* Forecasts;
* Daily reports (NHS);


---  


**Roles of People or Organizations**

* **Client;**
* **Administrator;**
* Doctor;
* Receptionist;
* Medical lab technician;
* Clinical chemistry technologist;
* Specialist doctor;
* Laboratory coordinator;


---


**Places**

* **Clinical Analysis Laboratory;**
* **Chemical Laboratory;**
* Application;



---

**Noteworthy Events**

* **Chemical Analysis;**
* Clinical analysis;

---


**Physical Objects**

*

---


**Descriptions of Things**

* **Type of Test;**
* **Category;**
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

* **Company;**
* NHS;


---

**Other External/Collaborating Systems**

* NHS API;
* External module;



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
| **Company** 	| **performs** 		 	| **Test**  |
|  | **conducts** | **TestType** |
|  |**owns** |**Chemical laboratory**|
|  | **owns** |**Clinical Analysis Laboratory**|
|  | **conducts**   		 	| **TestType**  |
|  | reports | Covid-19 Data |
|  **Parameter**	| **presented under**  		 	| **Category**  |
|  **Category**	| **created by**		 	| Administrator |
|  | **presented under** | **Parameter** |
|  | **created by** | **Administrator** |
| **Test**	| **requested by**  		 	| **Client** |
|  |  **is known by** |**Application** |
|  | **is of** | **TestType**|
|  | **collects** | **Sample**|
|  | **requested by** |  **Client**|
|  | **requestes analysis of** | **Parameter**|
|  | is registered by | Medical Lab Technician |
|Receptionist | registers | Client |
|| registers | Test |
|Medical Lab Technician | collects | Samples|
|| belongs to | Clinical Analysis Laboratory|
| | records the | Samples |
|Samples | are known by | Application |
|| are sent to| Chemical laboratory|
|Clinical chemistry technologist| belongs to | Chemical Laboratory|
|  |receives  |Sample |
| | performs |Chemical analysis|
| | records |Chemical result|
|Lab order | prescribed by  | Doctor |
|Chemical Result| is analysed by| Specialist doctor|
|Specialist doctor |  makes | Diagnosis |
|| writes| |Report|
|Report | is delivered to | Client |
| | is known by | Application|
|| is validated by | Laboratory Coordinator |
|Clinical Result | is known by | Application|
| | is validated by | Laboratory Coordinator|
|Client| receives |Notification|
|Result | is known by | Client|
| | is known by | Medical lab technicians|
| | is known by | Clinical chemistry technologist|
| | is known by | Specialist doctor|
| | is known by | Laboratory coordinator|
|NHS |receives |Covid-19 Data|
|Application | generates | Covid-19 Data|
|Daily reports (NHS) | is a part of | Covid-19 Data|
|Forecasts | is a part of | Covid-19 Data|
|Covid-19 test | is a part of | TestType|
| | is characterized by | Parameter|
|Blood test | is a part of |  TestType|
|| is characterized by | Parameter|
|Application | suports | Other Test|
|Other Test | is a part of | TestType|
|| is characterized by | Parameter|






 









## Domain Model

**Do NOT forget to identify concepts atributes too.**

**Insert below the Domain Model Diagram in a SVG format**

![DM.svg](DM.svg)




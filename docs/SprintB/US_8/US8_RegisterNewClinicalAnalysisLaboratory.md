# US 008 - Register a new clinical analysis laboratory 
 
## 1. Requirements Engineering
 
*In this section, it is suggested to capture the requirement description and specifications as provided by the client as well as any further clarification on it. It is also suggested to capture the requirements acceptance criteria and existing dependencies to other requirements. At last, identfy the involved input and output data and depicted an Actor-System interaction in order to fulfill the requirement.* 
 
### 1.1. User Story Description
 
As an administrator, I want to register a new clinical analysis laboratory stating which
kind of test(s) it operates.
 
### 1.2. Customer Specifications and Clarifications 
 
*Insert here any related specification and/or clarification provided by the client together with **your interpretation**. When possible, provide a link to such specifications/clarifications.*

**From the specifications document:**
>_Many Labs_ is an English company that (...) has a network of clinical analysis laboratories in England where analysis of blood (samples are collected) are performed, as well as Covid-19 tests.  
> 
> All _Many Labs_ clinical analysis laboratories perform clinical blood tests, and a subset of these laboratories also performs Covid-19 tests.
> 
> The set of _Many Labs_ clinical analysis laboratories form a network that covers all England, and it is responsible for collecting samples and interacting with clients.
> 
> The samples collected by the network of laboratories are then sent to the chemical laboratory located in the company headquarters and the chemical analysis are performed there.

**From the client clarifications:**
>**Question**: What kind of information does the company store about their employees and owned laboratories?  
> **Answer**: Each Clinical Analysis Laboratory is characterized by the following attributes:  
>Laboratory ID;  
> Name;  
> Address;  
> Phone Number;  
> TIN number.

You can read the whole discussion [here](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7126).

>**Question**: Does it have any specific acceptance criteria you want to introduce?  
> **Answer**: For now I have nothing more to say about the acceptance criteria of US8.  

You can read the whole discussion [here](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7744).

>**Question**: Do you prefer that when registering a new CLA that you can register the employees who will work there at the same time or do you prefer to be able to register first and fill in later? And in that order, would it be okay to register a new CLA without employees?   
> **Answer**: These are two different user stories in the requirements introduced in Sprint B. The application should include a functionality for creating CLAs and another one to register employees.  
> Each Receptionist and each Medical Lab Technician can work in any Clinical Analysis Laboratory of the Many Labs network.  
> 
>**Question**: Of all the information that we have about the CLA, what would be the bare minimum to be able to successfully register a new CLA?  
>**Answer**: All information is required.

You can read the whole discussion [here](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7536#p10174).  

>**Question**: Is there a maximum limit of types of tests a clinical analysis laboratory can operate?  
>**Answer**: No.  
> 
>**Question**:We know through the specifications document that "All Many Labs clinical analysis laboratories perform clinical blood tests".
>My question therefore is: When creating a new Clinical Analysis Laboratory, should the system automatically record that it operates that type of test or should the person who is creating it select it manually while selecting other types of tests? Or other option?  
>**Answer**: The administrator should select, manually, all the types of tests to be performed by the Clinical Analysis Laboratory.  
 
You can read the whole discussion [here](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7553#p10176).  

>**Question**: Which type/format has LaboratoryID, address, name, phone number, and TIN number on Clinical Analysis Laboratory?  
>**Answer**: Each Clinical Analysis Laboratory is characterized by the following attributes:  
>- Laboratory ID: five alphanumeric characters;  
>- Name: A string with no more than 20 characters;  
>- Address: A string with no more than 30 characters;  
>- Phone Number: 11 digit number;  
>- TIN number: 10 digit number; 
>- Type of tests performed by the lab.  

You can read the whole discussion [here](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7636#p10191).



### 1.3. Acceptance Criteria

* **AC1:** All required field must be filled in.  
* **AC2:** The Laboratory ID must have five alphanumeric characters.  
* **AC3:** The name is a string with no more than 20 characters.  
* **AC4:** The address is a string with no more than 30 characters.
* **AC5:** The Phone Number is a 11 digit number.
* **AC6:** The TIN Number is a 10 digit number.
* **AC7:** Type of tests must be an attribute of the Clinical Analysis Laboratory.  


### 1.4. Found out Dependencies

There is a dependency to "US009 To specify a new type of test and its collecting methods" since at least a type of test (specifically the blood test) must exist to determine the type of test(s) the clinical analysis laboratory operates.

### 1.5 Input and Output Data


*Identity here the data to be inputted by the system actor as well as the output data that the system have/needs to present in order to properly support the actor actions. Regarding the inputted data, it is suggested to distinguish between typed data and selected data (e.g. from a list)*  


**Input Data:**  

* Typed data:
    * a Laboratory ID
    * an Address
    * a Phone Number  
    * a TIN Number

* Selected data:
    * Determining type(s) of test    

**Output Data:**

* List of existing types of test
* (In)Success of the operation


### 1.6. System Sequence Diagram (SSD)

*Insert here a SSD depicting the envisioned Actor-System interactions and throughout which data is inputted and outputted to fulfill the requirement. All interactions must be numbered.*

![USXX-SSD](USXX-SSD.svg)


### 1.7 Other Relevant Remarks

*Use this section to capture other relevant information that is related with this US such as (i) special requirements ; (ii) data and/or technology variations; (iii) how often this US is held.* 


## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 
*In this section, it is suggested to present an excerpt of the domain model that is seen as relevant to fulfill this requirement.* 

![USXX-MD](USXX-MD.svg)

### 2.2. Other Remarks

*Use this section to capture some aditional notes/remarks that must be taken into consideration into the design activity. In some case, it might be usefull to add other analysis artifacts (e.g. activity or state diagrams).* 


## 3. Design - User Story Realization 

### 3.1. Rationale

**The rationale grounds on the SSD interactions and the identified input/output data.**

| Interaction ID | Question: Which class is responsible for... | Answer  | Justification (with patterns)  |
|:-------------  |:--------------------- |:------------|:---------------------------- |
| Step 1: asks to register a new Clinical Analysis Laboratory 		 |  ...interacting with the actor?							 |  RegisterNewCalUI           |   Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                           |
| 			  		 |	...coordinating the US? | RegisterNewCalController | Pure Fabrication: it refers to the Controller. there is no reason to assign this responsibility to any existing class in the Domain Model.                             |
| 			  		 |	...instantiating a new Clinical Analysis Laboratory? | Laboratory | Creator (Rule 1): in the DM, Laboratory has a Clinical Analysis Laboratory.                            |
| 			  		 |	...knowing the user using the system? |      UserSession                          | According to A&A component documentation.
| Step 2: requests data (laboratory ID, name, address, phone number, TIN number)  		 |	...asking the user for this data?						 | RegisterNewCalUI            | IE: responsible for user interaction.                             |
| Step 3: types requested data		 |	...validating the data locally (e.g.: mandatory vs. non-mandatory data)?						 |  ClinicalAnalysisLaboratory           |   IE: knows its own data.                           |
|               	 |	...saving the inputted data?						 |   ClinicalAnalysisLaboratory          | IE: The object created in Step 1 has its own data as well as inherits attributes from Laboratory class.                             |
| Step 4: shows types of test list and asks to select at least one   		 |	...knowing who has the responsability to show the types of test?					 |   Company      |  LC: Company uses TestTypeStore.                     |
|                                 		 |	...knowing the types of test to show?						 |   TestTypeStore       |  Pure Fabrication: for low coupling reasons. There is no reason to assign this responsibility to any existing class in the Domain Model.                          |
| Step 5: selects type(s) of test  		 | ...saving the selected type(s) of test?							 | ClinicalAnalysisLaboratory            |   IE: object created in Step 1 operates a certain number of types of test.                           |
| Step 6: shows all data and requests confirmation            		 |	...validating the data globally (e.g.: duplicated)?						 |  Laboratory           |   IE: knows all the ClinicalAnalysisLaboratory objects.                           |
| Step 7: confirms the data  		 |	...saving the created Clinical Analysis Laboratory?						 | Laboratory            |   IE: records all the ClinicalAnalysisLaboratory objects.                           |
| Step 8: informs operation success  		 |	...informing operation success?						 | RegisterNewCalUI            | IE: responsible for user interaction                                 |

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

* Company  
* Laboratory  
* ClinicalAnalysisLaboratory  

Other software classes (i.e. Pure Fabrication) identified:  

* RegisterNewCalUI  
* RegisterNewCalController

## 3.2. Sequence Diagram (SD)

*In this section, it is suggested to present an UML dynamic view stating the sequence of domain related software objects' interactions that allows to fulfill the requirement.* 

![USXX-SD](USXX-SD.svg)

## 3.3. Class Diagram (CD)

*In this section, it is suggested to present an UML static view representing the main domain related software classes that are involved in fulfilling the requirement as well as and their relations, attributes and methods.*

![USXX-CD](USXX-CD.svg)

# 4. Tests 
*In this section, it is suggested to systematize how the tests were designed to allow a correct measurement of requirements fulfilling.* 

**_DO NOT COPY ALL DEVELOPED TESTS HERE_**

**Test 1:** Check that it is not possible to create an instance of the Example class with null values. 

@Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
    Exemplo instance = new Exemplo(null, null);
}

*It is also recommended to organize this content by subsections.* 

# 5. Construction (Implementation)

*In this section, it is suggested to provide, if necessary, some evidence that the construction/implementation is in accordance with the previously carried out design. Furthermore, it is recommeded to mention/describe the existence of other relevant (e.g. configuration) files and highlight relevant commits.*

*It is also recommended to organize this content by subsections.* 

# 6. Integration and Demo 

*In this section, it is suggested to describe the efforts made to integrate this functionality with the other features of the system.*


# 7. Observations

*In this section, it is suggested to present a critical perspective on the developed work, pointing, for example, to other alternatives and or future related work.*
  
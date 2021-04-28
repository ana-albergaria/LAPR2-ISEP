# US 11 - to specify a new parameter category

## 1. Requirements Engineering


### 1.1. User Story Description


As an administrator, I want to specify a new parameter category.



### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

> “Blood tests are frequently characterized by measuring several parameters which for presentation/reporting purposes are organized by categories. For example, parameters such as the number of Red Blood Cells (RBC), White Blood Cells (RBC) and Platelets (PLT) are usually presented under the blood count (Hemogram) category.”


> “Regardless, such tests rely on measuring one or more parameters that can be grouped/organized by categories.”



**From the client clarifications:**

> **Question:** "What are the information related to a Parameter Category?"
> 
> **Answer:** "Each category has a name and a unique code. There are no subcategories."

>Read the whole answer [here](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7318#p9859).

-

> **Question:** "Should each parameter category have a state regarding the number of parameters under that category?"
>
> **Answer:** yyyy.

>Read the whole answer [here](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7610#p9903).

-

> **Question:** xxx.
>
> **Answer:** yyyy.

>Read the whole answer [here](zzz).


### 1.3. Acceptance Criteria


* **AC1:** Code must be unique having 4 to 8 chars.
* **AC2:** Description cannot be empty and has, at maximum, 40 chars.
* **AC3:** NHS identifier is not mandatory.


### 1.4. Found out Dependencies


* No dependencies were found.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* code
	* description
	* NHS identifier
	
* Selected data:
	* (none)


**Output Data:**

* (In)Sucess of the operation

### 1.6. System Sequence Diagram (SSD)


![US011_SSD](US011_SSD.svg)


### 1.7 Other Relevant Remarks

*Use this section to capture other relevant information that is related with this US such as (i) special requirements ; (ii) data and/or technology variations; (iii) how often this US is held.* 


## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 


![US011_MD](US011_MD.svg)


### 2.2. Other Remarks

*Use this section to capture some aditional notes/remarks that must be taken into consideration into the design activity. In some case, it might be usefull to add other analysis artifacts (e.g. activity or state diagrams).* 



## 3. Design - User Story Realization 

### 3.1. Rationale

**The rationale grounds on the SSD interactions and the identified input/output data.**

| Interaction ID | Question: Which class is responsible for... | Answer  | Justification (with patterns)  |
|:-------------  |:--------------------- |:------------|:---------------------------- |
| Step 1  		 |	... interacting with the actor? | CreateParameterCategoryUI | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|       	   	 |	... coordinating the US? | CreateParameterCategoryController | Controller |
|       	   	 |	... instantiating a new Parameter Category? | Administrator | Creator (Rule 1/2): in the DM Organization adopts a Parameter Category. |
|       	   	 |	... knowing the user using the system? | UserSession | ??? |
| Step 2  		 |							 |             |                              |
| Step 3  		 |	...saving the input data? | ParameterCategory | IE: The object created in step 1 has its own data. |
| Step 4  		 |	...validating the data locally (e.g.: mandatory vs. non-mandatory data)? | ParameterCategory | IE: knows its own data. |
|       	   	 |	... validating the data globally (e.g.: duplicated)? | Company | IE: knows all the ParameterCategory objects |
| Step 5  		 |	...saving the created parameter category? | Company | IE: adopts/records all the ParameterCategory objects |
| Step 6  	   	 |	... informing operation success? | UI | IE: responsible for user interaction |
### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Administrator
 * Company
 * ParameterCategory

Other software classes (i.e. Pure Fabrication) identified: 
 * CreateParameterCategoryUI  
 * CreateParameterCategoryController

## 3.2. Sequence Diagram (SD)


![US011_SD](US011_SD.svg)


## 3.3. Class Diagram (CD)


![US011_CD](US011_CD.svg)


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






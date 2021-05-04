# US 7 - As an administrator, I want to register a new employee

## 1. Requirements Engineering

*In this section, it is suggested to capture the requirement description and specifications as provided by the client as well as any further clarification on it. It is also suggested to capture the requirements acceptance criteria and existing dependencies to other requirements. At last, identfy the involved input and output data and depicted an Actor-System interaction in order to fulfill the requirement.*

### 1.1. User Story Description

As an ***administrator***, I want to **register** a new **employee**

### 1.2. Customer Specifications and Clarifications 

**From the specifications document:**

**From the client clarifications:**
>Q: "Hello,
      What kind of information does the company store about their employees and owned laboratories?
      Are there common attributes between the roles below? If so, which ones? (i.e. employee ID, TIN, contact info, etc.)
      Receptionist,cClinical Chemistry Technologist, Medical Lab Technician, Specialist Doctor, Lab Coordinator, Administrator"
>
>A: "All the roles that exist in the Many Labs company are characterized by the following attributes:  
      Employee ID;  
      Organization Role;  
      Name;  
      Address;  
      Phone Number;  
      E-Mail;  
      Standard Occupational Classification (SOC) code.  
      The Specialist Doctor has an additional attribute: Doctor Index Number."  

You can read the whole discussion here.  

>Q: Are there any other employee roles than the othes specified in the documents?  
>A: No.
>
>Q: Do these roles have different contracts with ManyLabs
> (i.e. temporary contract, permanent contract, freelance, etc...?)  
> If so, should this be registered in the system together with 
> the employee role information?  
> A: Many Labs is known for making permanent full-time contracts.
> Therefore, there is no need to register this type of information.  

You can read the whole discussion here.  

>Q: When the application is delivered, should it have default employees
> (ex: administrator, etc...), or should it be completely empty? (without any user or employee).  
> A: One Administrator must be registered before starting the application for the first time.  
> 
> Q: The application can add new employees, but can it remove them? 
> (for example, when one of them is fired)  
> A: For now I do not want such feature.  

You can read the whole discussion here.  

>Q: Though all the information I have seen, "The Specialist Doctor has an additional attribute: Doctor Index Number".  
> Since these specific role has an extra attribute, do you wish to be a new window where it asks
> for the Doctor Index Number if the employee role is a Specialist Doctor,
> or do you want to get all that information in a single window when creating a new employee?  
> A: Whenever the Administrator selects to create a Specialist Doctor, the application should ask for the Doctor Index Number.  
> **Important: in sprints B and C, the client only wants a console user interface.**  

You can read the whole discussion here.  

>Q: Regarding the registration of a new employee, which is the organization role's format?  
> A: Organization Role: a string with no more than 15 characters.  

You can read the whole discussion here.  

>Q: I read in a previous post that "The Doctor Index Number" is introduced by the administrator".  
> I searched in Google and I understood that its length is 6 digits.  
> So I want to know if we need to do any validation.  
> A: It is always a good practice to validate data introduced by the user.  
> For now, it is not necessary to confirm that the code really exists.  
>   
> Q: I searched about the SOC number and its length is 4 digits.  
> Having this in mind, I want to know if this number is introduced by the administrator or generated automatically and if there is any validation to do so.  
> A: It is introduced by the administrator.  

You can read the whole discussion here.  


### 1.3. Acceptance Criteria

* AC1:  "Each user must have a single role defined in the system."
* AC2:  "The "auth" component available on the repository must be reused (without modifications)"

### 1.4. Found out Dependencies

*Identify here any found out dependency to other US and/or requirements.*

### 1.5 Input and Output Data

* Typed data:
    * Employee ID
    * Organization Role
    * Name
    * Adress
    * Phone Number
    * E-mail
    * SOC code
    * Doctor Index Number
* Selected data:

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
| Step 1  		 |							 |             |                              |
| Step 2  		 |							 |             |                              |
| Step 3  		 |							 |             |                              |
| Step 4  		 |							 |             |                              |
| Step 5  		 |							 |             |                              |
| Step 6  		 |							 |             |                              |              

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Class1
 * Class2
 * Class3

Other software classes (i.e. Pure Fabrication) identified: 
 * xxxxUI  
 * xxxxController

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






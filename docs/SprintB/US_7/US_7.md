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
Receptionist,Clinical Chemistry Technologist, Medical Lab Technician, Specialist Doctor, Lab Coordinator, Administrator"
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

>You can read the whole discussion [here](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7123).

-

>Q: Relative to registering a new user, how should the system respond in the event of creating a new user with the same attributes of an already existing user?
>
>A: This should be treated as an error. A subset of the attributes of any client are unique.

>You can read the whole discussion [here](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7482).

-

>Q: Besides the Acceptance Criteria ("each user must have a single role defined in the system"), are there other criterias we need to take into consideration?
>
>A: Please read the Project Requirements for Sprint B: "Each user must have a single role defined in the system. The "auth" component available on the repository must be reused (without modifications).". In my previous posts you can find other requirements that must be met in order to mark US7 as complete.
>
>Q: When creating a new employee, the attributes "Employee ID" and "Doctor Index Number", are implemented by the administrator or incremented by the system?
>
>A: Only the employee ID should be generated from the initials of the employee name and should include a number. The number has 5 digits and is increases automatically when a new employee is registered in the system. For instance, if the company has 20 employees and wants to register a specialist doctor having the name Ana Maria Santos Moura, then the the employee ID for this specialist doctor should be AMSM00021.

>You can read the whole discussion [here](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7547).

-

>Q: Is the administrator an employee? If he/she is, who does him/her register?  
>
>A: Yes. An administrator should be registered when the application starts for first time.
The application can have more than one administrator.

>You can read the whole discussion [here](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7666).

-

>Q: When the application is delivered , should it have default employees , ex:administrator,etc.. , or should be completely empty (without any user or employee).
>
>A: One Administrator must be registered before starting the application for the first time.
>
>Q: The apllication can add new employees but can it remove them , for example when one of them is fired ?
>
>A: For now I do not want such feature.
>
>Q: Should we vallidate if a new employee is the same as one that there is already inside the system ? Do we need to vallidate same atributes to check similarities ?
>
>A: Yes. Yes.

>You can read the whole discussion [here](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7668).

-

>Q: Are there any other employee roles than the ones specified in the documents?
>
>A: No.
>
>Q: Do these roles have different contracts with ManyLabs (i.e. temporary contract, permanent contract, freelance, etc...)? If so, should this be registered in the system together with the employee role information?
>
>A: Many Labs is known for making only permanent full-time contracts.Therefore, there is no need to register this type of information.
>
>Q: For the role id and respective description should we use any specific format or type?
>
>A: From a previous answer: "All the roles that exist in the Many Labs company are characterized by the following attributes:
Employee ID; Organization Role; Name; Address; Phone Number; E-Mail; Standard Occupational Classification (SOC) code.

>You can read the whole discussion [here](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7738).

-

>Q: How should we generate the employee ID? What type and length should it have?
>
>A: The employee ID should be generated from the initials of the employee name and should include a number. The number should have 5 digits and it increases automatically when a new employee is registered in the system. For instance, if the company has 20 employees and the administrator wants to register a specialist doctor with the name Ana Maria Santos Moura, then the the employee ID for this specialist doctor should be AMSM00021.

>You can read the whole discussion [here](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7741).

-

>Q: Can an employee have more than a role?
> 
>A: Every user of the application has only one role.

>You can read the whole discussion [here](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7755).

-

>Q: I read in a previous post that " The doctor Index Number is introduced by the administrator " , I searched in Goolge and i understood that is lenght is 6 digits . So i want to know if we need to do any validation .
>
>A: It is always a good practice to validate data introduced by the user. For now, it is not necessary to confirm that the code really exists.
>
>Q: I searched about the SOC number and is lenght is 4 digits . Having this in mind , i want to know if this number is introduced by the administrator or generated automatically and if there is any validation to do .
>
>A: It is introduced by the administrator.

>You can read the whole discussion [here](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7854).

-

>Q: When registering a new employee should the administrator write or select his role
>
>A: The administrator should write the role when he is registering a new employee.
>
>Q: Assuming that the system should load with all know Roles for ManyLabs (Receptionist, Lab Technician, ...), should we guarantee the "typed" role match to one existent role or should we accept any "typed" role and register the new role in the system?
>
>A: The roles are typed but should be valid roles that exist (are known) in the system. How the system knows the roles should be decided by each team.

>You can read the whole discussion [here](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7863).

-

>Q: Regarding the registration of a new employee, which is the organization role's format?
>
>A: Organization Role: a string with no more than 15 characters.

>You can read the whole discussion [here](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7928).

-

>Q: Through all the information I have seen, "The Specialist Doctor has an additional attribute: Doctor Index Number". Since these specific role has an extra attribute, do you wish for there to be a new window where it asks for the Doctor Index Number if the employee role is a specialist doctor, or do you want to get all that information in a single window when creating a new employee?
> 
>A: Whenever the Administrator selects to create a Specialist Doctor, the application should ask for the Doctor Index Number.
> **Important: in sprints B and C, the client only wants a console user interface.**

>You can read the whole discussion [here](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7944).

-

>Q: Is there any size limit to the employee's name?
>
>A: Employee Name: "a string with no more than 35 characters".
> 
>Q: Is there any validation that needs to be done on the employee's email format and size?
>
>A: The format of an e-mail address is well known. Attribute validation is always a good practice.

>You can read the whole discussion [here](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=7973).

### 1.3. Acceptance Criteria

* **AC1:**  "Each user must have a single role defined in the system."
* **AC2:**  "The "auth" component available on the repository must be reused (without modifications)"
* **AC3:**  "The employee ID should be generated from the initials of the employee name and should include a number. 
  The number has 5 digits and is increases automatically when a new employee is registered in the system."
* **AC4:**  "The administrator should write the role when he is registering a new employee."
* **AC5:**  "The roles are typed but should be valid roles that exist (are known) in the system."
* **AC6:**  "Organization Role: a string with no more than 15 characters."
* **AC7:**  "Whenever the Administrator selects to create a Specialist Doctor, the application should ask for the Doctor Index Number."
* **AC8:**  "Employee Name: a string with no more than 35 characters."
* **AC9:**  Phone Number must have a 11-digit number.
* **AC10:** Email address should contain "@".

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
| Step 1: starts registering a new employee  		 |	...interacting with the actor?						 | RegisterEmployeeUI            | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.                              |
|  		 |	...coordinating the US?					 | RegisterEmployeeController            | Pure Fabrication: it refers to the Controller. there is no reason to assign this responsibility to any existing class in the Domain Model.                           |
| 		 |	...instantianting a new Employee?						 | Company            | Creator (Rule 1): in the DM, Company employ Employee.                          |
| Step 2: asks to type the employee role  		 |	...knowing the available Organization Roles?						 | Company            |   IE: in the DM, Company comprehends Organization Roles, therefore knows the available roles.                           |
| 		 |	...validating the typed role?						 | Company            |   IE: in the DM, Company comprehends Organization Roles, therefore knows the available roles and is able to validate them.                                 |
| Step 3: types the intended role 		 |	...saving the typed role?						 |  Employee           |    IE: object created in Step 1 has/plays a Organization Role.                      |
| Step 4: requests data  		 |	...asking the user for this data?					 |   RegisterEmployeeUI          |   IE: responsible for user interaction.                           |
| Step 5: types requested data   		 |	...validating the data locally (e.g.: mandatory vs. non-mandatory data)?						 |    Employee         |    IE: knows its own data.                          |
| 	 |	...saving the inputted data?						 |    Employee         |    IE: The object created in Step 1 has its own data.               |
| Step 6: shows all data and requests confirmation  		 |	...validating the data globally (e.g.: duplicated)?						 |  Company           |  IE: knows all the Employee objects.                            |              
| Step 7: confirms the data 		 |	...saving the created Employee?						 | Company            |  IE: saves all the ClinicalAnalysisLaboratory objects.                            |
| 		 |	...making the created Employee a user of the system?						 | Company            |  IE: has access to the users data through the AuthFacade.                            |
| Step 8: informs operation success	 |	...informing operation success?						 | RegisterEmployeeUI            |   	IE: responsible for user interaction                           |              



### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Employee
 * Specialist Doctor
 * Company  
 * OrgRole

Other software classes (i.e. Pure Fabrication) identified: 
 * RegisterEmployeeUI  
 * RegisterEmployeeController

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

 * To create an Employee, it is necessary to know the roles available in the system.  
 * Therefore, in order to reduce coupling, it was created a OrgRoleDto.    
 * Employee also has many arguments passing through layers, therefore a DTO could make the maintenance easier.  
 * The password for the user is generated and sent (written in a file) by an method in PasswordUtils since it is
not a responsability of any of domain classes to generate a password or send out data. Respecting Pure Fabrication.
Also being reused in US3.


# 7. Observations

The Company class is getting too much responsability assigned to it. Therefore, creating an EmployeeStore will be taken into consideration in the future to reduce coupling.






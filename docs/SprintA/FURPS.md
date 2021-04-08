# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

- _are common across several US/UC;_
- _are not related to US/UC, namely: Audit, Reporting and Security._



(fill in here)

### Audit
- “Considering that Many Labs has the exclusivity to perform Covid-19 tests, and that the contract with the NHS in England requires Many Labs to summarize and report Covid-19 data, the company needs to: identify the number of Covid-19 tests performed, identify all positive results to Covid-19 tests, report the total number of Covid-19 cases per day, per week and per month of the year and send the forecasts for these same time horizons (…)”

### Localization
- "The application must support the English language only." - It focus on the need of a specific language for the application, despite the location of the user. It has a different impact in the "Usability" in the subcategory "Consistency and standards".

### Email
- "The company is also required to generate daily (automatic) reports with all the information demanded by the NHS and should send them to the NHS using their API."
- "The client receives the notification by SMS and e-mail."

### Help
-	“Therefore, at least two sorting algorithms should be evaluated and documented in the application user manual (in the annexes) that must be delivered with the application.”  
-	“The time complexity analysis of the algorithms should be properly documented in the application user manual (in the annexes) that must be delivered with the application”  
-	“The accuracy of the prediction models should be analysed and documented in the application user manual (in the annexes) that must be delivered with the application.”  

All the three points above mention the necessity for an application user manual, that must be delivered with the application. It has a different impact in another context, namely in the “Usability” category, in the “Help and Documentation” subcategory.

### Printing
- "To facilitate the access to the results, when clients are listed they should be ordered by TIF number or name." - In this context, it refers to the necessity of a functionality that allows access to the results. It has a different impact in the “Usability” Category, in the “Consistency and standards” subcategory.

### Reporting
- "The company is also required to generate daily (automatic) reports with all the information demanded by the NHS and should send them to the NHS using their API."

### Security
- "All those who wish to use the application must be authenticated."  
- "The user authentication password must hold seven alphanumeric characters, including three capital letters and two digits" - It is a password's specificity for user authentication. It has a different impact in another context, namely in the “Others (+)” category, in the “Implementation” subcategory.
- "Only the specialist doctor is allowed to access all client data." - In this context, the system must have the functionality of accessing all client data. It has a different impact in another context, namely in the “Usability” category, in the “Adequacy Of The Interface For Different Types Of Users” subcategory.

### Workflow



## Usability 

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._


(fill in here )

### Adequacy of the interface for different types of users
- “Only the specialist doctor is allowed to access all client data.” – In this context, the system restricts the access to the client data only to the specialist doctor, making it adequate for this user. It has a different impact in another context, namely in the “Functionality” category.

### Interface aesthetics and design
- “All the images/figures produced during the software development process should be recorded in SVG format.” - In this context, the image's format will influence the images' quality in the application. It has a different impact in another context, namely in the “Others (+)” category, in the “Implementation” subcategory.

### Consistency and standards
- “All the images/figures produced during the software development process should be recorded in SVG format.”
- “To facilitate the access to the results, the application must allow ordering the clients by TIF and by name.”  - In this context, it focus on the usability, that is, the facility with which a user can access the results. It has a different impact in the “Functionality” Category, in the “Printing” subcategory.
- "The application must support the English language only." - All the application has to be in English, therefore the consistency aspect. It has a different impact in the "Functionality" in the subcategory "Localization".

### Help and documentation
- “Therefore, at least two sorting algorithms should be evaluated and documented in the application user manual (in the annexes) that must be delivered with the application.”
- “The time complexity analysis of the algorithms should be properly documented in the application user manual (in the annexes) that must be delivered with the application”
- “The accuracy of the prediction models should be analysed and documented in the application user manual (in the annexes) that must be delivered with the application.”

In this context, all three points above mention that the two sorting algorithms, the time complexity analysis of the algorithms and the accuracy of the prediction models, should be correctly and appropriately documented. It has a different impact in another context, namely in the “Functionality” category, in the “Help” subcategory.  

- “use Javadoc to generate useful documentation for Java code.”


## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._


(fill in here )

## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._


(fill in here )

## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._ 



(fill in here )


## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._
  

(fill in here )


### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._


(fill in here )


### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._


(fill in here )

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

(fill in here )
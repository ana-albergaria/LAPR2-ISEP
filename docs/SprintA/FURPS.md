# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

- _are common across several US/UC;_
- _are not related to US/UC, namely: Audit, Reporting and Security._

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
- "The company is also required to generate daily (automatic) reports with all the information demanded by the NHS and should send them to the NHS using their API." - In this context, it focus on the need for generating automatic reports. It has a different impact in the "Others (+)" category in the "Interface Constraints" subcategory.

### Security
- "All those who wish to use the application must be authenticated."  
- "The user authentication password must hold seven alphanumeric characters, including three capital letters and two digits" - It is a password's specificity for user authentication. It has a different impact in another context, namely in the “Others (+)” category, in the “Implementation” subcategory.
- "Only the specialist doctor is allowed to access all client data." - In this context, the system must have the functionality of accessing all client data. It has a different impact in another context, namely in the “Usability” category, in the “Adequacy Of The Interface For Different Types Of Users” subcategory.




## Usability 

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

### Adequacy of the interface for different types of users
- “Only the specialist doctor is allowed to access all client data.” – In this context, the system restricts the access to the client data only to the specialist doctor, making it adequate for this user. It has a different impact in another context, namely in the “Functionality” category.

### Interface aesthetics and design
- “All the images/figures produced during the software development process should be recorded in SVG format.” - In this context, the image's format will influence the images' quality in the application. It has a different impact in another context, namely in the “Others (+)” category, in the “Implementation” subcategory.
- The user interface must be simple, intuitive and consistent.

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

### Frequency and severity of system failures
- The system should not fail more than 5 days in one year.

### Disaster recovery possibility
- Whenever the system fails, there should be no data loss.

### Accuracy
- "The application should implement a brute-force algorithm (…) to determine the contiguous subsequence with maximum sum, for any interval of time registered. The implemented algorithm should be analysed in terms of its worst-case time complexity, and it should be compared to a provided benchmark algorithm.” - – In this context, it focus on the results’ accuracy to be provided in order to make the Many Lab’s application performance better. It has a different impact in another context, namely in the “Performance” category and even another impact in the “+” category, in the “Implementation” category.

## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

### Response time
- Choice of the ordering algorithm is based on the algorithm complexity (mainly the execution time). - In this context, it focus on the fact that the ordering algorithm is based on the execution time. It has a different impact in the "Others (+)" category in the "Implementation" subcategory.
- “The complexity analysis must be accompanied by the observation of the execution time of the algorithms for inputs of variable size in order to observe the asymptotic behaviour.”
- “Therefore, the company wants to decrease the number of tests waiting for its result. To evaluate this, it proceeds as following: (…). Now, the problem consists in determining what the contiguous subsequence of the initial sequence is, whose sum of their entries is maximum. This will show the time interval, in such week, when the company was less effective in responding. (…)"
- Any interface between a user and the system shall have a maximum response time of 3 seconds.

### System start-up time
- The system should start up in less than 10 seconds.

### Memory comsuption
- The application will be deployed to a machine with 8GB of RAM. - In this context, it specifies that the machine which uses the application should have 8GB of RAM.


## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._ 

### Testability
- "The development team must implement unit tests for all methods except methods that implement Input/Output operations." – In this context, it informs the necessity of testing the code. It has a different impact in another context, namely in the “+” category, in the “Implementation” subcategory.
- "The JaCoCo plugin should be used to generate the coverage report" - In this context, it refers to the necessity of having test coverage. It has a different impact in another context, namely in the “+” category, in the “Design” subcategory.

### Compatibility
- The application should run on all platforms for which there exists a Java Virtual Machine. - In this context, it informs that the application is only compatible with Java Virtual Machine.

## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

-	“During system development, the team must:  
(i)	adopt best practices for identifying requirements and for OO software analysis and design;  
(ii)	adopt recognized coding standards (e.g., CamelCase);  
(iii)	use Javadoc to generate useful documentation for Java code.”
- The Diagrams are to be made in UML.

### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._  

* "The application must be developed in Java language using the IntelliJ IDE"
* "The application graphical interface is to be developed in JavaFX 11"
* "The unit tests should be implemented using the JUnit 4 framework."
* "The JaCoCo plugin should be used to generate the coverage report." - In this context, it specifies which plugin is to be used for test coverage. It has a different impact in another context, namely in the “Supportability” category, in the “Testability” subcategory.
* “The development team must implement unit tests for all methods except methods that implement Input/Output operations.” – In this context, it specifies the need for a type of software testing – unit testing. It has a different impact in another context, namely in the “Supportability” category, in the “Testability” subcategory.
* “All the images/figures produced during the software development process should be recorded in SVG format.” - It is a mandatory standard to have all images in SVG format. It has a different impact in the "Usability" category in the "Interface aesthetics and design" subcategory.
* "The application should use object serialization to ensure data persistence between two runs of the application."
* "The implemented (brute-force) algorithm should be analysed in terms of its worst-case time complexity, and it should be compared to a provided benchmark algorithm." 
* “The algorithm to be used by the application must be defined through a configuration file.” – This for the ordering algorithm, the brute-force algorithm and for the linear regression algorithms.
* "For predictions of covid-19, forecasts the linear regression algorithm should be used."
* "The user authentication password must hold seven alphanumeric characters, including three capital letters and two digits."
* “It is intended that the choice of the ordering algorithm is based on the algorithm complexity (mainly the execution time).” - In this context, it refers to the necessity of an ordering algorithm. It has a different impact in the "Performance" category in the "Response Time" subcategory.
* The application should run on all platforms for which there exists a Java Virtual Machine. - In this context, it specifies the need of Java Virtual Machine to run the application.

### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._  

* “the application uses an external module that is responsible for doing an automatic validation using test reference values”
* “Client receives a notification alerting that the results are already available in the central application”
* “The company is also required to generate daily (automatic) reports with all the information demanded by the NHS and should send them to the NHS using their API.” - In this context, the system interacts with the NHS API. It has a different impact in the "Functionality" category, in the "Reporting" subcategory.


### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

* The application will be deployed to a machine with 8GB of RAM. - In this context, it limits the hardware of the machine that uses the application.
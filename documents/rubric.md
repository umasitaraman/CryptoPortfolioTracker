# Crypto Wizards Project Rubric

## Deliverables


| Deliverable                                                     |Due Date                  | Date Completed | URL                                                                                             |
|-----------------------------------------------------------------|---                       |----------------|-------------------------------------------------------------------------------------------------|
| Team name                                                       |Sprint 1 Module 1         | 2/7/2022       | name: Crypto Wizards                                                                            |
| [Design Document - problem statement](design_document.md)       |Sprint 1 Module 2         | 2/8/2022       |                                                                                                 |
| [Team Charter](team_charter.md)                                 |Sprint 1 Modeul 3         | 2/9/2022       | [Team Charter](team_charter.md)                                                              |
| [Design Document](design_document.md)                           |Sprint 1 Friday by 5pm    | 2/10/2022      | [Design Document](design_document.md)                                                           |
| Project Completion (Feature Complete)                           |Sprint 3 Friday by 5pm    | 2/25/2022      | [Crypto Portfolio Tracker](http://cryptoportfolio-frontend.s3-website.us-east-2.amazonaws.com/) |
| [Team Reflection](reflection.md)                                |Sprint 4 Wednesday by 5PM | 3/1/2022       | [Team Reflection](team_reflection.md)                                                           |
| [Accomplishment Tracking (David)](accomplishment_tracking.md)   |Sprint 4 Wednesday by 5PM | 3/1/2022       | [Accomplishment Tracking](david/accomplishment_tracking.md)                                                   |
| [Accomplishment Tracking (Uma)](accomplishment_tracking.md)     |Sprint 4 Wednesday by 5PM | 3/1/2022       | [Accomplishment Tracking](uma/accomplishment_tracking.md)                                                             |
| [Accomplishment Tracking (Shannon)](accomplishment_tracking.md) |Sprint 4 Wednesday by 5PM | 3/1/2022       | [Accomplishment Tracking](shannon/accomplishment_tracking.md)                                                             |
| Self Reflection (David)                                         |Sprint 4 Wednesday by 5PM | 3/1/2022       | n/a (will be submitted via Canvas)                                                              |
| Self Reflection (Uma)                                           |Sprint 4 Wednesday by 5PM | 3/1/2022       | n/a (will be submitted via Canvas)                                                              |
| Self Reflection (Shannon)                                       |Sprint 4 Wednesday by 5PM | 3/1/2022       | n/a (will be submitted via Canvas)                                                              |

## Technical Learning Objectives

### API Design

#### **Design an API to meet the needs of your application.** 
Provide a link to the definition for your endpoints (can be code/configuration, or can be
documentation). List one thing that your team learned about designing a good API.

*Endpoint definition location:* 

[Endpoints](design_document.md)       

*What we learned:*    

As a team when designing an API, we learned to structure data in the most efficient way to minimizing the 
amount of information needed to transfer.

#### **Develop a service endpoint definition that uses complex inputs and outputs.**
Select one of your endpoints and list the operation’s input and output objects.
Under each, list its attributes.

*Endpoint:* GetPortfolioActivity

*Input object(s):* username
* username
* authToken

*Output object(s):* assetQuantityMap
* username
* assetQuantityMap

#### **Given a user story that requires a user to provide values to a service endpoint, design a service endpoint including inputs, outputs, and errors.**
Select one of your endpoints that accepts input values from a client. List the
error cases you identified and how the service responds in each case. Provide at
most 3 error cases

**Use Case:**

As a customer, I want to update my list of assets in my portfolio.

|**Endpoint:**  | UpdatePortfolioActivity   |
|---            |---                  |
|**Error case** |**Service response** |
|Invalid username|PortfolioNotFoundException("[Not Found] Resource not found : Could not find Portfolio")|
|               |UnableToSaveToDatabaseException("[Internal Server Error] Failed : Unable to service request")|


#### **Develop a service endpoint definition that uses query parameters to determine how results are sorted or filtered.** 
List at least one endpoint that allows sorting or filtering of results. 
Which attribute(s) can be sorted/filtered on?

*Endpoint:* GetTransactionsActivity

*Attribute(s):* transactionDate

#### **Determine whether a given error condition should result in a client or server exception.** 
List one client exception and one server exception that your
service code throws. List one condition in which this exception is thrown.

|                       |**Exception** |**One case in which it is thrown** |
|---	                |---	       |---	                               |
|**Client exception:**  |TransactionsNotFoundException("[Not Found] Resource not found : Could not find Transaction History")|Null transactions|
|**Service exception:** |MissingFieldException("[Bad Request] Asset Flag cannot be null")|Null transactions|

### DynamoDB Table Design

####**Decompose a given set of use cases into a set of DynamoDB tables that provides efficient data access.** 
List the DynamoDB tables in your project:

1. users
2. assets  
3. portfolios
4. transactions


#### **Design a DynamoDB table key schema that allows items to be uniquely identified.** 
Describe the primary key structure for your DynamoDB table with the most interesting 
primary key. In a sentence or two, explain your choice of partition/sort key(s).

* The transactions table has username as the primary key and transactionDate as the sort key. 
The transitionDate with allow the user to find transactions on a specific date or sort the 
transactions between a start and end date. _**This will be implemented in phase 2_.

#### **Design the attributes of a DynamoDB table given a set of use cases.** 
List a DynamoDB table with at least 3 attributes. List one relevant use case that uses
the attribute. In one sentence, describe why the attribute is included.

**Table name:** portfolio

**Attributes:**

* Ths username is used as a partition key to get all the assets for that specific user. The assetQuantityMap 
is used to get a list of assetId's with the specific quantity associated with each asset.

| Attribute name |(One) relevant use case |attribute purpose |
|----------------|---                     |---               |
|username   |                       |Get portfolio with username|
|assetQuantityMap|As a customer, I want to view a portfolio holding all my assets with value converted to USD|Get assetId and quantity| 



### DynamoDB Indexes

#### **Design a GSI key schema and attribute projection that optimizes queries not supported by a provided DynamoDB table.** 
In one or two sentences, explain why
you created one of the GSIs that your project uses, including one use case that
uses that index.

The username-asset_id-index is used to retrieve all the users transactions for a specific assetId.

**Table name:** transactions  

**Table keys:** username

**GSI keys:** assetId

**Use case for GSI:** As a customer, I want to view all transactions for an asset in my portfolio.

#### **Implement functionality that uses query() to retrieve items from a provided DynamoDB's GSI.** 
List one of your use cases that uses `query()` on a GSI.

**Table name:** transactions

**Use case for `query()` on GSI:** 

As a customer, I want to view all transactions for an asset in my portfolio.

## Soft(er) Outcomes

#### **Learn a new technology.** 
For each team member, list something new that the team member learned:

| Team Member | Something new the team member learned |   
|-------------|---------------------------------------|
| David       | React, JSON web tokens                |   
| Uma         | DynamoDb queries and GSI              |     
| Shannon     | React for frontend                    |     


#### **Identify keywords to research to accomplish a technical goal | Use sources like sage and stack overflow to solve issues encountered while programming.**
Give an example of a search term that your team might have used to find an
answer to a technical question/obstacle that your team ran into. List the
resource that you found that was helpful.
 
| Search terms           | Helpful resource      |   
|------------------------|-----------------------|
| React                  | reactjs.org           |   
| Chartjs 3.7            | chartjs.org           |     
| CORS policy            | stackoverflow.com     |
| DynamoDb queries       | docs.aws.amazon.com   |
| Mocking Static methods | baeldung.com          |

#### **Find material online to learn new technical topics.** 
List one resource that your team found on your own that helped you on your project.

**Topic:**

API Gateway mapping template and access logging variable reference

**Resource:**

[AWS Documentation](https://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-mapping-template-reference.html)




| Topics                 | Resources             |   
|------------------------|-----------------------|
| React                  | reactjs.org           |   
| Chartjs 3.7            | chartjs.org           |     
| CORS policy            | stackoverflow.com     |
| DynamoDb queries       | docs.aws.amazon.com   |
| Mocking Static methods | baeldung.com          |

#### **Share what was worked on yesterday, the plan for today, and any blockers as a part of a scrum standup.** 
In one or two sentences, describe what your team
found to be the most useful outcome from holding daily standups.

1. The most valuable part of the daily standup was to know where everyone was as with their tasks. 
This gave a good idea how to estimate time for new tasks and to continue to focus on functionality 
versus polishing. 


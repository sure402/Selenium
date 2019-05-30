**Type of Framework:**

It's a hybrid testng framework that uses data driven approach. This framework was designed using Jersey client,maven,testng and other logging,reporting tools

**Powerful features:**

- We can integrate framework with any Continous Integration/Continuous deployment tools
- It has powerful logging functionality that is powered through log4j
- It has powerful reporting functionality that uses extent report
- This framework can support partial and complete regression cycles.
- For getting the data,used JSON file
- We can test with multiple sets of payload and input data by passing them through the dataprovider as a json object.
- For better readability,re-usability, maintainence added Validators to make the code look cleaner.

**Technologies used:**

Java,testng,maven,Gson,Jackson,JsonPath,Jersey client,log4j,Extent report

**Design Patterns used:**

Builder Pattern

**Core Components:**

Client, Data Providers,Request Builders,Validators,Model(For POJO's),Utilities,Test Scripts,log4j.properties,pom.xml,testng.xml,Properties file and Json files for managing the data.


**How the framework Works?**

1. Download the project
2. Right click on testng.xml and use Run as Testng
3. Testng will have different classes, Each class will have different test methods.
4. For each test method, we need following things.
	a. We need multiple sets of data, we pass that data through the data provider as a multiple Json data objects
	b. We need to build the request url
	c. Decide on the type of http request call to make (for example: Get,Post,Put,Delete)
	d. Pass the response to a Validator where it maps to a Pojo class using Gson and validate the response.
	e. Run all the tests, consolidate it and generate extent report out of it.


**Bugs and blockers.**

1.When we post data with all mandatory and optional fields and make a get call based on the movie name thats been passed as a payload in post call, we don't see that movie in the response.
2.Query name exact match doesn't give proper results(bug) for example q=Dante
3.No two movies should have the same image(Send same image through post man call and check).Currently I am blocked with making a post call and using a get call to verify the details.
4.Poster_Path links is not working even If I pass it to a post call.
5.Get call with count- check the no of count of records its giving(bug)
 a. check with negative numbers
 b. Special characters
 c. Alpha Numberic characters


**Good to handle:**

1.Can develop utilities to handle data through excel files if we think we may need to handle huge sets of data. But loading excel files will take time compared to JSON files.
2.Add custom handlers to better handle exception handling mechanism.
3.Add support to parse different types of responses like JSON,XML etc
4.We can add custom annotations for test script like Priority,Author etc.






































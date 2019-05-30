Problem: Need to Automate E2E scenario of walmart labs transaction flow and Validate it.
Solution: Created a flexible framework to automate this scenario.
Aboout the framework:
1.It's a hybrid Testng framework built using the concept of Page Object Model and maven
2.The modules are categorized as packages
3.Created seperate components and locators for each page.
4.Created seperate utils for getting data through excel file as well as Property files
5.Created own utils for custom exceptions handling and wait functions
6.Used Apachi POI libraries to get data from excel file
7.Used Data Provider to run the test script with multiple pools of data
8.Created a test data and stored the dependencies(drivers) and excel files
9.Created a master suite file to run the test scripts
10.This frameowkr is more readable and resuable.
11.We can also extend this framework by adding more functions.

How the framework works:
1.We need to first run the testng.xml file which contains the classes of the test script
2.First it will execute the Annotations(@BeforeClass and @BeforeMethod) present in the BaseTest.java file
3.Then it will call @Test method present in TestWalmartAssignment.java file
4.Here it will call the different functions that's been implemented and also gets data from the data provider
5.The data provider will get the data from the excel sheet in which the logic is implemented in the ExcelUtilsCommon.java
6.Used wait utilities to manage the concept of synchronization.
7.Finally at the end it will call @After class and @After method annotations

Issues faced while Automating:
There are 2 bugs found while automating this application. As a workaround came up with an approach and a template of this scenario. 

Bug-1:(This assignment was given by HR Alan last week)

Steps to Reproduce:
1.Login to the website https://www.walmart.com/account/
2.Give the Username,Password and click on SignIn
3.Verify in the right hand side you will see Hello Username(The username you logged In)- Working as expected
4.Type a keyword in the Search Panel for example : "iPhone" and click on Search button
5.Observe in the right hand side you will be automatically logged out. You will see Hello Sign In- Not working as expected
6.Select an item from the list of items displayed and Try to Add to cart(Observe you wont see the option Add to Cart,since you are not logged In)

Bug-2:
Scenario to automate:(Jason HR as provided me this Home work Assignment yesterday)
1. Perform a search on home page from a pool of key words given below
2. Identify an item from the result set that you can add to cart
3. Add the item to cart and then login using existing account which is set up with at least one shipping address(There is not Add to Cart button before getting logged into the application.
But the scenario says add the item to cart and then login.Tried a work around by logged In as user and searching an item and adding to cart.No luck and we faced Bug-1)
4. Validate that item added is present in the cart and is the only item in the cart
5. Select Ship to Home as shipping method for your order
6. Validate that you are on Payment details page
7. Go back to Cart Page, Remove the item from cart and validate cart is empty
8. Sign out from your account

If time permits How we can ehance this:
1.We can create an utility for validations part.
2.This utility will call the backend service using an API and will validate it using the FE
For example: To validate whether an item is really added to item, We can call the API related to Items present in the Cart and validate it against the items
that appear in the FE.
3.We can use the concept of parameterization and pass different browsers 
4.We Can capture screenshots On test failures or skip by implementing listeners
5.We can implement better logging for debugging purpose using log4j
6.We can support mobile Automation
7.We can integrate this with some other reporting tools like (XSLT) to get a better picture.
8.We can implement more custom exceptions and find a way to handle them.
9.We can add more validation checks.


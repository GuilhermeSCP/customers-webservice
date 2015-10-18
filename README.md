# customers-webservice

##Preparation:

1. Install Maven and make sure it's added to the PATH (https://maven.apache.org/install.html)
2. Install Tomcat (https://tomcat.apache.org/tomcat-7.0-doc/setup.html)
3. Install PostgreSQL (http://www.postgresql.org/download/)
4. Using pgAdmin, create a database called 'Multicert' and then create a table with the following code:
```sql
CREATE TABLE customers
(
  id serial PRIMARY KEY NOT NULL,
  name text NOT NULL,
  nif character(9) NOT NULL UNIQUE,
  address text,
  telephone text
)
```
You might want to change the openDatabaseConnection() function in [CustomerInterface.java](https://github.com/GuilhermeSCP/customers-webservice/blob/master/src/com/multicert/middleman/CustomerInterface.java) to change the user and password for your database.

##Instructions:

1. Pull the code from the repository.
2. Use the command line to navigate to project root folder.
3. Run the following commands :
 ```
mvn package

mvn tomcat:run
 ```
4. Hopefully, you will have the webservice up and running on http://localhost:8080 .

##Available services

Get all customers in the database. Usage: 
```http
GET http://localhost:8080/Multicert/rest/customerservice/getallcustomers
```

Get a customer by NIF. Usage (nif must have 9 digits, no more, no less):
```http
GET http://localhost:8080/Multicert/rest/customerservice/getcustomerbynif?nif=<NIFnumber>
```

Get a customer by name. Usage (case insensitive, will look for similar names):
```http
GET http://localhost:8080/Multicert/rest/customerservice/getcustomerbyname?name=<name>
```

Add a customer to the database. Usage (needs JSON with the new customer data):
```http
POST http://localhost:8080/Multicert/rest/customerservice/addcustomer
{"name":"Martins","nif":"123456789","address":"Multicert","telephone":"912345678"}
```

Deletes a customer from database. Usage (if customer doesn't exist, will return ACCEPTED deleting nothing):
```http
POST http://localhost:8080/Multicert/rest/customerservice/deletecustomer?nif=<NIFnumber>
```

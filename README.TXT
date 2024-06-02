Title: Java Scheduling Application
Purpose: Allows users to manage and schedule appointments for customers. It provides a user-friendly interface for creating, viewing, updating, and deleting appointments, as well as generating various reports related to customer appointments.

Author: Husnain Arif
Contact info: harif1@wgu.edu
Student app version: 1.1
Date: 5/29/2023

IDE: Apache NetBeans IDE 16
JDK: 17.0.6; OpenJDK 64-Bit Server VM 17.0.6+10-LTS
JavaFX: OpenJFX 17
MySQL: MySQL-Connector-J 8.0.33

Additional report:

- A report that shows the total number of appointments scheduled for each customer.

- It executes a SQL query that retrieves the customer ID, customer name, and the count of 
  appointments for each customer by joining the customers and appointments tables. 
  The result set is then looped through to display the report in the user interface.
  Each row of the report includes the customer ID, customer name, and the total number of appointments.


Running the program:

(1) Create a file named "db-config.properties" at the root of the application folder.

Fill it using the format:

db.url=<jdbc:mysql://...>
db.username=<username>
db.password=<password>

(2) Run the application: "java -jar JavaSchedulingApplication.jar"




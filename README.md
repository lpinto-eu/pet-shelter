Pet Shelter
==============
An open source solution for Animal Shelter managment.

Technologies
==============
 * HTML 5
 * CSS 3
 * Bootstrap 3.0
 * Ember.js
 * Java EE7       
 * Maven

How to use
==============
Mysql
---------------
Create a new database

Ember
---------------
 * Customize your REST api url in ApplicationAdapter (src/main/webapp/js/app/application.js)

java
---------------
* Just compile the project to get the war file.
* You have to configura a jdbc connection named jdbc/petshelter
** If you are using glassfish just configure glassfish-resources.xml (src\main\webapp\WEB-INF\glassfish-resources.xml)
* Upload the war file to your application server.

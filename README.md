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
 * Customize your db connection in the ApplicationAdapter (src/main/webapp/js/app/app.js)

java
---------------
* Just compile the project to get the war file.
* In the application server (I am using glassfish) create a new jdbc connection with name: jdbc/petshelter
* Upload the war file to your application server.


# Question 5

>Question 5 (Level 3)
Given the following content of a csv file:
name;bank_identifier
Postbank;10010010
Eurocity;10030700
Commerzbank;10040000
Raiffeisenbank;22163114
-> Write a program which imports the entries of the csv file into a database of your choice (preferably PostgreSQL) 2.
-> Fetch the record with the bank identifier 10040000 from DB and print the name of the related bank to system out.

### Requirements
***DOCKER***
***SBT***

### TO RUN PROJECT
***All commands must be run from the root folder***

Before the project is run , we need to get our Postgres Container as defined in the docker compose file up and running, we do that with
```docker-compose up```

Now I wrote a handy bash script that locates the postgresql container created with the command above and creates a table for storing the bank records. The script can be found in data/deploy.sh

```./data/deploy.sh```

Next we need to supply the path to our CSV file to the application. I decided to go this route rather than hardcoding the path to the file, The CSV file is located at ```data/file.csv``` and  is used when starting the project as shown below.
```sbt "run data/file.csv"```

### Application Logic

The code was designed to be flexible, in that all actions are done via Http Requests, To load the data into the database after the initial set up as described above, a GET http Request to the endpoint will do the job.

```curl http://localhost:8082/api/load```

This endpoint should only be called once to load the data, as any subsequent call will thrown an error because the stream used to read the data is closed after the first request.

To fetch the bank record with the identifier ***10040000*** we simply make a GET Http request with an identifer parameter to the endpoint as shown below

```curl http://localhost:8082/api/fetch?identifier=10040000```.

The result should be ***Commerzbank***

### Tests
I wrote some tests for the application and they are run with
```sbt test```


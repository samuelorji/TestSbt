
# Question 5

>You have an input of the URLs: “/users/Maria/info/location”, “/users/Marcelo/info/birthday” Please propose a design for an algorithm that given a set URL paths(as in example above), can identify dynamic parts and resolve the masked URL to the path like this “/users/*user_name*/info/*info_query*” Algorithm should be as efficient as possible based on consideration about the CPU, Memory and processing time. *user_name* will represent the username values in the path. In our example it is Maria or Marcelo. *info_query* will represent the last part of the path. In our example location or birthday. The URLs can be dynamic, and can have multiple dynamic parts.

### Requirements
***SBT***

### TO RUN PROJECT
***All commands must be run from the root folder***

First we need to supply the path to our CSV file to the application. I decided to go this route rather than hardcoding the path to the file, The CSV file is located at ```data/url.csv``` and  is used when starting the project as shown below.
```sbt "run data/url.csv"```

### Application Logic

The code was designed to be flexible, in that all actions are done via Http Requests, To get the dynamic representation after the initial set up as described above, a GET http Request to the endpoint will do the job.

```curl http://localhost:8082/api/dynamic```

### Tests
I wrote some tests for the application and they are run with
```sbt test```


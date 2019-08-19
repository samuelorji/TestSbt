#!/bin/bash
echo ":::::: Dumping  Data into postgresql"
export POSTGRESQLCONTAINER=$(docker ps  | grep bonify_postgres | cut -d" " -f 1)
cat $(pwd)/data/data.sql | docker exec -i $POSTGRESQLCONTAINER psql -U postgres
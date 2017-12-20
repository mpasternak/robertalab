#!/bin/bash
# start of the openroberta database server in a docker container.

VERSION=''
DB_BASEDIR=/opt/db

echo "startDbServer.sh <x.y.z> <"

VERSION=$1

if [ -z "$VERSION" ]
then
    echo "db version is missing - exit 12"
    exit 12
fi

DATABASE=db-${VERSION}/openroberta-db
echo "the database server will use database directory $DATABASE in base directory $DB_BASEDIR"
java -cp lib/\* org.hsqldb.Server --database.0 file:$DB_BASEDIR/$DATABASE --dbname.0 openroberta-db
#!/bin/bash

if [[ ! $(cat /proc/1/sched | head -n 1 | grep init) ]]
then
   echo 'running in a docker container :-)'
else
   echo 'not running in a docker container - exit 1 to avoid destruction and crash :-)'
   exit 1
fi
VERSION="$1"
if [ -z "$VERSION" ]
then
    echo 'the only one parameter version of form x.y.z is missing - exit 12'
    exit 12
else
    echo "generating the version $VERSION"
fi

# !!!!!!!!!!!!!!!!!! replace iserv by develop !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
cd /opt
git clone --depth=1 -b iserv https://github.com/OpenRoberta/robertalab.git

cd /opt/robertalab/OpenRobertaParent
mvn clean install -DskipTests -DskipITs

cd /opt/robertalab
rm -rf DockerInstallation
./ora.sh --export DockerInstallation

# supplying almost empty database ONLY FOR TESTS. This has no effects on productive systems
cp -r OpenRobertaParent/OpenRobertaServer/dbBase DockerInstallation/db-$VERSION

cp Docker/Dockerfile* Docker/startServer.sh Docker/startDbServer.sh DockerInstallation

cd /opt/robertalab/DockerInstallation
docker build -t rbudde/openrobertalab:$VERSION -f DockerfileLab .
docker build --build-arg version=$VERSION -t rbudde/openrobertaemptydbfortest:$VERSION -f DockerfileEmptyDbForTest .
docker build --build-arg version=$VERSION -t rbudde/openrobertadb:$VERSION -f DockerfileDb .
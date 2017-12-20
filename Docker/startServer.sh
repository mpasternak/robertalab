#!/bin/bash

# start of the openroberta server in a docker container. A -d parameter to the init script determines, whether the server
# runs in embedded or database server mode. NEVER ever set the -d parameter database.parentdir in a docker environment!

echo "startServer.sh [server parameter ...]"
echo "Parameters for the server are: $*"

java  -cp lib/\* de.fhg.iais.roberta.main.ServerStarter $*
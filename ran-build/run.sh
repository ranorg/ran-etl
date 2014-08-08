#!/bin/bash

############### Setup various constants ################

JAVA_HOME=/home/eamitmo/Software/jdk1.7.0
JARDIR=./lib

APP_TIMEOUT_ONSTART=60
COMPONENT_NAME="ETL"
PROCESS_TO_MONITOR="ETL"
LOGO="Huawei MSC"
PATH=$JAVA_HOME/bin:$PATH
########################################################

# Setup java class path
# Add lib directory to class path

libs=`ls $JARDIR/*.jar`
CLASSPATH=.:./conf
for lib in $libs
do
   CLASSPATH=$CLASSPATH":"$lib
done

#echo $CLASSPATH
# Allow remote debug. Commented-out by default.
#JVM_FLAGS="$JVM_FLAGS -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=4000"

JVM_FLAGS="$JVM_FLAGS -DCompType=${COMPTYPE}"
JVM_FLAGS="$JVM_FLAGS -Xms1024m -Xmx1024m"
JVM_FLAGS="$JVM_FLAGS -XX:+UseParallelOldGC"

START_CMD="$JAVA_HOME/bin/java -server -D$PROCESS_TO_MONITOR $JVM_FLAGS -cp $CLASSPATH com.ranorg.etl.core.ETL  -timeout $APP_TIMEOUT_ONSTART"

#############################################################################
# Prints the process id of the process ${PROCESS_TO_MONITOR}
#############################################################################
function getPid() {
    local mypid=`ps auxwwww | grep "$PROCESS_TO_MONITOR" | grep -v grep | awk '{ printf( "%s ", $2 );}'`
    echo "$mypid"
}

#############################################################################
# Logs a message to stdout
#############################################################################
function log() {
    echo "$@"
    
}

#############################################################################
# Show component status.
#
# Return values:
#   0: Component is running
#   1: Component is not running
#############################################################################
function showComponentStatus() {
    nbprocesses=`ps -ef | grep "$PROCESS_TO_MONITOR" | grep -v grep | wc -l`
    if [ ${nbprocesses} -gt 0 ]; then
	log "${COMPONENT_NAME} has ${nbprocesses} instance/s running with process id: $(getPid)"
	return 0
    else
        log "${COMPONENT_NAME} is not running"
        return 1
    fi
}
 
#############################################################################
# Graceful shutdown of the component
#
# Return values:
#   0: Component shutdown signal was sent to the process
#   1: Component is not running
#############################################################################
function stopComponent() {
	
    if [ $# -eq 0 ]; then
	    pid=$(getPid)
		pid0=${pid[0]}
		if [ ! -z "$pid0" ]; then
				#log "Stopping ${COMPONENT_NAME} instance (pid:$pid)"
				kill -15 $pid0
				return 0
		else
			log "${COMPONENT_NAME} is not running"
			return 1
		fi
	else
		echo "usage: $0 {start | stop <redis>| status | smsc}"
    	return 1
	fi
	     
}



function showLogo() {
echo ""
echo ""
echo "                $LOGO                                  "
echo "          Copyright 2013, RAN Org.                                "
echo ""
echo "    .............   .................   ...                       "
echo "    .............   .................   ...                       "
echo "    ...                    ...          ...                       "
echo "    ...                    ...          ...                       "
echo "    ...                    ...          ...                       "
echo "    .............          ...          ...                       "
echo "    .............          ...          ...                       "
echo "    ...                    ...          ...                       "
echo "    ...                    ...          ...                       "
echo "    ...                    ...          ...                       "
echo "    .............          ...          ................          "
echo "    .............          ...          ................          "
echo ""
echo ""
echo ""
}


#############################################################################
# Start the main process of the component
#
# Return values:
#   0: Component process was started
#   1: Component failed to start
#   2: Component is already running
#   3: Wrong user.  Refusing to start component.
#############################################################################
function startComponent() {   
        nbprocesses=`ps -ef | grep "$PROCESS_TO_MONITOR" | grep -v grep | wc -l`   
        if [ ${nbprocesses} -gt 0 ]; then
			log "${COMPONENT_NAME} has ${nbprocesses} instance/s running with process id: $(getPid)"
			return 0
    	else
    		log "    ${COMPONENT_NAME} started. Watching in input directory...."
		echo ""
        	bash -c "(${START_CMD} 2>&1) &"
        	return $?
    	fi           
        
}


case $1 in 
'status')
        showComponentStatus
    ;;
'start')
	showLogo
        startComponent
    ;;
'stop')
        stopComponent $2
    ;;


*)
    echo "usage: $0 {start | stop | status}"
    exit 1
    ;;

esac
exit $?

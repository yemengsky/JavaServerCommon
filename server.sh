#!/bin/bash
now=$(date +%Y%m%d)
command='java -Xms128m -Xmx2048m -jar DataCollection.jar'
log_file_url="./log/${now}_DataCollection.log"

start(){
    if [ "$log_file_url" != "" ]; then
        exec $command  > "$log_file_url" &
    else
        exec $command &
    fi
}

stop(){  
 ps -ef | grep "$command" | awk '{print $2}' | while read pid  
 do 
    C_PID=$(ps --no-heading $pid | wc -l)
    echo "当前PID=$pid"
    if [ "$C_PID" == "1" ]; then
        echo "PID=$pid 准备结束"
        kill -9 $pid
        echo "PID=$pid 已经结束"
    else
        echo "PID=$pid 不存在"
    fi 
 done  
}

case "$1" in  
start)  
start  
;;  
stop)  
stop  
;;    
restart)  
stop  
start  
;;  
*)  
printf 'Usage: %s {start|stop|restart}\n' "$prog"  
exit 1  
;;  
esac

basepath=~/docker/data/servicemonitor
imagename=servicemonitor
containername=servicemonitor

mkdir -p $basepath/{logs,tmp}

docker build -t $imagename .

docker run -itd --name $containername --restart always \
-e SPRING_PROFILES_ACTIVE=prod
-v $basepath:/app \
-v $basepath/logs:/app/logs \
-v $basepath/tmp:/app/tmp \
-p 8522:8080 $imagename

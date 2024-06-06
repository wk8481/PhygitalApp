#!/bin/bash

# Script Name: deployjar.sh
# Description: This script downloads the application JAR and configuration properties from the GCP bucket,
#              and runs the Spring Boot application using nohup.

# Ensure necessary directories exist
mkdir -p /opt /home/nguerin /home/team5

# Check if the script is run by team5, nguerin, or root
current_user=$(whoami)
if [[ "$current_user" != "team5" && "$current_user" != "nguerin" && "$current_user" != "root" ]]; then
    echo "This script must be run as the team5, nguerin, or root user. Current user is $current_user."
    exit 1
fi

# Set permissions for the /opt directory
chown root:root /opt
chmod 755 /opt
setfacl -m u:nguerin:rwx /opt
setfacl -m u:team5:rwx /opt

# Download application JAR and application.properties from the bucket
echo "Downloading application JAR and application.properties..."
gcloud storage cp gs://jar-team-bucket/Team_5_Phygital-0.0.1-SNAPSHOT.jar /opt/Team_5_Phygital-0.0.1-SNAPSHOT.jar
gcloud storage cp gs://jar-team-bucket/application.properties /home/nguerin/application.properties
gcloud storage cp gs://jar-team-bucket/application.properties /home/team5/application.properties

# Check if the application JAR was downloaded successfully
if [ ! -f /opt/Team_5_Phygital-0.0.1-SNAPSHOT.jar ]; then
    echo "Failed to download the application JAR."
    exit 1
fi

# Kill any running instance of the application
pkill -f "java -jar /opt/Team_5_Phygital-0.0.1-SNAPSHOT.jar"

# Determine which application.properties file to use
if [ -f /home/nguerin/application.properties ]; then
    CONFIG_PATH=/home/nguerin/application.properties
else
    CONFIG_PATH=/home/team5/application.properties
fi

# Run the Spring Boot application using nohup
nohup java -Dspring.config.location=file:$CONFIG_PATH -jar /opt/Team_5_Phygital-0.0.1-SNAPSHOT.jar > /opt/phygital.log 2>&1 &

echo "Spring Boot application started successfully."

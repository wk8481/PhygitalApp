#!/bin/bash

# Script Name: deployjar.sh
# Description: This script downloads the application JAR and configuration properties from the GCP bucket,
#              and configures the Spring Boot application as a systemd service.

# Ensure necessary directories exist
mkdir -p /opt /home/team5

# Check if the script is run by team5 or root
current_user=$(whoami)
if [[ "$current_user" != "team5" && "$current_user" != "root" ]]; then
    echo "This script must be run as the team5 or root user. Current user is $current_user."
    exit 1
fi

# Set permissions for the /opt directory
chown root:root /opt
chmod 755 /opt

# Download application JAR and application.properties from the bucket
echo "Downloading application JAR and application.properties..."
gcloud storage cp gs://jar-team-bucket/Team_5_Phygital-0.0.1-SNAPSHOT.jar /opt/Team_5_Phygital-0.0.1-SNAPSHOT.jar
gcloud storage cp gs://jar-team-bucket/application.properties /home/team5/application.properties

# Check if the application JAR was downloaded successfully
if [ ! -f /opt/Team_5_Phygital-0.0.1-SNAPSHOT.jar ]; then
    echo "Failed to download the application JAR."
    exit 1
fi

# Configure the Spring Boot app as a systemd service
cat <<EOD > /etc/systemd/system/phygital.service
[Unit]
Description=Spring Boot Application
After=network.target

[Service]
User=root
ExecStart=/usr/bin/java -Dspring.config.location=file:/home/team5/application.properties -jar /opt/Team_5_Phygital-0.0.1-SNAPSHOT.jar
SuccessExitStatus=143

[Install]
WantedBy=multi-user.target
EOD

# Enable and start the systemd service
systemctl enable phygital
#systemctl start phygital

echo "Spring Boot application started successfully."

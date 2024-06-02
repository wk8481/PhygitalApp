# Download application JAR and application.properties from the bucket
echo "Downloading application JAR and application.properties..."
gcloud storage cp gs://jar-team-bucket/Team_5_Phygital-0.0.1-SNAPSHOT.jar /opt/Team_5_Phygital-0.0.1-SNAPSHOT.jar
gcloud storage cp gs://jar-team-bucket/application.properties /home/nguerin/application.properties

# Configure the Spring Boot app as a systemd service
sudo bash -c 'cat <<EOD > /etc/systemd/system/phygital.service
[Unit]
Description=Spring Boot Application
After=network.target

[Service]
User=root
ExecStart=/usr/bin/java -Dspring.config.location=file:/home/nguerin/application.properties -jar /opt/Team_5_Phygital-0.0.1-SNAPSHOT.jar
SuccessExitStatus=143

[Install]
WantedBy=multi-user.target
EOD'

# Only enabling the service here because of the cloud sql proxy which is set up later
sudo systemctl enable phygital

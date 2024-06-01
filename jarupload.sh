#!/bin/bash
# This script uploads the JAR file and application.properties to a Google Cloud Storage bucket
# Author: William KASASA

BUCKET_NAME="jar-team-bucket"
BUCKET_LOCATION="us"
PROJECT_NAME="integration4-team5"
JAR_FILE_PATH="../build/libs/Team_5_Phygital-0.0.1-SNAPSHOT.jar"
PROPERTIES_FILE_PATH="../src/main/resources/application.properties"

# Check if the JAR file and properties file exist
if [ ! -f "$JAR_FILE_PATH" ]; then
    echo "JAR file not found at $JAR_FILE_PATH! Make sure you have built your project."
    exit 1
fi

if [ ! -f "$PROPERTIES_FILE_PATH" ]; then
    echo "application.properties file not found at $PROPERTIES_FILE_PATH!"
    exit 1
fi

# Function to create a GCS bucket if it does not exist
create_bucket() {
    echo "Checking if bucket '$BUCKET_NAME' exists..."
    if ! gsutil ls -p $PROJECT_NAME -b gs://$BUCKET_NAME &> /dev/null; then
        echo "Bucket '$BUCKET_NAME' does not exist. Creating bucket in location '$BUCKET_LOCATION'..."
        gsutil mb -p $PROJECT_NAME -l $BUCKET_LOCATION gs://$BUCKET_NAME
        if [ $? -eq 0 ]; then
            echo "Bucket created successfully."
        else
            echo "Failed to create bucket."
            exit 1
        fi
    else
        echo "Bucket '$BUCKET_NAME' already exists."
    fi
}

# Function to upload files to the bucket
upload_files() {
    echo "Uploading files to gs://$BUCKET_NAME/..."
    gsutil cp $JAR_FILE_PATH $PROPERTIES_FILE_PATH gs://$BUCKET_NAME/
    if [ $? -eq 0 ]; then
        echo "Files uploaded successfully."
    else
        echo "Failed to upload files."
        exit 1
    fi
}

# Create the bucket if necessary
create_bucket

# Upload files
upload_files

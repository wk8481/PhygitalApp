#!/bin/bash
# This script uploads the JAR file to a Google Cloud Storage bucket
# Author: William KASASA

BUCKET_NAME="jar-team-bucket"
BUCKET_LOCATION="us"
PROJECT_NAME="integration4-team5"
JAR_FILE_PATH="../Team_5_Phygital/build/libs/Team_5_Phygital-0.0.1-SNAPSHOT.jar"

# Check if the JAR file exists
if [ ! -f "$JAR_FILE_PATH" ]; then
    echo "JAR file not found at $JAR_FILE_PATH! Make sure you have built your project."
    exit 1
fi

# Function to create a GCS bucket if it does not exist
create_bucket() {
    echo "Checking if bucket '$BUCKET_NAME' exists..."
    if ! gcloud storage buckets list --project=$PROJECT_NAME --format="get(name)" | grep -q "^$BUCKET_NAME$"; then
        echo "Bucket '$BUCKET_NAME' does not exist. Creating bucket in location '$BUCKET_LOCATION'..."
        gcloud storage buckets create gs://$BUCKET_NAME --project=$PROJECT_NAME --location=$BUCKET_LOCATION
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

# Function to upload the JAR file to the bucket
upload_jar() {
    echo "Uploading $JAR_FILE_PATH to gs://$BUCKET_NAME/..."
    gcloud storage cp $JAR_FILE_PATH gs://$BUCKET_NAME/ --project=$PROJECT_NAME
    if [ $? -eq 0 ]; then
        echo "JAR file uploaded successfully."
    else
        echo "Failed to upload JAR file."
        exit 1
    fi
}

source ./check_for_gcloud.sh
check_for_gcloud

# Create the bucket if necessary
create_bucket

# Upload the JAR file
upload_jar

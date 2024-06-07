#!/bin/bash

# Author: William
# Script Name: dismantle.sh
# Description: This script stops a VM and a Cloud SQL instance on Google Cloud Platform (GCP),
#              and removes all authorized networks from the Cloud SQL instance.

# Function to clear all authorized networks from Cloud SQL instance
clear_sql_authorized_networks() {
    local cloud_sql_instance="$1"

    # Clear all authorized networks for the Cloud SQL instance
    if ! gcloud sql instances patch "$cloud_sql_instance" --clear-authorized-networks; then
        echo "Failed to clear authorized networks for $cloud_sql_instance"
        exit 1
    fi

    echo "Cleared all authorized networks for $cloud_sql_instance"
}

# Function to stop the VM
stop_vm() {
    local vm_name="$1"
    local zone="$2"

    # Stop the VM
    echo "Stopping VM $vm_name..."
    if ! gcloud compute instances stop "$vm_name" --zone="$zone" --quiet; then
        echo "Failed to stop VM $vm_name"
        exit 1
    fi

    echo "VM $vm_name successfully stopped"
}

# Function to stop the Cloud SQL instance
stop_cloud_sql_instance() {
    local cloud_sql_instance="$1"

    # Stop the Cloud SQL instance
    echo "Stopping Cloud SQL instance $cloud_sql_instance..."
    if ! gcloud sql instances patch "$cloud_sql_instance" --activation-policy=NEVER; then
        echo "Failed to stop Cloud SQL instance $cloud_sql_instance"
        exit 1
    fi

    echo "Cloud SQL instance $cloud_sql_instance successfully stopped"
}

# Variables
CLOUD_SQL_INSTANCE="pg1716984918"
VM_INSTANCE_NAME="team5-vm"
ZONE="europe-west1-d"

# Call the function to clear all authorized networks
clear_sql_authorized_networks "$CLOUD_SQL_INSTANCE"

# Call the function to stop the VM
stop_vm "$VM_INSTANCE_NAME" "$ZONE"

# Call the function to stop the Cloud SQL instance
stop_cloud_sql_instance "$CLOUD_SQL_INSTANCE"

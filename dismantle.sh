#!/bin/bash

# Author: William
# Script Name: dismantle.sh
# Description: This script stops a VM and a Cloud SQL instance on Google Cloud Platform (GCP),
#              and removes the VM's IP from the authorized networks of the Cloud SQL instance.

# Function to remove VM's IP from Cloud SQL authorized networks
remove_vm_ip_from_sql_authorized_networks() {
    local cloud_sql_instance="$1"
    local vm_instance_name="$2"
    local zone="$3"

    # Fetch the current authorized networks for the Cloud SQL instance
    local authorized_networks=$(gcloud sql instances describe "$cloud_sql_instance" --format="value(settings.ipConfiguration.authorizedNetworks)")

    # Fetch the IP address associated with the VM instance
    local vm_ip=$(gcloud compute instances describe "$vm_instance_name" --zone="$zone" --format="value(networkInterfaces[0].accessConfigs[0].natIP)")

    # Check if VM IP is obtained successfully
    if [ -z "$vm_ip" ]; then
        echo "Failed to retrieve IP address for VM $vm_instance_name"
        exit 1
    fi

    # Remove the VM's IP address from the authorized networks
    local new_authorized_networks=$(echo "$authorized_networks" | sed "s/$vm_ip\/32//")

    # Ensure there are no trailing commas or spaces
    new_authorized_networks=$(echo "$new_authorized_networks" | sed 's/,,/,/g' | sed 's/,$//' | sed 's/^,//')

    # Update the authorized networks for the Cloud SQL instance
    if [ -z "$new_authorized_networks" ]; then
        if ! gcloud sql instances patch "$cloud_sql_instance" --clear-authorized-networks; then
            echo "Failed to clear authorized networks for $cloud_sql_instance"
            exit 1
        fi
        echo "Cleared all authorized networks for $cloud_sql_instance"
    else
        if ! gcloud sql instances patch "$cloud_sql_instance" --authorized-networks="$new_authorized_networks"; then
            echo "Failed to update authorized networks for $cloud_sql_instance"
            exit 1
        fi
        echo "Successfully removed $vm_ip from authorized networks for $cloud_sql_instance"
    fi
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

# Call the function to remove VM's IP from Cloud SQL authorized networks
remove_vm_ip_from_sql_authorized_networks "$CLOUD_SQL_INSTANCE" "$VM_INSTANCE_NAME" "$ZONE"

# Call the function to stop the VM
stop_vm "$VM_INSTANCE_NAME" "$ZONE"

# Call the function to stop the Cloud SQL instance
stop_cloud_sql_instance "$CLOUD_SQL_INSTANCE"

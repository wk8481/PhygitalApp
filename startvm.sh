#!/bin/bash

# Script Name: start_vm.sh
# Description: This script starts a VM on Google Cloud Platform (GCP).

# Function to start the VM
start_vm() {
    local vm_name="$1"
    local zone="$2"

    # Start the VM
    echo "Starting VM $vm_name..."
    if ! gcloud compute instances start "$vm_name" --zone="$zone" --quiet; then
        echo "Failed to start VM $vm_name"
        exit 1
    fi

    echo "VM $vm_name successfully started"
}

# Variables
VM_INSTANCE_NAME="team5-vm"
ZONE="europe-west1-d"

# Call the function to start the VM
start_vm "$VM_INSTANCE_NAME" "$ZONE"

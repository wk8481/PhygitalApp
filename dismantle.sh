#!/bin/bash

# Function to remove VM's IP from Cloud SQL authorized networks
remove_vm_ip_from_sql_authorized_networks() {
    local cloud_sql_instance="$1"
    local vm_instance_name="$2"

    # Fetch the current authorized networks for the Cloud SQL instance
    local AUTHORIZED_NETWORKS=$(gcloud sql instances describe "$cloud_sql_instance" --format="value(settings.ipConfiguration.authorizedNetworks)")

    # Fetch the IP address associated with the VM instance
    local VM_IP=$(gcloud compute instances describe "$vm_instance_name" --format="value(networkInterfaces[0].accessConfigs[0].natIP)")

    # Check if VM IP is obtained successfully
    if [ -z "$VM_IP" ]; then
        echo "Failed to retrieve IP address for VM $vm_instance_name"
        exit 1
    fi

    # Remove the VM's IP address from the authorized networks
    local NEW_AUTHORIZED_NETWORKS=$(echo "$AUTHORIZED_NETWORKS" | grep -v "$VM_IP")

    # Update the authorized networks for the Cloud SQL instance
    if ! gcloud sql instances patch "$cloud_sql_instance" --authorized-networks="$NEW_AUTHORIZED_NETWORKS"; then
        echo "Failed to update authorized networks for $cloud_sql_instance"
        exit 1
    fi

    echo "Successfully removed $VM_IP from authorized networks for $cloud_sql_instance"
}

# Function to stop the VM
stop_vm() {
    local vm_name="$1"

    # Stop the VM
    echo "Stopping VM $vm_name..."
    if ! gcloud compute instances stop "$vm_name" --quiet; then
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
    if ! gcloud sql instances patch "$cloud_sql_instance" --activation-policy NEVER; then
        echo "Failed to stop Cloud SQL instance $cloud_sql_instance"
        exit 1
    fi

    echo "Cloud SQL instance $cloud_sql_instance successfully stopped"
}

# Variables for firewall rule
FIREWALL_RULE_NAME="http-https-postgresql-firewall"

# Call the function to remove VM's IP from Cloud SQL authorized networks
remove_vm_ip_from_sql_authorized_networks "pg1716984918" "team5-vm"

# Call the function to stop the VM
stop_vm "team5-vm"

# Call the function to stop the Cloud SQL instance
stop_cloud_sql_instance "pg1716984918"




##!/bin/bash
#
## Function to remove VM's IP from Cloud SQL authorized networks
#remove_vm_ip_from_sql_authorized_networks() {
#    local cloud_sql_instance="$1"
#    local vm_instance_name="$2"
#
#    # Fetch the current authorized networks for the Cloud SQL instance
#    local AUTHORIZED_NETWORKS=$(gcloud sql instances describe "$cloud_sql_instance" --format="value(settings.ipConfiguration.authorizedNetworks)")
#
#    # Fetch the IP address associated with the VM instance
#    local VM_IP=$(gcloud compute instances describe "$vm_instance_name" --format="value(networkInterfaces[0].accessConfigs[0].natIP)")
#
#    # Check if VM IP is obtained successfully
#    if [ -z "$VM_IP" ]; then
#        echo "Failed to retrieve IP address for VM $vm_instance_name"
#        exit 1
#    fi
#
#    # Remove the VM's IP address from the authorized networks
#    local NEW_AUTHORIZED_NETWORKS=$(echo "$AUTHORIZED_NETWORKS" | grep -v "$VM_IP")
#
#    # Update the authorized networks for the Cloud SQL instance
#    if ! gcloud sql instances patch "$cloud_sql_instance" --authorized-networks="$NEW_AUTHORIZED_NETWORKS"; then
#        echo "Failed to update authorized networks for $cloud_sql_instance"
#        exit 1
#    fi
#
#    echo "Successfully removed $VM_IP from authorized networks for $cloud_sql_instance"
#}
#
## Function to delete the VM
#delete_vm() {
#    local vm_name="$1"
#
#    # Delete the VM
#    echo "Deleting VM $vm_name..."
#    if ! gcloud compute instances delete "$vm_name" --quiet; then
#        echo "Failed to delete VM $vm_name"
#        exit 1
#    fi
#
#    echo "VM $vm_name successfully deleted"
#}
#
## Function to delete the Cloud SQL instance
#delete_cloud_sql_instance() {
#    local cloud_sql_instance="$1"
#
#    # Delete the Cloud SQL instance
#    echo "Deleting Cloud SQL instance $cloud_sql_instance..."
#    if ! gcloud sql instances delete "$cloud_sql_instance" --quiet; then
#        echo "Failed to delete Cloud SQL instance $cloud_sql_instance"
#        exit 1
#    fi
#
#    echo "Cloud SQL instance $cloud_sql_instance successfully deleted"
#}
#
## Function to delete the firewall rule
#delete_firewall_rule() {
#    local firewall_rule_name="$1"
#
#    # Delete the firewall rule
#    echo "Deleting firewall rule $firewall_rule_name..."
#    if ! gcloud compute firewall-rules delete "$firewall_rule_name" --quiet; then
#        echo "Failed to delete firewall rule $firewall_rule_name"
#        exit 1
#    fi
#
#    echo "Firewall rule $firewall_rule_name successfully deleted"
#}
#
## Variables for firewall rule
#FIREWALL_RULE_NAME="http-https-postgresql-firewall"
#
## Call the function to remove VM's IP from Cloud SQL authorized networks
#remove_vm_ip_from_sql_authorized_networks "pg1716984918" "team5-vm"
#
## Call the function to delete the VM
#delete_vm "team5-vm"
#
## Call the function to delete the Cloud SQL instance
#delete_cloud_sql_instance "pg1716984918"
#
## Call the function to delete the firewall rule
#delete_firewall_rule "$FIREWALL_RULE_NAME"

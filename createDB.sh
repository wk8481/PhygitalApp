#!/bin/bash

# Function to check if VM is running
check_vm_running() {
    local vm_instance_name="$1"
    local zone="$2"
    local vm_status=$(gcloud compute instances describe "$vm_instance_name" --zone="$zone" --format="value(status)")
    if [ "$vm_status" != "RUNNING" ]; then
        echo "VM $vm_instance_name is not running."
        exit 1
    fi
}

# Function to start SQL instance if it's not running
start_sql_instance() {
    local sql_instance="$1"

    # Check if SQL instance is running
    local sql_status=$(gcloud sql instances describe "$sql_instance" --format="value(state)")
    if [ "$sql_status" != "RUNNABLE" ]; then
        echo "Starting SQL instance $sql_instance..."
        gcloud sql instances patch "$sql_instance" --activation-policy=ALWAYS
        echo "SQL instance $sql_instance started successfully!"
    else
        echo "SQL instance $sql_instance is already running."
    fi
}

# Check if VM is running
check_vm_running "team5-vm" "europe-west1-d"

# Start SQL instance if not running
start_sql_instance "pg1716984918"

# Script Name: createDB.sh
# Description: This script sets up a PostgreSQL instance on Google Cloud Platform,
#              authorizes local and VM IP addresses, and creates required databases
#              if they do not exist.

# Fetch public IP address
auth_ip=$(curl -s ipinfo.io/ip)

# Instance name
pg="pg1716984918"

# Zone for the VM instance
zone="europe-west1-d"

# PostgreSQL root user password
pg_password="admin"

# Fetch the IP address of the PostgreSQL instance
pg_ip=$(gcloud sql instances describe $pg --format='value(ipAddresses.ipAddress)')

# Update .pgpass with PostgreSQL credentials
echo "$pg_ip:5432:*:postgres:$pg_password" > ~/.pgpass

# Set permissions for .pgpass
chmod 600 ~/.pgpass

# Provide instructions to connect to PostgreSQL
echo "To connect to the PostgreSQL instance:"
echo "Run the following command in your terminal:"
echo "psql -h $pg_ip -U postgres"
echo "Enter the password when prompted."
echo "Remember to store the password securely in ~/.pgpass."

# Fetch the IP address associated with the VM instance
vm_instance_name="team5-vm"
vm_ip=$(gcloud compute instances describe "$vm_instance_name" --zone="$zone" --format="value(networkInterfaces[0].accessConfigs[0].natIP)")

# Check if VM IP is obtained successfully
if [ -z "$vm_ip" ]; then
    echo "Failed to retrieve IP address for VM $vm_instance_name"
    exit 1
fi

# Patch the PostgreSQL instance to authorize local IP and VM IP
gcloud sql instances patch $pg --authorized-networks="$auth_ip/32,$vm_ip/32" --quiet

# Output confirmation message
echo "Patched PostgreSQL instance $pg to authorize local IP $auth_ip and VM IP $vm_ip"

# Function to create databases if they do not exist
create_db_if_not_exists() {
    local db_name=$1
    db_exists=$(PGPASSWORD=$pg_password psql -h $pg_ip -U postgres -tAc "SELECT 1 FROM pg_database WHERE datname='$db_name'")
    if [ "$db_exists" != "1" ]; then
        PGPASSWORD=$pg_password psql -h $pg_ip -U postgres -c "CREATE DATABASE $db_name;"
        echo "Database $db_name created successfully!"
    else
        echo "Database $db_name already exists. Skipping creation."
    fi
}

create_db_if_not_exists "phygital"
create_db_if_not_exists "phygital_dwh"
create_db_if_not_exists "phygital_actual"

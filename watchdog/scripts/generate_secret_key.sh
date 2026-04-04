#!/bin/bash

# Generate a 256-bit (32-byte) base64-encoded JWT secret key
SECRET_KEY=$(openssl rand -base64 32)

# Output the key (for use in scripts or manual copying)
echo "Generated JWT Secret Key: $SECRET_KEY"

#Optional: Save to a file (for local testing, but don't commit this file!)
echo "$SECRET_KEY" > jwt_secret_key.txt
echo "Key saved to jwt_secret_key.txt (DO NOT COMMIT THIS FILE)"

# For CI/CD: Export as an environment variable
export JWT_SECRET_KEY="$SECRET_KEY"
echo "Exported as JWT_SECRET_KEY environment variable"

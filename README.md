# Crema's Food - Setup Guide

Welcome to Crema's Food project! This guide will walk you through setting up the environment to test the project on your local machine.

## Prerequisites

- Android Studio installed on your machine.
- Docker installed for running the backend server.
- ngrok for exposing the local server to the internet.

## Getting Started

1. **Clone the Repository:**

    ```
    git clone https://github.com/lukesteves92/CremasFoodApp.git
    ```

2. **Open Project in Android Studio:**

    Open Android Studio and navigate to `File` > `Open` and select the cloned project directory.

3. **Run Backend Server:**

    - Navigate to the project directory.
    - Use `docker-compose` to start the backend server by running the following command:

    ```
    docker-compose -f docker-compose.yaml up
    ```

4. **Install ngrok:**

    - Download ngrok from [ngrok.com](https://ngrok.com/download).
    - Unzip the downloaded file.

5. **Configure ngrok:**

    - Open a terminal and navigate to the directory where ngrok is installed.
    - Run the following command to connect ngrok to your account:

    ```
    ./ngrok authtoken YOUR_AUTH_TOKEN
    ```

    Replace `YOUR_AUTH_TOKEN` with your ngrok authentication token.

6. **Expose Backend Server with ngrok:**

    - In the terminal, navigate to the directory where ngrok is installed.
    - Run the following command to expose the backend server:

    ```
    ./ngrok http --host-header=rewrite localhost:5040
    ```

    This will generate a public URL that tunnels traffic to your local backend server.

7. **Configure Android App:**

    - Open the Crema's Food project in Android Studio.
    - Locate the configuration file where the backend URL is defined (e.g., `Constants.java` or `strings.xml`).
    - Replace the backend URL with the ngrok URL generated in the previous step.

8. **Run the App:**

    - Build and run the Crema's Food app in Android Studio.
    - Test the app to ensure it connects to the backend server successfully.

Congratulations! You have successfully set up the environment to test the Crema's Food project on your local machine. If you encounter any issues, feel free to reach out for assistance. Enjoy exploring recipes with Crema's Food!

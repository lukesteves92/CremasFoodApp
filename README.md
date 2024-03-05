# Crema's Food - Setup Guide

Welcome to Crema's Food project! This guide will walk you through setting up the environment to test the project on your local machine.

`Jetpack Compose` & `MVI Architecture`

## Prerequisites

- Android Studio installed on your machine with Java 17.
- Docker installed for running the backend server.
- ngrok for exposing the local server to the internet.

## Getting Started

1. **Install Java 17:**

    ```
    https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
    ```

2. **Clone the Repository:**

    ```
    git clone https://github.com/lukesteves92/CremasFoodApp.git
    ```

3. **Open Project in Android Studio:**

    Open Android Studio and navigate to `File` > `Open` and select the cloned project directory.

4. **Set Java 17 by Default:**

   Open Android Studio and navigate to `File` > `Settings` > `Build, Execution...` > `Build Tools` > `Gradle` and select the new `Gradle SDK` downloaded, Java 17.

5. **Run Backend Server:**

    - Navigate to the project directory by `Git Bash`.
    - Use `docker-compose` to start the backend server by running the following command:

    ```
    docker-compose -f docker-compose.yaml up
    ```

6. **Install ngrok:**

    - Download ngrok from [ngrok.com](https://ngrok.com/download).
    - Unzip the downloaded file.

7. **Configure ngrok:**

    - Open a terminal and navigate to the directory where ngrok is installed.
    - Run the following command to connect ngrok to your account:

    ```
    ./ngrok authtoken YOUR_AUTH_TOKEN
    ```

    Replace `YOUR_AUTH_TOKEN` with your ngrok authentication token.

8. **Expose Backend Server with ngrok:**

    - In the terminal, navigate to the directory where ngrok is installed.
    - Run the following command to expose the backend server:

    ```
    ./ngrok http --host-header=rewrite localhost:5040
    ```

    This will generate a public URL that tunnels traffic to your local backend server.

9. **Configure Android App:**

    - Open the Crema's Food project in Android Studio.
    - Locate the `build.gradle.kts(app)` and set the public URL defined above.
    ```
    buildConfigField(type = "String", name = "URL_BASE", value = "\"https://yoururl.com\"")
    ```
    - Now, locate the `AndroidManifest.xml` and configure your Google Maps API Key. (Check https://cloud.google.com/apis)
    ```
    <meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="" />
    ```

10. **Run the App:**

    - Build and run the Crema's Food app in Android Studio.
    - Test the app to ensure it connects to the backend server successfully.

Congratulations! You have successfully set up the environment to test the Crema's Food project on your local machine. If you encounter any issues, feel free to reach out for assistance. Enjoy exploring recipes with Crema's Food!
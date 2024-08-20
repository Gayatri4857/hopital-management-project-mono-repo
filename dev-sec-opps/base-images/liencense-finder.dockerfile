# Use an official Ruby image as the base image
FROM ruby:2.7

# Install JDK, Maven, and other necessary packages
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven build-essential && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Set JAVA_HOME to point to the JDK
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64

# Update PATH to include the JAVA_HOME/bin directory
ENV PATH=$JAVA_HOME/bin:$PATH

# Install the license_finder gem
RUN gem install license_finder

# Set the working directory
WORKDIR /scan

# Command to run license_finder with the default command being ENTRYPOINT
ENTRYPOINT ["license_finder"]

# Optionally, specify the default command if needed
# CMD ["--help"]  # This is just an example, replace it with your desired default command

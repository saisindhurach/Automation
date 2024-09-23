# Use an official Maven image as the base image
FROM maven:3.8.1-openjdk-11

# Create a working directory inside the container
WORKDIR /usr/src/app

# Copy the pom.xml file to the container
COPY pom.xml .

# Download all the Maven dependencies first
RUN mvn dependency:resolve

# Copy the entire project source code to the container
COPY . .

# Build the project using Maven
RUN mvn clean install

# Command to execute tests using Maven (Rest Assured tests in this case)
CMD ["mvn", "test"]

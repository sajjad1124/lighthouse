# Lighthouse #

This project has been developed for the rescue team during disasters like flood, cyclone, tornado etc.  Our mobile application is enable to show distance of a victim from the data provided by the gateway.  BLE tag will be given to each people who live in a disaster prone area. It makes easier for the rescue team to find a victim during disaster. Our data will be sent to the IBM cloud server where data will be fetched using MQTT protocol. This data will be stored in the database to give updates about the people who aren’t rescued yet and also to maintain supply of foods, medicine and other essentials.

## Usage ##
•	Our app identifies victims who have not been rescued within 300 meters from the rescue team.  
•	People can inquire whether their family members are safely reached at the camp or not. This reduces mental stress and anxiety for families.  
•	Supply chain management becomes efficient and transparent.  
•	Our idea is also useful for storms, cyclone and hurricane disasters.

## System Architecture ##
![alt text](https://github.com/sajjad1124/lighthouse/blob/master/System%20Architecture.JPG?raw=true "System Architecture")

## Getting Started ##

To get started with our project, user will have to use an android phone as we haven't build the application in ios or any other platform.

## Installation ##

Download the Lighthouse mobile application [Lighthouse.apk](https://github.com/sajjad1124/lighthouse/blob/master/Lighthouse.apk).

## Built With ##

The mobile application has been developed using
•	[Gradle](https://gradle.org/) - an open-source build automation system.  

## Running the tests ##
To run the test, user will have to use a BLE tag to see the distance it shows from the gateway. In terms of testing purpose, user's smartphone can also work as a gateway but wintin a small range.

## Deployment ##
Server side scripts running on our local cloud server using the services like MQTT and storing data from IBM Watson platform.

## Contributing ##
We'll welcome enthusiast contributors to guide us who think it’s for a good cause. We’ll provide the procedures for contribution after a competition that we’re participating.

## Versioning ##
All notable changes to this project will be documented in this file.  The format is based on Keep a Changelog and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## Authors ##


## License ##

## Acknowledgments ##
•	[International Federation of Red Cross and Red Crescent Societies (IFRC)](https://media.ifrc.org/ifrc/)  
•	
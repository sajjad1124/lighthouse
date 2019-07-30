# Lighthouse #

This project has been developed for the rescue team during disasters like flood, cyclone, tornado etc.  Our mobile application is enable to show distance of a victim from the data provided by the gateway.  BLE tag will be given to each people who live in a disaster prone area. It makes easier for the rescue team to find a victim during disaster. Our data will be sent to the IBM cloud server where data will be fetched using MQTT protocol. This data will be stored in the database to give updates about the people who aren’t rescued yet and also to maintain supply of foods, medicine and other essentials.

## About The Project Lighthouse ##
[![Watch the video](https://github.com/sajjad1124/lighthouse/blob/master/Preview.JPG)](https://www.youtube.com/watch?v=whsaPtHd9L4&feature=youtu.be)

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

## Server Side Scripts ##

### MQTT JSON Data Sender ###

```python
import paho.mqtt.client as mqtt
from threading import Timer
import time
import json


client =mqtt.Client("shs12345")
# broker= "broker.hivemq.com"
broker= "182.163.112.207"
port=1883

def on_connect(client, userdata, flags, rc):
	print("Connected with result code "+str(rc))

def on_message(client, userdata, message):
    print("topic: "+message.topic+"	"+"payload: "+str(message.payload))
    print('\n')


client.on_connect = on_connect  #attach the callback function to the client object 
client.on_message = on_message	#attach the callback function to the client object 

tempSensorData={
  "msg": "advData",
  "gmac": "Gateway1",
  "obj": [
    {
      "dmac": "1033000A33DD",
      "rssi": "-78",
      "data1": "0201060303AAFE1116AAFE200009D70C00002A5696002A82DA"
    },
    {
      "dmac": "2004000A33DD",
      "rssi": "-70",
      "data1": "0201060303AAFE1116AAFE20000B17150000592D5D00599354"
    },
    {
      "dmac": "3004000A33DD",
      "rssi": "-70",
      "data1": "0201060303AAFE1116AAFE20000B17150000592D5D00599354"
    },
    {
      "dmac": "4004000A33DD",
      "rssi": "-70",
      "data1": "0201060303AAFE1116AAFE20000B17150000592D5D00599354"
    }
  ],
  "seq": 2896
}


tempSensorDataa={
  "msg": "advData2",
  "gmac": "Gateway2",
  "obj": [
    {
      "dmac": "5033000A33DD",
      "rssi": "-78",
      "data1": "0201060303AAFE1116AAFE200009D70C00002A5696002A82DA"
    },
    {
      "dmac": "6004000A33DD",
      "rssi": "-70",
      "data1": "0201060303AAFE1116AAFE20000B17150000592D5D00599354"
    },
    {
      "dmac": "7004000A33DD",
      "rssi": "-70",
      "data1": "0201060303AAFE1116AAFE20000B17150000592D5D00599354"
    },
    {
      "dmac": "8004000A33DD",
      "rssi": "-70",
      "data1": "0201060303AAFE1116AAFE20000B17150000592D5D00599354"
    }
  ],
  "seq": 2897
}

tempSensorDataaa={
  "msg": "advData",
  "gmac": "Gateway3",
  "obj": [
    {
      "dmac": "1133000A33DD",
      "rssi": "-78",
      "data1": "0201060303AAFE1116AAFE200009D70C00002A5696002A82DA"
    },
    {
      "dmac": "124000A33DD",
      "rssi": "-70",
      "data1": "0201060303AAFE1116AAFE20000B17150000592D5D00599354"
    },
    {
      "dmac": "1304000A33DD",
      "rssi": "-70",
      "data1": "0201060303AAFE1116AAFE20000B17150000592D5D00599354"
    },
    {
      "dmac": "1404000A33DD",
      "rssi": "-70",
      "data1": "0201060303AAFE1116AAFE20000B17150000592D5D00599354"
    }
  ],
  "seq": 2898
}


tempSensorDataaaa={
  "msg": "advData2",
  "gmac": "Gateway4",
  "obj": [
    {
      "dmac": "1533000A33DD",
      "rssi": "-78",
      "data1": "0201060303AAFE1116AAFE200009D70C00002A5696002A82DA"
    },
    {
      "dmac": "1604000A33DD",
      "rssi": "-70",
      "data1": "0201060303AAFE1116AAFE20000B17150000592D5D00599354"
    },
    {
      "dmac": "1704000A33DD",
      "rssi": "-70",
      "data1": "0201060303AAFE1116AAFE20000B17150000592D5D00599354"
    },
    {
      "dmac": "1804000A33DD",
      "rssi": "-70",
      "data1": "0201060303AAFE1116AAFE20000B17150000592D5D00599354"
    }
  ],
  "seq": 2899
}

data = json.dumps(tempSensorData)
data2 = json.dumps(tempSensorDataa)
data3 = json.dumps(tempSensorDataaa)
data4 = json.dumps(tempSensorDataaaa)

client.connect(broker, port, 60)
print ("connecting to broker")

# client.loop_start() #start the loop


client.subscribe("sensor/data")
print ("subscribed")


def publish():
    client.publish("sensor/data", data )
    client.publish("sensor/data", data2 )
    client.publish("sensor/data", data3 )
    client.publish("sensor/data", data4 )   # publish
    Timer(5.0, publish).start() # publish every 2 seconds


publish() # initialise the function

# time.sleep(4) # wait
# client.loop_stop() #stop the loop
client.loop_forever() # to maintain continuous network traffic flow with the broker



```

### MQTT JSON Data Receiver ###
```python
import paho.mqtt.client as mqtt
from threading import Timer
import time
import json
import math
from store_Sensor_Data_to_DB import sensor_Data_Handler


client =mqtt.Client("asdasd12334")
# broker= "broker.hivemq.com"
broker= "182.163.112.207"
port=1883



def on_connect(client, userdata, flags, rc):
	print("Connected with result code "+str(rc))

def on_message(client, userdata, message):
    print('\n')
    sensor_Data_Handler(message.topic, message.payload)


client.on_connect = on_connect  #attach the callback function to the client object 
client.on_message = on_message	#attach the callback function to the client object 


client.connect(broker, port, 60)
print ("connecting to broker")

# client.loop_start() #start the loop


client.subscribe("sensor/data")
print ("subscribed")



# time.sleep(4) # wait
# client.loop_stop() #stop the loop
client.loop_forever() # to maintain continuous network traffic flow with the broker



```

### Database Manager ###

```python
import pymysql
import json
import datetime


host = "127.0.0.1"
user = "root"
password = ""
db = "shwapnomq"
# import sqlite3

# # SQLite DB Name
# DB_Name =  "IoT.db"

# #===============================================================
# # Database Manager Class

# class DatabaseManager():
# 	def __init__(self):
# 		self.conn = sqlite3.connect(DB_Name)
# 		self.conn.execute('pragma foreign_keys = on')
# 		self.conn.commit()
# 		self.cur = self.conn.cursor()
		
# 	def add_del_update_db_record(self, sql_query, args=()):
# 		self.cur.execute(sql_query, args)
# 		self.conn.commit()
# 		return

# 	def __del__(self):
# 		self.cur.close()
# 		self.conn.close()

#===============================================================
# Functions to push Sensor Data into Database
time_now = datetime.datetime.now()
time_structured = time_now.strftime("%Y-%m-%d %H:%M:%S")
print(time_structured)
# Function to save Temperature to DB Table
def Temp_Data_Handler(jsonData):
	#Parse Data 
	json_Dict = json.loads(jsonData)

	for key, value in json_Dict.items():
		# print(key)
		#print("Type of value ") #---> str, list, int
		if key == 'gmac':
			gateway_address = value

		if type(value) is list:
			#print('This is a list')
			for obj in value:
				beacon_mac_address = obj['dmac']
				beacon_data = obj['data1']
				hex_temp_data =  beacon_data[30:32]
				temp_data = int(hex_temp_data, 16)
				beacon_rssi = obj['rssi']
				time_now = datetime.datetime.now()
				time_structured = time_now.strftime("%Y-%m-%d %H:%M:%S")
				print(time_structured)
				print(gateway_address)
				print(beacon_mac_address)
				print(temp_data)
				print(beacon_rssi)

				db = pymysql.connect("localhost","root","","shwapnomq")
				cursor = db.cursor()
				sql = "INSERT INTO sensordata(branch_id,device_id,tempdata,timestamp) VALUES(%s,%s,%s,%s)"
				try:
					cursor.execute(sql,(gateway_address,beacon_mac_address,temp_data,time_structured))
					db.commit()
				except:
						db.rollback()
						conn.send(data)
						conn.close()
		
				#Push into DB Table



				# print ("Inserted Temperature Data into Database.")
				# print ("")

	
	# except:
	# 	pass
#===============================================================
# Master Function to Select DB Funtion based on MQTT Topic

def sensor_Data_Handler(Topic, jsonData):
	Temp_Data_Handler(jsonData)



#===============================================================

```

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
All notable changes to this project will be documented in this file.  The format is based on Keep a [Changelog](https://github.com/sajjad1124/lighthouse/blob/master/Changelog) and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## Authors ##
• [Arshadina Najib](https://www.linkedin.com/in/arshadinaumaranajib)  
• [Zakir Hasan Choudhury](https://www.linkedin.com/in/zakir-hasan-choudhury/)  
• [Sagar Chakraborty](https://www.linkedin.com/in/sagar-chakraborty-55155510a/)  
• [Tausif Uddin Ahmed Chowdhury](https://www.linkedin.com/in/tausifua)  
• [Muhammad Sajjad Hussein](https://www.linkedin.com/in/sajjad1124/)  

## License ##
MIT [License](https://github.com/sajjad1124/lighthouse/blob/master/LICENSE)

## Acknowledgments ##
•	[International Federation of Red Cross and Red Crescent Societies (IFRC)](https://media.ifrc.org/ifrc/)  
•	[IBM](https://www.ibm.com/bd-en)
import paho.mqtt.client as mqtt
from threading import Timer
import time
import json
import math
from store_Sensor_Data_to_DB import sensor_Data_Handler



client = mqtt.Client('d:xkusl1:WiFi:did001')
broker= "xkusl1.messaging.internetofthings.ibmcloud.com"
port=1883
client.username_pw_set('use-token-auth', '1qgh0uRc5zHf8p!BVL')
client.connect(broker, port, 60)



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


client.subscribe("iot-2/evt/test/fmt/json")
print ("subscribed")



# time.sleep(4) # wait
# client.loop_stop() #stop the loop
client.loop_forever() # to maintain continuous network traffic flow with the broker



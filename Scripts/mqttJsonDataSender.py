import paho.mqtt.client as mqtt
from threading import Timer
import time
import json



client = mqtt.Client('d:xkusl1:WiFi:did001')
broker= "xkusl1.messaging.internetofthings.ibmcloud.com"
port=1883
client.username_pw_set('use-token-auth', '1qgh0uRc5zHf8p!BVL')
client.connect(broker, port, 60)

payload = {
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





def on_connect(client, userdata, flags, rc):
	print("Connected with result code "+str(rc))

def on_message(client, userdata, message):
    print("topic: "+message.topic+"	"+"payload: "+str(message.payload))
    print('\n')


client.on_connect = on_connect  #attach the callback function to the client object 
client.on_message = on_message	#attach the callback function to the client object 


data = json.dumps(tempSensorData)
data2 = json.dumps(tempSensorDataa)
data3 = json.dumps(tempSensorDataaa)
data4 = json.dumps(tempSensorDataaaa)

client.connect(broker, port, 60)
print ("connecting to broker")

# client.loop_start() #start the loop


# client.subscribe("sensor/data")
# print ("subscribed")


def publish():
    client.publish('iot-2/evt/test/fmt/json', json.dumps(payload))
    Timer(5.0, publish).start() # publish every 5 seconds


publish() # initialise the function


client.loop_forever() # to maintain continuous network traffic flow with the broker



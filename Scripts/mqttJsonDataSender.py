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




import pymysql
import json
import datetime


host = "127.0.0.1"
user = "root"
password = ""
db = "lighthouse"

time_now = datetime.datetime.now()
time_structured = time_now.strftime("%Y-%m-%d %H:%M:%S")
print(time_structured)
# Function to save Temperature to DB Table
def Temp_Data_Handler(jsonData):
	#Parse Data 
	json_Dict = json.loads(jsonData)

	for key, value in json_Dict.items():
		
		if key == 'gmac':
			gateway_address = value

		if type(value) is list:
			#print('This is a list')
			for obj in value:
				beacon_address = obj['dmac']
				beacon_data = obj['data1']
				hex_temp_data =  beacon_data[30:32]
				temp_data = int(hex_temp_data, 16)
				rssi = obj['rssi']
				time_now = datetime.datetime.now()
				time_structured = time_now.strftime("%Y-%m-%d %H:%M:%S")
				
				db = pymysql.connect("localhost","root","","lighthouse")
				cursor = db.cursor()
				sql = "INSERT INTO sensordata(dmac,rssi,decodeddata,timestamp) VALUES(%s,%s,%s,%s)"
				try:
					cursor.execute(sql,(beacon_address,rssi,temp_data,time_structured))
					db.commit()
				except:
						db.rollback()
						conn.send(data)
						conn.close()
		
				

	

def sensor_Data_Handler(Topic, jsonData):
	Temp_Data_Handler(jsonData)



#===============================================================

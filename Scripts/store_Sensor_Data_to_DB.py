# #------------------------------------------
# #--- Author: Pradeep Singh
# #--- Date: 20th January 2017
# #--- Version: 1.0
# #--- Python Ver: 2.7
# #--- Details At: https://iotbytes.wordpress.com/store-mqtt-data-from-sensors-into-sql-database/
# #------------------------------------------

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

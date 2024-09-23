import numpy as np
from flask import Flask
import mysql.connector as mysql

cnx = mysql.connect(user = "root",
                    password = "Dbroot2024.",
                    host = "127.0.0.1",
                    database = "champions")




cnx.cmd_query('''DROP TABLE teams_table''')
cnx.cmd_query('''CREATE TABLE teams_table(
              team_id CHAR(32),
              team_name CHAR(64),
              region CHAR(32),
              PRIMARY KEY(team_id))''')

print(cnx)

# app = Flask(__name__)

# @app.route("/")
# def hello_world():
#     return "<p>Hello, World!</p>"







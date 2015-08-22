from flask import Flask
from flask.ext.sqlalchemy import SQLAlchemy
from config import *
import random
from email import email

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] \
 = 'mysql://%s:%s@%s:%s/%s' \
 % (username, password , host, port, dbname)
                
db = SQLAlchemy(app)

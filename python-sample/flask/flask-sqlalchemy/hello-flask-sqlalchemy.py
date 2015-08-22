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

class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(80), unique=True)
    email = db.Column(db.String(120), unique=True)

    def __init__(self, username, email):
        self.username = username
        self.email = email

    def __repr__(self):
        return '<User %r>' % self.username


def main():
    db.create_all()
    
    v = random.gauss(0, 5)
    username = "user_%f" % v
    email = "address_%f" % v
    user = User(username, email)
    
    db.session.add(user)
    db.session.commit()
    
    users = User.query.all()
    for user in users:
        print user.id, user.username, user.email
    
if __name__ == '__main__':

    main()

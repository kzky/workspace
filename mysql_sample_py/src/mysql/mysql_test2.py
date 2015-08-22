'''
Created on 2012/10/16

@author: kzk
'''

import MySQLdb

if __name__ == '__main__':

    conn = MySQLdb.connect(db="python_test", host="127.0.0.1", port=3306, user="mysql", passwd="mysql")
    cur = conn.cursor()
    cur.execute("select * from book")
    rows = cur.fetchall()
    
    for row in rows:
        print row

    #cur.execute("create table if not exists big3 (id int(11), title varchar(250), obj varchar(66000))")
    
    cur.execute("insert into big3 values(10, 'test', 'obj')")
    
    cur.close()
    conn.close()
    
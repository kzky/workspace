'''
Created on 2012/10/16

@author: kzk
'''

import MySQLdb

if __name__ == '__main__':

    conn = MySQLdb.connect(db="python_test", host="127.0.0.1", port=3306, user="mysql", passwd="mysql")
    conn.cursorclass = MySQLdb.cursors.DictCursor
    cur = conn.cursor()    

    #cur.execute("create table if not exists test (id int(11), title varchar(250))")
    
    cur.execute('insert into test values(100, "test")')
    cur.execute('insert into test values(10000, "test")')
    cur.execute('insert into test values(10000000, "t")')
        
    cur.execute("select * from test")
    rows = cur.fetchall()
    for row in rows:
        print row["id"], row["title"]

    conn.commit() ## default is not commit
    cur.close()
    conn.close()

    
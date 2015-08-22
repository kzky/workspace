//@Grab(group = 'mysql', module = 'mysql-connector-java', version = '5.1.18')
//@GrabConfig(systemClassLoader = true)
import groovy.sql.Sql

def jdbc = 'jdbc:mysql://localhost:3306/orgasmo_dev'
def user = 'mysql'
def pass = 'mysql'
def driver = 'com.mysql.jdbc.Driver'
def sql = 'select * from actresses'

try {
	db = Sql.newInstance(jdbc, user, pass, driver)
    db.connection.autoCommit = false

	db.eachRow(sql){
		println "$it.id, $it.name, $it.ename"
	}
	db.commit()
} catch (e) {
	println e
	db?.rollback()
} finally {
	db?.close()
}
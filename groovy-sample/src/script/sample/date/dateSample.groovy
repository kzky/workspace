import java.text.DateFormat
import java.text.SimpleDateFormat

// simple date format class
println "--- simple date format ---"
DateFormat df = new SimpleDateFormat("yyyy/MM/dd-hh:mm:ss");
Date d = df.parse("1990/09/03-12:03:30");
println d.getTime();

// localもセット
Locale locale = new Locale("ja", "JP", "JP");
//Calendar cal = Calendar.getInstance();
Calendar cal = Calendar.getInstance(locale);
println cal.getClass()

// calenderをlong値を介してセット
println "--- calendar ---"
cal.setTimeInMillis(d.getTime())
println "year: ${cal.get(Calendar.YEAR)}"	// localをjpにすると元号扱いになる
println "month: ${cal.get(Calendar.MONTH)}"
println "week of year: ${cal.get(Calendar.WEEK_OF_YEAR)}"
println "day of month: ${cal.get(Calendar.DAY_OF_MONTH)}"
println "hour: ${cal.get(Calendar.HOUR)}"
println "minute: ${cal.get(Calendar.MINUTE)}"
println "second: ${cal.get(Calendar.SECOND)}"

// timezone
println "--- timezone ---"
TimeZone tz = TimeZone.getDefault();
println tz
println tz.getRawOffset()

Date date = new Date()
println "default: ${date}"
TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"))
println "america/la: ${date}"
TimeZone.setDefault(TimeZone.getTimeZone("GMT"))
println "GMT: ${date}"
TimeZone.setDefaultZone()
println "default: ${date}"

println "--- available time zone ids ---"
for(String id in TimeZone.getAvailableIDs()){
	println id
}


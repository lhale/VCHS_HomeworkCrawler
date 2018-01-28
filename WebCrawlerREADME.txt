    This project uses the following solution technologies:
1) Selenium ChromeDriver 
2) Google Spreadsheet APIs
   (Set up for new Google API project @ https://developers.google.com/sheets/api/quickstart/java)
	Application = other, Name = VCHS_Spreadsheet
	OAuth 2.0 Client ID: 162522261062-pk64ksoen5jae9uaii4qs7cpig9fekv1.apps.googleusercontent.com
	OAuth 2.0 Client secret: 9q64bNFL8lyfIvModcvuQ2zV
   ( Steps to grab the "Google Sheets API Quickstart" project were bypassed )
3) Whatever maven repo libraries are required (manually goes into pom.xml dependencies)

 in order to :

a) Connect to Learn.VCHS via standard login process, and
b) Use a set of seven root links pointing to Tom's seven course
c) Figure out from the Course's schedule (in upper left corner) today's assignments 
d) Keep track of Tom's A/B classes
d) Walk back a few days to fully capture assignments due the next day or so
e) Be aware of weekends, which don't have dates in the assignment schedules

Description of prototype programs:
NOTE: Design of this source code is rather single-file monolithic and as simple as possible. Further
true software development enhancements would be to split the monoliths into fucntionally
separate class files.

VCHS_HomeworkCrawler - 1st iteration opens up a Chrome browser via Selenium libraries and
walks to the class(es) that were uncommented. The idea was to use the JQueryLoader to
load up the VCHS page with Javascript modules that would do the client-side walking. The
JQuery loading was successful, but decided against it for now in favor of continuing along
the Selecnium route. Program hardcoded to Tom's classes and credentials.

VCHS_HomeworkCrawler2 - Iterates through all the classes in ALL_COURSES via Selenium libraries and
walks through the classes looking backwards three days and forward one day. Each teacher
appears to encode the date and the hoemwork items a bit differently, so pains have been taken
in the logic to handle all cases discovered. Program hardcoded to Tom's classes and credentials.

VCHS_HomeworkCrawler3 - a copy of VCHS_HomeworkCrawler2 that uses Tom's Python-based filler
program that updates a Google spreadsheet and makes use of a config file that contains all
the sensitive credential information.

Other prototype programs:
GoogleSpreadsheet1stTry - Multiple failed attempts to use the Google Drive v4 API to open
up a Google spreadsheet and worksheet combo and append rows to it (output of the homework
crawler). Unfortunately, there appears to be mutliple flavors of credentials for different
parts of the API, so I ran out of time getting this working (will use Tom's Python-based
filler program - see VCHS_HomeworkCrawler3 above).
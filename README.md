# Weffer_testplan
//Form Testing
//prerequisite
1: Need java 8 or higher
2. Need eclipse IDE to run maven project
3.In marketplace of eclipse, testNG should be installed and configured 
----------------------------------------------
Download Zip of the code //
unzip the file and copy the weffer folder
Open eclipse IDE
create a worspace 
using windows explorer go to created workspace location and paste the weffer folder
In eclipse go to file menu and click on import 
go to maven and click on Existing maven project and select the weffer folder.


--------------------------
The Main execution file is test.java which is embedded in(src/test/java) weffer.weffer package


--------------Input file---
The input file abcd.csv is in config folder

-------------Output file------
The output file spark.html is in target/new_report folder


--------------Execution---------
right click on test.java ->run as ->get an options as "run as TestNG".
if option is not displayed means the testng configuration is not proper.

After Execution spark.html is updated

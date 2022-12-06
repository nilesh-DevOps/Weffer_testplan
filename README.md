# Weffer_testplan
//Form Testing
//prerequisite
1: Need java 8 or higher
2. Need eclipse IDE to run maven project
3.In marketplace of eclipse, testNG should be installed and configured 
----------------------------------------------
1. Download Zip of the code //
2. unzip the file and copy the weffer folder
3. Open eclipse IDE
4. create a workspace 
5. using windows explorer go to created workspace location and paste the weffer folder
6. In eclipse go to file menu and click on import 
7. go to maven and click on Existing maven project and select the weffer folder.


--------------------------
The Main execution file is test.java which is embedded in(src/test/java) weffer.weffer package


--------------Input file---
The input file abcd.csv is in config folder

1. First row is header.
2. If you want to select multiple position then write QA:Node where in ":" is the separater


-------------Output file------
The output file spark.html is in target/new_report folder


--------------Execution---------
right click on test.java ->run as ->get an options as "run as TestNG".
if option is not displayed means the testng configuration is not proper.

After Execution spark.html is updated

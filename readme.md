By default the program is waiting for arguments from the project directory. 

settings.xml, source-data.tsv are in the resources directory. 

Example of launch:
java Generator.class \src\main\resources\settings.xml \src\main\resources\source-data.tsv \src\main\resources\example-report.txt

Program arguments could be changed for others paths but from the project directory as well. 

The program doesn't implement requirements for the width of the report.

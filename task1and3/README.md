# IV1350_SEMINAR_3_POS
The program is an implementation of an objective-oriented design project in seminar 3 of the course of IV1350 Objective Oriented Design in KTH.

There are two directoried which separatly contains the source code and the tests of the program. The program implements the basic flow, the startup scenario, and the alternative
flow (including 3-4b, 3-4c and 9a) specified in the document with tasks for seminar one and in basis of the design in seminar 2.

## How to run

Run the following command to compile the project
```bash
mvn clean compile
```

Run this following command to test the project
```bash
mvn test
```

Run this following command to run the main file, which contains a fake sale scenario
```bash
mvn compile exec:java -Dexec.mainClass="se.kth.iv1350.pos.startup.Main" -Dexec.args="./log.txt ./revenue_log.txt"
```# IV1350_SEMINAR_4_POS
# IV1350_higher_grade

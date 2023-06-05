# IV1350_Additional_Higher_Grade_Tasks1and3
The program is an implementation of an objective-oriented design project in additional higher grade tasks of the course of IV1350 Objective Oriented Design in KTH.

The task 1 implements the Template Method Pattern for the observers written in task 2a
in seminar 4, task 3 is to implement the unit tests for all the methods with printouts. 

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
```

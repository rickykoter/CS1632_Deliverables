# Deliverable 2
The files containing the Program Code are located in the src directory, and the files containing their corresponding Test Code are located in the test directory. 

# How to build/run the program code:
1. After cloning this repo (https://github.com/rickykoter/CS1632_Deliverables), navigate to the <pathto git dir>/CS1632_Deliverables/Deliverable2/src in your terminal.

2. From there, use the java compiler to compile the Program Code: 
        javac CoffeeMakerQuest.java

3. After compiling the Program Code, run the code using the command:
        java CoffeeMakerQuest

4. The game should be runnin and be playable through the terminal.

# How to build/run the program code:
1.  After cloning this repo (https://github.com/rickykoter/CS1632_Deliverables), navigate to the <pathto git dir>/CS1632_Deliverables/Deliverable2/src in your terminal.

2.  With the mockito and junit jars in the directory along with the test java files, issue the following command to build:
      javac -cp ../src/:junit-4.12.jar:mockito-all-1.10.19.jar C
offeeMakerQuestTest.java RoomTest.java MapTest.java PlayerTest.java FurnitureTest.java ItemTest.java GameTest.java 

3. Afrer building all tests, run the tests with junit using the following command:
      java -cp .:../src/:junit-4.12.jar:mockito-all-1.10.19.jar org.junit.runner.JUnitCore GameTest RoomTest MapTest CoffeeMakerQuestTest ItemTest FurnitureTest PlayerTest

4. The output should be similar to this:
    JUnit version 4.12
    ............................................................
    Time: 0.91

    OK (60 tests) 

  



Note: The comment indenting issues seen through GitHub are not an issue in Eclipse. This is due to mismatched indent sizes (4 versus 8).

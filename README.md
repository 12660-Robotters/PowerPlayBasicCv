# A Basic CV Object Declaration Using the Base FTC Model For The Power Play(2022-2023) Season:

## Installation:


### Android Studio:

Paste into the Teamcode Folder and follow Usage


### OnBotJava/Blocks:

Paste into the Teamcode Folder and Follow Usage


## Usage:

### Java:

First, create an Op Mode, and add this line to the top of the class file

    private PowerplayBasicCv cvClass = new PowerplayBasicCv();

Where you run your code add this line before the waitforstart command, making sure to pass your hardware map as a paramater. 

    cvClass.init(hardwareMap);

Finally Use 
    
    int parkingspot = cvClass.pullcv();

then use parkingspot in your code. 


## A sample OpMode is provided

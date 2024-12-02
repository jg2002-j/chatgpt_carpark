Design a Java project focusing on OOP principles, encapsulation, separation of concerns and general good practice.
It's a car park management system. it tracks the entry and exit times of vehicles and allows searches on historic data. There is a GUI through which all user interaction is done.
The database is to be saved as a comma-separated CSV file, the program should not attempt to store any data in Java, it should read/write to CSV as often as possible, relying on that to check/display/update/amend records.
Each record in the CSV should have a carVRN, entry datetimestamp and exit datetimestamp. Store it in the CSV in the least ambiguous way to minimise the likelihood of errors. Make sure it saves the carVRN one format though so a user can’t enter “LG64 HHY” and “LG64HHY” and it be treated as 2 different number plates as they are the same.
However, also create methods that parse the data into a nicer “readable” format. E.g timestamps should all be displayed to the user as “HH:mm:ss dd-MM-yyyy”
When opened, the app should have 2 buttons - "Admin Mode" or "User Mode"
If the user selects user mode, they can enter their car VRN. This should be validated to make sure the user has entered in any format of acceptable uk number plate.
Once the user has entered a valid UK number plate, the system is to check whether the vehicle is already in the car park or not.
If it is not currently in the car park, write a new record to the csv with this data, exit time should be left blank or some other thing to indicate the car is currently parked.
If the vehicle is currently in the car park, find the matching record in the csv, and update it with an exit time.
In both cases, display a confirmation message in the GUI to the user indicating which action has been taken. Let them return to the main menu after.
If they choose "Admin mode", they must enter a username and password, which needs to be validated. If correctly authenticated they can then choose to:

1. See all currently parked vehicles
   a. Column one should be car vrn
   b. 2 should be entry time (remember to display this in the readable format)
   c. 3 is duration (i.e. current time – entry time) displayed in HH:mm:ss. If multiple days then display as X days and HH:mm:ss
   d. Cost – calculated from duration and an hourly rate (make one up based on average cost for car parks in the UK)
2. Search for a vehicle’s records
   a. Make them enter a valid VRN (since this is used elsewhere in the code, make this a method shared between where its used)
   b. Once valid, show them all the records for this vehicle including any current stays in the car park if applicable
3. Amend a vehicle’s record
   a. Enter a valid vrn, once validated:
   b. Provide a drop-down list of all records
   c. Then ask which field the admin wants to edit
   d. Edit and update that to the csv as requested
   e. Display a confirmation message stating the old values and the updated values
4. Remove any incorrect records
   a. Enter a valid vrn, once validated:
   b. Provide a drop down list of all records
   c. Let them select the one they want to delete
   d. Display a confirmation message stating which record was successfully deleted
   Throughout the whole program, ensure that a vehicle cannot have entered twice or exited twice. I.e they must always enter, then exit before repeating.
   Before proceeding:

- Show me a class diagram written in plantUML
- Show me a user flow diagram written in plantUML
- Show me a sequence diagram written in plantUML
- Design
  Use JavaFX and any third party libraries needed.
  All windows across the program should be a minimum of 600 wide x 400 high pixels. Every window should resize to fit its content though – nothing should be hidden or cropped.
  There should be a minimum of 50 pixels between any one element and the edge of the window.
  Elements should be spaced a minimum of 20 pixels apart.
  I want the whole program to have a retro vibe similar to an arcade console game and the main menu should have this gif as a background: https://i.pinimg.com/originals/6f/94/bc/6f94bc83f0b71571c7317ce76605ced6.gif
  The font used should be https://fonts.google.com/specimen/Space+Grotesk
  I want there to be appropriate animations. E.g. button clicks should have animations that mimic pressing a real button on an arcade machine for example. There should be hover animations too that have a slight fade in/out

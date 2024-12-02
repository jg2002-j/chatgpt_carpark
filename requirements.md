# Design a Java Application for a Car Park Management System

Design and write a car park management system. It should track the entry and exit times of vehicles and allows searches on historic data. There is a GUI through which all user interaction is done. The data is to be saved as a comma-separated (CSV) file.

The .csv file entries should be written to include the following fields:

| CarVRN      | Entry Time        | Exit Time         |
| ----------- | ----------------- | ----------------- |
| Example VRN | Example Timestamp | Example Timestamp |

## Main Menu

When opened, the app should have 2 buttons - "Admin Mode" or "User Mode".

If the user selects user mode, they can enter their car VRN. This should be validated to make sure the user has entered in any format of acceptable uk number plate. If they choose "Admin mode", they must enter a username and password, which needs to be validated. If correctly authenticated they can then choose to:

## User Mode

1. **Enter their Car VRN:**
   - System is to check whether the number plate is a valid UK number plate.
   - If valid, continue to check whether the vehicle is currently parked or not.
   - If the vehicle is **not** currently in the car park, write a new record to the .csv file with this data. Exit time should be left blank, or the record should otherwise be noted as not having exited the car park.
   - If the vehicle **is** currently in the car park, find the matching record in the .csv file (same carVRN and most recent entryTime), and update it with the exit time.
   - The timestamp for entering / exiting should by the system's current time.

In both cases, display a confirmation message in the GUI to the user indicating which action has been taken. Let them return to the main menu after.

## Admin Mode

1. **See all currently parked vehicles:**

   - Display the data in a table for the user.
   - 1st column should be the vehicle VRN.
   - 2nd column should be entry time (displayed in the readable format).
   - 3rd column is duration (i.e. current time – entry time) displayed in `HH:mm:ss`. If multiple days then display as `dd HH:mm:ss`.
   - 4th column is cost – calculated from duration and an hourly rate (Generate one based on average cost for car parks in the UK).

2. **Search for a vehicle’s records:**

   - Make them enter a valid VRN.
   - Once valid, show them all the records for this vehicle, including any current stays if applicable.
   - Display the data in a table for the user.

3. **Amend a vehicle’s record:**

   - Enter a valid Vehicle VRN, and once validated:
   - Provide a drop-down list of all records.
   - Let the admin choose which field to edit.
   - Edit and update that to the csv as requested.
   - Display a confirmation message stating the old values and the updated values.

4. **Remove any incorrect records**
   - Enter a valid vrn, once validated:
   - Provide a drop down list of all records.
   - Let them select the one they want to delete.
   - Display a confirmation message stating which record was successfully deleted.

## Other requirements

- Adhere to OOP principles, making sure shared code is extracted to methods to avoid duplication of code. Ensure each class deals with only things relevant to itself (separation of concerns), and use encapsulation where possible. Avoid the use of `static` unless appropriate.
- A user can use the car park infinite times, however they must always enter then exit - they cannot enter, enter again then exit, or exit, exit again then enter. Throughout the whole program, ensure that this is the case.
- The program should take care to ensure no data is duplicated (either in Java or in the CSV file) or lost.
- It should read/write to CSV as often as possible, relying on that to check/display/update/amend records.
- Store data in the CSV in the least ambiguous way to minimise the likelihood of errors. Make sure it saves the carVRN in one format though so a user can’t enter “AB12 CDE” and “AB12CDE” and it be treated as 2 different number plates as they are the same vehicle.
- Likewise store the date/timestamps in one consistent format.
- However, also create methods that parse the data into a nicer “readable” format. E.g. timestamps should all be displayed to the user as `HH:mm:ss dd-MM-yyyy`.
- Generate the following PlantUML diagrams:
  - Activity
  - Class
  - Component
  - Deployment
  - Object
  - Sequence
  - State
  - Use Case
  - User Flow

# Project Design

- Generate one version using Java Swing and no third-party libraries.
- Generate a completely separate version but this time using JavaFX and any third-party libraries needed. The functionality should be the exact same between the two versions.

### Layout and Spacing

- All windows across the program should be a minimum of 600px wide x 400px high. Every window should resize to fit its content though – nothing should be hidden or cropped. The tables should fit all data horizontally, but can scroll vertically if needed.
- There should be a minimum of 50 pixels between any elements and the edge of the window.
- Elements should be spaced a minimum of 20 pixels apart.

### Colours and Themeing

- I want the whole program to have a retro vibe similar to an arcade console game and the main menu should have [this gif](https://i.pinimg.com/originals/6f/94/bc/6f94bc83f0b71571c7317ce76605ced6.gif) as a background. Download and include this image within the source code if necessary.
- The application should be designed in a "dark" mode.

### Font and Typography

- The font used should be [Space Grotesk](https://fonts.google.com/specimen/Space+Grotesk). Download and include this file within the source code if necessary.
- Buttons should be bold and larger font size than regular text.

### Other requirements

- There should be appropriate animations and transitions.

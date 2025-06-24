**Badminton Match Scheduler**


A simple Android application that helps organize badminton matches by dividing players into teams based on ability levels, available courts, and total playtime. 
The app generates fair rotations ensuring that all players get to play with different partners across the matches.

**Features**


Input number of players, total playing of hours, number of courts

Specify ability level for each player (1 to 5)

Automatic match generation and player rotation

Generates schedules such that players play with different partners when possible

Clean, responsive UI

Handles device rotation and activity lifecycle properly

Developed entirely in Java (no external libraries)

**How It Works**


Input Screen:

Enter total number of players

Enter total hours of play

Enter number of courts available

Generate Player Information:

Enter player name

Enter ability level for each player (1 to 5)

Scheduling Algorithm:

Divides players into teams, balancing ability levels

Calculates the number of rounds based on courts and players

Ensures rotation so that each player partners with as many others as possible

Match View Screen:

Displays the list of matches per round

Indicates which court and who plays where

Scroll or navigate through rounds

Start Match to view timer

**How to Run in Android Studio**


Clone the Repository

bash
Copy
Edit
git clone https://github.com/your-username/badminton-match-scheduler.git
Open in Android Studio

Launch Android Studio

Click File > Open and select the cloned project folder

Let Gradle sync finish

Configure Emulator (Recommended)

Target API level 33+ or a physical Android device

Recommended emulator settings:

Device: Pixel 4 / 5 or similar

API: 33 (Android 13) or higher

RAM: 2GB or more

Run the App

Press the green Run button (or Shift+F10)

Grant storage permissions if prompted

Start scheduling your badminton matches!

**Project Structure**


swift


Copy


Edit


/app/src/main/java/com/example/badmintonapp/


├── MainActivity.java          // Input UI & logic


├── MatchScheduler.java        // Algorithm for generating match schedules


├── Match.java                 // Generating team matches


└── Player.java                // Player info including ability


└── Team.java                  // Team info including combined ability


└── TimerActivity.java         // Match timer logic


└── Title.java                 // Title page logic and UI


/res/layout/


├── activity_main.xml          // Input screen layout


└── activity_timer.xml         // Timer display layout


└── activity_title.xml         // Title display layout

# My Personal Project - Entertainment Archive

My experience with consuming media has always been somewhat messy. Often, I read and watch
several things simultaneously, especially as a show is being released.
Given enough time and a multitude of series to follow, I eventually forget which episode
I was on, or even the title of the series all together.

To assist my fellow media consumers, my personal project will be a desktop application that will
aid avid show watchers and readers **_track_** and **_store_** the user's shows, movies,
books and other entertainment series. Users will be able to create entries for each series with many different tags.
Some tags include an episode/chapter tracker, a rating system, genre tags and even an option to
upload cover art. There will even be options to filter each tags; helping users find which series they have
started. Furthermore, the archive and filter system will help in giving recommendations to
friends!

## User Stories

- As a user, I want to be able to create and add new entries of media to the archive.
- As a user, I want to be able to tag my entries with new or old tags.
- As a user, I want to be able to view my archive entries.
- As a user, I want to be able to sort my archive entries.
- As a user, I want to be able to filter/hide some entries via tags.
- As a user, I want to be able to save the changes to the archive when I want to.
- As a user, I want to be able to load my archive when I want to.
- As a user, I want to be prompted to save my archive when I close the application.

# Instructions for Grader

- Adding Multiple Xs to Y
    - Right-Click
    - Select 'Add'
    - Enter a Title and end marker (episode, chapter etc)
    - Confirm the addition with 'OK' button
    - Repeat the step above for multiple additions


- Viewing List of Xs in Y
    - When Media entries are added to the Archive, they are automatically loaded to be viewed
    - To Sort or Filter the list view:
      - Select 'View' located in the top left corner
      - Select the sort type & fill out the prompt
      - Confirm and the list view will update
    - To Reset the filters:
      - 'View' --> 'Reset'


- Visual Component
  - Any action to modify the Archive will contain an icon on the popup:
    - i.e. Tagging the Media:
      - Left-Click an already existing Media to Select
      - Right Click
      - Select 'Tag'
      - Pop up with a visual component and entries to add tags appears


- Save
    - Select 'File' located in the top left corner
    - Select 'Save'
    - Confirm the save with 'YES' button


- Load
    - Select 'File' located in the top left corner
    - Select 'Load'
    - Confirm the save with 'YES' button 


# Phase 4: Task 2
Tue Nov 28 21:19:08 PST 2023  
Added MOVIE Entry: Kung Fu Panda with end marker 1

Tue Nov 28 21:19:20 PST 2023  
Added SERIES Entry: Pokemon with end marker 65

Tue Nov 28 21:19:42 PST 2023  
Pokemon's progress updated to 17

Tue Nov 28 21:19:42 PST 2023  
Pokemon's end marker updated to 65

Tue Nov 28 21:19:42 PST 2023  
Pokemon's rating updated to 9.2

Tue Nov 28 21:19:59 PST 2023  
Tag: cool added

Tue Nov 28 21:19:59 PST 2023  
Added tag: cool to Pokemon

Tue Nov 28 21:20:12 PST 2023  
Added tag: cool to Kung Fu Panda

Tue Nov 28 21:20:16 PST 2023  
Whitelisted for cool

Tue Nov 28 21:20:24 PST 2023  
Sorted by Progress Percent - Ascending

# Phase 4: Task 3

At the end of Phase 3, I noticed I had an unnecessary class, "EntertainmentTracker," in the UI that contained a single 
archive field and the save() and load() methods. Ideally, this class would be combined with my main UI class and 
directly create the archive field rather than creating a class that contained just that field. I noticed that this 
extra class forced me to make unnecessary getter calls to the EntertainmentTracker field and then call the method I 
really wanted. (Note: I did correct this before I began Task 2 of Phase 4. This can be confirmed via comparing this
commit to my final Phase 3 commit.)

Another thing I wished I figured out how to do or had more time to do is create an abstract class for the TopMenu 
Actions or somehow combine the Table actions and the TopMenu Actions into an abstract. I think this would be better
because they do similar jobs and would reduce code duplication.

Likewise, looking back on my program, many ui classes have fields to the main EntertainmentTrackerUI to only access 
the Archive field. I could have given these classes an Archive field instead of having them call the getter as an extra 
step.
# ConferenceTrackManagement
I choose to solve the problem of "Conference Track Management".

Here's my thought:

Step 1: load the input file to get all the talks, store these talks in a list of string
Step 2: Transfer the list of string into a list of Talk object
		splitting the string by numerics that with a SPACE prefix, like " 45"
Step 3: sort the talk list by descending order in duration of each talk
Step 4: arrange the morning session of each track
Step 5: arrange the afternoon session of each track
Step 6: print the result


How to build & run the code?

Step 1: unzip the "Conference_Track_ Management_by_ShiPenglong.zip" into a directory, like "test", then start eclipse
Step 2: File>>Import>>General>>Existing Projects into Workspace
Step 3: choose the unziped directory, like "test", click ok.
Step 4: the project's name is "ConferenceManagement"
Step 5: run the "com.spl.conferencemanagement.bootstrap.Main.java" to start to process
Step 6: you can run test case by runing ALLtest
Step END
Feature: Check if any new video of 2180p resolution is been updated in 1337x

 # The data within quotes can be changes and the code will search for the specified file in 1337x
 #keywork search and file name should be same
  @data1
  Scenario: Go to 1337x and download all 2160p named file name along with torrent uri
    Given user navigates to 1337x website
    Then user searches for all files with "2160p" as keyword
    Then user browses all the files along with torrent link to writes to file named "2160p.txt"
    Then user closes the website


  @data
  Scenario: Go to 1337x and check if there is new update in the file list
    Given user navigates to 1337x website
    Then user searches for all files with "2160p" as keyword
    Then user reads the previously stored data from file named as "2160p.txt" and stores data in Hashmap
    Then user check change in data or update in data and adds them to the file
    Then user closes the website


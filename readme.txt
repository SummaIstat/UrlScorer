**************************************
*****     UrlScorer readme       *****
**************************************

======================================================================
What is UrlScorer and what is it intended to be used for
======================================================================

UrlScorer is a Java program that reads one by one all the documents contained in a specified Solr collection and assigns to each of them a score on the basis of some predetermined rules.

Each document in the specified Solr collection is supposed to contain the textual content of a webpage related to an enterprise.

In order to assign a score to a particular document the program must have a set of information about the firm whose the page refers to.

For each document the SW generates a vector of numbers, something like this :

	207101

Every position in the vector contains a number that means “a specific element/characteristic in the web page was found or not” (eg. presence of the telephone number)

For each element we calculated the confusion matrix that would have been obtained if we used just that element for classification purposes.

Based on that confusion matrix we computed standard performance measures: 
	precision
	recall
	fmeasure

Then we assigned as raw weights to elements the f-measures of the corresponding confusion matrixes, lastly we have normalized the raw weights so that the sum of the final (adjusted) weights is 1000.

In order to assign a final score to a link we summed the normalized weights of those elements that were actually present in the vector.

======================================================================
How is the project folder made
======================================================================

The UrlScorer folder is an Eclipse project ready to be run or modified in the IDE (you just have to import the project "as an existing project" and optionally change some configuration parameters in the code).

Ignoring the hidden directories and the hidden files, under the main directory you can find 4 subdirectories :
1) src => contains the source code of the program
2) bin => contains the compiled version of the source files
3) lib => contains the jar files (libraries) that the program needs
4) sandbox => contains the executable jar file that you have to use in order to launch the program and some test input files that you can modify on the basis of your needs

As you probably already know it is definitely not a good practice to put all this stuff into a downloadable project folder, but i decided to break the rules in order to facilitate your job. Having all the stuff that will be necessary in just one location and by following the instructions you should be able to test the whole environment in 5-10 minutes.

======================================================================
How to execute the program on your PC by using the executable jar file
======================================================================

If you have Java already installed on your PC you just have to apply the following instruction points:

1) create a folder on your filesystem (let's say "myDir")

2) copy the following files from sandbox directory to "myDir" :
	
	firmsInfo.txt
	provinces.txt
	UrlScorer.jar
	urlScorerConf.properties 
	
3) customize the parameters inside the urlScorerConf.properties file :
	    
    Change the value of the parameters under the "paths section" according with the position of the files and folders on your filesystem.
    
4) customize the content of the provinces.txt file :

	Each line in this file represents a province, the format of each line must be the following:
	
	provinceAbbreviation TAB provinceName
	
	eg:
	PZ	Potenza

5) customize the content of the firmsInfo.txt file :

	Each line in this file represents a firm, the format of each line must be the following:
	
	FIRM_ID	VAT_CODE	TAB
	NAME	TAB
	CERTIFIED_MAIL	TAB
	MUNICIPALITY	TAB
	PROVINCE_ABBREVIATION	TAB
	TELEPHONE_PREFIX	TAB
	TELEPHONE_NUMBER	TAB
	ZIP_CODE
	
	eg:
	123	myFirmName	myFirmName@certified.it	myMunicipality	AA	098	123456	7890
	452	myFirmName2		myMunicipality2	BB			7890
	
6) make sure that the Solr installation containing the documents you want to score is up and running

7) open a terminal, go into the myDir directory, type and execute the following command:

        java -jar UrlScorer.jar urlScorerConf.properties

8) at the end of the program execution you should find inside the directory myDir a txt file called linksScores_[dateTime].txt

	The format of the txt file (actually it will be a TSV file) will be the following :

	FIRM_ID	LINK_POSITION	URL	SCORE_VECTOR	SCORE


======================================================================
LINUX			
======================================================================

If you are using a Linux based operating system open a terminal and type on a single line :

java -jar 
-Xmx_AmountOfRamMemoryInMB_m
/path_of_the_directory_containing_the_jar/UrlScorer.jar 
/path_of_the_directory_containing_the_conf_file/urlScorerConf.properties

eg:

java -jar -Xmx2048m UrlScorer.jar urlScorerConf.properties

java -jar -Xmx1024m /home/summa/workspace/UrlScorer/sandbox/UrlScorer.jar /home/summa/workspace/UrlScorer/sandbox/urlScorerConf.properties


======================================================================
WINDOWS			
======================================================================

If you are using a Windows based operating system you just have to do exactly the same, the only difference is that you have to substitute the slashes "/" with the backslashes "\" in the filepaths.

eg:

java -jar -Xmx1536m C:\workspace2\UrlScorer\sandbox\UrlScorer.jar C:\workspace2\UrlScorer\sandbox\urlScorerConf.properties

======================================================================
LICENSING
======================================================================

This software is released under the European Union Public License v. 1.2
A copy of the license is included in the project folder.

======================================================================
Considerations
======================================================================

This program is still a work in progress so be patient if it is not completely fault tolerant; in any case feel
free to contact me (donato.summa@istat.it) if you have any questions or comments.

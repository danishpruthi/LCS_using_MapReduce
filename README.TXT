****************************************************************************************

Problem Statement : 

Given a collection of N text documents, an intersection of content has to be obtained as defined below:
Assume each document (i.e. file) is a sequence of lines (i.e. each line is a text string that is treated as one entry in the sequence.).
Compute the pair-wise length of LCS (longest common-sub-sequence) for the N documents.
Select the top K pairs with largest lengths. Assume K << N.
Compute the pair-wise LCS (longest common-sub-sequence) for the K' documents involved in the top K pairs. Note that  K' <= 2*K.
Compute an intersection of the content in the K' documents i.e. the items that occur in all K' documents if any.



****************************************************************************************
Instructions on how to run the file : 


1. The entire package called "Parallel" should be imported in eclipse with a hadoop add-on
2. The path of the directory where the input files are taken as command lines that is : args[0]
3. The program uses 2 Map Reduces; There are hence two Drivers,Mappers,Reducers.
4. The DriverMain.java has the main function
5. The value of k can be changed from the file Driver1.java


****************************************************************************************


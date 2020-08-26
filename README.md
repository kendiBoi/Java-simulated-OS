# Java-simulated-OS
main class is OperatingSystem, it creates the processes where the process gets an
assigned processID it is then added to the process table and then added to the scheduler class
that sorts the processes.

The scheduler which works on a first come first serve (FCFS) when sorting always checks if
it’s arraylist “S” is empty by peek() if it is empty then the scheduler is done and everything
stops if it is not empty the scheduler checks the peek for 3 ProcessStates if its “new” then it's
sets its to “ready” and then to “running” and then it lets the process that is a thread start().

The process ID determines when running which type of process to run and just like in
milestone 1 each process at some point calls the systemCalls methods but what we have
implemented is that before each code section we implemented a semWait for each resource
used i.e semPrintWait(); semReadWait(); semTakeWait(); for
“OperatingSystem.printText(OperatingSystem.readFile(OperatingSystem.TakeInput()));”
And followed after with a semPost method for each resource used when the process is
finished it sets its process state to terminated.

semWait checks if the resource is free or not if it is then it sets the flag if the resource is
already being used then the process is added to that specific resource table and its process
state is set to Waiting and then suspended with suspend().

semPost sets the flag to false freeing the resource and then calls a method to check that
specific table if it is not empty then the process at the top is set to ready and is added to
Scheduler’s “S” and the process is also removed from the table.

The scheduler has 2 else if conditions beside the first if it checks if the processState is ready
and then it sets the processState to running and resumes with resume(); and if that is not true
it checks if the processState is terminated it then removes the process from “S” and calls
sort() again

Process 1
It should take input from the user: a filename. Then print the content of this file on the screen.

Process 2
It should take two inputs from the user: a filename, and some data. Then write the data to the
file.

Process 3
It should count and display to the user the numbers from 0 to 300.

Process 4
It should count and display to the user the numbers from 500 to 1000.

Process 5
It should take two inputs from the user: a lower number and a larger number. Then count
from the lower number to the upper number and write the count to a new file.

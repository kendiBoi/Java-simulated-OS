package Osman;
import java.util.ArrayList;
//import java.util.concurrent.Semaphore;
//import Osman.OperatingSystem;
public class Process extends Thread {
	
	public int processID;
	ProcessState status=ProcessState.New;
	public boolean read = false;
	public boolean write = false;
	public boolean print = false;
	public boolean take = false;
	
	public Process(int m) {
		processID = m;
	}
	
	@Override
	public void run() {

		switch(processID)
		{
		case 1:process1();
		case 2:process2();
		case 3:process3();
		case 4:process4();
		case 5:process5();
		}

	}

	private void process1() {
	//	setProcessState(this,ProcessState.Running);
		semPrintWait();
		semReadWait();
		semTakeWait();
		OperatingSystem.printText("Enter File Name: ");		
		OperatingSystem.printText(OperatingSystem.readFile(OperatingSystem.TakeInput()));
		semPrintPost();
		semReadPost();
		semTakePost();
		setProcessState(this,ProcessState.Terminated);	}

	private void process2() {
		semPrintWait();
		semTakeWait();
		semWriteWait();
		
		OperatingSystem.printText("Enter File Name: ");
		String filename= OperatingSystem.TakeInput();
		OperatingSystem.printText("Enter Data: ");
		String data= OperatingSystem.TakeInput();
		OperatingSystem.writefile(filename,data);
		
		semPrintPost();
		semTakePost();
		semWritePost();
		setProcessState(this,ProcessState.Terminated);
		
	}
	private void process3() {
		
		//setProcessState(this,ProcessState.Running);
		semPrintWait();

		int x=0;
		while (x<301)
		{ 
			OperatingSystem.printText(x+"\n");
			x++;
		}
		
		semPrintPost();
		setProcessState(this,ProcessState.Terminated);
	}

	private void process4() {
	//	setProcessState(this,ProcessState.Running);
		semPrintWait();

		int x=500;
		while (x<1001)
		{
			OperatingSystem.printText(x+"\n");
			x++;
		}	
		
		semPrintPost();
		setProcessState(this,ProcessState.Terminated);
	}
	private void process5() {
	//	setProcessState(this,ProcessState.Running);
		semPrintWait();
		semTakeWait();
		semWriteWait();

		OperatingSystem.printText("Enter LowerBound: ");
		String lower= OperatingSystem.TakeInput();
		OperatingSystem.printText("Enter UpperBound: ");
		String upper= OperatingSystem.TakeInput();
		int lowernbr=Integer.parseInt(lower);
		int uppernbr=Integer.parseInt(upper);
		String data="";
		
		while (lowernbr<=uppernbr)
		{
			data+=lowernbr++ +"\n";
		}	
		OperatingSystem.writefile("P5.txt", data);
		semPrintPost();
		semTakePost();
		semWritePost();

		setProcessState(this,ProcessState.Terminated);
	}

	public static void setProcessState(Process p, ProcessState s) {
		p.status=s;
		if(s == ProcessState.Waiting) {
			// p.suspend();
			 Scheduler.S.remove(Scheduler.S.indexOf(p));
		 }
	/*	if (s == ProcessState.Terminated)
		{
			
			//OperatingSystem.ProcessTable.remove(OperatingSystem.ProcessTable.indexOf(p));
			//Scheduler.S.remove(Scheduler.S.indexOf(p));
		}
		else if(s == ProcessState.Ready){
			s = ProcessState.Running;
		}
		/*else if(s == ProcessState.Waiting) {
			 p.suspend();
		 }*/
	}

	public static ProcessState getProcessState(Process p) {
		return p.status;
	}

	
	public  void semReadWait() {
		if(read == false) {
			read = true;
		}
		else {
			OperatingSystem.ReadTable.add(this);
			setProcessState(this,ProcessState.Waiting);
		}
	}

	public  void semReadPost() {
		read = false;
		checkRead();
	}

	public  void semWriteWait() {
		if(write == false) {
			write = true;
		}
		else {
			OperatingSystem.WriteTable.add(this);
			setProcessState(this,ProcessState.Waiting);

		}
	}

	public  void semWritePost() {
		write = false;
		checkWrite();
	}

	public  void semPrintWait() {
		if(print == false) {
			print = true;
		}
		else {
			OperatingSystem.PrintTable.add(this);
			setProcessState(this,ProcessState.Waiting);

		}
	}

	public  void semPrintPost() {
		print = false;
		checkPrint();
	}

	public  void semTakeWait() {
		if(take == false) {
			take = true;
		}
		else {
			OperatingSystem.TakeTable.add(this);
			setProcessState(this,ProcessState.Waiting);

		}
	}

	public  void semTakePost() {
		take = false;
		checkTake();
	}
	
	public void checkRead() {
		if( !OperatingSystem.ReadTable.isEmpty()) {
		setProcessState((Process) OperatingSystem.ReadTable.get(0),ProcessState.Ready);
		Scheduler.S.add(OperatingSystem.ReadTable.get(0));
		//OperatingSystem.ReadTable.get(0).resume(); //for testing the semwait and sempost methods
		OperatingSystem.ReadTable.remove(0);
		}
	}
	
	public void checkWrite() {
		if( !OperatingSystem.WriteTable.isEmpty()) {
		setProcessState((Process) OperatingSystem.WriteTable.get(0),ProcessState.Ready);
		Scheduler.S.add(OperatingSystem.WriteTable.get(0));
		//OperatingSystem.WriteTable.get(0).resume(); //for testing the semwait and sempost methods
		OperatingSystem.WriteTable.remove(0);
		}
	}
	public void checkPrint() {
		if( !OperatingSystem.PrintTable.isEmpty()) {
		setProcessState((Process) OperatingSystem.PrintTable.get(0),ProcessState.Ready);
		Scheduler.S.add(OperatingSystem.PrintTable.get(0));
		//OperatingSystem.PrintTable.get(0).resume(); //for testing the semwait and sempost methods
		OperatingSystem.PrintTable.remove(0);
		}
	}
	
	public void checkTake() {
		if( !OperatingSystem.TakeTable.isEmpty()) {
		setProcessState((Process) OperatingSystem.TakeTable.get(0),ProcessState.Ready);
		Scheduler.S.add(OperatingSystem.TakeTable.get(0));
		//OperatingSystem.TakeTable.get(0).resume(); //for testing the semwait and sempost methods
		OperatingSystem.TakeTable.remove(0);
		}
	}
	
}

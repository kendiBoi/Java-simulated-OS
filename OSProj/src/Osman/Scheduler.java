package Osman;
import java.util.ArrayList;

public class Scheduler{
	public static ArrayList<Thread> S;
	
	public Scheduler(){
		S = new ArrayList<Thread>();
	}
	public static Thread peek(ArrayList<Thread> N){
		return N.get(0);
	}
	
	@SuppressWarnings("deprecation")
	public static void sort(){
		if(peek(S) != null){
			if(Process.getProcessState((Process)peek(S)) == ProcessState.New){
				Process.setProcessState((Process)peek(S),ProcessState.Ready);
				Process.setProcessState((Process)peek(S),ProcessState.Running);
				peek(S).start();
			}
			else if(Process.getProcessState((Process)peek(S)) == ProcessState.Ready){
				Process.setProcessState((Process)peek(S),ProcessState.Running);
				peek(S).resume();
			}
			else if(Process.getProcessState((Process)peek(S)) == ProcessState.Terminated){
				S.remove(peek(S));
				sort();
			}
		}
	}
}

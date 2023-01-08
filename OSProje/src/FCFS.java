import java.util.ArrayList;

public class FCFS extends Queues {

	public FCFS (int priority) {
		super(priority);
		// TODO Auto-generated constructor stub
	}


	public Process GetProcess(int timeControl)
	{			
		ArrayList<Process> clone =new ArrayList<>();
		clone.addAll(processList);						

		
		clone.sort(Process.getComparation());
		
	
		for(int i=0;i<clone.size();i++)
		{
			Process process=clone.get(i);
			if(process.remainingTime==0 || process.arrivialTime>timeControl) continue;
			return process;			
		}
		
		return null;
	}

	@Override
	public void Operate(Process pp) {
		pp.ProcessExecute();		
	}
	
}

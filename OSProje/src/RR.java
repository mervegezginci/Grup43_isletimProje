import java.util.ArrayList;
public class RR extends Queues{

ArrayList<Process> runProcess;
	
	public RR (int priority) {
		super(priority);
		// TODO Auto-generated constructor stub
		runProcess = new ArrayList<Process>();
		
	}
	ArrayList<Process> readyProcess(int timeControl) {
		var clone = new ArrayList<Process>();
	// Süreye bağlı olarak  kalansüresi 0'a eşit veya geliş zamını kontrol edilen zamandan büyük olunca ekler.
		for (int i = 0; i < processList.size(); i++) {
		     Process process = processList.get(i);
		     if (process.remainingTime == 0 || process.remainingTime > timeControl)
			continue;
			clone.add(process);
		}
		return clone;
	}

	@Override
	public Process GetProcess(int timeControl) {
		// TODO Auto-generated method stub
		ArrayList<Process> clone = new ArrayList<Process>();
		clone.addAll(processList);
		var workingProcess = readyProcess(timeControl);
	
		if (Terminated(workingProcess))
			   runProcess.clear();
			// Listeyi sıraladık.
			clone.sort(Process.getComparation());
			// Kalan zaman 0'a eşit ya da geliş zamanı kontrol edilen zamandan büyükse devam eder.
			for (int i = 0; i < clone.size(); i++) {
				Process process = clone.get(i);
				if (process.remainingTime == 0 || process.arrivialTime > timeControl)
				    continue;
			// Çalışan processlerin tekrar çalışmaması için kontrol ediyoruz.
				if (runProcess.contains(process))
				    continue;

				return process;
				
			}
			return null;
		
	}
//  Bu turda çalışması gereken processlerin çalışıp çalışmadığını kontrol eder.
			boolean Terminated(ArrayList<Process> workingProcess) {
				// döngü ile herbirini kontrol ediyoruz. Bulunmuyorsa false.
				for (Process pp : workingProcess) {
				     if (!runProcess.contains(pp))
					return false;
				}
				// bulunuyorsa true döndürüyoruz.
				return true;
				}
			

	@Override
	public void Operate(Process pp) {
	pp.ProcessExecute();
	if (pp.getRemainingTime() > 0)
		runProcess.add(pp);
	}	
}

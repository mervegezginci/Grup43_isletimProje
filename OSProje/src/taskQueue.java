import java.util.ArrayList;

public class taskQueue {
	FCFS fcfs;
	RR rrQueue1;
	RR rrQueue2;
	RR rrQueue3;
	Process runQueue;
	public taskQueue() {
		// TODO Auto-generated constructor stub
		fcfs = new FCFS(0);
		rrQueue1 = new RR(1);
		rrQueue2 = new RR(2);
		rrQueue3 = new RR(3);
	}
	public void AddQueue(Process process) {
		switch (process.getPriority()) {
		case 0:
			fcfs.AddQueue(process);
			break;
		case 1:
			rrQueue1.AddQueue(process);
			break;
		case 2:
			rrQueue2.AddQueue(process);
			break;
		case 3:
			rrQueue3.AddQueue(process);
			break;
		}
	}

	public void Run(int second) {
		var process = GetProcess(second);
		if (process == null)
			return;

		if (second > 20) { //20 Saniye Sonra tüm görevler iptal ediliyor.
			var killList = KillProcess();

			for (var pro : killList) {
				Process.WriteProcess(pro, second, "öldü");
			}

			return;
		}

		if (runQueue == null || process.pid != runQueue.pid) {
			//Daha önce çalışan farklı process var ise askıya alınıyor
			if (runQueue != null && runQueue.remainingTime > 0
					&& runQueue.remainingTime!= runQueue.processTime) {
				Process.WriteProcess(runQueue,second, "askıya alındı");
			}

			//Farklı bir processden gelen işlem ilk adımı ise başlıyor
			if (process.remainingTime == process.processTime) {
				Process.WriteProcess(process, second, "başladı");
			}
		}

		getProcessQueue.Operate(process);

		if (second < 20) {
			//Eğer işlemler ölmedi ise , kalan süresi var ise yürütülüyor
			// Fakat kalan süresi kalmadı ise sonlandırıyor
			var pName = process.remainingTime> 0 ? "yürütülüyor" : "sonlandı ";
			Process.WriteProcess(process, second + 1, pName);
		}
		runQueue = process;
	}

	Queues getProcessQueue = null;
	Process GetProcess(int second) {
		var process = fcfs.GetProcess(second);
		getProcessQueue = fcfs;
		if (process != null)
			return process;
		process = rrQueue1.GetProcess(second);
		getProcessQueue = rrQueue1;
		if (process != null)
			return process;
		process = rrQueue2.GetProcess(second);
		getProcessQueue = rrQueue1;
		if (process != null)
			return process;
		process = rrQueue3.GetProcess(second);
		getProcessQueue = rrQueue1;
		if (process != null)
			return process;
		getProcessQueue = null;
		return null;
	}
	ArrayList<Process> KillProcess() {
		var liste = new ArrayList<Process>();
		liste.addAll(fcfs.KillProcess());
		liste.addAll(rrQueue1.KillProcess());
		liste.addAll(rrQueue2.KillProcess());
		liste.addAll(rrQueue3.KillProcess());
		return liste;
	}

}

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Queues {

	///Kuyruktaki process Listesidir
		ArrayList<Process> processList;

		/// Kuyrugun Önceliği
		int Priority;	

		///Kuyruğa Yeni Bir Process ekler
		public void AddQueue(Process process)
		{
			processList.add(process);
		}
		
		///Kuyruk Yapıcısı
		public Queues(int priority) {
			super();
			this.processList = new ArrayList<Process>();
			Priority = priority;
		}

		///Kuyruk Öncelik Sırası
		public int getPriority() {
			return Priority;
		}
		
		///Kontrol saniyesi için, aktif çalışacak process olup olmadığı kontrolu
		public boolean HasProcess(int timeControl)
		{		
			for(int i=0;i<processList.size();i++)
			{
				Process process=processList.get(i);
				//Eğer process bitmiş ise veya daha çalışma zamanı gelmediyse işlem yapmadan bir döngü başına döner.
				if(process.remainingTime==0 || process.arrivialTime>timeControl) continue;
				return true;			
			}
			
			return false;
		}	
		
		/// Zaman Aşımı olduğunda Kuyruktaki tüm processler öldülürüyor.
		public ArrayList<Process> KillProcess()
		{
			var list=new ArrayList<Process>();
			
			for(int i=0;i<processList.size();i++)
			{
				Process process=processList.get(i);
				/// işlem bitti ise veya işlem öldü ise , devam ediyor.
				if(process.remainingTime==0 || process.getKilled() ) continue;
				process.setKilled(true);
				list.add( process);	
			}
			
			return list;
		}
		
		///Türetilen Sınıfta Saniye için İşlenecek Processin bulunması
		abstract public Process GetProcess(int timeControl);
		
		// verilen process'in işlenmesi
		abstract public void Operate (Process pp) ;
	
}
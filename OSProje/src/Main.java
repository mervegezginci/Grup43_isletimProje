import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int pid=0;
		ArrayList<Process> process = new ArrayList<>(); 
		//Queues process =new Queues();
		  Scanner readFile;
	        try {
	            File data = new File(args[0]);
	            readFile = new Scanner(data);
	        } catch (FileNotFoundException e) {
	            throw new RuntimeException(e);
	        }
	        while (readFile.hasNextLine()) {
	            String line = readFile.nextLine();
	            String[] columns = line.split(", ");
	            
	            int arrivialTime = Integer.parseInt(columns[0]);
	            int priority = Integer.parseInt(columns[1]);
	            int processTime = Integer.parseInt(columns[2]);
	            Process p =new Process(pid,priority,arrivialTime,processTime);
	            //process.AddQueue(p);
	            pid++;     
	            
               }
	        readFile.close();
	        taskQueue task= new taskQueue();
	     // Process leri kuyruğa ekle.
			for (var pc : process) {
				task.AddQueue(pc);
			}		
			// Kuyruğu çalıştır.
			for (int i = 0; i < 30; i++) {
				task.Run(i);
			}		
}
}
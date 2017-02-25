
import java.util.*;
import java.lang.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CpuScheduler implements Runnable {
	FileHandler f = new FileHandler();
	CpuScheduler c = new CpuScheduler();
	ArrayList processes = new ArrayList();
	long timer =System.currentTimeMillis();

	@Override
	public void run() {

		try {
			processes = FileHandler.readProcesses();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {
		CpuScheduler m1 = new CpuScheduler();
		Thread t1 = new Thread(m1);
		t1.start();
	}

	public <T> void roundrobin(){
		
		Queue<process> rr = new LinkedList<process>();
		Queue<Integer> io = new LinkedList<Integer>();
		
		for (int i=0;i<processes.size();i++){
			process p=(process) processes.get(i);
			if(timer==p.arrivealtime){
			for(int j=0;i<p.io.size();j++)
io.add(p.io.remove());
			rr.add(p);
			process m=rr.remove();
		
			for(int i1=0;i1<10;i1++){
				int f=m.cpu.remove();
	            	f-=10;
	            	m.cpu.add(f);
			}
			rr.add(m);
			}
			for(int h=0;h<io.size();h++){
				for(long z=0;h<=io.peek();z++){
					if(z==io.peek())
						io.remove();
				}
			}
		}
		
		}

}

/*
 * public <T> void roundrobin(){ Queue<process> rr = new LinkedList<process>();
 * Queue<process> io = new LinkedList<process>(); int i=0;
 * while(processes.isEmpty()){ process selectedProcess=(process)
 * processes.get(i); Deque<Integer> running = new ArrayDeque<>(); for (long
 * quantum = 0; quantum < timer; ++quantum) { // Add new arrivals to the list of
 * running processes. // Note that if the processes are pre-sorted by arrival
 * time, // this loop can be made more efficient. This assumes no sorting. for
 * (int process = 0; process <= i; ++process) { if (
 * selectedProcess.arrivealtime== quantum) running.addLast(process); // Use
 * addFirst if new procs should run immediately. } int bsize=0; if
 * (selectedProcess.bursts.isEmpty()); // Nothing to run else { // Select the
 * process now at the head. String dProcess = (String)
 * selectedProcess.bursts.get(bsize); String[] parts; parts =
 * dProcess.split(":"); int m=Integer.parseInt(parts[1]); m-=10; // Decrement
 * the running process's burst time. If it's done, remove // it from the running
 * list. if (m == 0) bsize++; running.removeFirst();
 * 
 * // Record the run for this quantum.
 * 
 * // Rotate the previous head to the tail of the list for next quantum. if
 * (running.size() > 1) running.addLast(running.removeFirst()); } } i++; } }
 */


class FileHandler {

	static public ArrayList readProcesses() throws FileNotFoundException, IOException {
		ArrayList data = new ArrayList();

		FileReader fr = new FileReader("/Users/work/Desktop/m1.txt");
		BufferedReader bf = new BufferedReader(fr);

		String line;

		while ((line = bf.readLine()) != null) {
			String[] parts;
			String[] cp;
			String[] ios;
			ArrayList<Integer> io = new ArrayList<Integer>();
			ArrayList<Integer> cpu = new ArrayList<Integer>();
			parts = line.split(";");
			int q=0;
			
			for (String b : parts) {
				q+=Integer.parseInt(parts[2]);
				if(q>1024)
					System.out.println("Memory is Full!!!!"); 
				if (b.startsWith("cpu")) {
					cp = b.split(":");
					cpu.add(Integer.parseInt(cp[1]));
				}
				if (b.startsWith("io")) {
					ios = b.split(":");
					io.add(Integer.parseInt(ios[1]));
				}
			}
			ArrayList process = new ArrayList();
			for (int i = 3; i < parts.length; i++)
				process.add(parts[i]);
			process p = new process(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
					cpu, io);
			data.add(p);
		}
		return data;
	}

}

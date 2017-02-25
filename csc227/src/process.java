
import java.util.ArrayList;
import java.util.Queue;

public class process {
int id;
int arrivealtime;
int size;
Queue <Integer>cpu = (Queue<Integer>) new ArrayList<Integer>();
Queue <Integer>io = (Queue<Integer>) new ArrayList<Integer>();

public process(int id, int arrivealtime, int size, ArrayList cpu, ArrayList io) {
	super();
	this.id = id;
	this.arrivealtime = arrivealtime;
	this.size = size;
	this.io = (Queue<Integer>) io;
	this.cpu = (Queue<Integer>) cpu;
}



}

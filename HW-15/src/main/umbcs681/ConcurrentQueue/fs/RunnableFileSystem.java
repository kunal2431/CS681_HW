package umbcs681.ConcurrentQueue.fs;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class RunnableFileSystem implements Runnable {

	private AtomicBoolean done = new AtomicBoolean(false);

	private ConcurrentLinkedQueue<Directory> root_directories = new ConcurrentLinkedQueue<Directory>();

	public RunnableFileSystem() {

    }

	public void run() {
		for(int i = 0; i < 1; i++){
			if(done.get()){
				System.out.println("Stopped RunnableFileSystem");
				break;
			}
			FileSystem fs = FileSystem.getFileSystem().get();
			root_directories = fs.getRootDirs();
			fs.getRootDirs().peek().getChildren();
			fs.getRootDirs().peek().getFiles();
			try{
				Thread.sleep(2);
			}
			catch (InterruptedException ex){
				
			}
			if(done.get()){
				System.out.println("Stopped RunnableFileSystem");
				break;
			}
		}

	}

	public void setDone(){done.set(true);}

	public ConcurrentLinkedQueue<Directory> getRoot_directories(){
		return this.root_directories;
	}

	/*public static void main(String[] args) {
		RunnableFileSystem r1 = new RunnableFileSystem();
		RunnableFileSystem r2 = new RunnableFileSystem();
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
	}*/
}

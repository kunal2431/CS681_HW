package umbcs681.fsThreadSafe.fs;

import java.util.concurrent.atomic.AtomicBoolean;

public class RunnableFileSystem implements Runnable {

	private AtomicBoolean done = new AtomicBoolean(false);

	public RunnableFileSystem() {

    }

	public void run() {
		while(true){
			if(done.get()){
				System.out.println("Stopped RunnableFileSystem");
				break;
			}
			FileSystem fs = FileSystem.getFileSystem().get();
			fs.getRootDirs();
			try{
				Thread.sleep(2);
			}
			catch (InterruptedException ex){
				continue;
			}
		}

	}

	public void setDone(){done.set(true);}

	/*public static void main(String[] args) {
		RunnableFileSystem r1 = new RunnableFileSystem();
		RunnableFileSystem r2 = new RunnableFileSystem();
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
	}*/
}

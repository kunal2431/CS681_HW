package umbcs681.lockfs.fs;

public class RunnableFileSystem implements Runnable {

	public RunnableFileSystem() {

    }

	public void run() {
		FileSystem.getFileSystem();
	}

	public static void main(String[] args) {
		RunnableFileSystem r1 = new RunnableFileSystem();
		RunnableFileSystem r2 = new RunnableFileSystem();
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
	}
}

package umbcs681.ConcurrentQueue.util;
import umbcs681.ConcurrentQueue.fs.*;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.time.LocalDateTime;

public class RunnableFileCrawl implements Runnable{

    /*private static AtomicReference<LinkedList<File>> shared_list = new AtomicReference<>(new LinkedList<File>());*/
    private static ConcurrentLinkedQueue<File> shared_list = new ConcurrentLinkedQueue<File>();
    private Directory root_dir;
    private FileCrawlingVisitor visitor;

    private AtomicBoolean done = new AtomicBoolean(false);

    public RunnableFileCrawl(Directory root_dir, FileCrawlingVisitor visitor){
        this.root_dir = root_dir;
        this.visitor = visitor;
    }

    public void setDone(){done.set(true);}

    /*public void run() {
        for(int i = 0; i< 1; i++){
            if(done.get()){
                System.out.println("Stopped FileCrawlRunnable");
                break;
            }
            root_dir.accept(visitor);
            shared_list.addAll(visitor.getFiles());
            try{
                Thread.sleep(500);
            }
            catch (InterruptedException ex){
                break;
            }
        }
    }*/

    public void run() {
        while (true) {
            if(done.get()){
                System.out.println("Stopped RunnableFileCrawl");
                break;
            }
            try {
                fileCrawl(root_dir, visitor);

            } catch (InterruptedException e) {
                System.out.println("Stopped FileCrawlRunnable during Traversing");
                break;
            }
            finally {
                ConcurrentLinkedQueue<File> result = visitor.getFiles();
                for (File file : result) {
                    if (!shared_list.contains(file)) {
                        shared_list.add(file);
                        System.out.println("File crawled: " + file.getName());
                    }
                }
            }
        }
    }

    public void fileCrawl(Directory rootDir, FSVisitor v) throws InterruptedException {
            v.visit(rootDir);
            ConcurrentLinkedQueue<FSElement> children = new ConcurrentLinkedQueue<>(rootDir.getChildren());
            for (FSElement e : children) {
                if (done.get() || Thread.currentThread().isInterrupted()) {throw new InterruptedException();}
                if(e instanceof Directory){
                    fileCrawl((Directory) e, v);
                }
                else{
                    e.accept(v);
                }
            }
    }

    public static ConcurrentLinkedQueue<File> getShared_list() {
        return shared_list;
    }


/*    Alternate Solution:

    private LinkedList<File> shared_list1 = new LinkedList<File>();

    public void run() {
        for(int i = 0; i < 1; i++){
            if(done.get()){
                System.out.println("Stopped RunnableFileCrawl");
                break;
            }
            root_dir.accept(visitor);
            LinkedList<File> result = visitor.getFiles();

            for (File file : result) {
                if (!shared_list1.contains(file)) {
                    shared_list1.add(file);
                    System.out.println("File crawled: " + file.getName());

                }
            }

            try{
                Thread.sleep(500);
            }
            catch (InterruptedException ex){
                continue;
            }
        }
    }

    public static LinkedList<File> getShared_list1() {
        return shared_list1;
    }

    */

}

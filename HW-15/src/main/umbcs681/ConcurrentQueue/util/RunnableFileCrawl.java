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

    public void run() {
        for(int i = 0; i< 1; i++){
            if(done.get()){
                System.out.println("Stopped FileCrawlRunnable");
                break;
            }
            root_dir.accept(visitor);
            shared_list.addAll(visitor.getFiles());
            try{
                Thread.sleep(2);
            }
            catch (InterruptedException ex){
                continue;
            }
        }
    }


    public static ConcurrentLinkedQueue<File> getShared_list() {
        return shared_list;
    }

}

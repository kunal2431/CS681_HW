package umbcs681.fscrawl.util;
import umbcs681.fscrawl.fs.*;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.time.LocalDateTime;

public class RunnableFileCrawl implements Runnable{

    private static AtomicReference<LinkedList<File>> shared_list = new AtomicReference<>(new LinkedList<File>());
    private Directory root_dir;
    private FileCrawlingVisitor visitor;

    private AtomicBoolean done = new AtomicBoolean(false);

    public RunnableFileCrawl(Directory root_dir, FileCrawlingVisitor visitor){
        this.root_dir = root_dir;
        this.visitor = visitor;
    }

    public void setDone(){done.set(true);}

    public void run() {
        for(int i = 0; i < 1; i++){
            if(done.get()){
                System.out.println("Stopped RunnableFileCrawl");
                break;
            }
            root_dir.accept(visitor);
            LinkedList<File> result = visitor.getFiles();

            shared_list.updateAndGet(rootFileList -> {
                for (File file : result) {
                    if (!rootFileList.contains(file)) {
                        rootFileList.add(file);
                        System.out.println("File crawled: " + file.getName());
                        try{
                            Thread.sleep(500); // Sleep(try-catch) is not required here, but used here to validate intermediate results
                        }
                        catch (InterruptedException ex){
                            continue;
                        }
                    }
                }
                return rootFileList;
            });
            try{
                Thread.sleep(500);
            }
            catch (InterruptedException ex){
                continue;
            }
        }
    }

    public static LinkedList<File> getShared_list() {
        return shared_list.get();
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

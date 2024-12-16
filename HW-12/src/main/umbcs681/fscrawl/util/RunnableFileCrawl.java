package umbcs681.fscrawl.util;
import umbcs681.fscrawl.fs.*;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RunnableFileCrawl implements Runnable{

    private static AtomicReference<LinkedList<File>> shared_list = new AtomicReference<>(new LinkedList<File>());
    private Directory root_dir;
    private FileCrawlingVisitor visitor;

    private AtomicBoolean done = new AtomicBoolean(false);

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public RunnableFileCrawl(Directory root_dir, FileCrawlingVisitor visitor){
        this.root_dir = root_dir;
        this.visitor = visitor;
    }

    public void setDone(){done.set(true);}

    /*public void run() {
        while(!done.get()){
            root_dir.accept(visitor);
            LinkedList<File> result = visitor.getFiles();

            shared_list.updateAndGet(rootFileList -> {
                for (File file : result) {
                    if(done.get()){
                        System.out.println("Stopped RunnableFileCrawl during traversing");
                        break;
                    }
                    if (!rootFileList.contains(file)) {
                        rootFileList.add(file);
                        System.out.println("File crawled: " + file.getName());
                    }
                    try{
                        Thread.sleep(200);
                    }
                    catch (InterruptedException e){

                    }
                }
                return rootFileList;
            });
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
                System.out.println("Stopped FileCrawling during Traversing");
                break;
            }
            finally {
                LinkedList<File> result = visitor.getFiles();
                shared_list.updateAndGet(rootFileList -> {
                    for (File file : result) {
                        if (!rootFileList.contains(file)) {
                            rootFileList.add(file);
                            System.out.println("File crawled: " + file.getName());
                        }
                    }
                    return rootFileList;
                });
            }
        }
    }

    public void fileCrawl(Directory rootDir, FSVisitor v) throws InterruptedException {
        rwLock.readLock().lock();
        try {
            v.visit(rootDir);
            LinkedList<FSElement> children = new LinkedList<>(rootDir.getChildren());
            for (FSElement e : children) {
                if (done.get() || Thread.currentThread().isInterrupted()) {throw new InterruptedException();}
                if(e instanceof Directory){
                    fileCrawl((Directory) e, v);
                }
                else{
                    e.accept(v);
                }
            }
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public static LinkedList<File> getShared_list() {
        return shared_list.get();
    }

   /* public void run() {
        while (true) {
            if(done.get()){
                System.out.println("Stopped RunnableFileCrawl");
                break;
            }
            try {
                fileCrawl(root_dir, visitor);

            } catch (InterruptedException e) {break;}
            finally {
                LinkedList<File> result = visitor.getFiles();
                shared_list.updateAndGet(rootFileList -> {
                    for (File file : result) {
                        if (!rootFileList.contains(file)) {
                            rootFileList.add(file);
                            System.out.println("File crawled: " + file.getName());
                        }
                    }
                    return rootFileList;
                });
            }
        }
    }

    public void fileCrawl(Directory rootDir, FSVisitor v) throws InterruptedException {
        rwLock.readLock().lock();
        try {
            v.visit(rootDir);
            LinkedList<FSElement> children = new LinkedList<>(rootDir.getChildren());
            for (FSElement e : children) {
                if (Thread.currentThread().isInterrupted()) {throw new InterruptedException();}
                if(e instanceof Directory){
                    fileCrawl((Directory) e, v);
                }
                else{
                    e.accept(v);
                }
            }
        } finally {
            rwLock.readLock().unlock();
        }
        try {
            Thread.sleep(3);
        } catch (InterruptedException ex) {
            throw new InterruptedException();
        }
    }

    public void accept(FSVisitor v) throws InterruptedException {
        rwLock.readLock().lock();
        try {
            if (Thread.currentThread().isInterrupted()) {throw new InterruptedException();}
            v.visit(this);
        } finally {
            rwLock.readLock().unlock();
        }
        try{
            Thread.sleep(3);
        }
        catch (InterruptedException ex){}
    }


*/

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

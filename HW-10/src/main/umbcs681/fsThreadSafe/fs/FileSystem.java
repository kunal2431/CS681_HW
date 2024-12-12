package umbcs681.fsThreadSafe.fs;

import java.util.LinkedList;
//import java.util.Objects;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.*;

public class FileSystem{

    protected LinkedList<RunnableDirectory> directories_root = new LinkedList<RunnableDirectory>();

    private static AtomicReference<FileSystem> instance = new AtomicReference<>();
    private static ReentrantLock lock = new ReentrantLock();

    private FileSystem(){
        }

    public static AtomicReference<FileSystem> getFileSystem(){
        instance.compareAndSet(null, new FileSystem());
        return instance;
/*        lock.lock();
        try{
            if(instance.get()==null){
                instance.set(new FileSystem());
            }
            return instance.get();
        }
        finally {
            lock.unlock();
        }*/
    }

    protected AtomicBoolean done = new AtomicBoolean(false);

    public void setDone(){
        done.set(true);
    }

    public LinkedList<RunnableDirectory> getRootDirs(){return this.directories_root;}

    public void appendRootDir(RunnableDirectory root){
        this.directories_root.add(root);
    }

}
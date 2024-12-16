package umbcs681.ConcurrentQueue.fs;

//import java.util.Objects;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.*;

public class FileSystem{

    protected ConcurrentLinkedQueue<Directory> directories_root = new ConcurrentLinkedQueue<Directory>();

    private static AtomicReference<FileSystem> instance = new AtomicReference<>();

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

    public ConcurrentLinkedQueue<Directory> getRootDirs(){
        return this.directories_root;
    }

    public void appendRootDir(Directory root){
        this.directories_root.offer(root);;
    }

}
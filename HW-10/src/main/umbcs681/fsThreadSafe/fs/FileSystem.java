package umbcs681.fsThreadSafe.fs;

import java.util.LinkedList;
//import java.util.Objects;

import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileSystem{

    protected LinkedList<Directory> directories_root = new LinkedList<Directory>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

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

    public LinkedList<Directory> getRootDirs(){
        rwLock.readLock().lock();
        try{
            return this.directories_root;
        }
        finally {
            rwLock.readLock().unlock();
        }
    }

    public void appendRootDir(Directory root){
        rwLock.writeLock().lock();
        try{
            this.directories_root.add(root);;
        }
        finally {
            rwLock.writeLock().unlock();
        }
    }

}
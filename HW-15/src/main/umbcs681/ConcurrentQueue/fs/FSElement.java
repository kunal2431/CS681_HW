package umbcs681.ConcurrentQueue.fs;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class FSElement{

    protected String name;
    protected int size;
    protected LocalDateTime creationTime;

    protected Directory parent;

    protected ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();


    public FSElement(Directory parent, String name, int size, LocalDateTime creationTime){
        //creationTime = LocalDateTime.now();
        this.parent = parent;
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
    }

    public Directory getParent(){
        rwLock.readLock().lock();
        try{
            return this.parent;
        }
        finally {
            rwLock.readLock().unlock();
        }
    }

    public abstract boolean isDirectory();

    public String getName(){
        rwLock.readLock().lock();
        try{
            return this.name;
        }
        finally {
            rwLock.readLock().unlock();
        }
    }

    public int getSize(){
        rwLock.readLock().lock();
        try{
            return this.size;
        }
        finally {
            rwLock.readLock().unlock();
        }
    }

    public LocalDateTime getcreationTime(){
        rwLock.readLock().lock();
        try{
            return this.creationTime;
        }
        finally {
            rwLock.readLock().unlock();
        }
    }

    public void setParent(Directory parent) {
        rwLock.writeLock().lock();
        try{
            this.parent=parent;
        }
        finally {
            rwLock.writeLock().unlock();
        }
    }

    public abstract boolean isLink();

    public abstract void accept(FSVisitor v);

}

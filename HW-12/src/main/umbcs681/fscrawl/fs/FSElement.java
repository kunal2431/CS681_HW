package umbcs681.fscrawl.fs;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class FSElement{

    protected String name;
    protected int size;
    protected LocalDateTime creationTime;

    protected ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    protected Directory parent;

    public FSElement(Directory parent, String name, int size, LocalDateTime creationTime){
        //creationTime = LocalDateTime.now();
        this.parent = parent;
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
    }

    public Directory getParent(){
        return this.parent;
    }

    public abstract boolean isDirectory();

    public String getName(){
        return this.name;
    }

    public int getSize(){return this.size;}

    public LocalDateTime getcreationTime(){return this.creationTime;}

    public void setParent(Directory parent) {
        this.parent=parent;
    }

    public abstract boolean isLink();

    public abstract void accept(FSVisitor v);

}
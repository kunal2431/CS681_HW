package umbcs681.fsThreadSafe.fs;

import java.time.LocalDateTime;

public class Link extends FSElement{

    private FSElement target;

    public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target){
        super(parent, name, 0, creationTime);
        this.target = target;
    }

    public boolean isDirectory() {
        return false;
    }

    public boolean isLink() {
        return true;
    }

    public void accept(FSVisitor v) {
        rwLock.writeLock().lock();
        try{
            v.visit(this);
        }
        finally {
            rwLock.writeLock().unlock();
        }
    }

    public FSElement getLinkTarget(){
        rwLock.readLock().lock();
        try{
            return this.target;
        }
        finally {
            rwLock.readLock().unlock();
        }
    }
}
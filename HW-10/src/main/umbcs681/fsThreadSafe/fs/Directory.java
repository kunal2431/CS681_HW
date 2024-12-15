package umbcs681.fsThreadSafe.fs;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory  extends FSElement{

    protected LinkedList<FSElement> children = new LinkedList<FSElement>();
    protected LinkedList<Directory> directories = new LinkedList<Directory>();
    protected LinkedList<File> files = new LinkedList<File>();

    protected LinkedList<Link> links = new LinkedList<Link>();


    public Directory(Directory parent, String name, int size, LocalDateTime creationTime){
        super(parent, name, 0, creationTime);
    }

    public LinkedList<FSElement> getChildren(){
        rwLock.readLock().lock();
        try{
            return this.children;
        }
        finally {
            rwLock.readLock().unlock();
        }
    }

    public void appendChild(FSElement child){
        rwLock.writeLock().lock();
        try{
            this.children.add(child);
            child.setParent(this);

        }
        finally {
            rwLock.writeLock().unlock();
        }

    }

    public int countChildren(){
        rwLock.readLock().lock();
        try{
            return this.children.size();
        }
        finally {
            rwLock.readLock().unlock();
        }
    }

    public LinkedList<Directory> getSubDirectories(){
        rwLock.writeLock().lock(); // I used writeLock since linkedlist data is changing
        try{
            directories.clear();
            for(FSElement child: children ){
                if(child.isDirectory() == true){
                    this.directories.add((Directory) child);
                    this.directories.addAll(((Directory) child).getSubDirectories());
                }
            }
            return this.directories;
        }
        finally {
            rwLock.writeLock().unlock();
        }
    }

    public LinkedList<File> getFiles(){
        rwLock.writeLock().lock();
        try{
            files.clear();
            for(FSElement child: children ){
                if(child.isDirectory() == false && child.isLink() == false){
                    this.files.add((File) child);
                }
            }
            return this.files;
        }
        finally {
            rwLock.writeLock().unlock();
        }
    }

    public int getTotalSize(){
        rwLock.readLock().lock();
        try{
            int size=0;
            for (FSElement child : children) {
                if (child.isDirectory() == true) {
                    size = size + ((Directory) child).getTotalSize();
                } else {
                    size = size + child.getSize();
                }
            }
            return size;
        }
        finally {
            rwLock.readLock().unlock();
        }
    }

    public boolean isDirectory() {
        return true;
    }

    public boolean isLink() {
        return false;
    }

    public LinkedList<Link> getLink(){
        rwLock.readLock().lock();
        try{
            links.clear();
            for(FSElement child: children){
                if(child.isLink() == true){
                    this.links.add((Link) child);
                }
            }
            return this.links;
        }
        finally {
            rwLock.readLock().unlock();
        }
    }

    public void accept(FSVisitor v) {
        rwLock.writeLock().lock();
        try{
            v.visit(this);
            for (FSElement e: children){
                e.accept(v);
            }
        }
        finally {
            rwLock.writeLock().unlock();
        }
    }

}
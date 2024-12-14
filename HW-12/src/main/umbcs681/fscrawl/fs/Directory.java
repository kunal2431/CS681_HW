package umbcs681.fscrawl.fs;

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
        directories.clear();
        for(FSElement child: children ){
            rwLock.readLock().lock();
            try{
                if(child.isDirectory() == true){
                    this.directories.add((Directory) child);
                    this.directories.addAll(((Directory) child).getSubDirectories());
                }
            }
            finally {
                rwLock.readLock().unlock();
            }
        }
        return this.directories;
    }

    public LinkedList<File> getFiles(){
        files.clear();
        for(FSElement child: children ){
            rwLock.readLock().lock();
            try{
                if(child.isDirectory() == false && child.isLink() == false){
                    this.files.add((File) child);
                }
            }
            finally {
                rwLock.readLock().unlock();
            }

        }
        return this.files;
    }

    public int getTotalSize(){
        int size=0;
        for (FSElement child : children) {
            rwLock.readLock().lock();
            try{
                if (child.isDirectory() == true) {
                    size = size + ((Directory) child).getTotalSize();
                } else {
                    size = size + child.getSize();
                }
            }
            finally {
                rwLock.readLock().unlock();
            }

        }
        return size;
    }

    public boolean isDirectory() {
        return true;
    }

    public boolean isLink() {
        return false;
    }

    public LinkedList<Link> getLink(){
        links.clear();
        for(FSElement child: children){
            rwLock.readLock().lock();
            try{
                if(child.isLink() == true){
                    this.links.add((Link) child);
                }
            }
            finally {
                rwLock.readLock().unlock();
            }
        }
        return this.links;
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
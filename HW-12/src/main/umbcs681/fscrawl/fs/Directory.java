package umbcs681.fscrawl.fs;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory  extends FSElement{

    protected LinkedList<FSElement> children = new LinkedList<FSElement>();

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
        rwLock.readLock().lock();
        try{
            LinkedList<Directory> directories = new LinkedList<Directory>();
            for(FSElement child: children ){
                if(child.isDirectory() == true){
                    directories.add((Directory) child);
                    directories.addAll(((Directory) child).getSubDirectories());
                }
            }
            return directories;
        }
        finally {
            rwLock.readLock().unlock();
        }
    }

    public LinkedList<File> getFiles(){
        rwLock.readLock().lock();
        try{
            LinkedList<File> files = new LinkedList<File>();
            for(FSElement child: children ){
                if(child.isDirectory() == false && child.isLink() == false){
                    files.add((File) child);
                }
            }
            return files;
        }
        finally {
            rwLock.readLock().unlock();
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
            LinkedList<Link> links = new LinkedList<Link>();
            for(FSElement child: children){
                if(child.isLink() == true){
                    links.add((Link) child);
                }
            }
            return links;
        }
        finally {
            rwLock.readLock().unlock();
        }
    }

    public void accept(FSVisitor v) {
        rwLock.readLock().lock();
        try{
            v.visit(this);
            for (FSElement e: children){
                e.accept(v);
            }
        }
        finally {
            rwLock.readLock().unlock();
        }
    }

}
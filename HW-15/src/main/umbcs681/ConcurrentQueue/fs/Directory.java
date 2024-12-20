package umbcs681.ConcurrentQueue.fs;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Directory  extends FSElement{

    protected ConcurrentLinkedQueue<FSElement> children = new ConcurrentLinkedQueue<FSElement>();
    protected ConcurrentLinkedQueue<Directory> directories = new ConcurrentLinkedQueue<Directory>();
    protected ConcurrentLinkedQueue<File> files = new ConcurrentLinkedQueue<File>();

    protected ConcurrentLinkedQueue<Link> links = new ConcurrentLinkedQueue<Link>();


    public Directory(Directory parent, String name, int size, LocalDateTime creationTime){
        super(parent, name, 0, creationTime);
    }

    public ConcurrentLinkedQueue<FSElement> getChildren(){
        return this.children;
    }

    public void appendChild(FSElement child){
        this.children.offer(child);
        child.setParent(this);
    }

    public int countChildren(){
        return this.children.size();
    }

    public ConcurrentLinkedQueue<Directory> getSubDirectories(){
        directories.clear();
        for(FSElement child: children ){
            if(child.isDirectory() == true){
                this.directories.offer((Directory) child);
                this.directories.addAll(((Directory) child).getSubDirectories());
               }
        }
        return this.directories;
    }

    public ConcurrentLinkedQueue<File> getFiles(){
        files.clear();
        for(FSElement child: children ){
            if(child.isDirectory() == false && child.isLink() == false){
                this.files.offer((File) child);
            }
        }
        return this.files;
    }

    public int getTotalSize(){
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

    public boolean isDirectory() {
        return true;
    }

    public boolean isLink() {
        return false;
    }

    public ConcurrentLinkedQueue<Link> getLink(){
        links.clear();
        for(FSElement child: children){
            if(child.isLink() == true){
                this.links.add((Link) child);
            }
        }
        return this.links;
    }

    public void accept(FSVisitor v) {
        v.visit(this);
        for (FSElement e: children){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Stopped during traversing");
                return;
            }
            e.accept(v);
        }
    }

}

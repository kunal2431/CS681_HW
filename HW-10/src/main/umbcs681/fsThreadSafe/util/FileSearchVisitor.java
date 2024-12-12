package umbcs681.fsThreadSafe.util;
import umbcs681.fsThreadSafe.fs.*;

import java.util.LinkedList;

public class FileSearchVisitor implements FSVisitor{

    private String fileName;

    protected LinkedList<RunnableFile> foundFiles = new LinkedList<RunnableFile>();

    public FileSearchVisitor(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void visit(RunnableLink link) {
        return;
    }

    @Override
    public void visit(RunnableDirectory dir) {
        return;
    }

    @Override
    public void visit(RunnableFile file) {
        if(file.getName().equals(fileName)){
            foundFiles.add(file); }
    }

    public LinkedList<RunnableFile> getFoundFiles(){
        return foundFiles;
    }
}
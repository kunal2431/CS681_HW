package umbcs681.fsThreadSafe.util;
import umbcs681.fsThreadSafe.fs.*;

public class CountingVisitor implements FSVisitor{

    private int dirNum = 0;
    private int fileNum = 0;
    private int linkNum = 0;

    public CountingVisitor(){}

    @Override
    public void visit(RunnableLink link) {
        linkNum++;
    }

    @Override
    public void visit(RunnableDirectory dir) {
        dirNum++;
    }

    @Override
    public void visit(RunnableFile file) {
        fileNum++;
    }

    public int getDirNum(){
        return dirNum;
    }

    public int getFileNum(){
        return fileNum;
    }

    public int getLinkNum(){
        return linkNum;
    }
}

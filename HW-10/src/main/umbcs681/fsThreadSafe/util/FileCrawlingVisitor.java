package umbcs681.fsThreadSafe.util;
import umbcs681.fsThreadSafe.fs.*;

import java.util.LinkedList;
import java.util.stream.Stream;
import java.time.LocalDateTime;

public class FileCrawlingVisitor implements FSVisitor{

    protected LinkedList<RunnableFile> files_visitor = new LinkedList<RunnableFile>();

    public FileCrawlingVisitor(){}

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
        files_visitor.add(file);
    }

    public LinkedList<RunnableFile> getFiles(){
        return files_visitor;
    }

    public Stream<RunnableFile> files(){
        return getFiles().stream();
    }
}

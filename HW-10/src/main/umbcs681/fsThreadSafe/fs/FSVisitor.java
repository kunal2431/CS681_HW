package umbcs681.fsThreadSafe.fs;

public interface FSVisitor{
    void visit(RunnableLink link);
    void visit(RunnableDirectory dir);
    void visit(RunnableFile file);
}

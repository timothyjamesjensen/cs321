package ast;
import compiler.Position;

/** Abstract syntax for statements.
 */
public abstract class Stmt {

    

    /** Print an indented description of this abstract syntax node,
     *  including a name for the node itself at the specified level
     *  of indentation, plus more deeply indented descriptions of
     *  any child nodes.
     */
    public abstract void indent(IndentOutput out, int n);

    /** Generate a pretty-printed description of this abstract syntax
     *  node using the concrete syntax of the mini programming language.
     */
    public abstract void print(TextOutput out, int n);

    /** Print this statement as the "ifTrue" branch of an if-then-else
     *  having just printed the parenthesized test, but no newline.  This
     *  allows us to override the behaviour for Blocks to match the
     *  desired output formatting.  The elseStmt parameter specifies the
     *  corresponding else statement that should also be printed.  For
     *  current purposes, there are no if-then-else statements that do
     *  not have an else branch.
     */
    public void printThenElse(TextOutput out, int n, Stmt elseStmt) {
        out.println();
        print(out,n+1);
        out.indent(n);
        out.print("else");
        elseStmt.printElse(out, n);
    }

    /** Print this statement as the "ifFalse" branch of an if-then-else
     *  having just printed the "else" keyword but no newline.  Again,
     *  this allows us to override the behavior for Blocks.
     */
    public void printElse(TextOutput out, int n) {
        out.println();
        print(out, n+1);
    }

    /** Print out a program.  In general, we expect a top-level program to
     *  be represented by a Block, whose contents will be displayed here
     *  without the enclosing braces that might otherwise be expected.  We
     *  will also define an implementation of this method for arbitrary
     *  Stmt values too so that it can be used for debugging and testing.
     */
    public void printProgram(TextOutput out) { print(out, 0); out.println(); }

    /** Output a description of this node (with id n), including a
     *  link to its parent node (with id p) and returning the next
     *  available node id.
     */
    public int toDot(DotOutput dot, int p, String attr, int n) {
        dot.join(p, n, attr);
        return toDot(dot, n);
    }

    /** Output a description of this node (with id n) in dot format,
     *  adding an extra node for each subtree.
     */
    public abstract int toDot(DotOutput dot, int n);

    /** Output a dot description of this abstract syntax node
     *  using the specified label and id number.
     */
    protected int node(DotOutput dot, String lab, int n) {
        return dot.node(lab, n);
    }

    /** Run scope analysis on this statement.  The scoping parameter
     *  provides access to the scope analysis phase (in particular,
     *  to the associated error handler), and the env parameter
     *  reflects the environment at the start of the statement.  The
     *  return result is the environment at the end of the statement.
     */
    public abstract Env analyze(ScopeAnalysis scoping, Env env);

    /** Generate a dot description for the environment structure of this
     *  program.
     */
    public abstract void dotEnv(DotEnvOutput dot);

    /** Run type checker on this statement.  The typing parameter
     *  provides access to the scope analysis phase (specifically,
     *  to the associated error handler).
     */
    public abstract void analyze(TypeAnalysis typing);

    /** Run initialization analysis on this statement.  The init
     *  parameter provides access to an initialization analysis phase
     *  object (specifically, to an associated error handler).  The
     *  initialized parameter is the set of variables (each represented
     *  by pointers to environment entries) that have definitely been
     *  initialized before this statement is executed.
     */
    public abstract VarSet analyze(InitAnalysis init, VarSet initialized);

}

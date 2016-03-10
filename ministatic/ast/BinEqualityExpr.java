package ast;
import compiler.Position;

/** Abstract syntax for binary equality test expressions.
 */
public abstract class BinEqualityExpr extends BinExpr {

    /** Default constructor.
     */
    public BinEqualityExpr(Position pos, Expr left, Expr right) {
        super(pos, left, right);
    }

    /** Run type checking analysis on this expression.  The typing parameter
     *  provides access to the scope analysis phase (in particular,
     *  to the associated error handler), and the env parameter
     *  reflects the environment in which the expression is evaluated.
     *  Unlike scope analysis for statements, there is no return
     *  result here: an expression cannot introduce new variables in
     *  to a program, so the final environment will always be the same
     *  as the initial environment.
     */
    public Type analyze(TypeAnalysis typing) {   // Lt, Le, Gt, Gte
        Type lt = left.require(typing, Type.INT, Type.BOOLEAN);
        Type rt = right.require(typing, Type.INT, Type.BOOLEAN);
        requireSame(typing, lt, rt);
        return type = Type.BOOLEAN;
    }
}

import compiler.*;
import java.io.FileReader;

import syntax.*;
import ast.*;

public class Compiler {
  public static void main(String[] args) {
    Handler handler = new SimpleHandler();
    try {
      if (args.length!=1) {
        throw new Failure("This program requires exactly one argument");
      }

      // Read program:
      String     input  = args[0] + ".mini";
      FileReader reader = new FileReader(input);
      Source     source = new JavaSource(handler, input, reader);
      MiniLexer  lexer  = new MiniLexer(handler, source);
      MiniParser parser = new MiniParser(handler, lexer);
      Stmt       prog   = parser.getProgram();
      if (handler.hasFailures()) {
        throw new Failure("Aborting: errors detected during syntax analysis");
      }

      // Analyze program:
      new ScopeAnalysis(handler).analyze(prog);
      new TypeAnalysis(handler).analyze(prog);
      new InitAnalysis(handler).analyze(prog);

      // Output result:
      System.out.println("Static analysis did not detect any errors");

    } catch (Failure f) {
      handler.report(f);
    } catch (Exception e) { 
      handler.report(new Failure("Exception: " + e));
      e.printStackTrace();
    }     
  }     
}

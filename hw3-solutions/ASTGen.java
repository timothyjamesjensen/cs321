/* Generated By:JavaCC: Do not edit this line. ASTGen.java */
public class ASTGen implements ASTGenConstants {
  public static void main(String args[]) throws ParseException {
    new ASTGen(System.in);  // Read from standard input
    Class[] cs = Top();
    //Class.indent(new IndentOutput(System.out), 2, cs);
    Class.generate(null, cs);
    System.out.println("Syntax is valid");
  }

//- Define the syntax of a simple language for AST hierarchies: ------

// NOTE: this grammar is presented in top-down fashion, beginning
// with the start symbol for the grammar.  Some people may prefer
// to approach this in bottom-up order, starting with the lexical
// rules at the end of the file and working back up to this point
// from there.  Your choice!

// A ".ast" file is a sequence of zero or more classes, followed
// by an end of file marker:
  static final public Class[] Top() throws ParseException {
                  Class[] cs;
    cs = subclasses(0, null, null);
    jj_consume_token(0);
    {if (true) return cs;}
    throw new Error("Missing return statement in function");
  }

// Each class has an optional "abstract" modifier, an identifier that
// names the class, an optional list of fields/constructor arguments,
// and an optional list of zero or more subclasses:
  static final public Class Class(Args args, Methods methods) throws ParseException {
      boolean isAbs = false;
      String  id;
      Class[] cs;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 1:
      jj_consume_token(1);
                isAbs = true;
      break;
    default:
      jj_la1[0] = jj_gen;
      ;
    }
    jj_consume_token(2);
    id = Id();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 3:
      jj_consume_token(3);
      args = Args(args);
      jj_consume_token(4);
      break;
    default:
      jj_la1[1] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 5:
      jj_consume_token(5);
      methods = Methods(methods);
      cs = subclasses(0, args, methods);
      jj_consume_token(6);
      break;
    default:
      jj_la1[2] = jj_gen;
              cs=new Class[0];
    }
    {if (true) return new Class(isAbs, id, args, methods, cs);}
    throw new Error("Missing return statement in function");
  }

  static final public Class[] subclasses(int soFar, Args args, Methods methods) throws ParseException {
      Class c; Class[] classes;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 1:
    case 2:
      c = Class(args, methods);
      classes = subclasses(soFar+1, args, methods);
     classes[soFar] = c; {if (true) return classes;}
      break;
    default:
      jj_la1[3] = jj_gen;
      {if (true) return new Class[soFar];}
    }
    throw new Error("Missing return statement in function");
  }

// Constructor arguments are specified by a list of zero or more
// comma-separated elements.  In the following grammar, the first
// Arg() call matches the first argument, with the ("," Arg())*
// portion matching and additional arguments, each prefixed by a
// comma.  The whole thing is wrapped in (...)? to allow for an
// empty argument list:
  static final public Args Args(Args before) throws ParseException {
                           Arg last;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 8:
    case 9:
    case 10:
    case IDENT:
      last = Arg();
         before = new Args(before, last);
      label_1:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 7:
          ;
          break;
        default:
          jj_la1[4] = jj_gen;
          break label_1;
        }
        jj_consume_token(7);
        last = Arg();
         before = new Args(before, last);
      }
      break;
    default:
      jj_la1[5] = jj_gen;
      ;
    }
    {if (true) return before;}
    throw new Error("Missing return statement in function");
  }

// Individual arguments begin with an optional "visbility" modifier
// (one of the strings "public", "protected", or "private"), a type,
// and an identifier that specifies the argument name:
  static final public Arg Arg() throws ParseException {
              String vis; Type t; String id;
    vis = Vis();
    t = Type();
    id = Id();
    {if (true) return new Arg(vis, t, id);}
    throw new Error("Missing return statement in function");
  }

  static final public String Vis() throws ParseException {
                 Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 8:
    case 9:
    case 10:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 8:
        t = jj_consume_token(8);
        break;
      case 9:
        t = jj_consume_token(9);
        break;
      case 10:
        t = jj_consume_token(10);
        break;
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
                                                {if (true) return t.image;}
      break;
    default:
      jj_la1[7] = jj_gen;
      {if (true) return null;}
    }
    throw new Error("Missing return statement in function");
  }

// Types can be specified by a grammar of the form:
//
//   Type -> NameType
//   Type -> Type "[" "]"
//
// This grammar is left recursive, so it can't be used directly, but
// it is easy to rewrite the grammar without left recursion: every
// type begins with an NameType followed by zero or more sets of
// matching square brackets, "[" "]":
  static final public Type Type() throws ParseException {
                String[] strs; Type t;
    strs = NameType(0);
       t = new NameType(strs);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 11:
        ;
        break;
      default:
        jj_la1[8] = jj_gen;
        break label_2;
      }
      jj_consume_token(11);
      jj_consume_token(12);
                t = new ArrayType(t);
    }
    {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

// Note that we use two distinct tokens here rather than a single
// "[]"; this allows ".ast" files to include spaces, comments, or
// other items from our SKIP rules between the open and close
// brackets, without any additional effort on our part.

// A NameType is a sequence of identifiers, separated by periods.
// We include the rules for recognizing NameTypes in a parsing
// function rather than trying to capture this in a single regular
// expression token.  This is important because it means (1) that
// source programs may include spaces, line breaks, comments or
// other SKIP elements in NameTypes without any additional work; and
// (2) that we will be able to store and manipulate the individual
// parts of a NameType within an abstract syntax tree.  Note that
// our grammar for describing a list of period-separated identifiers
// follows exactly the same patterns as we used in the code above
// for parsing lists of comma-separated arguments.
  static final public String[] NameType(int soFar) throws ParseException {
                                 String id; String[] strs;
    id = Id();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 13:
      jj_consume_token(13);
      strs = NameType(soFar+1);
      break;
    default:
      jj_la1[9] = jj_gen;
               strs=new String[soFar+1];
    }
    strs[soFar] = id;
    {if (true) return strs;}
    throw new Error("Missing return statement in function");
  }

// For convenience, we use the following parsing function to
// recognize a single identifier.  This isn't particularly important
// here, but it will be useful when we come to add semantic actions:
// the logic for reading a token and returning its image can be
// written here just once so that it doesn't need to repeated in
// other parts of the code.
  static final public String Id() throws ParseException {
                Token t;
    t = jj_consume_token(IDENT);
              {if (true) return t.image;}
    throw new Error("Missing return statement in function");
  }

  static final public Methods Methods(Methods before) throws ParseException {
                                    Method m;
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 14:
      case IDENT:
        ;
        break;
      default:
        jj_la1[10] = jj_gen;
        break label_3;
      }
      m = Method();
       before = new Methods(before, m);
    }
      {if (true) return before;}
    throw new Error("Missing return statement in function");
  }

  static final public Method Method() throws ParseException {
                    Type ret; String id; Param[] ps;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENT:
      ret = Type();
      break;
    case 14:
      jj_consume_token(14);
                         ret=null;
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    id = Id();
    jj_consume_token(3);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENT:
      ps = Params(0);
      break;
    default:
      jj_la1[12] = jj_gen;
                     ps = new Param[0];
    }
    jj_consume_token(4);
    {if (true) return new Method(ret, id, ps);}
    throw new Error("Missing return statement in function");
  }

  static final public Param[] Params(int soFar) throws ParseException {
                              Param p; Param[] ps;
    p = Param();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 7:
      jj_consume_token(7);
      ps = Params(soFar+1);
      break;
    default:
      jj_la1[13] = jj_gen;
                 ps=new Param[soFar+1];
    }
    ps[soFar] = p;
    {if (true) return ps;}
    throw new Error("Missing return statement in function");
  }

  static final public Param Param() throws ParseException {
                  Type t; String id;
    t = Type();
    id = Id();
    {if (true) return new Param(t, id);}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ASTGenTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[14];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x2,0x8,0x20,0x6,0x80,0x200700,0x700,0x700,0x800,0x2000,0x204000,0x204000,0x200000,0x80,};
   }

  /** Constructor with InputStream. */
  public ASTGen(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public ASTGen(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ASTGenTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public ASTGen(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ASTGenTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public ASTGen(ASTGenTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ASTGenTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 14; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[24];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 14; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 24; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}

/* Generated By:JavaCC: Do not edit this line. ASTGen.java */
public class ASTGen implements ASTGenConstants {
  public static void main(String args[]) throws ParseException {
    new ASTGen(System.in);
    Class[] classes;
    classes = Top();

    Class.genJava(0, classes, null, null);

    //System.out.println(test.last.toString());
  }

  static final public Class[] Top() throws ParseException {
                  Class[] c = null;
    c = classList(0);
    jj_consume_token(0);
    {if (true) return c;}
    throw new Error("Missing return statement in function");
  }

  static final public Class Class() throws ParseException {
                  boolean isAbstract = false; Token name; Args args = null;
                  Class[] subClasses = new Class[0]; Class sc; Methods meth = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 1:
      jj_consume_token(1);
               isAbstract = true;
      break;
    default:
      jj_la1[0] = jj_gen;
      ;
    }
    jj_consume_token(2);
    name = Id();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 3:
      jj_consume_token(3);
      args = Args();
      jj_consume_token(4);
      break;
    default:
      jj_la1[1] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 5:
      jj_consume_token(5);
      meth = Methods();
      subClasses = classList(0);
      jj_consume_token(6);
      break;
    default:
      jj_la1[2] = jj_gen;
      ;
    }
    {if (true) return new Class(isAbstract, name.image, args, subClasses, meth);}
    throw new Error("Missing return statement in function");
  }

  static final public Class[] classList(int soFar) throws ParseException {
                                 Class c; Class[] subClasses = new Class[0];
    c = Class();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 1:
    case 2:
      subClasses = classList(soFar+1);
      break;
    default:
      jj_la1[3] = jj_gen;
                                      subClasses = new Class[soFar+1];
    }
    subClasses[soFar] = c;
    {if (true) return subClasses;}
    throw new Error("Missing return statement in function");
  }

  static final public Methods Methods() throws ParseException {
                      Methods before = null; Method last;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENT:
      last = Method();
                     before = new Methods(null, last);
      label_1:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case IDENT:
          ;
          break;
        default:
          jj_la1[4] = jj_gen;
          break label_1;
        }
        last = Method();
                          before = new Methods(before, last);
      }
      break;
    default:
      jj_la1[5] = jj_gen;
      ;
    }
    {if (true) return before;}
    throw new Error("Missing return statement in function");
  }

  static final public Method Method() throws ParseException {
                    Type ret; Token name; Param[] params = new Param[0];
    ret = Type();
    name = Id();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 3:
      jj_consume_token(3);
      params = ParamList(0);
      jj_consume_token(4);
      break;
    default:
      jj_la1[6] = jj_gen;
      ;
    }
    {if (true) return new Method(ret, name.image, params);}
    throw new Error("Missing return statement in function");
  }

  static final public Param[] ParamList(int soFar) throws ParseException {
                                 Param[] params = new Param[0]; Param p = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENT:
      p = Param();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 7:
        jj_consume_token(7);
        params = ParamList(soFar+1);
        break;
      default:
        jj_la1[7] = jj_gen;
                                                  params = new Param[soFar+1];
      }
      break;
    default:
      jj_la1[8] = jj_gen;
      ;
    }
    if (soFar == 0) {if (true) return params;}
    params[soFar] = p; {if (true) return params;}
    throw new Error("Missing return statement in function");
  }

  static final public Param Param() throws ParseException {
                  Type t; Token n;
    t = Type();
    n = Id();
    {if (true) return new Param(t, n.image);}
    throw new Error("Missing return statement in function");
  }

  static final public Args Args() throws ParseException {
                Args before = null; Arg last;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 8:
    case 9:
    case 10:
    case IDENT:
      last = Arg();
                 before = new Args(null, last);
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 7:
          ;
          break;
        default:
          jj_la1[9] = jj_gen;
          break label_2;
        }
        jj_consume_token(7);
        last = Arg();
                            before = new Args (before, last);
      }
      break;
    default:
      jj_la1[10] = jj_gen;
      ;
    }
    {if (true) return before;}
    throw new Error("Missing return statement in function");
  }

  static final public Arg Arg() throws ParseException {
              String v; Type t; Token id;
    v = Visibility();
    t = Type();
    id = Id();
    {if (true) return new Arg(v, t, id.image);}
    throw new Error("Missing return statement in function");
  }

  static final public String Visibility() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 8:
    case 9:
    case 10:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 8:
        jj_consume_token(8);
                  {if (true) return "public";}
        break;
      case 9:
        jj_consume_token(9);
                  {if (true) return "protected";}
        break;
      case 10:
        jj_consume_token(10);
                  {if (true) return "private";}
        break;
      default:
        jj_la1[11] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[12] = jj_gen;
      ;
    }
                  {if (true) return null;}
    throw new Error("Missing return statement in function");
  }

  static final public Type Type() throws ParseException {
                Type t;
    t = NameType();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 11:
        ;
        break;
      default:
        jj_la1[13] = jj_gen;
        break label_3;
      }
      jj_consume_token(11);
      jj_consume_token(12);
                            t = new ArrayType(t);
    }
    {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

  static final public NameType NameType() throws ParseException {
                        String[] ids;
    ids = Ids(0);
                 {if (true) return new NameType(ids);}
    throw new Error("Missing return statement in function");
  }

  static final public String[] Ids(int soFar) throws ParseException {
                          Token id; String[] ids;
    id = Id();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 13:
      jj_consume_token(13);
      ids = Ids(soFar+1);
      break;
    default:
      jj_la1[14] = jj_gen;
                                      ids = new String[soFar+1];
    }
    ids[soFar] = id.image; {if (true) return ids;}
    throw new Error("Missing return statement in function");
  }

  static final public Token Id() throws ParseException {
               Token t;
    t = jj_consume_token(IDENT);
    {if (true) return t;}
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
  static final private int[] jj_la1 = new int[15];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x2,0x8,0x20,0x6,0x100000,0x100000,0x8,0x80,0x100000,0x80,0x100700,0x700,0x700,0x800,0x2000,};
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
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
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
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
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
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
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
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ASTGenTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 15; i++) jj_la1[i] = -1;
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
    boolean[] la1tokens = new boolean[23];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 15; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 23; i++) {
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

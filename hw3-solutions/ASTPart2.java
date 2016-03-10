//--------------------------------------------------------------------
// CS321 Languages and Compiler Design I, Winter 2016
//
// Homework 3, Tasks 3 and 4, Sample Solution (AST.java)
//
// This is the version of AST.java that was constructed in the
// sample solution video, so there are not a lot of comments to
// explain the details.  Please consult the videos for explanations
// (or ask me if you have any questions about how this code works).
//
//--------------------------------------------------------------------

class Class {
  // declare the fields for this class
  private boolean isAbstract;
  private String name;
  private Args args;
  private Methods methods;
  private Class[] subclasses;

  // declare default constructor for this class
  Class(boolean isAbstract, String name, Args args, Methods methods, Class[] subclasses) {
    this.isAbstract = isAbstract;
    this.name = name;
    this.args = args;
    this.methods = methods;
    this.subclasses = subclasses;
  }

  /** Output an indented description of this class.
   */
  void indent(IndentOutput out, int n) {
     out.indent(n, name);
     out.indent(n+1, "isAbstract="+isAbstract);
     out.indent(n+1, "Args");
     Args.indent(out, n+2, args, null);
     out.indent(n+1, "Classes");
     indent(out, n+2, subclasses);
  }

  /** Output an indented description of an array of classes.
   */
  public static void indent(IndentOutput out, int n, Class[] classes) {
    for (int i=0; i<classes.length; i++) {
      classes[i].indent(out, n);
    }
  }

  public static void generate(Class parent, Class[] classes) {
    for (int i=0; i<classes.length; i++) {
      classes[i].generate(parent);
    }
  }

  public void generate(Class parent) {
    if (isAbstract) {
      System.out.print("abstract ");
    }
    System.out.print("class " + name);
    if (parent!=null) {
      System.out.print(" extends " + parent.name);
    }
    System.out.println(" {");

    // Find the arguments of the parent:
    Args parentArgs = (parent==null) ? null : parent.args;

    System.out.println("  // Class arguments:");
    Args.fieldDecls(args, parentArgs); // Print field declarations
    System.out.println();

    // Generate code for the constructor:
    System.out.println("  // Default constructor:");
    System.out.print("  " + name + "(");
    Args.constructorArgs(args); // Print constructor arguments
    System.out.println(") {");
    if (parentArgs!=null) {     // Print optional super call
      System.out.print("    super(");
      Args.superArgs(parentArgs);
      System.out.println(");");
    }
    Args.initializers(args, parentArgs); // Initialize class arguments
    System.out.println("  }");
    System.out.println();

    System.out.println("  // Methods:");
    Methods.print(methods);

    System.out.println("}");
    System.out.println();

    generate(this, subclasses);
  }

  // add additional methods and fields here ... 
}

class Args {
  // declare the fields for this class
  Args before;
  Arg  last;

  // declare default constructor for this class
  Args(Args before, Arg last) {
    this.before = before;
    this.last = last;
  }

  /** Output an indented description of a list of arguments.
   */
  public static void indent(IndentOutput out, int n, Args args, Args end) {
    if (args!=end) {                    // Add to the Args class
      indent(out, n, args.before, end);
      args.last.indent(out, n);
    }
  }

  public static void constructorArgs(Args args) {
    if (args!=null) {
       if (args.before!=null) {
         constructorArgs(args.before);
         System.out.print(", ");
       }
       args.last.constructorArg();
    }
  }

  public static void fieldDecls(Args args, Args parentArgs) {
    if (args!=parentArgs) {
      fieldDecls(args.before, parentArgs);
      args.last.fieldDecl();
    }
  }

  public static void superArgs(Args args) {
    if (args!=null) {
      if (args.before!=null) {
        superArgs(args.before);
        System.out.print(", ");
      }
      args.last.superArg();
    }
  }

  public static void initializers(Args args, Args parentArgs) {
    if (args!=parentArgs) {
      initializers(args.before, parentArgs);
      args.last.initializer();
    }
  }

  // add additional methods and fields here ... 
}

class Arg {
  // declare the fields for this class
  private String visibility;
  private Type type;
  private String id;

  // declare default constructor for this class
  Arg(String visibility, Type type, String id) {
    this.visibility = visibility;
    this.type = type;
    this.id = id;
  }

  /** Output an indented description of this argument.
   */
  public void indent(IndentOutput out, int n) {
    StringBuffer buf = new StringBuffer();
    if (visibility!=null) {
      buf.append(visibility);
      buf.append(" ");
    }
    buf.append(type.toString());
    buf.append(" ");
    buf.append(id);
    out.indent(n, buf.toString());
  }

  public void constructorArg() {
    System.out.print(type + " " + id);
  }

  public void fieldDecl() {
    System.out.print("  ");
    if (visibility!=null) {
      System.out.print(visibility + " ");
    }
    System.out.print(type.toString());
    System.out.print(" ");
    System.out.print(id);
    System.out.println(";");
  }

  public void superArg() {
    System.out.print(id);
  }

  public void initializer() {
    System.out.print("    this.");
    System.out.print(id);
    System.out.print(" = ");
    System.out.print(id);
    System.out.println(";");
  }

  // add additional methods and fields here ... 
}

abstract class Type {
  // declare default constructor for this class
  Type() {
  }

  // add additional methods and fields here ... 
}

class NameType extends Type {
  // declare the fields for this class
  private String[] ids;

  // declare default constructor for this class
  NameType(String[] ids) {
    this.ids = ids;
  }

  public String toString() {  // Add this method to the NameType class
    if (ids.length==1) {
      return ids[0];
    } else {
      StringBuffer buf = new StringBuffer(ids[0]);
      for (int i=1; i<ids.length; i++) {
        buf.append(" . ");
        buf.append(ids[i]);
      }
      return buf.toString();
    }
  }

  // add additional methods and fields here ... 
}

class ArrayType extends Type {
  // declare the fields for this class
  private Type elem;

  // declare default constructor for this class
  ArrayType(Type elem) {
    this.elem = elem;
  }

  public String toString() {  // Add this method to the ArrayType class
    return elem.toString() + " []";
  }
  // add additional methods and fields here ... 
}

class Methods {
  // Class arguments:
  private Methods before;
  private Method last;

  // Default constructor:
  Methods(Methods before, Method last) {
    this.before = before;
    this.last = last;
  }

  public static void print(Methods methods) {
    if (methods!=null) {
      print(methods.before);
      methods.last.print();
    }
  }
}

class Method {
  // Class arguments:
  private Type ret;
  private String name;
  private Param [] params;

  // Default constructor:
  Method(Type ret, String name, Param [] params) {
    this.ret = ret;
    this.name = name;
    this.params = params;
  }

  public void print() {
    System.out.print("  ");
    System.out.print((ret==null) ? "void" : ret.toString());
    System.out.print(" ");
    System.out.print(name);
    System.out.print("(");
    for (int i=0; i<params.length; i++) {
      if (i>0) {
        System.out.print(", ");
      }
      params[i].print();
    }
    System.out.println(") {");
    System.out.println("  }");
  }
}

class Param {
  // Class arguments:
  private Type type;
  private String name;

  // Default constructor:
  Param(Type type, String name) {
    this.type = type;
    this.name = name;
  }

  public void print() {
    System.out.print(type.toString());
    System.out.print(" ");
    System.out.print(name);
  }
}

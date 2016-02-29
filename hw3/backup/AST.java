// Tim Jensen
// CS 321
// ASsignment 3 Task 1

import java.util.ArrayList;


class Class {


  private boolean isAbstract;
  private String  name;
  private Args    args;
  private Class[] subclasses;
  private Methods methods;

  Class(boolean isAbstract,
        String  name,
        Args    args,
        Class[] subclasses,
        Methods methods) {

    this.isAbstract = isAbstract;
    this.name = name;
    this.args = args;
    this.subclasses = subclasses;
    this.methods = methods;

  }

  void genJava(int n, String parent, ArrayList<String> pList) {
    ArrayList<String> argList = new ArrayList<>();
    ArrayList<String> methodList = new ArrayList<>();
    StringBuffer buf = new StringBuffer();
    if (isAbstract) {
      buf.append("abstract ");
    }
    buf.append("class ");
    buf.append(name);
    if (parent != null) {
      buf.append(" extends " + parent); 
    }
    buf.append(" {");
    System.out.println(buf.toString());
    indent(n+1, "// declare the fields for this class\n");
    Args.genJava(args, null, n+1, argList);
    indent(0, "\n");
    indent(n+1, "// declare default constructor for this class\n");
    writeConstructor(n+1, name, pList, argList); 
    indent(n+1, "// add additional methods and fields here ...\n"); 
    Methods.genJava(methods, null, n, methodList);
    StringBuffer methodBuf = new StringBuffer();
    while (int i < methodList.size()) {
      methodBuf.append

    }
    System.out.println("}");
    
    genJava(n, subclasses, name, argList); 
     
  }
  
  public static void genJava(int n, Class[] classes, String parent, ArrayList<String> pList) {
    for (int i=0; i<classes.length; i++) {
      classes[i].genJava(n, parent, pList);
    }

  }

  void writeConstructor(int n, String name, ArrayList<String> pList, ArrayList<String> argList) {
    StringBuffer buf = new StringBuffer();
    StringBuffer supers = new StringBuffer();
    ArrayList<String> supersList = new ArrayList<>();
    ArrayList<String> varsList = new ArrayList<>();
    StringBuffer vars = new StringBuffer();
    buf.append(name);
    buf.append("(");
    if (pList != null) {
      int argCount = pList.size();
      int i = 0;
      while (i < argCount) {
        buf.append(pList.get(i));
        buf.append(" ");
        buf.append(pList.get(i+1));
        supers.append("super(" + pList.get(i+1) + ");\n");
        supersList.add(supers.toString());
        supers.delete(0, supers.length());
        i=i+2;
        if (i < argCount || argList.size() != 0) {
          buf.append(", ");
        } 
      }
    }
    if (argList.size() != 0) {
      int argCount = argList.size();
      int i = 0;
      while (i < argCount) {
        buf.append(argList.get(i));
        buf.append(" ");
        buf.append(argList.get(i+1));
        vars.append("this." + argList.get(i+1) + " = " + argList.get(i+1) + ";\n");
        varsList.add(vars.toString());
        vars.delete(0, vars.length());
        i=i+2;
        if (i < argCount) {
          buf.append(", ");
        }
      }
    }
    buf.append(") {\n");
    indent(n, buf.toString());
    for (int i = 0; i < supersList.size(); i++) {
      indent(n+1, supersList.get(i));
    }
    for (int i = 0; i < varsList.size(); i++) {
      indent(n+1, varsList.get(i));
    }
    //indent(n+1, supers.toString());
    //indent(n+1, vars.toString());
    indent(n, "}\n\n");
  }

   public void indent(int n, String msg) {
    for (int i=0; i<n; i++) {
      System.out.print("  ");
    }
    System.out.print(msg);
  }

} 

class Args {

  Args before;
  Arg last;

  Args(Args before, Arg last) {

    this.before = before;
  Arg last;

  Args(Args before, Arg last) {

    this.before = before;
  Arg last;

  Args(Args before, Arg last) {

    this.before = before;
} 

class Args {

  Args before;
  Arg last;

  Args(Args before, Arg last) {

    this.before = before;
    this.last = last;

  }

  public static void genJava(Args args, Args end, int n, ArrayList<String> argList) {
    if (args!=end) {
      genJava(args.before, end, n, argList);
      args.last.genJava(n, argList);
    }
  }

}

class Arg {

  private String visibility;
  private Type   type;
  private String id;

  Arg(String visibility,
      Type   type,
      String id) {

    this.visibility = visibility;
    this.type = type;
    this.id = id;

  }

   public String toString() {   // add to the Arg class
      StringBuffer buf = new StringBuffer();
      buf.append("<");
      if (visibility!=null) {
        buf.append(visibility);
        buf.append(" ");
      }
      buf.append(type.toString());
      buf.append(" ");
      buf.append(id);
      buf.append(">");
      return buf.toString();
  }

/*  public void indent(IndentOutput out, int n) {
    StringBuffer buf = new StringBuffer();
    if (visibility!=null) {
      buf.append(visibility);
      buf.append(" ");
    }
    buf.append(type.toString());
    buf.append(" ");
    buf.append(id);
    out.indent(n, buf.toString());
  }*/

  public void indent(int n, String msg) {
    for (int i=0; i<n; i++) {
      System.out.print("  ");
    }
    System.out.println(msg);
  }

  
  public void genJava(int n, ArrayList<String> argList) {
    StringBuffer buf = new StringBuffer();
    if (visibility!=null) {
      buf.append(visibility);
      buf.append(" ");
    }
    buf.append(type.toString());
    buf.append(" ");
    buf.append(id);
    buf.append(";");
    indent(n, buf.toString());

    argList.add(type.toString());
    argList.add(id);
  }


}

abstract class Type {
  
  Type() {

  }
 
}

class NameType extends Type {

  private String[] ids;

  NameType(String[] ids) {

    this.ids = ids;

  }
  
  public String toString() {  // Add this method to the NameType class
    if (ids.length==1) {
      return ids[0];
    } else {
      StringBuffer buf = new StringBuffer(ids[0]);
      for (int i=1; i<ids.length; i++) {
        buf.append(".");
        buf.append(ids[i]);
      }
      return buf.toString();
    }
  }

}

class ArrayType extends Type {

  private Type elem;

  ArrayType(Type elem) {

    this.elem = elem;

  }
  
  public String toString() {  // Add this method to the ArrayType class
    return elem.toString() + " []";
  }

}

class Methods {
  // declare the fields for this class
  private Methods before;
  private Method last;

  // declare default constructor for this class
  Methods(Methods before, Method last) {
    this.before = before;
    this.last = last;
  }

  // add additional methods and fields here ...
   public static void genJava(Methods args, Methods end, int n, ArrayList<String> argList) {
    if (args!=end) {
      genJava(args.before, end, n, argList);
      args.last.genJava(n, argList);
    }
  }

}
class Method {
  // declare the fields for this class
  private Type ret;
  private String name;
  private Param [] params;

  // declare default constructor for this class
  Method(Type ret, String name, Param [] params) {
    this.ret = ret;
    this.name = name;
    this.params = params;
  }

  // add additional methods and fields here ...

  public void indent(int n, String msg) {
    for (int i=0; i<n; i++) {
      System.out.print("  ");
    }
    System.out.println(msg);
  }


  public void genJava(int n, ArrayList<String> argList) {
    StringBuffer buf = new StringBuffer();
    if (ret == null) { 
      buf.append("void");
    } else {
      buf.append(ret.toString());
    }
    buf.append(" ");
    buf.append(name);
    buf.append(";");
    indent(n, buf.toString());

    argList.add(ret.toString());
    argList.add(name);
    StringBuffer par = new StringBuffer();
    if (params.length = 0) {
      argList.add("");
      return;
    }
    for (int i = 0; i < params.length; i++) {
      par.append(params[i].toString());
      if (i+1 < params.length) { par.append(", "); }
    }
    argList.add(par.toString());
  }
  

}

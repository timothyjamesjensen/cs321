// Tim Jensen
// CS 321
// ASsignment 3 Task 1




class Class {


  private boolean isAbstract;
  private String  name;
  private Args    args;
  private Class[] subclasses;

  Class(boolean isAbstract,
        String  name,
        Args    args,
        Class[] subclasses) {

    this.isAbstract = isAbstract;
    this.name = name;
    this.args = args;
    this.subclasses = subclasses;

  }
} 

class Args {

  Args before;
  Args last;

  Args(Args before, Args after) {

    this.before = before;
    this.last = last;

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
        buf.append(" . ");
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

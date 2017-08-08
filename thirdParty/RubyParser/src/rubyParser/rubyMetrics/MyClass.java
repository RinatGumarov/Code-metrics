package rubyMetrics;

import java.util.ArrayList;

public class MyClass {

	String name;
	MyClass parent;
	ArrayList<String> interfaces = new ArrayList<>();
	ArrayList<Method> methods = new ArrayList<>();
	ArrayList<String> attributes = new ArrayList<>();
	
	
	public void addMethod(String methodName) {
		Method m = new Method(methodName);
		methods.add(m);
	}
	
	public Method getMethod(String methodName) {
		for (Method m: methods) {
			if (methodName.equals(m.getName())) {
				return m;
			}
		}
		return null;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MyClass getParent() {
		return parent;
	}

	public void setParent(MyClass parent) {
		this.parent = parent;
	}

	public ArrayList<String> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(ArrayList<String> interfaces) {
		this.interfaces = interfaces;
	}

	public ArrayList<Method> getMethods() {
		return methods;
	}

	public void setMethods(ArrayList<Method> methods) {
		this.methods = methods;
	}

	public ArrayList<String> getAttributes() {
		return attributes;
	}

	public void setAttributes(ArrayList<String> attributes) {
		this.attributes = attributes;
	}

	public MyClass(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}
	
	
	public static void mergeClassesAll(ArrayList<MyClass> classes) {
		for (MyClass c: classes) {
			MyClass parent = c.getParent();
			if (parent != null && !parent.name.equals("null")) {
				c.setParent(getMyClassParent(parent, classes));			
			}
		}
	}
	
	
	private static boolean isOneClass(MyClass cl1, MyClass cl2) {
		if (cl1.name.equals(cl2.name)) {
			return true;
		}
		return false;
	}
	
	public static MyClass getMyClassParent (MyClass myClass, ArrayList<MyClass> classes) {
		for (MyClass c: classes) {
			if (isOneClass(myClass, c)) {
				return c;
			}
		}		
		return myClass;
		
	}
	
	public static ArrayList<Method> getEqualMethods (ArrayList<Method> mlist1, ArrayList<Method> mlist2) {
		ArrayList<Method> methodsList = new ArrayList<>();
		for (Method m1: mlist1) {
			for (Method m2: mlist2) {
				if (isOneMethod(m1, m2)) {	
					methodsList.add(m1) ;
				}
			}
		}		
		return methodsList;
		
	}

	private static boolean isOneMethod(Method m1, Method m2) {
		if (m1.name.equals(m2.name)) {
			return true;
		}
		return false;
	}
	
}

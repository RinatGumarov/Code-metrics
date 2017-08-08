package csmc.javacc.parse;

import csmc.javacc.generated.CSharpParser;
import csmc.javacc.generated.ParseException;
import csmc.javacc.generated.syntaxtree.Input;
import csmc.javacc.parse.util.Tuple3;
import csmc.lang.CSClass;
import csmc.lang.CSMethod;
import csmc.lang.CSNamespace;
import csmc.javacc.parse.context.NamespaceContext;
import csmc.javacc.parse.util.Tuple2;

import java.io.*;
import java.util.*;

/**
 * ParseDriver class that build intermediate representation for further metrics calculation
 */
public class ParseDriver {
    private CSNamespace global;
    private List<Tuple2<CSClass, String[]>> unresolvedParents; // Child class, fully-qualified name of base class
    private List<Tuple2<CSClass, String[]>> unresolvedUsedClasses; // Using class, fully-qualified names of used class
    private List<Tuple3<CSMethod, String[], String[]>> unresolvedUsedMethods; // Using method, fully-qualified type name, invoked methods names

    public ParseDriver() {
        // Create 'global' namespace
        this.global = new CSNamespace("global", null);
        unresolvedParents = new ArrayList<>();
        unresolvedUsedClasses = new ArrayList<>();
        unresolvedUsedMethods = new ArrayList<>();
    }

    /**
     * Recursively searches the nearest namespace to the given one
     * goDown default value - false
     */
    private Tuple2<CSNamespace, String[]> searchClosestNamespaceRec(CSNamespace current, String[] qualifiedName, boolean goDown) {
        if (qualifiedName.length == 1 && current.getName().equals(qualifiedName[0])) {
            return new Tuple2<>(current, Arrays.copyOfRange(qualifiedName, 1, qualifiedName.length));
        }

        Tuple2<CSNamespace, String[]> search = null;
        for (CSNamespace namespace : current.getNamespaces()) {
            if (namespace.getName().equals(qualifiedName[0])) {
                if (qualifiedName.length == 1) {
                    return new Tuple2<>(namespace, Arrays.copyOfRange(qualifiedName, 1, qualifiedName.length));
                }
                search = searchClosestNamespaceRec(namespace, Arrays.copyOfRange(qualifiedName, 1, qualifiedName.length), true);
                if (search == null) {
                    search = new Tuple2<>(namespace, Arrays.copyOfRange(qualifiedName, 1, qualifiedName.length));
                }
            }
        }

        if (current.getParent() != null && !goDown) {
            Tuple2<CSNamespace, String[]> search2 = searchClosestNamespaceRec(current.getParent(), qualifiedName, false);
            if ((search != null && search2 != null && search.getSecond().length > search2.getSecond().length) || search == null) {
                search = search2;
            }
        }

        if (current.getParent() == null && search == null) {
            search = new Tuple2<>(current, qualifiedName);
        }

        return search;
    }

    /**
     * Public wrapper for searchClosestNamespaceRec()
     */
    public Tuple2<CSNamespace, String[]> searchClosestNamespace(CSNamespace current, String[] qualifiedName) {
        return searchClosestNamespaceRec(current, qualifiedName, false);
    }

    /**
     * Searches closest namespace to the parent.
     * Creates new namespace if not found.
     */
    public CSNamespace searchNamespaceOrCreate(CSNamespace parent, String qualifiedName) {
        Tuple2<CSNamespace, String[]> search = searchClosestNamespaceRec(parent, qualifiedName.split("\\."), false);
        CSNamespace closestNamespace = search.getFirst();
        String[] namePartsLeft = search.getSecond();
        for (String namePart : namePartsLeft) {
            CSNamespace temp = new CSNamespace(namePart, closestNamespace);
            closestNamespace.addNamespace(temp);
            closestNamespace = temp;
        }
        return closestNamespace;
    }

    /**
     * Searches class in given namespace.
     * Crates new class if not found.
     */
    public CSClass searchClassOrCreate(CSNamespace namespace, String className) {
        for (CSClass csClass : namespace.getClasses()) {
            if (csClass.getName().equals(className)) {
                return csClass;
            }
        }
        CSClass csClass = new CSClass(className, namespace);
        namespace.addClass(csClass);
        return csClass;
    }

    /**
     * Searches class in given namespace.
     */
    public CSClass searchClass(CSNamespace namespace, String className) {
        for (CSClass csClass : namespace.getClasses()) {
            if (csClass.getName().equals(className)) {
                return csClass;
            }
        }
        return null;
    }

    /**
     * Searches class in given namespace.
     */
    public CSClass searchClassInTree(CSNamespace namespace, String className) {
        for (CSClass csClass : namespace.getClasses()) {
            if (csClass.getName().equals(className)) {
                return csClass;
            }
        }
        if (namespace.getParent() != null)
            return searchClassInTree(namespace.getParent(), className);
        return null;
    }

    /**
     * Searches closest defined alias and returns fully qualified name.
     */
    public String searchAlias(CSNamespace current, String aliasName) {
        return current.getAliases().containsKey(aliasName) ? current.getAliases().get(aliasName) : current.getParent() == null ? null : searchAlias(current.getParent(), aliasName);
    }

    /**
     * Splits fully qualified-name into parts, resolves alias if present
     */
    public String[] resolveNamespaceOrTypeName(String className, CSClass csClass) {
        if (className.split("::").length == 2) {
            String[] aliasAndName = className.split("::");
            String aliasQualifier = searchAlias(csClass.getNamespace(), aliasAndName[0]);
            if (aliasQualifier == null) {
                throw new RuntimeException("Could not find alias " + aliasAndName[0]);
            }
            className = aliasQualifier + "." + aliasAndName[1];
        }
        String resolvedAliasName = searchAlias(csClass.getNamespace(), className);
        if (resolvedAliasName != null) {
            className = resolvedAliasName;
        }
        String[] partiallyQualifiedName = className.split("\\.");
        String[] contextNamespaceName = csClass.getNamespace().toString().split("\\.");
        int i;
        for (i = contextNamespaceName.length - 1; i >= 0; i--) {
            if (contextNamespaceName[i].equals(partiallyQualifiedName[0]))
                break;
        }
        if (i >= 0)
            contextNamespaceName = Arrays.copyOfRange(contextNamespaceName, 0, i);
        else
            contextNamespaceName = Arrays.copyOfRange(contextNamespaceName, 0, 0);
        List<String> fullyQualifiedName = new ArrayList<>();
        fullyQualifiedName.addAll(Arrays.asList(contextNamespaceName));
        fullyQualifiedName.addAll(Arrays.asList(partiallyQualifiedName));
        if (fullyQualifiedName.get(0).equals("global"))
            fullyQualifiedName.remove(0);
        return fullyQualifiedName.toArray(contextNamespaceName);
    }

    /**
     * Add unresolved parent and related class for post parse processing
     */
    public void addUnresolvedParent(CSClass childClass, String[] baseClassName) {
        unresolvedParents.add(new Tuple2<>(childClass, baseClassName));
    }

    /**
     * Add unresolved used class name and its using class
     */
    public void addUnresolvedUsedClass(CSClass usingClass, String[] usedClassName) {
        unresolvedUsedClasses.add(new Tuple2<>(usingClass, usedClassName));
    }

    /**
     * Add unresolved used method invocation chain, type it is called on and using method
     */
    public void addUnresolvedUsedMethod(CSMethod method, String[] typeName, String[] invocationChain) {
        unresolvedUsedMethods.add(new Tuple3<>(method, typeName, invocationChain));
    }

    /**
     * Post parse processing
     */
    private void postParse() {
        int oldSize;
        do {
            oldSize = unresolvedParents.size();
            for (Iterator<Tuple2<CSClass, String[]>> it = unresolvedParents.iterator(); it.hasNext(); ) {
                Tuple2<CSClass, String[]> current = it.next();
                CSClass child = current.getFirst();
                String[] parentName = current.getSecond();
                Tuple2<CSNamespace, String[]> search = searchClosestNamespace(child.getNamespace(), parentName);
                CSNamespace foundNamespace = search.getFirst();
                String[] namePartsLeft = search.getSecond();
                if (namePartsLeft.length == 1) {
                    CSClass parent = searchClassOrCreate(foundNamespace, namePartsLeft[0]);
                    parent.addChild(child);
                    it.remove();
                }
            }
        } while (unresolvedParents.size() < oldSize);
        for (Tuple2<CSClass, String[]> tuple : unresolvedParents) {
            System.err.println("Could not find base class "
                    + String.join(".", tuple.getSecond())
                    + " for child class " + tuple.getFirst().toString());
        }

        do {
            oldSize = unresolvedUsedClasses.size();
            for (Iterator<Tuple2<CSClass, String[]>> it = unresolvedUsedClasses.iterator(); it.hasNext(); ) {
                Tuple2<CSClass, String[]> current = it.next();
                CSClass usingClass = current.getFirst();
                String[] usedClassName = current.getSecond();
                CSClass usedClass = null;
                if (usedClassName.length == 1)
                    usedClass = searchClassInTree(usingClass.getNamespace(), usedClassName[0]);
                if (usedClass != null) {
                    usingClass.addUsedClass(usedClass);
                    it.remove();
                } else {
                    Tuple2<CSNamespace, String[]> search = searchClosestNamespace(usingClass.getNamespace(), usedClassName);
                    CSNamespace foundNamespace = search.getFirst();
                    String[] namePartsLeft = search.getSecond();
                    if (namePartsLeft.length == 1) {
                        usedClass = searchClassOrCreate(foundNamespace, namePartsLeft[0]);
                        if (usedClass != null) {
                            usingClass.addUsedClass(usedClass);
                            it.remove();
                        }
                    }
                }
            }
        } while (unresolvedUsedClasses.size() < oldSize);
        for (Tuple2<CSClass, String[]> tuple : unresolvedUsedClasses) {
            System.err.println("Could not find used class "
                    + String.join(".", tuple.getSecond())
                    + " for using class " + tuple.getFirst().toString());
        }

        List<Tuple3<CSMethod, String[], String>> skippedUnresolvedUsedMethods = new ArrayList<>(); // Using method, fully-qualified type name, invoked methods names
        for (Iterator<Tuple3<CSMethod, String[], String[]>> it = unresolvedUsedMethods.iterator(); it.hasNext(); ) {
            Tuple3<CSMethod, String[], String[]> current = it.next();
            CSMethod usingMethod = current.getFirst();
            String varType = String.join(".", current.getSecond());
            String[] callChain = current.getThird();

            for (int i = 0; i < callChain.length; i++) {
                String[] qualifiedName = resolveNamespaceOrTypeName(varType, usingMethod.getCsClass());
                CSClass csClass = null;
                if (qualifiedName.length != 1) { // Fully qualified name, with namespace
                    Tuple2<CSNamespace, String[]> search = searchClosestNamespace(usingMethod.getCsClass().getNamespace(), qualifiedName);
                    CSNamespace foundNamespace = search.getFirst();
                    String[] namePartsLeft = search.getSecond();
                    if (namePartsLeft.length == 1) {
                        csClass = searchClass(foundNamespace, namePartsLeft[0]);
                    }
                } else {
                    csClass = searchClassInTree(usingMethod.getCsClass().getNamespace(), qualifiedName[0]);
                }
                if (csClass == null) {
                    if (!callChain[i].isEmpty())
                        skippedUnresolvedUsedMethods.add(new Tuple3<>(usingMethod, qualifiedName, callChain[i]));
                    continue;
                }
                int finalI = i;
                Optional<CSMethod> optionalMethod = csClass.getMethods().stream().filter(m -> m.getName().equals(callChain[finalI])).findFirst();
                if (optionalMethod.isPresent()) {
                    CSMethod method = optionalMethod.get();
                    usingMethod.addInvokedMethod(method);
                    varType = method.getType();
                } else {
                    if (!callChain[i].isEmpty())
                        skippedUnresolvedUsedMethods.add(new Tuple3<>(usingMethod, qualifiedName, callChain[i]));
                }
            }
            it.remove();
        }
        unresolvedUsedMethods.clear();
        for (Tuple3<CSMethod, String[], String> tuple : skippedUnresolvedUsedMethods) {
            System.err.println("Could not find methods used in invocation chain \""
                    + tuple.getThird()
                    + "\" from method " + tuple.getFirst().getCsClass().toString() + "." + tuple.getFirst().getName());
        }
    }

    public void parse(InputStream stream) {
        CSharpParser parser = null;
        try {
            parser = new CSharpParser(new BufferedReader(new InputStreamReader(stream, "UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Input parseTree = null;
        try {
            parseTree = parser.Input();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        parseTree.accept((ParseVisitor) new ParseVisitor(this), new NamespaceContext(null, global.getName(), global));
        postParse();
    }

    public void parse(String dirName) {
        parseFile(new File(dirName));
        postParse();
    }

    private void parseFile(File file) {
        if (file.isDirectory()) {
            File[] subFiles = file.listFiles(f -> f.getName().endsWith(".cs") || f.isDirectory());
            if (subFiles != null) {
                Arrays.sort(subFiles, (file1, file2) -> file1.isDirectory() ? -1 : +1);
                Arrays.stream(subFiles).forEach(this::parseFile);
            }
        } else {
            try {
                System.out.println("Parsing file " + file.getCanonicalPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            CSharpParser parser = null;
            try {
                parser = new CSharpParser(new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8")));
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            Input parseTree = null;
            try {
                parseTree = parser.Input();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            parseTree.accept((ParseVisitor) new ParseVisitor(this), new NamespaceContext(null, global.getName(), global));
        }
    }

    public CSNamespace getGlobalNamespace() {
        return global;
    }
}

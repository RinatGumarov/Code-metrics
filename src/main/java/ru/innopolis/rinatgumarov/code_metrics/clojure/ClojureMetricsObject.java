package ru.innopolis.rinatgumarov.code_metrics.clojure;

import ru.innopolis.rinatgumarov.code_metrics.db.Saveble;

import java.util.Arrays;

/**
 * Created by Rinat on 29.06.17.
 */
public class ClojureMetricsObject extends Saveble {
    private String full_name;
    private int forms, literals, keywords, symbols, lists,
            vectors, maps, sets, nestiness, mxnestiness,
            functions, pfunctions, macros, multis, methods;

    public ClojureMetricsObject(String full_name, int forms, int literals, int keywords, int symbols, int lists, int vectors, int maps, int sets, int nestiness, int mxnestiness, int functions, int pfunctions, int macros, int multis, int methods) {
        this.full_name = full_name;
        this.forms = forms;
        this.literals = literals;
        this.keywords = keywords;
        this.symbols = symbols;
        this.lists = lists;
        this.vectors = vectors;
        this.maps = maps;
        this.sets = sets;
        this.nestiness = nestiness;
        this.mxnestiness = mxnestiness;
        this.functions = functions;
        this.pfunctions = pfunctions;
        this.macros = macros;
        this.multis = multis;
        this.methods = methods;
    }

    public static void main(String[] args) throws NoSuchFieldException {
        ClojureMetricsObject o = new ClojureMetricsObject("ff",5,4,3,2,4,3,4,5,4,3,3,2,3,1,2);
        o.generateQuery();
        Class clojureClazz = ClojureMetricsObject.class;
        System.out.println(clojureClazz.getDeclaredField("full_name"));
//        System.out.println(o.getSimpleName(clojureClazz.getName()));
//        System.out.println(Arrays.toString(clojureClazz.getDeclaredFields()));
//        System.out.println(o.getSimpleName("private java.lang.String ru.innopolis.rinatgumarov.code_metrics.clojure.ClojureMetricsObject.full_name"));
    }

    private String getSimpleName(String name){
        return name.substring(name.lastIndexOf('.') + 1);
    }
}

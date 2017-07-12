package ru.innopolis.rinatgumarov.code_metrics.db;

import ru.innopolis.rinatgumarov.code_metrics.utils.ClassDefinder;

/**
 * Created by Rinat on 03.07.17.
 */
public class Saveble {

    public void save(){

    }

    protected String generateQuery(){
        this.getClass().getDeclaredFields();
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(ClassDefinder.getTableName(this.getClass().getName())).append(" (");
        for (int i = 0; i < this.getClass().getDeclaredFields().length - 1; i++) {
            query.append(this.getSimpleName(this.getClass().getDeclaredFields()[i].toString())).append(", ");
        }
        query.append(this.getSimpleName(this.getClass().getDeclaredFields()
                [this.getClass().getDeclaredFields().length - 1].toString())).append(")")
        .append(" values (");
        System.out.println(query);
        return "";
    }

    private String getSimpleName(String name){
        return name.substring(name.lastIndexOf('.') + 1);
    }
}

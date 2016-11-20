package com.android.javaeemongodb.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;

public class ServerInfoModel {
    @SerializedName("db_host")
    private String dbHost;

    @SerializedName("db_port")
    private String dbPort;

    @SerializedName("db_name")
    private String dbName;

    @SerializedName("collection_name")
    private String collectionName;

    public ServerInfoModel(
            String dbHost,
            String dbPort,
            String dbName,
            String collectionName
    ) {
        this.dbHost = dbHost;
        this.dbPort = dbPort;
        this.dbName = dbName;
        this.collectionName = collectionName;
    }

    public ArrayList<InfoModel> toArrayList() {
        ArrayList<InfoModel> list = new ArrayList<>();

        list.add(new InfoModel(getDbHostTitle(), getDbHost()));
        list.add(new InfoModel(getDbPortTitle(), getDbPort()));
        list.add(new InfoModel(getDbNameTitle(), getDbName()));
        list.add(new InfoModel(getCollectionNameTitle(), getCollectionName()));

        return list;
    }

    public String getDbHost() {
        return dbHost;
    }

    public String getDbPort() {
        return dbPort;
    }

    public String getDbName() {
        return dbName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getDbHostTitle() {
        return "Адреса бази даних";
    }

    public String getDbPortTitle() {
        return "Порт бази даних";
    }

    public String getDbNameTitle() {
        return "Назва бази даних";
    }

    public String getCollectionNameTitle() {
        return "Назва колекції";
    }
}

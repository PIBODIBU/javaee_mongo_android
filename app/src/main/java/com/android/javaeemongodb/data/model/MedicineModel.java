package com.android.javaeemongodb.data.model;

import com.google.gson.annotations.SerializedName;

public class MedicineModel {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("indication")
    private String indication;

    @SerializedName("contraindication")
    private String contraindication;

    @SerializedName("sales_form")
    private String salesForm;

    public MedicineModel(String id, String name, String indication, String contraindication, String salesForm) {
        this.id = id;
        this.name = name;
        this.indication = indication;
        this.contraindication = contraindication;
        this.salesForm = salesForm;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIndication() {
        return indication;
    }

    public String getContraindication() {
        return contraindication;
    }

    public String getSalesForm() {
        return salesForm;
    }
}

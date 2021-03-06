package com.android.javaeemongodb.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MedicineModel extends AbstractModel implements Serializable {
    private final String ID_TITLE = "Ідентифікатор";
    private final String NAME_TITLE = "Медична назва";
    private final String DESCRIPTION_TITLE = "Опис препарату";
    private final String INDICATION_TITLE = "Показання";
    private final String CONTRAINDICATION_TITLE = "Протипоказання";
    private final String SALES_FORM_TITLE = "Форма продажу";

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("indication")
    private String indication;

    @SerializedName("contraindication")
    private String contraindication;

    @SerializedName("sales_form")
    private String salesForm;

    public MedicineModel(
            String id,
            String name,
            String description,
            String indication,
            String contraindication,
            String salesForm
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
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

    public String getIdTitle() {
        return ID_TITLE;
    }

    public String getNameTitle() {
        return NAME_TITLE;
    }

    public String getIndicationTitle() {
        return INDICATION_TITLE;
    }

    public String getContraindicationTitle() {
        return CONTRAINDICATION_TITLE;
    }

    public String getSalesFormTitle() {
        return SALES_FORM_TITLE;
    }
}

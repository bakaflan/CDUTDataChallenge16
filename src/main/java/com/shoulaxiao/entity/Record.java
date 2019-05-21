package com.shoulaxiao.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    @Field
    private String specimen;//标本
    @Field
    private String pathology;//病理
    @Field
    private String differentLevel;//分化等级
    @Field
    private String iraits;//性状
    @Field
    private String uperCut;//上切断
    @Field
    private String lowerCut;//下切端
    @Field
    private String baseCut;//基底切端
    @Field
    private String pathologyLevel;//病理等级
    @Field
    private String tumorSize;//肿瘤大小
    @Field
    private String infiltration;//侵润
    @Field
    private String lymphTransfer;//淋巴结是否转移
    @Field
    private String transferRatio;//转移比例
    @Field
    private String vascularInvasion;//脉管侵犯
    @Field
    private String nerveInvasion;//脉管侵犯

    public Record() {
    }

    public Record(String specimen, String pathology, String differentLevel, String iraits, String uperCut, String lowerCut, String baseCut, String pathologyLevel, String tumorSize, String infiltration, String lymphTransfer, String transferRatio, String vascularInvasion, String nerveInvasion) {
        this.specimen = specimen;
        this.pathology = pathology;
        this.differentLevel = differentLevel;
        this.iraits = iraits;
        this.uperCut = uperCut;
        this.lowerCut = lowerCut;
        this.baseCut = baseCut;
        this.pathologyLevel = pathologyLevel;
        this.tumorSize = tumorSize;
        this.infiltration = infiltration;
        this.lymphTransfer = lymphTransfer;
        this.transferRatio = transferRatio;
        this.vascularInvasion = vascularInvasion;
        this.nerveInvasion = nerveInvasion;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecimen() {
        return specimen;
    }

    public void setSpecimen(String specimen) {
        this.specimen = specimen;
    }

    public String getPathology() {
        return pathology;
    }

    public void setPathology(String pathology) {
        this.pathology = pathology;
    }

    public String getDifferentLevel() {
        return differentLevel;
    }

    public void setDifferentLevel(String differentLevel) {
        this.differentLevel = differentLevel;
    }

    public String getIraits() {
        return iraits;
    }

    public void setIraits(String iraits) {
        this.iraits = iraits;
    }

    public String getUperCut() {
        return uperCut;
    }

    public void setUperCut(String uperCut) {
        this.uperCut = uperCut;
    }

    public String getLowerCut() {
        return lowerCut;
    }

    public void setLowerCut(String lowerCut) {
        this.lowerCut = lowerCut;
    }

    public String getBaseCut() {
        return baseCut;
    }

    public void setBaseCut(String baseCut) {
        this.baseCut = baseCut;
    }

    public String getPathologyLevel() {
        return pathologyLevel;
    }

    public void setPathologyLevel(String pathologyLevel) {
        this.pathologyLevel = pathologyLevel;
    }

    public String getTumorSize() {
        return tumorSize;
    }

    public void setTumorSize(String tumorSize) {
        this.tumorSize = tumorSize;
    }

    public String getInfiltration() {
        return infiltration;
    }

    public void setInfiltration(String infiltration) {
        this.infiltration = infiltration;
    }

    public String getLymphTransfer() {
        return lymphTransfer;
    }

    public void setLymphTransfer(String lymphTransfer) {
        this.lymphTransfer = lymphTransfer;
    }

    public String getTransferRatio() {
        return transferRatio;
    }

    public void setTransferRatio(String transferRatio) {
        this.transferRatio = transferRatio;
    }

    public String getVascularInvasion() {
        return vascularInvasion;
    }

    public void setVascularInvasion(String vascularInvasion) {
        this.vascularInvasion = vascularInvasion;
    }

    public String getNerveInvasion() {
        return nerveInvasion;
    }

    public void setNerveInvasion(String nerveInvasion) {
        this.nerveInvasion = nerveInvasion;
    }

    @Override
    public String toString() {
        return "Record{" +
                "specimen='" + specimen + '\'' +
                ", pathology='" + pathology + '\'' +
                ", differentLevel='" + differentLevel + '\'' +
                ", iraits='" + iraits + '\'' +
                ", uperCut='" + uperCut + '\'' +
                ", lowerCut='" + lowerCut + '\'' +
                ", baseCut='" + baseCut + '\'' +
                ", pathologyLevel=" + pathologyLevel +
                ", tumorSize='" + tumorSize + '\'' +
                ", infiltration='" + infiltration + '\'' +
                ", lymphTransfer='" + lymphTransfer + '\'' +
                ", transferRatio='" + transferRatio + '\'' +
                ", vascularInvasion='" + vascularInvasion + '\'' +
                ", nerveInvasion='" + nerveInvasion + '\'' +
                '}';
    }
}

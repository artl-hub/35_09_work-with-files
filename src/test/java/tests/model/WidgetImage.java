package tests.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WidgetImage {

    private String src;
    @JsonProperty("Name")
    private String name;
    private Integer hOffset;
    private Integer vOffset;
    private String alignment;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer gethOffset() {
        return hOffset;
    }

    public void sethOffset(Integer hOffset) {
        this.hOffset = hOffset;
    }

    public Integer getvOffset() {
        return vOffset;
    }

    public void setvOffset(Integer vOffset) {
        this.vOffset = vOffset;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

}

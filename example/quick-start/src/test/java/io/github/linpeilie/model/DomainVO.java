package io.github.linpeilie.model;

/**
 * 测试一对多映射
 *
 * @author lipanre
 */
public class DomainVO {

    private String voValue;

    public DomainVO(String voValue) {
        this.voValue = voValue;
    }

    public String getVoValue() {
        return voValue;
    }

    public void setVoValue(String voValue) {
        this.voValue = voValue;
    }
}

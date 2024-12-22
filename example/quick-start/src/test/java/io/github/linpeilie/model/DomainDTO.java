package io.github.linpeilie.model;

/**
 * 测试一对多映射
 *
 * @author lipanre
 */
public class DomainDTO {

    private String dtoValue;

    public DomainDTO(String dtoValue) {
        this.dtoValue = dtoValue;
    }

    public String getDtoValue() {
        return dtoValue;
    }

    public void setDtoValue(String dtoValue) {
        this.dtoValue = dtoValue;
    }

}

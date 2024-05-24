package io.github.linpeilie;

import io.github.linpeilie.me.conversion.lossy.CutleryInventoryDto;
import io.github.linpeilie.me.conversion.lossy.CutleryInventoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withinPercentage;

@SpringBootTest(classes = Application.class)
public class LossyConversionTest {

    @Autowired
    private Converter converter;

    @Test
    public void testNoErrorCase() {

        CutleryInventoryDto dto = new CutleryInventoryDto();
        dto.setNumberOfForks(5);
        dto.setNumberOfKnifes((short) 7);
        dto.setNumberOfSpoons((byte) 3);
        dto.setApproximateKnifeLength(3.7f);

        CutleryInventoryEntity entity = converter.convert(dto, CutleryInventoryEntity.class);
        assertThat(entity.getNumberOfForks()).isEqualTo(5L);
        assertThat(entity.getNumberOfKnifes()).isEqualTo(7);
        assertThat(entity.getNumberOfSpoons()).isEqualTo((short) 3);
        assertThat(entity.getApproximateKnifeLength()).isCloseTo(3.7d, withinPercentage(0.0001d));
    }

}

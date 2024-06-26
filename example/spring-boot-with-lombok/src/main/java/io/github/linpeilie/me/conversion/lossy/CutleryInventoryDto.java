/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.github.linpeilie.me.conversion.lossy;

import io.github.linpeilie.annotations.AutoMapper;
import org.mapstruct.ReportingPolicy;

@AutoMapper(
    target = CutleryInventoryEntity.class,
    typeConversionPolicy = ReportingPolicy.ERROR,
    reverseConvertGenerate = false
)
public class CutleryInventoryDto {

    private short numberOfKnifes;
    private int numberOfForks;
    private byte numberOfSpoons;

    private float approximateKnifeLength;

    public short getNumberOfKnifes() {
        return numberOfKnifes;
    }

    public void setNumberOfKnifes(short numberOfKnifes) {
        this.numberOfKnifes = numberOfKnifes;
    }

    public int getNumberOfForks() {
        return numberOfForks;
    }

    public void setNumberOfForks(int numberOfForks) {
        this.numberOfForks = numberOfForks;
    }

    public byte getNumberOfSpoons() {
        return numberOfSpoons;
    }

    public void setNumberOfSpoons(byte numberOfSpoons) {
        this.numberOfSpoons = numberOfSpoons;
    }

    public float getApproximateKnifeLength() {
        return approximateKnifeLength;
    }

    public void setApproximateKnifeLength(float approximateKnifeLength) {
        this.approximateKnifeLength = approximateKnifeLength;
    }
}

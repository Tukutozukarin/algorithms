package org.pg4200.ex01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleClassificationTest {

    @Test
    public void notATriangle(){
        assertEquals(TriangleClassification.Classification.NOT_A_TRIANGLE, TriangleClassification.classify(1,2,0));
        assertEquals(TriangleClassification.Classification.NOT_A_TRIANGLE, TriangleClassification.classify(1,2,5));
    }

    @Test
    public void equailateral() {
        assertEquals(TriangleClassification.Classification.EQUILATERAL, TriangleClassification.classify(2,2,2));
    }

    @Test
    public void isoceles() {
        assertEquals(TriangleClassification.Classification.ISOSCELES, TriangleClassification.classify(2,2,1));
    }

    @Test
    public void scalene() {
        assertEquals(TriangleClassification.Classification.SCALENE, TriangleClassification.classify(3,5,4));
    }
}

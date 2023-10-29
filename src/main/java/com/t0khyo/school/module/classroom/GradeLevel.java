package com.t0khyo.school.module.classroom;

public enum GradeLevel {
    GRADE_1(1),
    GRADE_2(2),
    GRADE_3(3),
    GRADE_4(4),
    GRADE_5(5),
    GRADE_6(6),
    GRADE_7(7),
    GRADE_8(8),
    GRADE_9(9),
    GRADE_10(10),
    GRADE_11_LITERARY(11),
    GRADE_11_SCIENCE(11),
    GRADE_12_LITERARY(12),
    GRADE_12_SCIENCE(12);

    private final int order;

    GradeLevel(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public boolean isLiterary() {
        return this == GRADE_11_LITERARY || this == GRADE_12_LITERARY;
    }

    public boolean isScience() {
        return this == GRADE_11_SCIENCE || this == GRADE_12_SCIENCE;
    }
}

package com.edilson.justiniano.schedulermanagement.base;

import java.time.LocalDateTime;

public final class DefaultConstants {

    private DefaultConstants() {
    }

    public static final String DEFAULT_JOB_ID_TWO_HOURS = "2";
    public static final String DEFAULT_JOB_ID_SIX_HOURS = "6";
    public static final String DEFAULT_JOB_ID_SEVEN_HOURS = "7";
    public static final String DEFAULT_JOB_ID_NINE_HOURS = "9";

    public static final String DEFAULT_JOB_DESCRIPTION = "description";
    public static final String DEFAULT_CONCLUSION_FROM = "2020-07-01 23:59:59";
    public static final String DEFAULT_CONCLUSION_DEADLINE = "2020-07-20 23:59:59";
    public static final LocalDateTime DEFAULT_CONCLUSION_FROM_DATETIME = LocalDateTime.of(2020, 7, 1, 23, 59, 59);
    public static final LocalDateTime DEFAULT_CONCLUSION_DEADLINE_DATETIME = LocalDateTime.of(2020, 7, 20, 23, 59, 59);

    public static final float DEFAULT_JOB_ESTIMATED_TIME_TWO_HOURS = 2F;
    public static final float DEFAULT_JOB_ESTIMATED_TIME_SIX_HOURS = 6F;
    public static final float DEFAULT_JOB_ESTIMATED_TIME_SEVEN_HOURS = 7F;
    public static final float DEFAULT_JOB_ESTIMATED_TIME_NINE_HOURS = 9F;

}

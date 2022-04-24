package com.learning.redis.springredis.entity.dto;

public class UserBookDto {

    private int reading_status;
    private int is_favourite;

    public int getReading_status() {
        return reading_status;
    }

    public void setReading_status(int reading_status) {
        this.reading_status = reading_status;
    }

    public int getIs_favourite() {
        return is_favourite;
    }

    public void setIs_favourite(int is_favourite) {
        this.is_favourite = is_favourite;
    }
}

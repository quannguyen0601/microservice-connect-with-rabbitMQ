/**
 * MyProject Project
 * Request.java
 * Copyright QuanNguyen. All rights reserved.
 */
package vn.quan.nguyen.message.core.entity;

import lombok.Data;

/**
 * Request.java
 */
@Data
public class Request {
    private Type type;
    private String content;
//    private LocalDate date;

    public enum Type {
        APP1,
        APP2
    }
}

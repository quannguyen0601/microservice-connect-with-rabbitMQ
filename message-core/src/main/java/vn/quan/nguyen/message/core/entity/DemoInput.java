/**
 * SharingWork Project
 * DemoImput.java
 * Copyright © Citynow Inc. All rights reserved.
 */
package vn.quan.nguyen.message.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

/**
 * DemoImput.java
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DemoInput implements Serializable {
    private static final long serialVersionUID = 1234L;

    private String data;
    private Date currentDate;
    private Boolean isFlag;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private OffsetDateTime offsetDateTime;
    private SmallEntity smallEntity;
}

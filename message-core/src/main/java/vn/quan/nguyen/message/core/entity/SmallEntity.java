/**
 * SharingWork Project
 * SmallEntity.java
 * Copyright © Citynow Inc. All rights reserved.
 */
package vn.quan.nguyen.message.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * SmallEntity.java
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmallEntity implements Serializable {
    private static final long serialVersionUID = 1235L;

    private String name;
    private BigDecimal total;
}

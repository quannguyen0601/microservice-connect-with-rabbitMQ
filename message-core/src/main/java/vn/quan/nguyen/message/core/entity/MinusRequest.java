/**
 * SharingWork Project
 * MinusRequest.java
 * Copyright © Citynow Inc. All rights reserved.
 */
package vn.quan.nguyen.message.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * MinusRequest.java
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MinusRequest implements Serializable {
    private Long numberA;
    private Long numberB;
}

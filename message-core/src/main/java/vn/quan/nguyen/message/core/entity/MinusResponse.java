/**
 * SharingWork Project
 * MinusResponse.java
 * Copyright © Citynow Inc. All rights reserved.
 */
package vn.quan.nguyen.message.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * MinusResponse.java
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MinusResponse implements Serializable {
    private Long minusResult;
    private Date data;
}

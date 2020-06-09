/**
 * SharingWork Project
 * SumResponse.java
 * Copyright © Citynow Inc. All rights reserved.
 */
package vn.quan.nguyen.message.core.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * SumResponse.java
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SumResponse implements Serializable {
    @NonNull
    private Long sum;

    private Date result;
}

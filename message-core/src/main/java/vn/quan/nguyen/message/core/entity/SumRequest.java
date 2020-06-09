/**
 * SharingWork Project
 * SumRequest.java
 * Copyright © Citynow Inc. All rights reserved.
 */
package vn.quan.nguyen.message.core.entity;

import lombok.*;

import java.io.Serializable;

/**
 * SumRequest.java
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SumRequest implements Serializable {
    @NonNull
    private Long a;
    @NonNull
    private Long b;
}

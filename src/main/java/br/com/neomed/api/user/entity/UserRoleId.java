package br.com.neomed.api.user.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleId implements Serializable {

    private Long userId;
    private Long roleId;
}
package com.adminService.admin.dtos;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminDto implements Serializable {

    private static final long serialVersionUID = 7156526077883281623L;

    int id;

    String adminName;

    String token;
}

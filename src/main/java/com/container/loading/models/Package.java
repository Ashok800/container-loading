package com.container.loading.models;

import lombok.Data;

@Data
public class Package {

        private String package_name;
        private Double package_length;
        private Double package_width;
        private Double package_height;
        private String package_status;

}


package com.container.loading.models;

import lombok.Data;

import java.util.Date;

@Data
public class Package {

        private String package_name;
        private String package_id;
        private Double package_length;
        private Double package_width;
        private Double package_height;
        private String package_status;
        private String package_source;
        private String package_destination;
        private String package_owner;
        private String package_contact_number;
        private Date created_date;

}


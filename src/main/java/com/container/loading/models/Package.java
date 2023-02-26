package com.container.loading.models;

import lombok.Data;

import java.util.Date;

@Data
public class Package {

        private String package_name;
        private String package_id;
        private int package_length;
        private int package_width;
        private int package_height;
        private String package_status;
        private String package_source;
        private String package_destination;
        private String package_owner;
        private String package_contact_number;

        private String created_date;
}


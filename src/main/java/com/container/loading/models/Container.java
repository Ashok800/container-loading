package com.container.loading.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Data
@Setter
@Getter
public class Container {
  private  String container_name;
  private String container_id;
  private int container_length;
  private int container_width;
  private int container_height;
  private String container_status;


}

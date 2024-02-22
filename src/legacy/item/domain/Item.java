package com.panda.back.v2.item.domain;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Item {
  private final Long id;
  private final String name;
  private List<String> images;
  private Long createdAt;

  @Builder
  public Item(Long id, String name, List<String> images, Long createdAt) {
    this.id = id;
    this.name = name;
    this.images = images;
    this.createdAt = createdAt;
  }
  public void addImages(String imageUrl) {
    if(this.images.size() <= 4) {
      images.add(imageUrl);
    }
  }
  public void updateImages(List<String> images) {
    this.images = images;
  }


}

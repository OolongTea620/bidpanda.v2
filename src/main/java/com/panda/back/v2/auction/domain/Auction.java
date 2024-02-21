package com.panda.back.v2.auction.domain;

import com.panda.back.v2.item.domain.Item;
import com.panda.back.v2.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Auction {
  private final Long id;
  private final User seller;
  private final Item item;
  public final String title;
  public final String description;

  private final long bidPriceUnit;
  private final long startPrice;
  private final long minSoldPrice;
  private final long bidCount;
  private final long curPrice;

  private final Long startedAt;
  private final Long closedAt;
  private final Long createdAt;
  private final Long lastBidAt;

  private final AuctionStatus status;

  @Builder
  public Auction(Long id, User seller, Item item, String title, String description,
      long bidPriceUnit,
      long startPrice, long minSoldPrice, long bidCount, long curPrice, Long startedAt,
      Long closedAt,
      Long createdAt,
      Long lastBidAt, AuctionStatus status) {
    this.id = id;
    this.seller = seller;
    this.item = item;
    this.title = title;
    this.description = description;
    this.bidPriceUnit = bidPriceUnit;
    this.startPrice = startPrice;
    this.minSoldPrice = minSoldPrice;
    this.bidCount = bidCount;
    this.curPrice = curPrice;
    this.startedAt = startedAt;
    this.closedAt = closedAt;
    this.createdAt = createdAt;
    this.lastBidAt = lastBidAt;
    this.status = status;
  }
}

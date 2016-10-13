package com.arx.android.salesapp.utils;

/**
 * Created by esa on 27/11/14. with awesomeness
 */
public class PageHelper implements Cloneable {

  private int page;
  private int firstPage;
  private int itemPerPage;
  private boolean onHold;
  private boolean onLastPage;

  private PageHelper() {
  }

  public static PageHelper with(int firstPage) {
    PageHelper pageHelper = new PageHelper();
    pageHelper.setFirstPage(firstPage);
    pageHelper.setPage(firstPage);
    return pageHelper;
  }

  public static PageHelper getDefault() {
    PageHelper pageHelper = new PageHelper().itemPerPage(15);
    pageHelper.setFirstPage(0);
    pageHelper.setPage(0);
    pageHelper.hold();
    return pageHelper;
  }

  public PageHelper itemPerPage(int itemPerPage) {
    this.itemPerPage = itemPerPage;
    return this;
  }

  public synchronized void nextPage() {
    page++;
    onHold = false;
  }

  public synchronized void nextPage(int page) {
    this.page = page;
    onHold = false;
  }

  public synchronized void hold() {
    onHold = true;
  }

  public synchronized void reset() {
    page = firstPage;
    onHold = false;
    onLastPage = false;
  }

  public synchronized int getNextPage() {
    return page + 1;
  }

  public int getCurrentPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getFirstPage() {
    return firstPage;
  }

  public void setFirstPage(int firstPage) {
    this.firstPage = firstPage;
  }

  public boolean isOnHold() {
    return onHold;
  }

  public int getThreshold() {
    return page * itemPerPage;
  }

  public void setLastPage(boolean isLastPage) {
    this.onLastPage = isLastPage;
  }

  public boolean isOnLastPage() {
    return this.onLastPage;
  }

  public boolean isFirstPage() {
    return page == firstPage;
  }

  public int getItemPerPage(){
    return itemPerPage;
  }

  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}

package in.co.rays.proj4.bean;

import java.util.Date;

public class PortfolioBean extends BaseBean {

    private String portfolioName;
    private String totalValue;      
    private Date createdDate;

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getKey() {
        return id + "";
    }

    public String getValue() {
        return portfolioName;
    }
}
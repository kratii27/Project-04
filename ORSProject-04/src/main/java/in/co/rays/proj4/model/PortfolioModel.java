package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.PortfolioBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class PortfolioModel {

    // ---------- nextPk(): Get next Primary Key ----------
    public Integer nextPk() throws DatabaseException {

        int pk = 0;
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_portfolio");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                pk = rs.getInt(1);
            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            throw new DatabaseException("Exception : Exception in getting PK");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        return pk + 1;
    }

    // ---------- add(): Add Portfolio ----------
    public void add(PortfolioBean bean) throws ApplicationException, DuplicateRecordException {

        Connection conn = null;
        int pk = 0;

        PortfolioBean existbean = findByPortfolioName(bean.getPortfolioName());
        if (existbean != null) {
            throw new DuplicateRecordException("Portfolio Name already exists");
        }

        try {
            pk = nextPk();
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(
                    "insert into st_portfolio values(?, ?, ?, ?, ?, ?, ?, ?)");

            pstmt.setInt(1, pk);
            pstmt.setString(2, bean.getPortfolioName());
            pstmt.setString(3, bean.getTotalValue());
            pstmt.setDate(4, new java.sql.Date(bean.getCreatedDate().getTime()));
            pstmt.setString(5, bean.getCreatedBy());
            pstmt.setString(6, bean.getModifiedBy());
            pstmt.setTimestamp(7, bean.getCreatedDatetime());
            pstmt.setTimestamp(8, bean.getModifiedDatetime());

            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();

        } catch (Exception e) {

            try {
                conn.rollback();
            } catch (SQLException e1) {
                throw new ApplicationException("Exception : add rollback exception " + e1.getMessage());
            }

            throw new ApplicationException("Exception : add exception " + e.getMessage());

        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    // ---------- update(): Update Portfolio ----------
    public void update(PortfolioBean bean) throws ApplicationException, DuplicateRecordException {

        Connection conn = null;

        PortfolioBean existBean = findByPortfolioName(bean.getPortfolioName());
        if (existBean != null && existBean.getId() != bean.getId()) {
            throw new DuplicateRecordException("Portfolio Name already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(
                    "update st_portfolio set portfolio_name=?, total_value=?, created_date=?, "
                  + "created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id=?");

            pstmt.setString(1, bean.getPortfolioName());
            pstmt.setString(2, bean.getTotalValue());
            pstmt.setDate(3, new java.sql.Date(bean.getCreatedDate().getTime()));
            pstmt.setString(4, bean.getCreatedBy());
            pstmt.setString(5, bean.getModifiedBy());
            pstmt.setTimestamp(6, bean.getCreatedDatetime());
            pstmt.setTimestamp(7, bean.getModifiedDatetime());
            pstmt.setLong(8, bean.getId());

            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();

        } catch (Exception e) {

            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException("Exception : Rollback exception " + ex.getMessage());
            }

            throw new ApplicationException("Exception in updating Portfolio");

        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    // ---------- delete(): Delete Portfolio ----------
    public void delete(PortfolioBean bean) throws ApplicationException {

        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement("delete from st_portfolio where id=?");
            pstmt.setLong(1, bean.getId());

            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();

        } catch (Exception e) {

            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
            }

            throw new ApplicationException("Exception : Exception in deleting Portfolio");

        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    // ---------- findByPortfolioName(): Find Portfolio by Name ----------
    public PortfolioBean findByPortfolioName(String portfolioName) throws ApplicationException {

        StringBuffer sql = new StringBuffer("select * from st_portfolio where portfolio_name=?");
        PortfolioBean bean = null;
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, portfolioName);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bean = new PortfolioBean();

                bean.setId(rs.getLong(1));
                bean.setPortfolioName(rs.getString(2));
                bean.setTotalValue(rs.getString(3));
                bean.setCreatedDate(rs.getDate(4));
                bean.setCreatedBy(rs.getString(5));
                bean.setModifiedBy(rs.getString(6));
                bean.setCreatedDatetime(rs.getTimestamp(7));
                bean.setModifiedDatetime(rs.getTimestamp(8));
            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            throw new ApplicationException("Exception: Exception in getting Portfolio by Name");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        return bean;
    }

    // ---------- findByPk(): Find Portfolio by ID ----------
    public PortfolioBean findByPk(long pk) throws ApplicationException {

        PortfolioBean bean = null;
        Connection conn = null;

        StringBuffer sql = new StringBuffer("select * from st_portfolio where id=?");

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bean = new PortfolioBean();

                bean.setId(rs.getLong(1));
                bean.setPortfolioName(rs.getString(2));
                bean.setTotalValue(rs.getString(3));
                bean.setCreatedDate(rs.getDate(4));
                bean.setCreatedBy(rs.getString(5));
                bean.setModifiedBy(rs.getString(6));
                bean.setCreatedDatetime(rs.getTimestamp(7));
                bean.setModifiedDatetime(rs.getTimestamp(8));
            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in getting Portfolio by PK");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        return bean;
    }

    // ---------- list(): List all Portfolios ----------
    public List<PortfolioBean> list() throws ApplicationException {
        return search(null, 0, 0);
    }

    // ---------- search(): Search Portfolios with Pagination ----------
    public List<PortfolioBean> search(PortfolioBean bean, int pageNo, int pageSize) throws ApplicationException {

        StringBuffer sql = new StringBuffer("select * from st_portfolio where 1=1");

        if (bean != null) {

            if (bean.getId() > 0) {
                sql.append(" and id=" + bean.getId());
            }

            if (bean.getPortfolioName() != null && bean.getPortfolioName().length() > 0) {
                sql.append(" and portfolio_name like '" + bean.getPortfolioName() + "%'");
            }

            if (bean.getTotalValue() != null && bean.getTotalValue().length() > 0) {
                sql.append(" and total_value like '" + bean.getTotalValue() + "%'");
            }

            if (bean.getCreatedDate() != null) {
                sql.append(" and created_date = '" + new java.sql.Date(bean.getCreatedDate().getTime()) + "'");
            }
        }

        if (pageSize > 0) {
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + ", " + pageSize);
        }

        Connection conn = null;
        ArrayList<PortfolioBean> list = new ArrayList<>();

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                PortfolioBean bean1 = new PortfolioBean();

                bean1.setId(rs.getLong(1));
                bean1.setPortfolioName(rs.getString(2));
                bean1.setTotalValue(rs.getString(3));
                bean1.setCreatedDate(rs.getDate(4));
                bean1.setCreatedBy(rs.getString(5));
                bean1.setModifiedBy(rs.getString(6));
                bean1.setCreatedDatetime(rs.getTimestamp(7));
                bean1.setModifiedDatetime(rs.getTimestamp(8));

                list.add(bean1);
            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in search Portfolio");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        return list;
    }
}
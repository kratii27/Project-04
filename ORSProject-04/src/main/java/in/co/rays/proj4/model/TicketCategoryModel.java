package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.TicketCategoryBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class TicketCategoryModel {

    // ---------- nextPk() ----------
    public Integer nextPk() throws DatabaseException {

        int pk = 0;
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_ticket_category");
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

    // ---------- add() ----------
    public void add(TicketCategoryBean bean) throws ApplicationException, DuplicateRecordException {

        Connection conn = null;
        int pk = 0;

        TicketCategoryBean existBean = findByName(bean.getCategoryName());
        if (existBean != null) {
            throw new DuplicateRecordException("Category Name already exists");
        }

        try {
            pk = nextPk();
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(
                    "insert into st_ticket_category values(?, ?, ?, ?, ?, ?, ?, ?)");

            pstmt.setInt(1, pk);
            pstmt.setString(2, bean.getCategoryName());
            pstmt.setLong(3, bean.getPrice());
            pstmt.setInt(4, bean.getAvailableSeats());
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

    // ---------- update() ----------
    public void update(TicketCategoryBean bean) throws ApplicationException, DuplicateRecordException {

        Connection conn = null;

        TicketCategoryBean existBean = findByName(bean.getCategoryName());
        if (existBean != null && existBean.getId() != bean.getId()) {
            throw new DuplicateRecordException("Category Name already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(
                    "update st_ticket_category set category_name=?, price=?, available_seats=?, "
                  + "created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id=?");

            pstmt.setString(1, bean.getCategoryName());
            pstmt.setLong(2, bean.getPrice());
            pstmt.setInt(3, bean.getAvailableSeats());
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

            throw new ApplicationException("Exception in updating TicketCategory");

        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    // ---------- delete() ----------
    public void delete(TicketCategoryBean bean) throws ApplicationException {

        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement("delete from st_ticket_category where id=?");
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

            throw new ApplicationException("Exception : Exception in deleting TicketCategory");

        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    // ---------- findByName() ----------
    public TicketCategoryBean findByName(String categoryName) throws ApplicationException {

        StringBuffer sql = new StringBuffer("select * from st_ticket_category where category_name=?");
        TicketCategoryBean bean = null;
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, categoryName);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bean = new TicketCategoryBean();
                bean.setId(rs.getLong(1));
                bean.setCategoryName(rs.getString(2));
                bean.setPrice(rs.getLong(3));
                bean.setAvailableSeats(rs.getInt(4));
                bean.setCreatedBy(rs.getString(5));
                bean.setModifiedBy(rs.getString(6));
                bean.setCreatedDatetime(rs.getTimestamp(7));
                bean.setModifiedDatetime(rs.getTimestamp(8));
            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException("Exception: Exception in getting TicketCategory by Name");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        return bean;
    }

    // ---------- findByPk() ----------
    public TicketCategoryBean findByPk(long pk) throws ApplicationException {

        TicketCategoryBean bean = null;
        Connection conn = null;

        StringBuffer sql = new StringBuffer("select * from st_ticket_category where id=?");

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bean = new TicketCategoryBean();
                bean.setId(rs.getLong(1));
                bean.setCategoryName(rs.getString(2));
                bean.setPrice(rs.getLong(3));
                bean.setAvailableSeats(rs.getInt(4));
                bean.setCreatedBy(rs.getString(5));
                bean.setModifiedBy(rs.getString(6));
                bean.setCreatedDatetime(rs.getTimestamp(7));
                bean.setModifiedDatetime(rs.getTimestamp(8));
            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in getting TicketCategory by PK");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        return bean;
    }

    // ---------- list() ----------
    public List<TicketCategoryBean> list() throws ApplicationException {
        return search(null, 0, 0);
    }

    // ---------- search() ----------
    public List<TicketCategoryBean> search(TicketCategoryBean bean, int pageNo, int pageSize) throws ApplicationException {

        StringBuffer sql = new StringBuffer("select * from st_ticket_category where 1=1");

        if (bean != null) {

            if (bean.getId() > 0) {
                sql.append(" and id=" + bean.getId());
            }

            if (bean.getCategoryName() != null && bean.getCategoryName().length() > 0) {
                sql.append(" and category_name like '" + bean.getCategoryName() + "%'");
            }

            if (bean.getPrice() != null && bean.getPrice() > 0) {
                sql.append(" and price=" + bean.getPrice());
            }

            if (bean.getAvailableSeats() != null && bean.getAvailableSeats() > 0) {
                sql.append(" and available_seats=" + bean.getAvailableSeats());
            }
        }

        if (pageSize > 0) {
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + ", " + pageSize);
        }

        Connection conn = null;
        ArrayList<TicketCategoryBean> list = new ArrayList<>();

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                TicketCategoryBean bean1 = new TicketCategoryBean();
                bean1.setId(rs.getLong(1));
                bean1.setCategoryName(rs.getString(2));
                bean1.setPrice(rs.getLong(3));
                bean1.setAvailableSeats(rs.getInt(4));
                bean1.setCreatedBy(rs.getString(5));
                bean1.setModifiedBy(rs.getString(6));
                bean1.setCreatedDatetime(rs.getTimestamp(7));
                bean1.setModifiedDatetime(rs.getTimestamp(8));

                list.add(bean1);
            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in search TicketCategory");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        return list;
    }
}
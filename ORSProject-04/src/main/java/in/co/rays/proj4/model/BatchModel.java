package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.BatchBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class BatchModel {

    // ---------- nextPk(): Get next Primary Key ----------
    public Integer nextPk() throws DatabaseException {

        int pk = 0;
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_batch");
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

    // ---------- add(): Add Batch ----------
    public void add(BatchBean bean) throws ApplicationException, DuplicateRecordException {

        Connection conn = null;
        int pk = 0;

        BatchBean existBean = findByBatchCode(bean.getBatchCode());
        if (existBean != null) {
            throw new DuplicateRecordException("Batch Code already exists");
        }

        try {
            pk = nextPk();
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(
                    "insert into st_batch values(?, ?, ?, ?, ?, ?, ?, ?, ?)");

            pstmt.setInt(1, pk);
            pstmt.setString(2, bean.getBatchCode());
            pstmt.setString(3, bean.getBatchName());
            pstmt.setString(4, bean.getTrainerName());
            pstmt.setString(5, bean.getBatchTiming());
            pstmt.setString(6, bean.getCreatedBy());
            pstmt.setString(7, bean.getModifiedBy());
            pstmt.setTimestamp(8, bean.getCreatedDatetime());
            pstmt.setTimestamp(9, bean.getModifiedDatetime());

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

    // ---------- update(): Update Batch ----------
    public void update(BatchBean bean) throws ApplicationException, DuplicateRecordException {

        Connection conn = null;

        BatchBean existBean = findByBatchCode(bean.getBatchCode());
        if (existBean != null && existBean.getId() != bean.getId()) {
            throw new DuplicateRecordException("Batch Code already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(
                    "update st_batch set batch_code=?, batch_name=?, trainer_name=?, batch_timing=?, "
                  + "created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id=?");

            pstmt.setString(1, bean.getBatchCode());
            pstmt.setString(2, bean.getBatchName());
            pstmt.setString(3, bean.getTrainerName());
            pstmt.setString(4, bean.getBatchTiming());
            pstmt.setString(5, bean.getCreatedBy());
            pstmt.setString(6, bean.getModifiedBy());
            pstmt.setTimestamp(7, bean.getCreatedDatetime());
            pstmt.setTimestamp(8, bean.getModifiedDatetime());
            pstmt.setLong(9, bean.getId());

            pstmt.executeUpdate();
            conn.commit();
            pstmt.close();

        } catch (Exception e) {

            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException("Exception : Rollback exception " + ex.getMessage());
            }

            throw new ApplicationException("Exception in updating Batch");

        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    // ---------- delete(): Delete Batch ----------
    public void delete(BatchBean bean) throws ApplicationException {

        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement("delete from st_batch where id=?");
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

            throw new ApplicationException("Exception : Exception in deleting Batch");

        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    // ---------- findByBatchCode(): Find Batch by Batch Code ----------
    public BatchBean findByBatchCode(String batchCode) throws ApplicationException {

        StringBuffer sql = new StringBuffer("select * from st_batch where batch_code = ?");
        BatchBean bean = null;
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, batchCode);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bean = new BatchBean();
                bean.setId(rs.getLong(1));
                bean.setBatchCode(rs.getString(2));
                bean.setBatchName(rs.getString(3));
                bean.setTrainerName(rs.getString(4));
                bean.setBatchTiming(rs.getString(5));
                bean.setCreatedBy(rs.getString(6));
                bean.setModifiedBy(rs.getString(7));
                bean.setCreatedDatetime(rs.getTimestamp(8));
                bean.setModifiedDatetime(rs.getTimestamp(9));
            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
        	 e.printStackTrace();
            throw new ApplicationException("Exception: Exception in getting Batch by Batch Code");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        return bean;
    }

    // ---------- findByPk(): Find Batch by ID ----------
    public BatchBean findByPk(long pk) throws ApplicationException {

        BatchBean bean = null;
        Connection conn = null;

        StringBuffer sql = new StringBuffer("select * from st_batch where id=?");

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bean = new BatchBean();
                bean.setId(rs.getLong(1));
                bean.setBatchCode(rs.getString(2));
                bean.setBatchName(rs.getString(3));
                bean.setTrainerName(rs.getString(4));
                bean.setBatchTiming(rs.getString(5));
                bean.setCreatedBy(rs.getString(6));
                bean.setModifiedBy(rs.getString(7));
                bean.setCreatedDatetime(rs.getTimestamp(8));
                bean.setModifiedDatetime(rs.getTimestamp(9));
            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in getting Batch by PK");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        return bean;
    }

    // ---------- list(): List all Batches ----------
    public List<BatchBean> list() throws ApplicationException {
        return search(null, 0, 0);
    }

    // ---------- search(): Search Batches with Pagination ----------
    public List<BatchBean> search(BatchBean bean, int pageNo, int pageSize) throws ApplicationException {

        StringBuffer sql = new StringBuffer("select * from st_batch where 1=1");

        if (bean != null) {

            if (bean.getId() > 0) {
                sql.append(" and id=" + bean.getId());
            }

            if (bean.getBatchCode() != null && bean.getBatchCode().length() > 0) {
                sql.append(" and batch_code like '" + bean.getBatchCode() + "%'");
            }

            if (bean.getBatchName() != null && bean.getBatchName().length() > 0) {
                sql.append(" and batch_name like '" + bean.getBatchName() + "%'");
            }

            if (bean.getTrainerName() != null && bean.getTrainerName().length() > 0) {
                sql.append(" and trainer_name like '" + bean.getTrainerName() + "%'");
            }

            if (bean.getBatchTiming() != null && bean.getBatchTiming().length() > 0) {
                sql.append(" and batch_timing like '" + bean.getBatchTiming() + "%'");
            }
        }

        if (pageSize > 0) {
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + ", " + pageSize);
        }

        Connection conn = null;
        ArrayList<BatchBean> list = new ArrayList<>();

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                BatchBean bean1 = new BatchBean();
                bean1.setId(rs.getLong(1));
                bean1.setBatchCode(rs.getString(2));
                bean1.setBatchName(rs.getString(3));
                bean1.setTrainerName(rs.getString(4));
                bean1.setBatchTiming(rs.getString(5));
                bean1.setCreatedBy(rs.getString(6));
                bean1.setModifiedBy(rs.getString(7));
                bean1.setCreatedDatetime(rs.getTimestamp(8));
                bean1.setModifiedDatetime(rs.getTimestamp(9));

                list.add(bean1);
            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in search Batch");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        return list;
    }
}
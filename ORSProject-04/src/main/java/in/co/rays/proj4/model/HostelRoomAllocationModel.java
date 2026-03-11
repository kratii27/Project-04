package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.HostelRoomAllocationBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class HostelRoomAllocationModel {

    // ---------- nextPk() ----------
    public Integer nextPk() throws DatabaseException {

        int pk = 0;
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_hostel_room_allocation");
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
    public void add(HostelRoomAllocationBean bean) throws ApplicationException, DuplicateRecordException {

        Connection conn = null;
        int pk = 0;

        HostelRoomAllocationBean existBean = findByRoomNumber(bean.getRoomNumber());
        if (existBean != null) {
            throw new DuplicateRecordException("Room Number already allocated");
        }

        try {
            pk = nextPk();
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(
                    "insert into st_hostel_room_allocation values(?, ?, ?, ?, ?, ?, ?, ?, ?)");

            pstmt.setInt(1, pk);
            pstmt.setString(2, bean.getStudentName());
            pstmt.setLong(3, bean.getRoomNumber());
            pstmt.setString(4, bean.getBlockName());
            pstmt.setDate(5, new java.sql.Date(bean.getAllotmentDate().getTime()));
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

    // ---------- update() ----------
    public void update(HostelRoomAllocationBean bean) throws ApplicationException, DuplicateRecordException {

        Connection conn = null;

        HostelRoomAllocationBean existBean = findByRoomNumber(bean.getRoomNumber());
        if (existBean != null && existBean.getId() != bean.getId()) {
            throw new DuplicateRecordException("Room Number already allocated");
        }

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(
                    "update st_hostel_room_allocation set student_name=?, room_number=?, block_name=?, "
                  + "allotment_date=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id=?");

            pstmt.setString(1, bean.getStudentName());
            pstmt.setLong(2, bean.getRoomNumber());
            pstmt.setString(3, bean.getBlockName());
            pstmt.setDate(4, new java.sql.Date(bean.getAllotmentDate().getTime()));
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

            throw new ApplicationException("Exception in updating HostelRoomAllocation");

        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    // ---------- delete() ----------
    public void delete(HostelRoomAllocationBean bean) throws ApplicationException {

        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement("delete from st_hostel_room_allocation where id=?");
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

            throw new ApplicationException("Exception : Exception in deleting HostelRoomAllocation");

        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    // ---------- findByRoomNumber() ----------
    public HostelRoomAllocationBean findByRoomNumber(Long roomNumber) throws ApplicationException {

        StringBuffer sql = new StringBuffer("select * from st_hostel_room_allocation where room_number=?");
        HostelRoomAllocationBean bean = null;
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, roomNumber);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bean = new HostelRoomAllocationBean();
                bean.setId(rs.getLong(1));
                bean.setStudentName(rs.getString(2));
                bean.setRoomNumber(rs.getLong(3));
                bean.setBlockName(rs.getString(4));
                bean.setAllotmentDate(rs.getDate(5));
                bean.setCreatedBy(rs.getString(6));
                bean.setModifiedBy(rs.getString(7));
                bean.setCreatedDatetime(rs.getTimestamp(8));
                bean.setModifiedDatetime(rs.getTimestamp(9));
            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException("Exception: Exception in getting HostelRoomAllocation by Room Number");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        return bean;
    }

    // ---------- findByPk() ----------
    public HostelRoomAllocationBean findByPk(long pk) throws ApplicationException {

        HostelRoomAllocationBean bean = null;
        Connection conn = null;

        StringBuffer sql = new StringBuffer("select * from st_hostel_room_allocation where id=?");

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bean = new HostelRoomAllocationBean();
                bean.setId(rs.getLong(1));
                bean.setStudentName(rs.getString(2));
                bean.setRoomNumber(rs.getLong(3));
                bean.setBlockName(rs.getString(4));
                bean.setAllotmentDate(rs.getDate(5));
                bean.setCreatedBy(rs.getString(6));
                bean.setModifiedBy(rs.getString(7));
                bean.setCreatedDatetime(rs.getTimestamp(8));
                bean.setModifiedDatetime(rs.getTimestamp(9));
            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in getting HostelRoomAllocation by PK");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        return bean;
    }

    // ---------- list() ----------
    public List<HostelRoomAllocationBean> list() throws ApplicationException {
        return search(null, 0, 0);
    }

    // ---------- search() ----------
    public List<HostelRoomAllocationBean> search(HostelRoomAllocationBean bean, int pageNo, int pageSize) throws ApplicationException {

        StringBuffer sql = new StringBuffer("select * from st_hostel_room_allocation where 1=1");

        if (bean != null) {

            if (bean.getId() > 0) {
                sql.append(" and id=" + bean.getId());
            }

            if (bean.getStudentName() != null && bean.getStudentName().length() > 0) {
                sql.append(" and student_name like '" + bean.getStudentName() + "%'");
            }

            if (bean.getRoomNumber() != null && bean.getRoomNumber() > 0) {
                sql.append(" and room_number=" + bean.getRoomNumber());
            }

            if (bean.getBlockName() != null && bean.getBlockName().length() > 0) {
                sql.append(" and block_name like '" + bean.getBlockName() + "%'");
            }
        }

        if (pageSize > 0) {
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + ", " + pageSize);
        }

        Connection conn = null;
        ArrayList<HostelRoomAllocationBean> list = new ArrayList<>();

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                HostelRoomAllocationBean bean1 = new HostelRoomAllocationBean();
                bean1.setId(rs.getLong(1));
                bean1.setStudentName(rs.getString(2));
                bean1.setRoomNumber(rs.getLong(3));
                bean1.setBlockName(rs.getString(4));
                bean1.setAllotmentDate(rs.getDate(5));
                bean1.setCreatedBy(rs.getString(6));
                bean1.setModifiedBy(rs.getString(7));
                bean1.setCreatedDatetime(rs.getTimestamp(8));
                bean1.setModifiedDatetime(rs.getTimestamp(9));

                list.add(bean1);
            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in search HostelRoomAllocation");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        return list;
    }
}
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.MusicPlaylistBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class MusicPlaylistModel {

    // ---------- nextPk() ----------
    public Integer nextPk() throws DatabaseException {

        int pk = 0;
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_music_playlist");
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
    public void add(MusicPlaylistBean bean) throws ApplicationException, DuplicateRecordException {

        Connection conn = null;
        int pk = 0;

        MusicPlaylistBean existBean = findBySongName(bean.getSongName());
        if (existBean != null) {
            throw new DuplicateRecordException("Song Name already exists");
        }

        try {
            pk = nextPk();
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(
                    "insert into st_music_playlist values(?, ?, ?, ?, ?, ?, ?, ?, ?)");

            pstmt.setInt(1, pk);
            pstmt.setString(2, bean.getPlaylistName());
            pstmt.setString(3, bean.getSongName());
            pstmt.setString(4, bean.getArtist());
            pstmt.setString(5, bean.getAlbum());
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
    public void update(MusicPlaylistBean bean) throws ApplicationException, DuplicateRecordException {

        Connection conn = null;

        MusicPlaylistBean existBean = findBySongName(bean.getSongName());
        if (existBean != null && existBean.getId() != bean.getId()) {
            throw new DuplicateRecordException("Song Name already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement(
                    "update st_music_playlist set playlist_name=?, song_name=?, artist=?, album=?, "
                  + "created_by=?, modified_by=?, created_datetime=?, modified_datetime=? where id=?");

            pstmt.setString(1, bean.getPlaylistName());
            pstmt.setString(2, bean.getSongName());
            pstmt.setString(3, bean.getArtist());
            pstmt.setString(4, bean.getAlbum());
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

            throw new ApplicationException("Exception in updating MusicPlaylist");

        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    // ---------- delete() ----------
    public void delete(MusicPlaylistBean bean) throws ApplicationException {

        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement pstmt = conn.prepareStatement("delete from st_music_playlist where id=?");
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

            throw new ApplicationException("Exception : Exception in deleting MusicPlaylist");

        } finally {
            JDBCDataSource.closeConnection(conn);
        }
    }

    // ---------- findBySongName() ----------
    public MusicPlaylistBean findBySongName(String songName) throws ApplicationException {

        StringBuffer sql = new StringBuffer("select * from st_music_playlist where song_name=?");
        MusicPlaylistBean bean = null;
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, songName);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bean = new MusicPlaylistBean();
                bean.setId(rs.getLong(1));
                bean.setPlaylistName(rs.getString(2));
                bean.setSongName(rs.getString(3));
                bean.setArtist(rs.getString(4));
                bean.setAlbum(rs.getString(5));
                bean.setCreatedBy(rs.getString(6));
                bean.setModifiedBy(rs.getString(7));
                bean.setCreatedDatetime(rs.getTimestamp(8));
                bean.setModifiedDatetime(rs.getTimestamp(9));
            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException("Exception: Exception in getting MusicPlaylist by Song Name");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        return bean;
    }

    // ---------- findByPk() ----------
    public MusicPlaylistBean findByPk(long pk) throws ApplicationException {

        MusicPlaylistBean bean = null;
        Connection conn = null;

        StringBuffer sql = new StringBuffer("select * from st_music_playlist where id=?");

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                bean = new MusicPlaylistBean();
                bean.setId(rs.getLong(1));
                bean.setPlaylistName(rs.getString(2));
                bean.setSongName(rs.getString(3));
                bean.setArtist(rs.getString(4));
                bean.setAlbum(rs.getString(5));
                bean.setCreatedBy(rs.getString(6));
                bean.setModifiedBy(rs.getString(7));
                bean.setCreatedDatetime(rs.getTimestamp(8));
                bean.setModifiedDatetime(rs.getTimestamp(9));
            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in getting MusicPlaylist by PK");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        return bean;
    }

    // ---------- list() ----------
    public List<MusicPlaylistBean> list() throws ApplicationException {
        return search(null, 0, 0);
    }

    // ---------- search() ----------
    public List<MusicPlaylistBean> search(MusicPlaylistBean bean, int pageNo, int pageSize) throws ApplicationException {

        StringBuffer sql = new StringBuffer("select * from st_music_playlist where 1=1");

        if (bean != null) {

            if (bean.getId() > 0) {
                sql.append(" and id=" + bean.getId());
            }

            if (bean.getPlaylistName() != null && bean.getPlaylistName().length() > 0) {
                sql.append(" and playlist_name like '" + bean.getPlaylistName() + "%'");
            }

            if (bean.getSongName() != null && bean.getSongName().length() > 0) {
                sql.append(" and song_name like '" + bean.getSongName() + "%'");
            }

            if (bean.getArtist() != null && bean.getArtist().length() > 0) {
                sql.append(" and artist like '" + bean.getArtist() + "%'");
            }

            if (bean.getAlbum() != null && bean.getAlbum().length() > 0) {
                sql.append(" and album like '" + bean.getAlbum() + "%'");
            }
        }

        if (pageSize > 0) {
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + ", " + pageSize);
        }

        Connection conn = null;
        ArrayList<MusicPlaylistBean> list = new ArrayList<>();

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                MusicPlaylistBean bean1 = new MusicPlaylistBean();
                bean1.setId(rs.getLong(1));
                bean1.setPlaylistName(rs.getString(2));
                bean1.setSongName(rs.getString(3));
                bean1.setArtist(rs.getString(4));
                bean1.setAlbum(rs.getString(5));
                bean1.setCreatedBy(rs.getString(6));
                bean1.setModifiedBy(rs.getString(7));
                bean1.setCreatedDatetime(rs.getTimestamp(8));
                bean1.setModifiedDatetime(rs.getTimestamp(9));

                list.add(bean1);
            }

            rs.close();
            pstmt.close();

        } catch (Exception e) {
            throw new ApplicationException("Exception : Exception in search MusicPlaylist");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        return list;
    }
}
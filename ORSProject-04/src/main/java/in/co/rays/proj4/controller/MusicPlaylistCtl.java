package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.MusicPlaylistBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.MusicPlaylistModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "MusicPlaylistCtl", urlPatterns = { "/ctl/MusicPlaylistCtl" })
public class MusicPlaylistCtl extends BaseCtl {

    @Override
    protected void preload(HttpServletRequest request) {
        // No preload required
    }

    @Override
    protected boolean validate(HttpServletRequest request) {

        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("playlistName"))) {
            request.setAttribute("playlistName", PropertyReader.getValue("error.require", "Playlist Name"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("songName"))) {
            request.setAttribute("songName", PropertyReader.getValue("error.require", "Song Name"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("artist"))) {
            request.setAttribute("artist", PropertyReader.getValue("error.require", "Artist"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("album"))) {
            request.setAttribute("album", PropertyReader.getValue("error.require", "Album"));
            pass = false;
        }

        return pass;
    }

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {

        MusicPlaylistBean bean = new MusicPlaylistBean();

        bean.setId(DataUtility.getLong(request.getParameter("id")));
        bean.setPlaylistName(DataUtility.getString(request.getParameter("playlistName")));
        bean.setSongName(DataUtility.getString(request.getParameter("songName")));
        bean.setArtist(DataUtility.getString(request.getParameter("artist")));
        bean.setAlbum(DataUtility.getString(request.getParameter("album")));

        populateDTO(bean, request);
        return bean;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id = DataUtility.getLong(request.getParameter("id"));
        MusicPlaylistModel model = new MusicPlaylistModel();

        if (id > 0) {
            try {
                MusicPlaylistBean bean = model.findByPk(id);
                ServletUtility.setBean(bean, request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                ServletUtility.handleException(e, request, response);
                return;
            }
        }
        ServletUtility.forward(getView(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("operation");
        MusicPlaylistModel model = new MusicPlaylistModel();
        long id = DataUtility.getLong(request.getParameter("id"));

        if (OP_SAVE.equalsIgnoreCase(op)) {
            MusicPlaylistBean bean = (MusicPlaylistBean) populateBean(request);
            try {
                model.add(bean);
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Music Playlist added successfully", request);
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Song Name already exists", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                ServletUtility.handleException(e, request, response);
                return;
            }

        } else if (OP_UPDATE.equalsIgnoreCase(op)) {
            MusicPlaylistBean bean = (MusicPlaylistBean) populateBean(request);
            try {
                if (id > 0) {
                    model.update(bean);
                }
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Music Playlist updated successfully", request);
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Song Name already exists", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                ServletUtility.handleException(e, request, response);
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.MUSIC_PLAYLIST_LIST_CTL, request, response);
            return;

        } else if (OP_RESET.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.MUSIC_PLAYLIST_CTL, request, response);
            return;
        }

        ServletUtility.forward(getView(), request, response);
    }

    @Override
    protected String getView() {
        return ORSView.MUSIC_PLAYLIST_VIEW;
    }
}
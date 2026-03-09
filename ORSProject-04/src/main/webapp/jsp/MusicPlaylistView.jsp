<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.MusicPlaylistCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Music Playlist</title>
</head>
<body>
    <%@ include file="Header.jsp"%>
    <form action="<%=ORSView.MUSIC_PLAYLIST_CTL%>" method="post">
        <jsp:useBean id="bean" class="in.co.rays.proj4.bean.MusicPlaylistBean"
            scope="request"></jsp:useBean>

        <h1 align="center" style="margin-bottom: -15; color: navy">
            <%if (bean != null && bean.getId() > 0) {%>Update<%} else {%>Add<%}%>
            Music Playlist
        </h1>
        <div style="height: 15px; margin-bottom: 12px">
            <H3 align="center"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></H3>
            <H3 align="center"><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></H3>
        </div>

        <input type="hidden" name="id" value="<%=bean.getId()%>">
        <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
        <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
        <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
        <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

        <table align="center">
            <tr>
                <th>Playlist Name</th>
                <td><input type="text" name="playlistName"
                    value="<%=DataUtility.getStringData(bean.getPlaylistName())%>"
                    placeholder="enter playlist name here"></td>
                <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("playlistName", request)%></font></td>
            </tr>
            <tr>
                <th>Song Name</th>
                <td><input type="text" name="songName"
                    value="<%=DataUtility.getStringData(bean.getSongName())%>"
                    placeholder="enter song name here"></td>
                <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("songName", request)%></font></td>
            </tr>
            <tr>
                <th>Artist</th>
                <td><input type="text" name="artist"
                    value="<%=DataUtility.getStringData(bean.getArtist())%>"
                    placeholder="enter artist here"></td>
                <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("artist", request)%></font></td>
            </tr>
            <tr>
                <th>Album</th>
                <td><input type="text" name="album"
                    value="<%=DataUtility.getStringData(bean.getAlbum())%>"
                    placeholder="enter album here"></td>
                <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("album", request)%></font></td>
            </tr>
            <tr>
                <th></th>
                <%if (bean != null && bean.getId() > 0) {%>
                <td align="left" colspan="2">
                    <input type="submit" name="operation" value="<%=MusicPlaylistCtl.OP_UPDATE%>">
                    <input type="submit" name="operation" value="<%=MusicPlaylistCtl.OP_CANCEL%>">
                <%} else {%>
                <td align="left" colspan="2">
                    <input type="submit" name="operation" value="<%=MusicPlaylistCtl.OP_SAVE%>">
                    <input type="submit" name="operation" value="<%=MusicPlaylistCtl.OP_RESET%>">
                <%}%>
                </td>
            </tr>
        </table>
    </form>
    <%@ include file="Footer.jsp"%>
</body>
</html>
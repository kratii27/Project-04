<%@page import="in.co.rays.proj4.bean.MusicPlaylistBean"%>
<%@page import="in.co.rays.proj4.controller.MusicPlaylistListCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<html>
<head>
<title>Music Playlist List</title>
<link rel="icon" type="image/png"
    href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
    <%@include file="Header.jsp"%>

    <jsp:useBean id="bean" class="in.co.rays.proj4.bean.MusicPlaylistBean"
        scope="request"></jsp:useBean>

    <div align="center">
        <h1 align="center" style="margin-bottom: -15; color: navy;">Music Playlist List</h1>

        <div style="height: 15px; margin-bottom: 12px">
            <h3><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></h3>
            <h3><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></h3>
        </div>

        <form action="<%=ORSView.MUSIC_PLAYLIST_LIST_CTL%>" method="post">
            <%
                int pageNo = ServletUtility.getPageNo(request);
                int pageSize = ServletUtility.getPageSize(request);
                int index = ((pageNo - 1) * pageSize) + 1;
                int nextListSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

                List<MusicPlaylistBean> list = (List<MusicPlaylistBean>) ServletUtility.getList(request);
                Iterator<MusicPlaylistBean> it = list.iterator();

                if (list.size() != 0) {
            %>

            <input type="hidden" name="pageNo" value="<%=pageNo%>">
            <input type="hidden" name="pageSize" value="<%=pageSize%>">

            <table style="width: 100%">
                <tr>
                    <td align="center">
                        <label><b>Playlist Name :</b></label>
                        <input type="text" name="playlistName" placeholder="Enter Playlist Name"
                            value="<%=ServletUtility.getParameter("playlistName", request)%>">&emsp;

                        <label><b>Song Name :</b></label>
                        <input type="text" name="songName" placeholder="Enter Song Name"
                            value="<%=ServletUtility.getParameter("songName", request)%>">&emsp;

                        <label><b>Artist :</b></label>
                        <input type="text" name="artist" placeholder="Enter Artist"
                            value="<%=ServletUtility.getParameter("artist", request)%>">&emsp;

                        <input type="submit" name="operation" value="<%=MusicPlaylistListCtl.OP_SEARCH%>">
                        &nbsp;
                        <input type="submit" name="operation" value="<%=MusicPlaylistListCtl.OP_RESET%>">
                    </td>
                </tr>
            </table>
            <br>

            <table border="1" style="width: 100%; border: groove;">
                <tr style="background-color: #e1e6f1e3;">
                    <th width="5%"><input type="checkbox" id="selectall" /></th>
                    <th width="5%">S.No</th>
                    <th width="15%">Playlist Name</th>
                    <th width="20%">Song Name</th>
                    <th width="20%">Artist</th>
                    <th width="20%">Album</th>
                    <th width="5%">Edit</th>
                </tr>

                <%
                    while (it.hasNext()) {
                        bean = (MusicPlaylistBean) it.next();
                %>
                <tr>
                    <td style="text-align: center;"><input type="checkbox"
                        class="case" name="ids" value="<%=bean.getId()%>"></td>
                    <td style="text-align: center;"><%=index++%></td>
                    <td style="text-align: center; text-transform: capitalize;"><%=bean.getPlaylistName()%></td>
                    <td style="text-align: center; text-transform: capitalize;"><%=bean.getSongName()%></td>
                    <td style="text-align: center; text-transform: capitalize;"><%=bean.getArtist()%></td>
                    <td style="text-align: center; text-transform: capitalize;"><%=bean.getAlbum()%></td>
                    <td style="text-align: center;"><a href="MusicPlaylistCtl?id=<%=bean.getId()%>">Edit</a></td>
                </tr>
                <%
                    }
                %>
            </table>

            <table style="width: 100%">
                <tr>
                    <td style="width: 25%"><input type="submit" name="operation"
                        value="<%=MusicPlaylistListCtl.OP_PREVIOUS%>"
                        <%=pageNo > 1 ? "" : "disabled"%>></td>
                    <td align="center" style="width: 25%"><input type="submit"
                        name="operation" value="<%=MusicPlaylistListCtl.OP_NEW%>"></td>
                    <td align="center" style="width: 25%"><input type="submit"
                        name="operation" value="<%=MusicPlaylistListCtl.OP_DELETE%>"></td>
                    <td style="width: 25%" align="right"><input type="submit"
                        name="operation" value="<%=MusicPlaylistListCtl.OP_NEXT%>"
                        <%=nextListSize != 0 ? "" : "disabled"%>></td>
                </tr>
            </table>

            <%
                } else {
            %>
            <table>
                <tr>
                    <td align="right"><input type="submit" name="operation"
                        value="<%=MusicPlaylistListCtl.OP_BACK%>"></td>
                </tr>
            </table>
            <%
                }
            %>
        </form>
    </div>
    <%@ include file="Footer.jsp"%>

    <script type="text/javascript">
        $(document).ready(function() {
            $("#selectall").click(function() {
                $('.case').prop('checked', this.checked);
            });
            $(".case").click(function() {
                $("#selectall").prop("checked",
                    $(".case").length === $(".case:checked").length);
            });
        });
    </script>
</body>
</html>
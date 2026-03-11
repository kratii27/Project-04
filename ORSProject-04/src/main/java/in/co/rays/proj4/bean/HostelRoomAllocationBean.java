package in.co.rays.proj4.bean;

import java.util.Date;

public class HostelRoomAllocationBean extends BaseBean {

    private String studentName;
    private Long roomNumber;
    private String blockName;
    private Date allotmentDate;

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public Long getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }
    public String getBlockName() {
        return blockName;
    }
    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }
    public Date getAllotmentDate() {
        return allotmentDate;
    }
    public void setAllotmentDate(Date allotmentDate) {
        this.allotmentDate = allotmentDate;
    }

    @Override
    public String getKey() {
        return null;
    }
    @Override
    public String getValue() {
        return null;
    }
}
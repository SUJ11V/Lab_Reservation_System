package source;

public class Reservation {
  public Lab lab;
  
  public String dateR;
  public String labId;
  public String startTimeR;
  public String endTimeR;
  public int seatId;
  
  public Reservation(String date, String labId, String startTime, String endTime,int seatId){
      this.dateR=date;
      this.labId=labId;
      this.startTimeR=startTime;
      this.endTimeR=endTime;
      this.seatId=seatId;
 };
  
  private Permission permission;
  
  public Reservation() {
      this.permission = new PermissionOff();
  }
  
  public void setPermission(Permission permission) {
      this.permission = permission;
  }
  
  public void permissionOnPushed() {
      permission.permissionOnPushed();  
  }
  
  public void permissionOffPushed() {
      permission.permissionOffPushed();
  }
  
  public void possible() {
    permission.possible();
  }


  public void checkIn() {
    // Bouml preserved body begin 0001F682
    // Bouml preserved body end 0001F682
  }

  public void checkOut() {
    // Bouml preserved body begin 0001F702
    // Bouml preserved body end 0001F702
  }

  public int right;

  public int reserId;

  public Student stu;

}

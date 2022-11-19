package source;

public class PermissionOn implements Permission {
  public void possible() {
      System.out.println("예약 승인");
  }

  
  public void permissionOnPushed() {
      System.out.println("예약 승인");   
  }
  public void permissionOffPushed() {
      System.out.println("예약 미승인");
  }
  

}

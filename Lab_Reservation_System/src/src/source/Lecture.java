package source;

public class Lecture {
  public Lab lab;

  public String startTime;

  public String endTime;

  public String lecName;
  
  public int day;
  
  public String pId;
  
  public String labId;
  
  public Lecture(String labId,String lecName, String startTime, String endTime, int day, String pId){
      this.labId=labId;
      this.lecName=lecName;
      this.startTime=startTime;
      this.endTime=endTime;
      this.day=day;
      this.pId=pId;
  }

  public void checkClass() {
    // Bouml preserved body begin 0001F582
    // Bouml preserved body end 0001F582
  }

  public void resetClass() {
    // Bouml preserved body begin 0001F602
    // Bouml preserved body end 0001F602
  }

  
}

package source;

public class Seminar {
  public String seminarName;
  public String dateS;
  public String labId;
  public String pId;
  public String startTimeS;
  public String endTimeS;
  
  public Seminar(String dateS, String seminarName, String pId, String labId, String startTimeS, String endTimeS){
      this.seminarName=seminarName;
      this.pId=pId;
      this.dateS=dateS;
      this.labId=labId;
      this.startTimeS=startTimeS;
      this.endTimeS=endTimeS;
 };

}

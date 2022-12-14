package LabReservationSystem;

import static LabReservationSystem.LoginMain.loginId;
import static LabReservationSystem.LoginMain.loginName;
import source.Lecture;
import source.Reservation;
import source.Seminar;
import java.awt.Color;
import java.awt.List;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.Border;

public class StudentMain extends javax.swing.JFrame {

    Color pink = new Color(246, 226, 231);  // 핑크색 저장
    Color yellow = new Color(254, 255, 233);  // 노란색 저장

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql; //쿼리문 받을 변수
    int num = 1;
    String labItem = null;  // 5시 이후 강의실 아이템 저장
    String[][] reser = {{"915", "0"}, {"916", "0"}, {"918", "0"}, {"911", "0"}};  // 예약카운트 2차원 배열

    ArrayList<JPanel> timeTable =new ArrayList<>(); //시간표
    ArrayList<JRadioButton> seat = new ArrayList<>();
    ArrayList<JRadioButton> seatInquire = new ArrayList<>();
    Reservation reservation; //사용자에게 입력받을 예약 정보 객체
    //Reservation reservationList[]=new Reservation[31];  //db에서 예약정보를 저장할 객체
    Lecture lecture;    //강의 객체
    Seminar seminar;    //세미나(또는 특강) 객체
    
    public String getDayString(int day) {
        String dayString = null;
        if (day == 1) {
            dayString = "월";
        }
        if (day == 2) {
            dayString = "화";
        }
        if (day == 3) {
            dayString = "수";
        }
        if (day == 4) {
            dayString = "목";
        }
        if (day == 5) {
            dayString = "금";
        }
        if (day == 6) {
            dayString = "토";
        }
        if (day == 7) {
            dayString = "일";
        }

        return dayString;
    }
    
    public int getDay(Seminar seminar) { //요일 구하기
        //dateR은 "yyyy/mm/dd" 형식으로 된 string type으로 받는다.
        int yNum = seminar.dateS.indexOf("/"); //4
        int mNum = seminar.dateS.indexOf("/", yNum + 1);//7

        int year = Integer.parseInt(seminar.dateS.substring(0, yNum));    //년
        int month = Integer.parseInt(seminar.dateS.substring(yNum + 1, mNum));   //월
        int day = Integer.parseInt(seminar.dateS.substring(mNum + 1));    //일

        LocalDate date = LocalDate.of(year, month, day);   //date에 날짜 저장
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int dayOfWeekNumber = dayOfWeek.getValue(); //날짜에 대한 요일을 숫자형식으로
        return dayOfWeekNumber;                   //1: 월, 2: 화, 3: 수, ....
    }

    public void setSeat() {
        seat.add(seat31);
        seat.add(seat32);
        seat.add(seat33);
        seat.add(seat34);
        seat.add(seat35);
        seat.add(seat36);
        seat.add(seat37);
        seat.add(seat38);
        seat.add(seat39);
        seat.add(seat40);
        seat.add(seat41);
        seat.add(seat42);
        seat.add(seat43);
        seat.add(seat44);
        seat.add(seat45);
        seat.add(seat46);
        seat.add(seat47);
        seat.add(seat48);
        seat.add(seat49);
        seat.add(seat50);
        seat.add(seat51);
        seat.add(seat52);
        seat.add(seat53);
        seat.add(seat54);
        seat.add(seat55);
        seat.add(seat56);
        seat.add(seat57);
        seat.add(seat58);
        seat.add(seat59);
        seat.add(seat60);
    }

    public void setSeatInquire() {
        seatInquire.add(seat61);
        seatInquire.add(seat62);
        seatInquire.add(seat63);
        seatInquire.add(seat64);
        seatInquire.add(seat65);
        seatInquire.add(seat66);
        seatInquire.add(seat67);
        seatInquire.add(seat68);
        seatInquire.add(seat69);
        seatInquire.add(seat70);
        seatInquire.add(seat71);
        seatInquire.add(seat72);
        seatInquire.add(seat73);
        seatInquire.add(seat74);
        seatInquire.add(seat75);
        seatInquire.add(seat76);
        seatInquire.add(seat77);
        seatInquire.add(seat78);
        seatInquire.add(seat79);
        seatInquire.add(seat80);
        seatInquire.add(seat81);
        seatInquire.add(seat82);
        seatInquire.add(seat83);
        seatInquire.add(seat84);
        seatInquire.add(seat85);
        seatInquire.add(seat86);
        seatInquire.add(seat87);
        seatInquire.add(seat88);
        seatInquire.add(seat89);
        seatInquire.add(seat90);
    }

    public void setTimeTable(){
        timeTable.add(Mon1);
        timeTable.add(Tue1);
        timeTable.add(Wed1);
        timeTable.add(Thu1);
        timeTable.add(Fri1);
        timeTable.add(Sat1);
        timeTable.add(Sun1);
        timeTable.add(Mon2);
        timeTable.add(Tue2);
        timeTable.add(Wed2);
        timeTable.add(Thu2);
        timeTable.add(Fri2);
        timeTable.add(jPanel111);
        timeTable.add(jPanel102);
        timeTable.add(Mon3);
        timeTable.add(Tue3);
        timeTable.add(Wed3);
        timeTable.add(Thu3);
        timeTable.add(Fri3);
        timeTable.add(jPanel112);
        timeTable.add(jPanel103);
        timeTable.add(Mon4);
        timeTable.add(Tue4);
        timeTable.add(Wed4);
        timeTable.add(Thu4);
        timeTable.add(Fri4);
        timeTable.add(jPanel113);
        timeTable.add(jPanel104);
        timeTable.add(M5);
        timeTable.add(Tue5);
        timeTable.add(Wed5);
        timeTable.add(Thu5);
        timeTable.add(Fri5);
        timeTable.add(jPanel114);
        timeTable.add(jPanel105);
        timeTable.add(M6);
        timeTable.add(Tue6);
        timeTable.add(Wed6);
        timeTable.add(Thu6);
        timeTable.add(Fri6);
        timeTable.add(jPanel97);
        timeTable.add(jPanel106);
        timeTable.add(M7);
        timeTable.add(Tue7);
        timeTable.add(Wed7);
        timeTable.add(Thu7);
        timeTable.add(Fri7);
        timeTable.add(jPanel98);
        timeTable.add(jPanel107);
        timeTable.add(M8);
        timeTable.add(Tue8);
        timeTable.add(Wed8);
        timeTable.add(Thu8);
        timeTable.add(Fri8);
        timeTable.add(jPanel99);
        timeTable.add(jPanel108);
        timeTable.add(M9);
        timeTable.add(Tue9);
        timeTable.add(Wed9);
        timeTable.add(Thu9);
        timeTable.add(Fri9);
        timeTable.add(jPanel100);
        timeTable.add(jPanel109);
    }
    
    public int getDay(Reservation reservation) { //요일 구하기
        //dateR은 "yyyy/mm/dd" 형식으로 된 string type으로 받는다.
        int yNum = reservation.dateR.indexOf("/"); //4
        int mNum = reservation.dateR.indexOf("/", yNum + 1);//7

        int year = Integer.parseInt(reservation.dateR.substring(0, yNum));    //년
        int month = Integer.parseInt(reservation.dateR.substring(yNum + 1, mNum));   //월
        int day = Integer.parseInt(reservation.dateR.substring(mNum + 1));    //일

        LocalDate date = LocalDate.of(year, month, day);   //date에 날짜 저장
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int dayOfWeekNumber = dayOfWeek.getValue(); //날짜에 대한 요일을 숫자형식으로
        return dayOfWeekNumber;                   //1: 월, 2: 화, 3: 수, ....
    }
    ArrayList<JRadioButton> afterseatS = new ArrayList<>();  // 5시 이후 개인 좌석

    public void setafterSeatS() {
        afterseatS.add(seat1);
        afterseatS.add(seat2);
        afterseatS.add(seat3);
        afterseatS.add(seat4);
        afterseatS.add(seat5);
        afterseatS.add(seat6);
        afterseatS.add(seat7);
        afterseatS.add(seat8);
        afterseatS.add(seat9);
        afterseatS.add(seat10);
        afterseatS.add(seat11);
        afterseatS.add(seat12);
        afterseatS.add(seat13);
        afterseatS.add(seat14);
        afterseatS.add(seat15);
        afterseatS.add(seat16);
        afterseatS.add(seat17);
        afterseatS.add(seat18);
        afterseatS.add(seat19);
        afterseatS.add(seat20);
        afterseatS.add(seat21);
        afterseatS.add(seat22);
        afterseatS.add(seat23);
        afterseatS.add(seat24);
        afterseatS.add(seat25);
        afterseatS.add(seat26);
        afterseatS.add(seat27);
        afterseatS.add(seat28);
        afterseatS.add(seat29);
        afterseatS.add(seat30);
    }

    ArrayList<JRadioButton> afterseatT = new ArrayList<>();  // 5시 이후 팀 좌석

    public void setafterSeatT() {
        afterseatT.add(seat91);
        afterseatT.add(seat92);
        afterseatT.add(seat93);
        afterseatT.add(seat94);
        afterseatT.add(seat95);
        afterseatT.add(seat96);
        afterseatT.add(seat97);
        afterseatT.add(seat98);
        afterseatT.add(seat99);
        afterseatT.add(seat100);
        afterseatT.add(seat101);
        afterseatT.add(seat102);
        afterseatT.add(seat103);
        afterseatT.add(seat104);
        afterseatT.add(seat105);
        afterseatT.add(seat106);
        afterseatT.add(seat107);
        afterseatT.add(seat108);
        afterseatT.add(seat109);
        afterseatT.add(seat110);
        afterseatT.add(seat111);
        afterseatT.add(seat112);
        afterseatT.add(seat113);
        afterseatT.add(seat114);
        afterseatT.add(seat115);
        afterseatT.add(seat116);
        afterseatT.add(seat117);
        afterseatT.add(seat118);
        afterseatT.add(seat119);
        afterseatT.add(seat120);
    }

    public void connect() {  //DB연결 함수
        try {
            //JDBC드라이버 로딩
            Class.forName("com.mysql.cj.jdbc.Driver");
            //디비 연결 용 변수
            String jdbcDriver = "jdbc:mysql://211.213.95.123:3360/labmanagement?serverTimeZone=UTC";
            String dbId = "20203128"; //MySQL 접속 아이디("20203132"도 가능)
            String dbPw = "20203128"; //접속 비밀번호(아이디를 20203132로 작성시, 비밀번호도 아이디와 같도록
            conn = DriverManager.getConnection(jdbcDriver, dbId, dbPw);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public StudentMain() {
        initComponents();
        TitlePanel.setVisible(true);
        textField1.setText(loginName);
        mainPanel.setVisible(true);

        Reser_menuPanel.setVisible(false);
        Lab_menuPanel.setVisible(false);
        UserInfo_menuPanel.setVisible(false);

        ReserPanel.setVisible(false);
        beforeReser.setVisible(false);
        afterReser.setVisible(false);
        CheckPanel.setVisible(false);
        CanclePanel.setVisible(false);
        check.setVisible(false);
        afterSeatStatePanel.setVisible(false);
        beforeSeatStatePanel.setVisible(false);
        OverReser.setVisible(false);
        OverLabCheckPanel.setVisible(false);
        overSeatStatePanel.setVisible(false);
        aPanel.setVisible(false);
        beforeTTPanel.setVisible(false);

        LabCheckPanel.setVisible(false);
        LabTTCheckPanel.setVisible(false);
        TT.setVisible(false);
        LabNoticePanel.setVisible(false);
        LabStatusPanel.setVisible(false);
        LabStatus.setVisible(false);

        UserChangePanel.setVisible(false);
        UserDeletePanel.setVisible(false);

        setSeat();
        setSeatInquire();
        setafterSeatS();
        setafterSeatT();
    }

    // 화면에 띄우는 패널들 초기화하는 함수
    public void reset() {
        TitlePanel.setVisible(true);
        mainPanel.setVisible(false);

        Reser_menuPanel.setVisible(false);
        Lab_menuPanel.setVisible(false);
        UserInfo_menuPanel.setVisible(false);

        ReserPanel.setVisible(false);
        beforeReser.setVisible(false);
        afterReser.setVisible(false);
        CheckPanel.setVisible(false);
        CanclePanel.setVisible(false);
        check.setVisible(false);
        afterSeatStatePanel.setVisible(false);
        beforeSeatStatePanel.setVisible(false);
        OverReser.setVisible(false);
        OverLabCheckPanel.setVisible(false);
        overSeatStatePanel.setVisible(false);
        aPanel.setVisible(false);
        beforeTTPanel.setVisible(false);

        LabCheckPanel.setVisible(false);
        LabTTCheckPanel.setVisible(false);
        TT.setVisible(false);
        LabNoticePanel.setVisible(false);
        LabStatusPanel.setVisible(false);
        LabStatus.setVisible(false);

        UserChangePanel.setVisible(false);
        UserDeletePanel.setVisible(false);

        menuReser.setBackground(Color.WHITE);
        menuCheck.setBackground(Color.WHITE);
        menuCancle.setBackground(Color.WHITE);
        menuLabCheck.setBackground(Color.WHITE);
        menuTimeTable.setBackground(Color.WHITE);
        menuLabNotice.setBackground(Color.WHITE);
        menuUserChange.setBackground(Color.WHITE);
        menuUserDelete.setBackground(Color.WHITE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        TitlePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textField1 = new java.awt.TextField();
        label1 = new java.awt.Label();
        mainPanel = new javax.swing.JPanel();
        labReserButt = new javax.swing.JButton();
        userInformationButt = new javax.swing.JButton();
        labButt = new javax.swing.JButton();
        manageButt = new javax.swing.JButton();
        Reser_menuPanel = new javax.swing.JPanel();
        menuCheck = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        menuCancle = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        menuReser = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ReserPanel = new javax.swing.JPanel();
        beforeReserRadio = new javax.swing.JRadioButton();
        afterReserRadio = new javax.swing.JRadioButton();
        reserNextButt = new javax.swing.JButton();
        beforeReser = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        afterReser = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        checkButt = new javax.swing.JButton();
        startTimeR = new javax.swing.JComboBox<>();
        endTimeR = new javax.swing.JComboBox<>();
        DATE_Text = new javax.swing.JTextField();
        CheckPanel = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        startButt = new javax.swing.JButton();
        endButt = new javax.swing.JButton();
        continueButt = new javax.swing.JButton();
        continueCombo = new javax.swing.JComboBox<>();
        jTextField14 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        CanclePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        MyReserCancleButt = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        MyReserCheckButt = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        check = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        AfterCheckButt = new javax.swing.JButton();
        afterSeatStatePanel = new javax.swing.JPanel();
        seat1 = new javax.swing.JRadioButton();
        seat2 = new javax.swing.JRadioButton();
        seat3 = new javax.swing.JRadioButton();
        seat4 = new javax.swing.JRadioButton();
        seat5 = new javax.swing.JRadioButton();
        seat6 = new javax.swing.JRadioButton();
        seat7 = new javax.swing.JRadioButton();
        seat8 = new javax.swing.JRadioButton();
        seat9 = new javax.swing.JRadioButton();
        seat10 = new javax.swing.JRadioButton();
        seat11 = new javax.swing.JRadioButton();
        seat12 = new javax.swing.JRadioButton();
        seat13 = new javax.swing.JRadioButton();
        seat14 = new javax.swing.JRadioButton();
        seat15 = new javax.swing.JRadioButton();
        seat16 = new javax.swing.JRadioButton();
        seat17 = new javax.swing.JRadioButton();
        seat18 = new javax.swing.JRadioButton();
        seat19 = new javax.swing.JRadioButton();
        seat20 = new javax.swing.JRadioButton();
        seat21 = new javax.swing.JRadioButton();
        seat22 = new javax.swing.JRadioButton();
        seat23 = new javax.swing.JRadioButton();
        seat24 = new javax.swing.JRadioButton();
        seat25 = new javax.swing.JRadioButton();
        seat26 = new javax.swing.JRadioButton();
        seat27 = new javax.swing.JRadioButton();
        seat28 = new javax.swing.JRadioButton();
        seat29 = new javax.swing.JRadioButton();
        seat30 = new javax.swing.JRadioButton();
        AfterReserButt = new javax.swing.JButton();
        OverReser = new javax.swing.JPanel();
        teamRadio = new javax.swing.JRadioButton();
        soloRadio = new javax.swing.JRadioButton();
        teamCheckButt = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        OverLabCheckPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox<>();
        overSeatCheckButt = new javax.swing.JButton();
        overSeatStatePanel = new javax.swing.JPanel();
        seat91 = new javax.swing.JRadioButton();
        seat92 = new javax.swing.JRadioButton();
        seat93 = new javax.swing.JRadioButton();
        seat94 = new javax.swing.JRadioButton();
        seat95 = new javax.swing.JRadioButton();
        seat96 = new javax.swing.JRadioButton();
        seat97 = new javax.swing.JRadioButton();
        seat98 = new javax.swing.JRadioButton();
        seat99 = new javax.swing.JRadioButton();
        seat100 = new javax.swing.JRadioButton();
        seat101 = new javax.swing.JRadioButton();
        seat102 = new javax.swing.JRadioButton();
        seat103 = new javax.swing.JRadioButton();
        seat104 = new javax.swing.JRadioButton();
        seat105 = new javax.swing.JRadioButton();
        seat106 = new javax.swing.JRadioButton();
        seat107 = new javax.swing.JRadioButton();
        seat108 = new javax.swing.JRadioButton();
        seat109 = new javax.swing.JRadioButton();
        seat110 = new javax.swing.JRadioButton();
        seat111 = new javax.swing.JRadioButton();
        seat112 = new javax.swing.JRadioButton();
        seat113 = new javax.swing.JRadioButton();
        seat114 = new javax.swing.JRadioButton();
        seat115 = new javax.swing.JRadioButton();
        seat116 = new javax.swing.JRadioButton();
        seat117 = new javax.swing.JRadioButton();
        seat118 = new javax.swing.JRadioButton();
        seat119 = new javax.swing.JRadioButton();
        seat120 = new javax.swing.JRadioButton();
        overReserButt = new javax.swing.JButton();
        Lab_menuPanel = new javax.swing.JPanel();
        menuLabNotice = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        menuTimeTable = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        menuLabCheck = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        LabNoticePanel = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        LabTTCheckPanel = new javax.swing.JPanel();
        jComboBox7 = new javax.swing.JComboBox<>();
        TTCheckButt = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        TT = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        LabCheckPanel = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        checkButt1 = new javax.swing.JButton();
        startTimeR1 = new javax.swing.JComboBox<>();
        endTimeR1 = new javax.swing.JComboBox<>();
        YYYY_Text1 = new javax.swing.JTextField();
        MM_Text1 = new javax.swing.JTextField();
        DD_Text1 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        LabStatusPanel = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        LabComboBox = new javax.swing.JComboBox<>();
        jButton10 = new javax.swing.JButton();
        LabStatus = new javax.swing.JPanel();
        seat61 = new javax.swing.JRadioButton();
        seat62 = new javax.swing.JRadioButton();
        seat63 = new javax.swing.JRadioButton();
        seat64 = new javax.swing.JRadioButton();
        seat65 = new javax.swing.JRadioButton();
        seat66 = new javax.swing.JRadioButton();
        seat67 = new javax.swing.JRadioButton();
        seat68 = new javax.swing.JRadioButton();
        seat69 = new javax.swing.JRadioButton();
        seat70 = new javax.swing.JRadioButton();
        seat71 = new javax.swing.JRadioButton();
        seat72 = new javax.swing.JRadioButton();
        seat73 = new javax.swing.JRadioButton();
        seat74 = new javax.swing.JRadioButton();
        seat75 = new javax.swing.JRadioButton();
        seat76 = new javax.swing.JRadioButton();
        seat77 = new javax.swing.JRadioButton();
        seat78 = new javax.swing.JRadioButton();
        seat79 = new javax.swing.JRadioButton();
        seat80 = new javax.swing.JRadioButton();
        seat81 = new javax.swing.JRadioButton();
        seat82 = new javax.swing.JRadioButton();
        seat83 = new javax.swing.JRadioButton();
        seat84 = new javax.swing.JRadioButton();
        seat85 = new javax.swing.JRadioButton();
        seat86 = new javax.swing.JRadioButton();
        seat87 = new javax.swing.JRadioButton();
        seat88 = new javax.swing.JRadioButton();
        seat89 = new javax.swing.JRadioButton();
        seat90 = new javax.swing.JRadioButton();
        UserInfo_menuPanel = new javax.swing.JPanel();
        menuUserDelete = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        menuUserChange = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        UserChangePanel = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        UserChangeButt = new javax.swing.JButton();
        UserDeletePanel = new javax.swing.JPanel();
        UserDeleteButt = new javax.swing.JButton();
        jTextField9 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        aPanel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jComboBox9 = new javax.swing.JComboBox<>();
        beforeCheckButt = new javax.swing.JButton();
        beforeTTPanel = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        Mon1 = new javax.swing.JPanel();
        Mon2 = new javax.swing.JPanel();
        Mon3 = new javax.swing.JPanel();
        Mon4 = new javax.swing.JPanel();
        M5 = new javax.swing.JPanel();
        M6 = new javax.swing.JPanel();
        M7 = new javax.swing.JPanel();
        M8 = new javax.swing.JPanel();
        M9 = new javax.swing.JPanel();
        Tue2 = new javax.swing.JPanel();
        Tue3 = new javax.swing.JPanel();
        Tue4 = new javax.swing.JPanel();
        Tue5 = new javax.swing.JPanel();
        Tue6 = new javax.swing.JPanel();
        Tue7 = new javax.swing.JPanel();
        Tue8 = new javax.swing.JPanel();
        Tue9 = new javax.swing.JPanel();
        Tue1 = new javax.swing.JPanel();
        Wed2 = new javax.swing.JPanel();
        Wed3 = new javax.swing.JPanel();
        Wed4 = new javax.swing.JPanel();
        Wed5 = new javax.swing.JPanel();
        Wed6 = new javax.swing.JPanel();
        Wed7 = new javax.swing.JPanel();
        Wed8 = new javax.swing.JPanel();
        Wed9 = new javax.swing.JPanel();
        Wed1 = new javax.swing.JPanel();
        Thu2 = new javax.swing.JPanel();
        Thu3 = new javax.swing.JPanel();
        Thu4 = new javax.swing.JPanel();
        Thu5 = new javax.swing.JPanel();
        Thu6 = new javax.swing.JPanel();
        Thu7 = new javax.swing.JPanel();
        Thu8 = new javax.swing.JPanel();
        Thu9 = new javax.swing.JPanel();
        Thu1 = new javax.swing.JPanel();
        Fri2 = new javax.swing.JPanel();
        Fri3 = new javax.swing.JPanel();
        Fri4 = new javax.swing.JPanel();
        Fri5 = new javax.swing.JPanel();
        Fri6 = new javax.swing.JPanel();
        Fri7 = new javax.swing.JPanel();
        Fri8 = new javax.swing.JPanel();
        Fri9 = new javax.swing.JPanel();
        Fri1 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jPanel97 = new javax.swing.JPanel();
        jPanel98 = new javax.swing.JPanel();
        jPanel99 = new javax.swing.JPanel();
        jPanel100 = new javax.swing.JPanel();
        Sat1 = new javax.swing.JPanel();
        jPanel102 = new javax.swing.JPanel();
        jPanel103 = new javax.swing.JPanel();
        jPanel104 = new javax.swing.JPanel();
        jPanel105 = new javax.swing.JPanel();
        jPanel106 = new javax.swing.JPanel();
        jPanel107 = new javax.swing.JPanel();
        jPanel108 = new javax.swing.JPanel();
        jPanel109 = new javax.swing.JPanel();
        Sun1 = new javax.swing.JPanel();
        jPanel111 = new javax.swing.JPanel();
        jPanel112 = new javax.swing.JPanel();
        jPanel113 = new javax.swing.JPanel();
        jPanel114 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        beforeSeatCheckButt = new javax.swing.JButton();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        beforeSeatStatePanel = new javax.swing.JPanel();
        seat31 = new javax.swing.JRadioButton();
        seat32 = new javax.swing.JRadioButton();
        seat33 = new javax.swing.JRadioButton();
        seat34 = new javax.swing.JRadioButton();
        seat35 = new javax.swing.JRadioButton();
        seat36 = new javax.swing.JRadioButton();
        seat37 = new javax.swing.JRadioButton();
        seat38 = new javax.swing.JRadioButton();
        seat39 = new javax.swing.JRadioButton();
        seat40 = new javax.swing.JRadioButton();
        seat41 = new javax.swing.JRadioButton();
        seat42 = new javax.swing.JRadioButton();
        seat43 = new javax.swing.JRadioButton();
        seat44 = new javax.swing.JRadioButton();
        seat45 = new javax.swing.JRadioButton();
        seat46 = new javax.swing.JRadioButton();
        seat47 = new javax.swing.JRadioButton();
        seat48 = new javax.swing.JRadioButton();
        seat49 = new javax.swing.JRadioButton();
        seat50 = new javax.swing.JRadioButton();
        seat51 = new javax.swing.JRadioButton();
        seat52 = new javax.swing.JRadioButton();
        seat53 = new javax.swing.JRadioButton();
        seat54 = new javax.swing.JRadioButton();
        seat55 = new javax.swing.JRadioButton();
        seat56 = new javax.swing.JRadioButton();
        seat57 = new javax.swing.JRadioButton();
        seat58 = new javax.swing.JRadioButton();
        seat59 = new javax.swing.JRadioButton();
        seat60 = new javax.swing.JRadioButton();
        BeforeReserButt = new javax.swing.JButton();
        resetRadio = new javax.swing.JRadioButton();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1110, 725));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TitlePanel.setBackground(new java.awt.Color(246, 226, 231));
        TitlePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TitlePanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TitlePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TitlePanelMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("굴림", 0, 30)); // NOI18N
        jLabel1.setText("컴퓨터소프트웨어공학과 실습실 예약 시스템");

        textField1.setBackground(new java.awt.Color(255, 255, 255));
        textField1.setEditable(false);
        textField1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        textField1.setText("홍길동");

        label1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label1.setText("님");

        javax.swing.GroupLayout TitlePanelLayout = new javax.swing.GroupLayout(TitlePanel);
        TitlePanel.setLayout(TitlePanelLayout);
        TitlePanelLayout.setHorizontalGroup(
            TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitlePanelLayout.createSequentialGroup()
                .addContainerGap(239, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(187, 187, 187)
                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        TitlePanelLayout.setVerticalGroup(
            TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitlePanelLayout.createSequentialGroup()
                .addGroup(TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TitlePanelLayout.createSequentialGroup()
                        .addContainerGap(71, Short.MAX_VALUE)
                        .addGroup(TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(TitlePanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(TitlePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 110));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        labReserButt.setBackground(new java.awt.Color(255, 255, 255));
        labReserButt.setForeground(new java.awt.Color(255, 255, 255));
        labReserButt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ButtPicture/실습실예약버튼.PNG"))); // NOI18N
        labReserButt.setBorderPainted(false);
        labReserButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labReserButtActionPerformed(evt);
            }
        });

        userInformationButt.setBackground(new java.awt.Color(255, 255, 255));
        userInformationButt.setForeground(new java.awt.Color(255, 255, 255));
        userInformationButt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ButtPicture/회원정보버튼.PNG"))); // NOI18N
        userInformationButt.setBorderPainted(false);
        userInformationButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userInformationButtActionPerformed(evt);
            }
        });

        labButt.setBackground(new java.awt.Color(255, 255, 255));
        labButt.setForeground(new java.awt.Color(255, 255, 255));
        labButt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ButtPicture/실습실버튼.PNG"))); // NOI18N
        labButt.setBorderPainted(false);
        labButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labButtActionPerformed(evt);
            }
        });

        manageButt.setBackground(new java.awt.Color(255, 255, 255));
        manageButt.setForeground(new java.awt.Color(255, 255, 255));
        manageButt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ButtPicture/신고관리버튼.PNG"))); // NOI18N
        manageButt.setBorderPainted(false);
        manageButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(userInformationButt)
                .addGap(46, 46, 46)
                .addComponent(labReserButt)
                .addGap(43, 43, 43)
                .addComponent(labButt)
                .addGap(40, 40, 40)
                .addComponent(manageButt)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(userInformationButt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                    .addComponent(manageButt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labReserButt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labButt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(204, Short.MAX_VALUE))
        );

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1110, 620));

        Reser_menuPanel.setBackground(new java.awt.Color(255, 255, 255));
        Reser_menuPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        menuCheck.setBackground(new java.awt.Color(255, 255, 255));
        menuCheck.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuCheck.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuCheck.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        menuCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuCheckMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel4.setText("실습실 이용 및 상황 조회");

        javax.swing.GroupLayout menuCheckLayout = new javax.swing.GroupLayout(menuCheck);
        menuCheck.setLayout(menuCheckLayout);
        menuCheckLayout.setHorizontalGroup(
            menuCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuCheckLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuCheckLayout.setVerticalGroup(
            menuCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuCheckLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        menuCancle.setBackground(new java.awt.Color(255, 255, 255));
        menuCancle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuCancle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuCancle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuCancleMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel5.setText("본인 예약 현황 ");

        jLabel14.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel14.setText("조회 및 취소");

        javax.swing.GroupLayout menuCancleLayout = new javax.swing.GroupLayout(menuCancle);
        menuCancle.setLayout(menuCancleLayout);
        menuCancleLayout.setHorizontalGroup(
            menuCancleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuCancleLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(menuCancleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuCancleLayout.setVerticalGroup(
            menuCancleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuCancleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addContainerGap())
        );

        menuReser.setBackground(new java.awt.Color(255, 255, 255));
        menuReser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuReser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuReser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuReserMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel6.setText("예약하기");

        javax.swing.GroupLayout menuReserLayout = new javax.swing.GroupLayout(menuReser);
        menuReser.setLayout(menuReserLayout);
        menuReserLayout.setHorizontalGroup(
            menuReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuReserLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuReserLayout.setVerticalGroup(
            menuReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuReserLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(20, 20, 20))
        );

        jLabel3.setFont(new java.awt.Font("맑은 고딕", 0, 36)); // NOI18N
        jLabel3.setText("예약");

        javax.swing.GroupLayout Reser_menuPanelLayout = new javax.swing.GroupLayout(Reser_menuPanel);
        Reser_menuPanel.setLayout(Reser_menuPanelLayout);
        Reser_menuPanelLayout.setHorizontalGroup(
            Reser_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Reser_menuPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(menuReser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuCancle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Reser_menuPanelLayout.setVerticalGroup(
            Reser_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Reser_menuPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel3)
                .addGap(34, 34, 34)
                .addComponent(menuReser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(menuCancle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(menuCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(291, Short.MAX_VALUE))
        );

        getContentPane().add(Reser_menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 150, 620));

        ReserPanel.setBackground(new java.awt.Color(254, 255, 233));

        buttonGroup1.add(beforeReserRadio);
        beforeReserRadio.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        beforeReserRadio.setText("5시 이전 실습실 사용 예약");

        buttonGroup1.add(afterReserRadio);
        afterReserRadio.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        afterReserRadio.setText("5시 이후 실습실 사용 예약");

        reserNextButt.setBackground(new java.awt.Color(255, 255, 255));
        reserNextButt.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        reserNextButt.setText("NEXT");
        reserNextButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reserNextButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ReserPanelLayout = new javax.swing.GroupLayout(ReserPanel);
        ReserPanel.setLayout(ReserPanelLayout);
        ReserPanelLayout.setHorizontalGroup(
            ReserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReserPanelLayout.createSequentialGroup()
                .addContainerGap(253, Short.MAX_VALUE)
                .addGroup(ReserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(beforeReserRadio)
                    .addComponent(afterReserRadio))
                .addGap(83, 83, 83)
                .addComponent(reserNextButt, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(204, 204, 204))
        );
        ReserPanelLayout.setVerticalGroup(
            ReserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReserPanelLayout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addGroup(ReserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ReserPanelLayout.createSequentialGroup()
                        .addComponent(beforeReserRadio)
                        .addGap(65, 65, 65)
                        .addComponent(afterReserRadio))
                    .addGroup(ReserPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(reserNextButt, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(275, Short.MAX_VALUE))
        );

        getContentPane().add(ReserPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        jLabel7.setFont(new java.awt.Font("굴림", 0, 24)); // NOI18N
        jLabel7.setText("5시 이전 실습실 사용 예약");

        jComboBox3.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "915", "916", "918", "911" }));

        jLabel18.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        jLabel18.setText("강의실");

        jLabel19.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        jLabel19.setText("날짜");

        jLabel20.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        jLabel20.setText("시작시간");

        jLabel21.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        jLabel21.setText("종료시간");

        jComboBox4.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00" }));

        jComboBox5.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00" }));

        jTextField1.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        jTextField1.setText("2022/10/10");

        jButton3.setText("좌석조회");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout beforeReserLayout = new javax.swing.GroupLayout(beforeReser);
        beforeReser.setLayout(beforeReserLayout);
        beforeReserLayout.setHorizontalGroup(
            beforeReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, beforeReserLayout.createSequentialGroup()
                .addGroup(beforeReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(beforeReserLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7))
                    .addGroup(beforeReserLayout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addGroup(beforeReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20))
                        .addGap(18, 18, 18)
                        .addGroup(beforeReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addGroup(beforeReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addGroup(beforeReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(115, 115, 115)
                .addComponent(jButton3)
                .addGap(94, 94, 94))
        );
        beforeReserLayout.setVerticalGroup(
            beforeReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beforeReserLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel7)
                .addGap(31, 31, 31)
                .addGroup(beforeReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(beforeReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap(455, Short.MAX_VALUE))
        );

        getContentPane().add(beforeReser, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        afterReser.setBackground(new java.awt.Color(254, 255, 233));
        afterReser.setPreferredSize(new java.awt.Dimension(914, 507));

        jLabel2.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        jLabel2.setText("5시 이후 실습실 사용 예약");

        jLabel10.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel10.setText("시작시간");

        jLabel11.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel11.setText("날짜");

        jLabel12.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel12.setText("종료시간");

        checkButt.setBackground(new java.awt.Color(255, 255, 255));
        checkButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        checkButt.setText("조회");
        checkButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkButtActionPerformed(evt);
            }
        });

        startTimeR.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        startTimeR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00" }));

        endTimeR.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        endTimeR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00" }));

        DATE_Text.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        DATE_Text.setText("2022/11/10");

        javax.swing.GroupLayout afterReserLayout = new javax.swing.GroupLayout(afterReser);
        afterReser.setLayout(afterReserLayout);
        afterReserLayout.setHorizontalGroup(
            afterReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, afterReserLayout.createSequentialGroup()
                .addGap(332, 332, 332)
                .addComponent(jLabel2)
                .addContainerGap(297, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, afterReserLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(afterReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(37, 37, 37)
                .addGroup(afterReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DATE_Text)
                    .addComponent(endTimeR, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startTimeR, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(216, 216, 216)
                .addComponent(checkButt, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(192, 192, 192))
        );
        afterReserLayout.setVerticalGroup(
            afterReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(afterReserLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabel2)
                .addGap(93, 93, 93)
                .addGroup(afterReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(afterReserLayout.createSequentialGroup()
                        .addGroup(afterReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(DATE_Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addComponent(checkButt, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(afterReserLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(afterReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(startTimeR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(afterReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(endTimeR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(172, Short.MAX_VALUE))
        );

        getContentPane().add(afterReser, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        CheckPanel.setBackground(new java.awt.Color(254, 255, 233));

        jLabel46.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel46.setText("실습실 번호 : ");

        jLabel47.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel47.setText("좌석 번호 : ");

        jLabel48.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel48.setText("시작시간 : ");

        jLabel49.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel49.setText("종료시간 : ");

        jLabel50.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel50.setText("연장가능시간 : ");

        jTextField10.setEditable(false);
        jTextField10.setBackground(new java.awt.Color(255, 255, 255));
        jTextField10.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N

        jTextField11.setEditable(false);
        jTextField11.setBackground(new java.awt.Color(255, 255, 255));
        jTextField11.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N

        jTextField12.setEditable(false);
        jTextField12.setBackground(new java.awt.Color(255, 255, 255));
        jTextField12.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N

        jTextField13.setEditable(false);
        jTextField13.setBackground(new java.awt.Color(255, 255, 255));
        jTextField13.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N

        startButt.setBackground(new java.awt.Color(255, 255, 255));
        startButt.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        startButt.setText("사용 시작");
        startButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtActionPerformed(evt);
            }
        });

        endButt.setBackground(new java.awt.Color(255, 255, 255));
        endButt.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        endButt.setText("퇴실");
        endButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endButtActionPerformed(evt);
            }
        });

        continueButt.setBackground(new java.awt.Color(255, 255, 255));
        continueButt.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        continueButt.setText("연장");
        continueButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButtActionPerformed(evt);
            }
        });

        continueCombo.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N

        jTextField14.setEditable(false);
        jTextField14.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N

        jLabel63.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel63.setText("실습실 관리 권한 : ");

        javax.swing.GroupLayout CheckPanelLayout = new javax.swing.GroupLayout(CheckPanel);
        CheckPanel.setLayout(CheckPanelLayout);
        CheckPanelLayout.setHorizontalGroup(
            CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CheckPanelLayout.createSequentialGroup()
                .addGap(334, 334, 334)
                .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CheckPanelLayout.createSequentialGroup()
                        .addComponent(jLabel63)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CheckPanelLayout.createSequentialGroup()
                        .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(CheckPanelLayout.createSequentialGroup()
                                .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                    .addComponent(jTextField11)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, CheckPanelLayout.createSequentialGroup()
                                .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField13, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                    .addComponent(jTextField12)
                                    .addComponent(continueCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(startButt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(endButt, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(continueButt, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(213, Short.MAX_VALUE))
        );
        CheckPanelLayout.setVerticalGroup(
            CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CheckPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(continueCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(continueButt))
                .addGap(36, 36, 36)
                .addComponent(startButt)
                .addGap(18, 18, 18)
                .addComponent(endButt)
                .addContainerGap(164, Short.MAX_VALUE))
        );

        getContentPane().add(CheckPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        CanclePanel.setBackground(new java.awt.Color(254, 255, 233));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "날짜", "실습실", "좌석", "시간시간", "종료시간", "예약승인여부", "관리권한여부"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionBackground(new java.awt.Color(246, 226, 231));
        jScrollPane1.setViewportView(jTable1);

        MyReserCancleButt.setBackground(new java.awt.Color(255, 255, 255));
        MyReserCancleButt.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        MyReserCancleButt.setText("예약취소");
        MyReserCancleButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MyReserCancleButtActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        jLabel13.setText("실습실 : ");

        jLabel17.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        jLabel17.setText("날짜 : ");

        jTextField2.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N

        MyReserCheckButt.setBackground(new java.awt.Color(255, 255, 255));
        MyReserCheckButt.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        MyReserCheckButt.setText("조회");
        MyReserCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MyReserCheckButtActionPerformed(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "915", "916", "918", "911" }));

        javax.swing.GroupLayout CanclePanelLayout = new javax.swing.GroupLayout(CanclePanel);
        CanclePanel.setLayout(CanclePanelLayout);
        CanclePanelLayout.setHorizontalGroup(
            CanclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CanclePanelLayout.createSequentialGroup()
                .addGap(239, 239, 239)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(MyReserCheckButt)
                .addContainerGap(228, Short.MAX_VALUE))
            .addGroup(CanclePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CanclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(MyReserCancleButt)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 871, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CanclePanelLayout.setVerticalGroup(
            CanclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CanclePanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(CanclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MyReserCheckButt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(MyReserCancleButt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        getContentPane().add(CanclePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        check.setBackground(new java.awt.Color(254, 255, 233));

        jLabel9.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel9.setText("강의실");

        jComboBox1.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N

        AfterCheckButt.setBackground(new java.awt.Color(255, 255, 255));
        AfterCheckButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        AfterCheckButt.setText("확인");
        AfterCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AfterCheckButtActionPerformed(evt);
            }
        });

        afterSeatStatePanel.setBackground(new java.awt.Color(254, 255, 233));
        afterSeatStatePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        buttonGroup2.add(seat1);
        seat1.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat1.setText("1");

        buttonGroup2.add(seat2);
        seat2.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat2.setText("2");

        buttonGroup2.add(seat3);
        seat3.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat3.setText("3");

        buttonGroup2.add(seat4);
        seat4.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat4.setText("4");

        buttonGroup2.add(seat5);
        seat5.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat5.setText("5");

        buttonGroup2.add(seat6);
        seat6.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat6.setText("6");

        buttonGroup2.add(seat7);
        seat7.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat7.setText("7");

        buttonGroup2.add(seat8);
        seat8.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat8.setText("8");

        buttonGroup2.add(seat9);
        seat9.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat9.setText("9");

        buttonGroup2.add(seat10);
        seat10.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat10.setText("10");

        buttonGroup2.add(seat11);
        seat11.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat11.setText("11");

        buttonGroup2.add(seat12);
        seat12.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat12.setText("12");

        buttonGroup2.add(seat13);
        seat13.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat13.setText("13");

        buttonGroup2.add(seat14);
        seat14.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat14.setText("14");

        buttonGroup2.add(seat15);
        seat15.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat15.setText("15");

        buttonGroup2.add(seat16);
        seat16.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat16.setText("16");

        buttonGroup2.add(seat17);
        seat17.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat17.setText("17");

        buttonGroup2.add(seat18);
        seat18.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat18.setText("18");

        buttonGroup2.add(seat19);
        seat19.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat19.setText("19");

        buttonGroup2.add(seat20);
        seat20.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat20.setText("20");

        buttonGroup2.add(seat21);
        seat21.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat21.setText("21");

        buttonGroup2.add(seat22);
        seat22.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat22.setText("22");

        buttonGroup2.add(seat23);
        seat23.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat23.setText("23");

        buttonGroup2.add(seat24);
        seat24.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat24.setText("24");

        buttonGroup2.add(seat25);
        seat25.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat25.setText("25");

        buttonGroup2.add(seat26);
        seat26.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat26.setText("26");

        buttonGroup2.add(seat27);
        seat27.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat27.setText("27");

        buttonGroup2.add(seat28);
        seat28.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat28.setText("28");

        buttonGroup2.add(seat29);
        seat29.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat29.setText("29");

        buttonGroup2.add(seat30);
        seat30.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat30.setText("30");

        AfterReserButt.setBackground(new java.awt.Color(255, 255, 255));
        AfterReserButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        AfterReserButt.setText("예약");
        AfterReserButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AfterReserButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout afterSeatStatePanelLayout = new javax.swing.GroupLayout(afterSeatStatePanel);
        afterSeatStatePanel.setLayout(afterSeatStatePanelLayout);
        afterSeatStatePanelLayout.setHorizontalGroup(
            afterSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(afterSeatStatePanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(afterSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(afterSeatStatePanelLayout.createSequentialGroup()
                        .addComponent(seat1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(seat2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(seat3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(seat4, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addGap(170, 170, 170))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, afterSeatStatePanelLayout.createSequentialGroup()
                        .addGroup(afterSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(afterSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(seat18)
                                .addGroup(afterSeatStatePanelLayout.createSequentialGroup()
                                    .addComponent(seat9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(seat10)))
                            .addComponent(seat17)
                            .addGroup(afterSeatStatePanelLayout.createSequentialGroup()
                                .addComponent(seat25)
                                .addGap(18, 18, 18)
                                .addComponent(seat26)))
                        .addGap(18, 18, 18)
                        .addGroup(afterSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(afterSeatStatePanelLayout.createSequentialGroup()
                                .addComponent(seat19)
                                .addGap(18, 18, 18)
                                .addComponent(seat20))
                            .addComponent(seat27)
                            .addGroup(afterSeatStatePanelLayout.createSequentialGroup()
                                .addComponent(seat11)
                                .addGap(18, 18, 18)
                                .addComponent(seat12)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(afterSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(seat5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat28, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(seat13, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(seat21, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addGroup(afterSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(afterSeatStatePanelLayout.createSequentialGroup()
                        .addComponent(seat22)
                        .addGap(18, 18, 18)
                        .addComponent(seat23))
                    .addGroup(afterSeatStatePanelLayout.createSequentialGroup()
                        .addGroup(afterSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seat6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seat14))
                        .addGap(23, 23, 23)
                        .addGroup(afterSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(afterSeatStatePanelLayout.createSequentialGroup()
                                .addComponent(seat7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(seat8))
                            .addGroup(afterSeatStatePanelLayout.createSequentialGroup()
                                .addComponent(seat15)
                                .addGap(18, 18, 18)
                                .addComponent(seat16))
                            .addGroup(afterSeatStatePanelLayout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(seat24))))
                    .addGroup(afterSeatStatePanelLayout.createSequentialGroup()
                        .addComponent(seat29)
                        .addGap(18, 18, 18)
                        .addComponent(seat30)))
                .addGap(52, 52, 52))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, afterSeatStatePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AfterReserButt)
                .addContainerGap())
        );
        afterSeatStatePanelLayout.setVerticalGroup(
            afterSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(afterSeatStatePanelLayout.createSequentialGroup()
                .addGroup(afterSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(afterSeatStatePanelLayout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addGroup(afterSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(seat13)
                            .addComponent(seat14)
                            .addComponent(seat15)
                            .addComponent(seat16)))
                    .addGroup(afterSeatStatePanelLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(afterSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(seat1)
                            .addComponent(seat2)
                            .addComponent(seat3)
                            .addComponent(seat4)
                            .addComponent(seat5)
                            .addComponent(seat6)
                            .addComponent(seat7)
                            .addComponent(seat8))
                        .addGroup(afterSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(afterSeatStatePanelLayout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(afterSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(seat9)
                                    .addComponent(seat10)
                                    .addComponent(seat11)
                                    .addComponent(seat12)))
                            .addGroup(afterSeatStatePanelLayout.createSequentialGroup()
                                .addGap(139, 139, 139)
                                .addGroup(afterSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(seat19)
                                    .addComponent(seat20)
                                    .addComponent(seat18)
                                    .addComponent(seat17)
                                    .addComponent(seat21)
                                    .addComponent(seat22)
                                    .addComponent(seat23)
                                    .addComponent(seat24))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(afterSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seat26)
                    .addComponent(seat25)
                    .addComponent(seat27)
                    .addComponent(seat28)
                    .addComponent(seat29)
                    .addComponent(seat30))
                .addGap(33, 33, 33)
                .addComponent(AfterReserButt)
                .addContainerGap())
        );

        javax.swing.GroupLayout checkLayout = new javax.swing.GroupLayout(check);
        check.setLayout(checkLayout);
        checkLayout.setHorizontalGroup(
            checkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkLayout.createSequentialGroup()
                .addGap(302, 302, 302)
                .addComponent(jLabel9)
                .addGap(62, 62, 62)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(AfterCheckButt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkLayout.createSequentialGroup()
                .addContainerGap(124, Short.MAX_VALUE)
                .addComponent(afterSeatStatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );
        checkLayout.setVerticalGroup(
            checkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(checkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AfterCheckButt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(afterSeatStatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );

        getContentPane().add(check, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 108, 960, 620));

        OverReser.setBackground(new java.awt.Color(254, 255, 233));

        buttonGroup3.add(teamRadio);
        teamRadio.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        teamRadio.setText("조별학습");

        buttonGroup3.add(soloRadio);
        soloRadio.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        soloRadio.setText("개인학습");

        teamCheckButt.setBackground(new java.awt.Color(255, 255, 255));
        teamCheckButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        teamCheckButt.setText("확인");
        teamCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teamCheckButtActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel23.setText("- 팀 인원수 : ");

        jComboBox6.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4", "5", "6" }));

        OverLabCheckPanel.setBackground(new java.awt.Color(254, 255, 233));

        jLabel8.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel8.setText("사용 가능한 강의실");

        jComboBox8.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N

        overSeatCheckButt.setBackground(new java.awt.Color(255, 255, 255));
        overSeatCheckButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        overSeatCheckButt.setText("좌석 조회");
        overSeatCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overSeatCheckButtActionPerformed(evt);
            }
        });

        overSeatStatePanel.setBackground(new java.awt.Color(254, 255, 233));
        overSeatStatePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        buttonGroup2.add(seat91);
        seat91.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat91.setText("1");

        buttonGroup2.add(seat92);
        seat92.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat92.setText("2");

        buttonGroup2.add(seat93);
        seat93.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat93.setText("3");

        buttonGroup2.add(seat94);
        seat94.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat94.setText("4");

        buttonGroup2.add(seat95);
        seat95.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat95.setText("5");

        buttonGroup2.add(seat96);
        seat96.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat96.setText("6");

        buttonGroup2.add(seat97);
        seat97.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat97.setText("7");

        buttonGroup2.add(seat98);
        seat98.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat98.setText("8");

        buttonGroup2.add(seat99);
        seat99.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat99.setText("9");

        buttonGroup2.add(seat100);
        seat100.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat100.setText("10");

        buttonGroup2.add(seat101);
        seat101.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat101.setText("11");

        buttonGroup2.add(seat102);
        seat102.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat102.setText("12");

        buttonGroup2.add(seat103);
        seat103.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat103.setText("13");

        buttonGroup2.add(seat104);
        seat104.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat104.setText("14");

        buttonGroup2.add(seat105);
        seat105.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat105.setText("15");

        buttonGroup2.add(seat106);
        seat106.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat106.setText("16");

        buttonGroup2.add(seat107);
        seat107.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat107.setText("17");

        buttonGroup2.add(seat108);
        seat108.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat108.setText("18");

        buttonGroup2.add(seat109);
        seat109.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat109.setText("19");

        buttonGroup2.add(seat110);
        seat110.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat110.setText("20");

        buttonGroup2.add(seat111);
        seat111.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat111.setText("21");

        buttonGroup2.add(seat112);
        seat112.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat112.setText("22");

        buttonGroup2.add(seat113);
        seat113.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat113.setText("23");

        buttonGroup2.add(seat114);
        seat114.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat114.setText("24");

        buttonGroup2.add(seat115);
        seat115.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat115.setText("25");

        buttonGroup2.add(seat116);
        seat116.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat116.setText("26");

        buttonGroup2.add(seat117);
        seat117.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat117.setText("27");

        buttonGroup2.add(seat118);
        seat118.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat118.setText("28");

        buttonGroup2.add(seat119);
        seat119.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat119.setText("29");

        buttonGroup2.add(seat120);
        seat120.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat120.setText("30");

        overReserButt.setBackground(new java.awt.Color(255, 255, 255));
        overReserButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        overReserButt.setText("예약");
        overReserButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overReserButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout overSeatStatePanelLayout = new javax.swing.GroupLayout(overSeatStatePanel);
        overSeatStatePanel.setLayout(overSeatStatePanelLayout);
        overSeatStatePanelLayout.setHorizontalGroup(
            overSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overSeatStatePanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(overSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(overSeatStatePanelLayout.createSequentialGroup()
                        .addComponent(seat91, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(seat92, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(seat93, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(seat94, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addGap(155, 155, 155)
                        .addComponent(seat95, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(overSeatStatePanelLayout.createSequentialGroup()
                        .addGroup(overSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(overSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(seat108)
                                .addGroup(overSeatStatePanelLayout.createSequentialGroup()
                                    .addComponent(seat99, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(seat100)))
                            .addComponent(seat107)
                            .addGroup(overSeatStatePanelLayout.createSequentialGroup()
                                .addComponent(seat115)
                                .addGap(18, 18, 18)
                                .addComponent(seat116)))
                        .addGap(18, 18, 18)
                        .addGroup(overSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(overSeatStatePanelLayout.createSequentialGroup()
                                .addComponent(seat101)
                                .addGap(18, 18, 18)
                                .addComponent(seat102)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(seat103))
                            .addGroup(overSeatStatePanelLayout.createSequentialGroup()
                                .addComponent(seat109)
                                .addGap(18, 18, 18)
                                .addComponent(seat110)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(seat111))
                            .addGroup(overSeatStatePanelLayout.createSequentialGroup()
                                .addComponent(seat117)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(seat118)))))
                .addGroup(overSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(overSeatStatePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(overSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seat96, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seat104)))
                    .addGroup(overSeatStatePanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(overSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seat112)
                            .addComponent(seat119))))
                .addGap(25, 25, 25)
                .addGroup(overSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(seat120)
                    .addGroup(overSeatStatePanelLayout.createSequentialGroup()
                        .addComponent(seat113)
                        .addGap(18, 18, 18)
                        .addComponent(seat114))
                    .addGroup(overSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, overSeatStatePanelLayout.createSequentialGroup()
                            .addComponent(seat97, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(seat98))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, overSeatStatePanelLayout.createSequentialGroup()
                            .addComponent(seat105)
                            .addGap(18, 18, 18)
                            .addComponent(seat106))))
                .addGap(52, 52, 52))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, overSeatStatePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(overReserButt)
                .addContainerGap())
        );
        overSeatStatePanelLayout.setVerticalGroup(
            overSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overSeatStatePanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(overSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seat91)
                    .addComponent(seat92)
                    .addComponent(seat93)
                    .addComponent(seat94)
                    .addComponent(seat95)
                    .addComponent(seat96)
                    .addComponent(seat97)
                    .addComponent(seat98))
                .addGroup(overSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(overSeatStatePanelLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(overSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(seat99)
                            .addComponent(seat100)
                            .addComponent(seat101)
                            .addComponent(seat102)
                            .addComponent(seat103)
                            .addComponent(seat104)
                            .addComponent(seat105)
                            .addComponent(seat106)))
                    .addGroup(overSeatStatePanelLayout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addGroup(overSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(seat109)
                            .addComponent(seat110)
                            .addComponent(seat108)
                            .addComponent(seat107)
                            .addComponent(seat111)
                            .addComponent(seat112)
                            .addComponent(seat113)
                            .addComponent(seat114))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(overSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seat116)
                    .addComponent(seat115)
                    .addComponent(seat117)
                    .addComponent(seat118)
                    .addComponent(seat119)
                    .addComponent(seat120))
                .addGap(33, 33, 33)
                .addComponent(overReserButt)
                .addContainerGap())
        );

        javax.swing.GroupLayout OverLabCheckPanelLayout = new javax.swing.GroupLayout(OverLabCheckPanel);
        OverLabCheckPanel.setLayout(OverLabCheckPanelLayout);
        OverLabCheckPanelLayout.setHorizontalGroup(
            OverLabCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OverLabCheckPanelLayout.createSequentialGroup()
                .addGroup(OverLabCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OverLabCheckPanelLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(overSeatStatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(OverLabCheckPanelLayout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(overSeatCheckButt)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        OverLabCheckPanelLayout.setVerticalGroup(
            OverLabCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OverLabCheckPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(OverLabCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(overSeatCheckButt))
                .addGap(18, 18, 18)
                .addComponent(overSeatStatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout OverReserLayout = new javax.swing.GroupLayout(OverReser);
        OverReser.setLayout(OverReserLayout);
        OverReserLayout.setHorizontalGroup(
            OverReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OverReserLayout.createSequentialGroup()
                .addGroup(OverReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OverReserLayout.createSequentialGroup()
                        .addGap(253, 253, 253)
                        .addComponent(soloRadio)
                        .addGap(35, 35, 35)
                        .addComponent(teamRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(teamCheckButt))
                    .addGroup(OverReserLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(OverLabCheckPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        OverReserLayout.setVerticalGroup(
            OverReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OverReserLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(OverReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(soloRadio)
                    .addComponent(teamRadio)
                    .addComponent(jLabel23)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(teamCheckButt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(OverLabCheckPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        getContentPane().add(OverReser, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        Lab_menuPanel.setBackground(new java.awt.Color(255, 255, 255));
        Lab_menuPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        menuLabNotice.setBackground(new java.awt.Color(255, 255, 255));
        menuLabNotice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuLabNotice.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuLabNotice.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        menuLabNotice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLabNoticeMouseClicked(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel22.setText("실습실 공지사항 및");

        jLabel51.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel51.setText("규칙 조회");

        javax.swing.GroupLayout menuLabNoticeLayout = new javax.swing.GroupLayout(menuLabNotice);
        menuLabNotice.setLayout(menuLabNoticeLayout);
        menuLabNoticeLayout.setHorizontalGroup(
            menuLabNoticeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLabNoticeLayout.createSequentialGroup()
                .addGroup(menuLabNoticeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuLabNoticeLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel22))
                    .addGroup(menuLabNoticeLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel51)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuLabNoticeLayout.setVerticalGroup(
            menuLabNoticeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLabNoticeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel51)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuTimeTable.setBackground(new java.awt.Color(255, 255, 255));
        menuTimeTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuTimeTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuTimeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuTimeTableMouseClicked(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel24.setText("실습실 시간표 조회");

        javax.swing.GroupLayout menuTimeTableLayout = new javax.swing.GroupLayout(menuTimeTable);
        menuTimeTable.setLayout(menuTimeTableLayout);
        menuTimeTableLayout.setHorizontalGroup(
            menuTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuTimeTableLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel24)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuTimeTableLayout.setVerticalGroup(
            menuTimeTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuTimeTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuLabCheck.setBackground(new java.awt.Color(255, 255, 255));
        menuLabCheck.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuLabCheck.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuLabCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLabCheckMouseClicked(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel25.setText("사용가능한 실습실 확인");

        javax.swing.GroupLayout menuLabCheckLayout = new javax.swing.GroupLayout(menuLabCheck);
        menuLabCheck.setLayout(menuLabCheckLayout);
        menuLabCheckLayout.setHorizontalGroup(
            menuLabCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLabCheckLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuLabCheckLayout.setVerticalGroup(
            menuLabCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLabCheckLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addGap(20, 20, 20))
        );

        jLabel26.setFont(new java.awt.Font("맑은 고딕", 0, 36)); // NOI18N
        jLabel26.setText("실습실");

        javax.swing.GroupLayout Lab_menuPanelLayout = new javax.swing.GroupLayout(Lab_menuPanel);
        Lab_menuPanel.setLayout(Lab_menuPanelLayout);
        Lab_menuPanelLayout.setHorizontalGroup(
            Lab_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuLabNotice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuTimeTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuLabCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Lab_menuPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel26)
                .addGap(26, 26, 26))
        );
        Lab_menuPanelLayout.setVerticalGroup(
            Lab_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Lab_menuPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel26)
                .addGap(35, 35, 35)
                .addComponent(menuLabCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuTimeTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuLabNotice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(273, Short.MAX_VALUE))
        );

        getContentPane().add(Lab_menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 150, 620));

        LabNoticePanel.setBackground(new java.awt.Color(254, 255, 233));

        jLabel29.setText("실습실 공지사항 및 규칙 조회");

        javax.swing.GroupLayout LabNoticePanelLayout = new javax.swing.GroupLayout(LabNoticePanel);
        LabNoticePanel.setLayout(LabNoticePanelLayout);
        LabNoticePanelLayout.setHorizontalGroup(
            LabNoticePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LabNoticePanelLayout.createSequentialGroup()
                .addGap(438, 438, 438)
                .addComponent(jLabel29)
                .addContainerGap(362, Short.MAX_VALUE))
        );
        LabNoticePanelLayout.setVerticalGroup(
            LabNoticePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LabNoticePanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel29)
                .addContainerGap(572, Short.MAX_VALUE))
        );

        getContentPane().add(LabNoticePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        LabTTCheckPanel.setBackground(new java.awt.Color(254, 255, 233));

        jComboBox7.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "915", "916", "918", "911" }));

        TTCheckButt.setBackground(new java.awt.Color(255, 255, 255));
        TTCheckButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        TTCheckButt.setText("조회");
        TTCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TTCheckButtActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel30.setText("실습실 : ");

        TT.setBackground(new java.awt.Color(254, 255, 233));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "요일", "강의명", "교수명", "시작시간", "종료시간"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.setSelectionBackground(new java.awt.Color(246, 226, 231));
        jScrollPane4.setViewportView(jTable4);

        javax.swing.GroupLayout TTLayout = new javax.swing.GroupLayout(TT);
        TT.setLayout(TTLayout);
        TTLayout.setHorizontalGroup(
            TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TTLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
                .addContainerGap())
        );
        TTLayout.setVerticalGroup(
            TTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TTLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout LabTTCheckPanelLayout = new javax.swing.GroupLayout(LabTTCheckPanel);
        LabTTCheckPanel.setLayout(LabTTCheckPanelLayout);
        LabTTCheckPanelLayout.setHorizontalGroup(
            LabTTCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LabTTCheckPanelLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(TTCheckButt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LabTTCheckPanelLayout.createSequentialGroup()
                .addContainerGap(90, Short.MAX_VALUE)
                .addComponent(TT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );
        LabTTCheckPanelLayout.setVerticalGroup(
            LabTTCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LabTTCheckPanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(LabTTCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TTCheckButt)
                    .addComponent(jLabel30))
                .addGap(18, 18, 18)
                .addComponent(TT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        getContentPane().add(LabTTCheckPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        LabCheckPanel.setBackground(new java.awt.Color(254, 255, 233));
        LabCheckPanel.setPreferredSize(new java.awt.Dimension(914, 507));

        jLabel31.setFont(new java.awt.Font("맑은 고딕", 0, 17)); // NOI18N
        jLabel31.setText("시작시간");

        jLabel32.setFont(new java.awt.Font("맑은 고딕", 0, 17)); // NOI18N
        jLabel32.setText("날짜");

        jLabel33.setFont(new java.awt.Font("맑은 고딕", 0, 17)); // NOI18N
        jLabel33.setText("종료시간");

        checkButt1.setFont(new java.awt.Font("맑은 고딕", 0, 17)); // NOI18N
        checkButt1.setText("조회");
        checkButt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkButt1ActionPerformed(evt);
            }
        });

        startTimeR1.setFont(new java.awt.Font("맑은 고딕", 0, 17)); // NOI18N
        startTimeR1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00" }));
        startTimeR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startTimeR1ActionPerformed(evt);
            }
        });

        endTimeR1.setFont(new java.awt.Font("맑은 고딕", 0, 17)); // NOI18N
        endTimeR1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00" }));

        YYYY_Text1.setFont(new java.awt.Font("맑은 고딕", 0, 17)); // NOI18N
        YYYY_Text1.setText("2022");
        YYYY_Text1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YYYY_Text1ActionPerformed(evt);
            }
        });

        MM_Text1.setFont(new java.awt.Font("맑은 고딕", 0, 17)); // NOI18N
        MM_Text1.setText("10");

        DD_Text1.setFont(new java.awt.Font("맑은 고딕", 0, 17)); // NOI18N
        DD_Text1.setText("10");

        jLabel34.setFont(new java.awt.Font("맑은 고딕", 0, 17)); // NOI18N
        jLabel34.setText("-");

        jLabel35.setFont(new java.awt.Font("맑은 고딕", 0, 17)); // NOI18N
        jLabel35.setText("-");

        javax.swing.GroupLayout LabCheckPanelLayout = new javax.swing.GroupLayout(LabCheckPanel);
        LabCheckPanel.setLayout(LabCheckPanelLayout);
        LabCheckPanelLayout.setHorizontalGroup(
            LabCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LabCheckPanelLayout.createSequentialGroup()
                .addContainerGap(227, Short.MAX_VALUE)
                .addGroup(LabCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33))
                .addGap(37, 37, 37)
                .addGroup(LabCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(YYYY_Text1)
                    .addComponent(endTimeR1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(startTimeR1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(LabCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LabCheckPanelLayout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MM_Text1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DD_Text1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122))
                    .addComponent(checkButt1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(206, 206, 206))
        );
        LabCheckPanelLayout.setVerticalGroup(
            LabCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LabCheckPanelLayout.createSequentialGroup()
                .addGroup(LabCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LabCheckPanelLayout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(checkButt1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LabCheckPanelLayout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addGroup(LabCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(DD_Text1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)
                            .addComponent(YYYY_Text1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)
                            .addComponent(MM_Text1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(LabCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(startTimeR1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(LabCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(endTimeR1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(198, Short.MAX_VALUE))
        );

        getContentPane().add(LabCheckPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        LabStatusPanel.setBackground(new java.awt.Color(254, 255, 233));

        jLabel27.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel27.setText("강의실");

        LabComboBox.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        LabComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LabComboBoxActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(255, 255, 255));
        jButton10.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jButton10.setText("확인");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        LabStatus.setBackground(new java.awt.Color(254, 255, 233));
        LabStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        buttonGroup2.add(seat61);
        seat61.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat61.setText("1");

        buttonGroup2.add(seat62);
        seat62.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat62.setText("2");

        buttonGroup2.add(seat63);
        seat63.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat63.setText("3");

        buttonGroup2.add(seat64);
        seat64.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat64.setText("4");

        buttonGroup2.add(seat65);
        seat65.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat65.setText("5");

        buttonGroup2.add(seat66);
        seat66.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat66.setText("6");

        buttonGroup2.add(seat67);
        seat67.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat67.setText("7");

        buttonGroup2.add(seat68);
        seat68.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat68.setText("8");

        buttonGroup2.add(seat69);
        seat69.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat69.setText("9");

        buttonGroup2.add(seat70);
        seat70.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat70.setText("10");

        buttonGroup2.add(seat71);
        seat71.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat71.setText("11");

        buttonGroup2.add(seat72);
        seat72.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat72.setText("12");

        buttonGroup2.add(seat73);
        seat73.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat73.setText("13");

        buttonGroup2.add(seat74);
        seat74.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat74.setText("14");

        buttonGroup2.add(seat75);
        seat75.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat75.setText("15");

        buttonGroup2.add(seat76);
        seat76.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat76.setText("16");

        buttonGroup2.add(seat77);
        seat77.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat77.setText("17");

        buttonGroup2.add(seat78);
        seat78.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat78.setText("18");

        buttonGroup2.add(seat79);
        seat79.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat79.setText("19");

        buttonGroup2.add(seat80);
        seat80.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat80.setText("20");

        buttonGroup2.add(seat81);
        seat81.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat81.setText("21");

        buttonGroup2.add(seat82);
        seat82.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat82.setText("22");

        buttonGroup2.add(seat83);
        seat83.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat83.setText("23");

        buttonGroup2.add(seat84);
        seat84.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat84.setText("24");

        buttonGroup2.add(seat85);
        seat85.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat85.setText("25");

        buttonGroup2.add(seat86);
        seat86.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat86.setText("26");

        buttonGroup2.add(seat87);
        seat87.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat87.setText("27");

        buttonGroup2.add(seat88);
        seat88.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat88.setText("28");

        buttonGroup2.add(seat89);
        seat89.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat89.setText("29");

        buttonGroup2.add(seat90);
        seat90.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat90.setText("30");

        javax.swing.GroupLayout LabStatusLayout = new javax.swing.GroupLayout(LabStatus);
        LabStatus.setLayout(LabStatusLayout);
        LabStatusLayout.setHorizontalGroup(
            LabStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LabStatusLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(LabStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(LabStatusLayout.createSequentialGroup()
                        .addComponent(seat61, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(seat62, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(seat63, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(seat64, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addGap(170, 170, 170))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, LabStatusLayout.createSequentialGroup()
                        .addGroup(LabStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(LabStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(seat78)
                                .addGroup(LabStatusLayout.createSequentialGroup()
                                    .addComponent(seat69, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(seat70)))
                            .addComponent(seat77)
                            .addGroup(LabStatusLayout.createSequentialGroup()
                                .addComponent(seat85)
                                .addGap(18, 18, 18)
                                .addComponent(seat86)))
                        .addGap(18, 18, 18)
                        .addGroup(LabStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(LabStatusLayout.createSequentialGroup()
                                .addComponent(seat79)
                                .addGap(18, 18, 18)
                                .addComponent(seat80))
                            .addComponent(seat87)
                            .addGroup(LabStatusLayout.createSequentialGroup()
                                .addComponent(seat71)
                                .addGap(18, 18, 18)
                                .addComponent(seat72)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(LabStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(seat65, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat88, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(seat73, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(seat81, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addGroup(LabStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LabStatusLayout.createSequentialGroup()
                        .addComponent(seat82)
                        .addGap(18, 18, 18)
                        .addComponent(seat83))
                    .addGroup(LabStatusLayout.createSequentialGroup()
                        .addGroup(LabStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seat66, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seat74))
                        .addGap(23, 23, 23)
                        .addGroup(LabStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(LabStatusLayout.createSequentialGroup()
                                .addComponent(seat67, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(seat68))
                            .addGroup(LabStatusLayout.createSequentialGroup()
                                .addComponent(seat75)
                                .addGap(18, 18, 18)
                                .addComponent(seat76))
                            .addGroup(LabStatusLayout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(seat84))))
                    .addGroup(LabStatusLayout.createSequentialGroup()
                        .addComponent(seat89)
                        .addGap(18, 18, 18)
                        .addComponent(seat90)))
                .addGap(52, 52, 52))
        );
        LabStatusLayout.setVerticalGroup(
            LabStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LabStatusLayout.createSequentialGroup()
                .addGroup(LabStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LabStatusLayout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addGroup(LabStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(seat73)
                            .addComponent(seat74)
                            .addComponent(seat75)
                            .addComponent(seat76)))
                    .addGroup(LabStatusLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(LabStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(seat61)
                            .addComponent(seat62)
                            .addComponent(seat63)
                            .addComponent(seat64)
                            .addComponent(seat65)
                            .addComponent(seat66)
                            .addComponent(seat67)
                            .addComponent(seat68))
                        .addGroup(LabStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(LabStatusLayout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(LabStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(seat69)
                                    .addComponent(seat70)
                                    .addComponent(seat71)
                                    .addComponent(seat72)))
                            .addGroup(LabStatusLayout.createSequentialGroup()
                                .addGap(139, 139, 139)
                                .addGroup(LabStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(seat79)
                                    .addComponent(seat80)
                                    .addComponent(seat78)
                                    .addComponent(seat77)
                                    .addComponent(seat81)
                                    .addComponent(seat82)
                                    .addComponent(seat83)
                                    .addComponent(seat84))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(LabStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seat86)
                    .addComponent(seat85)
                    .addComponent(seat87)
                    .addComponent(seat88)
                    .addComponent(seat89)
                    .addComponent(seat90))
                .addGap(72, 72, 72))
        );

        javax.swing.GroupLayout LabStatusPanelLayout = new javax.swing.GroupLayout(LabStatusPanel);
        LabStatusPanel.setLayout(LabStatusPanelLayout);
        LabStatusPanelLayout.setHorizontalGroup(
            LabStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LabStatusPanelLayout.createSequentialGroup()
                .addGap(302, 302, 302)
                .addComponent(jLabel27)
                .addGap(62, 62, 62)
                .addComponent(LabComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jButton10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LabStatusPanelLayout.createSequentialGroup()
                .addContainerGap(124, Short.MAX_VALUE)
                .addComponent(LabStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );
        LabStatusPanelLayout.setVerticalGroup(
            LabStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LabStatusPanelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(LabStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(LabComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(LabStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );

        getContentPane().add(LabStatusPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 108, 960, 620));

        UserInfo_menuPanel.setBackground(new java.awt.Color(255, 255, 255));
        UserInfo_menuPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        menuUserDelete.setBackground(new java.awt.Color(255, 255, 255));
        menuUserDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuUserDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuUserDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuUserDeleteMouseClicked(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel37.setText("회원 정보 삭제");

        javax.swing.GroupLayout menuUserDeleteLayout = new javax.swing.GroupLayout(menuUserDelete);
        menuUserDelete.setLayout(menuUserDeleteLayout);
        menuUserDeleteLayout.setHorizontalGroup(
            menuUserDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuUserDeleteLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel37)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuUserDeleteLayout.setVerticalGroup(
            menuUserDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuUserDeleteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuUserChange.setBackground(new java.awt.Color(255, 255, 255));
        menuUserChange.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuUserChange.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuUserChange.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuUserChangeMouseClicked(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel38.setText("회원 정보 수정");

        javax.swing.GroupLayout menuUserChangeLayout = new javax.swing.GroupLayout(menuUserChange);
        menuUserChange.setLayout(menuUserChangeLayout);
        menuUserChangeLayout.setHorizontalGroup(
            menuUserChangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuUserChangeLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel38)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuUserChangeLayout.setVerticalGroup(
            menuUserChangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuUserChangeLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel38)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel39.setFont(new java.awt.Font("맑은 고딕", 0, 36)); // NOI18N
        jLabel39.setText("회원정보");

        javax.swing.GroupLayout UserInfo_menuPanelLayout = new javax.swing.GroupLayout(UserInfo_menuPanel);
        UserInfo_menuPanel.setLayout(UserInfo_menuPanelLayout);
        UserInfo_menuPanelLayout.setHorizontalGroup(
            UserInfo_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuUserDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuUserChange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserInfo_menuPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel39)
                .addContainerGap())
        );
        UserInfo_menuPanelLayout.setVerticalGroup(
            UserInfo_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserInfo_menuPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel39)
                .addGap(32, 32, 32)
                .addComponent(menuUserChange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuUserDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(353, Short.MAX_VALUE))
        );

        getContentPane().add(UserInfo_menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 150, 620));

        UserChangePanel.setBackground(new java.awt.Color(254, 255, 233));

        jLabel36.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel36.setText("ID : ");

        jLabel40.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel40.setText("PW : ");

        jLabel41.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel41.setText("이름 : ");

        jLabel42.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel42.setText("전화번호 : ");

        jLabel43.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel43.setText("이메일 : ");

        jLabel44.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel44.setText("비고 : ");

        jTextField3.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jTextField3.setText("jTextField3");

        jTextField4.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jTextField4.setText("jTextField4");

        jTextField5.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jTextField5.setText("jTextField5");

        jTextField6.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jTextField6.setText("jTextField6");

        jTextField7.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jTextField7.setText("jTextField7");

        jTextField8.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jTextField8.setText("jTextField8");

        UserChangeButt.setBackground(new java.awt.Color(255, 255, 255));
        UserChangeButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        UserChangeButt.setText("수정");
        UserChangeButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserChangeButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UserChangePanelLayout = new javax.swing.GroupLayout(UserChangePanel);
        UserChangePanel.setLayout(UserChangePanelLayout);
        UserChangePanelLayout.setHorizontalGroup(
            UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserChangePanelLayout.createSequentialGroup()
                .addContainerGap(358, Short.MAX_VALUE)
                .addGroup(UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UserChangePanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel42)))
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(UserChangePanelLayout.createSequentialGroup()
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116)
                        .addComponent(UserChangeButt)))
                .addGap(268, 268, 268))
        );
        UserChangePanelLayout.setVerticalGroup(
            UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserChangePanelLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addGroup(UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addGap(21, 21, 21)
                .addGroup(UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserChangeButt))
                .addContainerGap(204, Short.MAX_VALUE))
        );

        getContentPane().add(UserChangePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        UserDeletePanel.setBackground(new java.awt.Color(254, 255, 233));

        UserDeleteButt.setBackground(new java.awt.Color(255, 255, 255));
        UserDeleteButt.setFont(new java.awt.Font("맑은 고딕", 0, 16)); // NOI18N
        UserDeleteButt.setText("회원 정보 삭제");
        UserDeleteButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserDeleteButtActionPerformed(evt);
            }
        });

        jTextField9.setFont(new java.awt.Font("맑은 고딕", 0, 16)); // NOI18N
        jTextField9.setText("홍길동");

        jLabel45.setFont(new java.awt.Font("맑은 고딕", 0, 16)); // NOI18N
        jLabel45.setText("님 회원 정보 삭제하시겠습니까?");

        javax.swing.GroupLayout UserDeletePanelLayout = new javax.swing.GroupLayout(UserDeletePanel);
        UserDeletePanel.setLayout(UserDeletePanelLayout);
        UserDeletePanelLayout.setHorizontalGroup(
            UserDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserDeletePanelLayout.createSequentialGroup()
                .addGap(315, 315, 315)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(UserDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserDeletePanelLayout.createSequentialGroup()
                        .addComponent(UserDeleteButt)
                        .addGap(82, 82, 82)))
                .addContainerGap(345, Short.MAX_VALUE))
        );
        UserDeletePanelLayout.setVerticalGroup(
            UserDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserDeletePanelLayout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addGroup(UserDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addGap(62, 62, 62)
                .addComponent(UserDeleteButt)
                .addContainerGap(285, Short.MAX_VALUE))
        );

        getContentPane().add(UserDeletePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        aPanel.setBackground(new java.awt.Color(254, 255, 233));

        jLabel15.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel15.setText("5시 이전 실습실 예약");

        jLabel16.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel16.setText("날짜 : ");

        jLabel28.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel28.setText("강의실 : ");

        jTextField17.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jTextField17.setText("2022/11/21");
        jTextField17.setToolTipText("");

        jComboBox9.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "915", "916", "918", "911" }));

        beforeCheckButt.setBackground(new java.awt.Color(255, 255, 255));
        beforeCheckButt.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        beforeCheckButt.setText("조회");
        beforeCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beforeCheckButtActionPerformed(evt);
            }
        });

        beforeTTPanel.setBackground(new java.awt.Color(254, 255, 233));

        jLabel52.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel52.setText("월");

        Mon1.setBackground(new java.awt.Color(255, 255, 255));
        Mon1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Mon1.setPreferredSize(new java.awt.Dimension(60, 60));
        Mon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Mon1MouseClicked(evt);
            }
        });
        Mon1.setLayout(new java.awt.GridLayout(1, 1));

        Mon2.setBackground(new java.awt.Color(255, 255, 255));
        Mon2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Mon2.setPreferredSize(new java.awt.Dimension(60, 60));
        Mon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Mon2MouseClicked(evt);
            }
        });
        Mon2.setLayout(new java.awt.GridLayout(1, 1));

        Mon3.setBackground(new java.awt.Color(255, 255, 255));
        Mon3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Mon3.setPreferredSize(new java.awt.Dimension(60, 60));
        Mon3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Mon3MouseClicked(evt);
            }
        });
        Mon3.setLayout(new java.awt.GridLayout(1, 1));

        Mon4.setBackground(new java.awt.Color(255, 255, 255));
        Mon4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Mon4.setPreferredSize(new java.awt.Dimension(60, 60));
        Mon4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Mon4MouseClicked(evt);
            }
        });
        Mon4.setLayout(new java.awt.GridLayout(1, 1));

        M5.setBackground(new java.awt.Color(255, 255, 255));
        M5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        M5.setPreferredSize(new java.awt.Dimension(60, 60));
        M5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                M5MouseClicked(evt);
            }
        });
        M5.setLayout(new java.awt.GridLayout(1, 1));

        M6.setBackground(new java.awt.Color(255, 255, 255));
        M6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        M6.setPreferredSize(new java.awt.Dimension(60, 60));
        M6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                M6MouseClicked(evt);
            }
        });
        M6.setLayout(new java.awt.GridLayout(1, 1));

        M7.setBackground(new java.awt.Color(255, 255, 255));
        M7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        M7.setPreferredSize(new java.awt.Dimension(60, 60));
        M7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                M7MouseClicked(evt);
            }
        });
        M7.setLayout(new java.awt.GridLayout(1, 1));

        M8.setBackground(new java.awt.Color(255, 255, 255));
        M8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        M8.setPreferredSize(new java.awt.Dimension(60, 60));
        M8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                M8MouseClicked(evt);
            }
        });
        M8.setLayout(new java.awt.GridLayout(1, 1));

        M9.setBackground(new java.awt.Color(255, 255, 255));
        M9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        M9.setPreferredSize(new java.awt.Dimension(60, 60));
        M9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                M9MouseClicked(evt);
            }
        });
        M9.setLayout(new java.awt.GridLayout(1, 1));

        Tue2.setBackground(new java.awt.Color(255, 255, 255));
        Tue2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tue2.setPreferredSize(new java.awt.Dimension(60, 60));
        Tue2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tue2MouseClicked(evt);
            }
        });
        Tue2.setLayout(new java.awt.GridLayout(1, 1));

        Tue3.setBackground(new java.awt.Color(255, 255, 255));
        Tue3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tue3.setPreferredSize(new java.awt.Dimension(60, 60));
        Tue3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tue3MouseClicked(evt);
            }
        });
        Tue3.setLayout(new java.awt.GridLayout(1, 1));

        Tue4.setBackground(new java.awt.Color(255, 255, 255));
        Tue4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tue4.setPreferredSize(new java.awt.Dimension(60, 60));
        Tue4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tue4MouseClicked(evt);
            }
        });
        Tue4.setLayout(new java.awt.GridLayout(1, 1));

        Tue5.setBackground(new java.awt.Color(255, 255, 255));
        Tue5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tue5.setPreferredSize(new java.awt.Dimension(60, 60));
        Tue5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tue5MouseClicked(evt);
            }
        });
        Tue5.setLayout(new java.awt.GridLayout(1, 1));

        Tue6.setBackground(new java.awt.Color(255, 255, 255));
        Tue6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tue6.setPreferredSize(new java.awt.Dimension(60, 60));
        Tue6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tue6MouseClicked(evt);
            }
        });
        Tue6.setLayout(new java.awt.GridLayout(1, 1));

        Tue7.setBackground(new java.awt.Color(255, 255, 255));
        Tue7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tue7.setPreferredSize(new java.awt.Dimension(60, 60));
        Tue7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tue7MouseClicked(evt);
            }
        });
        Tue7.setLayout(new java.awt.GridLayout(1, 1));

        Tue8.setBackground(new java.awt.Color(255, 255, 255));
        Tue8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tue8.setPreferredSize(new java.awt.Dimension(60, 60));
        Tue8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tue8MouseClicked(evt);
            }
        });
        Tue8.setLayout(new java.awt.GridLayout(1, 1));

        Tue9.setBackground(new java.awt.Color(255, 255, 255));
        Tue9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tue9.setPreferredSize(new java.awt.Dimension(60, 60));
        Tue9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tue9MouseClicked(evt);
            }
        });
        Tue9.setLayout(new java.awt.GridLayout(1, 1));

        Tue1.setBackground(new java.awt.Color(255, 255, 255));
        Tue1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tue1.setPreferredSize(new java.awt.Dimension(60, 60));
        Tue1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tue1MouseClicked(evt);
            }
        });
        Tue1.setLayout(new java.awt.GridLayout(1, 1));

        Wed2.setBackground(new java.awt.Color(255, 255, 255));
        Wed2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Wed2.setPreferredSize(new java.awt.Dimension(60, 60));
        Wed2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Wed2MouseClicked(evt);
            }
        });
        Wed2.setLayout(new java.awt.GridLayout(1, 1));

        Wed3.setBackground(new java.awt.Color(255, 255, 255));
        Wed3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Wed3.setPreferredSize(new java.awt.Dimension(60, 60));
        Wed3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Wed3MouseClicked(evt);
            }
        });
        Wed3.setLayout(new java.awt.GridLayout(1, 1));

        Wed4.setBackground(new java.awt.Color(255, 255, 255));
        Wed4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Wed4.setPreferredSize(new java.awt.Dimension(60, 60));
        Wed4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Wed4MouseClicked(evt);
            }
        });
        Wed4.setLayout(new java.awt.GridLayout(1, 1));

        Wed5.setBackground(new java.awt.Color(255, 255, 255));
        Wed5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Wed5.setPreferredSize(new java.awt.Dimension(60, 60));
        Wed5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Wed5MouseClicked(evt);
            }
        });
        Wed5.setLayout(new java.awt.GridLayout(1, 1));

        Wed6.setBackground(new java.awt.Color(255, 255, 255));
        Wed6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Wed6.setPreferredSize(new java.awt.Dimension(60, 60));
        Wed6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Wed6MouseClicked(evt);
            }
        });
        Wed6.setLayout(new java.awt.GridLayout(1, 1));

        Wed7.setBackground(new java.awt.Color(255, 255, 255));
        Wed7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Wed7.setPreferredSize(new java.awt.Dimension(60, 60));
        Wed7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Wed7MouseClicked(evt);
            }
        });
        Wed7.setLayout(new java.awt.GridLayout(1, 1));

        Wed8.setBackground(new java.awt.Color(255, 255, 255));
        Wed8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Wed8.setPreferredSize(new java.awt.Dimension(60, 60));
        Wed8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Wed8MouseClicked(evt);
            }
        });
        Wed8.setLayout(new java.awt.GridLayout(1, 1));

        Wed9.setBackground(new java.awt.Color(255, 255, 255));
        Wed9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Wed9.setPreferredSize(new java.awt.Dimension(60, 60));
        Wed9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Wed9MouseClicked(evt);
            }
        });
        Wed9.setLayout(new java.awt.GridLayout(1, 1));

        Wed1.setBackground(new java.awt.Color(255, 255, 255));
        Wed1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Wed1.setPreferredSize(new java.awt.Dimension(60, 60));
        Wed1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Wed1MouseClicked(evt);
            }
        });
        Wed1.setLayout(new java.awt.GridLayout(1, 1));

        Thu2.setBackground(new java.awt.Color(255, 255, 255));
        Thu2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Thu2.setPreferredSize(new java.awt.Dimension(60, 60));
        Thu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Thu2MouseClicked(evt);
            }
        });
        Thu2.setLayout(new java.awt.GridLayout(1, 1));

        Thu3.setBackground(new java.awt.Color(255, 255, 255));
        Thu3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Thu3.setPreferredSize(new java.awt.Dimension(60, 60));
        Thu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Thu3MouseClicked(evt);
            }
        });
        Thu3.setLayout(new java.awt.GridLayout(1, 1));

        Thu4.setBackground(new java.awt.Color(255, 255, 255));
        Thu4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Thu4.setPreferredSize(new java.awt.Dimension(60, 60));
        Thu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Thu4MouseClicked(evt);
            }
        });
        Thu4.setLayout(new java.awt.GridLayout(1, 1));

        Thu5.setBackground(new java.awt.Color(255, 255, 255));
        Thu5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Thu5.setPreferredSize(new java.awt.Dimension(60, 60));
        Thu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Thu5MouseClicked(evt);
            }
        });
        Thu5.setLayout(new java.awt.GridLayout(1, 1));

        Thu6.setBackground(new java.awt.Color(255, 255, 255));
        Thu6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Thu6.setPreferredSize(new java.awt.Dimension(60, 60));
        Thu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Thu6MouseClicked(evt);
            }
        });
        Thu6.setLayout(new java.awt.GridLayout(1, 1));

        Thu7.setBackground(new java.awt.Color(255, 255, 255));
        Thu7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Thu7.setPreferredSize(new java.awt.Dimension(60, 60));
        Thu7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Thu7MouseClicked(evt);
            }
        });
        Thu7.setLayout(new java.awt.GridLayout(1, 1));

        Thu8.setBackground(new java.awt.Color(255, 255, 255));
        Thu8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Thu8.setPreferredSize(new java.awt.Dimension(60, 60));
        Thu8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Thu8MouseClicked(evt);
            }
        });
        Thu8.setLayout(new java.awt.GridLayout(1, 1));

        Thu9.setBackground(new java.awt.Color(255, 255, 255));
        Thu9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Thu9.setPreferredSize(new java.awt.Dimension(60, 60));
        Thu9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Thu9MouseClicked(evt);
            }
        });
        Thu9.setLayout(new java.awt.GridLayout(1, 1));

        Thu1.setBackground(new java.awt.Color(255, 255, 255));
        Thu1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Thu1.setPreferredSize(new java.awt.Dimension(60, 60));
        Thu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Thu1MouseClicked(evt);
            }
        });
        Thu1.setLayout(new java.awt.GridLayout(1, 1));

        Fri2.setBackground(new java.awt.Color(255, 255, 255));
        Fri2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Fri2.setPreferredSize(new java.awt.Dimension(60, 60));
        Fri2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Fri2MouseClicked(evt);
            }
        });
        Fri2.setLayout(new java.awt.GridLayout(1, 1));

        Fri3.setBackground(new java.awt.Color(255, 255, 255));
        Fri3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Fri3.setPreferredSize(new java.awt.Dimension(60, 60));
        Fri3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Fri3MouseClicked(evt);
            }
        });
        Fri3.setLayout(new java.awt.GridLayout(1, 1));

        Fri4.setBackground(new java.awt.Color(255, 255, 255));
        Fri4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Fri4.setPreferredSize(new java.awt.Dimension(60, 60));
        Fri4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Fri4MouseClicked(evt);
            }
        });
        Fri4.setLayout(new java.awt.GridLayout(1, 1));

        Fri5.setBackground(new java.awt.Color(255, 255, 255));
        Fri5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Fri5.setPreferredSize(new java.awt.Dimension(60, 60));
        Fri5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Fri5MouseClicked(evt);
            }
        });
        Fri5.setLayout(new java.awt.GridLayout(1, 1));

        Fri6.setBackground(new java.awt.Color(255, 255, 255));
        Fri6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Fri6.setPreferredSize(new java.awt.Dimension(60, 60));
        Fri6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Fri6MouseClicked(evt);
            }
        });
        Fri6.setLayout(new java.awt.GridLayout(1, 1));

        Fri7.setBackground(new java.awt.Color(255, 255, 255));
        Fri7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Fri7.setPreferredSize(new java.awt.Dimension(60, 60));
        Fri7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Fri7MouseClicked(evt);
            }
        });
        Fri7.setLayout(new java.awt.GridLayout(1, 1));

        Fri8.setBackground(new java.awt.Color(255, 255, 255));
        Fri8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Fri8.setPreferredSize(new java.awt.Dimension(60, 60));
        Fri8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Fri8MouseClicked(evt);
            }
        });
        Fri8.setLayout(new java.awt.GridLayout(1, 1));

        Fri9.setBackground(new java.awt.Color(255, 255, 255));
        Fri9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Fri9.setPreferredSize(new java.awt.Dimension(60, 60));
        Fri9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Fri9MouseClicked(evt);
            }
        });
        Fri9.setLayout(new java.awt.GridLayout(1, 1));

        Fri1.setBackground(new java.awt.Color(255, 255, 255));
        Fri1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Fri1.setPreferredSize(new java.awt.Dimension(60, 60));
        Fri1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Fri1MouseClicked(evt);
            }
        });
        Fri1.setLayout(new java.awt.GridLayout(1, 1));

        jLabel53.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel53.setText("화");

        jLabel54.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel54.setText("수");

        jLabel55.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel55.setText("목");

        jLabel56.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel56.setText("금");

        jPanel97.setBackground(new java.awt.Color(255, 255, 255));
        jPanel97.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel97.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel97.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel97MouseClicked(evt);
            }
        });
        jPanel97.setLayout(new java.awt.GridLayout(1, 1));

        jPanel98.setBackground(new java.awt.Color(255, 255, 255));
        jPanel98.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel98.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel98.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel98MouseClicked(evt);
            }
        });
        jPanel98.setLayout(new java.awt.GridLayout(1, 1));

        jPanel99.setBackground(new java.awt.Color(255, 255, 255));
        jPanel99.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel99.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel99.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel99MouseClicked(evt);
            }
        });
        jPanel99.setLayout(new java.awt.GridLayout(1, 1));

        jPanel100.setBackground(new java.awt.Color(255, 255, 255));
        jPanel100.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel100.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel100.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel100MouseClicked(evt);
            }
        });
        jPanel100.setLayout(new java.awt.GridLayout(1, 1));

        Sat1.setBackground(new java.awt.Color(255, 255, 255));
        Sat1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Sat1.setPreferredSize(new java.awt.Dimension(60, 60));
        Sat1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Sat1MouseClicked(evt);
            }
        });
        Sat1.setLayout(new java.awt.GridLayout(1, 1));

        jPanel102.setBackground(new java.awt.Color(255, 255, 255));
        jPanel102.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel102.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel102.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel102MouseClicked(evt);
            }
        });
        jPanel102.setLayout(new java.awt.GridLayout(1, 1));

        jPanel103.setBackground(new java.awt.Color(255, 255, 255));
        jPanel103.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel103.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel103.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel103MouseClicked(evt);
            }
        });
        jPanel103.setLayout(new java.awt.GridLayout(1, 1));

        jPanel104.setBackground(new java.awt.Color(255, 255, 255));
        jPanel104.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel104.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel104.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel104MouseClicked(evt);
            }
        });
        jPanel104.setLayout(new java.awt.GridLayout(1, 1));

        jPanel105.setBackground(new java.awt.Color(255, 255, 255));
        jPanel105.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel105.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel105.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel105MouseClicked(evt);
            }
        });
        jPanel105.setLayout(new java.awt.GridLayout(1, 1));

        jPanel106.setBackground(new java.awt.Color(255, 255, 255));
        jPanel106.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel106.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel106.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel106MouseClicked(evt);
            }
        });
        jPanel106.setLayout(new java.awt.GridLayout(1, 1));

        jPanel107.setBackground(new java.awt.Color(255, 255, 255));
        jPanel107.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel107.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel107.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel107MouseClicked(evt);
            }
        });
        jPanel107.setLayout(new java.awt.GridLayout(1, 1));

        jPanel108.setBackground(new java.awt.Color(255, 255, 255));
        jPanel108.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel108.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel108.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel108MouseClicked(evt);
            }
        });
        jPanel108.setLayout(new java.awt.GridLayout(1, 1));

        jPanel109.setBackground(new java.awt.Color(255, 255, 255));
        jPanel109.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel109.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel109.setLayout(new java.awt.GridLayout(1, 1));

        Sun1.setBackground(new java.awt.Color(255, 255, 255));
        Sun1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Sun1.setPreferredSize(new java.awt.Dimension(60, 60));
        Sun1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Sun1MouseClicked(evt);
            }
        });
        Sun1.setLayout(new java.awt.GridLayout(1, 1));

        jPanel111.setBackground(new java.awt.Color(255, 255, 255));
        jPanel111.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel111.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel111.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel111MouseClicked(evt);
            }
        });
        jPanel111.setLayout(new java.awt.GridLayout(1, 1));

        jPanel112.setBackground(new java.awt.Color(255, 255, 255));
        jPanel112.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel112.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel112.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel112MouseClicked(evt);
            }
        });
        jPanel112.setLayout(new java.awt.GridLayout(1, 1));

        jPanel113.setBackground(new java.awt.Color(255, 255, 255));
        jPanel113.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel113.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel113.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel113MouseClicked(evt);
            }
        });
        jPanel113.setLayout(new java.awt.GridLayout(1, 1));

        jPanel114.setBackground(new java.awt.Color(255, 255, 255));
        jPanel114.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel114.setPreferredSize(new java.awt.Dimension(60, 60));
        jPanel114.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel114MouseClicked(evt);
            }
        });
        jPanel114.setLayout(new java.awt.GridLayout(1, 1));

        jLabel57.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel57.setText("토");

        jLabel58.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel58.setText("일");

        beforeSeatCheckButt.setBackground(new java.awt.Color(255, 255, 255));
        beforeSeatCheckButt.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        beforeSeatCheckButt.setText("조회");
        beforeSeatCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beforeSeatCheckButtActionPerformed(evt);
            }
        });

        jLabel73.setText("9");

        jLabel74.setText("10");

        jLabel75.setText("11");

        jLabel76.setText("12");

        jLabel77.setText("13");

        jLabel78.setText("14");

        jLabel79.setText("15");

        jLabel80.setText("16");

        jLabel81.setText("17");

        javax.swing.GroupLayout beforeTTPanelLayout = new javax.swing.GroupLayout(beforeTTPanel);
        beforeTTPanel.setLayout(beforeTTPanelLayout);
        beforeTTPanelLayout.setHorizontalGroup(
            beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beforeTTPanelLayout.createSequentialGroup()
                .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(beforeTTPanelLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel52)
                        .addGap(86, 86, 86)
                        .addComponent(jLabel53)
                        .addGap(81, 81, 81)
                        .addComponent(jLabel54)
                        .addGap(83, 83, 83)
                        .addComponent(jLabel55)
                        .addGap(78, 78, 78)
                        .addComponent(jLabel56)
                        .addGap(84, 84, 84)
                        .addComponent(jLabel57)
                        .addGap(81, 81, 81)
                        .addComponent(jLabel58))
                    .addGroup(beforeTTPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(beforeTTPanelLayout.createSequentialGroup()
                                .addComponent(jLabel74)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Mon2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(beforeTTPanelLayout.createSequentialGroup()
                                .addComponent(jLabel75)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Mon3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(beforeTTPanelLayout.createSequentialGroup()
                                .addComponent(jLabel76)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Mon4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(beforeTTPanelLayout.createSequentialGroup()
                                .addComponent(jLabel77)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(M5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(beforeTTPanelLayout.createSequentialGroup()
                                .addComponent(jLabel78)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(M6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(beforeTTPanelLayout.createSequentialGroup()
                                .addComponent(jLabel79)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(M7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(beforeTTPanelLayout.createSequentialGroup()
                                .addComponent(jLabel80)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(M8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(beforeTTPanelLayout.createSequentialGroup()
                                .addComponent(jLabel81)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(M9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(beforeTTPanelLayout.createSequentialGroup()
                                .addComponent(jLabel73)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Mon1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                        .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Tue1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Tue2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Tue3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Tue4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Tue5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Tue6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Tue7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Tue8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Tue9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Wed1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Wed2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Wed3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Wed4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Wed5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Wed6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Wed7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Wed8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Wed9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Thu1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Thu2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Thu3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Thu4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Thu5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Thu6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Thu7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Thu8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Thu9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Fri1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Fri2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Fri3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Fri4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Fri5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Fri6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Fri7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Fri8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Fri9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Sat1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel111, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel112, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel113, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel114, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel97, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel98, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel99, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel100, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Sun1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel102, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel103, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel104, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel105, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel106, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel107, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel108, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel109, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(beforeSeatCheckButt)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        beforeTTPanelLayout.setVerticalGroup(
            beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, beforeTTPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel53, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel56)
                        .addComponent(jLabel57)
                        .addComponent(jLabel58)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(beforeTTPanelLayout.createSequentialGroup()
                                .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Mon1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel73))
                                .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel74)
                                    .addComponent(Mon2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel75)
                                    .addComponent(Mon3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel76)
                                    .addComponent(Mon4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel77)
                                    .addComponent(M5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel78)
                                    .addComponent(M6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel79)
                                    .addComponent(M7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel80)
                                    .addComponent(M8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(M9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel81)))
                            .addGroup(beforeTTPanelLayout.createSequentialGroup()
                                .addComponent(Tue1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Tue2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Tue3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Tue4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Tue5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Tue6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Tue7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Tue8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Tue9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(beforeTTPanelLayout.createSequentialGroup()
                                .addComponent(Wed1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Wed2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Wed3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Wed4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Wed5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Wed6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Wed7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Wed8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Wed9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(beforeTTPanelLayout.createSequentialGroup()
                                .addComponent(Fri1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Fri2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Fri3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Fri4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Fri5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Fri6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Fri7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Fri8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Fri9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(beforeTTPanelLayout.createSequentialGroup()
                                .addComponent(Thu1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Thu2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Thu3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Thu4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Thu5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Thu6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Thu7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Thu8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(Thu9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(beforeTTPanelLayout.createSequentialGroup()
                        .addComponent(Sat1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel111, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel112, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel113, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel114, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel97, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel98, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel99, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel100, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(beforeTTPanelLayout.createSequentialGroup()
                        .addComponent(Sun1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel102, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel103, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel104, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(beforeTTPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(beforeTTPanelLayout.createSequentialGroup()
                                .addComponent(jPanel105, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel106, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel107, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel108, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel109, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, beforeTTPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(beforeSeatCheckButt)))))
                .addContainerGap())
        );

        javax.swing.GroupLayout aPanelLayout = new javax.swing.GroupLayout(aPanel);
        aPanel.setLayout(aPanelLayout);
        aPanelLayout.setHorizontalGroup(
            aPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aPanelLayout.createSequentialGroup()
                .addGroup(aPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(aPanelLayout.createSequentialGroup()
                        .addGap(387, 387, 387)
                        .addComponent(jLabel15))
                    .addGroup(aPanelLayout.createSequentialGroup()
                        .addGap(233, 233, 233)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(beforeCheckButt))
                    .addGroup(aPanelLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(beforeTTPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        aPanelLayout.setVerticalGroup(
            aPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(aPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel28)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(beforeCheckButt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beforeTTPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(aPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        beforeSeatStatePanel.setBackground(new java.awt.Color(254, 255, 233));
        beforeSeatStatePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        buttonGroup2.add(seat31);
        seat31.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat31.setText("1");
        seat31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat31ActionPerformed(evt);
            }
        });

        buttonGroup2.add(seat32);
        seat32.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat32.setText("2");

        buttonGroup2.add(seat33);
        seat33.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat33.setText("3");

        buttonGroup2.add(seat34);
        seat34.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat34.setText("4");

        buttonGroup2.add(seat35);
        seat35.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat35.setText("5");

        buttonGroup2.add(seat36);
        seat36.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat36.setText("6");
        seat36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat36ActionPerformed(evt);
            }
        });

        buttonGroup2.add(seat37);
        seat37.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat37.setText("7");

        buttonGroup2.add(seat38);
        seat38.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat38.setText("8");

        buttonGroup2.add(seat39);
        seat39.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat39.setText("9");

        buttonGroup2.add(seat40);
        seat40.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat40.setText("10");

        buttonGroup2.add(seat41);
        seat41.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat41.setText("11");

        buttonGroup2.add(seat42);
        seat42.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat42.setText("12");

        buttonGroup2.add(seat43);
        seat43.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat43.setText("13");

        buttonGroup2.add(seat44);
        seat44.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat44.setText("14");

        buttonGroup2.add(seat45);
        seat45.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat45.setText("15");

        buttonGroup2.add(seat46);
        seat46.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat46.setText("16");

        buttonGroup2.add(seat47);
        seat47.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat47.setText("17");

        buttonGroup2.add(seat48);
        seat48.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat48.setText("18");

        buttonGroup2.add(seat49);
        seat49.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat49.setText("19");

        buttonGroup2.add(seat50);
        seat50.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat50.setText("20");

        buttonGroup2.add(seat51);
        seat51.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat51.setText("21");

        buttonGroup2.add(seat52);
        seat52.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat52.setText("22");

        buttonGroup2.add(seat53);
        seat53.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat53.setText("23");

        buttonGroup2.add(seat54);
        seat54.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat54.setText("24");

        buttonGroup2.add(seat55);
        seat55.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat55.setText("25");

        buttonGroup2.add(seat56);
        seat56.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat56.setText("26");

        buttonGroup2.add(seat57);
        seat57.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat57.setText("27");

        buttonGroup2.add(seat58);
        seat58.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat58.setText("28");

        buttonGroup2.add(seat59);
        seat59.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat59.setText("29");

        buttonGroup2.add(seat60);
        seat60.setFont(new java.awt.Font("굴림", 0, 16)); // NOI18N
        seat60.setText("30");

        BeforeReserButt.setBackground(new java.awt.Color(255, 255, 255));
        BeforeReserButt.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        BeforeReserButt.setText("예약");
        BeforeReserButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BeforeReserButtActionPerformed(evt);
            }
        });

        buttonGroup2.add(resetRadio);

        jLabel59.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel59.setText("날짜 : ");

        jLabel60.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel60.setText("강의실 : ");

        jLabel61.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel61.setText("시작시간 : ");

        jLabel62.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jLabel62.setText("종료시간 : ");

        jTextField15.setEditable(false);
        jTextField15.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N

        jTextField16.setEditable(false);
        jTextField16.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N

        jTextField19.setEditable(false);
        jTextField19.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N

        jTextField20.setEditable(false);
        jTextField20.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N

        javax.swing.GroupLayout beforeSeatStatePanelLayout = new javax.swing.GroupLayout(beforeSeatStatePanel);
        beforeSeatStatePanel.setLayout(beforeSeatStatePanelLayout);
        beforeSeatStatePanelLayout.setHorizontalGroup(
            beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(seat31, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat39, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat47)
                    .addComponent(seat55))
                .addGap(48, 48, 48)
                .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(seat48)
                    .addComponent(seat56)
                    .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(seat40)
                        .addComponent(seat32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48)
                .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seat49)
                            .addComponent(seat57))
                        .addGap(48, 48, 48)
                        .addComponent(seat50))
                    .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seat33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seat41))
                        .addGap(48, 48, 48)
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seat42)
                            .addComponent(seat34, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(92, 163, Short.MAX_VALUE)
                .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, beforeSeatStatePanelLayout.createSequentialGroup()
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BeforeReserButt)
                            .addComponent(resetRadio))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, beforeSeatStatePanelLayout.createSequentialGroup()
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seat43, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(seat35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seat51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seat58, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seat36, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(seat59, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(seat52, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(seat44, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(48, 48, 48)
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seat60)
                            .addComponent(seat45)
                            .addComponent(seat53)
                            .addComponent(seat37, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seat54)
                            .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(seat38, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(seat46, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(98, 98, 98))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, beforeSeatStatePanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, beforeSeatStatePanelLayout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, beforeSeatStatePanelLayout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(114, 114, 114)
                .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62)
                    .addComponent(jLabel60))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(251, 251, 251))
        );
        beforeSeatStatePanelLayout.setVerticalGroup(
            beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, beforeSeatStatePanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resetRadio)
                    .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel59)
                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel60)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel61)
                            .addComponent(jLabel62)
                            .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(77, 77, 77)
                .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(seat31)
                            .addComponent(seat32)
                            .addComponent(seat33)
                            .addComponent(seat34))
                        .addGap(74, 74, 74)
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(seat40)
                            .addComponent(seat41)
                            .addComponent(seat42)
                            .addComponent(seat39))
                        .addGap(74, 74, 74)
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(seat47)
                            .addComponent(seat48)
                            .addComponent(seat49)
                            .addComponent(seat50))
                        .addGap(74, 74, 74)
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(seat55)
                            .addComponent(seat56)
                            .addComponent(seat57)))
                    .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                        .addComponent(seat38)
                        .addGap(74, 74, 74)
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(seat43)
                            .addComponent(seat46))
                        .addGap(74, 74, 74)
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(seat51)
                                .addComponent(seat54))
                            .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                                .addGap(101, 101, 101)
                                .addComponent(seat58))))
                    .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                        .addComponent(seat37)
                        .addGap(74, 74, 74)
                        .addComponent(seat45)
                        .addGap(74, 74, 74)
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seat53)
                            .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                                .addGap(101, 101, 101)
                                .addComponent(seat60))))
                    .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(seat44)
                        .addGap(74, 74, 74)
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seat52)
                            .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                                .addGap(101, 101, 101)
                                .addComponent(seat59))))
                    .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(seat36)
                        .addComponent(seat35)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(BeforeReserButt)
                .addGap(23, 23, 23))
        );

        getContentPane().add(beforeSeatStatePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // 메인화면 - 실습실 예약 버튼
    private void labReserButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labReserButtActionPerformed
        reset();
        ReserPanel.setVisible(true);
        Reser_menuPanel.setVisible(true);

        menuReser.setBackground(yellow);
    }//GEN-LAST:event_labReserButtActionPerformed

    // 타이틀 패널 클릭시 메인 패널로 이동
    private void TitlePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TitlePanelMouseClicked
        reset();
        mainPanel.setVisible(true);
    }//GEN-LAST:event_TitlePanelMouseClicked

    // 5시 이전 or 이후 예약 선택 후 next 버튼
    private void reserNextButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reserNextButtActionPerformed
        if (beforeReserRadio.isSelected() == true) {  // 5시 이전
            reset();
            Reser_menuPanel.setVisible(true);
            menuReser.setBackground(yellow);
            //beforeReser.setVisible(true);
            aPanel.setVisible(true);
        } else if (afterReserRadio.isSelected() == true) {  // 5시 이후
            reset();
            Reser_menuPanel.setVisible(true);
            menuReser.setBackground(yellow);
            afterReser.setVisible(true);

            jComboBox1.removeAllItems();  // 5시 이후 개인 학습 강의실 콤보박스 아이템 초기화
            jComboBox8.removeAllItems();  // 5시 이후 팀 학습 강의실 콤보박스 아이템 초기화
        }
        // else 아무것도 선택하지 않고 버튼 눌렀을 경우 
    }//GEN-LAST:event_reserNextButtActionPerformed

    // 예약 메뉴바 - 실습실 이용 및 상황 조회 선택 시
    private void menuCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCheckMouseClicked
        reset();
        CheckPanel.setVisible(true);
        Reser_menuPanel.setVisible(true);

        menuCheck.setBackground(yellow);
        
        jTextField10.setText("");
        jTextField11.setText("");
        jTextField12.setText("");
        jTextField13.setText("");
        jTextField14.setText("");

        String continueTime = null;  // 연장 시간
        continueCombo.removeAllItems();  // 연장가능시간 콤보박스 아이템 초기화

        LocalDate nowDate = LocalDate.now();  // 현재 날짜 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");  // "yyyy/MM/dd"형식으로 포맷 정의
        String formatedNow = nowDate.format(formatter);  // 포맷 적용

        LocalTime nowTime = LocalTime.now();  // 현재 시간
        int hour = nowTime.getHour();  // 시 구하기

        connect();  // DB 연결

        try {
            // 현재 시간을 기준으로 사용할 수 있는 예약 찾기
            sql = "select reserId, labId, seatId, startTimeR, endTimeR, useCheck from reservation where sId = ? and dateR = ? and (startTimeR <= ? and endTimeR > ?) and reserPermission = 1";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, loginId);  // 학생 아이디
            pstmt.setString(2, formatedNow);  // 오늘 날짜
            //pstmt.setString(2, "2022/11/20");  // 오늘 날짜
            pstmt.setString(3, Integer.toString(hour));  // 현재 시간의 '시'
            pstmt.setString(4, Integer.toString(hour));  // 현재 시간의 '시'
            //pstmt.setString(3, "13");  // 현재 시간의 '시'
            //pstmt.setString(4, "13");  // 현재 시간의 '시'

            rs = pstmt.executeQuery();

            if (rs.next()) {  // 현재 사용할 수 있는 실습실 번호, 좌석번호, 시작시간, 종료시간, 연장가능시간 출력
                jTextField10.setText(rs.getString(2));  // 실습실 번호
                jTextField11.setText(rs.getString(3));  // 좌석 번호
                jTextField12.setText(rs.getString(4) + " : 00");  // 시작시간
                jTextField13.setText(rs.getString(5) + " : 00");  // 종료시간

                reservation = new Reservation(formatedNow, rs.getString(2), rs.getString(4), rs.getString(5), Integer.parseInt(rs.getString(3)));
                //reservation = new Reservation("2022/11/20", rs.getString(2), rs.getString(4), rs.getString(5), Integer.parseInt(rs.getString(3)));

                if (rs.getString(6).equals("1")) {  // 사용여부가 1이면 
                    startButt.setEnabled(false);  // 사용 시작 버튼 비활성화
                }

                // 같은 실습실, 좌석, 날짜에 대해서 조회해서 연장 가능 시간 구하기
                // 예약 중 가장 빠른 startTimeR까지 연장 가능
                sql = "select startTimeR from reservation where labId = ? and seatId = ? and dateR = ? and (? <= startTimeR) order by startTimeR ";

                pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, reservation.labId);  // 실습실 번호
                pstmt.setInt(2, reservation.seatId);  // 좌석번호
                pstmt.setString(3, reservation.dateR);  // 날짜
                pstmt.setString(4, reservation.endTimeR);  // 종료시간

                rs = pstmt.executeQuery();

                if (rs.next()) {
                    continueCombo.setEnabled(true);  // 연장 가능 시간 콤보박스 활성화
                    continueTime = rs.getString(1);  // 최대 연장 가능 시간 값 저장
                    System.out.println("continueTime : " + continueTime);

                    if (rs.getString(1).equals(reservation.endTimeR)) {  // 다음 예약이 없을 경우

                        if (Integer.parseInt(reservation.endTimeR) <= 17 && Integer.parseInt(reservation.endTimeR) >= 9) {
                            for (int i = Integer.parseInt(reservation.endTimeR) + 1; i <= 17; i++) {
                                continueCombo.addItem(Integer.toString(i) + " : 00");
                            }
                        } else if (Integer.parseInt(reservation.endTimeR) >= 17 || Integer.parseInt(reservation.endTimeR) <= 8) {
                            for (int i = Integer.parseInt(reservation.endTimeR) + 1; i <= 24; i++) {
                                continueCombo.addItem(Integer.toString(i) + " : 00");
                            }
                        }

                    } else { // 다음 예약이 있을 경우
                        // 종료시간부터 최대 연장 가능 시간값까지 콤보박스 아이템으로 추가
                        for (int i = Integer.parseInt(reservation.endTimeR) + 1; i <= Integer.parseInt(continueTime); i++) {
                            continueCombo.addItem(Integer.toString(i) + " : 00");
                        }
                    }
                } else {  // 연장 불가능하면
                    continueCombo.setEnabled(false);  // 연장 가능 시간 콤보박스 비활성화
                }

                /*
                else {  // 사용 여부가 0이면
                    startButt.setEnabled(true);  // 사용 시작 버튼 활성화
                }
                 */
            } else {
                JOptionPane.showMessageDialog(this, "현재 사용할 수 있는 예약이 존재하지 않습니다.");
                reset();
                mainPanel.setVisible(true);
            }

            // 해당 날짜, 강의실에 관리권한 가진 학생 이름 찾기
            sql = "select s.sName from student s, reservation r where r.authority = ? and r.dateR = ? and r.labId = ? and  ((r.startTimeR > ? and r.startTimeR < ?) or (r.startTimeR <= ? and r.endTimeR > ?))";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,1);  // 관리권한여부 1
            pstmt.setString(2, formatedNow);  //오늘날짜
            pstmt.setString(3, jTextField10.getText());  // 강의실
            pstmt.setString(4, jTextField12.getText());  // 시작시간
            pstmt.setString(5, jTextField13.getText());  // 종료시간
            pstmt.setString(6, jTextField12.getText());  // 시작시간
            pstmt.setString(7, jTextField12.getText());  // 시작시간
            rs = pstmt.executeQuery();
            if(rs.next()) {
                jTextField14.setText(rs.getString(1));  // 관리권한 학생 이름 
            } 
            

            
            
            
            
               
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException ex) {
            }
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException ex) {
            }
            if (conn != null) try {
                conn.close();
            } catch (SQLException ex) {
            }
        }
    }//GEN-LAST:event_menuCheckMouseClicked

    // 예약 메뉴바 - 본인 예약 현황 조회 및 취소 선택 시
    private void menuCancleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCancleMouseClicked
        reset();
        CanclePanel.setVisible(true);
        Reser_menuPanel.setVisible(true);

        menuCancle.setBackground(yellow);
    }//GEN-LAST:event_menuCancleMouseClicked

    // 5시 이후 실습실 예약 날짜. 시작시간. 종료시간 입력 받은 후 실습실 조회버튼
    private void checkButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkButtActionPerformed
        reset();
        Reser_menuPanel.setVisible(true);
        menuReser.setBackground(yellow);

        ArrayList<String> seminar = new ArrayList<>();  //  해당 날짜, 시작시간, 종료시간에 존재하는 세미나 동적 배열 
 
        connect(); //디비 연결

        String date = DATE_Text.getText();  // 날짜 값 받아오기

        String sTimeR = startTimeR.getSelectedItem().toString();  // 시작 시간 값 받아오기
        int sTimeRIndex = sTimeR.indexOf(":");
        String startTime = sTimeR.substring(0, sTimeRIndex);  // ':' 위치 이전까지 자르기

        String eTimeR = endTimeR.getSelectedItem().toString();  // 종료 시간 값 받아오기
        int eTimeRIndex = eTimeR.indexOf(":");
        String endTime = eTimeR.substring(0, eTimeRIndex);  // ':' 위치 이전까지 자르기

        System.out.println("date : " + date);
        System.out.println("startTime : " + startTime);
        System.out.println("endTime : " + endTime);

        // 예약 정보 저장 - 강의실과 좌석 번호 값 0으로 임의저장 
        reservation = new Reservation(date, "0", startTime, endTime, 0);

        try {

            // 세미나 강의실
            // 해당 날짜, 강의실, 시간에 세미나가 존재하면 예약 불가능
            sql = "select labId from seminar where dateS = ? and ((startTimeS > ? and startTimeS < ?) or (startTimeS <= ? and endTimeS > ?))";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, reservation.dateR);  // 날짜
            pstmt.setString(2, reservation.startTimeR);  // 시작시간
            pstmt.setString(3, reservation.endTimeR);  // 종료시간
            pstmt.setString(4, reservation.startTimeR);  // 시작시간
            pstmt.setString(5, reservation.startTimeR);  // 시작시간

            rs = pstmt.executeQuery();

            while (rs.next()) {  // 겹치는 세미나가 있으면 해당 강의실 번호 배열에 저장
                seminar.add(rs.getString(1));
            }
            for (int i = 0; i < seminar.size(); i++) {
                System.out.println(seminar.get(i));
            }

            // 해당 날짜, 시간에 강의실 별 예약 카운트
            sql = "select count(case when dateR = ? and labId =? and ((startTimeR > ? and startTimeR < ?) or (startTimeR <= ? and endTimeR > ?) ) then 1 end) as lab915Count, "
                    + "count(case when dateR = ? and labId =? and ((startTimeR > ? and startTimeR < ?) or (startTimeR <= ? and endTimeR > ?) ) then 1 end) as lab916Count, "
                    + "count(case when dateR = ? and labId =? and ((startTimeR > ? and startTimeR < ?) or (startTimeR <= ? and endTimeR > ?) ) then 1 end) as lab918Count, "
                    + "count(case when dateR = ? and labId =? and ((startTimeR > ? and startTimeR < ?) or (startTimeR <= ? and endTimeR > ?) ) then 1 end) as lab911Count from reservation";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, reservation.dateR);  // 날짜
            pstmt.setString(2, "915");  // 915 강의실
            pstmt.setString(3, reservation.startTimeR);  // 시작 시간
            pstmt.setString(4, reservation.endTimeR);  // 종료 시간
            pstmt.setString(5, reservation.startTimeR);  // 시작 시간
            pstmt.setString(6, reservation.startTimeR);  // 종료 시간

            pstmt.setString(7, reservation.dateR);  // 날짜
            pstmt.setString(8, "916");  // 916 강의실
            pstmt.setString(9, reservation.startTimeR);  // 시작 시간
            pstmt.setString(10, reservation.endTimeR);  // 종료 시간
            pstmt.setString(11, reservation.startTimeR);  // 시작 시간
            pstmt.setString(12, reservation.startTimeR);  // 시작 시간

            pstmt.setString(13, reservation.dateR);  // 날짜
            pstmt.setString(14, "918");  // 918 강의실
            pstmt.setString(15, reservation.startTimeR);  // 시작 시간
            pstmt.setString(16, reservation.endTimeR);  // 종료 시간
            pstmt.setString(17, reservation.startTimeR);  // 시작 시간
            pstmt.setString(18, reservation.startTimeR);  // 종료 시간

            pstmt.setString(19, reservation.dateR);  // 날짜
            pstmt.setString(20, "911");  // 911 강의실
            pstmt.setString(21, reservation.startTimeR);  // 시작 시간
            pstmt.setString(22, reservation.endTimeR);  // 종료 시간
            pstmt.setString(23, reservation.startTimeR);  // 시작 시간
            pstmt.setString(24, reservation.startTimeR);  // 종료 시간

            rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("lab915 reserCount : " + rs.getString(1));
                System.out.println("lab916 reserCount : " + rs.getString(2));
                System.out.println("lab918 reserCount : " + rs.getString(3));
                System.out.println("lab911 reserCount : " + rs.getString(4));

                // 915, 916, 918, 911 강의실 별 해당 시간에 대한 예약 인원 수 저장
                reser[0][1] = rs.getString(1);
                reser[1][1] = rs.getString(2);
                reser[2][1] = rs.getString(3);
                reser[3][1] = rs.getString(4);
            }

            // 세미나 있는 강의실 카운트 -1로 설정
            for (int i = 0; i < seminar.size(); i++) {
                if (seminar.get(i).equals(reser[i][0])) {
                    reser[i][1] = "-1";
                }
            }

            /*
            for (int i = 0; i < reser.length; i++) {
                for (int j = 0; j < reser[i].length; j++) {
                    System.out.println(reser[i][j]);
                }
            }
            */
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException ex) {
            }
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException ex) {
            }
            if (conn != null) try {
                conn.close();
            } catch (SQLException ex) {
            }
        }
 

        ArrayList<String> item = new ArrayList<>();  // 강의실 아이템

        for (int i = 0; i < 4; i++) {

            if ((!reser[i][1].equals("-1")) && Integer.parseInt(reser[i][1]) < 2) {  // if 현재 강의실에 학생 수 20명 미만이면 
                reset();
                Reser_menuPanel.setVisible(true);
                menuReser.setBackground(yellow);
                check.setVisible(true);  // 개인 예약 패널로 이동

                item.add(reser[i][0]);  // 세미나 예약이 없고 2명이하인 강의실 아이템
                break;
            } else if ((!reser[i][1].equals("-1")) && Integer.parseInt(reser[i][1]) >= 2) {  // else if 학생 수 20명 이상이면 (개인, 조별 학습 확인)
                reset();
                Reser_menuPanel.setVisible(true);
                menuReser.setBackground(yellow);
                OverReser.setVisible(true);  // 팀 학습 예약 패널로 이동

                return;
            }

        }

        jComboBox1.addItem(item.get(0));  // 강의실 추가
    }//GEN-LAST:event_checkButtActionPerformed

    // 예약 메뉴바 - 실습실 예약 선택 시 
    private void menuReserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuReserMouseClicked
        reset();
        Reser_menuPanel.setVisible(true);
        ReserPanel.setVisible(true);

        menuReser.setBackground(yellow);
    }//GEN-LAST:event_menuReserMouseClicked

    // (afterReser -> check) 강의실 선택 후 확인 버튼 클릭
    private void AfterCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AfterCheckButtActionPerformed
        // 개인 좌석 초기화
        for (int i = 0; i < 30; i++) {
            afterseatS.get(i).setEnabled(true);
        }

        afterSeatStatePanel.setVisible(true);

        String lab = jComboBox1.getSelectedItem().toString();   // 선택한 강의실 값 받아오기

        // 예약 정보 저장 - 좌석 번호 0으로 임의 저장
        reservation = new Reservation(reservation.dateR, lab, reservation.startTimeR, reservation.endTimeR, 0);

        connect();  // DB 연결

        try {
            // 예약 있는 좌석 찾기
            sql = "select seatId from reservation where dateR = ? and labId =? and ((startTimeR > ? and startTimeR < ?) or (startTimeR <= ? and endTimeR > ?))";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, reservation.dateR);  // 날짜
            pstmt.setString(2, lab);  // 강의실
            pstmt.setString(3, reservation.startTimeR);  // 시작시간
            pstmt.setString(4, reservation.endTimeR);  // 종료시간
            pstmt.setString(5, reservation.startTimeR);  // 시작시간
            pstmt.setString(6, reservation.startTimeR);  // 시작시간

            rs = pstmt.executeQuery();

            while (rs.next()) {
                //System.out.println("seatNum : " + rs.getString(1));

                // 예약 있는 자리 비활성화
                afterseatS.get(rs.getInt("seatId") - 1).setEnabled(false);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException ex) {
            }
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException ex) {
            }
            if (conn != null) try {
                conn.close();
            } catch (SQLException ex) {
            }
        }

    }//GEN-LAST:event_AfterCheckButtActionPerformed

    // 메인화면 - 회원정보 버튼
    private void userInformationButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userInformationButtActionPerformed
        reset();
        UserChangePanel.setVisible(true);
        UserInfo_menuPanel.setVisible(true);

        menuUserChange.setBackground(yellow);

        // 회원 정보 db에서 가져와서 UserChangePanel에 띄우기
    }//GEN-LAST:event_userInformationButtActionPerformed

    // 메인화면 - 실습실 버튼
    private void labButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labButtActionPerformed
        reset();
        LabCheckPanel.setVisible(true);
        Lab_menuPanel.setVisible(true);

        menuLabCheck.setBackground(yellow);
    }//GEN-LAST:event_labButtActionPerformed

    // 메인화면 - 신고관리 버튼
    private void manageButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageButtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_manageButtActionPerformed

    // 5시 이후 개인 좌석 선택 후 예약 버튼
    private void AfterReserButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AfterReserButtActionPerformed
        connect();  // DB 연결

        for (int i = 0; i < 30; i++) {

            if (afterseatS.get(i).isSelected()) {  // 선택된 좌석이 있으면

                // 예약 정보 저장
                reservation = new Reservation(reservation.dateR, reservation.labId, reservation.startTimeR, reservation.endTimeR, i + 1);

                try {
                    // 예약 정보 db에 저장
                    sql = "insert into reservation (sId, labId, seatId, dateR, startTimeR, endTimeR, reserPermission, authority, useCheck) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    pstmt = conn.prepareStatement(sql);

                    pstmt.setString(1, loginId);  // 아이디 
                    pstmt.setString(2, reservation.labId);  // 강의실
                    pstmt.setInt(3, reservation.seatId);  // 좌석
                    pstmt.setString(4, reservation.dateR);  // 날짜
                    pstmt.setString(5, reservation.startTimeR);  //시작 시간
                    pstmt.setString(6, reservation.endTimeR);  //종료 시간
                    pstmt.setInt(7, 0);  //예약 승인
                    pstmt.setInt(8, 0);  //권한 여부
                    pstmt.setInt(9, 0);  //사용 여부

                    pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(this, "예약이 완료되었습니다.");

                    // 메인화면으로 이동
                    reset();
                    mainPanel.setVisible(true);

                    // 라디오버튼 선택 초기화
                    buttonGroup2.clearSelection();

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                } finally {
                    if (rs != null) try {
                        rs.close();
                    } catch (SQLException ex) {
                    }
                    if (pstmt != null) try {
                        pstmt.close();
                    } catch (SQLException ex) {
                    }
                    if (conn != null) try {
                        conn.close();
                    } catch (SQLException ex) {
                    }
                }
            }
        }

    }//GEN-LAST:event_AfterReserButtActionPerformed

    //5시 이전 좌석 선택 후 예약 버튼
    private void BeforeReserButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BeforeReserButtActionPerformed
        connect(); //디비 연결
        
        // DB에 예약 저장
        for (int i = 0; i < 30; i++) {
            if (seat.get(i).isSelected()) {
                //디비에 예약 정보 저장
                try {
                    //데이터 삽입을 위한 sql문
                    sql = "insert into reservation (sId,labId,seatId,dateR,startTimeR,endTimeR,reserPermission,authority,useCheck) values (?,?,?,?,?,?,?,?,?)";
                    pstmt = conn.prepareStatement(sql); //디비 구문과 연결

                    //로그인 시 학생 정보 객체에 저장해서 아이디 가져와야 함
                    pstmt.setString(1, loginId);         //학생아이디 
                    pstmt.setString(2, reservation.labId);   //예약날짜
                    pstmt.setInt(3, i + 1);    //좌석번호
                    pstmt.setString(4, reservation.dateR);   //예약날짜
                    pstmt.setString(5, reservation.startTimeR); //시작시간
                    pstmt.setString(6, reservation.endTimeR); //종료시간
                    pstmt.setInt(7, 1);   //예약승인
                    pstmt.setInt(8, 0);   //권한여부
                    pstmt.setInt(9, 0);   //사용여부

                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "예약 완료되었습니다.", "Message", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                } finally {
                    if (rs != null) try {
                        rs.close();
                    } catch (SQLException ex) {
                    }
                    if (pstmt != null) try {
                        pstmt.close();
                    } catch (SQLException ex) {
                    }
                    if (conn != null) try {
                        conn.close();
                    } catch (SQLException ex) {
                    }
                }
            }
        }
        reset();
        mainPanel.setVisible(true);//메인 패널로 이동
    }//GEN-LAST:event_BeforeReserButtActionPerformed

    // 5시 이전 좌석 조회
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        connect(); //디비 연결
        resetRadio.setVisible(false); //라디오 버튼 하나 안보이게 설정
        resetRadio.setSelected(true); //안보이는 라디오 버튼 선택되도록
        //모든 좌석 활성화
        for (int i = 0; i < 30; i++) {
            seat.get(i).setEnabled(true);
        }

        String sText = jComboBox4.getSelectedItem().toString();   //시작 시간 가져와서 문자열로 변수에 저장
        int sNum = sText.indexOf(":");    //":"위치 저장
        String eText = jComboBox5.getSelectedItem().toString();   //종료 시간 가져와서 문자열로 변수에 저장
        int eNum = eText.indexOf(":");    //":" 위치 저장

        if (Integer.parseInt(sText.substring(0, sNum)) >= Integer.parseInt(eText.substring(0, eNum))) { //시작시간을 종료시간보다 늦게 설정했을 경우
            JOptionPane.showMessageDialog(this, "시작 시간을 종료 시간보다 빠르게 설정하세요.", "Message", JOptionPane.ERROR_MESSAGE);
            beforeReser.setVisible(true);
            beforeSeatStatePanel.setVisible(false); //좌석 안보이게 설정
        } else {
            //사용자에게 입력받은 정보를 저장하는 객체
            //아직 자리를 지정하지 않아서 자리번호는 0으로 설정
            reservation = new Reservation(jTextField1.getText(), jComboBox3.getSelectedItem().toString(), sText.substring(0, sNum), eText.substring(0, eNum), 0);

            try {
                //기존의 강의와 겹치는지 조회
                sql = "select * from lecture where day=? and labId=? and ((startTime >=? and startTime<?) or (endTime>? and endTime<=?) or (startTime>=? and endTime<=?))";
                pstmt = conn.prepareStatement(sql); //디비 구문과 연결

                pstmt.setInt(1, getDay(reservation));      //요일
                pstmt.setString(2, reservation.labId);      //실습실 번호
                pstmt.setString(3, reservation.startTimeR); //시작시간
                pstmt.setString(4, reservation.endTimeR);   //종료시간
                pstmt.setString(5, reservation.startTimeR); //시작시간
                pstmt.setString(6, reservation.endTimeR);   //종료시간
                pstmt.setString(7, reservation.startTimeR); //시작시간
                pstmt.setString(8, reservation.endTimeR);   //종료시간

                rs = pstmt.executeQuery();
                if (rs.next()) { //해당 예약 정보와 겹치는 강의가 존재한다면
                    //lecture=new Lecture(rs.getString("lectureName"));   //lecture 테이블에 lectureName 속성에서 값 가져오기 //값 안들어감.. 왜지
                    JOptionPane.showMessageDialog(this, rs.getString("lectureName") + " 강의 시간입니다.", "Message", JOptionPane.INFORMATION_MESSAGE);
                    beforeReser.setVisible(true);
                    beforeSeatStatePanel.setVisible(false); //좌석 안보이게 설정
                } else {
                    //기존의 세미나(혹은 특강)과 겹치는지 조회
                    sql = "select * from seminar where dateS=? and labId=? and ((startTimeS >=? and startTimeS<?) or (endTimeS>? and endTimeS<=?) or (startTimeS>=? and endTimeS<=?))";
                    pstmt = conn.prepareStatement(sql); //디비 구문과 연결

                    pstmt.setString(1, reservation.dateR);      //날짜
                    pstmt.setString(2, reservation.labId);      //실습실 번호
                    pstmt.setString(3, reservation.startTimeR); //시작시간
                    pstmt.setString(4, reservation.endTimeR);   //종료시간
                    pstmt.setString(5, reservation.startTimeR); //시작시간
                    pstmt.setString(6, reservation.endTimeR);   //종료시간
                    pstmt.setString(7, reservation.startTimeR); //시작시간
                    pstmt.setString(8, reservation.endTimeR);   //종료시간

                    rs = pstmt.executeQuery();
                    if (rs.next()) {    //해당 예약 정보와 겹치는 세미나가 존재한다면
                        //seminar=new Seminar(rs.getString("seminarName"));   //seminar 테이블에 seminarName 속성에서 값 가져오기 //값 안들어감.. 왜지
                        JOptionPane.showMessageDialog(this, rs.getString("seminarName") + " 세미나(특강) 시간입니다.", "Message", JOptionPane.INFORMATION_MESSAGE);
                        beforeReser.setVisible(true);
                        beforeSeatStatePanel.setVisible(false); //좌석 안보이게 설정
                    } else {
                        //기존의 예약과 겹치는지 조회
                        sql = "select * from reservation where dateR=? and labId=? and ((startTimeR >=? and startTimeR<?) or (endTimeR>? and endTimeR<=?) or (startTimeR>=? and endTimeR<=?))";
                        pstmt = conn.prepareStatement(sql); //디비 구문과 연결

                        pstmt.setString(1, reservation.dateR);      //날짜
                        pstmt.setString(2, reservation.labId);      //실습실 번호
                        pstmt.setString(3, reservation.startTimeR);
                        pstmt.setString(4, reservation.endTimeR);
                        pstmt.setString(5, reservation.startTimeR); //시작시간
                        pstmt.setString(6, reservation.endTimeR);   //종료시간
                        pstmt.setString(7, reservation.startTimeR); //시작시간
                        pstmt.setString(8, reservation.endTimeR);   //종료시간

                        rs = pstmt.executeQuery();

                        boolean exist = true;

                        while (rs.next()) {
                            if (loginId.equals(rs.getString("sId"))) {
                                JOptionPane.showMessageDialog(this, "해당 시각에 이미 예약이 존재합니다.", "Message", JOptionPane.ERROR_MESSAGE);
                                beforeReser.setVisible(true);
                                beforeSeatStatePanel.setVisible(false); //좌석 안보이게 설정
                                exist = false;
                                break;
                            }
                            // 예약 중인 좌석이라면 라디오버튼 비활성화
                            seat.get(rs.getInt("seatId") - 1).setEnabled(false);
                        }
                        if (exist) {
                            // 좌석 출력
                            beforeSeatStatePanel.setVisible(true);
                        }
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } finally {
                if (rs != null) try {
                    rs.close();
                } catch (SQLException ex) {
                }
                if (pstmt != null) try {
                    pstmt.close();
                } catch (SQLException ex) {
                }
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    // 개인, 조별학습 선택받아서 확인 버튼
    private void teamCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teamCheckButtActionPerformed
        OverLabCheckPanel.setVisible(false);
        overSeatStatePanel.setVisible(false);

        // 강의실 아이템 어레이리스트
        ArrayList<String> item = new ArrayList<>();

        if (soloRadio.isSelected() == true) {  // 개인학습 선택
            reset();
            Reser_menuPanel.setVisible(true);
            menuReser.setBackground(yellow);

            check.setVisible(true);

            for (int i = 0; i < reser.length; i++) {
                for (int j = 0; j < reser[i].length; j++) {
                    if ((!reser[i][1].equals("-1")) && Integer.parseInt(reser[i][1]) < 6) {  // 세미나가 없고 30명 미만인 강의실
                        item.add(reser[i][0]);
                        break;
                    }
                }
            }
            jComboBox1.addItem(item.get(0));  // 강의실 추가
            
        } else if (teamRadio.isSelected() == true) {  // 조별학습 선택
            OverLabCheckPanel.setVisible(true);

            // OverLabCheckPanel의 사용 가능한 강의실 콤보 박스에 사용 가능한 실습실 값 아이템으로 넣기
            jComboBox8.removeAllItems();  // 5시 이후 팀 학습 강의실 콤보박스 아이템 초기화

            for (int i = 0; i < 4; i++) {

                if ((!reser[i][1].equals("-1")) && (Integer.parseInt(reser[i][1]) >= 2 && Integer.parseInt(reser[i][1]) < 6)) {  // 20명 이상 30명 미만 (현재, 다음 강의실 선택 할 수 있음)

                    if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i][1]) < 6) {  // 팀원수 + 현재 인원 수가 30명 미만
                        item.add(reser[i][0]);
                        jComboBox8.addItem(item.get(0));

                        if ((!reser[i + 1][1].equals("-1")) && Integer.parseInt(reser[i + 1][1]) < 2) {  //  다음 강의실 세미나 예약이 없고 20명 미만
                            item.add(reser[i + 1][0]);
                            jComboBox8.addItem(item.get(1));
                        } else if ((!reser[i + 1][1].equals("-1")) && (Integer.parseInt(reser[i + 1][1]) >= 2 && Integer.parseInt(reser[i + 1][1]) < 6)) {  // 다음 강의실이 20명이상 30명 미만
                            if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 1][1]) < 6) {  // 팀원 수 + 현재 인원수가 30명 미만
                                item.add(reser[i + 1][0]);
                                jComboBox8.addItem(item.get(1));
                                return;
                            } else {  // 팀원수 + 현재 인원수가 30명 이상
                                if ((!reser[i + 2][1].equals("-1")) && Integer.parseInt(reser[i + 2][1]) < 2) {  // 다음 강의실 세미나 예약이 없고 20명 미만
                                    item.add(reser[i + 2][0]);
                                    jComboBox8.addItem(item.get(1));
                                } else if ((!reser[i + 2][1].equals("-1")) && (Integer.parseInt(reser[i + 2][1]) >= 2 && Integer.parseInt(reser[i + 2][1]) < 6)) {  // 다음 강의실 세미나 예약 없고 20명 이상 30명 미만
                                    if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 2][1]) < 6) { // 팀원 수 + 현재 인원수가 30명 미만
                                        item.add(reser[i + 2][0]);
                                        jComboBox8.addItem(item.get(1));
                                    } else {  // 팀원수 + 현재 인원수가 30명 이상
                                        if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) < 2) { // 다음 강의실 세미나 예약이 없고 20명 미만
                                            item.add(reser[i + 3][0]);
                                            jComboBox8.addItem(item.get(1));
                                        } else if ((!reser[i + 3][1].equals("-1")) && (Integer.parseInt(reser[i + 3][1]) >= 2 && Integer.parseInt(reser[i + 3][1]) < 6)) {  // 다음 강의실 세미나 예약 없고 20명 이상 30명 미만
                                            if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 3][1]) < 6) {  // 팀원 수 + 현재 인원수가 30명 미만
                                                item.add(reser[i + 3][0]);
                                                jComboBox8.addItem(item.get(1));
                                            } else {  // 팀원수 + 현재 인원수가 30명 이상
                                                System.out.println("no labs");
                                            }
                                        } else if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) >= 6) {  // 다음 강의실 30명 이상
                                            System.out.println("no labs");
                                        }
                                    }
                                } else if ((!reser[i + 2][1].equals("-1")) && Integer.parseInt(reser[i + 2][1]) >= 6) {  // 다음 강의실 30명 이상
                                    if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) < 2) {
                                        item.add(reser[i + 3][0]);
                                        jComboBox8.addItem(item.get(1));
                                    } else if ((!reser[i + 3][1].equals("-1")) && (Integer.parseInt(reser[i + 3][1]) >= 2 && Integer.parseInt(reser[i + 3][1]) < 6)) {
                                        if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 3][1]) < 6) {
                                            item.add(reser[i + 3][0]);
                                            jComboBox8.addItem(item.get(1));
                                        } else {
                                            System.out.println("no labs");
                                        }
                                    } else if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) >= 6) {
                                        System.out.println("no labs");
                                    }
                                }
                            }
                        } else if ((!reser[i + 1][1].equals("-1")) && Integer.parseInt(reser[i + 1][1]) >= 6) {
                            if ((!reser[i + 2][1].equals("-1")) && Integer.parseInt(reser[i + 2][1]) < 2) {
                                item.add(reser[i + 2][0]);
                                jComboBox8.addItem(item.get(1));
                            } else if ((!reser[i + 2][1].equals("-1")) && (Integer.parseInt(reser[i + 2][1]) >= 2 && Integer.parseInt(reser[i + 2][1]) < 6)) {
                                if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 2][1]) < 6) {
                                    item.add(reser[i + 2][0]);
                                    jComboBox8.addItem(item.get(1));
                                } else {
                                    if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) < 2) {
                                        item.add(reser[i + 3][0]);
                                        jComboBox8.addItem(item.get(1));
                                    } else if ((!reser[i + 3][1].equals("-1")) && (Integer.parseInt(reser[i + 3][1]) >= 2 && Integer.parseInt(reser[i + 3][1]) < 6)) {
                                        if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 3][1]) < 6) {
                                            item.add(reser[i + 3][0]);
                                            jComboBox8.addItem(item.get(1));
                                        } else {
                                            System.out.println("no labs");
                                        }
                                    } else if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) >= 6) {
                                        System.out.println("no labs");
                                    }
                                }
                            } else if ((!reser[i + 2][1].equals("-1")) && Integer.parseInt(reser[i + 2][1]) >= 6) {
                                if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) < 2) {
                                    item.add(reser[i + 3][0]);
                                    jComboBox8.addItem(item.get(1));
                                } else if ((!reser[i + 3][1].equals("-1")) && (Integer.parseInt(reser[i + 3][1]) >= 2 && Integer.parseInt(reser[i + 3][1]) < 6)) {
                                    if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 3][1]) < 6) {
                                        item.add(reser[i + 3][0]);
                                        jComboBox8.addItem(item.get(1));
                                    } else {
                                        System.out.println("no labs");
                                    }
                                } else if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) >= 6) {
                                    System.out.println("no labs");
                                }
                            }
                        }

                    } else {  // 팀원수 + 현재 인원 수가 30명 이상
                        if ((!reser[i + 1][1].equals("-1")) && Integer.parseInt(reser[i + 1][1]) < 2) {
                            item.add(reser[i + 1][0]);
                            jComboBox8.addItem(item.get(0));
                        } else if ((!reser[i + 1][1].equals("-1")) && (Integer.parseInt(reser[i + 1][1]) >= 2 && Integer.parseInt(reser[i + 1][1]) < 6)) {
                            if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 1][1]) < 6) {
                                item.add(reser[i + 1][0]);
                                jComboBox8.addItem(item.get(0));

                                if ((!reser[i + 2][1].equals("-1")) && Integer.parseInt(reser[i + 2][1]) < 2) {
                                    item.add(reser[i + 2][0]);
                                    jComboBox8.addItem(item.get(1));
                                } else if ((!reser[i + 2][1].equals("-1")) && (Integer.parseInt(reser[i + 2][1]) >= 2 && Integer.parseInt(reser[i + 2][1]) < 6)) {
                                    if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 2][1]) < 6) {
                                        item.add(reser[i + 2][0]);
                                        jComboBox8.addItem(item.get(1));
                                    } else {
                                        if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) < 2) {
                                            item.add(reser[i + 3][0]);
                                            jComboBox8.addItem(item.get(1));
                                        } else if ((!reser[i + 3][1].equals("-1")) && (Integer.parseInt(reser[i + 3][1]) >= 2 && Integer.parseInt(reser[i + 3][1]) < 6)) {
                                            if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 3][1]) < 6) {
                                                item.add(reser[i + 3][0]);
                                                jComboBox8.addItem(item.get(1));
                                            } else {
                                                System.out.println("no labs");
                                            }
                                        } else if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) >= 6) {
                                            System.out.println("no labs");
                                        }
                                    }
                                } else if ((!reser[i + 2][1].equals("-1")) && Integer.parseInt(reser[i + 2][1]) >= 6) {
                                    if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) < 2) {
                                        item.add(reser[i + 3][0]);
                                        jComboBox8.addItem(item.get(1));
                                    } else if ((!reser[i + 3][1].equals("-1")) && (Integer.parseInt(reser[i + 3][1]) >= 2 && Integer.parseInt(reser[i + 3][1]) < 6)) {
                                        if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 3][1]) < 6) {
                                            item.add(reser[i + 3][0]);
                                            jComboBox8.addItem(item.get(1));
                                        } else {
                                            System.out.println("no labs");
                                        }
                                    } else if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) >= 6) {
                                        System.out.println("no labs");
                                    }
                                }

                            } else {
                                if ((!reser[i + 2][1].equals("-1")) && Integer.parseInt(reser[i + 2][1]) < 2) {
                                    item.add(reser[i + 2][0]);
                                    jComboBox8.addItem(item.get(0));
                                } else if ((!reser[i + 2][1].equals("-1")) && (Integer.parseInt(reser[i + 2][1]) >= 2 && Integer.parseInt(reser[i + 2][1]) < 6)) {
                                    if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 2][1]) < 6) {
                                        item.add(reser[i + 2][0]);
                                        jComboBox8.addItem(item.get(0));
                                        if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) < 2) {
                                            item.add(reser[i + 3][0]);
                                            jComboBox8.addItem(item.get(1));
                                        } else if ((!reser[i + 3][1].equals("-1")) && (Integer.parseInt(reser[i + 3][1]) >= 2 && Integer.parseInt(reser[i + 3][1]) < 6)) {
                                            if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 3][1]) < 6) {
                                                item.add(reser[i + 3][0]);
                                                jComboBox8.addItem(item.get(1));
                                            } else {
                                                System.out.println("no labs");
                                            }
                                        } else if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) >= 6) {
                                            System.out.println("no labs");
                                        }
                                    } else {
                                        if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) < 2) {
                                            item.add(reser[i + 3][0]);
                                            jComboBox8.addItem(item.get(0));
                                        } else if ((!reser[i + 3][1].equals("-1")) && (Integer.parseInt(reser[i + 3][1]) >= 2 && Integer.parseInt(reser[i + 3][1]) < 6)) {
                                            if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 3][1]) < 6) {
                                                item.add(reser[i + 3][0]);
                                                jComboBox8.addItem(item.get(0));
                                            } else {
                                                System.out.println("no labs");
                                            }
                                        } else if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) >= 6) {
                                            System.out.println("no labs");
                                        }
                                    }
                                } else if ((!reser[i + 2][1].equals("-1")) && Integer.parseInt(reser[i + 2][1]) >= 6) {
                                    if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) < 2) {
                                        item.add(reser[i + 3][0]);
                                        jComboBox8.addItem(item.get(0));
                                    } else if ((!reser[i + 3][1].equals("-1")) && (Integer.parseInt(reser[i + 3][1]) >= 2 && Integer.parseInt(reser[i + 3][1]) < 6)) {
                                        if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 3][1]) < 6) {
                                            item.add(reser[i + 3][0]);
                                            jComboBox8.addItem(item.get(0));
                                        } else {
                                            System.out.println("no labs");
                                        }
                                    } else if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) >= 6) {
                                        System.out.println("no labs");
                                    }
                                }
                            }
                        } else if ((!reser[i + 1][1].equals("-1")) && Integer.parseInt(reser[i + 1][1]) >= 6) {
                            if ((!reser[i + 2][1].equals("-1")) && Integer.parseInt(reser[i + 2][1]) < 2) {
                                item.add(reser[i + 2][0]);
                                jComboBox8.addItem(item.get(0));
                            } else if ((!reser[i + 2][1].equals("-1")) && (Integer.parseInt(reser[i + 2][1]) >= 2 && Integer.parseInt(reser[i + 2][1]) < 6)) {
                                if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 2][1]) < 6) {
                                    item.add(reser[i + 2][0]);
                                    jComboBox8.addItem(item.get(0));
                                    if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) < 2) {
                                        item.add(reser[i + 3][0]);
                                        jComboBox8.addItem(item.get(1));
                                    } else if ((!reser[i + 3][1].equals("-1")) && (Integer.parseInt(reser[i + 3][1]) >= 2 && Integer.parseInt(reser[i + 3][1]) < 6)) {
                                        if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 3][1]) < 6) {
                                            item.add(reser[i + 3][0]);
                                            jComboBox8.addItem(item.get(1));
                                        } else {
                                            System.out.println("no labs");
                                        }
                                    } else if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) >= 6) {
                                        System.out.println("no labs");
                                    }
                                } else {
                                    if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) < 2) {
                                        item.add(reser[i + 3][0]);
                                        jComboBox8.addItem(item.get(0));
                                    } else if ((!reser[i + 3][1].equals("-1")) && (Integer.parseInt(reser[i + 3][1]) >= 2 && Integer.parseInt(reser[i + 3][1]) < 6)) {
                                        if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 3][1]) < 6) {
                                            item.add(reser[i + 3][0]);
                                            jComboBox8.addItem(item.get(0));
                                        } else {
                                            System.out.println("no labs");
                                        }
                                    } else if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) >= 6) {
                                        System.out.println("no labs");
                                    }
                                }
                            } else if ((!reser[i + 2][1].equals("-1")) && Integer.parseInt(reser[i + 2][1]) >= 6) {
                                if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) < 2) {
                                    item.add(reser[i + 3][0]);
                                    jComboBox8.addItem(item.get(0));
                                } else if ((!reser[i + 3][1].equals("-1")) && (Integer.parseInt(reser[i + 3][1]) >= 2 && Integer.parseInt(reser[i + 3][1]) < 6)) {
                                    if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 3][1]) < 6) {
                                        item.add(reser[i + 3][0]);
                                        jComboBox8.addItem(item.get(0));
                                    } else {
                                        System.out.println("no labs");
                                    }
                                } else if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) >= 6) {
                                    System.out.println("no labs");
                                }
                            }
                        }
                    }

                    break;
                } else if ((!reser[i][1].equals("-1")) && (Integer.parseInt(reser[i][1]) >= 6)) {  // 30명 이상
                    if ((!reser[i + 1][1].equals("-1")) && Integer.parseInt(reser[i + 1][1]) < 2) {
                        item.add(reser[i + 1][0]);
                        jComboBox8.addItem(item.get(0));
                    } else if ((!reser[i + 1][1].equals("-1")) && (Integer.parseInt(reser[i + 1][1]) >= 2 && Integer.parseInt(reser[i + 1][1]) < 6)) {
                        if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 1][1]) < 6) {
                            item.add(reser[i + 1][0]);
                            jComboBox8.addItem(item.get(0));

                            if ((!reser[i + 2][1].equals("-1")) && Integer.parseInt(reser[i + 2][1]) < 2) {
                                item.add(reser[i + 2][0]);
                                jComboBox8.addItem(item.get(1));
                                
                                break;
                            } else if ((!reser[i + 2][1].equals("-1")) && (Integer.parseInt(reser[i + 2][1]) >= 2 && Integer.parseInt(reser[i + 2][1]) < 6)) {
                                if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 2][1]) < 6) {
                                    item.add(reser[i + 2][0]);
                                    jComboBox8.addItem(item.get(1));
                                } else {
                                    if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) < 2) {
                                        item.add(reser[i + 3][0]);
                                        jComboBox8.addItem(item.get(1));
                                    } else if ((!reser[i + 3][1].equals("-1")) && (Integer.parseInt(reser[i + 3][1]) >= 2 && Integer.parseInt(reser[i + 3][1]) < 6)) {
                                        if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 3][1]) < 6) {
                                            item.add(reser[i + 3][0]);
                                            jComboBox8.addItem(item.get(1));
                                        } else {
                                            System.out.println("no labs");
                                        }
                                    } else if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) >= 6) {
                                        System.out.println("no labs");
                                    }
                                }
                            } else if ((!reser[i + 2][1].equals("-1")) && Integer.parseInt(reser[i + 2][1]) >= 6) {
                                if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) < 2) {
                                    item.add(reser[i + 3][0]);
                                    jComboBox8.addItem(item.get(1));
                                } else if ((!reser[i + 3][1].equals("-1")) && (Integer.parseInt(reser[i + 3][1]) >= 2 && Integer.parseInt(reser[i + 3][1]) < 6)) {
                                    if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 3][1]) < 6) {
                                        item.add(reser[i + 3][0]);
                                        jComboBox8.addItem(item.get(1));
                                    } else {
                                        System.out.println("no labs");
                                    }
                                } else if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) >= 6) {
                                    System.out.println("no labs");
                                }
                            }

                        } else {
                            if ((!reser[i + 2][1].equals("-1")) && Integer.parseInt(reser[i + 2][1]) < 2) {
                                item.add(reser[i + 2][0]);
                                jComboBox8.addItem(item.get(0));
                                break;
                            } else if ((!reser[i + 2][1].equals("-1")) && (Integer.parseInt(reser[i + 2][1]) >= 2 && Integer.parseInt(reser[i + 2][1]) < 6)) {
                                if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 2][1]) < 6) {
                                    item.add(reser[i + 2][0]);
                                    jComboBox8.addItem(item.get(0));

                                    if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) < 2) {
                                        item.add(reser[i + 3][0]);
                                        jComboBox8.addItem(item.get(1));
                                    } else if ((!reser[i + 3][1].equals("-1")) && (Integer.parseInt(reser[i + 3][1]) >= 2 && Integer.parseInt(reser[i + 3][1]) < 6)) {
                                        if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 3][1]) < 6) {
                                            item.add(reser[i + 3][0]);
                                            jComboBox8.addItem(item.get(1));
                                        } else {
                                            System.out.println("no labs");
                                        }
                                    } else if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) >= 6) {
                                        System.out.println("no labs");
                                    }

                                } else {
                                    if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) < 2) {
                                        item.add(reser[i + 3][0]);
                                        jComboBox8.addItem(item.get(0));
                                    } else if ((!reser[i + 3][1].equals("-1")) && (Integer.parseInt(reser[i + 3][1]) >= 2 && Integer.parseInt(reser[i + 3][1]) < 6)) {
                                        if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 3][1]) < 6) {
                                            item.add(reser[i + 3][0]);
                                            jComboBox8.addItem(item.get(0));
                                        } else {
                                            System.out.println("no labs");
                                        }
                                    } else if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) >= 6) {
                                        System.out.println("no labs");
                                    }
                                }
                            } else if ((!reser[i + 2][1].equals("-1")) && Integer.parseInt(reser[i + 2][1]) >= 6) {
                                if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) < 2) {
                                    item.add(reser[i + 3][0]);
                                    jComboBox8.addItem(item.get(0));
                                } else if ((!reser[i + 3][1].equals("-1")) && (Integer.parseInt(reser[i + 3][1]) >= 2 && Integer.parseInt(reser[i + 3][1]) < 6)) {
                                    if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 3][1]) < 6) {
                                        item.add(reser[i + 3][0]);
                                        jComboBox8.addItem(item.get(0));
                                    } else {
                                        System.out.println("no labs");
                                    }
                                } else if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) >= 6) {
                                    System.out.println("no labs");
                                }
                            }
                        }
                    } else if ((!reser[i + 1][1].equals("-1")) && Integer.parseInt(reser[i + 1][1]) >= 6) {
                        if ((!reser[i + 2][1].equals("-1")) && Integer.parseInt(reser[i + 2][1]) < 2) {
                            item.add(reser[i + 3][0]);
                            jComboBox8.addItem(item.get(0));
                        } else if ((!reser[i + 2][1].equals("-1")) && (Integer.parseInt(reser[i + 2][1]) >= 2 && Integer.parseInt(reser[i + 2][1]) < 6)) {
                            if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 2][1]) < 6) {
                                item.add(reser[i + 2][0]);
                                jComboBox8.addItem(item.get(0));

                                if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) < 2) {
                                    item.add(reser[i + 3][0]);
                                    jComboBox8.addItem(item.get(1));
                                } else if ((!reser[i + 3][1].equals("-1")) && (Integer.parseInt(reser[i + 3][1]) >= 2 && Integer.parseInt(reser[i + 3][1]) < 6)) {
                                    if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 3][1]) < 6) {
                                        item.add(reser[i + 3][0]);
                                        jComboBox8.addItem(item.get(1));
                                    } else {
                                        System.out.println("no labs");
                                    }
                                } else if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) >= 6) {
                                    System.out.println("no labs");
                                }

                            } else {
                                if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) < 2) {
                                    item.add(reser[i + 3][0]);
                                    jComboBox8.addItem(item.get(0));
                                } else if ((!reser[i + 3][1].equals("-1")) && (Integer.parseInt(reser[i + 3][1]) >= 2 && Integer.parseInt(reser[i + 3][1]) < 6)) {
                                    if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 3][1]) < 6) {
                                        item.add(reser[i + 3][0]);
                                        jComboBox8.addItem(item.get(0));
                                    } else {
                                        System.out.println("no labs");
                                    }
                                } else if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) >= 6) {
                                    System.out.println("no labs");
                                }
                            }
                        } else if ((!reser[i + 2][1].equals("-1")) && Integer.parseInt(reser[i + 2][1]) >= 6) {
                            if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) < 2) {
                                item.add(reser[i + 3][0]);
                                jComboBox8.addItem(item.get(0));
                            } else if ((!reser[i + 3][1].equals("-1")) && (Integer.parseInt(reser[i + 3][1]) >= 2 && Integer.parseInt(reser[i + 3][1]) < 6)) {
                                if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(reser[i + 3][1]) < 6) {
                                    item.add(reser[i + 3][0]);
                                    jComboBox8.addItem(item.get(0));
                                } else {
                                    System.out.println("no labs");
                                }
                            } else if ((!reser[i + 3][1].equals("-1")) && Integer.parseInt(reser[i + 3][1]) >= 6) {
                                System.out.println("no labs");
                            }
                        }

                    }

                }
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "학습 유형을 선택해주세요.");
        }

        /*
        // 개인 학습 선택된 경우
        if (soloRadio.isSelected() == true) {
            reset();
            Reser_menuPanel.setVisible(true);
            menuReser.setBackground(yellow);

            check.setVisible(true);

            // check의 콤보박스에 사용 가능한 실습실 값 아이템으로 넣기
            if (Integer.parseInt(lab915Num) < 6) {  // 915 - 30명 미만이면 915 추가
                jComboBox1.addItem("915");
            } else if (Integer.parseInt(lab915Num) >= 6) {  // 915 - 30명 이상이면 다음 강의실(916) 확인
                if (Integer.parseInt(lab916Num) < 6) {  //916 - 30명 미만이면 916 추가
                    jComboBox1.addItem("916");
                } else if (Integer.parseInt(lab916Num) >= 6) {  // 916 - 30명 이상이면 다음 강의실(918) 확인
                    if (Integer.parseInt(lab918Num) < 6) {  // 918 - 30명 미만이면 918 추가
                        jComboBox1.addItem("918");
                    } else if (Integer.parseInt(lab918Num) >= 6) {  // 918 - 30명 이상이면 다음 강의실(911) 확인
                        if (Integer.parseInt(lab911Num) < 6) {  // 911 - 30명 미만이면 911 추가
                            jComboBox1.addItem("911");
                        } else if (Integer.parseInt(lab911Num) >= 6) {  // 911 - 30명 이상이면 다음 강의실 없음
                            System.out.println("no lab");
                        }
                    }
                }
            }

            // 조별학습 선택된 경우
        } else if (teamRadio.isSelected() == true) {
            OverLabCheckPanel.setVisible(true);

            // OverLabCheckPanel의 사용 가능한 강의실 콤보 박스에 사용 가능한 실습실 값 아이템으로 넣기
            jComboBox8.removeAllItems();  // 5시 이후 팀 학습 강의실 콤보박스 아이템 초기화

            // 강의실 20명 이상 30명 미만이면 현재, 다음 강의실 선택 가능
            // 팀별 학습인 경우 팀원 수 + 강의실 인원 수가 30명 미만이면 현재 강의실 추가 / 30명 이상이면 다음 강의실
            // 현재 강의실이 20명 미만 -> 현재 강의실 추가
            // 현재 강의실이 20명 이상 30명 미만 -> 팀원 수 + 강의실 인원 수가 30명 미만인지 (30명 미만이면 현재 강의실 추가) / 30명 이상인지 (30명 이상이면 다음 강의실이 20명 미만인지 / 20명이상 30명미만인지 / 30명 이상인지) 상태 확인 
            // 현재 강의실이 30명 이상 -> 다음 강의실이 20명 미만인지 / 20명 이상 30명 미만인지 / 30명 이상 인지 상태 확인
            if (Integer.parseInt(lab915Num) >= 2 && Integer.parseInt(lab915Num) < 6) { // 915 - 20명 이상 30명 미만 (현재, 다음 강의실 선택 할 수 있음)

                if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab915Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 915 추가
                    jComboBox8.addItem("915");  // 현재 강의실

                    if (Integer.parseInt(lab916Num) < 2) {  // 다음 강의실 916 - 20명 미만
                        jComboBox8.addItem("916");
                    } else if (Integer.parseInt(lab916Num) >= 2 && Integer.parseInt(lab916Num) < 6) {  // 다음 강의실 916 - 20명 이상 30명 미만
                        if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab916Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 916 추가
                            jComboBox8.addItem("916");
                        } else {   // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실(918) 확인
                            if (Integer.parseInt(lab918Num) < 2) {  // 다음 강의실 918 - 20명 미만
                                jComboBox8.addItem("918");
                            } else if (Integer.parseInt(lab918Num) >= 2 && Integer.parseInt(lab918Num) < 6) {  // 다음 강의실 918 - 20명 이상 30명 미만
                                if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab918Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 918 추가
                                    jComboBox8.addItem("918");
                                } else {   // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실(911) 확인
                                    if (Integer.parseInt(lab911Num) < 2) {  // 다음 강의실 911 - 20명 미만
                                        jComboBox8.addItem("911");
                                    } else if (Integer.parseInt(lab911Num) >= 2 && Integer.parseInt(lab911Num) < 6) {  // 다음 강의실 911 - 20명 이상 30명 미만
                                        if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab911Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 911 추가
                                            jComboBox8.addItem("911");
                                        } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                            System.out.println("no labs");
                                        }
                                    } else if (Integer.parseInt(lab911Num) >= 6) {  // 다음 강의실 911 - 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                        System.out.println("no labs");
                                    }
                                }
                            } else if (Integer.parseInt(lab918Num) >= 6) {  // 다음 강의실 918 - 30명 이상
                                if (Integer.parseInt(lab911Num) < 2) {  // 다음 강의실 911 - 20명 미만
                                    jComboBox8.addItem("911");
                                } else if (Integer.parseInt(lab911Num) >= 2 && Integer.parseInt(lab911Num) < 6) {  // 다음 강의실 911 - 20명 이상 30명 미만
                                    if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab911Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 911 추가
                                        jComboBox8.addItem("911");
                                    } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                        System.out.println("no labs");
                                    }
                                } else if (Integer.parseInt(lab911Num) >= 6) {  // 다음 강의실 911 - 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                    System.out.println("no labs");
                                }
                            }
                        }
                    } else if (Integer.parseInt(lab916Num) >= 6) {  // 다음 강의실 916 - 30명 이상
                        if (Integer.parseInt(lab918Num) < 2) {  // 다음 강의실 918 - 20명 미만
                            jComboBox8.addItem("918");
                        } else if (Integer.parseInt(lab918Num) >= 2 && Integer.parseInt(lab918Num) < 6) {  // 다음 강의실 918 - 20명 이상 30명 미만
                            if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab918Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 918 추가
                                jComboBox8.addItem("918");
                            } else {   // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실(911) 확인
                                if (Integer.parseInt(lab911Num) < 2) {  // 다음 강의실 911 - 20명 미만
                                    jComboBox8.addItem("911");
                                } else if (Integer.parseInt(lab911Num) >= 2 && Integer.parseInt(lab911Num) < 6) {  // 다음 강의실 911 - 20명 이상 30명 미만 
                                    if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab911Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 911 추가
                                        jComboBox8.addItem("911");
                                    } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                        System.out.println("no labs");
                                    }
                                } else if (Integer.parseInt(lab911Num) >= 6) {  // 다음 강의실 911 - 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                    System.out.println("no labs");
                                }
                            }
                        }
                    }
                } else {  // 입력받은 팀원 수 + 현재 강의실 인원수가 30명 이상이면 다음 강의실(916) 확인
                    // 916이 20명 미만이면 916 추가, 20명 이상이면 30명 미만이면 916 추가와 다음 강의실(918) 확인, 30명 이상이면 다음 강의실(918)확인

                    if (Integer.parseInt(lab916Num) < 2) {  // 다음 강의실 916 - 20명 미만
                        jComboBox8.addItem("916");
                    } else if (Integer.parseInt(lab916Num) >= 2 && Integer.parseInt(lab916Num) < 6) {  // 다음 강의실 916 - 20명 이상 30명 미만 (현재, 다음 선택)

                        if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab916Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 916 추가
                            jComboBox8.addItem("916");
                            if (Integer.parseInt(lab918Num) < 2) {  // 다음 강의실 918 - 20명 미만
                                jComboBox8.addItem("918");
                            } else if (Integer.parseInt(lab918Num) >= 2 && Integer.parseInt(lab918Num) < 6) {  // 다음 강의실 918 - 20명 이상 30명 미만
                                if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab918Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 918 추가
                                    jComboBox8.addItem("918");
                                } else {
                                    if (Integer.parseInt(lab911Num) < 2) {  // 다음 강의실 911 - 20명 미만
                                        jComboBox8.addItem("911");
                                    } else if (Integer.parseInt(lab911Num) >= 2 && Integer.parseInt(lab911Num) < 6) {  // 다음 강의실 911 - 20명 이상 30명 미만 (현재, 다음 강의실 선택)
                                        if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab911Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 911 추가
                                            jComboBox8.addItem("911");
                                        } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                            System.out.println("add no labs");
                                        }
                                    } else if (Integer.parseInt(lab911Num) >= 6) {  // 다음 강의실 911 - 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                        System.out.println("add no labs");
                                    }
                                }
                            }

                        } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실(918) 확인
                            if (Integer.parseInt(lab918Num) < 2) {  // 다음 강의실 918 - 20명 미만
                                jComboBox8.addItem("918");
                            } else if (Integer.parseInt(lab918Num) >= 2 && Integer.parseInt(lab918Num) < 6) {  // 다음 강의실 918 - 20명 이상 30명 미만 (현재, 다음 선택)
                                if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab918Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 918 추가
                                    jComboBox8.addItem("918");
                                    if (Integer.parseInt(lab911Num) < 2) {  // 다음 강의실 911 - 20명 미만
                                        jComboBox8.addItem("911");
                                    } else if (Integer.parseInt(lab911Num) >= 2 && Integer.parseInt(lab911Num) < 6) {  // 다음 강의실 911 - 20명 이상 30명 미만 (현재, 다음 강의실 선택)
                                        if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab911Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 911 추가
                                            jComboBox8.addItem("911");
                                        } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                            System.out.println("no add labs");
                                        }
                                    } else if (Integer.parseInt(lab911Num) >= 6) {  // 다음 강의실 911 - 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                        System.out.println("no add labs");
                                    }
                                } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실(911) 확인
                                    if (Integer.parseInt(lab911Num) < 2) {  // 다음 강의실 911 - 20명 미만
                                        jComboBox8.addItem("911");
                                    } else if (Integer.parseInt(lab911Num) >= 2 && Integer.parseInt(lab911Num) < 6) {  // 다음 강의실 911 - 20명 이상 30명 미만 (현재, 다음 선택)
                                        if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab911Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 911 추가
                                            jComboBox8.addItem("911");
                                        } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                            JOptionPane.showMessageDialog(this, "빈 강의실이 없습니다.");
                                        }
                                    } else if (Integer.parseInt(lab911Num) >= 6) {  // 다음 강의실 911 - 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                        JOptionPane.showMessageDialog(this, "빈 강의실이 없습니다.");
                                    }
                                }
                            } else if (Integer.parseInt(lab918Num) >= 6) {  // 다음 강의실 918 - 30명 이상이면 다음 강의실 (911) 확인
                                if (Integer.parseInt(lab911Num) < 2) {  // 다음 강의실 911 - 20명 미만
                                    jComboBox8.addItem("911");
                                } else if (Integer.parseInt(lab911Num) >= 2 && Integer.parseInt(lab911Num) < 6) {  // 다음 강의실 911 - 20명 이상 30명 미만 (현재, 다음 강의실 선택)
                                    if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab911Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 911 추가
                                        jComboBox8.addItem("911");
                                    } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                        JOptionPane.showMessageDialog(this, "빈 강의실이 없습니다.");
                                    }
                                } else if (Integer.parseInt(lab911Num) >= 6) {  // 다음 강의실 911 - 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                    JOptionPane.showMessageDialog(this, "빈 강의실이 없습니다.");
                                }
                            }
                        }
                    } else if (Integer.parseInt(lab916Num) >= 6) {  // 다음 강의실 916 - 30명 이상 다음 강의실(918) 확인
                        if (Integer.parseInt(lab918Num) < 2) {  // 다음 강의실 918 - 20명 미만
                            jComboBox8.addItem("918");
                        } else if (Integer.parseInt(lab918Num) >= 2 && Integer.parseInt(lab918Num) < 6) {  // 다음 강의실 918- 20명 이상 30명 미만 (현재, 다음 강의실 선택)
                            if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab918Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 918 추가
                                jComboBox8.addItem("918");
                                if (Integer.parseInt(lab911Num) < 2) {  // 다음 강의실 911 - 20명 미만
                                    jComboBox8.addItem("911");
                                } else if (Integer.parseInt(lab911Num) >= 2 && Integer.parseInt(lab911Num) < 6) {  // 다음 강의실 911 - 20명 이상 30명 미만
                                    if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab911Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 911 추가
                                        jComboBox8.addItem("911");
                                    } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                        System.out.println("no add labs");
                                    }
                                } else if (Integer.parseInt(lab911Num) >= 6) {  // 다음 강의실 911 - 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                    System.out.println("no add labs");
                                }
                            } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 20명 이상이면 다음 강의실(911) 확인 
                                if (Integer.parseInt(lab911Num) < 2) {  // 다음 강의실 911 - 20명 미만
                                    jComboBox8.addItem("911");
                                } else if (Integer.parseInt(lab911Num) >= 2 && Integer.parseInt(lab911Num) < 6) {  // 다음 강의실 911 - 20명 이상 30명 미만 (현재, 다음 강의실 선택)
                                    if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab911Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 911 추가
                                        jComboBox8.addItem("911");
                                    } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                        JOptionPane.showMessageDialog(this, "빈 강의실이 없습니다.");
                                    }
                                } else if (Integer.parseInt(lab911Num) >= 6) {  // 다음 강의실 911 - 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                    JOptionPane.showMessageDialog(this, "빈 강의실이 없습니다.");
                                }
                            }
                        } else if (Integer.parseInt(lab918Num) >= 6) {  // 다음 강의실 918 - 30명 이상이면 다음 강의실 확인
                            if (Integer.parseInt(lab911Num) < 2) {  // 다음 강의실 911 - 20명 미만 
                                jComboBox8.addItem("911");
                            } else if (Integer.parseInt(lab911Num) >= 2 && Integer.parseInt(lab911Num) < 6) {  // 다음 강의실 20명 이상 30명 미만 (현재, 다음 강의실 선택)
                                if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab911Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 911 추가
                                    jComboBox8.addItem("911");
                                } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                    JOptionPane.showMessageDialog(this, "빈 강의실이 없습니다.");
                                }
                            } else if (Integer.parseInt(lab911Num) >= 6) {  // 다음 강의실 911 - 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                JOptionPane.showMessageDialog(this, "빈 강의실이 없습니다.");
                            }
                        }
                    }

                }

            } else if (Integer.parseInt(lab915Num) >= 6) { // 915 - 30명 이상 (다음 강의실 확인)

                if (Integer.parseInt(lab916Num) < 2) {  // 다음 강의실 916 - 20명 미만
                    jComboBox8.addItem("916");
                } else if (Integer.parseInt(lab916Num) >= 2 && Integer.parseInt(lab916Num) < 6) {  // 다음 강의실 916 - 20명 이상 30명 미만 (현재, 다음 강의실 선택)
                    if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab916Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 916 추가
                        jComboBox8.addItem("916");
                        if (Integer.parseInt(lab918Num) < 2) {  // 다음 강의실 918 - 20명 미만
                            jComboBox8.addItem("918");
                        } else if (Integer.parseInt(lab918Num) >= 2 && Integer.parseInt(lab918Num) < 6) {    // 다음 강의실 918 - 20명 이상 30명 미만
                            if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab918Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 918 추가
                                jComboBox8.addItem("918");
                            } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실(911) 확인
                                if (Integer.parseInt(lab911Num) < 2) {  // 다음 강의실 911 - 20명 미만
                                    jComboBox8.addItem("911");
                                } else if (Integer.parseInt(lab911Num) >= 2 && Integer.parseInt(lab911Num) < 6) {  // 다음 강의실 911 - 20명 이상 30명 미만 (현재, 다음 강의실 선택)
                                    if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab911Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 911 추가
                                        jComboBox8.addItem("911");
                                    } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                        System.out.println("add no labs");
                                    }
                                } else if (Integer.parseInt(lab911Num) >= 6) {  // 다음 강의실 911 - 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                    System.out.println("add no labs");
                                }
                            }
                        }
                    } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실(918) 확인
                        if (Integer.parseInt(lab918Num) < 2) {  // 다음 강의실 918 - 20명 미만
                            jComboBox8.addItem("918");
                        } else if (Integer.parseInt(lab918Num) >= 2 && Integer.parseInt(lab918Num) < 6) {  // 다음 강의실 20명 이상 30명 미만 (현재, 다음 강의실 선택)
                            if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab918Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 918 추가
                                jComboBox8.addItem("918");
                                if (Integer.parseInt(lab911Num) < 2) {  // 다음 강의실 911 - 20명 미만
                                    jComboBox8.addItem("911");
                                } else if (Integer.parseInt(lab911Num) >= 2 && Integer.parseInt(lab911Num) < 6) {  // 다음 강의실 911 - 20명 이상 30명 미만
                                    if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab911Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 911 추가
                                        jComboBox8.addItem("911");
                                    } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                        System.out.println("add no labs");
                                    }
                                } else if (Integer.parseInt(lab911Num) >= 6) {  // 다음 강의실 911 - 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                    System.out.println("add no labs");
                                }
                            } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실(911) 확인
                                if (Integer.parseInt(lab911Num) < 2) {  // 다음 강의실 911 - 20명 미만
                                    jComboBox8.addItem("911");
                                } else if (Integer.parseInt(lab911Num) >= 2 && Integer.parseInt(lab911Num) < 6) {  // 다음 강의실 911 - 20명 이상 30명 미만 (현재, 다음 강의실 선택)
                                    if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab911Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 911 추가
                                        jComboBox8.addItem("911");
                                    } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                        JOptionPane.showMessageDialog(this, "빈 강의실이 없습니다.");
                                    }
                                } else if (Integer.parseInt(lab911Num) >= 6) {  // 다음 강의실 911 - 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                    JOptionPane.showMessageDialog(this, "빈 강의실이 없습니다.");
                                }
                            }
                        } else if (Integer.parseInt(lab918Num) >= 6) {  // 다음 강의실 918 - 30명 이상이면 다음 강의실(911) 확인
                            if (Integer.parseInt(lab911Num) < 2) {  // 다음 강의실 911 - 20명 미만
                                jComboBox8.addItem("911");
                            } else if (Integer.parseInt(lab911Num) >= 2 && Integer.parseInt(lab911Num) < 6) {  // 다음 강의실 911 - 20명 이상 30명 미만 (현재, 다음 강의실 선택)
                                if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab911Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 911 추가
                                    jComboBox8.addItem("911");
                                } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                    JOptionPane.showMessageDialog(this, "빈 강의실이 없습니다.");
                                }
                            } else if (Integer.parseInt(lab911Num) >= 6) {  // 다음 강의실 911 - 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                JOptionPane.showMessageDialog(this, "빈 강의실이 없습니다.");
                            }
                        }
                    }
                } else if (Integer.parseInt(lab916Num) >= 6) {  // 다음 강의실 916 - 30명 이상이면 다음 강의실(918) 확인
                    if (Integer.parseInt(lab918Num) < 2) {  // 다음 강의실 918 - 20명 미만 
                        jComboBox8.addItem("918");
                    } else if (Integer.parseInt(lab918Num) >= 2 && Integer.parseInt(lab918Num) < 6) {  // 다음 강의실 918 - 20명 이상 30명 미만 (현재, 다음 강의실 선택)
                        if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab918Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 918 추가
                            jComboBox8.addItem("918");
                            if (Integer.parseInt(lab911Num) < 2) {  // 다음 강의실 911 - 20명 미만
                                jComboBox8.addItem("911");
                            } else if (Integer.parseInt(lab911Num) >= 2 && Integer.parseInt(lab911Num) < 6) {  // 다음 강의실 911 - 20명 이상 30명 미만 
                                if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab911Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 911 추가
                                    jComboBox8.addItem("911");
                                } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                    System.out.println("add no labs");
                                }
                            } else if (Integer.parseInt(lab911Num) >= 6) {  // 다음 강의실 911 - 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                System.out.println("add no labs");
                            }
                        } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실(911) 확인
                            if (Integer.parseInt(lab911Num) < 2) {  // 다음 강의실 911 - 20명 미만
                                jComboBox8.addItem("911");
                            } else if (Integer.parseInt(lab911Num) >= 2 && Integer.parseInt(lab911Num) < 6) {  // 다음 강의실 911 - 20명 이상 30명 미만 (현재, 다음 강의실 선택)
                                if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab911Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 911 추가
                                    jComboBox8.addItem("911");
                                } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                    JOptionPane.showMessageDialog(this, "빈 강의실이 없습니다.");
                                }
                            } else if (Integer.parseInt(lab911Num) >= 6) {  // 다음 강의실 911 - 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                JOptionPane.showMessageDialog(this, "빈 강의실이 없습니다.");
                            }
                        }
                    } else if (Integer.parseInt(lab918Num) >= 6) {  // 다음 강의실 918 - 30명 이상이면 다음 강의실(911) 확인
                        if (Integer.parseInt(lab911Num) < 2) {  // 다음 강의실 911 - 20명 미만
                            jComboBox8.addItem("911");
                        } else if (Integer.parseInt(lab911Num) >= 2 && Integer.parseInt(lab911Num) < 6) {  // 다음 강의실 911 - 20명 이상 30명 미만 (현재, 다음 강의실 선택)
                            if (Integer.parseInt((String) jComboBox6.getSelectedItem()) + Integer.parseInt(lab911Num) < 6) {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 미만이면 911 추가
                                jComboBox8.addItem("911");
                            } else {  // 입력받은 팀원 수 + 현재 강의실 인원 수가 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                                JOptionPane.showMessageDialog(this, "빈 강의실이 없습니다.");
                            }
                        } else if (Integer.parseInt(lab911Num) >= 6) {  // 다음 강의실 911 - 30명 이상이면 다음 강의실 확인 -> 911 다음 강의실 없음
                            JOptionPane.showMessageDialog(this, "빈 강의실이 없습니다.");
                        }
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "학습 유형을 선택해주세요.");
        }
         */
    }//GEN-LAST:event_teamCheckButtActionPerformed

    // 실습실 메뉴바 -  실습실 공지사항 및 규칙 조회 선택 시 
    private void menuLabNoticeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLabNoticeMouseClicked
        reset();
        Lab_menuPanel.setVisible(true);
        menuLabNotice.setBackground(yellow);
        LabNoticePanel.setVisible(true);
    }//GEN-LAST:event_menuLabNoticeMouseClicked

    // 실습실 메뉴바 -  실습실 시간표 조회 선택 시 
    private void menuTimeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTimeTableMouseClicked
        reset();
        Lab_menuPanel.setVisible(true);
        menuTimeTable.setBackground(yellow);
        LabTTCheckPanel.setVisible(true);
    }//GEN-LAST:event_menuTimeTableMouseClicked

    // 실습실 메뉴바 -  사용가능한 실습실 확인 선택 시 
    private void menuLabCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLabCheckMouseClicked
        reset();
        Lab_menuPanel.setVisible(true);
        menuLabCheck.setBackground(yellow);
        LabCheckPanel.setVisible(true);
    }//GEN-LAST:event_menuLabCheckMouseClicked

    // 실습실 시간표 조회 버튼
    private void TTCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TTCheckButtActionPerformed
        TT.setVisible(true);

        connect();

        // 테이블에 db에서 시간표 값 가져와서 띄우기
        DefaultTableModel table = (DefaultTableModel) jTable4.getModel();
        table.setNumRows(0);  // 테이블 초기화

        try {
            sql = "select l.day, l.lectureName, pName, l.startTime, l.endTime from lecture l, professor p where l.pId = p.pId and l.labId=? order by l.day";
            pstmt = conn.prepareStatement(sql); //디비 구문과 연결

            pstmt.setString(1, jComboBox7.getSelectedItem().toString());    //실습실

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Object data[] = {getDayString(Integer.parseInt(rs.getString(1))) + "요일", rs.getString(2), rs.getString(3), rs.getString(4) + " : 00", rs.getString(5) + " : 00"};
                table.addRow(data);  // 테이블에 값 추가 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException ex) {
            }
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException ex) {
            }
            if (conn != null) try {
                conn.close();
            } catch (SQLException ex) {
            }
        }
    }//GEN-LAST:event_TTCheckButtActionPerformed

    // 날짜, 시간 입력 후 사용 가능한 실습실 조회 버튼
    private void checkButt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkButt1ActionPerformed
        reset();
        connect();
        Lab_menuPanel.setVisible(true);
        menuLabCheck.setBackground(yellow);

        LabComboBox.removeAllItems();   //콤보박스 값 제거

        ArrayList<String> lab = new ArrayList<>();

        String sText = startTimeR1.getSelectedItem().toString();   //시작 시간 가져와서 문자열로 변수에 저장
        int sNum = sText.indexOf(":");    //":"위치 저장
        String eText = endTimeR1.getSelectedItem().toString();   //종료 시간 가져와서 문자열로 변수에 저장
        int eNum = eText.indexOf(":");    //":" 위치 저장

        if (Integer.parseInt(sText.substring(0, sNum)) >= Integer.parseInt(eText.substring(0, eNum))) { //시작시간을 종료시간보다 늦게 설정했을 경우
            JOptionPane.showMessageDialog(this, "시작 시간을 종료 시간보다 빠르게 설정하세요.", "Message", JOptionPane.ERROR_MESSAGE);
            LabCheckPanel.setVisible(true);
        } else {//정상적으로 입력했을 경우
            String yyyy = YYYY_Text1.getText();
            String mm = MM_Text1.getText();
            String dd = DD_Text1.getText();
            String date = yyyy + "/" + mm + "/" + dd;

            //사용자에게 입력받은 정보를 저장하는 객체
            //자리번호는 임의로 0으로 설정
            //실습실번호는 임의로 0으로 설정
            reservation = new Reservation(date, "0", sText.substring(0, sNum), eText.substring(0, eNum), 0);
            try {
                //강의가 있는지 조회
                sql = "select * from lecture where day=? and ((startTime >=? and startTime<?) or (endTime>? and endTime<=?) or (startTime>=? and endTime<=?))";
                pstmt = conn.prepareStatement(sql); //디비 구문과 연결

                pstmt.setInt(1, getDay(reservation));      //요일
                pstmt.setString(2, reservation.startTimeR); //시작시간
                pstmt.setString(3, reservation.endTimeR);   //종료시간
                pstmt.setString(4, reservation.startTimeR); //시작시간
                pstmt.setString(5, reservation.endTimeR);   //종료시간
                pstmt.setString(6, reservation.startTimeR); //시작시간
                pstmt.setString(7, reservation.endTimeR);   //종료시간

                rs = pstmt.executeQuery();
                while (rs.next()) { //해당 예약 정보와 겹치는 강의가 존재한다면
                    if (lab.contains(rs.getString("labId")) == false) { //실습실이 이미 리스트에 있다면 추가하지 않는다.
                        lab.add(rs.getString("labId"));
                    }
                } //해당 예약 정보와 겹치는 세미나(또는 특강)이 존재한다면

                sql = "select * from seminar where dateS=? and ((startTimeS >=? and startTimeS<?) or (endTimeS>? and endTimeS<=?) or (startTimeS>=? and endTimeS<=?))";
                pstmt = conn.prepareStatement(sql); //디비 구문과 연결

                pstmt.setString(1, reservation.dateR);      //날짜
                pstmt.setString(2, reservation.startTimeR); //시작시간
                pstmt.setString(3, reservation.endTimeR);   //종료시간
                pstmt.setString(4, reservation.startTimeR); //시작시간
                pstmt.setString(5, reservation.endTimeR);   //종료시간
                pstmt.setString(6, reservation.startTimeR); //시작시간
                pstmt.setString(7, reservation.endTimeR);   //종료시간

                rs = pstmt.executeQuery();
                while (rs.next()) {
                    if (lab.contains(rs.getString("labId")) == false) { //실습실이 이미 리스트에 있다면 추가하지 않는다.
                        lab.add(rs.getString("labId"));
                    }
                }
                sql = "select count(*),labId from reservation where dateR=? and ((startTimeR >=? and startTimeR<?) or (endTimeR>? and endTimeR<=?) or (startTimeR>=? and endTimeR<=?))"
                        + "group by labId having count(*)>=30";
                pstmt = conn.prepareStatement(sql); //디비 구문과 연결

                pstmt.setString(1, reservation.dateR);      //날짜
                pstmt.setString(2, reservation.startTimeR); //시작시간
                pstmt.setString(3, reservation.endTimeR);   //종료시간
                pstmt.setString(4, reservation.startTimeR); //시작시간
                pstmt.setString(5, reservation.endTimeR);   //종료시간
                pstmt.setString(6, reservation.startTimeR); //시작시간
                pstmt.setString(7, reservation.endTimeR);   //종료시간

                rs = pstmt.executeQuery();
                while (rs.next()) {
                    if (lab.contains(rs.getString(2)) == false) { //실습실이 이미 리스트에 있다면 추가하지 않는다.
                        lab.add(rs.getString(2));   //실습실번호 저장
                    }
                }
                if (lab.contains("911") == false) {
                    LabComboBox.addItem("911");
                }
                if (lab.contains("915") == false) {
                    LabComboBox.addItem("915");
                }
                if (lab.contains("916") == false) {
                    LabComboBox.addItem("916");
                }
                if (lab.contains("918") == false) {
                    LabComboBox.addItem("918");
                }

                if (lab.size() >= 4) {  //사용 가능한 실습실이 없다면
                    JOptionPane.showMessageDialog(this, "예약 가능한 실습실이 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
                    LabCheckPanel.setVisible(true);
                } else {  //사용 가능한 실습실이 있다면
                    LabStatusPanel.setVisible(true);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } finally {
                if (rs != null) try {
                    rs.close();
                } catch (SQLException ex) {
                }
                if (pstmt != null) try {
                    pstmt.close();
                } catch (SQLException ex) {
                }
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }

        // 날짜, 시간 입력 후 사용 가능한 실습실만 콤보박스 아이템으로 넣기 (LabStatusPanel)
    }//GEN-LAST:event_checkButt1ActionPerformed

    // 실습실 선택 후 확인 버튼 클릭
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        LabStatus.setVisible(true);
        connect();

        for (int i = 0; i < 30; i++) {
            seatInquire.get(i).setEnabled(true);
        }

        reservation.labId = LabComboBox.getSelectedItem().toString();
        try {
            sql = "select * from reservation where dateR=? and labId=? and ((startTimeR <=? and endTimeR>?) or (startTimeR<? and endTimeR>=?) or (startTimeR>=? and endTimeR<=?))";
            pstmt = conn.prepareStatement(sql); //디비 구문과 연결

            pstmt.setString(1, reservation.dateR);      //날짜
            pstmt.setString(2, reservation.labId);      //실습실 번호
            pstmt.setString(3, reservation.startTimeR); //시작시간
            pstmt.setString(4, reservation.endTimeR);   //종료시간
            pstmt.setString(5, reservation.startTimeR); //시작시간
            pstmt.setString(6, reservation.endTimeR);   //종료시간
            pstmt.setString(7, reservation.startTimeR); //시작시간
            pstmt.setString(8, reservation.endTimeR);   //종료시간

            rs = pstmt.executeQuery();
            while (rs.next()) {
                // 예약 중인 좌석이라면 라디오버튼 비활성화
                seatInquire.get(rs.getInt("seatId") - 1).setEnabled(false);
            }
            // 좌석 출력
            LabStatus.setVisible(true);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException ex) {
            }
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException ex) {
            }
            if (conn != null) try {
                conn.close();
            } catch (SQLException ex) {
            }
        }

        // 사용 가능한 불가능한 좌석 비활성화
    }//GEN-LAST:event_jButton10ActionPerformed

    // 회원정보 메뉴바 -  회원 정보 삭제 선택 시 
    private void menuUserDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuUserDeleteMouseClicked
        reset();
        UserInfo_menuPanel.setVisible(true);
        menuUserDelete.setBackground(yellow);
        UserDeletePanel.setVisible(true);
    }//GEN-LAST:event_menuUserDeleteMouseClicked

    // 회원정보 메뉴바 -  회원 정보 수정 선택 시 
    private void menuUserChangeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuUserChangeMouseClicked
        reset();
        UserInfo_menuPanel.setVisible(true);
        menuUserChange.setBackground(yellow);
        UserChangePanel.setVisible(true);

        // 회원 정보 db에서 가져와서 UserChangePanel에 띄우기
    }//GEN-LAST:event_menuUserChangeMouseClicked

    // 회원 정보 삭제 버튼
    private void UserDeleteButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserDeleteButtActionPerformed
        // db에서 회원 정보 삭제
    }//GEN-LAST:event_UserDeleteButtActionPerformed

    // 회원 정보 수정 버튼
    private void UserChangeButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserChangeButtActionPerformed
        // 입력된 값으로 db값 수정
    }//GEN-LAST:event_UserChangeButtActionPerformed

    // 본인 예약 상황 조회 버튼
    private void MyReserCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MyReserCheckButtActionPerformed
        DefaultTableModel table = (DefaultTableModel) jTable1.getModel();

        table.setNumRows(0);  // 테이블 초기화
        
        connect();  // 디비연결
        
        try {
            sql = "select r.dateR, r.labId, r.seatId, r.startTimeR, r.endTimeR, r.reserPermission, r.authority from reservation r, student s where r.sId = s.sId and r.sId = ? and r.labId = ? and r.dateR = ? order by r.startTimeR";
            pstmt = conn.prepareStatement(sql); //디비 구문과 연결

            pstmt.setString(1, loginId);      //아이디
            pstmt.setString(2, (String) jComboBox2.getSelectedItem());      //실습실 번호
            pstmt.setString(3, jTextField2.getText()); //날짜

            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Object data[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)};  // 값 저장
                table.addRow(data);  // 테이블에 값 추가 
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException ex) {
            }
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException ex) {
            }
            if (conn != null) try {
                conn.close();
            } catch (SQLException ex) {
            }
        }
    }//GEN-LAST:event_MyReserCheckButtActionPerformed

    // 본인 예약 취소 버튼
    private void MyReserCancleButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MyReserCancleButtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MyReserCancleButtActionPerformed

    // 예약 사용 시작 버튼
    private void startButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtActionPerformed
        connect();  // 디비 연결

        // 사용 시작 상태 디비에 저장 
        try {

            // 사용 여부 1로 수정
            sql = "update reservation set useCheck = ? where labId = ? and seatId = ? and dateR = ? and startTimeR =? and endTimeR =?";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, 1);  // 사용 시작 여부
            pstmt.setString(2, reservation.labId);  // 강의실
            pstmt.setInt(3, reservation.seatId);  // 좌석
            pstmt.setString(4, reservation.dateR);  // 날짜
            pstmt.setString(5, reservation.startTimeR);  //시작 시간
            pstmt.setString(6, reservation.endTimeR);  //종료 시간

            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "예약 사용 시작");

            startButt.setEnabled(false);  // 사용 시작 버튼 비활성화

            // 메인 패널로 이동
            reset();
            mainPanel.setVisible(true);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException ex) {
            }
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException ex) {
            }
            if (conn != null) try {
                conn.close();
            } catch (SQLException ex) {
            }
        }
    }//GEN-LAST:event_startButtActionPerformed

    //예약 퇴실 버튼
    private void endButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endButtActionPerformed
        if (startButt.isEnabled() == true) {  // 예약 사용 시작을 안했다면 
            JOptionPane.showMessageDialog(this, "예약 사용 시작 후 퇴실해주세요.");
        } else {
            connect();  // 디비 연결

            // 퇴실 상태 디비에 저장 
            try {
                // 사용 여부 0로 수정
                sql = "update reservation set useCheck = ? where labId = ? and seatId = ? and dateR = ? and startTimeR =? and endTimeR =?";

                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(1, 0);  // 사용 시작 여부
                pstmt.setString(2, reservation.labId);  // 강의실
                pstmt.setInt(3, reservation.seatId);  // 좌석
                pstmt.setString(4, reservation.dateR);  // 날짜
                pstmt.setString(5, reservation.startTimeR);  //시작 시간
                pstmt.setString(6, reservation.endTimeR);  //종료 시간

                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "퇴실완료");

                // 퇴실할 경우 다시 사용 시작 불가능이므로 사용 시작 버튼 및 퇴실 버튼 비활성화
                startButt.setEnabled(false);
                endButt.setEnabled(false);

                // 메인 패널로 이동
                reset();
                mainPanel.setVisible(true);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } finally {
                if (rs != null) try {
                    rs.close();
                } catch (SQLException ex) {
                }
                if (pstmt != null) try {
                    pstmt.close();
                } catch (SQLException ex) {
                }
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
    }//GEN-LAST:event_endButtActionPerformed

    // 좌석 선택후 예약 버튼 (5시 이후, 20명 이상, 팀별 학습)
    private void overReserButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_overReserButtActionPerformed
        // 예약 디비에 넣기

        connect();  // DB 연결

        for (int i = 0; i < 30; i++) {

            if (afterseatT.get(i).isSelected()) {  // 5시 이후, 20명 이상, 팀별 학습 -> 선택된 좌석이 있으면

                // 예약 정보 저장
                reservation = new Reservation(reservation.dateR, reservation.labId, reservation.startTimeR, reservation.endTimeR, i + 1);

                try {
                    // 예약 정보 db에 저장
                    sql = "insert into reservation (sId, labId, seatId, dateR, startTimeR, endTimeR, reserPermission, authority, useCheck) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    pstmt = conn.prepareStatement(sql);

                    pstmt.setString(1, loginId);  // 아이디 
                    pstmt.setString(2, reservation.labId);  // 강의실
                    pstmt.setInt(3, reservation.seatId);  // 좌석
                    pstmt.setString(4, reservation.dateR);  // 날짜
                    pstmt.setString(5, reservation.startTimeR);  //시작 시간
                    pstmt.setString(6, reservation.endTimeR);  //종료 시간
                    pstmt.setInt(7, 0);  //예약 승인
                    pstmt.setInt(8, 0);  //권한 여부
                    pstmt.setInt(9, 0);  //사용 여부

                    pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(this, "예약이 완료되었습니다.");

                    // 메인화면으로 이동
                    reset();
                    mainPanel.setVisible(true);

                    // 라디오버튼 선택 초기화
                    buttonGroup2.clearSelection();

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                } finally {
                    if (rs != null) try {
                        rs.close();
                    } catch (SQLException ex) {
                    }
                    if (pstmt != null) try {
                        pstmt.close();
                    } catch (SQLException ex) {
                    }
                    if (conn != null) try {
                        conn.close();
                    } catch (SQLException ex) {
                    }
                }
            }
        }

    }//GEN-LAST:event_overReserButtActionPerformed

    // 강의실 선택 후 좌석조회 버튼(5시 이후, 20명 이상, 팀별학습)
    private void overSeatCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_overSeatCheckButtActionPerformed

        // 선택한 강의실 값 가져오기
        String lab = (String) jComboBox8.getSelectedItem();

        if (jComboBox8.getSelectedIndex() == -1) {  // 강의실이 선택되지 않았을 경우
            JOptionPane.showMessageDialog(this, "강의실이 선택되지 않았습니다.");
        } else {  // 강의실이 선택된 경우

            // 팀 좌석 초기화
            for (int i = 0; i < 30; i++) {
                afterseatT.get(i).setEnabled(true);
            }

            overSeatStatePanel.setVisible(true);  // 좌석 상태 패널 띄우기

            // 예약 정보 저장 - 좌석 번호 0으로 임의 저장
            reservation = new Reservation(reservation.dateR, lab, reservation.startTimeR, reservation.endTimeR, 0);
            connect();  // DB 연결

            try {
                // 예약 있는 좌석 찾기
                sql = "select seatId from reservation where dateR = ? and labId = ? and ((startTimeR > ? and startTimeR < ?) or (startTimeR <= ? and endTimeR > ?))";

                pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, reservation.dateR);  // 날짜
                pstmt.setString(2, lab);  // 강의실
                pstmt.setString(3, reservation.startTimeR);  // 시작시간
                pstmt.setString(4, reservation.endTimeR);  // 종료시간
                pstmt.setString(5, reservation.startTimeR);  // 시작시간
                pstmt.setString(6, reservation.startTimeR);  // 시작시간

                rs = pstmt.executeQuery();

                while (rs.next()) {
                    System.out.println("seatNum : " + rs.getString(1));

                    // 예약 있는 자리 비활성화
                    afterseatT.get(rs.getInt("seatId") - 1).setEnabled(false);
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } finally {
                if (rs != null) try {
                    rs.close();
                } catch (SQLException ex) {
                }
                if (pstmt != null) try {
                    pstmt.close();
                } catch (SQLException ex) {
                }
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }

    }//GEN-LAST:event_overSeatCheckButtActionPerformed

    private void seat31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seat31ActionPerformed

    private void YYYY_Text1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YYYY_Text1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_YYYY_Text1ActionPerformed

    private void LabComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LabComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LabComboBoxActionPerformed

    private void startTimeR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startTimeR1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startTimeR1ActionPerformed

    // 5시 이전 조회 버튼
    private void beforeCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beforeCheckButtActionPerformed
        beforeTTPanel.setVisible(false);
        // 패널에 강의시간표 출력
        // 입력받은 날짜에 테투리 표시
        // 입력받은 날짜에 세미나 출력
        
        connect();
        setTimeTable();
        for(int i=0;i<63;i++){
            timeTable.get(i).setBackground(Color.LIGHT_GRAY);
            timeTable.get(i).removeAll();
        }
        
        //날짜, 실습실번호, 시작시간, 종료시간, 좌석번호
        reservation =new Reservation(jTextField17.getText(),jComboBox9.getSelectedItem().toString(),"0","0",0);
        try{
            sql="select * from lecture where labId=?";
            pstmt=conn.prepareStatement(sql);
            
            pstmt.setString(1, reservation.labId);  //실습실번호
            
            rs=pstmt.executeQuery();
            int sTime;
            int eTime;
            for(int i=0;i<9;i++){
                timeTable.get(getDay(reservation)+(i*7)-1).setBackground(Color.WHITE);
            }
            while(rs.next()){
                sTime=Integer.parseInt(rs.getString("startTime"));
                eTime=Integer.parseInt(rs.getString("endTime"));
                for(int i=9 ; i<=16 ; i++){ //1교시부터 8교시까지
                    for(int k=1 ; k<=7 ; k++){  //월요일부터 금요일까지 
                        if(rs.getInt("day")==k && sTime<=i && i<eTime){
                            timeTable.get(((((i-8)-1)*7)+k)-1).setBackground(Color.yellow); //강의가 있는 시간 색상 변경
                            timeTable.get(((((i-8)-1)*7)+k)-1).add(new JLabel(rs.getString("lectureName"), JLabel.CENTER));
                        }
                    }
                }
            }
            
            sql="select * from seminar where labId=? and dateS=?";
            pstmt=conn.prepareStatement(sql);
            
            pstmt.setString(1, reservation.labId);
            pstmt.setString(2, reservation.dateR);
            
            rs=pstmt.executeQuery();
            int k=getDay(reservation);
            while(rs.next()){
                sTime=Integer.parseInt(rs.getString("startTimeS"));
                eTime=Integer.parseInt(rs.getString("endTimeS"));
                for(int i=9;i<=16;i++){//1교시부터 8교시까지
                    if(sTime<=i && i<eTime){
                        timeTable.get(((((i-8)-1)*7)+k)-1).setBackground(Color.ORANGE);
                        timeTable.get(((((i-8)-1)*7)+k)-1).add(new JLabel(rs.getString("seminarName"), JLabel.CENTER));
                    }
                }   
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException ex) {
            }
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException ex) {
            }
            if (conn != null) try {
                conn.close();
            } catch (SQLException ex) {
            }
        }
        beforeTTPanel.setVisible(true);
    }//GEN-LAST:event_beforeCheckButtActionPerformed

    // 5시 이전 좌석 조회 버튼
    private void beforeSeatCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beforeSeatCheckButtActionPerformed
        reset();
        connect();
        Reser_menuPanel.setVisible(true);
        menuReser.setBackground(yellow);
        beforeSeatStatePanel.setVisible(true);
        
        resetRadio.setVisible(false);   //안보이게 설정
        resetRadio.setSelected(true);
        
        jTextField15.setText(reservation.dateR);
        jTextField16.setText(reservation.labId);
        jTextField19.setText(reservation.startTimeR+":00");
        jTextField20.setText(reservation.endTimeR+":00");
        
        //모든 좌석 활성화
        for (int i = 0; i < 30; i++) {
            seat.get(i).setEnabled(true);
        }
        
        try {
            //기존의 예약과 겹치는지 조회
            sql = "select * from reservation where dateR=? and ((startTimeR >=? and startTimeR<?) or (endTimeR>? and endTimeR<=?) or (startTimeR>=? and endTimeR<=?))";
            pstmt=conn.prepareStatement(sql); //디비 구문과 연결

            pstmt.setString(1, reservation.dateR);      //날짜
            pstmt.setString(2, reservation.startTimeR);
            pstmt.setString(3, reservation.endTimeR);
            pstmt.setString(4, reservation.startTimeR); //시작시간
            pstmt.setString(5, reservation.endTimeR);   //종료시간
            pstmt.setString(6, reservation.startTimeR); //시작시간
            pstmt.setString(7, reservation.endTimeR);   //종료시간

            rs=pstmt.executeQuery();

            boolean exist = true;
            while (rs.next()) {
                if (loginId.equals(rs.getString("sId"))) {
                    beforeSeatStatePanel.setVisible(false);
                    JOptionPane.showMessageDialog(this, "해당 시각에 이미 예약이 존재합니다.", "Message", JOptionPane.ERROR_MESSAGE);
                    aPanel.setVisible(true);
                    exist = false;
                    break;
                }
                // 예약 중인 좌석이라면 라디오버튼 비활성화
                seat.get(rs.getInt("seatId") - 1).setEnabled(false);
            }
            if (exist) {
                // 좌석 출력
                beforeSeatStatePanel.setVisible(true);
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (rs != null) try {
                rs.close();
                } catch (SQLException ex) {
                }
            if (pstmt != null) try {
                pstmt.close();
            } catch (SQLException ex) {
            }
            if (conn != null) try {
                conn.close();
            } catch (SQLException ex) {
            }
        }
    }//GEN-LAST:event_beforeSeatCheckButtActionPerformed

    private void seat36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat36ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seat36ActionPerformed
    //시간표 0번째 위치
    private void Mon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Mon1MouseClicked
        // TODO add your handling code here:
        //if(timeTable.get(0).getBackground().getRGB() == -16711936)
        if(timeTable.get(0).getBackground().getRGB() == -256){  //노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(0).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(0).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(0).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="9";
                reservation.endTimeR="10";
                timeTable.get(0).setBackground(new Color(206,251,201));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("10")){    //선택한 패널과 이어지는 경우
                    reservation.startTimeR="9";
                    timeTable.get(0).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("9") && reservation.endTimeR.equals("10")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(0).setBackground(Color.WHITE);
            }else{
                reservation.startTimeR="10";
                timeTable.get(0).setBackground(Color.WHITE);
            }
        }
    }//GEN-LAST:event_Mon1MouseClicked

    private void Tue1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tue1MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(1).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(1).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(1).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(1).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="9";
                reservation.endTimeR="10";
                timeTable.get(1).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("10")){    //선택한 패널과 이어지는 경우
                    reservation.startTimeR="9";
                    timeTable.get(1).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("9") && reservation.endTimeR.equals("10")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(1).setBackground(Color.WHITE);
            }else{
                reservation.startTimeR="10";
                timeTable.get(1).setBackground(Color.WHITE);
            }
        }
    }//GEN-LAST:event_Tue1MouseClicked

    private void Wed1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Wed1MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(2).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(2).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(2).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(2).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="9";
                reservation.endTimeR="10";
                timeTable.get(2).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("10")){    //선택한 패널과 이어지는 경우
                    reservation.startTimeR="9";
                    timeTable.get(2).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("9") && reservation.endTimeR.equals("10")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(2).setBackground(Color.WHITE);
            }else{
                reservation.startTimeR="10";
                timeTable.get(2).setBackground(Color.WHITE);
            }
        }
    }//GEN-LAST:event_Wed1MouseClicked

    private void Thu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Thu1MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(3).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(3).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(3).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(3).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="9";
                reservation.endTimeR="10";
                timeTable.get(3).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("10")){    //선택한 패널과 이어지는 경우
                    reservation.startTimeR="9";
                    timeTable.get(3).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("9") && reservation.endTimeR.equals("10")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(3).setBackground(Color.WHITE);
            }else{
                reservation.startTimeR="10";
                timeTable.get(3).setBackground(Color.WHITE);
            }
        }
    }//GEN-LAST:event_Thu1MouseClicked

    private void Fri1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Fri1MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(4).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(4).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(4).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(4).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="9";
                reservation.endTimeR="10";
                timeTable.get(4).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("10")){    //선택한 패널과 이어지는 경우
                    reservation.startTimeR="9";
                    timeTable.get(4).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("9") && reservation.endTimeR.equals("10")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(4).setBackground(Color.WHITE);
            }else{
                reservation.startTimeR="10";
                timeTable.get(4).setBackground(Color.WHITE);
            }
        }
    }//GEN-LAST:event_Fri1MouseClicked

    private void Sat1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Sat1MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(5).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(5).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(5).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(5).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="9";
                reservation.endTimeR="10";
                timeTable.get(5).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("10")){    //선택한 패널과 이어지는 경우
                    reservation.startTimeR="9";
                    timeTable.get(5).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("9") && reservation.endTimeR.equals("10")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(5).setBackground(Color.WHITE);
            }else{
                reservation.startTimeR="10";
                timeTable.get(5).setBackground(Color.WHITE);
            }
        }
    }//GEN-LAST:event_Sat1MouseClicked

    private void Sun1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Sun1MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(6).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(6).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(6).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(6).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="9";
                reservation.endTimeR="10";
                timeTable.get(6).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("10")){    //선택한 패널과 이어지는 경우
                    reservation.startTimeR="9";
                    timeTable.get(6).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("9") && reservation.endTimeR.equals("10")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(6).setBackground(Color.WHITE);
            }else{
                reservation.startTimeR="10";
                timeTable.get(6).setBackground(Color.WHITE);
            }
        }
    }//GEN-LAST:event_Sun1MouseClicked

    private void Mon2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Mon2MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(7).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(7).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(7).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(7).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="10";
                reservation.endTimeR="11";
                timeTable.get(7).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("11")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="10";
                    timeTable.get(7).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("10")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="11";
                    timeTable.get(7).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("10") && reservation.endTimeR.equals("11")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(7).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("10")){
                reservation.startTimeR="11";
                timeTable.get(7).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("11")){
                reservation.endTimeR="10";
                timeTable.get(7).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Mon2MouseClicked

    private void Tue2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tue2MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(8).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(8).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(8).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(8).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="10";
                reservation.endTimeR="11";
                timeTable.get(8).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("11")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="10";
                    timeTable.get(8).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("10")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="11";
                    timeTable.get(8).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("10") && reservation.endTimeR.equals("11")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(8).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("10")){
                reservation.startTimeR="11";
                timeTable.get(8).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("11")){
                reservation.endTimeR="10";
                timeTable.get(8).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Tue2MouseClicked

    private void Wed2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Wed2MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(9).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(9).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(9).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(9).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="10";
                reservation.endTimeR="11";
                timeTable.get(9).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("11")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="10";
                    timeTable.get(9).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("10")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="11";
                    timeTable.get(9).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("10") && reservation.endTimeR.equals("11")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(9).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("10")){
                reservation.startTimeR="11";
                timeTable.get(9).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("11")){
                reservation.endTimeR="10";
                timeTable.get(9).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Wed2MouseClicked

    private void Thu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Thu2MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(10).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(10).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(10).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(10).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="10";
                reservation.endTimeR="11";
                timeTable.get(10).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("11")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="10";
                    timeTable.get(10).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("10")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="11";
                    timeTable.get(10).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("10") && reservation.endTimeR.equals("11")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(10).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("10")){
                reservation.startTimeR="11";
                timeTable.get(10).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("11")){
                reservation.endTimeR="10";
                timeTable.get(10).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Thu2MouseClicked

    private void Fri2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Fri2MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(11).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(11).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(11).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(11).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="10";
                reservation.endTimeR="11";
                timeTable.get(11).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("11")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="10";
                    timeTable.get(11).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("10")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="11";
                    timeTable.get(11).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("10") && reservation.endTimeR.equals("11")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(11).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("10")){
                reservation.startTimeR="11";
                timeTable.get(11).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("11")){
                reservation.endTimeR="10";
                timeTable.get(11).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Fri2MouseClicked

    private void jPanel111MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel111MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(12).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(12).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(12).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(12).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="10";
                reservation.endTimeR="11";
                timeTable.get(12).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("11")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="10";
                    timeTable.get(12).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("10")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="11";
                    timeTable.get(12).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("10") && reservation.endTimeR.equals("11")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(12).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("10")){
                reservation.startTimeR="11";
                timeTable.get(12).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("11")){
                reservation.endTimeR="10";
                timeTable.get(12).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jPanel111MouseClicked

    private void jPanel102MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel102MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(13).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(13).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(13).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(13).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="10";
                reservation.endTimeR="11";
                timeTable.get(13).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("11")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="10";
                    timeTable.get(13).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("10")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="11";
                    timeTable.get(13).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("10") && reservation.endTimeR.equals("11")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(13).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("10")){
                reservation.startTimeR="11";
                timeTable.get(13).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("11")){
                reservation.endTimeR="10";
                timeTable.get(13).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jPanel102MouseClicked

    private void Mon3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Mon3MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(14).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(14).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(14).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(14).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="11";
                reservation.endTimeR="12";
                timeTable.get(14).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("12")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="11";
                    timeTable.get(14).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("11")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="12";
                    timeTable.get(14).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("11") && reservation.endTimeR.equals("12")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(14).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("11")){
                reservation.startTimeR="12";
                timeTable.get(14).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("12")){
                reservation.endTimeR="11";
                timeTable.get(14).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Mon3MouseClicked

    private void Tue3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tue3MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(15).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(15).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(15).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(15).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="11";
                reservation.endTimeR="12";
                timeTable.get(15).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("12")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="11";
                    timeTable.get(15).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("11")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="12";
                    timeTable.get(15).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("11") && reservation.endTimeR.equals("12")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(15).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("11")){
                reservation.startTimeR="12";
                timeTable.get(15).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("12")){
                reservation.endTimeR="11";
                timeTable.get(15).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Tue3MouseClicked

    private void Wed3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Wed3MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(16).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(16).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(16).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(16).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="11";
                reservation.endTimeR="12";
                timeTable.get(16).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("12")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="11";
                    timeTable.get(16).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("11")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="12";
                    timeTable.get(16).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("11") && reservation.endTimeR.equals("12")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(16).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("11")){
                reservation.startTimeR="12";
                timeTable.get(16).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("12")){
                reservation.endTimeR="11";
                timeTable.get(16).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Wed3MouseClicked

    private void Thu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Thu3MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(17).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(17).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(17).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(17).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="11";
                reservation.endTimeR="12";
                timeTable.get(17).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("12")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="11";
                    timeTable.get(17).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("11")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="12";
                    timeTable.get(17).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("11") && reservation.endTimeR.equals("12")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(17).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("11")){
                reservation.startTimeR="12";
                timeTable.get(17).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("12")){
                reservation.endTimeR="11";
                timeTable.get(17).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Thu3MouseClicked

    private void Fri3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Fri3MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(18).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(18).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(18).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(18).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="11";
                reservation.endTimeR="12";
                timeTable.get(18).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("12")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="11";
                    timeTable.get(18).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("11")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="12";
                    timeTable.get(18).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("11") && reservation.endTimeR.equals("12")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(18).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("11")){
                reservation.startTimeR="12";
                timeTable.get(18).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("12")){
                reservation.endTimeR="11";
                timeTable.get(18).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Fri3MouseClicked

    private void jPanel112MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel112MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(19).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(19).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(19).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(19).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="11";
                reservation.endTimeR="12";
                timeTable.get(19).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("12")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="11";
                    timeTable.get(19).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("11")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="12";
                    timeTable.get(19).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("11") && reservation.endTimeR.equals("12")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(19).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("11")){
                reservation.startTimeR="12";
                timeTable.get(19).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("12")){
                reservation.endTimeR="11";
                timeTable.get(19).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jPanel112MouseClicked

    private void jPanel103MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel103MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(20).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(20).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(20).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(20).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="11";
                reservation.endTimeR="12";
                timeTable.get(20).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("12")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="11";
                    timeTable.get(20).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("11")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="12";
                    timeTable.get(20).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("11") && reservation.endTimeR.equals("12")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(20).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("11")){
                reservation.startTimeR="12";
                timeTable.get(20).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("12")){
                reservation.endTimeR="11";
                timeTable.get(20).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jPanel103MouseClicked

    private void Mon4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Mon4MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(21).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(21).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(21).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(21).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="12";
                reservation.endTimeR="13";
                timeTable.get(21).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("13")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="12";
                    timeTable.get(21).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("12")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="13";
                    timeTable.get(21).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("12") && reservation.endTimeR.equals("13")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(21).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("12")){
                reservation.startTimeR="13";
                timeTable.get(21).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("13")){
                reservation.endTimeR="12";
                timeTable.get(21).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Mon4MouseClicked

    private void Tue4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tue4MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(22).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(22).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(22).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(22).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="12";
                reservation.endTimeR="13";
                timeTable.get(22).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("13")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="12";
                    timeTable.get(22).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("12")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="13";
                    timeTable.get(22).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("12") && reservation.endTimeR.equals("13")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(22).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("12")){
                reservation.startTimeR="13";
                timeTable.get(22).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("13")){
                reservation.endTimeR="12";
                timeTable.get(22).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Tue4MouseClicked

    private void Wed4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Wed4MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(23).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(23).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(23).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(23).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="12";
                reservation.endTimeR="13";
                timeTable.get(23).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("13")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="12";
                    timeTable.get(23).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("12")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="13";
                    timeTable.get(23).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("12") && reservation.endTimeR.equals("13")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(23).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("12")){
                reservation.startTimeR="13";
                timeTable.get(23).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("13")){
                reservation.endTimeR="12";
                timeTable.get(23).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Wed4MouseClicked

    private void Thu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Thu4MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(24).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(24).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(24).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(24).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="12";
                reservation.endTimeR="13";
                timeTable.get(24).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("13")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="12";
                    timeTable.get(24).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("12")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="13";
                    timeTable.get(24).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("12") && reservation.endTimeR.equals("13")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(24).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("12")){
                reservation.startTimeR="13";
                timeTable.get(24).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("13")){
                reservation.endTimeR="12";
                timeTable.get(24).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Thu4MouseClicked

    private void Fri4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Fri4MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(25).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(25).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(25).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(25).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="12";
                reservation.endTimeR="13";
                timeTable.get(25).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("13")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="12";
                    timeTable.get(25).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("12")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="13";
                    timeTable.get(25).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("12") && reservation.endTimeR.equals("13")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(25).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("12")){
                reservation.startTimeR="13";
                timeTable.get(25).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("13")){
                reservation.endTimeR="12";
                timeTable.get(25).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Fri4MouseClicked

    private void jPanel113MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel113MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(26).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(26).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(26).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(26).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="12";
                reservation.endTimeR="13";
                timeTable.get(26).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("13")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="12";
                    timeTable.get(26).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("12")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="13";
                    timeTable.get(26).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("12") && reservation.endTimeR.equals("13")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(26).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("12")){
                reservation.startTimeR="13";
                timeTable.get(26).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("13")){
                reservation.endTimeR="12";
                timeTable.get(26).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jPanel113MouseClicked

    private void jPanel104MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel104MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(27).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(27).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(27).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(27).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="12";
                reservation.endTimeR="13";
                timeTable.get(27).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("13")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="12";
                    timeTable.get(27).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("12")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="13";
                    timeTable.get(27).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("12") && reservation.endTimeR.equals("13")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(27).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("12")){
                reservation.startTimeR="13";
                timeTable.get(27).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("13")){
                reservation.endTimeR="12";
                timeTable.get(27).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jPanel104MouseClicked

    private void M5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_M5MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(28).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(28).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(28).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(28).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="13";
                reservation.endTimeR="14";
                timeTable.get(28).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("14")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="13";
                    timeTable.get(28).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("13")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="14";
                    timeTable.get(28).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("13") && reservation.endTimeR.equals("14")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(28).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("13")){
                reservation.startTimeR="14";
                timeTable.get(28).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("14")){
                reservation.endTimeR="13";
                timeTable.get(28).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_M5MouseClicked

    private void Tue5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tue5MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(29).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(29).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(29).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(29).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="13";
                reservation.endTimeR="14";
                timeTable.get(29).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("14")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="13";
                    timeTable.get(29).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("13")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="14";
                    timeTable.get(29).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("13") && reservation.endTimeR.equals("14")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(29).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("13")){
                reservation.startTimeR="14";
                timeTable.get(29).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("14")){
                reservation.endTimeR="13";
                timeTable.get(29).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Tue5MouseClicked

    private void Wed5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Wed5MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(30).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(30).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(30).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(30).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="13";
                reservation.endTimeR="14";
                timeTable.get(30).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("14")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="13";
                    timeTable.get(30).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("13")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="14";
                    timeTable.get(30).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("13") && reservation.endTimeR.equals("14")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(30).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("13")){
                reservation.startTimeR="14";
                timeTable.get(30).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("14")){
                reservation.endTimeR="13";
                timeTable.get(30).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Wed5MouseClicked

    private void Thu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Thu5MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(31).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(31).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(31).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(31).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="13";
                reservation.endTimeR="14";
                timeTable.get(31).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("14")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="13";
                    timeTable.get(31).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("13")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="14";
                    timeTable.get(31).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("13") && reservation.endTimeR.equals("14")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(31).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("13")){
                reservation.startTimeR="14";
                timeTable.get(31).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("14")){
                reservation.endTimeR="13";
                timeTable.get(31).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Thu5MouseClicked

    private void Fri5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Fri5MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(32).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(32).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(32).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(32).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="13";
                reservation.endTimeR="14";
                timeTable.get(32).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("14")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="13";
                    timeTable.get(32).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("13")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="14";
                    timeTable.get(32).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("13") && reservation.endTimeR.equals("14")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(32).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("13")){
                reservation.startTimeR="14";
                timeTable.get(32).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("14")){
                reservation.endTimeR="13";
                timeTable.get(32).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Fri5MouseClicked

    private void jPanel114MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel114MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(33).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(33).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(33).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(33).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="13";
                reservation.endTimeR="14";
                timeTable.get(33).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("14")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="13";
                    timeTable.get(33).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("13")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="14";
                    timeTable.get(33).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("13") && reservation.endTimeR.equals("14")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(33).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("13")){
                reservation.startTimeR="14";
                timeTable.get(33).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("14")){
                reservation.endTimeR="13";
                timeTable.get(33).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jPanel114MouseClicked

    private void jPanel105MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel105MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(34).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(34).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(34).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(34).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="13";
                reservation.endTimeR="14";
                timeTable.get(34).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("14")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="13";
                    timeTable.get(34).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("13")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="14";
                    timeTable.get(34).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("13") && reservation.endTimeR.equals("14")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(34).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("13")){
                reservation.startTimeR="14";
                timeTable.get(34).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("14")){
                reservation.endTimeR="13";
                timeTable.get(34).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jPanel105MouseClicked

    private void M6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_M6MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(35).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(35).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(35).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(35).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="14";
                reservation.endTimeR="15";
                timeTable.get(35).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("15")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="14";
                    timeTable.get(35).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("14")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="15";
                    timeTable.get(35).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("14") && reservation.endTimeR.equals("15")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(35).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("14")){
                reservation.startTimeR="15";
                timeTable.get(35).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("15")){
                reservation.endTimeR="14";
                timeTable.get(35).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_M6MouseClicked

    private void Tue6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tue6MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(36).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(36).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(36).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(36).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="14";
                reservation.endTimeR="15";
                timeTable.get(36).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("15")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="14";
                    timeTable.get(36).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("14")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="15";
                    timeTable.get(36).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("14") && reservation.endTimeR.equals("15")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(36).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("14")){
                reservation.startTimeR="15";
                timeTable.get(36).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("15")){
                reservation.endTimeR="14";
                timeTable.get(36).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Tue6MouseClicked

    private void Wed6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Wed6MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(37).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(37).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(37).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(37).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="14";
                reservation.endTimeR="15";
                timeTable.get(37).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("15")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="14";
                    timeTable.get(37).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("14")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="15";
                    timeTable.get(37).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("14") && reservation.endTimeR.equals("15")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(37).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("14")){
                reservation.startTimeR="15";
                timeTable.get(37).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("15")){
                reservation.endTimeR="14";
                timeTable.get(37).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Wed6MouseClicked

    private void Thu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Thu6MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(38).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(38).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(38).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(38).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="14";
                reservation.endTimeR="15";
                timeTable.get(38).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("15")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="14";
                    timeTable.get(38).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("14")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="15";
                    timeTable.get(38).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("14") && reservation.endTimeR.equals("15")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(38).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("14")){
                reservation.startTimeR="15";
                timeTable.get(38).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("15")){
                reservation.endTimeR="14";
                timeTable.get(38).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Thu6MouseClicked

    private void Fri6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Fri6MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(39).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(39).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(39).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(39).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="14";
                reservation.endTimeR="15";
                timeTable.get(39).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("15")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="14";
                    timeTable.get(39).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("14")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="15";
                    timeTable.get(39).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("14") && reservation.endTimeR.equals("15")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(39).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("14")){
                reservation.startTimeR="15";
                timeTable.get(39).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("15")){
                reservation.endTimeR="14";
                timeTable.get(39).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Fri6MouseClicked

    private void jPanel97MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel97MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(40).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(40).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(40).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(40).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="14";
                reservation.endTimeR="15";
                timeTable.get(40).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("15")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="14";
                    timeTable.get(40).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("14")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="15";
                    timeTable.get(40).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("14") && reservation.endTimeR.equals("15")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(40).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("14")){
                reservation.startTimeR="15";
                timeTable.get(40).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("15")){
                reservation.endTimeR="14";
                timeTable.get(40).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jPanel97MouseClicked

    private void jPanel106MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel106MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(41).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(41).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(41).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(41).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="14";
                reservation.endTimeR="15";
                timeTable.get(41).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("15")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="14";
                    timeTable.get(41).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("14")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="15";
                    timeTable.get(41).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("14") && reservation.endTimeR.equals("15")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(41).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("14")){
                reservation.startTimeR="15";
                timeTable.get(41).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("15")){
                reservation.endTimeR="14";
                timeTable.get(41).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jPanel106MouseClicked

    private void M7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_M7MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(42).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(42).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(42).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(42).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="15";
                reservation.endTimeR="16";
                timeTable.get(42).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("16")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="15";
                    timeTable.get(42).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("15")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="16";
                    timeTable.get(42).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("15") && reservation.endTimeR.equals("16")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(42).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("15")){
                reservation.startTimeR="16";
                timeTable.get(42).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("16")){
                reservation.endTimeR="15";
                timeTable.get(42).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_M7MouseClicked

    private void Tue7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tue7MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(43).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(43).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(43).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(43).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="15";
                reservation.endTimeR="16";
                timeTable.get(43).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("16")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="15";
                    timeTable.get(43).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("15")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="16";
                    timeTable.get(43).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("15") && reservation.endTimeR.equals("16")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(43).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("15")){
                reservation.startTimeR="16";
                timeTable.get(43).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("16")){
                reservation.endTimeR="15";
                timeTable.get(43).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Tue7MouseClicked

    private void Wed7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Wed7MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(44).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(44).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(44).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(44).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="15";
                reservation.endTimeR="16";
                timeTable.get(44).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("16")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="15";
                    timeTable.get(44).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("15")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="16";
                    timeTable.get(44).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("15") && reservation.endTimeR.equals("16")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(44).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("15")){
                reservation.startTimeR="16";
                timeTable.get(44).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("16")){
                reservation.endTimeR="15";
                timeTable.get(44).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Wed7MouseClicked

    private void Thu7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Thu7MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(45).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(45).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(45).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(45).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="15";
                reservation.endTimeR="16";
                timeTable.get(45).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("16")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="15";
                    timeTable.get(45).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("15")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="16";
                    timeTable.get(45).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("15") && reservation.endTimeR.equals("16")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(45).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("15")){
                reservation.startTimeR="16";
                timeTable.get(45).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("16")){
                reservation.endTimeR="15";
                timeTable.get(45).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Thu7MouseClicked

    private void Fri7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Fri7MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(46).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(46).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(46).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(46).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="15";
                reservation.endTimeR="16";
                timeTable.get(46).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("16")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="15";
                    timeTable.get(46).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("15")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="16";
                    timeTable.get(46).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("15") && reservation.endTimeR.equals("16")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(46).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("15")){
                reservation.startTimeR="16";
                timeTable.get(46).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("16")){
                reservation.endTimeR="15";
                timeTable.get(46).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Fri7MouseClicked

    private void jPanel98MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel98MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(47).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(47).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(47).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(47).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="15";
                reservation.endTimeR="16";
                timeTable.get(47).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("16")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="15";
                    timeTable.get(47).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("15")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="16";
                    timeTable.get(47).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("15") && reservation.endTimeR.equals("16")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(47).setBackground(new Color(183,240,177));
            }else if(reservation.startTimeR.equals("15")){
                reservation.startTimeR="16";
                timeTable.get(47).setBackground(new Color(183,240,177));
            }else if(reservation.endTimeR.equals("16")){
                reservation.endTimeR="15";
                timeTable.get(47).setBackground(new Color(183,240,177));
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jPanel98MouseClicked

    private void jPanel107MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel107MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(48).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(48).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(48).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(48).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="15";
                reservation.endTimeR="16";
                timeTable.get(48).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("16")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="15";
                    timeTable.get(48).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("15")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="16";
                    timeTable.get(48).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("15") && reservation.endTimeR.equals("16")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(48).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("15")){
                reservation.startTimeR="16";
                timeTable.get(48).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("16")){
                reservation.endTimeR="15";
                timeTable.get(48).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jPanel107MouseClicked

    private void M8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_M8MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(49).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(49).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(49).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(49).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="16";
                reservation.endTimeR="17";
                timeTable.get(49).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("17")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="16";
                    timeTable.get(49).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("16")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="17";
                    timeTable.get(49).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("16") && reservation.endTimeR.equals("17")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(49).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("16")){
                reservation.startTimeR="17";
                timeTable.get(49).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("17")){
                reservation.endTimeR="16";
                timeTable.get(49).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_M8MouseClicked

    private void Tue8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tue8MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(50).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(50).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(50).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(50).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="16";
                reservation.endTimeR="17";
                timeTable.get(50).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("17")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="16";
                    timeTable.get(50).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("16")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="17";
                    timeTable.get(50).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("16") && reservation.endTimeR.equals("17")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(50).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("16")){
                reservation.startTimeR="17";
                timeTable.get(50).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("17")){
                reservation.endTimeR="16";
                timeTable.get(50).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Tue8MouseClicked

    private void Wed8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Wed8MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(51).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(51).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(51).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(51).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="16";
                reservation.endTimeR="17";
                timeTable.get(51).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("17")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="16";
                    timeTable.get(51).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("16")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="17";
                    timeTable.get(51).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("16") && reservation.endTimeR.equals("17")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(51).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("16")){
                reservation.startTimeR="17";
                timeTable.get(51).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("17")){
                reservation.endTimeR="16";
                timeTable.get(51).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Wed8MouseClicked

    private void Thu8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Thu8MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(52).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(52).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(52).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(52).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="16";
                reservation.endTimeR="17";
                timeTable.get(52).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("17")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="16";
                    timeTable.get(52).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("16")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="17";
                    timeTable.get(52).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("16") && reservation.endTimeR.equals("17")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(52).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("16")){
                reservation.startTimeR="17";
                timeTable.get(52).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("17")){
                reservation.endTimeR="16";
                timeTable.get(52).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Thu8MouseClicked

    private void Fri8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Fri8MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(53).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(53).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(53).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(53).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="16";
                reservation.endTimeR="17";
                timeTable.get(53).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("17")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="16";
                    timeTable.get(53).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("16")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="17";
                    timeTable.get(53).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("16") && reservation.endTimeR.equals("17")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(53).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("16")){
                reservation.startTimeR="17";
                timeTable.get(53).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("17")){
                reservation.endTimeR="16";
                timeTable.get(53).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Fri8MouseClicked

    private void jPanel99MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel99MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(54).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(54).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(54).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(54).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="16";
                reservation.endTimeR="17";
                timeTable.get(54).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("17")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="16";
                    timeTable.get(54).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("16")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="17";
                    timeTable.get(54).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("16") && reservation.endTimeR.equals("17")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(54).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("16")){
                reservation.startTimeR="17";
                timeTable.get(54).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("17")){
                reservation.endTimeR="16";
                timeTable.get(54).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jPanel99MouseClicked

    private void jPanel108MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel108MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(55).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(55).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(55).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(55).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="16";
                reservation.endTimeR="17";
                timeTable.get(55).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.startTimeR.equals("17")){    //기존의 시작 시간과 이어지는 경우
                    reservation.startTimeR="16";
                    timeTable.get(55).setBackground(new Color(183,240,177));
                }else if(reservation.endTimeR.equals("16")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="17";
                    timeTable.get(55).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("16") && reservation.endTimeR.equals("17")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(55).setBackground(Color.WHITE);
            }else if(reservation.startTimeR.equals("16")){
                reservation.startTimeR="17";
                timeTable.get(55).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("17")){
                reservation.endTimeR="16";
                timeTable.get(55).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jPanel108MouseClicked

    private void M9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_M9MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(56).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(56).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(56).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(56).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="17";
                reservation.endTimeR="18";
                timeTable.get(56).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.endTimeR.equals("17")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="18";
                    timeTable.get(56).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("17") && reservation.endTimeR.equals("18")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(56).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("17")){
                reservation.endTimeR="16";
                timeTable.get(56).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_M9MouseClicked

    private void Tue9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tue9MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(57).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(57).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(57).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(57).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="17";
                reservation.endTimeR="18";
                timeTable.get(57).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.endTimeR.equals("17")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="18";
                    timeTable.get(57).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("17") && reservation.endTimeR.equals("18")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(57).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("17")){
                reservation.endTimeR="16";
                timeTable.get(57).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Tue9MouseClicked

    private void Wed9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Wed9MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(58).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(58).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(58).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(58).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="17";
                reservation.endTimeR="18";
                timeTable.get(58).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.endTimeR.equals("17")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="18";
                    timeTable.get(58).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("17") && reservation.endTimeR.equals("18")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(58).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("17")){
                reservation.endTimeR="16";
                timeTable.get(58).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Wed9MouseClicked

    private void Thu9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Thu9MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(59).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(59).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(59).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(59).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="17";
                reservation.endTimeR="18";
                timeTable.get(59).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.endTimeR.equals("17")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="18";
                    timeTable.get(59).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("17") && reservation.endTimeR.equals("18")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(59).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("17")){
                reservation.endTimeR="16";
                timeTable.get(59).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Thu9MouseClicked

    private void Fri9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Fri9MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(60).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(60).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(60).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(60).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="17";
                reservation.endTimeR="18";
                timeTable.get(60).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.endTimeR.equals("17")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="18";
                    timeTable.get(60).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("17") && reservation.endTimeR.equals("18")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(60).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("17")){
                reservation.endTimeR="16";
                timeTable.get(60).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_Fri9MouseClicked

    private void jPanel100MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel100MouseClicked
        // TODO add your handling code here:
        if(timeTable.get(61).getBackground().getRGB() == -256){//노란색이라면
            JOptionPane.showMessageDialog(this, "강의가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(61).getBackground().getRGB() == -14336){  //주황색이라면
            JOptionPane.showMessageDialog(this, "세미나가 있는 시간은 선택할 수 없습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(61).getBackground().getRGB() == -4144960){    //회색이라면
            JOptionPane.showMessageDialog(this, "입력한 날짜와 일치하는 요일만 선택 가능합니다.", "Message", JOptionPane.ERROR_MESSAGE);
        }else if(timeTable.get(61).getBackground().getRGB() == -1){ //흰색이라면
            if(reservation.startTimeR.equals("0")){     //아직 아무것도 선택하지 않았을 경우
                reservation.startTimeR="17";
                reservation.endTimeR="18";
                timeTable.get(61).setBackground(new Color(183,240,177));
            }else{  //이미 선택한 패널이 있는 경우
                if(reservation.endTimeR.equals("17")){    //기존의 종료 시간과 이어지는 경우
                    reservation.endTimeR="18";
                    timeTable.get(61).setBackground(new Color(183,240,177));
                }else{
                    JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{  //초록색인 경우
            if(reservation.startTimeR.equals("17") && reservation.endTimeR.equals("18")){  //아무것도 선택하지 않아지는 경우
                reservation.startTimeR="0";
                reservation.endTimeR="0";
                timeTable.get(61).setBackground(Color.WHITE);
            }else if(reservation.endTimeR.equals("17")){
                reservation.endTimeR="16";
                timeTable.get(61).setBackground(Color.WHITE);
            }else{
                JOptionPane.showMessageDialog(this, "시간이 이어지도록 선택해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jPanel100MouseClicked

    // 연장버튼
    private void continueButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continueButtActionPerformed
        if (continueCombo.getSelectedIndex() == -1) {  // 연장 가능 시간 콤보박스 아이템이 없을 경우
            JOptionPane.showMessageDialog(this, "연장 불가능");
        } else {  // 연장 가능 시간 콤보박스 아이템 있을 경우
            connect();  // 디비 연결
            String continueT = continueCombo.getSelectedItem().toString();   // 연장가능시간 가져와서 저장
            int continueTNum = continueT.indexOf(":");    //":"위치 저장

            try {

                // 종료 시간 수정
                sql = "update reservation set endTimeR = ? where labId = ? and seatId = ? and dateR = ? and startTimeR =? and endTimeR =?";

                pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, continueT.substring(0, continueTNum));  // 연장 시간
                pstmt.setString(2, reservation.labId);  // 강의실
                pstmt.setInt(3, reservation.seatId);  // 좌석
                pstmt.setString(4, reservation.dateR);  // 날짜
                pstmt.setString(5, reservation.startTimeR);  //시작 시간
                pstmt.setString(6, reservation.endTimeR);  //종료 시간

                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "연장 완료");

                // 메인 패널로 이동
                reset();
                mainPanel.setVisible(true);

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            } finally {
                if (rs != null) try {
                    rs.close();
                } catch (SQLException ex) {
                }
                if (pstmt != null) try {
                    pstmt.close();
                } catch (SQLException ex) {
                }
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }
    }//GEN-LAST:event_continueButtActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AfterCheckButt;
    private javax.swing.JButton AfterReserButt;
    private javax.swing.JButton BeforeReserButt;
    private javax.swing.JPanel CanclePanel;
    private javax.swing.JPanel CheckPanel;
    private javax.swing.JTextField DATE_Text;
    private javax.swing.JTextField DD_Text1;
    private javax.swing.JPanel Fri1;
    private javax.swing.JPanel Fri2;
    private javax.swing.JPanel Fri3;
    private javax.swing.JPanel Fri4;
    private javax.swing.JPanel Fri5;
    private javax.swing.JPanel Fri6;
    private javax.swing.JPanel Fri7;
    private javax.swing.JPanel Fri8;
    private javax.swing.JPanel Fri9;
    private javax.swing.JPanel LabCheckPanel;
    private javax.swing.JComboBox<String> LabComboBox;
    private javax.swing.JPanel LabNoticePanel;
    private javax.swing.JPanel LabStatus;
    private javax.swing.JPanel LabStatusPanel;
    private javax.swing.JPanel LabTTCheckPanel;
    private javax.swing.JPanel Lab_menuPanel;
    private javax.swing.JPanel M5;
    private javax.swing.JPanel M6;
    private javax.swing.JPanel M7;
    private javax.swing.JPanel M8;
    private javax.swing.JPanel M9;
    private javax.swing.JTextField MM_Text1;
    private javax.swing.JPanel Mon1;
    private javax.swing.JPanel Mon2;
    private javax.swing.JPanel Mon3;
    private javax.swing.JPanel Mon4;
    private javax.swing.JButton MyReserCancleButt;
    private javax.swing.JButton MyReserCheckButt;
    private javax.swing.JPanel OverLabCheckPanel;
    private javax.swing.JPanel OverReser;
    private javax.swing.JPanel ReserPanel;
    private javax.swing.JPanel Reser_menuPanel;
    private javax.swing.JPanel Sat1;
    private javax.swing.JPanel Sun1;
    private javax.swing.JPanel TT;
    private javax.swing.JButton TTCheckButt;
    private javax.swing.JPanel Thu1;
    private javax.swing.JPanel Thu2;
    private javax.swing.JPanel Thu3;
    private javax.swing.JPanel Thu4;
    private javax.swing.JPanel Thu5;
    private javax.swing.JPanel Thu6;
    private javax.swing.JPanel Thu7;
    private javax.swing.JPanel Thu8;
    private javax.swing.JPanel Thu9;
    private javax.swing.JPanel TitlePanel;
    private javax.swing.JPanel Tue1;
    private javax.swing.JPanel Tue2;
    private javax.swing.JPanel Tue3;
    private javax.swing.JPanel Tue4;
    private javax.swing.JPanel Tue5;
    private javax.swing.JPanel Tue6;
    private javax.swing.JPanel Tue7;
    private javax.swing.JPanel Tue8;
    private javax.swing.JPanel Tue9;
    private javax.swing.JButton UserChangeButt;
    private javax.swing.JPanel UserChangePanel;
    private javax.swing.JButton UserDeleteButt;
    private javax.swing.JPanel UserDeletePanel;
    private javax.swing.JPanel UserInfo_menuPanel;
    private javax.swing.JPanel Wed1;
    private javax.swing.JPanel Wed2;
    private javax.swing.JPanel Wed3;
    private javax.swing.JPanel Wed4;
    private javax.swing.JPanel Wed5;
    private javax.swing.JPanel Wed6;
    private javax.swing.JPanel Wed7;
    private javax.swing.JPanel Wed8;
    private javax.swing.JPanel Wed9;
    private javax.swing.JTextField YYYY_Text1;
    private javax.swing.JPanel aPanel;
    private javax.swing.JPanel afterReser;
    private javax.swing.JRadioButton afterReserRadio;
    private javax.swing.JPanel afterSeatStatePanel;
    private javax.swing.JButton beforeCheckButt;
    private javax.swing.JPanel beforeReser;
    private javax.swing.JRadioButton beforeReserRadio;
    private javax.swing.JButton beforeSeatCheckButt;
    private javax.swing.JPanel beforeSeatStatePanel;
    private javax.swing.JPanel beforeTTPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JPanel check;
    private javax.swing.JButton checkButt;
    private javax.swing.JButton checkButt1;
    private javax.swing.JButton continueButt;
    private javax.swing.JComboBox<String> continueCombo;
    private javax.swing.JButton endButt;
    private javax.swing.JComboBox<String> endTimeR;
    private javax.swing.JComboBox<String> endTimeR1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel100;
    private javax.swing.JPanel jPanel102;
    private javax.swing.JPanel jPanel103;
    private javax.swing.JPanel jPanel104;
    private javax.swing.JPanel jPanel105;
    private javax.swing.JPanel jPanel106;
    private javax.swing.JPanel jPanel107;
    private javax.swing.JPanel jPanel108;
    private javax.swing.JPanel jPanel109;
    private javax.swing.JPanel jPanel111;
    private javax.swing.JPanel jPanel112;
    private javax.swing.JPanel jPanel113;
    private javax.swing.JPanel jPanel114;
    private javax.swing.JPanel jPanel97;
    private javax.swing.JPanel jPanel98;
    private javax.swing.JPanel jPanel99;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JButton labButt;
    private javax.swing.JButton labReserButt;
    private java.awt.Label label1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton manageButt;
    private javax.swing.JPanel menuCancle;
    private javax.swing.JPanel menuCheck;
    private javax.swing.JPanel menuLabCheck;
    private javax.swing.JPanel menuLabNotice;
    private javax.swing.JPanel menuReser;
    private javax.swing.JPanel menuTimeTable;
    private javax.swing.JPanel menuUserChange;
    private javax.swing.JPanel menuUserDelete;
    private javax.swing.JButton overReserButt;
    private javax.swing.JButton overSeatCheckButt;
    private javax.swing.JPanel overSeatStatePanel;
    private javax.swing.JButton reserNextButt;
    private javax.swing.JRadioButton resetRadio;
    private javax.swing.JRadioButton seat1;
    private javax.swing.JRadioButton seat10;
    private javax.swing.JRadioButton seat100;
    private javax.swing.JRadioButton seat101;
    private javax.swing.JRadioButton seat102;
    private javax.swing.JRadioButton seat103;
    private javax.swing.JRadioButton seat104;
    private javax.swing.JRadioButton seat105;
    private javax.swing.JRadioButton seat106;
    private javax.swing.JRadioButton seat107;
    private javax.swing.JRadioButton seat108;
    private javax.swing.JRadioButton seat109;
    private javax.swing.JRadioButton seat11;
    private javax.swing.JRadioButton seat110;
    private javax.swing.JRadioButton seat111;
    private javax.swing.JRadioButton seat112;
    private javax.swing.JRadioButton seat113;
    private javax.swing.JRadioButton seat114;
    private javax.swing.JRadioButton seat115;
    private javax.swing.JRadioButton seat116;
    private javax.swing.JRadioButton seat117;
    private javax.swing.JRadioButton seat118;
    private javax.swing.JRadioButton seat119;
    private javax.swing.JRadioButton seat12;
    private javax.swing.JRadioButton seat120;
    private javax.swing.JRadioButton seat13;
    private javax.swing.JRadioButton seat14;
    private javax.swing.JRadioButton seat15;
    private javax.swing.JRadioButton seat16;
    private javax.swing.JRadioButton seat17;
    private javax.swing.JRadioButton seat18;
    private javax.swing.JRadioButton seat19;
    private javax.swing.JRadioButton seat2;
    private javax.swing.JRadioButton seat20;
    private javax.swing.JRadioButton seat21;
    private javax.swing.JRadioButton seat22;
    private javax.swing.JRadioButton seat23;
    private javax.swing.JRadioButton seat24;
    private javax.swing.JRadioButton seat25;
    private javax.swing.JRadioButton seat26;
    private javax.swing.JRadioButton seat27;
    private javax.swing.JRadioButton seat28;
    private javax.swing.JRadioButton seat29;
    private javax.swing.JRadioButton seat3;
    private javax.swing.JRadioButton seat30;
    private javax.swing.JRadioButton seat31;
    private javax.swing.JRadioButton seat32;
    private javax.swing.JRadioButton seat33;
    private javax.swing.JRadioButton seat34;
    private javax.swing.JRadioButton seat35;
    private javax.swing.JRadioButton seat36;
    private javax.swing.JRadioButton seat37;
    private javax.swing.JRadioButton seat38;
    private javax.swing.JRadioButton seat39;
    private javax.swing.JRadioButton seat4;
    private javax.swing.JRadioButton seat40;
    private javax.swing.JRadioButton seat41;
    private javax.swing.JRadioButton seat42;
    private javax.swing.JRadioButton seat43;
    private javax.swing.JRadioButton seat44;
    private javax.swing.JRadioButton seat45;
    private javax.swing.JRadioButton seat46;
    private javax.swing.JRadioButton seat47;
    private javax.swing.JRadioButton seat48;
    private javax.swing.JRadioButton seat49;
    private javax.swing.JRadioButton seat5;
    private javax.swing.JRadioButton seat50;
    private javax.swing.JRadioButton seat51;
    private javax.swing.JRadioButton seat52;
    private javax.swing.JRadioButton seat53;
    private javax.swing.JRadioButton seat54;
    private javax.swing.JRadioButton seat55;
    private javax.swing.JRadioButton seat56;
    private javax.swing.JRadioButton seat57;
    private javax.swing.JRadioButton seat58;
    private javax.swing.JRadioButton seat59;
    private javax.swing.JRadioButton seat6;
    private javax.swing.JRadioButton seat60;
    private javax.swing.JRadioButton seat61;
    private javax.swing.JRadioButton seat62;
    private javax.swing.JRadioButton seat63;
    private javax.swing.JRadioButton seat64;
    private javax.swing.JRadioButton seat65;
    private javax.swing.JRadioButton seat66;
    private javax.swing.JRadioButton seat67;
    private javax.swing.JRadioButton seat68;
    private javax.swing.JRadioButton seat69;
    private javax.swing.JRadioButton seat7;
    private javax.swing.JRadioButton seat70;
    private javax.swing.JRadioButton seat71;
    private javax.swing.JRadioButton seat72;
    private javax.swing.JRadioButton seat73;
    private javax.swing.JRadioButton seat74;
    private javax.swing.JRadioButton seat75;
    private javax.swing.JRadioButton seat76;
    private javax.swing.JRadioButton seat77;
    private javax.swing.JRadioButton seat78;
    private javax.swing.JRadioButton seat79;
    private javax.swing.JRadioButton seat8;
    private javax.swing.JRadioButton seat80;
    private javax.swing.JRadioButton seat81;
    private javax.swing.JRadioButton seat82;
    private javax.swing.JRadioButton seat83;
    private javax.swing.JRadioButton seat84;
    private javax.swing.JRadioButton seat85;
    private javax.swing.JRadioButton seat86;
    private javax.swing.JRadioButton seat87;
    private javax.swing.JRadioButton seat88;
    private javax.swing.JRadioButton seat89;
    private javax.swing.JRadioButton seat9;
    private javax.swing.JRadioButton seat90;
    private javax.swing.JRadioButton seat91;
    private javax.swing.JRadioButton seat92;
    private javax.swing.JRadioButton seat93;
    private javax.swing.JRadioButton seat94;
    private javax.swing.JRadioButton seat95;
    private javax.swing.JRadioButton seat96;
    private javax.swing.JRadioButton seat97;
    private javax.swing.JRadioButton seat98;
    private javax.swing.JRadioButton seat99;
    private javax.swing.JRadioButton soloRadio;
    private javax.swing.JButton startButt;
    private javax.swing.JComboBox<String> startTimeR;
    private javax.swing.JComboBox<String> startTimeR1;
    private javax.swing.JButton teamCheckButt;
    private javax.swing.JRadioButton teamRadio;
    private java.awt.TextField textField1;
    private javax.swing.JButton userInformationButt;
    // End of variables declaration//GEN-END:variables
}

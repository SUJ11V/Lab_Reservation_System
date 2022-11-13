package LabReservationSystem;

import source.Lecture;
import source.Reservation;
import source.Seminar;
import java.awt.Color;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class StudentMain extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql; //쿼리문 받을 변수
    int num = 1;

    ArrayList<JRadioButton> seat = new ArrayList<>();
    Reservation reservation; //사용자에게 입력받을 예약 정보 객체
    //Reservation reservationList[]=new Reservation[31];  //db에서 예약정보를 저장할 객체
    Lecture lecture;    //강의 객체
    Seminar seminar;    //세미나(또는 특강) 객체

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

    public int getDay(Reservation reservation) { //요일 구하기
        //dateR은 "yyyy/mm/dd" 형식으로 된 string type으로 받는다.
        int year = Integer.parseInt(reservation.dateR.substring(0, 4));    //년
        int month = Integer.parseInt(reservation.dateR.substring(5, 7));   //월
        int day = Integer.parseInt(reservation.dateR.substring(8, 10));    //일

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

    // 강의실별 인원수
    String lab915Num = null;
    String lab916Num = null;
    String lab918Num = null;
    String lab911Num = null;

    public StudentMain() {
        initComponents();
        TitlePanel.setVisible(true);
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

        LabCheckPanel.setVisible(false);
        LabTTCheckPanel.setVisible(false);
        TT.setVisible(false);
        LabNoticePanel.setVisible(false);
        LabStatusPanel.setVisible(false);
        LabStatus.setVisible(false);

        UserChangePanel.setVisible(false);
        UserDeletePanel.setVisible(false);

        setSeat();
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
        jTextField14 = new javax.swing.JTextField();
        startButt = new javax.swing.JButton();
        endButt = new javax.swing.JButton();
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

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1110, 725));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TitlePanel.setBackground(new java.awt.Color(204, 204, 204));
        TitlePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TitlePanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TitlePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TitlePanelMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("굴림", 0, 30)); // NOI18N
        jLabel1.setText("컴퓨터소프트웨어공학과 실습실 예약 시스템");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuCheckLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );
        menuCheckLayout.setVerticalGroup(
            menuCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuCheckLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(19, 19, 19))
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
                .addContainerGap())
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
            .addComponent(menuCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuCancle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuReser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Reser_menuPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Reser_menuPanelLayout.setVerticalGroup(
            Reser_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Reser_menuPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel3)
                .addGap(34, 34, 34)
                .addComponent(menuReser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuCancle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(275, Short.MAX_VALUE))
        );

        getContentPane().add(Reser_menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 150, 620));

        buttonGroup1.add(beforeReserRadio);
        beforeReserRadio.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        beforeReserRadio.setText("5시 이전 실습실 사용 예약");

        buttonGroup1.add(afterReserRadio);
        afterReserRadio.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        afterReserRadio.setText("5시 이후 실습실 사용 예약");

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
                .addContainerGap(265, Short.MAX_VALUE))
        );

        getContentPane().add(ReserPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 610));

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

        BeforeReserButt.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        BeforeReserButt.setText("예약");
        BeforeReserButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BeforeReserButtActionPerformed(evt);
            }
        });

        buttonGroup2.add(resetRadio);
        resetRadio.setSelected(true);

        javax.swing.GroupLayout beforeSeatStatePanelLayout = new javax.swing.GroupLayout(beforeSeatStatePanel);
        beforeSeatStatePanel.setLayout(beforeSeatStatePanelLayout);
        beforeSeatStatePanelLayout.setHorizontalGroup(
            beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                        .addComponent(seat31, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(seat32, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(seat33, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(seat34, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                        .addGap(155, 155, 155)
                        .addComponent(seat35, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(seat48)
                                .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                                    .addComponent(seat39, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(seat40)))
                            .addComponent(seat47)
                            .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                                .addComponent(seat55)
                                .addGap(18, 18, 18)
                                .addComponent(seat56)))
                        .addGap(18, 18, 18)
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                                .addComponent(seat41)
                                .addGap(18, 18, 18)
                                .addComponent(seat42)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(seat43))
                            .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                                .addComponent(seat49)
                                .addGap(18, 18, 18)
                                .addComponent(seat50)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(seat51))
                            .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                                .addComponent(seat57)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(seat58)))))
                .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seat36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seat44)))
                    .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(seat52)
                            .addComponent(seat59))))
                .addGap(25, 25, 25)
                .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(seat60)
                    .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                        .addComponent(seat53)
                        .addGap(18, 18, 18)
                        .addComponent(seat54))
                    .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, beforeSeatStatePanelLayout.createSequentialGroup()
                            .addComponent(seat37, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(seat38))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, beforeSeatStatePanelLayout.createSequentialGroup()
                            .addComponent(seat45)
                            .addGap(18, 18, 18)
                            .addComponent(seat46))))
                .addGap(52, 52, 52))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, beforeSeatStatePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BeforeReserButt)
                .addContainerGap())
            .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                .addGap(290, 290, 290)
                .addComponent(resetRadio)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        beforeSeatStatePanelLayout.setVerticalGroup(
            beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seat31)
                    .addComponent(seat32)
                    .addComponent(seat33)
                    .addComponent(seat34)
                    .addComponent(seat35)
                    .addComponent(seat36)
                    .addComponent(seat37)
                    .addComponent(seat38))
                .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(seat39)
                            .addComponent(seat40)
                            .addComponent(seat41)
                            .addComponent(seat42)
                            .addComponent(seat43)
                            .addComponent(seat44)
                            .addComponent(seat45)
                            .addComponent(seat46)))
                    .addGroup(beforeSeatStatePanelLayout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(seat49)
                            .addComponent(seat50)
                            .addComponent(seat48)
                            .addComponent(seat47)
                            .addComponent(seat51)
                            .addComponent(seat52)
                            .addComponent(seat53)
                            .addComponent(seat54))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(beforeSeatStatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(seat56)
                    .addComponent(seat55)
                    .addComponent(seat57)
                    .addComponent(seat58)
                    .addComponent(seat59)
                    .addComponent(seat60))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resetRadio)
                .addGap(9, 9, 9)
                .addComponent(BeforeReserButt)
                .addContainerGap())
        );

        javax.swing.GroupLayout beforeReserLayout = new javax.swing.GroupLayout(beforeReser);
        beforeReser.setLayout(beforeReserLayout);
        beforeReserLayout.setHorizontalGroup(
            beforeReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, beforeReserLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(beforeSeatStatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
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
                .addGap(18, 18, 18)
                .addComponent(beforeSeatStatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        getContentPane().add(beforeReser, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 610));

        afterReser.setPreferredSize(new java.awt.Dimension(914, 507));

        jLabel2.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        jLabel2.setText("5시 이후 실습실 사용 예약");

        jLabel10.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel10.setText("시작시간");

        jLabel11.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel11.setText("날짜");

        jLabel12.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel12.setText("종료시간");

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
                .addContainerGap(275, Short.MAX_VALUE))
        );

        getContentPane().add(afterReser, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 610));

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
        jTextField10.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jTextField10.setText("jTextField10");

        jTextField11.setEditable(false);
        jTextField11.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jTextField11.setText("jTextField11");

        jTextField12.setEditable(false);
        jTextField12.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jTextField12.setText("jTextField12");

        jTextField13.setEditable(false);
        jTextField13.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jTextField13.setText("jTextField13");

        jTextField14.setEditable(false);
        jTextField14.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        jTextField14.setText("jTextField14");

        startButt.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        startButt.setText("사용 시작");
        startButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtActionPerformed(evt);
            }
        });

        endButt.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        endButt.setText("퇴실");
        endButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CheckPanelLayout = new javax.swing.GroupLayout(CheckPanel);
        CheckPanel.setLayout(CheckPanelLayout);
        CheckPanelLayout.setHorizontalGroup(
            CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CheckPanelLayout.createSequentialGroup()
                .addGap(334, 334, 334)
                .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CheckPanelLayout.createSequentialGroup()
                            .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField10, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                .addComponent(jTextField11)))
                        .addGroup(CheckPanelLayout.createSequentialGroup()
                            .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.LEADING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField14, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                .addComponent(jTextField13)
                                .addComponent(jTextField12))))
                    .addGroup(CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(startButt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(endButt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(342, Short.MAX_VALUE))
        );
        CheckPanelLayout.setVerticalGroup(
            CheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CheckPanelLayout.createSequentialGroup()
                .addGap(107, 107, 107)
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
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(startButt)
                .addGap(18, 18, 18)
                .addComponent(endButt)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        getContentPane().add(CheckPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 610));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

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
        jTextField2.setText("jTextField2");

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
                .addGap(149, 149, 149)
                .addGroup(CanclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CanclePanelLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(MyReserCheckButt)
                        .addGap(51, 51, 51))
                    .addComponent(MyReserCancleButt, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(174, Short.MAX_VALUE))
        );
        CanclePanelLayout.setVerticalGroup(
            CanclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CanclePanelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(CanclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MyReserCheckButt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CanclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jLabel17)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(MyReserCancleButt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        getContentPane().add(CanclePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 610));

        jLabel9.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel9.setText("강의실");

        jComboBox1.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N

        AfterCheckButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        AfterCheckButt.setText("확인");
        AfterCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AfterCheckButtActionPerformed(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(afterSeatStatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );

        getContentPane().add(check, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 108, 960, 610));

        buttonGroup3.add(teamRadio);
        teamRadio.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        teamRadio.setText("조별학습");

        buttonGroup3.add(soloRadio);
        soloRadio.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        soloRadio.setText("개인학습");

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

        jLabel8.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel8.setText("사용 가능한 강의실");

        jComboBox8.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N

        overSeatCheckButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        overSeatCheckButt.setText("좌석 조회");
        overSeatCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overSeatCheckButtActionPerformed(evt);
            }
        });

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
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(OverReser, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 610));

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
                .addContainerGap(562, Short.MAX_VALUE))
        );

        getContentPane().add(LabNoticePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 610));

        jComboBox7.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "915", "916", "918", "911" }));

        TTCheckButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        TTCheckButt.setText("조회");
        TTCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TTCheckButtActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel30.setText("실습실 : ");

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
                .addContainerGap(80, Short.MAX_VALUE)
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

        getContentPane().add(LabTTCheckPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 950, 620));

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
        startTimeR1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00" }));

        endTimeR1.setFont(new java.awt.Font("맑은 고딕", 0, 17)); // NOI18N
        endTimeR1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00" }));

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
                .addContainerGap(273, Short.MAX_VALUE)
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
                .addContainerGap(301, Short.MAX_VALUE))
        );

        getContentPane().add(LabCheckPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 610));

        jLabel27.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel27.setText("강의실");

        LabComboBox.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        LabComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton10.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jButton10.setText("확인");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(LabStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84))
        );

        getContentPane().add(LabStatusPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 108, 960, 610));

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
                .addContainerGap(194, Short.MAX_VALUE))
        );

        getContentPane().add(UserChangePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 610));

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
                .addContainerGap(275, Short.MAX_VALUE))
        );

        getContentPane().add(UserDeletePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // 메인화면 - 실습실 예약 버튼
    private void labReserButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labReserButtActionPerformed
        reset();
        ReserPanel.setVisible(true);
        Reser_menuPanel.setVisible(true);

        menuReser.setBackground(Color.red);
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
            menuReser.setBackground(Color.RED);
            beforeReser.setVisible(true);
        } else if (afterReserRadio.isSelected() == true) {  // 5시 이후
            reset();
            Reser_menuPanel.setVisible(true);
            menuReser.setBackground(Color.RED);
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

        menuCheck.setBackground(Color.red);

        // 현재 사용할 수 있는 실습실 번호, 좌석번호, 시작시간, 종료시간, 연장가능시간 출력

    }//GEN-LAST:event_menuCheckMouseClicked

    // 예약 메뉴바 - 본인 예약 현황 조회 및 취소 선택 시
    private void menuCancleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCancleMouseClicked
        reset();
        CanclePanel.setVisible(true);
        Reser_menuPanel.setVisible(true);

        menuCancle.setBackground(Color.red);
    }//GEN-LAST:event_menuCancleMouseClicked

    // 5시 이후 실습실 예약 날짜. 시작시간. 종료시간 입력 받은 후 실습실 조회버튼
    private void checkButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkButtActionPerformed
        reset();
        Reser_menuPanel.setVisible(true);
        menuReser.setBackground(Color.RED);

        connect(); //디비 연결

        String date = DATE_Text.getText();  // 날짜 값 받아오기

        String sTimeR = startTimeR.getSelectedItem().toString();  // 시작 시간 값 받아오기
        int sTimeRIndex = sTimeR.indexOf(":");
        String startTime = sTimeR.substring(0, sTimeRIndex);  // ':' 위치 이전까지 자르기

        String eTimeR = endTimeR.getSelectedItem().toString();  // 종료 시간 값 받아오기
        int eTimeRIndex = eTimeR.indexOf(":");
        String endTime = eTimeR.substring(0, eTimeRIndex);  // ':' 위치 이전까지 자르기

        if (Integer.parseInt(sTimeR.substring(0, sTimeRIndex)) >= Integer.parseInt(eTimeR.substring(0, eTimeRIndex))) {  // 시작시간이 종료시간보다 같거나 늦으면 안됨
            JOptionPane.showMessageDialog(this, "예약 시작시간이 종료시간보다 빨라야합니다.");
            afterReser.setVisible(true);
        } else {

            try {
                // 해당 사용자, 날짜, 시간에 대한 예약 카운트
                sql = "select count(case when sId = ? and dateR = ? and ((startTimeR > ? and startTimeR < ?) or (startTimeR <= ? and endTimeR > ?) ) then 1 end) as reserCount from reservation";
                pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, "stu1");  // 학생 아이디
                pstmt.setString(2, date);  // 날짜
                pstmt.setString(3, startTime);  // 시작시간
                pstmt.setString(4, endTime);  // 종료시간
                pstmt.setString(5, startTime);  // 시작시간
                pstmt.setString(6, startTime);  // 시작시간

                rs = pstmt.executeQuery();

                if (rs.next()) {
                    
                    if (!rs.getString(1).equals("0")) {  // 예약 카운트가 0이 아니면 예약이 이미 존재 
                        JOptionPane.showMessageDialog(this, "이미 예약이 존재하는 시간입니다.");
                        afterReser.setVisible(true);
                    } else { // 예약 카운트가 0이면 예약이 없으므로 예약 가능
                        System.out.println("date : " + date);
                        System.out.println("startTime : " + startTime);
                        System.out.println("endTime : " + endTime);

                        // 예약 정보 저장 - 강의실과 좌석 번호 값 0으로 임의저장 
                        reservation = new Reservation(date, "0", startTime, endTime, 0);

                        try {
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
                                lab915Num = rs.getString(1);
                                lab916Num = rs.getString(2);
                                lab918Num = rs.getString(3);
                                lab911Num = rs.getString(4);
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

                        if (Integer.parseInt(lab915Num) < 2) {  // if 현재 강의실에 학생 수 20명 미만이면 
                            check.setVisible(true);  // 개인 예약 패널로 이동
                            jComboBox1.addItem("915");  // 현재 강의실 915 추가
                        } else if (Integer.parseInt(lab915Num) >= 2) {  // else if 학생 수 20명 이상이면 (개인, 조별 학습 확인)
                            OverReser.setVisible(true);  // 팀 학습 예약 패널로 이동
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
    }//GEN-LAST:event_checkButtActionPerformed

    // 예약 메뉴바 - 실습실 예약 선택 시 
    private void menuReserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuReserMouseClicked
        reset();
        Reser_menuPanel.setVisible(true);
        ReserPanel.setVisible(true);

        menuReser.setBackground(Color.RED);
    }//GEN-LAST:event_menuReserMouseClicked

    // (afterReser -> check) 강의실 선택 후 확인 버튼 클릭
    private void AfterCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AfterCheckButtActionPerformed
        afterSeatStatePanel.setVisible(true);

        String lab = jComboBox1.getSelectedItem().toString();   // 선택한 강의실 값 받아오기

        // 5시 이후 개인 예약 좌석 활성화
        for (int i = 0; i < 30; i++) {
            afterseatS.get(i).setEnabled(true);
        }

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

        menuUserChange.setBackground(Color.yellow);

        // 회원 정보 db에서 가져와서 UserChangePanel에 띄우기
    }//GEN-LAST:event_userInformationButtActionPerformed

    // 메인화면 - 실습실 버튼
    private void labButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labButtActionPerformed
        reset();
        LabCheckPanel.setVisible(true);
        Lab_menuPanel.setVisible(true);

        menuLabCheck.setBackground(Color.yellow);
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

                    pstmt.setString(1, "stu1");  // 아이디 
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

                    pstmt.setString(1, "stu1");         //학생아이디 
                    pstmt.setString(2, reservation.labId);   //예약날짜
                    pstmt.setInt(3, i + 1);    //좌석번호
                    pstmt.setString(4, reservation.dateR);   //예약날짜
                    pstmt.setString(5, reservation.startTimeR); //시작시간
                    pstmt.setString(6, reservation.endTimeR); //종료시간
                    pstmt.setInt(7, 0);   //예약승인
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

        //사용자에게 입력받은 정보를 저장하는 객체
        //아직 자리를 지정하지 않아서 자리번호는 0으로 설정
        reservation = new Reservation(jTextField1.getText(), jComboBox3.getSelectedItem().toString(), sText.substring(0, sNum), eText.substring(0, eNum), 0);

        try {
            //기존의 강의와 겹치는지 조회
            sql = "select * from lecture where day=? and labId=? and ((startTime <=? and endTime>?) or (startTime<? and endTime>=?) or (startTime>=? and endTime<=?))";
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
                System.out.println("ㅓㅗ퍼ㅗㅓㅜㅏ");
            } else {
                //기존의 세미나(혹은 특강)과 겹치는지 조회
                sql = "select * from seminar where dateS=? and labId=? and ((startTimeS <=? and endTimeS>?) or (startTimeS<? and endTimeS>=?) or (startTimeS>=? and endTimeS<=?))";
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
                if (rs.next()) {
                    //seminar=new Seminar(rs.getString("seminarName"));   //seminar 테이블에 seminarName 속성에서 값 가져오기 //값 안들어감.. 왜지
                    JOptionPane.showMessageDialog(this, rs.getString("seminarName") + " 강의 시간입니다.", "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    //기존의 예약과 겹치는지 조회
                    sql = "select * from reservation where dateR=? and labId=? and ((startTimeR <=? and endTimeR>?) or (startTimeR<? and endTimeR>=?) or (startTimeR>=? and endTimeR<=?))";
                    pstmt = conn.prepareStatement(sql); //디비 구문과 연결

                    pstmt.setString(1, reservation.dateR);      //날짜
                    pstmt.setString(2, reservation.labId);      //실습실 번호
                    pstmt.setString(2, reservation.labId);      //실습실 번호
                    pstmt.setString(3, reservation.startTimeR); //시작시간
                    pstmt.setString(4, reservation.endTimeR);   //종료시간
                    pstmt.setString(5, reservation.startTimeR); //시작시간
                    pstmt.setString(6, reservation.endTimeR);   //종료시간
                    pstmt.setString(7, reservation.startTimeR); //시작시간
                    pstmt.setString(8, reservation.endTimeR);   //종료시간

                    rs = pstmt.executeQuery();
                    while (rs.next()) {
                        //reservation = new Reservation(rs.getString("dateR"),rs.getString("labId"),rs.getString("startTimeR"),rs.getString("endTimeR"),rs.getInt("seatId"));
                        //reservationList[num] = reservation;
                        // 예약 중인 좌석이라면 라디오버튼 비활성화
                        seat.get(rs.getInt("seatId") - 1).setEnabled(false);
                    }
                    // 좌석 출력
                    beforeSeatStatePanel.setVisible(true);
                    //좌석 선택 후 예약 클릭

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

    }//GEN-LAST:event_jButton3ActionPerformed

    // 개인, 조별학습 선택받아서 확인 버튼
    private void teamCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teamCheckButtActionPerformed
        OverLabCheckPanel.setVisible(false);
        overSeatStatePanel.setVisible(false);

        // 개인 학습 선택된 경우
        if (soloRadio.isSelected() == true) {
            reset();
            Reser_menuPanel.setVisible(true);
            menuReser.setBackground(Color.RED);

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
    }//GEN-LAST:event_teamCheckButtActionPerformed

    // 실습실 메뉴바 -  실습실 공지사항 및 규칙 조회 선택 시 
    private void menuLabNoticeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLabNoticeMouseClicked
        reset();
        Lab_menuPanel.setVisible(true);
        menuLabNotice.setBackground(Color.yellow);
        LabNoticePanel.setVisible(true);
    }//GEN-LAST:event_menuLabNoticeMouseClicked

    // 실습실 메뉴바 -  실습실 시간표 조회 선택 시 
    private void menuTimeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTimeTableMouseClicked
        reset();
        Lab_menuPanel.setVisible(true);
        menuTimeTable.setBackground(Color.yellow);
        LabTTCheckPanel.setVisible(true);
    }//GEN-LAST:event_menuTimeTableMouseClicked

    // 실습실 메뉴바 -  사용가능한 실습실 확인 선택 시 
    private void menuLabCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLabCheckMouseClicked
        reset();
        Lab_menuPanel.setVisible(true);
        menuLabCheck.setBackground(Color.yellow);
        LabCheckPanel.setVisible(true);
    }//GEN-LAST:event_menuLabCheckMouseClicked

    // 실습실 시간표 조회 버튼
    private void TTCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TTCheckButtActionPerformed
        TT.setVisible(true);

        //테이블에 시간표 출력
    }//GEN-LAST:event_TTCheckButtActionPerformed

    // 날짜, 시간 입력 후 사용 가능한 실습실 조회 버튼
    private void checkButt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkButt1ActionPerformed
        reset();
        connect();
        Lab_menuPanel.setVisible(true);
        menuLabCheck.setBackground(Color.yellow);

        String sText = startTimeR1.getSelectedItem().toString();   //시작 시간 가져와서 문자열로 변수에 저장
        int sNum = sText.indexOf(":");    //":"위치 저장
        String eText = endTimeR1.getSelectedItem().toString();   //종료 시간 가져와서 문자열로 변수에 저장
        int eNum = eText.indexOf(":");    //":" 위치 저장

        if (Integer.parseInt(sText.substring(0, sNum)) >= Integer.parseInt(eText.substring(0, eNum))) { //시작시간을 종료시간보다 늦게 설정했을 경우
            JOptionPane.showMessageDialog(this, "시작 시간을 종료 시간보다 빠르게 설정하세요.", "Message", JOptionPane.ERROR_MESSAGE);
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
                sql = "select * from lecture where day=? and ((startTime <=? and endTime>?) or (startTime<? and endTime>=?) or (startTime>=? and endTime<=?))";
                pstmt = conn.prepareStatement(sql); //디비 구문과 연결

                System.out.println(getDay(reservation));
                System.out.println(reservation.startTimeR);
                System.out.println(reservation.endTimeR);

                pstmt.setInt(1, getDay(reservation));      //요일
                pstmt.setString(2, reservation.startTimeR); //시작시간
                pstmt.setString(3, reservation.endTimeR);   //종료시간
                pstmt.setString(4, reservation.startTimeR); //시작시간
                pstmt.setString(5, reservation.endTimeR);   //종료시간
                pstmt.setString(6, reservation.startTimeR); //시작시간
                pstmt.setString(7, reservation.endTimeR);   //종료시간

                rs = pstmt.executeQuery();
                if (rs.next()) { //해당 예약 정보와 겹치는 강의가 존재한다면
                    lecture = new Lecture(rs.getString("lectureName"), rs.getString("labId"));
                    System.out.println(rs.getString("lectureName"));
                }
                LabStatusPanel.setVisible(true);

                // 날짜, 시간 입력 후 사용 가능한 실습실만 콤보박스 아이템으로 넣기 (LabStatusPanel)
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
        LabStatusPanel.setVisible(true);

        // 날짜, 시간 입력 후 사용 가능한 실습실만 콤보박스 아이템으로 넣기 (LabStatusPanel)
    }//GEN-LAST:event_checkButt1ActionPerformed

    // 실습실 선택 후 확인 버튼 클릭
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        LabStatus.setVisible(true);

        // 사용 가능한 불가능한 좌석 비활성화
    }//GEN-LAST:event_jButton10ActionPerformed

    // 회원정보 메뉴바 -  회원 정보 삭제 선택 시 
    private void menuUserDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuUserDeleteMouseClicked
        reset();
        UserInfo_menuPanel.setVisible(true);
        menuUserDelete.setBackground(Color.yellow);
        UserDeletePanel.setVisible(true);
    }//GEN-LAST:event_menuUserDeleteMouseClicked

    // 회원정보 메뉴바 -  회원 정보 수정 선택 시 
    private void menuUserChangeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuUserChangeMouseClicked
        reset();
        UserInfo_menuPanel.setVisible(true);
        menuUserChange.setBackground(Color.yellow);
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
        // TODO add your handling code here:
    }//GEN-LAST:event_MyReserCheckButtActionPerformed

    // 본인 예약 취소 버튼
    private void MyReserCancleButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MyReserCancleButtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MyReserCancleButtActionPerformed

    // 예약 사용 시작 버튼
    private void startButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtActionPerformed
        // 예약 사용 시작 버튼 클릭 시 사용시작버튼 비활성화
        startButt.setEnabled(false);

        // 사용 시작 상태 디비에 저장 
    }//GEN-LAST:event_startButtActionPerformed

    //예약 퇴실 버튼
    private void endButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endButtActionPerformed
        if (startButt.isEnabled() == true) {  // 예약 사용 시작을 안했다면 
            System.out.printf("No end!!");  // 퇴실 불가능
        }

        // 퇴실 상태 디비에 저장
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

                    pstmt.setString(1, "stu1");  // 아이디 
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

        // 5시 이후 팀 예약 좌석 활성화
        for (int i = 0; i < 30; i++) {
            afterseatT.get(i).setEnabled(true);
        }

        if (jComboBox8.getSelectedIndex() == -1) {  // 강의실이 선택되지 않았을 경우
            JOptionPane.showMessageDialog(this, "강의실이 선택되지 않았습니다.");
        } else {  // 강의실이 선택된 경우
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
    private javax.swing.JPanel LabCheckPanel;
    private javax.swing.JComboBox<String> LabComboBox;
    private javax.swing.JPanel LabNoticePanel;
    private javax.swing.JPanel LabStatus;
    private javax.swing.JPanel LabStatusPanel;
    private javax.swing.JPanel LabTTCheckPanel;
    private javax.swing.JPanel Lab_menuPanel;
    private javax.swing.JTextField MM_Text1;
    private javax.swing.JButton MyReserCancleButt;
    private javax.swing.JButton MyReserCheckButt;
    private javax.swing.JPanel OverLabCheckPanel;
    private javax.swing.JPanel OverReser;
    private javax.swing.JPanel ReserPanel;
    private javax.swing.JPanel Reser_menuPanel;
    private javax.swing.JPanel TT;
    private javax.swing.JButton TTCheckButt;
    private javax.swing.JPanel TitlePanel;
    private javax.swing.JButton UserChangeButt;
    private javax.swing.JPanel UserChangePanel;
    private javax.swing.JButton UserDeleteButt;
    private javax.swing.JPanel UserDeletePanel;
    private javax.swing.JPanel UserInfo_menuPanel;
    private javax.swing.JTextField YYYY_Text1;
    private javax.swing.JPanel afterReser;
    private javax.swing.JRadioButton afterReserRadio;
    private javax.swing.JPanel afterSeatStatePanel;
    private javax.swing.JPanel beforeReser;
    private javax.swing.JRadioButton beforeReserRadio;
    private javax.swing.JPanel beforeSeatStatePanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JPanel check;
    private javax.swing.JButton checkButt;
    private javax.swing.JButton checkButt1;
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
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
    private javax.swing.JTextField jTextField2;
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

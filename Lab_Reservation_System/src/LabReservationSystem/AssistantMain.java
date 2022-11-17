/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabReservationSystem;

import java.awt.Color;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import source.Lecture;
import source.Seminar;
import source.Manager;
import source.Reservation;

/**
 *
 * @author asdf0
 */
public class AssistantMain extends javax.swing.JFrame {

    
    Color yellow = new Color(254,255,233);  // 노란색 저장
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql; //쿼리문 받을 변수
    
    Lecture lecture;    //강의 정보를 저장할 객체
    Seminar seminar;
    Manager manager;
    /**
     * Creates new form ProfessorMain
     */
    
    
    public String getDayString(int day){
        String dayString = null;
        if(day==1)
            dayString="월";
        if(day==2)
            dayString="화";
        if(day==2)
            dayString="수";
        if(day==2)
            dayString="목";
        if(day==2)
            dayString="금";
        if(day==2)
            dayString="토";
        if(day==2)
            dayString="일";
        
        return dayString;
    }
    
    public int getDay(Seminar seminar){ //요일 구하기
        //dateR은 "yyyy/mm/dd" 형식으로 된 string type으로 받는다.
        int yNum=seminar.dateS.indexOf("/"); //4
        int mNum=seminar.dateS.indexOf("/",yNum+1);//7
        
        int year=Integer.parseInt(seminar.dateS.substring(0,yNum));    //년
        int month=Integer.parseInt(seminar.dateS.substring(yNum+1,mNum));   //월
        int day=Integer.parseInt(seminar.dateS.substring(mNum+1));    //일

        LocalDate date =LocalDate.of(year,month,day);   //date에 날짜 저장
        DayOfWeek dayOfWeek =date.getDayOfWeek();
        int dayOfWeekNumber=dayOfWeek.getValue(); //날짜에 대한 요일을 숫자형식으로
        return dayOfWeekNumber;                   //1: 월, 2: 화, 3: 수, ....
    }
    
    public AssistantMain() {
        initComponents();
        
        TitlePanel.setVisible(true);
        mainPanel.setVisible(true);
        
        // 예약 관리
        ReserManage_menuPanel.setVisible(false);
        
        ReserCheckPanel.setVisible(false);
        SeatReserCheckPanel.setVisible(false);
        ListCheckPanel.setVisible(false);
        seatCheckPanel.setVisible(false);
        SeatReserCanclePanel.setVisible(false);
        
        // 시간표 관리
        TimeTable_menuPanel.setVisible(false);
        
        TimeTableAddPanel.setVisible(false);
        SeminarAddPanel.setVisible(false);
        TimeTableCheckPanel.setVisible(false);
        TT.setVisible(false);
        seminarReserCheckPanel.setVisible(false);
        
        // 실습실 관리
        LabManage_menuPanel.setVisible(false);
        
        LabStatusPanel.setVisible(false);
        ManageRightPanel.setVisible(false);
        NoticeAddPanel.setVisible(false);
        seatStatusPanel.setVisible(false);
        
        // 회원 정보 관리
        UserManage_menuPanel.setVisible(false);
        
        UserInfoPanel.setVisible(false);
        UserDeletePanel.setVisible(false);
        UserChangePanel.setVisible(false);
        
        // 승인 및 초기화 관리
        Manage_menuPanel.setVisible(false);
        
        TokenPanel.setVisible(false);
        UserOkPanel.setVisible(false);
        UserResetPanel.setVisible(false);
        TTResetPanel.setVisible(false);
        
    }

    
    // 화면에 띄우는 패널들 초기화하는 함수
    public void reset() {
        TitlePanel.setVisible(true);
        mainPanel.setVisible(false);
        
        // 예약 관리
        ReserManage_menuPanel.setVisible(false);
        menuReserCheck.setBackground(Color.WHITE);
        menuseatReserCheck.setBackground(Color.WHITE);
        menuListCheck.setBackground(Color.WHITE);
       
        ReserCheckPanel.setVisible(false);
        SeatReserCheckPanel.setVisible(false);
        ListCheckPanel.setVisible(false);
        seatCheckPanel.setVisible(false);
        SeatReserCanclePanel.setVisible(false);
        
        // 시간표 관리
        TimeTable_menuPanel.setVisible(false);
        menuTimeTableAdd.setBackground(Color.WHITE);
        menuSeminarAdd.setBackground(Color.WHITE);
        menuTimeTableCheck.setBackground(Color.WHITE);
        
        TimeTableAddPanel.setVisible(false);
        SeminarAddPanel.setVisible(false);
        TimeTableCheckPanel.setVisible(false);
        TT.setVisible(false);
        seminarReserCheckPanel.setVisible(false);
        
        // 실습실 관리
        LabManage_menuPanel.setVisible(false);
        menuLabStatus.setBackground(Color.WHITE);
        menuManageRight.setBackground(Color.WHITE);
        menuNoticeAdd.setBackground(Color.WHITE);
        
        LabStatusPanel.setVisible(false);
        ManageRightPanel.setVisible(false);
        NoticeAddPanel.setVisible(false);
        seatStatusPanel.setVisible(false);
        
        // 회원 정보 관리
        UserManage_menuPanel.setVisible(false);
        menuUserDelete.setBackground(Color.WHITE);
        menuUserChange.setBackground(Color.WHITE);
        
        UserInfoPanel.setVisible(false);
        UserDeletePanel.setVisible(false);
        UserChangePanel.setVisible(false);
        
        // 승인 및 초기화 관리
        Manage_menuPanel.setVisible(false);
        menuToken.setBackground(Color.WHITE);
        menuUserOk.setBackground(Color.WHITE);
        menuUserReset.setBackground(Color.WHITE);
        menuTTReset.setBackground(Color.WHITE);
        
        TokenPanel.setVisible(false);
        UserOkPanel.setVisible(false);
        UserResetPanel.setVisible(false);
        TTResetPanel.setVisible(false);
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        timeTableManageButt = new javax.swing.JButton();
        manageButt = new javax.swing.JButton();
        reservationManageButt = new javax.swing.JButton();
        noticeManageButt = new javax.swing.JButton();
        userInforManageButt = new javax.swing.JButton();
        labManageButt = new javax.swing.JButton();
        TitlePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textField1 = new java.awt.TextField();
        label1 = new java.awt.Label();
        jLabel2 = new javax.swing.JLabel();
        ReserManage_menuPanel = new javax.swing.JPanel();
        menuseatReserCheck = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        menuListCheck = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        menuReserCheck = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ReserCheckPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        ReserOkButt = new javax.swing.JButton();
        ListCheckPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        ListCheckButt = new javax.swing.JButton();
        ListCancleButt = new javax.swing.JButton();
        SeatReserCheckPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        labNum = new javax.swing.JComboBox<>();
        dateNum = new javax.swing.JTextField();
        SeatCheckButt = new javax.swing.JButton();
        seatCheckPanel = new javax.swing.JPanel();
        seat2 = new javax.swing.JButton();
        seat1 = new javax.swing.JButton();
        seat3 = new javax.swing.JButton();
        seat4 = new javax.swing.JButton();
        seat5 = new javax.swing.JButton();
        seat6 = new javax.swing.JButton();
        seat7 = new javax.swing.JButton();
        seat8 = new javax.swing.JButton();
        seat9 = new javax.swing.JButton();
        seat10 = new javax.swing.JButton();
        seat11 = new javax.swing.JButton();
        seat12 = new javax.swing.JButton();
        seat13 = new javax.swing.JButton();
        seat14 = new javax.swing.JButton();
        seat15 = new javax.swing.JButton();
        seat16 = new javax.swing.JButton();
        seat17 = new javax.swing.JButton();
        seat18 = new javax.swing.JButton();
        seat19 = new javax.swing.JButton();
        seat20 = new javax.swing.JButton();
        seat25 = new javax.swing.JButton();
        seat26 = new javax.swing.JButton();
        seat27 = new javax.swing.JButton();
        seat21 = new javax.swing.JButton();
        seat22 = new javax.swing.JButton();
        seat23 = new javax.swing.JButton();
        seat24 = new javax.swing.JButton();
        seat28 = new javax.swing.JButton();
        seat29 = new javax.swing.JButton();
        seat30 = new javax.swing.JButton();
        SeatReserCanclePanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        SeatReserCancle = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        lab = new javax.swing.JTextField();
        date = new javax.swing.JTextField();
        seatNum = new javax.swing.JTextField();
        TimeTable_menuPanel = new javax.swing.JPanel();
        menuTimeTableCheck = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        menuSeminarAdd = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        menuTimeTableAdd = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        TimeTableAddPanel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField5 = new javax.swing.JTextField();
        TimeTableAddButt = new javax.swing.JButton();
        jComboBox8 = new javax.swing.JComboBox<>();
        jComboBox9 = new javax.swing.JComboBox<>();
        SeminarAddPanel = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        SDateText = new javax.swing.JTextField();
        seminarCheckButt = new javax.swing.JButton();
        SStartTime = new javax.swing.JComboBox<>();
        SEndTime = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        SNameText = new javax.swing.JTextField();
        TimeTableCheckPanel = new javax.swing.JPanel();
        jComboBox4 = new javax.swing.JComboBox<>();
        TTCheckButt = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        TT = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        seminarReserCheckPanel = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        SDate = new javax.swing.JTextField();
        SStart = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        SEnd = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel34 = new javax.swing.JLabel();
        SeminarAddButt = new javax.swing.JButton();
        LabManage_menuPanel = new javax.swing.JPanel();
        menuNoticeAdd = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        menuManageRight = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        menuLabStatus = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        LabStatusPanel = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        LabCheckButt = new javax.swing.JButton();
        seatStatusPanel = new javax.swing.JPanel();
        s1 = new javax.swing.JButton();
        s2 = new javax.swing.JButton();
        s3 = new javax.swing.JButton();
        s4 = new javax.swing.JButton();
        s5 = new javax.swing.JButton();
        s6 = new javax.swing.JButton();
        s7 = new javax.swing.JButton();
        s8 = new javax.swing.JButton();
        s9 = new javax.swing.JButton();
        s10 = new javax.swing.JButton();
        s11 = new javax.swing.JButton();
        s12 = new javax.swing.JButton();
        s13 = new javax.swing.JButton();
        s14 = new javax.swing.JButton();
        s15 = new javax.swing.JButton();
        s16 = new javax.swing.JButton();
        s17 = new javax.swing.JButton();
        s18 = new javax.swing.JButton();
        s19 = new javax.swing.JButton();
        s20 = new javax.swing.JButton();
        s25 = new javax.swing.JButton();
        s26 = new javax.swing.JButton();
        s27 = new javax.swing.JButton();
        s21 = new javax.swing.JButton();
        s22 = new javax.swing.JButton();
        s23 = new javax.swing.JButton();
        s24 = new javax.swing.JButton();
        s28 = new javax.swing.JButton();
        s29 = new javax.swing.JButton();
        s30 = new javax.swing.JButton();
        ManageRightPanel = new javax.swing.JPanel();
        jComboBox5 = new javax.swing.JComboBox<>();
        LabRightCheckButt = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        RightButt = new javax.swing.JButton();
        NoticeAddPanel = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        UserManage_menuPanel = new javax.swing.JPanel();
        menuUserChange = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        menuUserDelete = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        UserDeletePanel = new javax.swing.JPanel();
        DeleteButt = new javax.swing.JButton();
        jTextField18 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        UserChangePanel = new javax.swing.JPanel();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        ChangeButt = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        UserInfoPanel = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        UserDeleteButt = new javax.swing.JButton();
        UserChangeButt = new javax.swing.JButton();
        Manage_menuPanel = new javax.swing.JPanel();
        menuToken = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        menuUserOk = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        menuUserReset = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        menuTTReset = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        TokenPanel = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        TokenOkButt = new javax.swing.JButton();
        TokenResetButt = new javax.swing.JButton();
        UserOkPanel = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        UserOkButt = new javax.swing.JButton();
        UserResetPanel = new javax.swing.JPanel();
        UserResetButt = new javax.swing.JButton();
        TTResetPanel = new javax.swing.JPanel();
        TTResetButt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        timeTableManageButt.setBackground(new java.awt.Color(255, 255, 255));
        timeTableManageButt.setForeground(new java.awt.Color(255, 255, 255));
        timeTableManageButt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ButtPicture/조교_시간표관리 버튼.PNG"))); // NOI18N
        timeTableManageButt.setBorderPainted(false);
        timeTableManageButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeTableManageButtActionPerformed(evt);
            }
        });

        manageButt.setBackground(new java.awt.Color(255, 255, 255));
        manageButt.setForeground(new java.awt.Color(255, 255, 255));
        manageButt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ButtPicture/조교_승인 및 초기화 관리 버튼.PNG"))); // NOI18N
        manageButt.setBorderPainted(false);
        manageButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageButtActionPerformed(evt);
            }
        });

        reservationManageButt.setBackground(new java.awt.Color(255, 255, 255));
        reservationManageButt.setForeground(new java.awt.Color(255, 255, 255));
        reservationManageButt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ButtPicture/조교_예약 관리 버튼.PNG"))); // NOI18N
        reservationManageButt.setBorderPainted(false);
        reservationManageButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reservationManageButtActionPerformed(evt);
            }
        });

        noticeManageButt.setBackground(new java.awt.Color(255, 255, 255));
        noticeManageButt.setForeground(new java.awt.Color(255, 255, 255));
        noticeManageButt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ButtPicture/조교_공지사항 및 신고관리 버튼.PNG"))); // NOI18N
        noticeManageButt.setBorderPainted(false);
        noticeManageButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noticeManageButtActionPerformed(evt);
            }
        });

        userInforManageButt.setBackground(new java.awt.Color(255, 255, 255));
        userInforManageButt.setForeground(new java.awt.Color(255, 255, 255));
        userInforManageButt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ButtPicture/조교_회원정보관리 버튼.PNG"))); // NOI18N
        userInforManageButt.setBorderPainted(false);
        userInforManageButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userInforManageButtActionPerformed(evt);
            }
        });

        labManageButt.setBackground(new java.awt.Color(255, 255, 255));
        labManageButt.setForeground(new java.awt.Color(255, 255, 255));
        labManageButt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ButtPicture/조교_실습실관리 버튼.PNG"))); // NOI18N
        labManageButt.setBorderPainted(false);
        labManageButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labManageButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(reservationManageButt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labManageButt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(110, 110, 110)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timeTableManageButt, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(noticeManageButt, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(110, 110, 110)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(userInforManageButt)
                    .addComponent(manageButt))
                .addGap(80, 80, 80))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timeTableManageButt, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(reservationManageButt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userInforManageButt, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(16, 16, 16)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labManageButt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(noticeManageButt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manageButt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1110, 620));

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
        textField1.setText("안수진");
        textField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField1ActionPerformed(evt);
            }
        });

        label1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label1.setText("님");

        jLabel2.setText("(조교)");

        javax.swing.GroupLayout TitlePanelLayout = new javax.swing.GroupLayout(TitlePanel);
        TitlePanel.setLayout(TitlePanelLayout);
        TitlePanelLayout.setHorizontalGroup(
            TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitlePanelLayout.createSequentialGroup()
                .addContainerGap(245, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(137, 137, 137)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        TitlePanelLayout.setVerticalGroup(
            TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TitlePanelLayout.createSequentialGroup()
                .addGroup(TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(TitlePanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(TitlePanelLayout.createSequentialGroup()
                        .addContainerGap(71, Short.MAX_VALUE)
                        .addGroup(TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, TitlePanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(TitlePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 110));

        ReserManage_menuPanel.setBackground(new java.awt.Color(255, 255, 255));
        ReserManage_menuPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        menuseatReserCheck.setBackground(new java.awt.Color(255, 255, 255));
        menuseatReserCheck.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuseatReserCheck.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuseatReserCheck.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        menuseatReserCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuseatReserCheckMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel4.setText("실습실 좌석별");

        jLabel7.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel7.setText("예약 조회 및 취소");

        javax.swing.GroupLayout menuseatReserCheckLayout = new javax.swing.GroupLayout(menuseatReserCheck);
        menuseatReserCheck.setLayout(menuseatReserCheckLayout);
        menuseatReserCheckLayout.setHorizontalGroup(
            menuseatReserCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuseatReserCheckLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(menuseatReserCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuseatReserCheckLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuseatReserCheckLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(31, 31, 31))))
        );
        menuseatReserCheckLayout.setVerticalGroup(
            menuseatReserCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuseatReserCheckLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuListCheck.setBackground(new java.awt.Color(255, 255, 255));
        menuListCheck.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuListCheck.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuListCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuListCheckMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel5.setText("예약 리스트 조회 및 취소");

        javax.swing.GroupLayout menuListCheckLayout = new javax.swing.GroupLayout(menuListCheck);
        menuListCheck.setLayout(menuListCheckLayout);
        menuListCheckLayout.setHorizontalGroup(
            menuListCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuListCheckLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );
        menuListCheckLayout.setVerticalGroup(
            menuListCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuListCheckLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuReserCheck.setBackground(new java.awt.Color(255, 255, 255));
        menuReserCheck.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuReserCheck.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuReserCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuReserCheckMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel6.setText("예약 승인");

        javax.swing.GroupLayout menuReserCheckLayout = new javax.swing.GroupLayout(menuReserCheck);
        menuReserCheck.setLayout(menuReserCheckLayout);
        menuReserCheckLayout.setHorizontalGroup(
            menuReserCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuReserCheckLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuReserCheckLayout.setVerticalGroup(
            menuReserCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuReserCheckLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("맑은 고딕", 0, 36)); // NOI18N
        jLabel3.setText("예약관리");

        javax.swing.GroupLayout ReserManage_menuPanelLayout = new javax.swing.GroupLayout(ReserManage_menuPanel);
        ReserManage_menuPanel.setLayout(ReserManage_menuPanelLayout);
        ReserManage_menuPanelLayout.setHorizontalGroup(
            ReserManage_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuseatReserCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuListCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuReserCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReserManage_menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ReserManage_menuPanelLayout.setVerticalGroup(
            ReserManage_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReserManage_menuPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel3)
                .addGap(33, 33, 33)
                .addComponent(menuReserCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuListCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuseatReserCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(273, Short.MAX_VALUE))
        );

        getContentPane().add(ReserManage_menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 150, 620));

        ReserCheckPanel.setBackground(new java.awt.Color(254, 255, 233));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "강의실", "날짜", "시작시간", "종료시간", "학번", "이름"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionBackground(new java.awt.Color(246, 226, 231));
        jScrollPane1.setViewportView(jTable1);

        ReserOkButt.setBackground(new java.awt.Color(255, 255, 255));
        ReserOkButt.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        ReserOkButt.setText("승인");
        ReserOkButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReserOkButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ReserCheckPanelLayout = new javax.swing.GroupLayout(ReserCheckPanel);
        ReserCheckPanel.setLayout(ReserCheckPanelLayout);
        ReserCheckPanelLayout.setHorizontalGroup(
            ReserCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReserCheckPanelLayout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(ReserCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ReserOkButt)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 867, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        ReserCheckPanelLayout.setVerticalGroup(
            ReserCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReserCheckPanelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(ReserOkButt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        getContentPane().add(ReserCheckPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        ListCheckPanel.setBackground(new java.awt.Color(254, 255, 233));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "강의실", "날짜", "시작시간", "종료시간", "학번", "이름"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setSelectionBackground(new java.awt.Color(246, 226, 231));
        jScrollPane2.setViewportView(jTable2);

        jComboBox1.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "915", "916", "918", "911" }));

        jLabel8.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        jLabel8.setText("실습실 : ");

        jLabel11.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        jLabel11.setText("날짜 : ");

        jTextField1.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        jTextField1.setText("2022- 11 - 06");

        ListCheckButt.setBackground(new java.awt.Color(255, 255, 255));
        ListCheckButt.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        ListCheckButt.setText("조회");
        ListCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListCheckButtActionPerformed(evt);
            }
        });

        ListCancleButt.setBackground(new java.awt.Color(255, 255, 255));
        ListCancleButt.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        ListCancleButt.setText("취소");
        ListCancleButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListCancleButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ListCheckPanelLayout = new javax.swing.GroupLayout(ListCheckPanel);
        ListCheckPanel.setLayout(ListCheckPanelLayout);
        ListCheckPanelLayout.setHorizontalGroup(
            ListCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ListCheckPanelLayout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addGroup(ListCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ListCheckPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(ListCheckButt))
                    .addComponent(ListCancleButt))
                .addGap(40, 40, 40))
        );
        ListCheckPanelLayout.setVerticalGroup(
            ListCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ListCheckPanelLayout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(ListCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ListCheckButt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ListCancleButt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        getContentPane().add(ListCheckPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        SeatReserCheckPanel.setBackground(new java.awt.Color(254, 255, 233));

        jLabel9.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        jLabel9.setText("실습실 : ");

        jLabel12.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        jLabel12.setText("날짜 : ");

        labNum.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        labNum.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "915", "916", "918", "911" }));

        dateNum.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        dateNum.setText("2022 - 11 - 06");

        SeatCheckButt.setBackground(new java.awt.Color(255, 255, 255));
        SeatCheckButt.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        SeatCheckButt.setText("조회");
        SeatCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeatCheckButtActionPerformed(evt);
            }
        });

        seatCheckPanel.setBackground(new java.awt.Color(254, 255, 233));
        seatCheckPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        seat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat2ActionPerformed(evt);
            }
        });

        seat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat1ActionPerformed(evt);
            }
        });

        seat3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat3ActionPerformed(evt);
            }
        });

        seat4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat4ActionPerformed(evt);
            }
        });

        seat5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat5ActionPerformed(evt);
            }
        });

        seat6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat6ActionPerformed(evt);
            }
        });

        seat7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });

        seat8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat8ActionPerformed(evt);
            }
        });

        seat9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat9ActionPerformed(evt);
            }
        });

        seat10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat10ActionPerformed(evt);
            }
        });

        seat11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat11ActionPerformed(evt);
            }
        });

        seat12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat12ActionPerformed(evt);
            }
        });

        seat13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat13ActionPerformed(evt);
            }
        });

        seat14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat14ActionPerformed(evt);
            }
        });

        seat15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat15ActionPerformed(evt);
            }
        });

        seat16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat16ActionPerformed(evt);
            }
        });

        seat17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat17ActionPerformed(evt);
            }
        });

        seat18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat18ActionPerformed(evt);
            }
        });

        seat19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat19ActionPerformed(evt);
            }
        });

        seat20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat20ActionPerformed(evt);
            }
        });

        seat25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat25ActionPerformed(evt);
            }
        });

        seat26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat26ActionPerformed(evt);
            }
        });

        seat27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat27ActionPerformed(evt);
            }
        });

        seat21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat21ActionPerformed(evt);
            }
        });

        seat22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat22ActionPerformed(evt);
            }
        });

        seat23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat23ActionPerformed(evt);
            }
        });

        seat24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat24ActionPerformed(evt);
            }
        });

        seat28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat28ActionPerformed(evt);
            }
        });

        seat29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat29ActionPerformed(evt);
            }
        });

        seat30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seat7MouseClicked(evt);
            }
        });
        seat30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seat30ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout seatCheckPanelLayout = new javax.swing.GroupLayout(seatCheckPanel);
        seatCheckPanel.setLayout(seatCheckPanelLayout);
        seatCheckPanelLayout.setHorizontalGroup(
            seatCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seatCheckPanelLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(seatCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, seatCheckPanelLayout.createSequentialGroup()
                        .addComponent(seat9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat10, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat12, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                        .addComponent(seat13, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat14, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat15, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat16, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, seatCheckPanelLayout.createSequentialGroup()
                        .addComponent(seat1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(seat5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(seatCheckPanelLayout.createSequentialGroup()
                        .addGroup(seatCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(seatCheckPanelLayout.createSequentialGroup()
                                .addComponent(seat17, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seat18, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seat19, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seat20, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(seatCheckPanelLayout.createSequentialGroup()
                                .addComponent(seat25, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seat26, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seat27, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(seatCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(seatCheckPanelLayout.createSequentialGroup()
                                .addComponent(seat28, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seat29, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seat30, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(seatCheckPanelLayout.createSequentialGroup()
                                .addComponent(seat21, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seat22, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seat23, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seat24, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(92, 92, 92))
        );
        seatCheckPanelLayout.setVerticalGroup(
            seatCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seatCheckPanelLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(seatCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(seat5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(seatCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(seat9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat11, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat13, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat16, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat15, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(seatCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(seat17, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat20, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat19, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat18, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat21, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat24, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat23, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat22, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(seatCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(seat25, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat27, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat26, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat28, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat30, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat29, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout SeatReserCheckPanelLayout = new javax.swing.GroupLayout(SeatReserCheckPanel);
        SeatReserCheckPanel.setLayout(SeatReserCheckPanelLayout);
        SeatReserCheckPanelLayout.setHorizontalGroup(
            SeatReserCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SeatReserCheckPanelLayout.createSequentialGroup()
                .addGap(213, 213, 213)
                .addComponent(jLabel9)
                .addGap(27, 27, 27)
                .addComponent(labNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(SeatCheckButt)
                .addContainerGap(237, Short.MAX_VALUE))
            .addGroup(SeatReserCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SeatReserCheckPanelLayout.createSequentialGroup()
                    .addContainerGap(130, Short.MAX_VALUE)
                    .addComponent(seatCheckPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(136, Short.MAX_VALUE)))
        );
        SeatReserCheckPanelLayout.setVerticalGroup(
            SeatReserCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SeatReserCheckPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(SeatReserCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(labNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(dateNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SeatCheckButt))
                .addContainerGap(552, Short.MAX_VALUE))
            .addGroup(SeatReserCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SeatReserCheckPanelLayout.createSequentialGroup()
                    .addContainerGap(95, Short.MAX_VALUE)
                    .addComponent(seatCheckPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(51, Short.MAX_VALUE)))
        );

        getContentPane().add(SeatReserCheckPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        SeatReserCanclePanel.setBackground(new java.awt.Color(254, 255, 233));

        jLabel13.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        jLabel13.setText("강의실 : ");

        jLabel14.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        jLabel14.setText("날짜 : ");

        jLabel15.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        jLabel15.setText("좌석번호 : ");

        SeatReserCancle.setBackground(new java.awt.Color(255, 255, 255));
        SeatReserCancle.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        SeatReserCancle.setText("취소");
        SeatReserCancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeatReserCancleActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable3);

        lab.setEditable(false);
        lab.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        lab.setText("915");

        date.setEditable(false);
        date.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        date.setText("2022 - 11 - 06");

        seatNum.setFont(new java.awt.Font("굴림", 0, 14)); // NOI18N
        seatNum.setText("seat2");

        javax.swing.GroupLayout SeatReserCanclePanelLayout = new javax.swing.GroupLayout(SeatReserCanclePanel);
        SeatReserCanclePanel.setLayout(SeatReserCanclePanelLayout);
        SeatReserCanclePanelLayout.setHorizontalGroup(
            SeatReserCanclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SeatReserCanclePanelLayout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(SeatReserCanclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(SeatReserCancle)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(120, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SeatReserCanclePanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seatNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(216, 216, 216))
        );
        SeatReserCanclePanelLayout.setVerticalGroup(
            SeatReserCanclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SeatReserCanclePanelLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(SeatReserCanclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(lab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(seatNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SeatReserCancle, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        getContentPane().add(SeatReserCanclePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        TimeTable_menuPanel.setBackground(new java.awt.Color(255, 255, 255));
        TimeTable_menuPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        menuTimeTableCheck.setBackground(new java.awt.Color(255, 255, 255));
        menuTimeTableCheck.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuTimeTableCheck.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuTimeTableCheck.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        menuTimeTableCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuTimeTableCheckMouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel16.setText("실습실 별 시간표 조회");

        javax.swing.GroupLayout menuTimeTableCheckLayout = new javax.swing.GroupLayout(menuTimeTableCheck);
        menuTimeTableCheck.setLayout(menuTimeTableCheckLayout);
        menuTimeTableCheckLayout.setHorizontalGroup(
            menuTimeTableCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuTimeTableCheckLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuTimeTableCheckLayout.setVerticalGroup(
            menuTimeTableCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuTimeTableCheckLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(18, 18, 18))
        );

        menuSeminarAdd.setBackground(new java.awt.Color(255, 255, 255));
        menuSeminarAdd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuSeminarAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuSeminarAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSeminarAddMouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel18.setText("특강 시간표 입력");

        javax.swing.GroupLayout menuSeminarAddLayout = new javax.swing.GroupLayout(menuSeminarAdd);
        menuSeminarAdd.setLayout(menuSeminarAddLayout);
        menuSeminarAddLayout.setHorizontalGroup(
            menuSeminarAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuSeminarAddLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel18)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuSeminarAddLayout.setVerticalGroup(
            menuSeminarAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuSeminarAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuTimeTableAdd.setBackground(new java.awt.Color(255, 255, 255));
        menuTimeTableAdd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuTimeTableAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuTimeTableAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuTimeTableAddMouseClicked(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel19.setText("수업 시간표 입력");

        javax.swing.GroupLayout menuTimeTableAddLayout = new javax.swing.GroupLayout(menuTimeTableAdd);
        menuTimeTableAdd.setLayout(menuTimeTableAddLayout);
        menuTimeTableAddLayout.setHorizontalGroup(
            menuTimeTableAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuTimeTableAddLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuTimeTableAddLayout.setVerticalGroup(
            menuTimeTableAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuTimeTableAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel20.setFont(new java.awt.Font("맑은 고딕", 0, 36)); // NOI18N
        jLabel20.setText("관리");

        jLabel21.setFont(new java.awt.Font("맑은 고딕", 0, 36)); // NOI18N
        jLabel21.setText("시간표");

        javax.swing.GroupLayout TimeTable_menuPanelLayout = new javax.swing.GroupLayout(TimeTable_menuPanel);
        TimeTable_menuPanel.setLayout(TimeTable_menuPanelLayout);
        TimeTable_menuPanelLayout.setHorizontalGroup(
            TimeTable_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuTimeTableCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuSeminarAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuTimeTableAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(TimeTable_menuPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(TimeTable_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TimeTable_menuPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20))
                    .addComponent(jLabel21))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TimeTable_menuPanelLayout.setVerticalGroup(
            TimeTable_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimeTable_menuPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel21)
                .addGap(0, 0, 0)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addComponent(menuTimeTableAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuSeminarAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuTimeTableCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
        );

        getContentPane().add(TimeTable_menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 150, 620));

        TimeTableAddPanel.setBackground(new java.awt.Color(254, 255, 233));

        jLabel17.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel17.setText("실습실 : ");

        jComboBox2.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "915", "916", "918", "911" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel22.setText("강의명 : ");

        jLabel23.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel23.setText("시작시간 : ");

        jLabel24.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel24.setText("종료시간 : ");

        jLabel25.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel25.setText("요일 : ");

        jLabel26.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel26.setText("교수번호 : ");

        jTextField2.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N

        jComboBox3.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "월", "화", "수", "목", "금", "토", "일" }));

        jTextField5.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jTextField5.setToolTipText("");

        TimeTableAddButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        TimeTableAddButt.setText("추가");
        TimeTableAddButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TimeTableAddButtActionPerformed(evt);
            }
        });

        jComboBox8.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00" }));

        jComboBox9.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00" }));

        javax.swing.GroupLayout TimeTableAddPanelLayout = new javax.swing.GroupLayout(TimeTableAddPanel);
        TimeTableAddPanel.setLayout(TimeTableAddPanelLayout);
        TimeTableAddPanelLayout.setHorizontalGroup(
            TimeTableAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimeTableAddPanelLayout.createSequentialGroup()
                .addGap(374, 374, 374)
                .addGroup(TimeTableAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(TimeTableAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TimeTableAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField2)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField5)
                        .addComponent(TimeTableAddButt, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(425, Short.MAX_VALUE))
        );
        TimeTableAddPanelLayout.setVerticalGroup(
            TimeTableAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimeTableAddPanelLayout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addGroup(TimeTableAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(TimeTableAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(TimeTableAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(TimeTableAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(TimeTableAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addGap(18, 18, 18)
                .addGroup(TimeTableAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(TimeTableAddButt, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        getContentPane().add(TimeTableAddPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        SeminarAddPanel.setBackground(new java.awt.Color(254, 255, 233));

        jLabel28.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel28.setText("시작시간 : ");

        jLabel29.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel29.setText("종료 시간 : ");

        jLabel30.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel30.setText("날짜 : ");

        SDateText.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        SDateText.setText("2022/11/06");

        seminarCheckButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        seminarCheckButt.setText("조회");
        seminarCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seminarCheckButtActionPerformed(evt);
            }
        });

        SStartTime.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        SStartTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00" }));

        SEndTime.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        SEndTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00" }));

        jLabel37.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel37.setText("세미나명 : ");

        SNameText.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        SNameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SNameTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SeminarAddPanelLayout = new javax.swing.GroupLayout(SeminarAddPanel);
        SeminarAddPanel.setLayout(SeminarAddPanelLayout);
        SeminarAddPanelLayout.setHorizontalGroup(
            SeminarAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SeminarAddPanelLayout.createSequentialGroup()
                .addGap(365, 365, 365)
                .addGroup(SeminarAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(SeminarAddPanelLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SDateText))
                    .addGroup(SeminarAddPanelLayout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SNameText))
                    .addGroup(SeminarAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(seminarCheckButt)
                        .addGroup(SeminarAddPanelLayout.createSequentialGroup()
                            .addGroup(SeminarAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel28)
                                .addComponent(jLabel29))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(SeminarAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(SEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(SStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(449, Short.MAX_VALUE))
        );
        SeminarAddPanelLayout.setVerticalGroup(
            SeminarAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SeminarAddPanelLayout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addGroup(SeminarAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(SDateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(SeminarAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(SStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(SeminarAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(SEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(SeminarAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(SNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(seminarCheckButt)
                .addContainerGap(201, Short.MAX_VALUE))
        );

        getContentPane().add(SeminarAddPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        TimeTableCheckPanel.setBackground(new java.awt.Color(254, 255, 233));

        jComboBox4.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "915", "916", "918", "911" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        TTCheckButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        TTCheckButt.setText("조회");
        TTCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TTCheckButtActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel27.setText("실습실 : ");

        TT.setBackground(new java.awt.Color(254, 255, 233));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "요일", "강의명", "교수명", "시작 시간", "종료 시간"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

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

        javax.swing.GroupLayout TimeTableCheckPanelLayout = new javax.swing.GroupLayout(TimeTableCheckPanel);
        TimeTableCheckPanel.setLayout(TimeTableCheckPanelLayout);
        TimeTableCheckPanelLayout.setHorizontalGroup(
            TimeTableCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimeTableCheckPanelLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(TTCheckButt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TimeTableCheckPanelLayout.createSequentialGroup()
                .addContainerGap(90, Short.MAX_VALUE)
                .addComponent(TT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );
        TimeTableCheckPanelLayout.setVerticalGroup(
            TimeTableCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimeTableCheckPanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(TimeTableCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TTCheckButt)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addComponent(TT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        getContentPane().add(TimeTableCheckPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        seminarReserCheckPanel.setBackground(new java.awt.Color(254, 255, 233));

        jLabel31.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel31.setText("날짜 : ");

        jLabel32.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel32.setText("시작시간 : ");

        SDate.setEditable(false);
        SDate.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        SDate.setText("2022-10-10");

        SStart.setEditable(false);
        SStart.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        SStart.setText("jTextField10");

        jLabel33.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel33.setText("종료시간 : ");

        SEnd.setEditable(false);
        SEnd.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        SEnd.setText("jTextField11");
        SEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEndActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(jList1);

        jLabel34.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel34.setText("사용 가능한 강의실");

        SeminarAddButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        SeminarAddButt.setText("추가");
        SeminarAddButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeminarAddButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout seminarReserCheckPanelLayout = new javax.swing.GroupLayout(seminarReserCheckPanel);
        seminarReserCheckPanel.setLayout(seminarReserCheckPanelLayout);
        seminarReserCheckPanelLayout.setHorizontalGroup(
            seminarReserCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seminarReserCheckPanelLayout.createSequentialGroup()
                .addGroup(seminarReserCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(seminarReserCheckPanelLayout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(seminarReserCheckPanelLayout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(jLabel34)
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(SeminarAddButt)))
                .addContainerGap(200, Short.MAX_VALUE))
        );
        seminarReserCheckPanelLayout.setVerticalGroup(
            seminarReserCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seminarReserCheckPanelLayout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(seminarReserCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(SDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(SEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(105, 105, 105)
                .addGroup(seminarReserCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(SeminarAddButt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(245, Short.MAX_VALUE))
        );

        getContentPane().add(seminarReserCheckPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        LabManage_menuPanel.setBackground(new java.awt.Color(255, 255, 255));
        LabManage_menuPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        menuNoticeAdd.setBackground(new java.awt.Color(255, 255, 255));
        menuNoticeAdd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuNoticeAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuNoticeAdd.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        menuNoticeAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuNoticeAddMouseClicked(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel40.setText("실습실 공지사항 및");

        jLabel45.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel45.setText("규칙 입력");

        javax.swing.GroupLayout menuNoticeAddLayout = new javax.swing.GroupLayout(menuNoticeAdd);
        menuNoticeAdd.setLayout(menuNoticeAddLayout);
        menuNoticeAddLayout.setHorizontalGroup(
            menuNoticeAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuNoticeAddLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(menuNoticeAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40)
                    .addGroup(menuNoticeAddLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel45)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuNoticeAddLayout.setVerticalGroup(
            menuNoticeAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuNoticeAddLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel45)
                .addContainerGap())
        );

        menuManageRight.setBackground(new java.awt.Color(255, 255, 255));
        menuManageRight.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuManageRight.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuManageRight.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuManageRightMouseClicked(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel41.setText("실습실 관리권한 부여");

        javax.swing.GroupLayout menuManageRightLayout = new javax.swing.GroupLayout(menuManageRight);
        menuManageRight.setLayout(menuManageRightLayout);
        menuManageRightLayout.setHorizontalGroup(
            menuManageRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuManageRightLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        menuManageRightLayout.setVerticalGroup(
            menuManageRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuManageRightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuLabStatus.setBackground(new java.awt.Color(255, 255, 255));
        menuLabStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuLabStatus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuLabStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLabStatusMouseClicked(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel42.setText("실습실 사용현황 조회");

        javax.swing.GroupLayout menuLabStatusLayout = new javax.swing.GroupLayout(menuLabStatus);
        menuLabStatus.setLayout(menuLabStatusLayout);
        menuLabStatusLayout.setHorizontalGroup(
            menuLabStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLabStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuLabStatusLayout.setVerticalGroup(
            menuLabStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLabStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel43.setFont(new java.awt.Font("맑은 고딕", 0, 36)); // NOI18N
        jLabel43.setText("관리");

        jLabel44.setFont(new java.awt.Font("맑은 고딕", 0, 36)); // NOI18N
        jLabel44.setText("실습실");

        javax.swing.GroupLayout LabManage_menuPanelLayout = new javax.swing.GroupLayout(LabManage_menuPanel);
        LabManage_menuPanel.setLayout(LabManage_menuPanelLayout);
        LabManage_menuPanelLayout.setHorizontalGroup(
            LabManage_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuNoticeAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuManageRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuLabStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(LabManage_menuPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(LabManage_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LabManage_menuPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel43))
                    .addComponent(jLabel44))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        LabManage_menuPanelLayout.setVerticalGroup(
            LabManage_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LabManage_menuPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel44)
                .addGap(0, 0, 0)
                .addComponent(jLabel43)
                .addGap(18, 18, 18)
                .addComponent(menuLabStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuManageRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuNoticeAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(242, Short.MAX_VALUE))
        );

        getContentPane().add(LabManage_menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 150, 620));

        LabStatusPanel.setBackground(new java.awt.Color(254, 255, 233));

        jLabel39.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel39.setText("강의실 : ");

        jComboBox7.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "915", "916", "918", "911" }));

        LabCheckButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        LabCheckButt.setText("조회");
        LabCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LabCheckButtActionPerformed(evt);
            }
        });

        seatStatusPanel.setBackground(new java.awt.Color(254, 255, 233));
        seatStatusPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout seatStatusPanelLayout = new javax.swing.GroupLayout(seatStatusPanel);
        seatStatusPanel.setLayout(seatStatusPanelLayout);
        seatStatusPanelLayout.setHorizontalGroup(
            seatStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seatStatusPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(seatStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, seatStatusPanelLayout.createSequentialGroup()
                        .addComponent(s9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(s10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(s11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(s12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(s13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(s14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(s15, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(s16, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, seatStatusPanelLayout.createSequentialGroup()
                        .addComponent(s1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(s2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(s3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(s4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(s5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(s6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(s7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(s8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(seatStatusPanelLayout.createSequentialGroup()
                        .addGroup(seatStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(seatStatusPanelLayout.createSequentialGroup()
                                .addComponent(s17, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(s18, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(s19, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(s20, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(seatStatusPanelLayout.createSequentialGroup()
                                .addComponent(s25, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(s26, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(s27, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(100, 100, 100)
                        .addGroup(seatStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(seatStatusPanelLayout.createSequentialGroup()
                                .addComponent(s28, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(s29, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(s30, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(seatStatusPanelLayout.createSequentialGroup()
                                .addComponent(s21, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(s22, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(s23, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(s24, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(15, 15, 15))
        );
        seatStatusPanelLayout.setVerticalGroup(
            seatStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seatStatusPanelLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(seatStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(seatStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s11, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s13, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s16, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s15, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(seatStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s17, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s20, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s19, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s18, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s21, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s24, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s23, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s22, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(seatStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s25, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s27, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s26, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s28, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s30, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(s29, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(80, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout LabStatusPanelLayout = new javax.swing.GroupLayout(LabStatusPanel);
        LabStatusPanel.setLayout(LabStatusPanelLayout);
        LabStatusPanelLayout.setHorizontalGroup(
            LabStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LabStatusPanelLayout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(seatStatusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(LabStatusPanelLayout.createSequentialGroup()
                .addGap(327, 327, 327)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(LabCheckButt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LabStatusPanelLayout.setVerticalGroup(
            LabStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LabStatusPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(LabStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabCheckButt))
                .addGap(34, 34, 34)
                .addComponent(seatStatusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        getContentPane().add(LabStatusPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        ManageRightPanel.setBackground(new java.awt.Color(254, 255, 233));

        jComboBox5.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "915", "916", "918", "911" }));

        LabRightCheckButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        LabRightCheckButt.setText("조회");
        LabRightCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LabRightCheckButtActionPerformed(evt);
            }
        });

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(jTable5);

        RightButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        RightButt.setText("관리권한부여");
        RightButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RightButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ManageRightPanelLayout = new javax.swing.GroupLayout(ManageRightPanel);
        ManageRightPanel.setLayout(ManageRightPanelLayout);
        ManageRightPanelLayout.setHorizontalGroup(
            ManageRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ManageRightPanelLayout.createSequentialGroup()
                .addContainerGap(97, Short.MAX_VALUE)
                .addGroup(ManageRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ManageRightPanelLayout.createSequentialGroup()
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(LabRightCheckButt))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RightButt))
                .addGap(80, 80, 80))
        );
        ManageRightPanelLayout.setVerticalGroup(
            ManageRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManageRightPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(ManageRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LabRightCheckButt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ManageRightPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RightButt, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addGap(41, 41, 41))
        );

        getContentPane().add(ManageRightPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        NoticeAddPanel.setBackground(new java.awt.Color(254, 255, 233));

        jLabel35.setText("실습실 공지사항 및 규칙 입력");

        javax.swing.GroupLayout NoticeAddPanelLayout = new javax.swing.GroupLayout(NoticeAddPanel);
        NoticeAddPanel.setLayout(NoticeAddPanelLayout);
        NoticeAddPanelLayout.setHorizontalGroup(
            NoticeAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NoticeAddPanelLayout.createSequentialGroup()
                .addContainerGap(419, Short.MAX_VALUE)
                .addComponent(jLabel35)
                .addGap(381, 381, 381))
        );
        NoticeAddPanelLayout.setVerticalGroup(
            NoticeAddPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NoticeAddPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addContainerGap(595, Short.MAX_VALUE))
        );

        getContentPane().add(NoticeAddPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        UserManage_menuPanel.setBackground(new java.awt.Color(255, 255, 255));
        UserManage_menuPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        menuUserChange.setBackground(new java.awt.Color(255, 255, 255));
        menuUserChange.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuUserChange.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuUserChange.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuUserChangeMouseClicked(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel48.setText("회원 정보 수정");

        javax.swing.GroupLayout menuUserChangeLayout = new javax.swing.GroupLayout(menuUserChange);
        menuUserChange.setLayout(menuUserChangeLayout);
        menuUserChangeLayout.setHorizontalGroup(
            menuUserChangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuUserChangeLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel48)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuUserChangeLayout.setVerticalGroup(
            menuUserChangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuUserChangeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuUserDelete.setBackground(new java.awt.Color(255, 255, 255));
        menuUserDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuUserDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuUserDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuUserDeleteMouseClicked(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel49.setText("회원 정보 삭제");

        javax.swing.GroupLayout menuUserDeleteLayout = new javax.swing.GroupLayout(menuUserDelete);
        menuUserDelete.setLayout(menuUserDeleteLayout);
        menuUserDeleteLayout.setHorizontalGroup(
            menuUserDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuUserDeleteLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel49)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuUserDeleteLayout.setVerticalGroup(
            menuUserDeleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuUserDeleteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel50.setFont(new java.awt.Font("맑은 고딕", 0, 36)); // NOI18N
        jLabel50.setText("관리");

        jLabel51.setFont(new java.awt.Font("맑은 고딕", 0, 36)); // NOI18N
        jLabel51.setText("회원정보");

        javax.swing.GroupLayout UserManage_menuPanelLayout = new javax.swing.GroupLayout(UserManage_menuPanel);
        UserManage_menuPanel.setLayout(UserManage_menuPanelLayout);
        UserManage_menuPanelLayout.setHorizontalGroup(
            UserManage_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuUserChange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuUserDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(UserManage_menuPanelLayout.createSequentialGroup()
                .addComponent(jLabel51)
                .addGap(0, 4, Short.MAX_VALUE))
            .addGroup(UserManage_menuPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel50)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        UserManage_menuPanelLayout.setVerticalGroup(
            UserManage_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserManage_menuPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel51)
                .addGap(0, 0, 0)
                .addComponent(jLabel50)
                .addGap(18, 18, 18)
                .addComponent(menuUserDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuUserChange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(322, Short.MAX_VALUE))
        );

        getContentPane().add(UserManage_menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 150, 620));

        UserDeletePanel.setBackground(new java.awt.Color(254, 255, 233));

        DeleteButt.setFont(new java.awt.Font("맑은 고딕", 0, 16)); // NOI18N
        DeleteButt.setText("회원 정보 삭제");
        DeleteButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtActionPerformed(evt);
            }
        });

        jTextField18.setFont(new java.awt.Font("맑은 고딕", 0, 16)); // NOI18N
        jTextField18.setText("홍길동");

        jLabel55.setFont(new java.awt.Font("맑은 고딕", 0, 16)); // NOI18N
        jLabel55.setText("님의 회원 정보 삭제하시겠습니까?");

        javax.swing.GroupLayout UserDeletePanelLayout = new javax.swing.GroupLayout(UserDeletePanel);
        UserDeletePanel.setLayout(UserDeletePanelLayout);
        UserDeletePanelLayout.setHorizontalGroup(
            UserDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserDeletePanelLayout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(UserDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel55)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserDeletePanelLayout.createSequentialGroup()
                        .addComponent(DeleteButt)
                        .addGap(82, 82, 82)))
                .addContainerGap(340, Short.MAX_VALUE))
        );
        UserDeletePanelLayout.setVerticalGroup(
            UserDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserDeletePanelLayout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addGroup(UserDeletePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55))
                .addGap(62, 62, 62)
                .addComponent(DeleteButt)
                .addContainerGap(285, Short.MAX_VALUE))
        );

        getContentPane().add(UserDeletePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        UserChangePanel.setBackground(new java.awt.Color(254, 255, 233));

        jTextField12.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jTextField12.setText("jTextField6");

        jTextField13.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jTextField13.setText("jTextField7");

        jLabel36.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel36.setText("ID : ");

        jTextField14.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jTextField14.setText("jTextField8");

        jLabel46.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel46.setText("PW : ");

        ChangeButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        ChangeButt.setText("수정");
        ChangeButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangeButtActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel47.setText("이름 : ");

        jLabel52.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel52.setText("전화번호 : ");

        jLabel53.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel53.setText("이메일 : ");

        jLabel54.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel54.setText("비고 : ");

        jTextField15.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jTextField15.setText("jTextField3");

        jTextField16.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jTextField16.setText("jTextField4");

        jTextField17.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jTextField17.setText("jTextField5");

        javax.swing.GroupLayout UserChangePanelLayout = new javax.swing.GroupLayout(UserChangePanel);
        UserChangePanel.setLayout(UserChangePanelLayout);
        UserChangePanelLayout.setHorizontalGroup(
            UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserChangePanelLayout.createSequentialGroup()
                .addContainerGap(339, Short.MAX_VALUE)
                .addGroup(UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UserChangePanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel52)))
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(UserChangePanelLayout.createSequentialGroup()
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116)
                        .addComponent(ChangeButt)))
                .addGap(287, 287, 287))
        );
        UserChangePanelLayout.setVerticalGroup(
            UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserChangePanelLayout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addGroup(UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addGap(21, 21, 21)
                .addGroup(UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(UserChangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChangeButt))
                .addContainerGap(192, Short.MAX_VALUE))
        );

        getContentPane().add(UserChangePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        UserInfoPanel.setBackground(new java.awt.Color(254, 255, 233));

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(jTable6);

        UserDeleteButt.setBackground(new java.awt.Color(255, 255, 255));
        UserDeleteButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        UserDeleteButt.setText("삭제");
        UserDeleteButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserDeleteButtActionPerformed(evt);
            }
        });

        UserChangeButt.setBackground(new java.awt.Color(255, 255, 255));
        UserChangeButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        UserChangeButt.setText("수정");
        UserChangeButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserChangeButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UserInfoPanelLayout = new javax.swing.GroupLayout(UserInfoPanel);
        UserInfoPanel.setLayout(UserInfoPanelLayout);
        UserInfoPanelLayout.setHorizontalGroup(
            UserInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserInfoPanelLayout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addGroup(UserInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(UserInfoPanelLayout.createSequentialGroup()
                        .addComponent(UserChangeButt)
                        .addGap(31, 31, 31)
                        .addComponent(UserDeleteButt))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 838, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );
        UserInfoPanelLayout.setVerticalGroup(
            UserInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserInfoPanelLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(UserInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(UserDeleteButt, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(UserChangeButt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        getContentPane().add(UserInfoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        Manage_menuPanel.setBackground(new java.awt.Color(255, 255, 255));
        Manage_menuPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        menuToken.setBackground(new java.awt.Color(255, 255, 255));
        menuToken.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuToken.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuToken.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuTokenMouseClicked(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel59.setText("토큰 생성 및 초기화");

        javax.swing.GroupLayout menuTokenLayout = new javax.swing.GroupLayout(menuToken);
        menuToken.setLayout(menuTokenLayout);
        menuTokenLayout.setHorizontalGroup(
            menuTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuTokenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel59)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuTokenLayout.setVerticalGroup(
            menuTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuTokenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuUserOk.setBackground(new java.awt.Color(255, 255, 255));
        menuUserOk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuUserOk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuUserOk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuUserOkMouseClicked(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel58.setText("회원 정보 승인");

        javax.swing.GroupLayout menuUserOkLayout = new javax.swing.GroupLayout(menuUserOk);
        menuUserOk.setLayout(menuUserOkLayout);
        menuUserOkLayout.setHorizontalGroup(
            menuUserOkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuUserOkLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel58)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuUserOkLayout.setVerticalGroup(
            menuUserOkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuUserOkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuUserReset.setBackground(new java.awt.Color(255, 255, 255));
        menuUserReset.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuUserReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuUserReset.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        menuUserReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuUserResetMouseClicked(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel56.setText("회원 정보 초기화");

        javax.swing.GroupLayout menuUserResetLayout = new javax.swing.GroupLayout(menuUserReset);
        menuUserReset.setLayout(menuUserResetLayout);
        menuUserResetLayout.setHorizontalGroup(
            menuUserResetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuUserResetLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel56)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuUserResetLayout.setVerticalGroup(
            menuUserResetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuUserResetLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel56)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        menuTTReset.setBackground(new java.awt.Color(255, 255, 255));
        menuTTReset.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuTTReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuTTReset.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        menuTTReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuTTResetMouseClicked(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel63.setText("수업 시간표 초기화");

        javax.swing.GroupLayout menuTTResetLayout = new javax.swing.GroupLayout(menuTTReset);
        menuTTReset.setLayout(menuTTResetLayout);
        menuTTResetLayout.setHorizontalGroup(
            menuTTResetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuTTResetLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel63)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuTTResetLayout.setVerticalGroup(
            menuTTResetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuTTResetLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel63)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel60.setFont(new java.awt.Font("맑은 고딕", 0, 36)); // NOI18N
        jLabel60.setText("관리");

        jLabel61.setFont(new java.awt.Font("맑은 고딕", 0, 36)); // NOI18N
        jLabel61.setText("초기화");

        jLabel62.setFont(new java.awt.Font("맑은 고딕", 0, 36)); // NOI18N
        jLabel62.setText("승인 및");

        javax.swing.GroupLayout Manage_menuPanelLayout = new javax.swing.GroupLayout(Manage_menuPanel);
        Manage_menuPanel.setLayout(Manage_menuPanelLayout);
        Manage_menuPanelLayout.setHorizontalGroup(
            Manage_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuUserReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuUserOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuToken, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Manage_menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Manage_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62)
                    .addGroup(Manage_menuPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(Manage_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Manage_menuPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel60))
                            .addComponent(jLabel61))))
                .addContainerGap(15, Short.MAX_VALUE))
            .addComponent(menuTTReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Manage_menuPanelLayout.setVerticalGroup(
            Manage_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Manage_menuPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel61)
                .addGap(2, 2, 2)
                .addComponent(jLabel60)
                .addGap(18, 18, 18)
                .addComponent(menuToken, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuUserOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuUserReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuTTReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(110, Short.MAX_VALUE))
        );

        getContentPane().add(Manage_menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 150, 620));

        TokenPanel.setBackground(new java.awt.Color(254, 255, 233));

        jLabel38.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        jLabel38.setText("토큰값");

        jTextField6.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        jTextField6.setText("jTextField6");

        TokenOkButt.setBackground(new java.awt.Color(255, 255, 255));
        TokenOkButt.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        TokenOkButt.setText("생성");
        TokenOkButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TokenOkButtActionPerformed(evt);
            }
        });

        TokenResetButt.setBackground(new java.awt.Color(255, 255, 255));
        TokenResetButt.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        TokenResetButt.setText("초기화");
        TokenResetButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TokenResetButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TokenPanelLayout = new javax.swing.GroupLayout(TokenPanel);
        TokenPanel.setLayout(TokenPanelLayout);
        TokenPanelLayout.setHorizontalGroup(
            TokenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TokenPanelLayout.createSequentialGroup()
                .addGap(332, 332, 332)
                .addGroup(TokenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(TokenPanelLayout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TokenPanelLayout.createSequentialGroup()
                        .addComponent(TokenOkButt, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TokenResetButt)))
                .addContainerGap(371, Short.MAX_VALUE))
        );
        TokenPanelLayout.setVerticalGroup(
            TokenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TokenPanelLayout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addGroup(TokenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(TokenPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TokenOkButt)
                    .addComponent(TokenResetButt))
                .addContainerGap(291, Short.MAX_VALUE))
        );

        getContentPane().add(TokenPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        UserOkPanel.setBackground(new java.awt.Color(254, 255, 233));

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(jTable7);

        UserOkButt.setBackground(new java.awt.Color(255, 255, 255));
        UserOkButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        UserOkButt.setText("승인");
        UserOkButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserOkButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UserOkPanelLayout = new javax.swing.GroupLayout(UserOkPanel);
        UserOkPanel.setLayout(UserOkPanelLayout);
        UserOkPanelLayout.setHorizontalGroup(
            UserOkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserOkPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(UserOkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(UserOkButt)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        UserOkPanelLayout.setVerticalGroup(
            UserOkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserOkPanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(UserOkButt, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        getContentPane().add(UserOkPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        UserResetPanel.setBackground(new java.awt.Color(254, 255, 233));

        UserResetButt.setBackground(new java.awt.Color(255, 255, 255));
        UserResetButt.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        UserResetButt.setText("회원 승인 정보 초기화");
        UserResetButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserResetButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout UserResetPanelLayout = new javax.swing.GroupLayout(UserResetPanel);
        UserResetPanel.setLayout(UserResetPanelLayout);
        UserResetPanelLayout.setHorizontalGroup(
            UserResetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserResetPanelLayout.createSequentialGroup()
                .addGap(329, 329, 329)
                .addComponent(UserResetButt)
                .addContainerGap(358, Short.MAX_VALUE))
        );
        UserResetPanelLayout.setVerticalGroup(
            UserResetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserResetPanelLayout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addComponent(UserResetButt)
                .addContainerGap(325, Short.MAX_VALUE))
        );

        getContentPane().add(UserResetPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        TTResetPanel.setBackground(new java.awt.Color(254, 255, 233));

        TTResetButt.setBackground(new java.awt.Color(255, 255, 255));
        TTResetButt.setFont(new java.awt.Font("맑은 고딕", 0, 24)); // NOI18N
        TTResetButt.setText("시간표 초기화");
        TTResetButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TTResetButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TTResetPanelLayout = new javax.swing.GroupLayout(TTResetPanel);
        TTResetPanel.setLayout(TTResetPanelLayout);
        TTResetPanelLayout.setHorizontalGroup(
            TTResetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TTResetPanelLayout.createSequentialGroup()
                .addGap(380, 380, 380)
                .addComponent(TTResetButt)
                .addContainerGap(395, Short.MAX_VALUE))
        );
        TTResetPanelLayout.setVerticalGroup(
            TTResetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TTResetPanelLayout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addComponent(TTResetButt)
                .addContainerGap(328, Short.MAX_VALUE))
        );

        getContentPane().add(TTResetPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // 타이틀 패널 클릭시 메인 패널로 이동
    private void TitlePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TitlePanelMouseClicked
        reset();
        mainPanel.setVisible(true);
    }//GEN-LAST:event_TitlePanelMouseClicked

    // 메인화면 - 시간표 관리 버튼
    private void timeTableManageButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeTableManageButtActionPerformed
        reset();
        TimeTable_menuPanel.setVisible(true);
        TimeTableAddPanel.setVisible(true);
        menuTimeTableAdd.setBackground(yellow);
    }//GEN-LAST:event_timeTableManageButtActionPerformed

    // 메인화면 - 승인 및 초기화 관리 버튼
    private void manageButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageButtActionPerformed
        reset();
        Manage_menuPanel.setVisible(true);
        TokenPanel.setVisible(true);
        menuToken.setBackground(yellow);
    }//GEN-LAST:event_manageButtActionPerformed

    // 메인화면 - 예약 관리 버튼
    private void reservationManageButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reservationManageButtActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        ReserCheckPanel.setVisible(true);
        menuReserCheck.setBackground(yellow);
        
        DefaultTableModel table = (DefaultTableModel) jTable1.getModel();

        table.setNumRows(0);  // 테이블 초기화

        // 예약 승인 패널(ReserCheckPanel)에 테이블 값 DB에서 가져와서 띄우기
        connect();  // 디비 연결

        try {
            // 미승인 예약의 강의실번호, 날짜, 시작시간, 종료시간, 학생아이디, 학생이름 
            sql = "select r.labId, r.dateR, r.startTimeR, r.endTimeR, r.sId, s.sName from reservation r, student s where r.sId = s.sId and r.reserPermission = ?";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, 0);  // 예약 미승인
            
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Object data[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};  // 값 저장
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
   
    }//GEN-LAST:event_reservationManageButtActionPerformed

    // 메인화면 - 공지사항 및 신고 관리 버튼
    private void noticeManageButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noticeManageButtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noticeManageButtActionPerformed

    // 메인화면 - 회원정보 관리 버튼
    private void userInforManageButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userInforManageButtActionPerformed
        reset();
        UserManage_menuPanel.setVisible(true);
        UserInfoPanel.setVisible(true);
        menuUserDelete.setBackground(yellow);
        
        // 테이블에 모든 회원 정보 띄움
    }//GEN-LAST:event_userInforManageButtActionPerformed

    // 메인화면 - 실습실 관리 버튼
    private void labManageButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labManageButtActionPerformed
        reset();
        LabManage_menuPanel.setVisible(true);
        LabStatusPanel.setVisible(true);
        menuLabStatus.setBackground(yellow);
    }//GEN-LAST:event_labManageButtActionPerformed

    // 예약 관리 메뉴바 - 실습실 좌석별 예약 조회 및 취소 선택 시
    private void menuseatReserCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuseatReserCheckMouseClicked
        reset();
        ReserManage_menuPanel.setVisible(true);
        SeatReserCheckPanel.setVisible(true);

        menuseatReserCheck.setBackground(yellow);
    }//GEN-LAST:event_menuseatReserCheckMouseClicked

    // 예약 관리 메뉴바 - 예약 리스트 조회 및 취소 선택 시
    private void menuListCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuListCheckMouseClicked
        reset();
        ReserManage_menuPanel.setVisible(true);
        ListCheckPanel.setVisible(true);

        menuListCheck.setBackground(yellow);
        
        // 예약 리스트 조회 및 취소 패널(ReserCanclePanel)에 테이블 값 DB에서 가져와서 띄우기
        
        DefaultTableModel table = (DefaultTableModel) jTable2.getModel();

        table.setNumRows(0);  // 테이블 초기화

        // 예약 승인 패널(ReserCheckPanel)에 테이블 값 DB에서 가져와서 띄우기
        connect();  // 디비 연결

        try {
            // 모든 예약 조회 
            sql = "select r.labId, r.dateR, r.startTimeR, r.endTimeR, r.sId, s.sName from reservation r, student s where r.sId = s.sId";

            pstmt = conn.prepareStatement(sql);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Object data[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};  // 값 저장
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
    }//GEN-LAST:event_menuListCheckMouseClicked

    // 예약 관리 메뉴바 - 예약 승인 선택 시
    private void menuReserCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuReserCheckMouseClicked
        reset();
        ReserManage_menuPanel.setVisible(true);
        ReserCheckPanel.setVisible(true);

        menuReserCheck.setBackground(yellow);
        
        DefaultTableModel table = (DefaultTableModel) jTable1.getModel();

        table.setNumRows(0);  // 테이블 초기화

        // 예약 승인 패널(ReserCheckPanel)에 테이블 값 DB에서 가져와서 띄우기
        connect();  // 디비 연결

        try {
            // 미승인 예약의 강의실번호, 날짜, 시작시간, 종료시간, 학생아이디, 학생이름 
            sql = "select r.labId, r.dateR, r.startTimeR, r.endTimeR, r.sId, s.sName from reservation r, student s where r.sId = s.sId and r.reserPermission = ?";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, 0);  // 예약 미승인
            
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Object data[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};  // 값 저장
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
    }//GEN-LAST:event_menuReserCheckMouseClicked

    // 좌석별 조회 - 실습실, 날짜 입력 후 조회 버튼 클릭
    private void SeatCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeatCheckButtActionPerformed
        seatCheckPanel.setVisible(true);
        
        // 입력받은 실습실, 날짜에 따라 해당 좌석의 하루의 모든 예약을 카운트해서 출력
        seat2.setText("4");
        seat23.setText("1");
        
        // 예약이 없는 좌석 -> 버튼 비활성화
        seat1.setEnabled(false);
    }//GEN-LAST:event_SeatCheckButtActionPerformed

    // 좌석2 클릭 시
    private void seat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat2ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat2");
    }//GEN-LAST:event_seat2ActionPerformed

    // 좌석1 클릭 시
    private void seat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat1ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat1");
    }//GEN-LAST:event_seat1ActionPerformed

    // 좌석3 클릭 시
    private void seat3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat3ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat3");
    }//GEN-LAST:event_seat3ActionPerformed

    // 좌석4 클릭 시
    private void seat4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat4ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat4");
    }//GEN-LAST:event_seat4ActionPerformed

    // 좌석5 클릭 시
    private void seat5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat5ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat5");
    }//GEN-LAST:event_seat5ActionPerformed

    // 좌석6 클릭 시
    private void seat6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat6ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat6");
    }//GEN-LAST:event_seat6ActionPerformed

    // 좌석7 클릭 시
    private void seat7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seat7MouseClicked
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat7");
    }//GEN-LAST:event_seat7MouseClicked

    // 좌석8 클릭 시
    private void seat8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat8ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat8");
    }//GEN-LAST:event_seat8ActionPerformed

    // 좌석9 클릭 시
    private void seat9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat9ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat9");
    }//GEN-LAST:event_seat9ActionPerformed

    // 좌석10 클릭 시
    private void seat10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat10ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat10");
    }//GEN-LAST:event_seat10ActionPerformed

    // 좌석11 클릭 시
    private void seat11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat11ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat2");
    }//GEN-LAST:event_seat11ActionPerformed

    // 좌석12 클릭 시
    private void seat12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat12ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat12");
    }//GEN-LAST:event_seat12ActionPerformed

    // 좌석13 클릭 시
    private void seat13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat13ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat13");
    }//GEN-LAST:event_seat13ActionPerformed

    // 좌석14 클릭 시
    private void seat14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat14ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat14");
    }//GEN-LAST:event_seat14ActionPerformed

    // 좌석15 클릭 시
    private void seat15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat15ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat15");
    }//GEN-LAST:event_seat15ActionPerformed

    // 좌석16 클릭 시
    private void seat16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat16ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat16");
    }//GEN-LAST:event_seat16ActionPerformed

    // 좌석17 클릭 시
    private void seat17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat17ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat17");
    }//GEN-LAST:event_seat17ActionPerformed

    // 좌석18 클릭 시
    private void seat18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat18ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat18");
    }//GEN-LAST:event_seat18ActionPerformed

    // 좌석19 클릭 시
    private void seat19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat19ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat19");
    }//GEN-LAST:event_seat19ActionPerformed

    // 좌석20 클릭 시
    private void seat20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat20ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat20");
    }//GEN-LAST:event_seat20ActionPerformed

    // 좌석21 클릭 시
    private void seat21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat21ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat21");
    }//GEN-LAST:event_seat21ActionPerformed

    // 좌석22 클릭 시
    private void seat22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat22ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat22");
    }//GEN-LAST:event_seat22ActionPerformed

    // 좌석23 클릭 시
    private void seat23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat23ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat23");
    }//GEN-LAST:event_seat23ActionPerformed

    // 좌석24 클릭 시
    private void seat24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat24ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat24");
    }//GEN-LAST:event_seat24ActionPerformed

    // 좌석25 클릭 시
    private void seat25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat25ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat25");
    }//GEN-LAST:event_seat25ActionPerformed

    // 좌석26 클릭 시
    private void seat26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat26ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat26");
    }//GEN-LAST:event_seat26ActionPerformed

    // 좌석27 클릭 시
    private void seat27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat27ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat27");
    }//GEN-LAST:event_seat27ActionPerformed

    // 좌석28 클릭 시
    private void seat28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat28ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat28");
    }//GEN-LAST:event_seat28ActionPerformed

    // 좌석29 클릭 시
    private void seat29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat29ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat29");
    }//GEN-LAST:event_seat29ActionPerformed

    // 좌석30 클릭 시
    private void seat30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seat30ActionPerformed
        reset();
        ReserManage_menuPanel.setVisible(true);
        menuseatReserCheck.setBackground(yellow);
        
        SeatReserCanclePanel.setVisible(true);
        
        // 강의실, 날짜 값 띄우기
        lab.setText(labNum.getSelectedItem().toString());
        date.setText(dateNum.getText());
        seatNum.setText("seat30");
    }//GEN-LAST:event_seat30ActionPerformed

    // 시간표 관리 메뉴바 - 실습실 별 시간표 조회 선택 시
    private void menuTimeTableCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTimeTableCheckMouseClicked
        reset();
        TimeTable_menuPanel.setVisible(true);
        TimeTableCheckPanel.setVisible(true);

        menuTimeTableCheck.setBackground(yellow);
    }//GEN-LAST:event_menuTimeTableCheckMouseClicked

    // 시간표 관리 메뉴바 - 특강 시간표 입력 선택 시
    private void menuSeminarAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSeminarAddMouseClicked
        reset();
        TimeTable_menuPanel.setVisible(true);
        SeminarAddPanel.setVisible(true);

        menuSeminarAdd.setBackground(yellow);
    }//GEN-LAST:event_menuSeminarAddMouseClicked

    // 시간표 관리 메뉴바 - 수업 시간표 입력 선택 시
    private void menuTimeTableAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTimeTableAddMouseClicked
        reset();
        TimeTable_menuPanel.setVisible(true);
        TimeTableAddPanel.setVisible(true);

        menuTimeTableAdd.setBackground(yellow);
    }//GEN-LAST:event_menuTimeTableAddMouseClicked

    // 실습실 별 시간표 조회 버튼
    private void TTCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TTCheckButtActionPerformed
        TT.setVisible(true);
        connect();
        
        try{
            sql="select * from lecture where labId=? order by day";
            pstmt = conn.prepareStatement(sql); //디비 구문과 연결
            
            pstmt.setString(1, jComboBox4.getSelectedItem().toString());    //실습실
                
            rs=pstmt.executeQuery();
                while(rs.next()){ // 레코드(Record, Row) 생성
                   
                } 
        }catch(SQLException ex){
               System.out.println(ex.getMessage()); 
            }finally {
                if(rs != null) try {rs.close();} catch (SQLException ex) {}
                if(pstmt != null) try {pstmt.close();} catch (SQLException ex) {}
                if(conn != null) try {conn.close();} catch (SQLException ex) {}
            }
        // 테이블에 시간표 출력
    }//GEN-LAST:event_TTCheckButtActionPerformed

    // 특강 및 세미나 사용가능한 강의실 조회
    private void seminarCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seminarCheckButtActionPerformed
        reset();
        connect();
        TimeTable_menuPanel.setVisible(true);
        menuSeminarAdd.setBackground(yellow);
        
        ArrayList<String> lab = new ArrayList<>();
        
        String sText=SStartTime.getSelectedItem().toString();   //시작 시간 가져와서 문자열로 변수에 저장
        int sNum=sText.indexOf(":");    //":"위치 저장
        String eText=SEndTime.getSelectedItem().toString();   //종료 시간 가져와서 문자열로 변수에 저장
        int eNum=eText.indexOf(":");    //":" 위치 저장
        
        if(Integer.parseInt(sText.substring(0,sNum)) >= Integer.parseInt(eText.substring(0,eNum))){ //시작시간을 종료시간보다 늦게 설정했을 경우
            JOptionPane.showMessageDialog(this, "시작 시간을 종료 시간보다 빠르게 설정하세요." , "Message",JOptionPane.ERROR_MESSAGE );
            SeminarAddPanel.setVisible(true);
        }else{
        //실습실 번호는 임의로 0으로 설정
        //pId는 로그인 시 객체 저장해서 가져와야 한다. 현재는 임의로 pro1로 설정
        seminar=new Seminar(SDateText.getText(), SNameText.getText(), "pro1", "0", sText.substring(0,sNum), eText.substring(0,eNum));
            System.out.println(seminar.dateS);
        try{
            //강의가 있는지 조회
                sql="select * from lecture where day=? and ((startTime >=? and startTime<?) or (endTime>? and endTime<=?) or (startTime>=? and endTime<=?))";
                pstmt = conn.prepareStatement(sql); //디비 구문과 연결
                
                pstmt.setInt(1, getDay(seminar));      //요일
                pstmt.setString(2, seminar.startTimeS); //시작시간
                pstmt.setString(3, seminar.endTimeS);   //종료시간
                pstmt.setString(4, seminar.startTimeS); //시작시간
                pstmt.setString(5, seminar.endTimeS);   //종료시간
                pstmt.setString(6, seminar.startTimeS); //시작시간
                pstmt.setString(7, seminar.endTimeS);   //종료시간

                rs=pstmt.executeQuery();
                while(rs.next()){ //해당 예약 정보와 겹치는 강의가 존재한다면
                    if(lab.contains(rs.getString("labId"))==false){ //실습실이 이미 리스트에 있다면 추가하지 않는다.
                        lab.add(rs.getString("labId"));
                    }
                } //해당 예약 정보와 겹치는 세미나(또는 특강)이 존재한다면

                sql="select * from seminar where dateS=? and ((startTimeS >=? and startTimeS<?) or (endTimeS>? and endTimeS<=?) or (startTimeS>=? and endTimeS<=?))";
                pstmt = conn.prepareStatement(sql); //디비 구문과 연결
                
                pstmt.setString(1, seminar.dateS);      //날짜
                pstmt.setString(2, seminar.startTimeS); //시작시간
                pstmt.setString(3, seminar.endTimeS);   //종료시간
                pstmt.setString(4, seminar.startTimeS); //시작시간
                pstmt.setString(5, seminar.endTimeS);   //종료시간
                pstmt.setString(6, seminar.startTimeS); //시작시간
                pstmt.setString(7, seminar.endTimeS);   //종료시간
                
                rs=pstmt.executeQuery();
                while(rs.next()){
                    if(lab.contains(rs.getString("labId"))==false){ //실습실이 이미 리스트에 있다면 추가하지 않는다.
                        lab.add(rs.getString("labId"));
                    }
                }
                //꽉 찬 실습실이 존재한다면
                sql="select count(*),labId from reservation where dateR=? and ((startTimeR >=? and startTimeR<?) or (endTimeR>? and endTimeR<=?) or (startTimeR>=? and endTimeR<=?))"
                        +"group by labId";
                pstmt = conn.prepareStatement(sql); //디비 구문과 연결

                pstmt.setString(1, seminar.dateS);      //날짜
                pstmt.setString(2, seminar.startTimeS); //시작시간
                pstmt.setString(3, seminar.endTimeS);   //종료시간
                pstmt.setString(4, seminar.startTimeS); //시작시간
                pstmt.setString(5, seminar.endTimeS);   //종료시간
                pstmt.setString(6, seminar.startTimeS); //시작시간
                pstmt.setString(7, seminar.endTimeS);   //종료시간

                rs = pstmt.executeQuery();
                while(rs.next()){
                    if(lab.contains(rs.getString(2))==false){ //실습실이 이미 리스트에 있다면 추가하지 않는다.
                            lab.add(rs.getString(2));   //실습실번호 저장
                        }
                }
                DefaultListModel listModel = new DefaultListModel();
                if(lab.contains("911")==false){
                    listModel.addElement("911");
                }
                if(lab.contains("915")==false){
                    listModel.addElement("915");
                }
                if(lab.contains("916")==false){
                    listModel.addElement("916");
                }
                if(lab.contains("918")==false){
                    listModel.addElement("918");
                }
                System.out.println(lab);
                jList1.setModel(listModel);
                if(lab.size()>=4){  //사용 가능한 실습실이 없다면
                    JOptionPane.showMessageDialog(this, "세미나(특강) 등록 가능한 실습실이 없습니다." , "Message",JOptionPane.ERROR_MESSAGE );
                    SeminarAddPanel.setVisible(true);
                }else{  //사용 가능한 실습실이 있다면
                    seminarReserCheckPanel.setVisible(true);
                    // SeminarAddPanel에서 입력받은 날짜, 시작시간, 종료시간 받아와서 출력 
                    SDate.setText(SDateText.getText());
                    SStart.setText(SStartTime.getSelectedItem().toString());
                    SEnd.setText(SEndTime.getSelectedItem().toString());
        
                    // 사용가능한 강의실 출력
                }
        }catch(SQLException ex){
               System.out.println(ex.getMessage()); 
            }finally {
                if(rs != null) try {rs.close();} catch (SQLException ex) {}
                if(pstmt != null) try {pstmt.close();} catch (SQLException ex) {}
                if(conn != null) try {conn.close();} catch (SQLException ex) {}
            }
        }
    }//GEN-LAST:event_seminarCheckButtActionPerformed

    // 실습실 관리 메뉴바 - 실습실 공지사항 및 규칙 입력 선택 시
    private void menuNoticeAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuNoticeAddMouseClicked
        reset();
        LabManage_menuPanel.setVisible(true);
        NoticeAddPanel.setVisible(true);

        menuNoticeAdd.setBackground(yellow);
    }//GEN-LAST:event_menuNoticeAddMouseClicked

    // 실습실 관리 메뉴바 - 실습실 관리 권한 부여 선택 시
    private void menuManageRightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuManageRightMouseClicked
        reset();
        LabManage_menuPanel.setVisible(true);
        ManageRightPanel.setVisible(true);

        menuManageRight.setBackground(yellow);
    }//GEN-LAST:event_menuManageRightMouseClicked

    // 실습실 관리 메뉴바 - 실습실 사용현황 조회 선택 시
    private void menuLabStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLabStatusMouseClicked
        reset();
        LabManage_menuPanel.setVisible(true);
        LabStatusPanel.setVisible(true);

        menuLabStatus.setBackground(yellow);
    }//GEN-LAST:event_menuLabStatusMouseClicked

    // 회원정보 관리 메뉴바 - 회원 정보 수정 선택 시
    private void menuUserChangeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuUserChangeMouseClicked
        reset();
        UserManage_menuPanel.setVisible(true);
        UserInfoPanel.setVisible(true);

        menuUserChange.setBackground(yellow);
        
        // 테이블에 모든 회원 정보 띄움
    }//GEN-LAST:event_menuUserChangeMouseClicked

    // 회원정보 관리 메뉴바 - 회원 정보 삭제 선택 시
    private void menuUserDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuUserDeleteMouseClicked
        reset();
        UserManage_menuPanel.setVisible(true);
        UserInfoPanel.setVisible(true);

        menuUserDelete.setBackground(yellow);
        
        // 테이블에 모든 회원 정보 띄움
    }//GEN-LAST:event_menuUserDeleteMouseClicked

    // 테이블에서 한 행 선택 후 수정 버튼 클릭 시
    private void UserChangeButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserChangeButtActionPerformed
        reset();
        UserManage_menuPanel.setVisible(true);
        UserChangePanel.setVisible(true);
        
        menuUserChange.setBackground(yellow);
        
        // 테이블에서 선택한 행의 값 출력
    }//GEN-LAST:event_UserChangeButtActionPerformed

    // 테이블에서 한 행 선택 후 삭제 버튼 클릭 시
    private void UserDeleteButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserDeleteButtActionPerformed
        reset();
        UserManage_menuPanel.setVisible(true);
        UserDeletePanel.setVisible(true);
        
        menuUserDelete.setBackground(yellow);
        
        // 테이블에서 선택한 행의 값 출력
    }//GEN-LAST:event_UserDeleteButtActionPerformed

    // 회원 정보 수정 버튼
    private void ChangeButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeButtActionPerformed
        // 입력된 값으로 db값 수정
    }//GEN-LAST:event_ChangeButtActionPerformed

    // 회원 정보 삭제 버튼
    private void DeleteButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtActionPerformed
        // db에서 회원 정보 삭제
    }//GEN-LAST:event_DeleteButtActionPerformed

    // 예약 승인 버튼
    private void ReserOkButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReserOkButtActionPerformed
        // 선택된 테이블 값 예약 승인
    }//GEN-LAST:event_ReserOkButtActionPerformed

    // 예약 리스트 조회 버튼
    private void ListCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListCheckButtActionPerformed
        // 입력받은 실습실, 날짜에 대해서 조회 후 테이블에 값 출력
        
        // jComboBox1 : 실습실
        // jTextField1 : 날짜 
        
        
    }//GEN-LAST:event_ListCheckButtActionPerformed

    // 예약 리스트 삭제 버튼
    private void ListCancleButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListCancleButtActionPerformed
        // 선택된 테이블 행 정보 db에서 삭제
    }//GEN-LAST:event_ListCancleButtActionPerformed

    // 좌석별 예약 조회 및 취소 -> 조회 -> 좌석 선택 ->  예약 취소 버튼
    private void SeatReserCancleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeatReserCancleActionPerformed
        // 선택된 테이블 행 db에서 삭제
    }//GEN-LAST:event_SeatReserCancleActionPerformed

    // 시간표 추가 버튼
    private void TimeTableAddButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TimeTableAddButtActionPerformed
        // 입력받은 값 시간표 db에 저장
        connect();
        
        String sText=jComboBox8.getSelectedItem().toString();   //시작 시간 가져와서 문자열로 변수에 저장
        int sNum=sText.indexOf(":");    //":"위치 저장
        String eText=jComboBox9.getSelectedItem().toString();   //종료 시간 가져와서 문자열로 변수에 저장
        int eNum=eText.indexOf(":");    //":" 위치 저장
        
        //요일을 숫자 형태로
        int day;
        if(jComboBox3.getSelectedItem().toString().equals("월")){
            day=1;
        }else if(jComboBox3.getSelectedItem().toString().equals("화")){
            day=2;
        }else if(jComboBox3.getSelectedItem().toString().equals("수")){
            day=3;
        }else if(jComboBox3.getSelectedItem().toString().equals("목")){
            day=4;
        }else if(jComboBox3.getSelectedItem().toString().equals("금")){
            day=5;
        }else if(jComboBox3.getSelectedItem().toString().equals("토")){
            day=6;
        }else{
            day=7;
        }
        
        if(Integer.parseInt(sText.substring(0,sNum)) >= Integer.parseInt(eText.substring(0,eNum))){ //시작시간을 종료시간보다 늦게 설정했을 경우
            JOptionPane.showMessageDialog(this, "시작 시간을 종료 시간보다 빠르게 설정하세요." , "Message",JOptionPane.ERROR_MESSAGE );
            TimeTableAddPanel.setVisible(true);
        }else{
            lecture=new Lecture(jComboBox2.getSelectedItem().toString(),jTextField2.getText(),sText.substring(0,sNum),eText.substring(0,eNum),day,jTextField5.getText());
            try{
                //기존의 강의와 겹치는지 조회
                sql="select * from lecture where day=? and labId=? and ((startTime >=? and startTime<?) or (endTime>? and endTime<=?) or (startTime>=? and endTime<=?))";
                pstmt = conn.prepareStatement(sql); //디비 구문과 연결
            
                pstmt.setInt(1, day);      //요일
                pstmt.setString(2, lecture.labId);      //실습실 번호
                pstmt.setString(3, lecture.startTime); //시작시간
                pstmt.setString(4, lecture.endTime);   //종료시간
                pstmt.setString(5, lecture.startTime); //시작시간
                pstmt.setString(6, lecture.endTime);   //종료시간
                pstmt.setString(7, lecture.startTime); //시작시간
                pstmt.setString(8, lecture.endTime);   //종료시간

                rs = pstmt.executeQuery();
                if (rs.next()) { //만약 겹치는 강의가 존재한다면
                    JOptionPane.showMessageDialog(this, "시간이 겹치는 강의가 존재합니다." , "Message",JOptionPane.INFORMATION_MESSAGE );
                    TimeTableAddPanel.setVisible(true);
                } else {
                    sql = "insert into lecture (labId,lectureName,startTime, endTime,day,pId) values (?,?,?,?,?,?)";
                    pstmt = conn.prepareStatement(sql); //디비 구문과 연결

                    //로그인 시 조교 정보 객체에 저장해서 아이디 가져와야 함
                    pstmt.setString(1,lecture.labId);         //실습실 번호 
                    pstmt.setString(2, lecture.lecName);   //
                    pstmt.setString(3, lecture.startTime);    //좌석번호
                    pstmt.setString(4, lecture.endTime);   //예약날짜
                    pstmt.setInt(5, day); //시작시간
                    pstmt.setString(6,lecture.pId);   //교수명 (임의로 아이디 넣어둠! 수정해야함)

                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "등록 완료되었습니다.", "Message", JOptionPane.INFORMATION_MESSAGE);
                }     
            }catch(SQLException ex){
               System.out.println(ex.getMessage()); 
            }finally {
                if(rs != null) try {rs.close();} catch (SQLException ex) {}
                if(pstmt != null) try {pstmt.close();} catch (SQLException ex) {}
                if(conn != null) try {conn.close();} catch (SQLException ex) {}
            }
        }
    }//GEN-LAST:event_TimeTableAddButtActionPerformed

    // 특강 추가 버튼
    private void SeminarAddButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeminarAddButtActionPerformed
        connect();
        // DB에 특강 정보 저장
        seminar.labId=jList1.getSelectedValue();
        if(seminar.labId==null){
            JOptionPane.showMessageDialog(this, "실습실을 먼저 선택해주세요.", "Message", JOptionPane.INFORMATION_MESSAGE);
        }else{
        try {
            //데이터 삽입을 위한 sql문
            sql = "insert into seminar (pId,labId,seminarName,dateS,startTimeS,endTimeS) values (?,?,?,?,?,?)";
                    pstmt = conn.prepareStatement(sql); //디비 구문과 연결

                    //로그인 시 조교 정보 객체에 저장해서 아이디 가져와야 함
                    pstmt.setString(1,"pro1");         //조교아이디 
                    pstmt.setString(2, seminar.labId);   //실습실번호
                    pstmt.setString(3, seminar.seminarName);    //세미나명
                    pstmt.setString(4, seminar.dateS);   //예약날짜
                    pstmt.setString(5, seminar.startTimeS); //시작시간
                    pstmt.setString(6, seminar.endTimeS); //종료시간

                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "등록 완료되었습니다.", "Message", JOptionPane.INFORMATION_MESSAGE);
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
        reset();
        mainPanel.setVisible(true);//메인 패널로 이동
        }
    }//GEN-LAST:event_SeminarAddButtActionPerformed

    // 실습실 예약 조회 
    private void LabRightCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LabRightCheckButtActionPerformed
        // 입력받은 실습실에 대해서 테이블에 실습실 예약 출력
    }//GEN-LAST:event_LabRightCheckButtActionPerformed

    // 관리권한부여 버튼
    private void RightButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RightButtActionPerformed
        // 테이블에서 선택된 행의 학생의 db에 관리권한부여 정보 추가
    }//GEN-LAST:event_RightButtActionPerformed

    // 승인 및 초기화 관리 메뉴바 - 회원 정보 초기화 선택 시
    private void menuUserResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuUserResetMouseClicked
        reset();
        Manage_menuPanel.setVisible(true);
        UserResetPanel.setVisible(true);

        menuUserReset.setBackground(yellow);
    }//GEN-LAST:event_menuUserResetMouseClicked

    // 승인 및 초기화 관리 메뉴바 - 회원 정보 승인 선택 시
    private void menuUserOkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuUserOkMouseClicked
        reset();
        Manage_menuPanel.setVisible(true);
        UserOkPanel.setVisible(true);

        menuUserOk.setBackground(yellow);
        
        // 정상적인 토큰 값이 입력되고 승인 정보가 미승인 상태인 정보 테이블에 출력
        
    }//GEN-LAST:event_menuUserOkMouseClicked

    // 승인 및 초기화 관리 메뉴바 - 토큰 생성 및 초기화 선택 시
    private void menuTokenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTokenMouseClicked
        reset();
        Manage_menuPanel.setVisible(true);
        TokenPanel.setVisible(true);

        menuToken.setBackground(yellow);
    }//GEN-LAST:event_menuTokenMouseClicked

    // 승인 및 초기화 관리 메뉴바 - 시간표 초기화 선택 시
    private void menuTTResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTTResetMouseClicked
        reset();
        Manage_menuPanel.setVisible(true);
        TTResetPanel.setVisible(true);

        menuTTReset.setBackground(yellow);
    }//GEN-LAST:event_menuTTResetMouseClicked

    // 토큰 입력 후 토큰 생성 버튼
    private void TokenOkButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TokenOkButtActionPerformed
        // 디비에 값 저장
        
    }//GEN-LAST:event_TokenOkButtActionPerformed

    // 토큰 초기화 버튼
    private void TokenResetButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TokenResetButtActionPerformed
        // 디비에서 값 삭제
        
    }//GEN-LAST:event_TokenResetButtActionPerformed

    // 회원 정보 승인 버튼
    private void UserOkButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserOkButtActionPerformed
        // 테이블에서 선택된 행 회원 정보 승인 
        
    }//GEN-LAST:event_UserOkButtActionPerformed

    // 회원 승인 정보 초기화 버튼
    private void UserResetButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserResetButtActionPerformed
        // 모든 회원 승인 정보 미승인으로 디비값 수정
        
    }//GEN-LAST:event_UserResetButtActionPerformed

    // 시간표 초기화 버튼
    private void TTResetButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TTResetButtActionPerformed
        // 모든 시간표 정보 디비에서 삭제
        
    }//GEN-LAST:event_TTResetButtActionPerformed

    private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField1ActionPerformed

    private void LabCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LabCheckButtActionPerformed
        seatStatusPanel.setVisible(true);

        // 입력받은 강의실의 각 좌석에 현재 사용하고 있는 학생 이름 띄우기

        s1.setText("홍길동");
    }//GEN-LAST:event_LabCheckButtActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void SNameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SNameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SNameTextActionPerformed

    private void SEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SEndActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

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
            java.util.logging.Logger.getLogger(AssistantMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AssistantMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AssistantMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AssistantMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AssistantMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ChangeButt;
    private javax.swing.JButton DeleteButt;
    private javax.swing.JButton LabCheckButt;
    private javax.swing.JPanel LabManage_menuPanel;
    private javax.swing.JButton LabRightCheckButt;
    private javax.swing.JPanel LabStatusPanel;
    private javax.swing.JButton ListCancleButt;
    private javax.swing.JButton ListCheckButt;
    private javax.swing.JPanel ListCheckPanel;
    private javax.swing.JPanel ManageRightPanel;
    private javax.swing.JPanel Manage_menuPanel;
    private javax.swing.JPanel NoticeAddPanel;
    private javax.swing.JPanel ReserCheckPanel;
    private javax.swing.JPanel ReserManage_menuPanel;
    private javax.swing.JButton ReserOkButt;
    private javax.swing.JButton RightButt;
    private javax.swing.JTextField SDate;
    private javax.swing.JTextField SDateText;
    private javax.swing.JTextField SEnd;
    private javax.swing.JComboBox<String> SEndTime;
    private javax.swing.JTextField SNameText;
    private javax.swing.JTextField SStart;
    private javax.swing.JComboBox<String> SStartTime;
    private javax.swing.JButton SeatCheckButt;
    private javax.swing.JButton SeatReserCancle;
    private javax.swing.JPanel SeatReserCanclePanel;
    private javax.swing.JPanel SeatReserCheckPanel;
    private javax.swing.JButton SeminarAddButt;
    private javax.swing.JPanel SeminarAddPanel;
    private javax.swing.JPanel TT;
    private javax.swing.JButton TTCheckButt;
    private javax.swing.JButton TTResetButt;
    private javax.swing.JPanel TTResetPanel;
    private javax.swing.JButton TimeTableAddButt;
    private javax.swing.JPanel TimeTableAddPanel;
    private javax.swing.JPanel TimeTableCheckPanel;
    private javax.swing.JPanel TimeTable_menuPanel;
    private javax.swing.JPanel TitlePanel;
    private javax.swing.JButton TokenOkButt;
    private javax.swing.JPanel TokenPanel;
    private javax.swing.JButton TokenResetButt;
    private javax.swing.JButton UserChangeButt;
    private javax.swing.JPanel UserChangePanel;
    private javax.swing.JButton UserDeleteButt;
    private javax.swing.JPanel UserDeletePanel;
    private javax.swing.JPanel UserInfoPanel;
    private javax.swing.JPanel UserManage_menuPanel;
    private javax.swing.JButton UserOkButt;
    private javax.swing.JPanel UserOkPanel;
    private javax.swing.JButton UserResetButt;
    private javax.swing.JPanel UserResetPanel;
    private javax.swing.JTextField date;
    private javax.swing.JTextField dateNum;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField lab;
    private javax.swing.JButton labManageButt;
    private javax.swing.JComboBox<String> labNum;
    private java.awt.Label label1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton manageButt;
    private javax.swing.JPanel menuLabStatus;
    private javax.swing.JPanel menuListCheck;
    private javax.swing.JPanel menuManageRight;
    private javax.swing.JPanel menuNoticeAdd;
    private javax.swing.JPanel menuReserCheck;
    private javax.swing.JPanel menuSeminarAdd;
    private javax.swing.JPanel menuTTReset;
    private javax.swing.JPanel menuTimeTableAdd;
    private javax.swing.JPanel menuTimeTableCheck;
    private javax.swing.JPanel menuToken;
    private javax.swing.JPanel menuUserChange;
    private javax.swing.JPanel menuUserDelete;
    private javax.swing.JPanel menuUserOk;
    private javax.swing.JPanel menuUserReset;
    private javax.swing.JPanel menuseatReserCheck;
    private javax.swing.JButton noticeManageButt;
    private javax.swing.JButton reservationManageButt;
    private javax.swing.JButton s1;
    private javax.swing.JButton s10;
    private javax.swing.JButton s11;
    private javax.swing.JButton s12;
    private javax.swing.JButton s13;
    private javax.swing.JButton s14;
    private javax.swing.JButton s15;
    private javax.swing.JButton s16;
    private javax.swing.JButton s17;
    private javax.swing.JButton s18;
    private javax.swing.JButton s19;
    private javax.swing.JButton s2;
    private javax.swing.JButton s20;
    private javax.swing.JButton s21;
    private javax.swing.JButton s22;
    private javax.swing.JButton s23;
    private javax.swing.JButton s24;
    private javax.swing.JButton s25;
    private javax.swing.JButton s26;
    private javax.swing.JButton s27;
    private javax.swing.JButton s28;
    private javax.swing.JButton s29;
    private javax.swing.JButton s3;
    private javax.swing.JButton s30;
    private javax.swing.JButton s4;
    private javax.swing.JButton s5;
    private javax.swing.JButton s6;
    private javax.swing.JButton s7;
    private javax.swing.JButton s8;
    private javax.swing.JButton s9;
    private javax.swing.JButton seat1;
    private javax.swing.JButton seat10;
    private javax.swing.JButton seat11;
    private javax.swing.JButton seat12;
    private javax.swing.JButton seat13;
    private javax.swing.JButton seat14;
    private javax.swing.JButton seat15;
    private javax.swing.JButton seat16;
    private javax.swing.JButton seat17;
    private javax.swing.JButton seat18;
    private javax.swing.JButton seat19;
    private javax.swing.JButton seat2;
    private javax.swing.JButton seat20;
    private javax.swing.JButton seat21;
    private javax.swing.JButton seat22;
    private javax.swing.JButton seat23;
    private javax.swing.JButton seat24;
    private javax.swing.JButton seat25;
    private javax.swing.JButton seat26;
    private javax.swing.JButton seat27;
    private javax.swing.JButton seat28;
    private javax.swing.JButton seat29;
    private javax.swing.JButton seat3;
    private javax.swing.JButton seat30;
    private javax.swing.JButton seat4;
    private javax.swing.JButton seat5;
    private javax.swing.JButton seat6;
    private javax.swing.JButton seat7;
    private javax.swing.JButton seat8;
    private javax.swing.JButton seat9;
    private javax.swing.JPanel seatCheckPanel;
    private javax.swing.JTextField seatNum;
    private javax.swing.JPanel seatStatusPanel;
    private javax.swing.JButton seminarCheckButt;
    private javax.swing.JPanel seminarReserCheckPanel;
    private java.awt.TextField textField1;
    private javax.swing.JButton timeTableManageButt;
    private javax.swing.JButton userInforManageButt;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabReservationSystem;

import java.awt.Color;
import java.sql.*;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;

/**
 *
 * @author asdf0
 */
public class ProfessorMain extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql; //쿼리문 받을 변수
   
    Color yellow = new Color(254,255,233);  // 노란색 저장
    
    ArrayList<JButton> seatState = new ArrayList<>();  // 좌석별 상태

    public void seatState() {
        seatState.add(seat1);
        seatState.add(seat2);
        seatState.add(seat3);
        seatState.add(seat4);
        seatState.add(seat5);
        seatState.add(seat6);
        seatState.add(seat7);
        seatState.add(seat8);
        seatState.add(seat9);
        seatState.add(seat10);
        seatState.add(seat11);
        seatState.add(seat12);
        seatState.add(seat13);
        seatState.add(seat14);
        seatState.add(seat15);
        seatState.add(seat16);
        seatState.add(seat17);
        seatState.add(seat18);
        seatState.add(seat19);
        seatState.add(seat20);
        seatState.add(seat21);
        seatState.add(seat22);
        seatState.add(seat23);
        seatState.add(seat24);
        seatState.add(seat25);
        seatState.add(seat26);
        seatState.add(seat27);
        seatState.add(seat28);
        seatState.add(seat29);
        seatState.add(seat30);
    }
    
    public ProfessorMain() {
        initComponents();
        
        TitlePanel.setVisible(true);
        mainPanel.setVisible(true);
        
        // 특강 및 세미나
        Seminar_menuPanel.setVisible(false);
        
        SeminarReserPanel.setVisible(false);
        SeminarCanclePanel.setVisible(false);
        SeminarReserCheckPanel.setVisible(false);
        
        // 시간표 조회
        TTCheck_menuPanel.setVisible(false);
        
        TimeTableCheckPanel.setVisible(false);
        TT.setVisible(false);
        
        // 실습실 조회
        LabCheck_menuPanel.setVisible(false);
        
        LabCheckPanel.setVisible(false);
        seatCheckPanel.setVisible(false);
        
        seatState();
                
    }

    // 화면에 띄우는 패널들 초기화하는 함수
    public void reset() {
        TitlePanel.setVisible(true);
        mainPanel.setVisible(false);
        
        // 특강 및 세미나
        Seminar_menuPanel.setVisible(false);
        menuSeminarReser.setBackground(Color.WHITE);
        menuSeminarCancle.setBackground(Color.WHITE);
       
        SeminarReserPanel.setVisible(false);
        SeminarCanclePanel.setVisible(false);
        SeminarReserCheckPanel.setVisible(false);
        
        // 시간표 조회
        TTCheck_menuPanel.setVisible(false);
        menuTTCheck.setBackground(Color.WHITE);
        
        TimeTableCheckPanel.setVisible(false);
        TT.setVisible(false);
        
        // 실습실 조회
        LabCheck_menuPanel.setVisible(false);
        menuLabCheck.setBackground(Color.WHITE);
        
        LabCheckPanel.setVisible(false);
        seatCheckPanel.setVisible(false);
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        mainPanel = new javax.swing.JPanel();
        labCheckButt = new javax.swing.JButton();
        seminarReserButt = new javax.swing.JButton();
        timeTableCheckButt = new javax.swing.JButton();
        TitlePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textField1 = new java.awt.TextField();
        label1 = new java.awt.Label();
        jLabel2 = new javax.swing.JLabel();
        Seminar_menuPanel = new javax.swing.JPanel();
        menuSeminarCancle = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        menuSeminarReser = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        SeminarCanclePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        CancleButt = new javax.swing.JButton();
        SeminarReserPanel = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        dateText = new javax.swing.JTextField();
        startText = new javax.swing.JTextField();
        endText = new javax.swing.JTextField();
        seminarCheckButt = new javax.swing.JButton();
        SeminarReserCheckPanel = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        start = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        end = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        ReserButt = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        TimeTableCheckPanel = new javax.swing.JPanel();
        jComboBox4 = new javax.swing.JComboBox<>();
        TTCheckButt = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        TT = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        TTCheck_menuPanel = new javax.swing.JPanel();
        menuTTCheck = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        LabCheck_menuPanel = new javax.swing.JPanel();
        menuLabCheck = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        LabCheckPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        LabCheckButt = new javax.swing.JButton();
        seatCheckPanel = new javax.swing.JPanel();
        seat1 = new javax.swing.JButton();
        seat2 = new javax.swing.JButton();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1120, 750));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        labCheckButt.setBackground(new java.awt.Color(255, 255, 255));
        labCheckButt.setForeground(new java.awt.Color(255, 255, 255));
        labCheckButt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ButtPicture/조교_실습실 조회 버튼.PNG"))); // NOI18N
        labCheckButt.setBorderPainted(false);
        labCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labCheckButtActionPerformed(evt);
            }
        });

        seminarReserButt.setBackground(new java.awt.Color(255, 255, 255));
        seminarReserButt.setForeground(new java.awt.Color(255, 255, 255));
        seminarReserButt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ButtPicture/교수_특강 및 세미나 버튼.PNG"))); // NOI18N
        seminarReserButt.setBorderPainted(false);
        seminarReserButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seminarReserButtActionPerformed(evt);
            }
        });

        timeTableCheckButt.setBackground(new java.awt.Color(255, 255, 255));
        timeTableCheckButt.setForeground(new java.awt.Color(255, 255, 255));
        timeTableCheckButt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ButtPicture/교수_시간표 조회 버튼.PNG"))); // NOI18N
        timeTableCheckButt.setBorderPainted(false);
        timeTableCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeTableCheckButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(seminarReserButt, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(labCheckButt)
                .addGap(98, 98, 98)
                .addComponent(timeTableCheckButt, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timeTableCheckButt, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labCheckButt, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seminarReserButt, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(194, 194, 194))
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
        textField1.setText("홍길동");

        label1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label1.setText("님");

        jLabel2.setText("(교수)");

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

        Seminar_menuPanel.setBackground(new java.awt.Color(255, 255, 255));
        Seminar_menuPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        menuSeminarCancle.setBackground(new java.awt.Color(255, 255, 255));
        menuSeminarCancle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuSeminarCancle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuSeminarCancle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSeminarCancleMouseClicked(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel41.setText("특강 조회 및 취소");

        javax.swing.GroupLayout menuSeminarCancleLayout = new javax.swing.GroupLayout(menuSeminarCancle);
        menuSeminarCancle.setLayout(menuSeminarCancleLayout);
        menuSeminarCancleLayout.setHorizontalGroup(
            menuSeminarCancleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuSeminarCancleLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel41)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuSeminarCancleLayout.setVerticalGroup(
            menuSeminarCancleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuSeminarCancleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuSeminarReser.setBackground(new java.awt.Color(255, 255, 255));
        menuSeminarReser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuSeminarReser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuSeminarReser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSeminarReserMouseClicked(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel42.setText("특강 예약");

        javax.swing.GroupLayout menuSeminarReserLayout = new javax.swing.GroupLayout(menuSeminarReser);
        menuSeminarReser.setLayout(menuSeminarReserLayout);
        menuSeminarReserLayout.setHorizontalGroup(
            menuSeminarReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuSeminarReserLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel42)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuSeminarReserLayout.setVerticalGroup(
            menuSeminarReserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuSeminarReserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel43.setFont(new java.awt.Font("맑은 고딕", 0, 36)); // NOI18N
        jLabel43.setText("세미나");

        jLabel44.setFont(new java.awt.Font("맑은 고딕", 0, 36)); // NOI18N
        jLabel44.setText("특강 및");

        javax.swing.GroupLayout Seminar_menuPanelLayout = new javax.swing.GroupLayout(Seminar_menuPanel);
        Seminar_menuPanel.setLayout(Seminar_menuPanelLayout);
        Seminar_menuPanelLayout.setHorizontalGroup(
            Seminar_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuSeminarCancle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuSeminarReser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(Seminar_menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Seminar_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44)
                    .addGroup(Seminar_menuPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel43)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        Seminar_menuPanelLayout.setVerticalGroup(
            Seminar_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Seminar_menuPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43)
                .addGap(18, 18, 18)
                .addComponent(menuSeminarReser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuSeminarCancle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(314, Short.MAX_VALUE))
        );

        getContentPane().add(Seminar_menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 150, 620));

        SeminarCanclePanel.setBackground(new java.awt.Color(254, 255, 233));

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

        CancleButt.setBackground(new java.awt.Color(255, 255, 255));
        CancleButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        CancleButt.setText("취소");
        CancleButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancleButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SeminarCanclePanelLayout = new javax.swing.GroupLayout(SeminarCanclePanel);
        SeminarCanclePanel.setLayout(SeminarCanclePanelLayout);
        SeminarCanclePanelLayout.setHorizontalGroup(
            SeminarCanclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SeminarCanclePanelLayout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addGroup(SeminarCanclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CancleButt)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 818, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69))
        );
        SeminarCanclePanelLayout.setVerticalGroup(
            SeminarCanclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SeminarCanclePanelLayout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CancleButt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        getContentPane().add(SeminarCanclePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        SeminarReserPanel.setBackground(new java.awt.Color(254, 255, 233));

        jLabel28.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel28.setText("시작시간 : ");

        jLabel29.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel29.setText("종료 시간 : ");

        jLabel30.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel30.setText("날짜 : ");

        dateText.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        dateText.setText("2022 - 11 - 06");

        startText.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        startText.setText("jTextField7");

        endText.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        endText.setText("jTextField8");

        seminarCheckButt.setBackground(new java.awt.Color(255, 255, 255));
        seminarCheckButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        seminarCheckButt.setText("조회");
        seminarCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seminarCheckButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SeminarReserPanelLayout = new javax.swing.GroupLayout(SeminarReserPanel);
        SeminarReserPanel.setLayout(SeminarReserPanelLayout);
        SeminarReserPanelLayout.setHorizontalGroup(
            SeminarReserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SeminarReserPanelLayout.createSequentialGroup()
                .addGap(365, 365, 365)
                .addGroup(SeminarReserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(seminarCheckButt)
                    .addGroup(SeminarReserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(SeminarReserPanelLayout.createSequentialGroup()
                            .addComponent(jLabel28)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(startText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(SeminarReserPanelLayout.createSequentialGroup()
                            .addComponent(jLabel30)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(SeminarReserPanelLayout.createSequentialGroup()
                            .addComponent(jLabel29)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(endText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(432, Short.MAX_VALUE))
        );
        SeminarReserPanelLayout.setVerticalGroup(
            SeminarReserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SeminarReserPanelLayout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addGroup(SeminarReserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(SeminarReserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(startText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(SeminarReserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(endText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addComponent(seminarCheckButt)
                .addContainerGap(232, Short.MAX_VALUE))
        );

        getContentPane().add(SeminarReserPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 950, 620));

        SeminarReserCheckPanel.setBackground(new java.awt.Color(254, 255, 233));

        jLabel31.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel31.setText("날짜 : ");

        jLabel32.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel32.setText("시작시간 : ");

        date.setEditable(false);
        date.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        date.setText("2022-10-10");

        start.setEditable(false);
        start.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        start.setText("jTextField10");

        jLabel33.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel33.setText("종료시간 : ");

        end.setEditable(false);
        end.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        end.setText("jTextField11");

        jLabel34.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel34.setText("사용 가능한 강의실");

        ReserButt.setBackground(new java.awt.Color(255, 255, 255));
        ReserButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        ReserButt.setText("예약");
        ReserButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReserButtActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jRadioButton1.setText("915");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jRadioButton2.setText("916");

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jRadioButton3.setText("918");

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jRadioButton4.setText("911");

        javax.swing.GroupLayout SeminarReserCheckPanelLayout = new javax.swing.GroupLayout(SeminarReserCheckPanel);
        SeminarReserCheckPanel.setLayout(SeminarReserCheckPanelLayout);
        SeminarReserCheckPanelLayout.setHorizontalGroup(
            SeminarReserCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SeminarReserCheckPanelLayout.createSequentialGroup()
                .addGroup(SeminarReserCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SeminarReserCheckPanelLayout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SeminarReserCheckPanelLayout.createSequentialGroup()
                        .addGap(358, 358, 358)
                        .addGroup(SeminarReserCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SeminarReserCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jRadioButton3)
                                .addComponent(jRadioButton2)
                                .addComponent(jRadioButton1)
                                .addGroup(SeminarReserCheckPanelLayout.createSequentialGroup()
                                    .addComponent(jRadioButton4)
                                    .addGap(119, 119, 119)
                                    .addComponent(ReserButt)))
                            .addGroup(SeminarReserCheckPanelLayout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addGap(60, 60, 60)))))
                .addContainerGap(190, Short.MAX_VALUE))
        );
        SeminarReserCheckPanelLayout.setVerticalGroup(
            SeminarReserCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SeminarReserCheckPanelLayout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(SeminarReserCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addComponent(jLabel34)
                .addGap(27, 27, 27)
                .addComponent(jRadioButton1)
                .addGap(27, 27, 27)
                .addComponent(jRadioButton2)
                .addGap(27, 27, 27)
                .addComponent(jRadioButton3)
                .addGap(27, 27, 27)
                .addGroup(SeminarReserCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ReserButt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton4))
                .addContainerGap(165, Short.MAX_VALUE))
        );

        getContentPane().add(SeminarReserCheckPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 950, 620));

        TimeTableCheckPanel.setBackground(new java.awt.Color(254, 255, 233));

        jComboBox4.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "915", "916", "918", "911" }));

        TTCheckButt.setBackground(new java.awt.Color(255, 255, 255));
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
                .addContainerGap(80, Short.MAX_VALUE)
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

        getContentPane().add(TimeTableCheckPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 950, 620));

        TTCheck_menuPanel.setBackground(new java.awt.Color(255, 255, 255));
        TTCheck_menuPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        menuTTCheck.setBackground(new java.awt.Color(255, 255, 255));
        menuTTCheck.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuTTCheck.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuTTCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuTTCheckMouseClicked(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel46.setText("실습실 별");

        jLabel45.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel45.setText("시간표 조회");

        javax.swing.GroupLayout menuTTCheckLayout = new javax.swing.GroupLayout(menuTTCheck);
        menuTTCheck.setLayout(menuTTCheckLayout);
        menuTTCheckLayout.setHorizontalGroup(
            menuTTCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuTTCheckLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(menuTTCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuTTCheckLayout.setVerticalGroup(
            menuTTCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuTTCheckLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jLabel47.setFont(new java.awt.Font("맑은 고딕", 0, 36)); // NOI18N
        jLabel47.setText("조회");

        jLabel48.setFont(new java.awt.Font("맑은 고딕", 0, 36)); // NOI18N
        jLabel48.setText("시간표");

        javax.swing.GroupLayout TTCheck_menuPanelLayout = new javax.swing.GroupLayout(TTCheck_menuPanel);
        TTCheck_menuPanel.setLayout(TTCheck_menuPanelLayout);
        TTCheck_menuPanelLayout.setHorizontalGroup(
            TTCheck_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuTTCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(TTCheck_menuPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(TTCheck_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TTCheck_menuPanelLayout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addGap(18, 18, 18)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        TTCheck_menuPanelLayout.setVerticalGroup(
            TTCheck_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TTCheck_menuPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel47)
                .addGap(18, 18, 18)
                .addComponent(menuTTCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(391, Short.MAX_VALUE))
        );

        getContentPane().add(TTCheck_menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 150, 620));

        LabCheck_menuPanel.setBackground(new java.awt.Color(255, 255, 255));
        LabCheck_menuPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        menuLabCheck.setBackground(new java.awt.Color(255, 255, 255));
        menuLabCheck.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuLabCheck.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuLabCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuLabCheckMouseClicked(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel49.setText("실습실");

        jLabel50.setFont(new java.awt.Font("맑은 고딕", 0, 12)); // NOI18N
        jLabel50.setText("사용현황 조회");

        javax.swing.GroupLayout menuLabCheckLayout = new javax.swing.GroupLayout(menuLabCheck);
        menuLabCheck.setLayout(menuLabCheckLayout);
        menuLabCheckLayout.setHorizontalGroup(
            menuLabCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLabCheckLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(menuLabCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel50)
                    .addGroup(menuLabCheckLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel49)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        menuLabCheckLayout.setVerticalGroup(
            menuLabCheckLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLabCheckLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jLabel51.setFont(new java.awt.Font("맑은 고딕", 0, 36)); // NOI18N
        jLabel51.setText("조회");

        jLabel52.setFont(new java.awt.Font("맑은 고딕", 0, 36)); // NOI18N
        jLabel52.setText("실습실");

        javax.swing.GroupLayout LabCheck_menuPanelLayout = new javax.swing.GroupLayout(LabCheck_menuPanel);
        LabCheck_menuPanel.setLayout(LabCheck_menuPanelLayout);
        LabCheck_menuPanelLayout.setHorizontalGroup(
            LabCheck_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuLabCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(LabCheck_menuPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(LabCheck_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LabCheck_menuPanelLayout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addGap(18, 18, 18)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        LabCheck_menuPanelLayout.setVerticalGroup(
            LabCheck_menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LabCheck_menuPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel51)
                .addGap(18, 18, 18)
                .addComponent(menuLabCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(391, Short.MAX_VALUE))
        );

        getContentPane().add(LabCheck_menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 150, 620));

        LabCheckPanel.setBackground(new java.awt.Color(254, 255, 233));

        jLabel4.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel4.setText("강의실 : ");

        jComboBox1.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "915", "916", "918", "911" }));

        LabCheckButt.setBackground(new java.awt.Color(255, 255, 255));
        LabCheckButt.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        LabCheckButt.setText("조회");
        LabCheckButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LabCheckButtActionPerformed(evt);
            }
        });

        seatCheckPanel.setBackground(new java.awt.Color(254, 255, 233));
        seatCheckPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout seatCheckPanelLayout = new javax.swing.GroupLayout(seatCheckPanel);
        seatCheckPanel.setLayout(seatCheckPanelLayout);
        seatCheckPanelLayout.setHorizontalGroup(
            seatCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seatCheckPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(seatCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, seatCheckPanelLayout.createSequentialGroup()
                        .addComponent(seat9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(seat13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat15, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat16, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, seatCheckPanelLayout.createSequentialGroup()
                        .addComponent(seat1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(seat5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seat8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(seatCheckPanelLayout.createSequentialGroup()
                        .addGroup(seatCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(seatCheckPanelLayout.createSequentialGroup()
                                .addComponent(seat17, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seat18, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seat19, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seat20, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(seatCheckPanelLayout.createSequentialGroup()
                                .addComponent(seat25, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seat26, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seat27, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(100, 100, 100)
                        .addGroup(seatCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(seatCheckPanelLayout.createSequentialGroup()
                                .addComponent(seat28, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seat29, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seat30, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(seatCheckPanelLayout.createSequentialGroup()
                                .addComponent(seat21, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seat22, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seat23, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seat24, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(15, 15, 15))
        );
        seatCheckPanelLayout.setVerticalGroup(
            seatCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(seatCheckPanelLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(seatCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(seat5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seat1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(80, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout LabCheckPanelLayout = new javax.swing.GroupLayout(LabCheckPanel);
        LabCheckPanel.setLayout(LabCheckPanelLayout);
        LabCheckPanelLayout.setHorizontalGroup(
            LabCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LabCheckPanelLayout.createSequentialGroup()
                .addGap(0, 29, Short.MAX_VALUE)
                .addComponent(seatCheckPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addGroup(LabCheckPanelLayout.createSequentialGroup()
                .addGap(327, 327, 327)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(LabCheckButt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LabCheckPanelLayout.setVerticalGroup(
            LabCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LabCheckPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(LabCheckPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabCheckButt))
                .addGap(34, 34, 34)
                .addComponent(seatCheckPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        getContentPane().add(LabCheckPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 960, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // 메인화면 - 실습실 조회 버튼
    private void labCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labCheckButtActionPerformed
       reset();
       LabCheck_menuPanel.setVisible(true);
       LabCheckPanel.setVisible(true);
       menuLabCheck.setBackground(yellow);
    }//GEN-LAST:event_labCheckButtActionPerformed

    // 메인화면 - 특강 및 세미나 버튼
    private void seminarReserButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seminarReserButtActionPerformed
        reset();
        Seminar_menuPanel.setVisible(true);
        SeminarReserPanel.setVisible(true);
        menuSeminarReser.setBackground(yellow);
    }//GEN-LAST:event_seminarReserButtActionPerformed

    // 메인화면 - 시간표 조회 버튼
    private void timeTableCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeTableCheckButtActionPerformed
        reset();
        TTCheck_menuPanel.setVisible(true);
        TimeTableCheckPanel.setVisible(true);
        menuTTCheck.setBackground(yellow);
    }//GEN-LAST:event_timeTableCheckButtActionPerformed

    // 타이틀 패널 클릭시 메인 패널로 이동
    private void TitlePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TitlePanelMouseClicked
        reset();
        mainPanel.setVisible(true);
    }//GEN-LAST:event_TitlePanelMouseClicked

    // 특강 및 세미나 메뉴바 - 특강 조회 및 취소 선택 시
    private void menuSeminarCancleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSeminarCancleMouseClicked
        reset();
        Seminar_menuPanel.setVisible(true);
        SeminarCanclePanel.setVisible(true);

        menuSeminarCancle.setBackground(yellow);
        
        // 테이블에 db에서 특강 예약 값 가져와서 띄우기
    }//GEN-LAST:event_menuSeminarCancleMouseClicked

    // 특강 및 세미나 메뉴바 - 특강 예약 선택 시
    private void menuSeminarReserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSeminarReserMouseClicked
        reset();
        Seminar_menuPanel.setVisible(true);
        SeminarReserPanel.setVisible(true);

        menuSeminarReser.setBackground(yellow);
    }//GEN-LAST:event_menuSeminarReserMouseClicked

    // 특강 날짜, 시간 입력 후 사용가능한 강의실 조회
    private void seminarCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seminarCheckButtActionPerformed
        reset();
        Seminar_menuPanel.setVisible(true);
        menuSeminarReser.setBackground(yellow);

        SeminarReserCheckPanel.setVisible(true);
        
        // SeminarReserPanel에서 입력한 날짜, 시작시간, 종료시간 값 받아와서 SeminarReserCheckPanel에 출력
        date.setText(dateText.getText());
        start.setText(startText.getText());
        end.setText(endText.getText());
        
        // 사용 불가능한 강의실 라디오 버튼 비활성화
    }//GEN-LAST:event_seminarCheckButtActionPerformed

    // 특강 조회 및 취소 - 취소 버튼 클릭 시
    private void CancleButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancleButtActionPerformed
        // 테이블에서 선택된 행 db에서 삭제
    }//GEN-LAST:event_CancleButtActionPerformed

    // 실습실 선택 후 시간표 조회 클릭 시
    private void TTCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TTCheckButtActionPerformed
        TT.setVisible(true);
        
        // 테이블에 db에서 시간표 값 가져와서 띄우기
    }//GEN-LAST:event_TTCheckButtActionPerformed

    // 시간표 조회 메뉴바 - 실습실 별 시간표 조회 선택 시
    private void menuTTCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTTCheckMouseClicked
        reset();
        TTCheck_menuPanel.setVisible(true);
        TimeTableCheckPanel.setVisible(true);

        menuTTCheck.setBackground(yellow);
    }//GEN-LAST:event_menuTTCheckMouseClicked

    // 특강 예약 버튼
    private void ReserButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReserButtActionPerformed
        // db 값 추가 
    }//GEN-LAST:event_ReserButtActionPerformed

    // 실습실 조회 메뉴바 - 실습실 사용현황 조회 선택 시
    private void menuLabCheckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuLabCheckMouseClicked
        reset();
        LabCheck_menuPanel.setVisible(true);
        LabCheckPanel.setVisible(true);

        menuLabCheck.setBackground(yellow);
    }//GEN-LAST:event_menuLabCheckMouseClicked

    // 강의실 선택 후 실습실 사용 현황 조회 버튼
    private void LabCheckButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LabCheckButtActionPerformed
        seatCheckPanel.setVisible(true);
        
        // 입력받은 강의실의 각 좌석에 현재 사용하고 있는 학생 이름 띄우기
        
        for (int i = 0; i < 30; i++) {
            seatState.get(i).setText("");  // 좌석 초기화
            seatState.get(i).setEnabled(false);  // 좌석 비활성화
        }

        LocalDate nowDate = LocalDate.now();  // 현재 날짜 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");  // "yyyy/MM/dd"형식으로 포맷 정의
        String formatedNow = nowDate.format(formatter);  // 포맷 적용

        LocalTime nowTime = LocalTime.now();  // 현재 시간
        int hour = nowTime.getHour();  // 시 구하기

        connect();  // 디비연결

        try {

            // 1번 좌석부터 30번 좌석에 대해서 사용중인 좌석 찾기 
            for (int i = 1; i <= 30; i++) {
                
                // 현재 시간을 기준으로 사용하고 있는 예약 찾기
                sql = "select s.sName from reservation r, student s where r.sId = s.sId and r.labId = ? and r.dateR = ? and (r.startTimeR <= ? and r.endTimeR > ?) and r.useCheck = ? and seatId = ?";

                pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, (String) jComboBox1.getSelectedItem());  // 강의실
                pstmt.setString(2, formatedNow);  // 오늘 날짜
                //pstmt.setString(2, "2022/11/10");  // 오늘 날짜
                pstmt.setString(3, Integer.toString(hour));  // 현재 시간의 '시'
                pstmt.setString(4, Integer.toString(hour));  // 현재 시간의 '시'
                //pstmt.setString(3, "18");  // 현재 시간의 '시'
                //pstmt.setString(4, "18");  // 현재 시간의 '시'
                pstmt.setInt(5, 1);  // 사용 여부가 1
                pstmt.setString(6, Integer.toString(i));  // i번째 좌석

                rs = pstmt.executeQuery();

                while (rs.next()) {
                    seatState.get(i - 1).setText(rs.getString(1));  // 사용 중인 좌석에 이름 출력
                    seatState.get(i - 1).setEnabled(true);  // 사용 중인 좌석 활성화
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
    }//GEN-LAST:event_LabCheckButtActionPerformed

    /**/
    
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
            java.util.logging.Logger.getLogger(ProfessorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProfessorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProfessorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProfessorMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProfessorMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancleButt;
    private javax.swing.JButton LabCheckButt;
    private javax.swing.JPanel LabCheckPanel;
    private javax.swing.JPanel LabCheck_menuPanel;
    private javax.swing.JButton ReserButt;
    private javax.swing.JPanel SeminarCanclePanel;
    private javax.swing.JPanel SeminarReserCheckPanel;
    private javax.swing.JPanel SeminarReserPanel;
    private javax.swing.JPanel Seminar_menuPanel;
    private javax.swing.JPanel TT;
    private javax.swing.JButton TTCheckButt;
    private javax.swing.JPanel TTCheck_menuPanel;
    private javax.swing.JPanel TimeTableCheckPanel;
    private javax.swing.JPanel TitlePanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField date;
    private javax.swing.JTextField dateText;
    private javax.swing.JTextField end;
    private javax.swing.JTextField endText;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable4;
    private javax.swing.JButton labCheckButt;
    private java.awt.Label label1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel menuLabCheck;
    private javax.swing.JPanel menuSeminarCancle;
    private javax.swing.JPanel menuSeminarReser;
    private javax.swing.JPanel menuTTCheck;
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
    private javax.swing.JButton seminarCheckButt;
    private javax.swing.JButton seminarReserButt;
    private javax.swing.JTextField start;
    private javax.swing.JTextField startText;
    private java.awt.TextField textField1;
    private javax.swing.JButton timeTableCheckButt;
    // End of variables declaration//GEN-END:variables
}

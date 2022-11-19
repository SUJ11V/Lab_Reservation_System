package source;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import source.Reservation;

public class PermissionOn implements Permission {

    public void PermissionOn() {
    }

    @Override
    public void possible(Reservation reservation) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql;

        try {
            //JDBC드라이버 로딩
            Class.forName("com.mysql.cj.jdbc.Driver");
            //디비 연결 용 변수
            String jdbcDriver = "jdbc:mysql://211.213.95.123:3360/labmanagement?serverTimeZone=UTC";
            String dbId = "20203128"; //MySQL 접속 아이디("20203132"도 가능)
            String dbPw = "20203128"; //접속 비밀번호(아이디를 20203132로 작성시, 비밀번호도 아이디와 같도록
            conn = DriverManager.getConnection(jdbcDriver, dbId, dbPw);

            // 예약 승인 1로 변경
            sql = "update reservation set reserPermission = 1 where labId = ? and dateR = ? and startTimeR = ? and endTimeR = ? ";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, reservation.labId);  // 강의실
            pstmt.setString(2, reservation.dateR);  // 날짜
            pstmt.setString(3, reservation.startTimeR);  // 시작시간
            pstmt.setString(4, reservation.endTimeR);  // 종료시간

            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "예약이 승인되었습니다.");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PermissionOn.class.getName()).log(Level.SEVERE, null, ex);
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

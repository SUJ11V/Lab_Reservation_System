package source;

public class PermissionOff implements Permission {

    public void PermissionOff() {
    }

    @Override
    public void possible(Reservation reservation) {
        System.out.println("예약 미승인");
    }
}

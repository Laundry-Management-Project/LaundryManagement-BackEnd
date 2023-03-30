package project.laundry.domain.entity.status;

public enum ClothStatus {
    WASH_BEFORE("세탁 전"),
    WASH_IN_PROCESS("세탁 중"),
    WASH_COMPLETE("세탁 완료");

    private final String status;

    ClothStatus(String displayName) {
        this.status = displayName;
    }

    public String getStatus() {
        return status;
    }

}

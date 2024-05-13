package com.multi.miniproject.member.model.dto;

public class PresetDto {


//    PRESET_NUM VARCHAR2(100) PRIMARY KEY,
//    MEMBER_NUM VARCHAR2(100) REFERENCES MEMBERS(MEMBER_NUM),
//    PRESET_COMFORT NUMBER NOT NULL,
//    PRESET_WEIGHT NUMBER NOT NULL,
//    PRESET_PASSENGER NUMBER NOT NULL


    // Preset 쪽지
    // /. presetNum ? ____PR1________
    // 1. memberNum ? ____1________
    // 2. presetComfort? _____1_______
    // 3. presetWeight? _______1_____
    // 4. presetPassenger? _____1_______

    private String presetNum;
    private String memberNum;
    private int presetComfort;
    private int presetWeight;
    private int presetPassenger;

    public PresetDto(){}

    public PresetDto(String presetNum, String memberNum, int presetComfort, int presetWeight, int presetPassenger) {
        this.presetNum = presetNum;
        this.memberNum = memberNum;
        this.presetComfort = presetComfort;
        this.presetWeight = presetWeight;
        this.presetPassenger = presetPassenger;
    }

    public String getPresetNum() {
        return presetNum;
    }

    public void setPresetNum(String presetNum) {
        this.presetNum = presetNum;
    }

    public String getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum;
    }

    public int getPresetComfort() {
        return presetComfort;
    }

    public void setPresetComfort(int presetComfort) {
        this.presetComfort = presetComfort;
    }

    public int getPresetWeight() {
        return presetWeight;
    }

    public void setPresetWeight(int presetWeight) {
        this.presetWeight = presetWeight;
    }

    public int getPresetPassenger() {
        return presetPassenger;
    }

    public void setPresetPassenger(int presetPassenger) {
        this.presetPassenger = presetPassenger;
    }

    @Override
    public String toString() {
        return "PresetDto{" +
                "presetNum='" + presetNum + '\'' +
                ", memberNum='" + memberNum + '\'' +
                ", presetComfort=" + presetComfort +
                ", presetWeight=" + presetWeight +
                ", presetPassenger=" + presetPassenger +
                '}';
    }
}

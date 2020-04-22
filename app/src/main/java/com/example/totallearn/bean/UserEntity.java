package com.example.totallearn.bean;

public class UserEntity {


    /**
     * hasNotReadInfo : 0
     * resultId : 200
     * resultType : 交易成功
     * ringInfo : {"nickName":"马冬妹","ringId":"","ringPass":"","userHeadImg":"http://47.99.88.134:8080/pdsci/pdsciupload//userImages/20190313/ffaafafeb7bd48e5a5e0f1a69040bfbf.jpg"}
     * userInfo : {"endDate":"2020-04-21","isCkk":false,"manualRotationFlag":"N","orgFlow":"af2dce53371548ffaa9515f3e8db75c3","orgName":"遵义医学院第五附属医院","roleId":"Student","roleName":"医师","rotationFlow":"6e4657894a144435a7e8bbd6b2a72a59","rotationName":"遵医五院-西医内科培训方案","schDays":"630","schProcess":"83","sessionNumber":"2018","startDate":"2018-08-01","trainingSpeId":"0100","trainingSpeName":"内科","trainingYears":"3","userFlow":"YH2019088","userName":"马冬妹","userSex":"女"}
     */

    private String hasNotReadInfo;
    private int resultId;
    private String resultType;
    private RingInfoBean ringInfo;
    private UserInfoBean userInfo;

    public String getHasNotReadInfo() {
        return hasNotReadInfo;
    }

    public void setHasNotReadInfo(String hasNotReadInfo) {
        this.hasNotReadInfo = hasNotReadInfo;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public RingInfoBean getRingInfo() {
        return ringInfo;
    }

    public void setRingInfo(RingInfoBean ringInfo) {
        this.ringInfo = ringInfo;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class RingInfoBean {
        /**
         * nickName : 马冬妹
         * ringId :
         * ringPass :
         * userHeadImg : http://47.99.88.134:8080/pdsci/pdsciupload//userImages/20190313/ffaafafeb7bd48e5a5e0f1a69040bfbf.jpg
         */

        private String nickName;
        private String ringId;
        private String ringPass;
        private String userHeadImg;

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getRingId() {
            return ringId;
        }

        public void setRingId(String ringId) {
            this.ringId = ringId;
        }

        public String getRingPass() {
            return ringPass;
        }

        public void setRingPass(String ringPass) {
            this.ringPass = ringPass;
        }

        public String getUserHeadImg() {
            return userHeadImg;
        }

        public void setUserHeadImg(String userHeadImg) {
            this.userHeadImg = userHeadImg;
        }
    }

    public static class UserInfoBean {
        /**
         * endDate : 2020-04-21
         * isCkk : false
         * manualRotationFlag : N
         * orgFlow : af2dce53371548ffaa9515f3e8db75c3
         * orgName : 遵义医学院第五附属医院
         * roleId : Student
         * roleName : 医师
         * rotationFlow : 6e4657894a144435a7e8bbd6b2a72a59
         * rotationName : 遵医五院-西医内科培训方案
         * schDays : 630
         * schProcess : 83
         * sessionNumber : 2018
         * startDate : 2018-08-01
         * trainingSpeId : 0100
         * trainingSpeName : 内科
         * trainingYears : 3
         * userFlow : YH2019088
         * userName : 马冬妹
         * userSex : 女
         */

        private String endDate;
        private boolean isCkk;
        private String manualRotationFlag;
        private String orgFlow;
        private String orgName;
        private String roleId;
        private String roleName;
        private String rotationFlow;
        private String rotationName;
        private String schDays;
        private String schProcess;
        private String sessionNumber;
        private String startDate;
        private String trainingSpeId;
        private String trainingSpeName;
        private String trainingYears;
        private String userFlow;
        private String userName;
        private String userSex;

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public boolean isIsCkk() {
            return isCkk;
        }

        public void setIsCkk(boolean isCkk) {
            this.isCkk = isCkk;
        }

        public String getManualRotationFlag() {
            return manualRotationFlag;
        }

        public void setManualRotationFlag(String manualRotationFlag) {
            this.manualRotationFlag = manualRotationFlag;
        }

        public String getOrgFlow() {
            return orgFlow;
        }

        public void setOrgFlow(String orgFlow) {
            this.orgFlow = orgFlow;
        }

        public String getOrgName() {
            return orgName;
        }

        public void setOrgName(String orgName) {
            this.orgName = orgName;
        }

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public String getRotationFlow() {
            return rotationFlow;
        }

        public void setRotationFlow(String rotationFlow) {
            this.rotationFlow = rotationFlow;
        }

        public String getRotationName() {
            return rotationName;
        }

        public void setRotationName(String rotationName) {
            this.rotationName = rotationName;
        }

        public String getSchDays() {
            return schDays;
        }

        public void setSchDays(String schDays) {
            this.schDays = schDays;
        }

        public String getSchProcess() {
            return schProcess;
        }

        public void setSchProcess(String schProcess) {
            this.schProcess = schProcess;
        }

        public String getSessionNumber() {
            return sessionNumber;
        }

        public void setSessionNumber(String sessionNumber) {
            this.sessionNumber = sessionNumber;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getTrainingSpeId() {
            return trainingSpeId;
        }

        public void setTrainingSpeId(String trainingSpeId) {
            this.trainingSpeId = trainingSpeId;
        }

        public String getTrainingSpeName() {
            return trainingSpeName;
        }

        public void setTrainingSpeName(String trainingSpeName) {
            this.trainingSpeName = trainingSpeName;
        }

        public String getTrainingYears() {
            return trainingYears;
        }

        public void setTrainingYears(String trainingYears) {
            this.trainingYears = trainingYears;
        }

        public String getUserFlow() {
            return userFlow;
        }

        public void setUserFlow(String userFlow) {
            this.userFlow = userFlow;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserSex() {
            return userSex;
        }

        public void setUserSex(String userSex) {
            this.userSex = userSex;
        }
    }
}

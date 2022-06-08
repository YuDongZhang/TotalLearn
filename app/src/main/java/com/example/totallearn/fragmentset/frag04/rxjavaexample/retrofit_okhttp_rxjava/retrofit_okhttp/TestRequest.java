package com.example.totallearn.fragmentset.frag04.rxjavaexample.retrofit_okhttp_rxjava.retrofit_okhttp;

public class TestRequest {
//    userFlow=219bd2afa5a746d598ca969b36c85807 &
//    deptFlow=0a44b5df92bd4bf6abed4d30697a72db  &'
//    pageIndex=2   &
//    pageSize=10


    public TestRequest(String userFlow, String deptFlow, int pageIndex, int pageSize) {
        this.userFlow = userFlow;
        this.deptFlow = deptFlow;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    private String userFlow;
    private String deptFlow;
    private int pageIndex;
    private int pageSize;

}

package com.example.ptboard;

public class Board {
    // 각각 데이터가 들어갈 공간 만들어줌
    String name;
    String Date;
    String user;
    String board;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    // 생성자 만들어줌
    public Board(String board, String name, String date, String user) {
        this.board = board;
        this.name = name;
        Date = date;
        this.user = user;
    }
    // Getter Setter 만들어줌
    public String getDate() {
        return Date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

}

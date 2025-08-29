package com.bookstore.meccrascunhos.models.enums;

import com.bookstore.meccrascunhos.exceptions.ResourceNotFoundException;

public enum Status {

    ATIVO(1, "Ativo"),
    INATIVO(0, "Inativo");

    private final int code;
    private final String name;

    Status(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static Status valueOfCode(int code) {
        for (Status s : Status.values()) {
            if (s.getCode() == code) return s;
        }
        throw new ResourceNotFoundException("Status with code " + code + " not found.");
    }

    public static Status valueOfName(String name) {
        for (Status s : Status.values()) {
            if (s.getName().equalsIgnoreCase(name)) return s;
        }
        throw new ResourceNotFoundException("Status with name " + name + " not found.");
    }

    @Override
    public String toString() {
        return "Status[" +
                "code=" + code +
                ", name=" + name +
                ']';
    }
}

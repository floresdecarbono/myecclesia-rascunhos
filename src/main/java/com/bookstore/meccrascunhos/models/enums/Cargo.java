package com.bookstore.meccrascunhos.models.enums;

public enum Cargo {

    PASTOR(1, "Pastor"),
    DIACONO(2, "Diácono"),
    SECRETARIO(3, "Secretário"),
    TESOUREIRO(4, "Tesoureiro"),
    LIDER(5, "Líder"),
    VOLUNTARIO(6, "Voluntário");

    private final Integer code;
    private final String name;

    private Cargo(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static Cargo valueOfCode(int code) {
        for (Cargo c : Cargo.values()) {
            if (c.getCode() == code) return c;
        }
        throw new IllegalArgumentException("Cargo de código " + code + " não existe.");
    }

    public static Cargo valueOfName(String name) {
        for (Cargo c : Cargo.values()) {
            if (c.getName().equalsIgnoreCase(name)) return c;
        }
        throw new IllegalArgumentException("Cargo de nome " + name + " não existe.");
    }

}

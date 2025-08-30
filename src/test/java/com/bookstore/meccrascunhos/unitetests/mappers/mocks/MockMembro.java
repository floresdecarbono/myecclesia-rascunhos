package com.bookstore.meccrascunhos.unitetests.mappers.mocks;

import com.bookstore.meccrascunhos.models.Membro;
import com.bookstore.meccrascunhos.models.dtos.MembroDTO;
import com.bookstore.meccrascunhos.models.enums.Cargo;
import com.bookstore.meccrascunhos.models.enums.Status;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MockMembro {

    public MembroDTO mockDTO() {
        return mockDTO(0);
    }

    public Membro mockEntity() {
        return mockEntity(0);
    }

    public MembroDTO mockDTO(Integer number) {
        MembroDTO m = new MembroDTO();
        m.setId(UUID.randomUUID());
        m.setNome("Entidade " + number);
        m.setCPF(criaCPF(number));
        m.setTelefone(criaTelefone(number));

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        m.setDataNascimento(LocalDate.parse("01/01/2001", fmt));

        if (number % 2 == 0) {
            m.setCargoName(Cargo.PASTOR);
            m.setStatusName(Status.ATIVO);
        }
        else {
            m.setCargoName(Cargo.LIDER);
            m.setStatusName(Status.INATIVO);
        }

        return m;
    }

    public List<MembroDTO> mockDTOList() {
        List<MembroDTO> list = new ArrayList<MembroDTO>();

        for (int i = 1; i <= 15; i++) {
            list.add(mockDTO(i));
        }

        return list;
    }

    public Membro mockEntity(Integer number) {
        Membro m = new Membro();
        m.setId(UUID.randomUUID());
        m.setNome("Entidade " + number);
        m.setCPF(criaCPF(number));
        m.setTelefone(criaTelefone(number));

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        m.setDataNascimento(LocalDate.parse("01/01/2001", fmt));

        if (number % 2 == 0) {
            m.setCargoCode(Cargo.PASTOR);
            m.setStatusCode(Status.ATIVO);
        }
        else {
            m.setCargoCode(Cargo.LIDER);
            m.setStatusCode(Status.INATIVO);
        }

        return m;
    }

    public List<Membro> mockEntityList() {
        List<Membro> list = new ArrayList<Membro>();

        for (int i = 0; i < 15; i++) {
            list.add(mockEntity(i));
        }

        return list;
    }

    public static String criaCPF(Integer number) {
        String n = String.valueOf(number);

        StringBuilder cpf = new StringBuilder();
        for (int i = 1; i <= 13; i++) {
            if (i == 4 || i == 8) {
                cpf.append(".");
            }
            else {
                if (i == 12) cpf.append("-");
                cpf.append(n);
            }
        }
        return cpf.toString();
    }

    public static String criaTelefone(Integer number) {
        String n = String.valueOf(number);

        StringBuilder telefone = new StringBuilder();
        telefone.append("(");

        for (int i = 1; i <= 12; i++) {
            switch (i) {
                case 3 -> telefone.append(") ");
                case 8 -> telefone.append("-");
                default -> telefone.append(n);
            }
        }

        return telefone.toString();
    }

}

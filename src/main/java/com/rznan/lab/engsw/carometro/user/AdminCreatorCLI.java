package com.rznan.lab.engsw.carometro.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

@Component
public class AdminCreatorCLI implements CommandLineRunner {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void run(String... args) throws Exception {

        boolean createAdmin = false;

        // verifica se o argumento --create-admin foi passado
        for (String arg : args) {
            if (arg.equalsIgnoreCase("--create-admin")) {
                createAdmin = true;
                break;
            }
        }

        if (createAdmin) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Criando administrador...");

            System.out.print("Informe o email do admin: ");
            String email = scanner.nextLine();

            System.out.print("Informe a senha do admin: ");
            String senha = scanner.nextLine();

            Usuario admin = new Usuario();
            admin.setEmail(email);
            admin.setSenha(senha);
            admin.setRole("ADMIN");

            try {
                usuarioService.saveUser(admin);
                System.out.println("Administrador criado com sucesso!");
            } catch (Exception e) {
                System.out.println("Erro ao criar administrador: " + e.getMessage());
            }
        }
    }
}

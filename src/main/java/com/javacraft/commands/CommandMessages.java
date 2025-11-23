package com.javacraft.commands;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
public class CommandMessages {

    public static void sendIfExplanation(CommandSourceStack source) {
        source.sendSuccess(()-> Component.literal(
                """
                        [Java Basico] - Estrutura IF / ELSE
                        
                        if(condição) {
                        ///executa se for verdadeiro
                        }
                        else {
                        ///executa se for falso
                        }
                        """
        ), false);
    }
    public static void sendLoopExplanation(CommandSourceStack source) {
        source.sendSuccess(() -> Component.literal(
                """
                        [Java Básico] - Loop FOR
                        for(int i = 0; i < 5; i++){
                        System.out.println(i);
                        }
                        ///Ele repete um bloco de código enquanto uma condição for verdadeira, no caso do exemplo enquanto i for menor que 5
                        """
        ), false);
    }
    public static void sendVariableExplanation(CommandSourceStack source){
        source.sendSuccess(() -> Component.literal(
                """
                        [Java Básico] - Variáveis
                        int idade = 20;
                        String nome = "Joãozim";
                        double altura = 1.75;
                        boolean ativo = true;
                        
                        Estrutura básica para declaração de variáveis!!
                        """
        ),false);
    }
}

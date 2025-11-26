package com.javacraft.commands;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.List;
import java.util.Objects;

public class CommandMessages {
    static String varName;
    static String varValue;
    static String varType;
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
    public static void sendHelpText(CommandSourceStack source) {
        MutableComponent message = Component.empty();
        // Função para ocupar uma linha inteira
        StringBuilder obfText = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            obfText.append("A");
        }
        MutableComponent obfuscatedLine = Component.literal(obfText.toString())
                .withStyle(ChatFormatting.OBFUSCATED);
        message.append(obfuscatedLine)
                .append(Component.literal("\n\n\n\n"));
        message.append(Component.literal("[JAVA] Para utilizar o mod, use os seguintes comandos:\n\n"))
                .append(Component.literal("•/java_if\n").withStyle(ChatFormatting.RED))
                .append(Component.literal("•/java_loop\n").withStyle(ChatFormatting.GREEN))
                .append(Component.literal("•/java_variavel\n\n\n\n").withStyle(ChatFormatting.BLUE))
                .append(obfuscatedLine);

        source.sendSuccess(() -> message, false);
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
    // Renderiza uma tela ao invés de mandar algo no chat.
    public static void sendVariableExplanation(){
        Minecraft.getInstance().execute(() -> {
            Minecraft.getInstance().setScreen(new InfoScreen("variables"));
        });
    }

    public static void summonVariable(CommandSourceStack source){
        if (varValue != null && !varValue.isBlank() && varName != null && !varName.isBlank()) {
            source.sendSuccess(() -> Component.literal("✅ Sua variável ("  + varType  + ") é: "   + varName + "\n" + "✅ Com valor: " + varValue), false);
        }
        else {
            source.sendSuccess(() -> Component.literal("❌ Você não declarou uma variável ainda."), false);
        }

    }


    // Criador da tela
    public static class InfoScreen extends Screen {
        EditBox input1;
        EditBox input2;
        final String text;
        List<String> options = List.of("int", "String", "bool");
        //Se o index for 1 é int, se for 2 é String etc...
        int selectedIndex = 0;



        public InfoScreen(String type) {
            // Será reutilizável,

            super(Component.literal(Objects.equals(type, "variables") ? "Variáveis em Java" : "---"));
            this.text = "[Java Básico] - Variáveis\nExemplo:\nint idade = 20;\nString nome = \"Joãozim\";\n...";
        }
        // Inicia a renderização
        @Override
        protected void init() {
                int buttonWidth = 100;
                int buttonHeight = 20;

                // Botão para selecionar o tipo da variável [Incompleto]
                int x = (this.width - buttonWidth) / 2;
                int y = (this.height / 2) - 40;
                Button dropdownButton = Button.builder(Component.literal(options.get(selectedIndex)),
                                b -> {
                                    selectedIndex++;
                                    if (selectedIndex >= options.size()) selectedIndex = 0;
                                    b.setMessage(Component.literal(options.get(selectedIndex)));
                                })
                        .bounds(x, y, buttonWidth, buttonHeight)
                        .build();
                this.addRenderableWidget(dropdownButton);

                int rowY = this.height / 2;

                input1 = new EditBox(this.font, this.width / 2 - 150, rowY, 80, 20, Component.literal("Escreva aqui o nome da sua variável")){
                    @Override
                    public void setFocused(boolean focused) {
                        super.setFocused(focused);
                        if (focused) {
                            this.setSuggestion("");
                        }
                    }
                };
                input1.setSuggestion("Variável");
                this.addRenderableWidget(input1);


                input2 = new EditBox(this.font, this.width / 2 + 70, rowY, 80, 20, Component.literal("Atribua um valor à sua variável")){
                    // Solução para apagar a sugestão do input quando o usuário clicar nela.
                    @Override
                    public void setFocused(boolean focused) {
                        super.setFocused(focused);
                        if (focused) {
                            this.setSuggestion("");
                        }
                    }
                };
                input2.setSuggestion("Valor");
                this.addRenderableWidget(input2);

                this.addRenderableWidget(Button.builder(Component.literal("OK"), (button) -> {
                    varType = options.get(selectedIndex);
                    varName = input1.getValue();
                    varValue = input2.getValue();

                    // Checa se os valores são nulos ou não
                    if (!varValue.isBlank() && !varName.isBlank()){
                        Minecraft.getInstance().player.sendSystemMessage(Component.literal("Variável (" + varType + ") " + varName + " = " + varValue));
                    }



                    this.onClose();
                }).bounds(this.width / 2 - 40, this.height / 2 + 20, 80, 20).build());
            }

            @Override
            public void render(net.minecraft.client.gui.GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
                if (this.minecraft != null && this.minecraft.level != null) {
                    this.renderBackground(graphics);
                }
                graphics.drawCenteredString(this.font, this.title.getString(), this.width / 2, 20, 0xFFFFFF);
                int startY = 40;

                for (String line : text.split("\n")) {
                    graphics.drawCenteredString(this.font, line, this.width / 2, startY, 0xFFFFFF);
                    startY += 10;
                }

                graphics.drawCenteredString(this.font,
                        "=",
                        this.width / 2,
                        this.height / 2 + 4,
                        0xFFFFFF);

                super.render(graphics, mouseX, mouseY, partialTicks);

        }


}}

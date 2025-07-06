
# 🧮 Calculadora Java Swing

Este é um projeto de uma **Calculadora Gráfica** desenvolvida em **Java** com **Swing**, utilizando uma arquitetura
**MVC simplificada** (a camada View está implementada neste exemplo). A calculadora oferece uma interface amigável
com funcionalidades básicas de operações aritméticas e algumas funções de memória.

## 📸 Captura de Tela

*(adicione aqui uma imagem do aplicativo rodando, se desejar)*

---

## 🚀 Funcionalidades

- Interface gráfica com layout moderno.
- Suporte às principais operações aritméticas: `+`, `-`, `*`, `/`.
- Teclas de memória: `MC`, `MR`, `MS`, `M+`, `M-` *(sem funcionalidade ainda)*.
- Operações adicionais:
  - `←`: Apaga o último dígito.
  - `CE` e `C`: Limpa o visor e histórico.
  - `±`, `√`, `⅟x`, `%`: Botões presentes mas ainda não implementados.
- Avaliação de expressões com dois operandos (ex: `2 + 3 = 5`).

---

## 🛠️ Tecnologias Utilizadas

- **Java SE**
- **Swing (javax.swing)** para a interface gráfica.
- **Layouts**: `BorderLayout`, `GridBagLayout`, `GridLayout`
- `ActionListener` para captura de eventos de botões.

---

## 📁 Estrutura de Arquivos

```
Calculadora/
├── View/
│   └── Calculadora.java     # Classe principal com GUI e lógica
├── Model/                   # (Recomendado) Lógica e estrutura de dados
├── Controller/              # (Recomendado) Coordenação entre Model e View
├── README.md
```

---

## ▶️ Como Executar

### 1. Requisitos

- Java Development Kit (JDK 8 ou superior) instalado.
- IDE (IntelliJ, Eclipse, NetBeans) ou terminal com `javac`.

### 2. Compilar e Executar

No terminal:
```bash
javac View/Calculadora.java
java View.Calculadora
```

---

## 🔄 Melhorias Futuras

- Implementar funções avançadas (`±`, `√`, `⅟x`, `%`, memória).
- Separar lógica em camadas reais de **Model** (com DAO, ValueObject, BO).
- Adicionar testes unitários.
- Melhorar tratamento de erros (divisão por zero, entradas inválidas).
- Suporte a expressões com múltiplos operadores (ex: `2 + 3 * 4`).

---

## 👨🏽‍💻 Autor

**Omar Davide Xavier**  
Moçambique 🇲🇿  

---

## 📜 Licença

Este projeto é de uso livre para fins educativos.  
Sinta-se à vontade para modificar e expandir!

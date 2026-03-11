# 📱 SysInfoAPP

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)

Uma aplicação Android nativa e minimalista desenvolvida no âmbito da unidade curricular de **Desenvolvimento de Aplicações Móveis (DAM)**. O principal objetivo da SysInfoAPP é extrair e apresentar instantaneamente especificações vitais de hardware e software do dispositivo físico ou emulador sobre o qual está a ser executada.

### 📑 Documentação Académica

Um relatório exaustivo foi elaborado sobre toda a lógica de programação, arquitetura e decisões de design. **[Pode consultar o Relatório Técnico aqui](Relatorio_SysInfoAPP.md).**

---

## 🚀 Funcionalidades Principais

- **Informação de Hardware:** Modelo, Marca (Brand), Fabricante (Manufacturer), Dispositivo e Informações de Hardware de Baixo Nível (ID, Bootloader).
- **Informação do Sistema Operativo:** Versão Canónica do Android (Release), Nível da API (SDK), Versão Base e ID da Build.
- **Interface Imersiva (Edge-to-Edge):** A aplicação suporta *Window Insets*, fluindo de forma responsiva sob a barra de estado e a barra de navegação para uma experiência de utilizador otimizada e livre de recortes rudes marginais.
- **Sem Permissões Intrusivas:** A app comunica eficazmente com as propriedades estáticas de `android.os.Build` nativas da framework, abdicando de requisições de privacidade obstrutivas ou solicitações em tempo de execução (*runtime permissions*).

## 📸 Capturas de Ecrã (Screenshots)

> *(Sugestão: Adicione as capturas de ecrã do funcionamento da app aqui para robustecer a apresentação)*  
<img src="https://via.placeholder.com/200x400.png?text=Preenchimento+App" width="200" alt="SysInfoAPP Demo Screenshot">

## 🛠️ Tecnologias e Arquitetura

- **Linguagem:** [Kotlin](https://kotlinlang.org/)
- **Paradigma de UI:** Layouts XML clássicos, ancorados no poderoso `ConstraintLayout`.
- **Bibliotecas Principais:** 
  - `androidx.core:core-ktx`
  - `androidx.appcompat:appcompat`
  - `androidx.constraintlayout:constraintlayout`

## ⚙️ Instalação e Execução

Para clonar e executar esta aplicação localmente e aceder ao log de código fonte, necessita de ter o [Git](https://git-scm.com) e o [Android Studio](https://developer.android.com/studio) devidamente configurados no seu ambiente de trabalho (Workspace).

```bash
# 1. Clonar o repositório
$ git clone https://github.com/Ruben23Z/DAM-TP1-SysInfoAPP.git

# 2. Aceder ao diretório transposto
$ cd DAM-TP1-SysInfoAPP
```

**Passos em ambiente de IDE:**
1. Inicie e abra o **Android Studio**.
2. No menu principal ou ecrã inicial de boas-vindas, selecione `Open` (ou File > Open) e indique o trajeto da pasta do repositório clonada.
3. Aguarde que o _Gradle_ sincronize todas as dependências (`Build` > `Make Project`).
4. Execute via emulador ou conectando um dispositivo físico utilizando a funcionalidade de Run (Botão verde ▶️ ou *Shift + F10*).

## 🧠 Ficheiros Nucleares do Esqueleto (Arquitetura)

- `MainActivity.kt`: Hospeda a lógica de negócio estrutural. Contém os subrotinas inerentes ao Ciclo de Vida (*Activity Lifecycle*) e encarrega-se de interpelar a sandbox via o componente nativo para devolver a panóplia métrica em *String*.
- `activity_main.xml`: Hierarquia basilar em UI assente num `ConstraintLayout` de resiliência e adaptabilidade escalar que alberga instâncias customizadas de `TextView` (para o cabeçalho) e `EditText` em leitura imperativa (para rolagem dinâmica dos dados extraídos). 

## 📝 Licença e Atribuição

Este projeto constitui um trabalho académico universitário, focado na exploração tecnológica dos fundamentos operacionais de Kotlin sobre Android.

**Desenvolvido por:** Rúben (Ruben23Z)
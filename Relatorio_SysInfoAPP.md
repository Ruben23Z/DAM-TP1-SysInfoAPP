# Relatório Técnico de Desenvolvimento: Aplicação Móvel "SysInfoAPP"

**Unidade Curricular:** Desenvolvimento de Aplicações Móveis (DAM)  
**Autor:** Ruben Zhang
**Data:** Março de 2026
**Instituição:** Instituto Superior de Engenharia de Lisboa(ISEL) 

---

## 1. Abstract

O presente documento consubstancia o relatório referente à idealização, conceção e implementação da aplicação móvel denominada **SysInfoAPP**, desenvolvida nativamente para o ecossistema Android. O principal fito desta aplicação é a extração e exibição, em tempo real, dos metadados referentes ao hardware e software do dispositivo hospedeiro. Através deste relatório, é dissecada a arquitetura do projeto, as escolhas tecnológicas efetuadas, a estruturação da interface de utilizador e a lógica subjacente ao código-fonte, evidenciando as competências adquiridas no âmbito do desenvolvimento em Kotlin e da interação com a API do Android.

## 2. Introdução

No corolário da ubiquidade dos dispositivos móveis, a compreensão aprofundada das especificidades de hardware e das versões de sistema operativo consubstancia um requisito fulcral, quer para propósitos de depuração (debugging) por parte de programadores, quer para a saciação da curiosidade tecnológica do utilizador comum. A **SysInfoAPP** emerge como uma solução minimalista e eficiente para colmatar esta premissa.

A aplicação foi gizada com o intuito pedagógico e utilitário de demonstrar de que forma a framework de desenvolvimento Android providencia acesso direto às propriedades basilares do dispositivo, abdicando de permissões intrusivas ou operações assíncronas complexas na sua iteração embrionária, privilegiando a robustez e a celeridade.

## 3. Enquadramento Tecnológico

O alicerce tecnológico da SysInfoAPP repousa nas seguintes idiossincrasias e paradigmas:

*   **Linguagem de Programação - Kotlin:** Adotada pela sua concisão, expressividade e segurança intrínseca contra referências nulas (*null safety*), o Kotlin afigura-se como o padrão-ouro (standard) contemporâneo preconizado pela Google para o desenvolvimento Android.
*   **Android SDK:** Fornece as bibliotecas e APIs imperativas para a construção de aplicações, com especial ênfase na classe `android.os.Build`, que atua como o repositório de constantes estáticas representativas do sistema.
*   **Interface Gráfica - XML:** A componente visual é orquestrada através da Extensible Markup Language (XML), potenciando o uso do `ConstraintLayout` para assegurar a responsividade e o alinhamento profícuo dos elementos bidimensionais (Views) em ecrãs de dimensões heterogéneas.

## 4. Estrutura e Arquitetura do Projeto

O modelo arquitetural prostra-se sobre o padrão MVC (Model-View-Controller) tradicionalmente apanagiado por aplicações Android clássicas:

*   **View (Vista):** Materializada no ficheiro `activity_main.xml`. Estabelece a disposição espacial dos componentes visuais.
*   **Controller/Model (Controlador/Modelo):** Concentrados na `MainActivity.kt`. A atividade atua concomitantemente na recuperação de dados da API e na sua injeção (binding) nos recetáculos visuais da interface.

## 5. Lógica de Programação e Análise Criteriosa do Código-Fonte

O cerne da inteligibilidade da aplicação reside numa exegese minuciosa do comportamento da classe `MainActivity`.

### 5.1. Ciclo de Vida da Atividade: O Método `onCreate`

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_main)
    // ...
}
```
A génese da interface e a alocação de recursos ocorrem impreterivelmente no método primordial do ciclo de vida da `Activity`: `onCreate`. 
*   A invocação de `super.onCreate(savedInstanceState)` garante a restauração e correta inicialização do estado pretérito da atividade.
*   A função `enableEdgeToEdge()` ilustra a adesão a práticas de design moderno, permitindo que a aplicação flua por todo o ecrã, incluindo as áreas subjacentes às barras do sistema, proporcionando uma experiência imersiva e limiar.
*   `setContentView(R.layout.activity_main)` estabelece a ponte (inflação) entre a lógica de negócio e o ficheiro XML respetivo.

### 5.2. Extração de Componentes do Layout (View Binding)

```kotlin
val textView = findViewById<TextView>(R.id.textInfo)
```
Apesar de tipicamente denominado `textInfo`, deve notar-se que, devido à configuração do XML, o recetáculo manipulado assume também contornos de um `EditText` configurado como elemento de apenas leitura (*read-only*), imiscuindo uma capacidade inata de realizar rolagem longitudinal (scroll) caso o volume de dados suplante a altura delimitada no ecrã. O paradigma clássico `findViewById` ancora a referência do XML num objeto Kotlin maleável em memória.

### 5.3. Interpelação da Sandbox do Sistema via 'android.os.Build'

O epílogo da lógica processual consolida-se através de um *String Template* multinível (delimitado por três aspas assinalando a multilinearidade textual), conjugado simultaneamente com o comando `.trimIndent()`, operando uma mitigação cirúrgica dos espaços ou indentações espúrias decorrentes da tabulação no código-fonte.

```kotlin
val info = """
Manufacturer: ${Build.MANUFACTURER}
Model: ${Build.MODEL}
Brand: ${Build.BRAND}
Device: ${Build.DEVICE}
User: ${Build.USER}
Base: ${Build.VERSION_CODES.BASE}
Incremental: ${Build.VERSION.INCREMENTAL}
SDK: ${Build.VERSION.SDK_INT}
Version Code: ${Build.VERSION.RELEASE}
Display: ${Build.DISPLAY}
Hardware: ${Build.HARDWARE}
Host: ${Build.HOST}
ID: ${Build.ID}
""".trimIndent()
```
*   **`Build.MANUFACTURER`, `MODEL`, `BRAND`**: Revelam a genealogia comercial e industrial do dispositivo (e.g., Samsung, SM-G998B).
*   **`Build.VERSION.SDK_INT` e `RELEASE`**: Moles propulsoras essenciais para gerir compatibilidade aplicacional intrínseca. `SDK_INT` expõe o nível da API (ex: 34), e `RELEASE` apresenta ao utilizador final a versão canónica (ex: Android 14).
*   **`Build.HARDWARE`, `DEVICE`, `ID`**: Proporcionam rastreabilidade física de baixo nível, frequentemente fundamentais para despiste holístico de bugs que ocorrem incisivamente na panóplia fragmentada e díspar de dispositivos alojados no sistema Android.

### 5.4. Exibição e Tratamento de Insets (Transições de Ecrã)

```kotlin
textView.text = info
ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
    insets
}
```
Uma vez compilado o repositório de variáveis na epígrafe `info`, procede-se à sua assimilação visual instruindo o texto à view. Simultaneamente, a aplicação aplica a pragmática exímia da gestão dos *Window Insets*. Esta lógica providencia proativamente a injunção de margens dinâmicas (Paddings), esquivando-se assertivamente da sobreposição indesejada da IU (Interface de Utilizador) com o recortes de câmara (Notch) ou com os botões físicos e ou de hardware e barras de navegação ou sistema na base de ecrã (Edge-to-Edge compatibility).

## 6. Design da Interface do Utilizador (UI) e Layout

O esqueleto gráfico está ancorado no uso do `ConstraintLayout`, que proporciona um nível ímpar de escalabilidade posicional das vistas sem originar alinhamentos hierárquicos aninhados dispendiosos em computação gráfica (View hierarchy flattening).

O ecrã divide-se em dois elementos nucleares:
1.  **Cabeçalho Estético**: Um `TextView` decorado e robusto, possuindo um fundo arroxeado (`@color/purple`) e uma tipografia imponente e nítida e negrito (`34sp` com estiloso *bold*).
2.  **Corpo Apresentador (EditText Read-only)**: O elemento `textInfo`, engenhado meticulosamente não como uma vista de inserção mas como de demonstração. Estão inertes as variáveis nativas de inserção tipográfica estancando-se atributos cirúrgicos como:
    *   `android:clickable="false"` e `android:focusable="false"` repulsando o cursor de entrada ou invocação do teclado virtual.
    *   `android:inputType="textMultiLine"` fomentando e habilitando a visibilidade polifásica do texto, em convergência com uma marginação exímia e salutar (`android:padding="16dp"`).

## 7. Conclusão e Perspetivas Futuras (Roadmap)

A conceção da SysInfoAPP superou em pleno os requisitos embrionários subscritos, consubstanciando-se como uma epifania técnica eloquente da agilidade de extração de métricas estruturais usando Kotlin. O código edificado apresenta uma resiliência superlativa derivante das boas práticas apreendidas (i.e. edge-to-edge UI).

**Aprimoramentos Futuros sugeridos:**
*   Implementação de uma injeção de dependências ou *View Binding* e abolição progressiva de `findViewById`.
*   Extensão da aplicação para recolha de dados efémeros e mutáveis tais como a percentagem de uso da bateria, uso e temperatura de CPU, carecendo da gestão assíncrona com manifestações e interligação com os *BroadcastReceivers*.
*   Reestruturação UI adotando Jetpack Compose para conformidade acérrima aos padrões do estado da arte do desenvolvimento android vindouros.

---
*Fim do Relatório*

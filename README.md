# Sistema de Controle Bancário

Em um banco, haverá arquivos de **Contas**, **Movimento** e **Atualizado**.

* Criar um sistema que cadastre contas de 5 clientes;
* Gravar também o cadastro de movimento com 10 registros contendo código da conta, valor do movimento, tipo de movimento e `statusM`;
* Após a geração dos cadastros (contas e movimento), processar e gerar as **Contas Atualizadas**, assim como suas consultas.

---

## Estrutura dos Arquivos

### ContasCorrentes
| codContaC | nomeClientesC | saldoContaC | LimitecontaC | tipoContaC |
| :--- | :--- | :--- | :--- | :--- |
| `int` | `String` | `double` | `double` | `int` |

#### `tipoContaC`
* **1** — Física
* **2** — Conjunta
* **3** — Jurídica
* **4** — Especial

---

### MovimentoContas
| codContaM | ValorMovimentoM | TipoMovimentoM | statusM |
| :--- | :--- | :--- | :--- |
| `int` | `double` | `int` | `int` |

#### `TipoMovimento`
* **1** — Crédito
* **2** — Débito

---

### ContasAtualizados
| codContaA | nomeClientesA | saldoContaA | LimitecontaA | tipoContaA | (Outros campos conforme regra) |
| :--- | :--- | :--- | :--- | :--- | :--- |
| `int` | `String` | `double` | `int` | `int` | `int` |

---

## Regras de Processamento

* Classificar ambos os arquivos em ordem crescente de `codConta`.
* Gerar o `CadastroAtualizado` a partir do método **Balance-line** utilizando os arquivos `ContasCorrentes` e `MovimentoContas`.

### Regras de Atualização

1. **Identificar a partir do `codConta` seu correspondente no `codContaM`, caso exista:**
   * **1.1** Se `statusM = 1`, gravar no `ContasAtualizado`, conforme o layout.
   * **1.2** Se `statusM = 2`, verificar o `TipoMovimentoM`:
     * **1.2.1** Caso `saldoContaC < ValorMovimentoM`:
       * Critério do valor limite baseado em `tipoContaC`:
         * **1** --------- Zero
         * **2** --------- `LimitecontaC`
         * **3** --------- `LimitecontaC + 50% saldoContaC`
         * **4** --------- `LimitecontaC + 100% saldoContaC`
     * **1.2.2** Caso `saldoContaC > ValorMovimentoM`:
       * Calcular a subtração dos valores. Se o resultado for negativo, utilizar o critério do valor limite.
2. Se `codConta < codContaM`, gravar dados no `ContasAtualizados` sem alteração.

---

## Menus do Sistema

### Menu Principal
```text
======================================
           MENU PRINCIPAL             
======================================
1 - Cadastra Contas Correntes         
2 - Cadastra movimento                
3 - Gera Contas Atualizadas           
4 - Consulta cadastros                
9 - FIM                               
======================================


```
### Menu de Consulta de Cadastros
```text
======================================
         Consulta Cadastros           
======================================
1 - Contas Correntes                  
2 - Movimento                         
3 - Atualizado                        
9 - FIM                               
======================================

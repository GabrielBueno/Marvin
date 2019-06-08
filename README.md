# Marvin

O Marvin é um carro controlado por bluetooth, através do módulo **HC-05**, interfaceado com o **Arduino**.

## Protocolo de comunicação

Para enviar um comando ao marvin (ir para frente, para trás, etc), utilizamos somente um byte,
onde os seus 4 bits mais significativos contém informações para o motor da **direita**, enquanto
os 4 menos significativos possuem informações para o motor da **esquerda**.

`r00e r00d`

Onde:
    - **e**: Quando este bit está ligado, indica que o motor da esquerda deve ser ligado.
    - **d**: Quando este bit está ligado, indica que o motor da direita deve ser ligado.
    - **r**: Quando este bit está ligado, indica que o motor correspondente deve ter a rotação invertida.

**0x11**: Para frente

**0x99**: Ré

**0x10**: Vira para a direita

**0x01**: Vira para a esquerda

**0x91**: Curva fechada para a direita

**0x19**: Curva fechada para a esquerda

**0x00**: Ponto morto

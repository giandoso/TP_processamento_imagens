O projeto foi desenvolvido no NetBeans, portando pode ser aberto através da interface gráfica do mesmo(ctrl + shift + O) na pasta raiz deste projeto. 
Caso deseje abrir de outra forma, os arquivos .java estão localizados dentro da pasta src. 

Dentro do arquivo processamento_de_imagens.java(que contêm o main), temos o código responsável por invocar cada máscara. 
O código não requer nenhuma interação, tem como entrada a imagem .pgm, que pode ser alterada ao trocar o caminho passado por parâmetro ao construtor da imagem.
Temos a saída do código com as 6 imagens, cada qual com uma das máscaras solicitadas.
A execução do código irá popular a pasta processamento_de_imagens/src/processamento_de_imagens/filtered_images com todas os arquivos.pgm de saída do algoritmo A classe Imagem(arquivo imagem.java) possui a implementação de todos os métodos referentes as transformações solicitadas, além disso realiza a leitura e escrita das imagens. 

Praticamente toda a lógica e algoritmos relevantes estão na classe imagem.
Todas as mascaras são métodos da classe imagem e possuem lógica similar.

O projeto foi desenvolvido em ambiente linux, porém também deve executar em windows, além disso foi minimamente documento para orientar a compreensão.

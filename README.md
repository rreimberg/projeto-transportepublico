# Projeto Transporte Público (Hackatona do Ônibus)

## Cadê o Ônibus?

["Cadê o Ônibus?"](http://www.cadeoonibus.com.br/) surgiu da necessidade de disponibilizar as informações do sistema Olho Vivo em formato que pudesse ser utilizado em situações do cotidiano, como no ponto de ônibus. A primeira versão do aplicativo foi lançada em 2011, e extraía as informações de AVL dos arquivos em formato *xml* que atualizavam a primeira página do Olho Vivo, que mais tarde foi denominada [De Olho na Via](http://olhovivo.sptrans.com.br/#pDeOlhoVia).

Desde seu lançamento muitas informações foram adicionadas aos dados inicialmente coletados, como por exemplo o tipo do ônibus (micro-ônibus, ônibus, articulado ou bi-articulado), na intenção de melhorar a experiência do usuário e nutrí-lo de informação suficiente para que possa tomar uma decisão segura sobre esperar o ônibus de uma linha, ou utilizar uma linha alternativa.

A partir das lições aprendidas o grupo participou da 1ª Hackatona do Ônibus com 4 propostas, centradas na coleta e divulgação da informação através de dispositivos móveis, tendo como principais atores neste processo o usuário de transporte público e o cobrador de ônibus.

## Hackatona

As propostas apresentadas pela Equipe Nanoit são:

1. Painel do Cobrador
2. Aplicativo do Usuário
3. API
4. QRCODE nos pontos de ônibus

### Painel do Cobrador

O Painel do Cobrador é um aplicativo para tablet que seria instalado nos coletivos numa posição que possibilitasse o uso por parte do cobrador sem prejuízo de suas atribuições. Através do Painel pode-se informar as condições da viagem em relação a trânsito e lotação do veículo.

Acreditamos que o Cobrador pode desempenhar um papel importante na geração de informações em tempo real a respeito da situação da viagem e, para tanto, o Painel envia a informação para um serviço web, para que tais informações sejam imediatamente disponibilizadas para os usuários. Além destas informações através do Painel é possível relatar incidentes ocorridos durante a viagem, tais como: assalto, depredação, perturbação da ordem, etc.

Outra funcionalidade importante atrelada ao Painel é a sistematização do processo de Início e Término das viagens, processo realizado através dos relatórios preenchidos pelo cobrador ao longo do dia, que são consolidados manualmente nas garagens de ônibus.

### Aplicativo do Usuário

A principio o aplicativo do Usuário foi desenvolvido na plataforma Android, no entanto há planos para disponibilizá-lo em versões para Ios e Windows Phone.

Dentre as funcionalidades existentes, podemos listar:

:: Consulta de Linhas / Acompanhamento em tempo real

    Funcionalidade responsável por exibir todas as linhas que atendam a um texto informado pelo usuário.
    O retorno da pesquisa listará todas as linhas de ônibus da SPTrans e EMTU.
    Após o usuário selecionar a linha desejada, as mesmas serão exibidas no mapa para acompanhamento em tempo real.
       
:: Trânsito

    Funcionalidade no qual mostra os dados de trânsito de determinadas regiões mapeadas pela SPTrans.
    
:: Lotação

    Funcionalidade no qual exibe no mapa marcações de regiões com trânsito, baseado em informações dadas pelo próprio usuário.
    
:: Notícias

    Funcionalidade no qual listará os últimos tweets da @SPTrans_.
    
:: Ponto a ponto

    Funcionalidade no qual o usuário selecionará um ponto de ônibus de origem e outro de destino, e o próprio aplicativo lista a melhor opção utilizando uma linha de ônibus direta.
    
:: Notificações

    Funcionalidade que envia uma notificação para o celular do usuário quando uma determinada linha está a XX km de um ponto de ônibus informado pelo usuário. 
    
... entre outras informações e funcionalidades adicionais que auxiliam o dia-a-dia do usuário.

### API

A API aqui disponibilizada visa permitir com que qualquer desenvolvedor possa utilizar as informações do transporte público de São Paulo dentro de outros sistemas.

### QRCODE nos pontos de ônibus

O QRCode é um formato universal no qual um determinado conteúdo é traduzido para imagem (semelhante a um código de barras).

A proposta em questão visa criar um código QRCode para cada parada de ônibus, que quando acessado (por qualquer aplicativo de leitura de QRCode) redireciona o usuário para uma página web com informações da parada de ônibus, ônibus próximos, horários, rotas, itinerários, entre outros. Além disso, a página web permitirá com as informações sejam exibidas em outros idiomas.

Por se tratar de uma página web, qualquer plataforma de dispositivo móvel visualizará as informações.
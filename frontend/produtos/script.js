function enviarFormulario() {
    // Obter referências aos campos do formulário
    var nome = document.getElementById('nome').value;
    var marca = document.getElementById('marca').value;
    var tipo = document.getElementById('tipo').value;
    var descricao = document.getElementById('descricao').value;
    var quantidade = document.getElementById('quantidade').value;
    var unidade = document.getElementById('unidade').value;

    // Criar um objeto com os dados do formulário
    var dados = {
        "nome": nome,
        "marca": marca,
        "tipo": tipo,
        "descricao": descricao,
        "quantidade": quantidade,
        "unidade": unidade
    };

    // Converter o objeto de dados para uma string JSON
    var dadosJSON = JSON.stringify(dados);

    // Criar uma nova solicitação XMLHttpRequest
    var xhr = new XMLHttpRequest();

    // Configurar a solicitação
    xhr.open('POST', 'http://localhost:8080/produtos/cadastrarProduto', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    // Lidar com a resposta da solicitação
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            // A solicitação foi bem-sucedida
            console.log('Resposta do servidor:', xhr.responseText);
        } else {
            // A solicitação falhou
            console.error('Erro na solicitação:', xhr.statusText);
        }
    };

    // Lidar com erros de rede
    xhr.onerror = function () {
        console.error('Erro de rede');
    };

    // Enviar a solicitação com os dados do formulário
    xhr.send(dadosJSON);
}
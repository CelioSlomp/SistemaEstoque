function enviarVenda() {
    var idProduto = document.getElementById('idProduto').value;
    var idCliente = document.getElementById('idCliente').value;
    var quantidade = document.getElementById('Quantidade').value;
    var valor = document.getElementById('Valor').value;

    var dados = {
        "idProduto": idProduto,
        "idCliente": idCliente,
        "quantidade": quantidade,
        "valor": valor
    };

    var dadosJSON = JSON.stringify(dados);

    var xhr = new XMLHttpRequest();

    xhr.open('POST', 'http://localhost:8080/vendas/realizarVenda', true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            console.log('Resposta do servidor:', xhr.responseText);
        } else {
            console.error('Erro na solicitação:', xhr.statusText);
        }
    };
    xhr.onerror = function () {
        console.error('Erro de rede');
    };
    xhr.send(dadosJSON);
}

function exibirVendas(jsonData) {
    const vendas = jsonData;
    try {
        const tableBody = document.getElementById('tabelaVendas').getElementsByTagName('tbody')[0];

        tableBody.innerHTML = '';

        vendas.forEach(venda => {
            const newRow = tableBody.insertRow();
            newRow.innerHTML = `
            <td>${venda.prodNome}</td>
            <td>${venda.clieNome}</td>
            <td>${venda.quantidade}</td>
            <td>${venda.valor}</td>
        `;
        });
    } catch { }
}
fetch('http://localhost:8080/vendas/listaVendas')
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro ao carregar as vendas');
        }
        return response.json();
    })
    .then(data => {
        exibirVendas(data);
    })
    .catch(error => {
        console.error('Erro:', error);
    });
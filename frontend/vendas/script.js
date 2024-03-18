function enviarVenda() {
    var idProduto = document.getElementById('produtos').value;
    var idCliente = document.getElementById('clientes').value;
    var quantidade = document.getElementById('Quantidade').value;
    var valor = document.getElementById('Valor').value;

    var dados = {
        "idProduto": idProduto,
        "idCliente": idCliente,
        "quantidade": quantidade,
        "valor": valor
    };

    var dadosJSON = JSON.stringify(dados);
    console.log(dadosJSON);
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

function pegarProdutos(jsonData) {
    const produtos = jsonData;
    try {
        const tableBody = document.getElementById('produtos');

        tableBody.innerHTML = '';

        produtos.forEach(produto => {
            const optionElement = document.createElement('option');
            optionElement.value = produto.id;
            optionElement.textContent = `${produto.nome}, ${produto.marca}`;
            tableBody.appendChild(optionElement);
        });
    } catch (error) {
        console.error('Erro:', error);
    }
}

fetch('http://localhost:8080/produtos/visualizarProdutos')
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro ao carregar os produtos');
        }
        return response.json();
    })
    .then(data => {
        pegarProdutos(data);
    })
    .catch(error => {
        console.error('Erro:', error);
    });

function pegarClientes(jsonData) {
    const clientes = jsonData;
    try {
        const tableBody = document.getElementById('clientes');

        tableBody.innerHTML = '';

        clientes.forEach(cliente => {
            const optionElement = document.createElement('option');
            optionElement.value = cliente.id;
            optionElement.textContent = `${cliente.nome}`;
            tableBody.appendChild(optionElement);
        });
    } catch (error) {
        console.error('Erro:', error);
    }
}

fetch('http://localhost:8080/clientes/visualizarClientes')
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro ao carregar os produtos');
        }
        return response.json();
    })
    .then(data => {
        pegarClientes(data);
    })
    .catch(error => {
        console.error('Erro:', error);
    });
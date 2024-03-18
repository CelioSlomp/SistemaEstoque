function enviarCompra() {
    var id = document.getElementById('produtos').value;
    var quantidade = document.getElementById('Quantidade').value;
    var preco = document.getElementById('Preco').value;

    var dados = {
        "idProduto": id,
        "quantidade": quantidade,
        "vlrPago": preco
    };

    var dadosJSON = JSON.stringify(dados);

    var xhr = new XMLHttpRequest();

    xhr.open('POST', 'http://localhost:8080/compras/realizarCompra', true);
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

function exibirCompras(jsonData) {
    const compras = jsonData;
    try {
        const tableBody = document.getElementById('tabelaCompras').getElementsByTagName('tbody')[0];

        tableBody.innerHTML = '';

        compras.forEach(compra => {
            const newRow = tableBody.insertRow();
            newRow.innerHTML = `
            <td>${compra.nome}</td>
            <td>${compra.quantidade}</td>
            <td>${compra.vlrPago}</td>
        `;
        });
    } catch { }
}

fetch('http://localhost:8080/compras/listaCompras')
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro ao carregar as compras');
        }
        return response.json();
    })
    .then(data => {
        exibirCompras(data);
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
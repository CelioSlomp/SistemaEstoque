function enviarCadastroProduto() {
    var nome = document.getElementById('nome').value;
    var marca = document.getElementById('marca').value;
    var tipo = document.getElementById('tipo').value;
    var descricao = document.getElementById('descricao').value;
    var unidade = document.getElementById('unidade').value;

    var dados = {
        "nome": nome,
        "marca": marca,
        "tipo": tipo,
        "descricao": descricao,
        "unidade": unidade
    };

    var dadosJSON = JSON.stringify(dados);

    var xhr = new XMLHttpRequest();

    xhr.open('POST', 'http://localhost:8080/produtos/cadastrarProduto', true);
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

function enviarCadastroCliente() {
    var nome = document.getElementById('nome').value;
    var bomPag = document.getElementById('bomPag').value;

    var dados = {
        "nome": nome,
        "bomPag": bomPag
    };

    var dadosJSON = JSON.stringify(dados);

    var xhr = new XMLHttpRequest();

    xhr.open('POST', 'http://localhost:8080/clientes/cadastrarCliente', true);
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

function exibirProdutos(jsonData) {
    const produtos = jsonData;

    const tableBody = document.getElementById('tabelaProdutos').getElementsByTagName('tbody')[0];
    
    tableBody.innerHTML = '';

    produtos.forEach(produto => {
        const newRow = tableBody.insertRow();
        newRow.innerHTML = `
            <td>${produto.id}</td>
            <td>${produto.nome}</td>
            <td>${produto.marca}</td>
            <td>${produto.tipo}</td>
            <td>${produto.unidade}</td>
            <td>${produto.quantidade}</td>
            <td>${produto.vlrPago}</td>
        `;
    });
}

fetch('http://localhost:8080/produtos/visualizarProdutos')
.then(response => {
    if (!response.ok) {
      throw new Error('Erro ao carregar os produtos');
    }
    return response.json();
})
.then(data => {
    exibirProdutos(data);
})
.catch(error => {
    console.error('Erro:', error);
});


function exibirClientes(jsonData) {
    const clientes = jsonData;

    const tableBody = document.getElementById('tabelaClientes').getElementsByTagName('tbody')[0];
    
    tableBody.innerHTML = '';

    clientes.forEach(cliente => {
        const newRow = tableBody.insertRow();
        newRow.innerHTML = `
            <td>${cliente.id}</td>
            <td>${cliente.nome}</td>
            <td>${cliente.bomPag}</td>
        `;
    });
}

fetch('http://localhost:8080/clientes/visualizarClientes')
.then(response => {
    if (!response.ok) {
      throw new Error('Erro ao carregar os clientes');
    }
    return response.json();
})
.then(data => {
    exibirClientes(data);
})
.catch(error => {
    console.error('Erro:', error);
});

function deletarCliente() {
    var id = document.getElementById('deleteCliente').value;
    console.log(id);
}